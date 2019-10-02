
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