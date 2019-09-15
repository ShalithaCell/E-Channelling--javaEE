
function RegisterUser(email, password) {
    $.ajax({
        url: 'login',
        type: 'post',
        data: { "txtEmail": email, "password" : password , "command" : "login"},
        success: function(response) {

            console.log(response);

        }
    });

}