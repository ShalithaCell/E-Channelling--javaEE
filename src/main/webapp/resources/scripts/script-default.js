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

