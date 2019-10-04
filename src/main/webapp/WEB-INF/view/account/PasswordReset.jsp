<%--
  Created by IntelliJ IDEA.
  User: shalithasenanayaka
  Date: 2019-10-04
  Time: 21:38
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


        <script src="../../../resources/scripts/toastMessageHandler.js"></script>
        <script src="../../../resources/libs/momentjs/moment.min.js"></script>




        <script>

            var pageValied = '${Result}';

            $(document).ready(function () {

                var result = '${Result}';
                var Message = '${Message}';

                console.log(result);

                if(result == 'false' || result == 'False'){
                    $.confirm({
                        theme: 'supervan' ,
                        title: 'Timeout !',
                        content: Message,
                        buttons: {
                            ok: function () {
                                window.location.href = 'home';
                            }
                        }
                    });
                }



                $("input[type=password]").keyup(function(){
                    var ucase = new RegExp("[A-Z]+");
                    var lcase = new RegExp("[a-z]+");
                    var num = new RegExp("[0-9]+");

                    if($("#password1").val().length >= 8){
                        $("#8char").removeClass("glyphicon-remove");
                        $("#8char").addClass("glyphicon-ok");
                        $("#8char").css("color","#00A41E");
                    }else{
                        $("#8char").removeClass("glyphicon-ok");
                        $("#8char").addClass("glyphicon-remove");
                        $("#8char").css("color","#FF0004");
                    }

                    if(ucase.test($("#password1").val())){
                        $("#ucase").removeClass("glyphicon-remove");
                        $("#ucase").addClass("glyphicon-ok");
                        $("#ucase").css("color","#00A41E");
                    }else{
                        $("#ucase").removeClass("glyphicon-ok");
                        $("#ucase").addClass("glyphicon-remove");
                        $("#ucase").css("color","#FF0004");
                    }

                    if(lcase.test($("#password1").val())){
                        $("#lcase").removeClass("glyphicon-remove");
                        $("#lcase").addClass("glyphicon-ok");
                        $("#lcase").css("color","#00A41E");
                    }else{
                        $("#lcase").removeClass("glyphicon-ok");
                        $("#lcase").addClass("glyphicon-remove");
                        $("#lcase").css("color","#FF0004");
                    }

                    if(num.test($("#password1").val())){
                        $("#num").removeClass("glyphicon-remove");
                        $("#num").addClass("glyphicon-ok");
                        $("#num").css("color","#00A41E");
                    }else{
                        $("#num").removeClass("glyphicon-ok");
                        $("#num").addClass("glyphicon-remove");
                        $("#num").css("color","#FF0004");
                    }

                    if($("#password1").val() == $("#password2").val()){
                        $("#pwmatch").removeClass("glyphicon-remove");
                        $("#pwmatch").addClass("glyphicon-ok");
                        $("#pwmatch").css("color","#00A41E");
                    }else{
                        $("#pwmatch").removeClass("glyphicon-ok");
                        $("#pwmatch").addClass("glyphicon-remove");
                        $("#pwmatch").css("color","#FF0004");
                    }
                });

            });

            function getUrlVars()
            {
                var vars = [], hash;
                var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
                for(var i = 0; i < hashes.length; i++)
                {
                    hash = hashes[i].split('=');
                    vars.push(hash[0]);
                    vars[hash[0]] = hash[1];
                }
                return vars;
            }

            function changePassword() {
                var pass1 = $('#password1').val();
                var pass2 = $('#password2').val();

                var controllers = CheckFormTextElementsIsEmpty('passwordForm');

                if(controllers.length > 0){
                    jQuery.each( controllers, function( i, val ) {
                        $('#'+val).addClass('has-error');
                    });

                    StartToasterMessage(MESSAGE_DANGER, REQUIRED_FIELD, 'required');
                    return;
                }

                var resetToken = getUrlVars()["resetToken"];

                if(resetToken == ''){
                    return;
                }

                if(pass1 != pass2){
                    StartToasterMessage(MESSAGE_DANGER, PASSWORD_DOESNOT_MATCH, 'not match');
                    return;
                }

                $.ajax({
                    url: 'passwordreset',
                    type: 'post',
                    data: { "txtPassword": pass2, "token" : resetToken , "command" : "changepassword"},
                    beforeSend: function() {
                        $('#loadingSpinner').show();
                    },
                    error: function (request, status, error) {
                        $('#loadingSpinner').hide();
                        window.location = "Error";
                    },
                    success: function(response) {
                        window.location = "home";
                    }
                });

            }

        </script>
    </head>
    <body>

        <%@include file="../site-master/header.jsp"%>

        <div class="container">
            <div class="row">
                <div class="col-sm-12" style="text-align: center">
                    <h1>Change Password</h1>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-6 col-sm-offset-3">
                    <p class="text-center">Use the form below to change your password. Your password cannot be the same as your username.</p>
                    <form method="post" id="passwordForm">
                        <input type="password" class="input-lg form-control" name="password1" id="password1" placeholder="New Password" autocomplete="off">
                        <div class="row">
                            <div class="col-sm-6">
                                <span id="8char" class="glyphicon glyphicon-remove" style="color:#FF0004;"></span> 8 Characters Long<br>
                                <span id="ucase" class="glyphicon glyphicon-remove" style="color:#FF0004;"></span> One Uppercase Letter
                            </div>
                            <div class="col-sm-6">
                                <span id="lcase" class="glyphicon glyphicon-remove" style="color:#FF0004;"></span> One Lowercase Letter<br>
                                <span id="num" class="glyphicon glyphicon-remove" style="color:#FF0004;"></span> One Number
                            </div>
                        </div>
                        <br>
                        <input type="password" class="input-lg form-control" name="password2" id="password2" placeholder="Repeat Password" autocomplete="off">
                        <div class="row">
                            <div class="col-sm-12">
                                <span id="pwmatch" class="glyphicon glyphicon-remove" style="color:#FF0004;"></span> Passwords Match
                            </div>
                        </div>
                        <br>
                        <input type="button" onclick="changePassword()" class="col-xs-12 btn btn-primary btn-load btn-lg" data-loading-text="Changing Password..." value="Change Password">
                    </form>
                </div><!--/col-sm-6-->
            </div><!--/row-->
        </div>
<br>
        <br>
        <br>
        <br>

    </body>

    <%@include file="../site-master/footer.jsp"%>

</html>

