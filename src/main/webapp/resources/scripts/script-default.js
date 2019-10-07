function sessionManagement(name) {
    switch (name) {
        case 'home':
            $('.navbartop').find('.accountnav').removeClass('active');

            $('.navbartop').find('.homenav').addClass('active');
            console.log("home");
            break;
        case 'login':
            $('.navbartop').find('.homenav').removeClass('active');
            $('.navbartop').find('.accountnav').addClass('active');
            console.log("login");
            break;
    }
}

function checkMailIsValied(email){
    var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
    if(!email.match(re)) {
        return false;
    }
    return true;
}

function CheckFormTextElementsIsEmpty(FormName) {
    var controlArray = [];

    $('#'+FormName).find('input[type="text"],input[type="password"],input[type="email"],input[type="number"]').each(function () {


        if($(this).val().length == 0){
            controlArray.push($(this).attr('id'));
        }

    });

    return controlArray;
}

$.urlParam = function (name) {
    var results = new RegExp('[\?&]' + name + '=([^&#]*)')
        .exec(window.location.search);

    return (results !== null) ? results[1] || 0 : false;
}

function HandleLoginButtons(status) {
    if(status){
        $('#li_LogIn').hide();
        $('#li_formSignUp').hide();
        $('#li_Register').hide();
        $('#li_SignOut').show();
    }else{
        $('#li_LogIn').show();
        $('#li_formSignUp').show();
        $('#li_Register').show();
        $('#li_SignOut').hide();
    }
}

function showHideAdminPanel(status) {
    if(status){
        $('#li_adminPanel').show();
    }else{
        $('#li_adminPanel').hide();
    }
}

function checkSessionAvailability() {
    var success = false;

    $.ajax({
        url: 'login',
        type: 'post',
        async: false,
        data: { "command" : "sessionCheck"},
        error: function (request, status, error) {
            window.location = "Error";
        },
        success: function(response) {
            var result = JSON.parse(response);
            //console.log(result);
            if(result.result == 'true'){
                //console.log('valid');
                success = true;
            }else{
                //console.log('invalid');
                success = false;
            }

        }
    });

    return success;
}

function GetCurrentUserRoleCode() {
    var success = false;
    $.ajax({
        url: 'login',
        type: 'post',
        async: false,
        data: { "command" : "getcurrentuserjson"},
        error: function (request, status, error) {
            window.location = "Error";
        },
        success: function(response) {
            var result = JSON.parse(response);
            console.log(result);
            if(result.fk_RoleID == '1'){

                success = true;
            }else{

                success = false;
            }

        }
    });

    return success;
}




