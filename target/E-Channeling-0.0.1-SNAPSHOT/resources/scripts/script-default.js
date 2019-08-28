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

