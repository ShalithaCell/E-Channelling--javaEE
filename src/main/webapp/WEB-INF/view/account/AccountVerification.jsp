<%--
  Created by IntelliJ IDEA.
  User: shalithasenanayaka
  Date: 2019-09-15
  Time: 22:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

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
        <!-- Font special for pages-->
        <link href="https://fonts.googleapis.com/css?family=Poppins:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

        <!-- Vendor CSS-->
        <link href="../../../resources/libs/vendor/select2/select2.min.css" rel="stylesheet" media="all">
        <link href="../../../resources/libs/vendor/datepicker/daterangepicker.css" rel="stylesheet" media="all">

        <!-- Main CSS-->
        <link href="../../../resources/libs/vendor/main.css" rel="stylesheet" media="all">

        <script>
            var email = '${email}';
            var tempUserID = '${userID}';
            var invalidPassword = ${passCorrect}

            $(document).ready(function () {
                if(tempUserID == '0'){
                    passwordPrompt();
                }

            });


            function passwordPrompt() {
                $.confirm({
                    title: 'Congratulations! your account has been activated',
                    boxWidth: '50%',
                    type: 'success',
                    typeAnimated: true,
                    useBootstrap: false,
                    content: '' +
                        '<form action="verification" type="post" class="formName">' +
                        '<div class="form-group">' +
                        '<label>please confirm your current password to continue.</label>' +
                        '<input type="password" placeholder="Enter your password" class="password form-control" required />' +
                        '</div>' +
                        '</form>',
                    buttons: {
                        formSubmit: {
                            text: 'Submit',
                            btnClass: 'btn-blue',
                            action: function () {
                                var password = this.$content.find('.password').val();
                                if(!password){
                                    $.alert('provide a valid password');
                                    return false;
                                }

                                $.ajax({
                                    url: 'verification',
                                    type: 'post',
                                    data: { "password" : password , "command" : "login"},
                                    error: function (request, status, error) {

                                        window.location = "Error";
                                    },
                                    success: function(response) {

                                    }
                                });

                            }
                        },
                    },
                    onContentReady: function () {
                        // bind to events
                        if(invalidPassword){
                            $.alert('Password incorrect !');
                        }

                        var jc = this;
                        this.$content.find('form').on('submit', function (e) {
                            // if the user submits the form by pressing enter in the field.
                            e.preventDefault();
                            jc.$$formSubmit.trigger('click'); // reference the button and click it
                        });
                    }
                });
            }

        </script>

    </head>

    <body>
        <div class="page-wrapper bg-gra-02 p-t-130 p-b-100 font-poppins">
            <div class="wrapper wrapper--w680">
                <div class="card card-4">
                    <div class="card-body">
                        <h2 class="title">365Care Registration Form</h2>
                        <form method="POST">
                            <div class="row row-space">
                                <div class="col-2">
                                    <div class="input-group">
                                        <label class="label">first name</label>
                                        <input class="input--style-4" type="text" name="first_name">
                                    </div>
                                </div>
                                <div class="col-2">
                                    <div class="input-group">
                                        <label class="label">last name</label>
                                        <input class="input--style-4" type="text" name="last_name">
                                    </div>
                                </div>
                            </div>
                            <div class="row row-space">
                                <div class="col-2">
                                    <div class="input-group">
                                        <label class="label">Birthday</label>
                                        <div class="input-group-icon">
                                            <input class="input--style-4 js-datepicker" type="text" name="birthday">
                                            <i class="zmdi zmdi-calendar-note input-icon js-btn-calendar"></i>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-2">
                                    <div class="input-group">
                                        <label class="label">Gender</label>
                                        <div class="p-t-10">
                                            <label class="radio-container m-r-45">Male
                                                <input type="radio" checked="checked" name="gender">
                                                <span class="checkmark"></span>
                                            </label>
                                            <label class="radio-container">Female
                                                <input type="radio" name="gender">
                                                <span class="checkmark"></span>
                                            </label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row row-space">
                                <div class="col-2">
                                    <div class="input-group">
                                        <label class="label">Email</label>
                                        <input class="input--style-4" type="email" name="email" value="${email}" disabled>
                                    </div>
                                </div>
                                <div class="col-2">
                                    <div class="input-group">
                                        <label class="label">Phone Number</label>
                                        <input class="input--style-4" type="text" name="phone">
                                    </div>
                                </div>
                            </div>
                            <div class="input-group">
                                <label class="label">Subject</label>
                                <div class="rs-select2 js-select-simple select--no-search">
                                    <select name="subject">
                                        <option disabled="disabled" selected="selected">Choose option</option>
                                        <option>Subject 1</option>
                                        <option>Subject 2</option>
                                        <option>Subject 3</option>
                                    </select>
                                    <div class="select-dropdown"></div>
                                </div>
                            </div>
                            <div class="p-t-15">
                                <button class="btn btn--radius-2 btn--blue" type="submit">Submit</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>


        <!-- Vendor JS-->
        <script src="../../../resources/libs/vendor/select2/select2.min.js"></script>
        <script src="../../../resources/libs/vendor/datepicker/moment.min.js"></script>
        <script src="../../../resources/libs/vendor/datepicker/daterangepicker.js"></script>

        <!-- Main JS-->
        <script src="../../../resources/libs/vendor/global.js"></script>

    </body><!-- This templates was made by Colorlib (https://colorlib.com) -->

</html>
<!-- end document-->
