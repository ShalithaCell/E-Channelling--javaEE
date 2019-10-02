<%--
  Created by IntelliJ IDEA.
  User: shalithasenanayaka
  Date: 2019-08-10
  Time: 09:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="../../../resources/styles/googleapis-family--verela.css" rel="stylesheet">
        <link rel="stylesheet" href="../../../resources/styles/googleapis-family-materialIcons.css">
        <link href="../../../resources/libs/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet">
        <link rel="stylesheet" href="../../../resources/libs/bootstrap-3.3.7/css/bootstrap.min.css">
        <script src="../../../resources/libs/jquery/jquery-3.4.1.min.js"></script>
        <script src="../../../resources/libs/bootstrap-3.3.7/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="../../../resources/styles/navbar-master.css">
        <link href="../../../resources/styles/style-default.css" rel="stylesheet">

        <script src="../../../resources/scripts/constants.js"></script>
        <script src="../../../resources/scripts/script-login.js"></script>
        <script src="../../../resources/scripts/toastMessageHandler.js"></script>
        <script src="../../../resources/scripts/script-default.js"></script>

        <script src="../../../resources/libs/jquery-confirm/jquery-confirm.min.js"></script>
        <link rel="stylesheet" href="../../../resources/libs/jquery-confirm/jquery-confirm.min.css">

        <script src="https://apis.google.com/js/platform.js" async defer></script>
        <meta name="google-signin-client_id" content="201405093980-siql7qs8kergiu0k3d75vjvcdgr2usb3.apps.googleusercontent.com">


        <script type="text/javascript">
            // Prevent dropdown menu from closing when click inside the form
            $(document).on("click", ".navbar-right .dropdown-menu", function(e){
                e.stopPropagation();
            });

            $(document).ready(function () {

                //nevigation
                $("#aService").click(function() {
                    $('html, body').animate({
                        scrollTop: $("#services").offset().top
                    }, 2000);
                });

                if(checkSessionAvailability()){
                    //console.log('ok');
                    HandleLoginButtons(true);
                }

                var session = '<%= session.getAttribute("page") %>';
                sessionManagement(name);


                $('#formSignUp').find('input[type="text"],input[type="password"],input[type="email"]').on("change paste keyup", function() {


                    if($(this).val().length != 0){
                        $(this).removeClass('has-error');
                    }

                });




            });

            function SignUp(event) {
                event.preventDefault();
                console.log('asdasd');
                var counter = 0;
                var controllers = CheckFormTextElementsIsEmpty('formSignUp');

                if(controllers.length > 0){
                    jQuery.each( controllers, function( i, val ) {
                        counter++;
                        $('#'+val).addClass('has-error');
                    });

                    StartToasterMessage(MESSAGE_DANGER, REQUIRED_FIELD, 'required');

                }else if($('#txtPassword').val() != $('#confirmPassword').val()){
                    StartToasterMessage(MESSAGE_DANGER, PASSWORD_DOESNOT_MATCH, 'Notice');
                    $('#txtPassword').addClass('has-error');
                    $('#confirmPassword').addClass('has-error');
                }
                else{
                    //$('#testform').submit();
                    $('#loadingSpinner').show();
                    RegisterUser($('#txtEmail').val(), $('#txtPassword').val());
                }
            }


            function onSignIn(googleUser) {
                /*var profile = googleUser.getBasicProfile();
                console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
                console.log('Name: ' + profile.getName());
                console.log('Image URL: ' + profile.getImageUrl());
                console.log('Email: ' + profile.getEmail()); // This is null if the 'email' scope is not present.*/
            }

            function signOut() {
                var auth2 = gapi.auth2.getAuthInstance();
                auth2.signOut().then(function () {
                    console.log('User signed out.');
                });
            }

        </script>
    </head>
    <body>
        <nav class="navbar navbar-default navbar-expand-lg navbar-light">
            <div class="navbar-header d-flex col">
                <a class="navbar-brand" href="#">365<b>Care</b></a>
                <button type="button" data-target="#navbarCollapse" data-toggle="collapse" class="navbar-toggle navbar-toggler ml-auto">
                    <span class="navbar-toggler-icon"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
            </div>
            <!-- Collection of nav links, forms, and other content for toggling -->
            <div id="navbarCollapse" class="collapse navbar-collapse justify-content-start">
                <ul class="nav navbar-nav">
                    <li class="nav-item"><a href="#" class="nav-link">Home</a></li>
                    <li class="nav-item"><a href="#" class="nav-link">About</a></li>
                    <li class="nav-item"><a id="aService" class="nav-link cursor-pointer">Service</a></li>
                    <li class="nav-item"><a href="#" class="nav-link">Contact</a></li>
                </ul>
                <form class="navbar-form form-inline">
                    <div class="input-group search-box">
                        <input type="text" id="search" class="form-control" placeholder="Search here...">
                        <span class="input-group-addon"><i class="material-icons">&#xE8B6;</i></span>
                    </div>
                </form>
                <ul class="nav navbar-nav navbar-right ml-auto">
                    <li class="nav-item" id="li_LogIn">
                        <a data-toggle="dropdown" class="nav-link dropdown-toggle" href="#">Login</a>
                        <ul class="dropdown-menu form-wrapper">
                            <li>
                                <form id="FrmLogIn" action="login" method="post">
                                    <p class="hint-text">Sign in with your social media account</p>
                                    <div class="form-group social-btn clearfix">
                                        <div class="form-group">
                                            <div style="margin-left: 10%;" class="g-signin2" data-longtitle="true" data-onsuccess="onSignIn"></div>
                                        </div>
                                    </div>
                                    <div class="or-seperator"><b>or</b></div>
                                    <div class="form-group">
                                        <input type="text" id="txtUserName" class="form-control" placeholder="Username" required="required">
                                    </div>
                                    <div class="form-group">
                                        <input type="password" id="txtPasswordl" class="form-control" placeholder="Password" required="required">
                                    </div>
                                    <input type="button" class="btn btn-primary btn-block" value="Login" onclick="UserLogIn()">
                                    <div class="form-footer">
                                        <a href="#">Forgot Your password?</a>
                                    </div>
                                </form>
                            </li>
                        </ul>
                    </li>
                    <li id="li_Register" class="nav-item">
                        <a href="#" data-toggle="dropdown" class="btn btn-primary dropdown-toggle get-started-btn mt-1 mb-1">Sign up</a>
                        <ul class="dropdown-menu form-wrapper" id="li_formSignUp">
                            <li>
                                <form id="formSignUp" action="login" method="post">
                                    <p class="hint-text">Fill in this form to create your account!</p>
                                    <div class="form-group">
                                        <input type="email" id="txtEmail" class="form-control" placeholder="Email" required="required">
                                    </div>
                                    <div class="form-group">
                                        <input type="password" id="txtPassword" class="form-control" placeholder="Password" required="required">
                                    </div>
                                    <div class="form-group">
                                        <input type="password" id="confirmPassword" class="form-control" placeholder="Confirm Password" required="required">
                                    </div>
                                    <input type="button" onclick="SignUp(event)" class="btn btn-primary btn-block btn_sign_up" id="btnSigbnUp" value="Sign up">

                                </form>
                            </li>
                        </ul>
                    </li>
                    <li class="nav-item" id="li_SignOut" style="display: none">
                        <a data-toggle="dropdown" data-hover="dropdown" data-delay="1000" class="nav-link dropdown-toggle" href="#">My Account</a>
                        <ul class="dropdown-menu form-wrapper">
                            <a class="dropdown-item" style="cursor: pointer;" id="btnAccount">My Account</a><br><br>
                            <a class="dropdown-item" id="btnSignOut" style="color: orangered;cursor: pointer;">Log Out</a>
                        </ul>

                    </li>
                </ul>
            </div>
        </nav>
    </body>

    <div id="loadingSpinner" class="loading" style="display: none">
        <div class="loader"></div>
    </div>

    <script src="../../../resources/libs/jquery.toaster-master/jquery.toaster.js"></script>
</html>