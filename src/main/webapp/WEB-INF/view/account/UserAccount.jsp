<%--
  Created by IntelliJ IDEA.
  User: shalithasenanayaka
  Date: 2019-10-02
  Time: 07:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <!-- Required meta tags-->
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Title Page-->
        <title>365Care - Account</title>
        <!-- Jquery JS-->
        <script src="../../../resources/libs/jquery/jquery-3.4.1.min.js"></script>


        <!-- Jquery Confirm JS-->
        <script src="../../../resources/libs/jquery-confirm/jquery-confirm.min.js"></script>
        <link href="../../../resources/libs/jquery-confirm/jquery-confirm.min.css" rel="stylesheet">

        <!-- Icons font CSS-->
        <link href="../../../resources/libs/vendor/mdi-font/css/material-design-iconic-font.min.css" rel="stylesheet" media="all">
        <link href="../../../resources/libs/vendor/font-awesome-4.7/css/font-awesome.min.css" rel="stylesheet" media="all">

        <script src="../../../resources/scripts/script-default.js"></script>

        <!-- Icons font CSS-->
        <link href="../../../resources/libs/vendor/mdi-font/css/material-design-iconic-font.min.css" rel="stylesheet" media="all">
        <link href="../../../resources/libs/vendor/font-awesome-4.7/css/font-awesome.min.css" rel="stylesheet" media="all">
        <!-- Font special for pages-->
        <link href="https://fonts.googleapis.com/css?family=Poppins:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

        <!-- Vendor CSS-->
        <link href="../../../resources/libs/vendor/select2/select2.min.css" rel="stylesheet" media="all">
        <link href="../../../resources/libs/vendor/datepicker/daterangepicker.css" rel="stylesheet" media="all">

        <!-- Main CSS-->
        <link href="../../../resources/libs/vendor/main.css" rel="stylesheet" media="all">
        <script src="../../../resources/scripts/script-default.js"></script>


        <script src="../../../resources/scripts/toastMessageHandler.js"></script>
        <script src="../../../resources/libs/momentjs/moment.min.js"></script>

        <link href="../../../resources/styles/style-useraccount.css" rel="stylesheet">

        <style>
            .mt-1{
                margin-bottom: 1% !important;
            }

        </style>

        <script>


            $(document).ready(function () {
                $('#home-tab').click();
                $('.con-edit').hide();


            });

            var isEditMode = false;
            var UserID = ${UserID};

            function BtnEditOnClick() {
                if(!isEditMode){
                    $('.con-view').hide();
                    $('.con-edit').show();

                    var date = $('#viewDOB').html();
                    var date_string = moment(date, "YYYY-MM-DD").format("MM/DD/YYYY");

                    $('#txtDOB').val(date_string);

                    var gender = $('#viewGender').html();
                    if(gender == 'Male'){
                        $('#rdoMale').click();
                    }else{
                        $('#rdoFeMale').click();
                    }
                    var radioValue = $("input[name='gender']:checked").val();

                    isEditMode = true;
                }else{
                    $('.con-view').show();
                    $('.con-edit').hide();
                    isEditMode = false;
                }
            }
            
            function saveUserDetails() {
                var controllers = CheckFormTextElementsIsEmpty('home');

                if(controllers.length > 0){
                    jQuery.each( controllers, function( i, val ) {
                        $('#'+val).addClass('has-error');
                    });

                    StartToasterMessage(MESSAGE_DANGER, REQUIRED_FIELD, 'required');
                    return;
                }

                if($('#txtPasswordU').val() != $('#txtConfirmPasswordU').val()){
                    $('#txtPasswordU').addClass('has-error');
                    $('#txtConfirmPasswordU').addClass('has-error');

                    StartToasterMessage(MESSAGE_DANGER, PASSWORD_DOESNOT_MATCH, 'required');
                    return;
                }

                UpdateUser();
            }

            function UpdateUser() {

                var FirstName = $('#txtFirstNameName').val();
                var LastName = $('#txtLastName').val();
                var GenderRadioValue = $("input[name='gender']:checked").val();
                var DateOfBirth = $('#txtDOB').val();
                var phone = $('#txtContact').val();
                var password = $('#txtPasswordU').val();

                $.ajax({
                    url: 'account',
                    type: 'post',
                    data: {
                        "UserID": UserID,
                        "first_name" : FirstName ,
                        "last_name" : LastName,
                        "birthday" : DateOfBirth,
                        "gender" : GenderRadioValue,
                        "phone" : phone,
                        "password" : password,
                        "command" : "update"
                    },
                    beforeSend: function() {
                        $('#loadingSpinner').show();
                    },
                    error: function (request, status, error) {
                        $('#loadingSpinner').hide();
                        window.location = "Error";
                    },
                    success: function(response) {
                        //console.log(response);
                        $('#loadingSpinner').hide();
                        var result = JSON.parse(response);
                        if(result.result == 'false'){
                            StartToasterMessage(MESSAGE_DANGER, result.message, 'Notice');
                        }else{
                            location.reload(true);
                        }

                        ClearSignUpFields();

                    }
                });

            }

        </script>
    </head>
    <body>

        <%@include file="../site-master/header.jsp"%>

        <div class="container emp-profile">
            <form method="post">
                <div class="row">
                    <div class="col-md-4">
                        <div class="profile-img">
                            <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS52y5aInsxSm31CvHOFHWujqUx_wWTS9iM6s7BAm21oEN_RiGoog" alt=""/>
                            <div class="file btn btn-lg btn-primary">
                                Change Photo
                                <input type="file" name="file"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="profile-head">
                            <h5>
                                ${FirstName} ${LastName}
                            </h5>
                            <p class="proile-rating">RANKINGS : <span>8/10</span></p>
                            <ul class="nav nav-tabs" id="myTab" role="tablist">
                                <li class="nav-item">
                                    <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true">About</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile" aria-selected="false">Timeline</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-md-2">
                        <input type="button" class="profile-edit-btn" id="btnEdit" onclick="BtnEditOnClick()" value="Edit Profile"/>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-4">
                        <div class="profile-work">
                            <p>Nevigation</p>
                            <a href="">Home</a><br/>
                            <a href="">Appointments</a><br/>
                            <p>My Appointments</p>
                            <a href="">Dr. Nalin 20.00 Today</a><br/>
                            <a href="">Dr. Gayan 15.30 Tomorrow</a><br/>
                            <a href="">Dr. Sheron 19.20 20/12/2019</a><br/>
                            <br>
                            <br>
                            <br>
                            <br>
                        </div>
                    </div>
                    <div class="col-md-8">
                        <div class="tab-content profile-tab" id="myTabContent">
                            <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                                <div class="row  mt-1">
                                    <div class="col-md-6">
                                        <label>First Name</label>
                                    </div>
                                    <div class="col-md-6">
                                        <p class="con-view">${FirstName}</p>
                                        <input type="text" class="form-control con-edit" id="txtFirstNameName" value="${FirstName}" placeholder="First Name">
                                    </div>
                                </div>
                                <div class="row  mt-1">
                                    <div class="col-md-6">
                                        <label>Last Name</label>
                                    </div>
                                    <div class="col-md-6">
                                        <p class="con-view">${LastName}</p>
                                        <input type="text" class="form-control con-edit" id="txtLastName" value="${LastName}" placeholder="Last Name">
                                    </div>
                                </div>
                                <div class="row  mt-1">
                                    <div class="col-md-6">
                                        <label>Email</label>
                                    </div>
                                    <div class="col-md-6">
                                        <p class="con-view">${Email}</p>
                                        <input type="email" class="form-control con-edit" id="txtEmail" disabled value="${Email}">
                                    </div>
                                </div>
                                <div class="row  mt-1">
                                    <div class="col-md-6">
                                        <label>Gender</label>
                                    </div>
                                    <div class="col-md-6">
                                        <p class="con-view" id="viewGender">${Gender}</p>
                                        <div class="p-t-10 con-edit">
                                            <label class="radio-container m-r-45">Male
                                                <input type="radio" id="rdoMale" value="1" checked="checked" name="gender">
                                                <span class="checkmark"></span>
                                            </label>
                                            <label class="radio-container">Female
                                                <input type="radio" id="rdoFeMale" value="0" name="gender">
                                                <span class="checkmark"></span>
                                            </label>
                                        </div>
                                    </div>
                                </div>
                                <div class="row  mt-1">
                                    <div class="col-md-6">
                                        <label>Date Of Birth</label>
                                    </div>
                                    <div class="col-md-6">
                                        <p class="con-view" id="viewDOB" >${DOB}</p>
                                        <div class="input-group-icon con-edit">
                                            <input required class="input--style-4 js-datepicker" type="text" id="txtDOB" name="birthday">
                                            <i class="zmdi zmdi-calendar-note input-icon js-btn-calendar"></i>
                                        </div>
                                    </div>
                                </div>
                                <div class="row  mt-1">
                                    <div class="col-md-6">
                                        <label>Contact</label>
                                    </div>
                                    <div class="col-md-6">
                                        <p class="con-view">${Contact}</p>
                                        <input type="email" class="form-control con-edit" id="txtContact" value="${Contact}" placeholder="Conact  No">
                                    </div>
                                </div>
                                <div class="row con-edit  mt-1">
                                    <div class="col-md-6">
                                        <label>Password</label>
                                    </div>
                                    <div class="col-md-6">
                                        <input type="password" class="form-control con-edit" id="txtPasswordU" placeholder="Password">
                                    </div>
                                </div>
                                <div class="row con-edit  mt-1">
                                    <div class="col-md-6">
                                        <label>Re-Enter Passsword</label>
                                    </div>
                                    <div class="col-md-6">
                                        <input type="password" class="form-control con-edit" id="txtConfirmPasswordU" placeholder="Re-Enter Password">
                                    </div>
                                </div>
                                <div class="row con-edit">
                                    <div class="col-md-6"></div>
                                    <div class="col-md-6">
                                        <button type="button" style="width: 100%;" onclick="saveUserDetails()" class="btn btn-success">Save</button>
                                    </div>
                                </div>
                            </div>
                            <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
                                <div class="row">
                                    <div class="col-md-6">
                                        <label>Experience</label>
                                    </div>
                                    <div class="col-md-6">
                                        <p>Expert</p>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <label>Hourly Rate</label>
                                    </div>
                                    <div class="col-md-6">
                                        <p>10$/hr</p>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <label>Total Projects</label>
                                    </div>
                                    <div class="col-md-6">
                                        <p>230</p>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <label>English Level</label>
                                    </div>
                                    <div class="col-md-6">
                                        <p>Expert</p>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <label>Availability</label>
                                    </div>
                                    <div class="col-md-6">
                                        <p>6 months</p>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <label>Your Bio</label><br/>
                                        <p>Your detail description</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>

        <!-- Vendor JS-->
        <script src="../../../resources/libs/vendor/select2/select2.min.js"></script>
        <script src="../../../resources/libs/vendor/datepicker/moment.min.js"></script>
        <script src="../../../resources/libs/vendor/datepicker/daterangepicker.js"></script>

        <!-- Main JS-->
        <script src="../../../resources/libs/vendor/global.js"></script>

    </body>

    <%@include file="../site-master/footer.jsp"%>

</html>
