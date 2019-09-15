
function RegisterUser(email, password) {
    $.ajax({
        url: 'login',
        type: 'post',
        data: { "txtEmail": email, "password" : password , "command" : "login"},
        error: function (request, status, error) {
            $('#loadingSpinner').hide();
            window.location = "Error";
        },
        success: function(response) {
            //console.log(response);
            ClearSignUpFields();
            $('#loadingSpinner').hide();
            displayAccountConfirmBox(email,"User");
        }
    });

}

function ClearSignUpFields() {
    $('#txtEmail').val('');
    $('#txtPassword').val('');
    $('#confirmPassword').val('');
}

function displayAccountConfirmBox(email, name) {

    var content = "<div class=\"container text-center\">\n" +
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