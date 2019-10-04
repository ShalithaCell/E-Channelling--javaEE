
function RegisterUser(email, password) {
    $.ajax({
        url: 'login',
        type: 'post',
        data: { "txtEmail": email, "password" : password , "command" : "register"},
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
                displayAccountConfirmBox(email,"User");
            }

            ClearSignUpFields();

        }
    });

}

function ClearSignUpFields() {
    $('#txtEmail').val('');
    $('#txtPassword').val('');
    $('#confirmPassword').val('');
    $('#txtUserName').val('');
    $('#txtPasswordl').val('');
}

function displayAccountConfirmBox(email, name) {

    var content = "<div class=\"text-center\">\n" +
        "    <div >\n" +
        "\t\t<h3 style=\"color: rgb(24, 157, 14);\"> \Thank you for Registering. Please verify your account.</h3>\n" +
        "        <h4 style=\"font-size: 14px;\">In order to use 365Care features, you must verify your account. An email has been sent to "+email+" .</h4>\n" +
        "\t</div>\n" +
        "</div>";

    $.confirm({
        title: 'Congratulation!',
        content: content,
        type: 'green',
        typeAnimated: true,
        columnClass: 'col-md-12',
        buttons: {
            ok: {
                text: 'ok',
                btnClass: 'btn-green',
                action: function(){
                    //redirect to home
                }
            }
        }
    });
}


function displayPasswordResetBox() {

    var content = "<div class=\"form-gap\"></div>\n" +
        "<div class=\"\">\n" +
        "\t<div class=\"\">\n" +
        "\t\t<div class=\"col-md-4 col-md-offset-4\">\n" +
        "            <div class=\"panel panel-default\">\n" +
        "              <div class=\"panel-body\">\n" +
        "                <div class=\"text-center\">\n" +
        "                  <h3><i class=\"fa fa-lock fa-4x\"></i></h3>\n" +
        "                  <h2 class=\"text-center\">Forgot Password?</h2>\n" +
        "                  <p>You can reset your password here.</p>\n" +
        "                  <div class=\"panel-body\">\n" +
        "    \n" +
        "                    <form id=\"register-form\" role=\"form\" autocomplete=\"off\" class=\"form\" method=\"post\">\n" +
        "    \n" +
        "                      <div class=\"form-group\">\n" +
        "                        <div class=\"input-group\">\n" +
        "                          <span class=\"input-group-addon\"><i class=\"glyphicon glyphicon-envelope color-blue\"></i></span>\n" +
        "                          <input id=\"emailReset\" name=\"email\" placeholder=\"email address\" class=\"form-control\"  type=\"email\">\n" +
        "                        </div>\n" +
        "                      </div>\n" +
        "                      <div class=\"form-group\">\n" +
        "                        <input name=\"recover-submit\" id='btnSendResetPassword' class=\"btn btn-lg btn-primary btn-block\" value=\"Reset Password\" type=\"button\">\n" +
        "                      </div>\n" +
        "                      \n" +
        "                      <input type=\"hidden\" class=\"hide\" name=\"token\" id=\"token\" value=\"\"> \n" +
        "                    </form>\n" +
        "    \n" +
        "                  </div>\n" +
        "                </div>\n" +
        "              </div>\n" +
        "            </div>\n" +
        "          </div>\n" +
        "\t</div>\n" +
        "</div>";

    $.confirm({
        title: '',
        content: content,
        type: 'red',
        typeAnimated: true,
        columnClass: 'col-md-12',
        buttons: {
            Cancel: {
                text: 'Cancel',
                btnClass: 'btn-warning',
                action: function(){

                }
            }
        },
        onContentReady: function(){

            var success = false;

            $("#btnSendResetPassword").click( function()
                {
                    if($('#emailReset').val() != ''){
                        $.ajax({
                            url: 'login',
                            type: 'post',
                            async: false,
                            data: { "email" : $('#emailReset').val() , "command" : "resetpassword"},
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
                                    StartToasterMessage(MESSAGE_DANGER, result.message, 'No User Found !');
                                    $('#emailReset').addClass('has-error');
                                }else{
                                    $.confirm({
                                        theme: 'supervan' ,
                                        title: 'Check your Email',
                                        content: 'please check your email and click on the provided link to reset your password'
                                    });

                                    return true;
                                }
                            }
                        });
                    }
                }
            );


        }
    });
}

function UserLogIn() {

    var controllers = CheckFormTextElementsIsEmpty('FrmLogIn');

    if(controllers.length > 0){
        jQuery.each( controllers, function( i, val ) {
            $('#'+val).addClass('has-error');
        });

        StartToasterMessage(MESSAGE_DANGER, REQUIRED_FIELD, 'required');
        return;
    }

    var email = $('#txtUserName').val();
    var password = $('#txtPasswordl').val();

    $.ajax({
        url: 'login',
        type: 'post',
        data: { "txtEmail": email, "password" : password , "command" : "login"},
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
                StartToasterMessage(MESSAGE_DANGER, result.message, 'Incorrect Credentials');
                $('#txtUserName').addClass('has-error');
                $('#txtPasswordl').addClass('has-error');
            }else{
                HandleLoginButtons(true);
                location.reload();
            }

            ClearSignUpFields();

        }
    });

}
