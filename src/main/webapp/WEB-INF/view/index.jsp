<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="keywords" content="Bootstrap, Landing page, Template">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <title>365Care</title>

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="../../resources/libs/bootstrap-3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="../../resources/libs/essence_templete/css/line-icons.css">
        <link rel="stylesheet" href="../../resources/libs/essence_templete/css/owl.carousel.css">
        <link rel="stylesheet" href="../../resources/libs/essence_templete/css/owl.theme.css">
        <link rel="stylesheet" href="../../resources/libs/essence_templete/css/nivo-lightbox.css">
        <link rel="stylesheet" href="../../resources/libs/essence_templete/css/magnific-popup.css">
        <link rel="stylesheet" href="../../resources/libs/essence_templete/css/animate.css">
        <link rel="stylesheet" href="../../resources/libs/essence_templete/css/color-switcher.css">
        <link rel="stylesheet" href="../../resources/libs/essence_templete/css/menu_sideslide.css">
        <link rel="stylesheet" href="../../resources/libs/essence_templete/css/main.css">
        <link rel="stylesheet" href="../../resources/libs/essence_templete/css/responsive.css">



        <style>
            .panel.with-nav-tabs .panel-heading{
                padding: 5px 5px 0 5px;
            }
            .panel.with-nav-tabs .nav-tabs{
                border-bottom: none;
            }
            .panel.with-nav-tabs .nav-justified{
                margin-bottom: -1px;
            }

            /********************************************************************/
            /*** PANEL PRIMARY ***/
            .with-nav-tabs.panel-primary .nav-tabs > li > a,
            .with-nav-tabs.panel-primary .nav-tabs > li > a:hover,
            .with-nav-tabs.panel-primary .nav-tabs > li > a:focus {
                color: #fff;
            }
            .with-nav-tabs.panel-primary .nav-tabs > .open > a,
            .with-nav-tabs.panel-primary .nav-tabs > .open > a:hover,
            .with-nav-tabs.panel-primary .nav-tabs > .open > a:focus,
            .with-nav-tabs.panel-primary .nav-tabs > li > a:hover,
            .with-nav-tabs.panel-primary .nav-tabs > li > a:focus {
                color: #fff;
                background-color: #3071a9;
                border-color: transparent;
            }
            .with-nav-tabs.panel-primary .nav-tabs > li.active > a,
            .with-nav-tabs.panel-primary .nav-tabs > li.active > a:hover,
            .with-nav-tabs.panel-primary .nav-tabs > li.active > a:focus {
                color: #428bca;
                background-color: #fff;
                border-color: #428bca;
                border-bottom-color: transparent;
            }
            .with-nav-tabs.panel-primary .nav-tabs > li.dropdown .dropdown-menu {
                background-color: #428bca;
                border-color: #3071a9;
            }
            .with-nav-tabs.panel-primary .nav-tabs > li.dropdown .dropdown-menu > li > a {
                color: #fff;
            }
            .with-nav-tabs.panel-primary .nav-tabs > li.dropdown .dropdown-menu > li > a:hover,
            .with-nav-tabs.panel-primary .nav-tabs > li.dropdown .dropdown-menu > li > a:focus {
                background-color: #3071a9;
            }
            .with-nav-tabs.panel-primary .nav-tabs > li.dropdown .dropdown-menu > .active > a,
            .with-nav-tabs.panel-primary .nav-tabs > li.dropdown .dropdown-menu > .active > a:hover,
            .with-nav-tabs.panel-primary .nav-tabs > li.dropdown .dropdown-menu > .active > a:focus {
                background-color: #4a9fe9;
            }

            .filter-option{
                color: black !important;
            }


        </style>

        <script>



            function BookDoctor() {
                if(!checkSessionAvailability()){
                    StartToasterMessage(MESSAGE_DANGER, "Please Login First", 'Login failed');
                    return;
                }

                var counter = 0;
                var controllers = CheckFormTextElementsIsEmpty('divBook');

                if(controllers.length > 0){
                    jQuery.each( controllers, function( i, val ) {
                        counter++;
                        $('#'+val).addClass('has-error');
                    });

                    StartToasterMessage(MESSAGE_DANGER, REQUIRED_FIELD, 'required');

                }

                if($('#hospitalDDL').val() == '0' || $('#hospitalDDL').val() == ''){
                    StartToasterMessage(MESSAGE_DANGER, 'Please Select Hospital', 'required');
                    return;
                }

                if($('#hospitalDDL').val() == '0' || $('#hospitalDDL').val() == ''){
                    StartToasterMessage(MESSAGE_DANGER, 'Please Select Doctor', 'required');
                    return;
                }

                var name = $('#txtFullName').val();
                var adress = $('#txtAddress').val();
                var gurdian = $('#Guardian').val();
                var emailPatient = $('#txtEmailPatient').val();
                var phone = $('#phoneNumber').val();
                var Age = $('#txtAge').val();
                var hospital = $('#hospitalDDL').val();
                var doctor = $('#doctorDDL').val();

                $.ajax({
                    url: 'home',
                    type: 'post',
                    data: {
                        "name": name,
                        "adress" : adress ,
                        "gurdian" : gurdian ,
                        "emailPatient" : emailPatient ,
                        "phone" : phone ,
                        "Age" : Age ,
                        "hospital" : hospital,
                        "doctor" : doctor,
                        "command" : "book" },
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

                            window.open('payment?amount=200');
                        }

                    }
                });



            }

            function loadHospitals() {
                $.ajax({
                    url: 'adminPanel',
                    type: 'post',
                    async : false,
                    data: {
                        "command" : "getHospital" },
                    beforeSend: function() {
                        $('#loadingSpinner').show();
                    },
                    error: function (request, status, error) {
                        $('#loadingSpinner').hide();
                        window.location = "Error";
                    },
                    success: function(response) {

                        $('#loadingSpinner').hide();
                        var result = JSON.parse(response);
                        var ele = document.getElementById('hospitalDDL');

                        $.each(result, function(i, item) {
                            //hospitalDDL

                            console.log(result[i].Description);



                            ele.innerHTML = ele.innerHTML +
                                '<option value="' + result[i].HospitalID + '">' + result[i].Description + '</option>';

                        });




                    }
                });
            }

            function loadDoctors() {
                $.ajax({
                    url: 'adminPanel',
                    type: 'post',
                    async : false,
                    data: {
                        "command" : "getDoctors" },
                    beforeSend: function() {
                        $('#loadingSpinner').show();
                    },
                    error: function (request, status, error) {
                        $('#loadingSpinner').hide();
                        window.location = "Error";
                    },
                    success: function(response) {

                        $('#loadingSpinner').hide();
                        var result = JSON.parse(response);
                        var ele = document.getElementById('doctorDDL');

                        $.each(result, function(i, item) {
                            //hospitalDDL

                            ele.innerHTML = ele.innerHTML +
                                '<option value="' + result[i].DoctorID + '">' + result[i].DoctorName + '</option>';

                        });




                    }
                });
            }

        </script>

    </head>
<body>

    <%@include file="site-master/header.jsp"%>

    <!-- Channel  Section Start -->
    <div id="subscribe" class="section">
        <div class="container">
            <div class="row justify-content-between">
                <div class="col-lg-5 col-md-12 col-xs-12">
                    <div class="subscribe-form">
                        <div class="form-wrapper">
                            <div class="sub-title text-center">
                                <h3>We are No 1. Channeling Website</h3>
                                <p>e-Channelling PLC is the pioneer software development and ICT service provider to the Healthcare industry in Sri Lanka. It is the first company in Sri Lanka to offer a complete e-commerce based service and the first public quoted Technology Company in the Colombo Stock Exchange. E-Channelling has consistently been part of the 100 top brands in Sri Lanka.</p>
                            </div>

                        </div>
                    </div>
                </div>
                <div class="col-lg-7 col-md-12 col-xs-12">
                    <div class="col-md-12">
                        <div class="panel with-nav-tabs panel-primary">
                            <div class="panel-heading">
                                <ul class="nav nav-tabs">
                                    <li class="active"><a href="#tab1primary" data-toggle="tab">Quick Channel</a></li>
                                        </ul>
                                    </li>
                                </ul>
                            </div>
                            <div class="panel-body">
                                <div class="tab-content">
                                    <div class="tab-pane fade in active" id="tab1primary">Quickly channel your doctor</div>

                                    <div class="" id="divBook">
                                        <table class="table table-striped">
                                            <tbody>
                                                <tr>
                                                    <td colspan="1">
                                                        <form class="well form-horizontal">
                                                            <fieldset>
                                                                <div class="form-group">
                                                                    <label class="col-md-4 control-label">Full Name</label>
                                                                    <div class="col-md-8 inputGroupContainer">
                                                                        <div class="input-group"><span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span><input id="txtFullName" name="fullName" placeholder="Full Name" class="form-control" required="true" value="" type="text"></div>
                                                                    </div>
                                                                </div>
                                                                <div class="form-group">
                                                                    <label class="col-md-4 control-label">Address </label>
                                                                    <div class="col-md-8 inputGroupContainer">
                                                                        <div class="input-group"><span class="input-group-addon"><i class="glyphicon glyphicon-home"></i></span><input id="txtAddress" name="addressLine1" placeholder="Address" class="form-control" required="true" value="" type="text"></div>
                                                                    </div>
                                                                </div>
                                                                <div class="form-group">
                                                                    <label class="col-md-4 control-label">Guardian </label>
                                                                    <div class="col-md-8 inputGroupContainer">
                                                                        <div class="input-group"><span class="input-group-addon"><i class="glyphicon glyphicon-eye-open"></i></span><input id="Guardian" name="Guardian" placeholder="Guardian" class="form-control" required="true" value="" type="text"></div>
                                                                    </div>
                                                                </div>
                                                                <div class="form-group">
                                                                    <label class="col-md-4 control-label">Email</label>
                                                                    <div class="col-md-8 inputGroupContainer">
                                                                        <div class="input-group"><span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span><input id="txtEmailPatient" name="txtEmailPatient" placeholder="Email" class="form-control" required="true" value="" type="email"></div>
                                                                    </div>
                                                                </div>
                                                                <div class="form-group">
                                                                    <label class="col-md-4 control-label">Phone Number</label>
                                                                    <div class="col-md-8 inputGroupContainer">
                                                                        <div class="input-group"><span class="input-group-addon"><i class="glyphicon glyphicon-earphone"></i></span><input id="phoneNumber" name="phoneNumber" placeholder="Phone Number" class="form-control" required="true" value="" type="text"></div>
                                                                    </div>
                                                                </div>
                                                                <div class="form-group">
                                                                    <label class="col-md-4 control-label">Age</label>
                                                                    <div class="col-md-8 inputGroupContainer">
                                                                        <div class="input-group"><span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span><input id="txtAge" name="txtDOB" placeholder="Age" class="form-control" required="true" value="" type="number"></div>
                                                                    </div>
                                                                </div>
                                                                <div class="form-group">
                                                                    <label class="col-md-4 control-label">Hospital</label>
                                                                    <div class="col-md-8 inputGroupContainer">
                                                                        <select id="hospitalDDL" class="selectpicker">
                                                                            <option value="0">Select...</option>
                                                                        </select>
                                                                    </div>

                                                                </div>

                                                                <div class="form-group">
                                                                    <label class="col-md-4 control-label">Doctor</label>
                                                                    <div class="col-md-8 inputGroupContainer">
                                                                        <select id="doctorDDL" class="selectpicker">
                                                                            <option value="0">Select...</option>
                                                                        </select>
                                                                    </div>

                                                                </div>

                                                                <div class="form-group">
                                                                    <input type="button" id="btnBookNow" class="btn btn-success" onclick="BookDoctor()" value="Book Now" style="width: 100%;">
                                                                </div>
                                                            </fieldset>
                                                        </form>
                                                    </td>

                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Subcribe1 Section End -->

    <!-- Services Section Start -->
    <section id="services" tabindex="1" class="section">
        <div class="container">
            <div class="section-header">
                <h2 class="section-title">Our Services</h2>
                <span>Services</span>
                <p class="section-subtitle">The smartest way to stay healthy</p>
            </div>
            <div class="row">
                <div class="col-lg-4 col-md-6 col-xs-12">
                    <div class="item-boxes services-item wow fadeInDown" data-wow-delay="0.2s">
                        <div class="icon color-1">
                            <i class="lni-pencil"></i>
                        </div>
                        <h4>Online Appointments</h4>
                        <p>Find relevant Doctors and book confirmed appointments online via 365Care. Channel Doctors from 225+ hospitals in Sri Lanka with just a few clicks. It's that easy!</p>

                    </div>
                </div>
                <div class="col-lg-4 col-md-6 col-xs-12">
                    <div class="item-boxes services-item wow fadeInDown" data-wow-delay="0.4s">
                        <div class="icon color-2">
                            <i class="lni-cog"></i>
                        </div>
                        <h4>Laboratory Service</h4>
                        <p>When you have a new-born baby or a loved one who is immobile, taking them to a hospital for medical tests can be as traumatic for them as it is for you</p>

                    </div>
                </div>
                <div class="col-lg-4 col-md-6 col-xs-12">
                    <div class="item-boxes services-item wow fadeInDown" data-wow-delay="0.6s">
                        <div class="icon color-3">
                            <i class="lni-stats-up"></i>
                        </div>
                        <h4>Online Report Analysis</h4>
                        <p>You’re about to use a short (3 min), safe and anonymous health checkup. Your answers will be carefully analyzed and you’ll learn about possible causes of your symptoms.</p>
                    </div>
                </div>
                <div class="col-lg-4 col-md-6 col-xs-12">
                    <div class="item-boxes services-item wow fadeInDown" data-wow-delay="0.8s">
                        <div class="icon color-4">
                            <i class="lni-layers"></i>
                        </div>
                        <h4>Online Calendar</h4>
                        <p>Manage multiple doctor and nurse calendars, send appointment reminders, cancel your appointments, report arrivals and other things via online calendar.</p>
                    </div>
                </div>
                <div class="col-lg-4 col-md-6 col-xs-12">
                    <div class="item-boxes services-item wow fadeInDown" data-wow-delay="1s">
                        <div class="icon color-5">
                            <i class="lni-tab"></i>
                        </div>
                        <h4>Notification</h4>
                        <p>Automated text reminders ensure your customers keep their commitments, saving you the cost of missing appointments. And you can keep in touch with your reports arrivals and other details.</p>
                    </div>
                </div>
                <div class="col-lg-4 col-md-6 col-xs-12">
                    <div class="item-boxes services-item wow fadeInDown" data-wow-delay="1.2s">
                        <div class="icon color-6">
                            <i class="lni-briefcase"></i>
                        </div>
                        <h4>Online Payments</h4>
                        <p>You can make online payments using “VISA” or “MASTER”. Cards from any Bank will be processed through our Payment Gateway. Make an online payment for your appointment.</p>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Services Section End -->

    <!-- Call to Action Start -->
    <section class="call-action section">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-10">
                    <div class="cta-trial text-center">
                        <h3>Are You Ready To Meet Your Doctor?</h3>
                        <p>Get to meet a wide range of doctors according to your requirements. Select the best!!</p>
                        <a href="#" class="btn btn-common btn-effect">Book Your Doctor Now!</a>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Call to Action End -->

    <!-- Features Section Start -->
    <section id="features" class="section">
        <div class="container">
            <div class="section-header">
                <h2 class="section-title">Why Choose Us</h2>
                <span>Why</span>
                <p class="section-subtitle">Because we are the best..!!</p>
            </div>
            <div class="row">
                <!-- Start featured -->
                <div class="col-lg-4 col-md-6 col-xs-12">
                    <div class="featured-box">
                        <div class="featured-icon">
                            <i class="lni-layout"></i>
                        </div>
                        <div class="featured-content">
                            <div class="icon-o"><i class="lni-layout"></i></div>
                            <h4>Free 24/7 Support</h4>
                            <p>Our customer care unit is available 24/7. Contact us with your inquiries on our hot line.</p>
                        </div>
                    </div>
                </div>
                <!-- End featured -->
                <!-- Start featured -->
                <div class="col-lg-4 col-md-6 col-xs-12">
                    <div class="featured-box">
                        <div class="featured-icon">
                            <i class="lni-tab"></i>
                        </div>
                        <div class="featured-content">
                            <div class="icon-o"><i class="lni-tab"></i></div>
                            <h4>Fully Responsive</h4>
                            <p>As a good customer service we always try give a best solutions for your problems.</p>
                        </div>
                    </div>
                </div>
                <!-- End featured -->
                <!-- Start featured -->
                <div class="col-lg-4 col-md-6 col-xs-12">
                    <div class="featured-box">
                        <div class="featured-icon">
                            <i class="lni-rocket"></i>
                        </div>
                        <div class="featured-content">
                            <div class="icon-o"><i class="lni-rocket"></i></div>
                            <h4>Guaranteed Responsibility</h4>
                            <p>Guaranteed quality service and responsible management of information.</p>
                        </div>
                    </div>
                </div>
                <!-- End featured -->
                <!-- Start featured -->
                <div class="col-lg-4 col-md-6 col-xs-12">
                    <div class="featured-box">
                        <div class="featured-icon">
                            <i class="lni-database"></i>
                        </div>
                        <div class="featured-content">
                            <div class="icon-o"><i class="lni-database"></i></div>
                            <h4>Fast & Smooth</h4>
                            <p>Opportunity to experience a fast and smooth service.</p>
                        </div>
                    </div>
                </div>
                <!-- End featured -->
                <!-- Start featured -->
                <div class="col-lg-4 col-md-6 col-xs-12">
                    <div class="featured-box">
                        <div class="featured-icon">
                            <i class="lni-leaf"></i>
                        </div>
                        <div class="featured-content">
                            <div class="icon-o"><i class="lni-leaf"></i></div>
                            <h4>Save Your Time</h4>
                            <p>No more waiting in queues to get your things.</p>
                        </div>
                    </div>
                </div>
                <!-- End featured -->
                <!-- Start featured -->
                <div class="col-lg-4 col-md-6 col-xs-12">
                    <div class="featured-box">
                        <div class="featured-icon">
                            <i class="lni-pencil"></i>
                        </div>
                        <div class="featured-content">
                            <div class="icon-o"><i class="lni-pencil"></i></div>
                            <h4>Refreshing Design</h4>
                            <p>Simple yet elegant design with easy navigation.</p>
                        </div>
                    </div>
                </div>
                <!-- End featured -->
            </div>
        </div>
    </section>
    <!-- Features Section End -->

    <!-- Start Video promo Section -->
    <section class="video-promo section">
        <div class="overlay"></div>
        <div class="container">
            <div class="row">
                <div class="col-md-12 col-sm-12">
                    <div class="video-promo-content text-center">
                        <a href="https://youtu.be/mLMpueKG3RQ" class="video-popup"><i class="lni-film-play"></i></a>
                        <h2 class="wow zoomIn" data-wow-duration="1000ms" data-wow-delay="100ms">Our Introductory Video</h2>
                        <p class="wow zoomIn" data-wow-duration="1000ms" data-wow-delay="100ms">Learn more about us.</p>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- End Video Promo Section -->

    <!-- Portfolio Section -->
    <section id="portfolios" class="section">
        <!-- Container Starts -->
        <div class="container">
            <div class="section-header">
                <h2 class="section-title">Our Popular Clients</h2>
                <span>Clients</span>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <!-- Portfolio Controller/Buttons -->
                    <div class="controls text-center">
                        <a class="filter active btn btn-common btn-effect" data-filter="all">
                            Hospital
                        </a>
                        <a class="filter btn btn-common btn-effect" data-filter=".design">
                            Pharmacy
                        </a>
                        <a class="filter btn btn-common btn-effect" data-filter=".development">
                            Laboratory
                        </a>
                        <a class="filter btn btn-common btn-effect" data-filter=".print">
                            Channeling Center
                        </a>
                    </div>
                    <!-- Portfolio Controller/Buttons Ends-->
                </div>
            </div>

            <!-- Portfolio Recent Projects -->
            <div id="portfolio" class="row">
                <div class="col-lg-4 col-md-6 col-xs-12 mix development print">
                    <div class="portfolio-item">
                        <div class="shot-item">
                            <img src="../../resources/libs/essence_templete/img/portfolio/asiri.jpg" alt="" />
                            <div class="single-content">
                                <div class="fancy-table">
                                    <div class="table-cell">
                                        <div class="zoom-icon">
                                            <a class="lightbox" href="../../resources/libs/essence_templete/img/portfolio/asiri.jpg"><i class="lni-zoom-in item-icon"></i></a>
                                        </div>
                                        <a href="#">View Hospital</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-6 col-xs-12 mix design print">
                    <div class="portfolio-item">
                        <div class="shot-item">
                            <img src="../../resources/libs/essence_templete/img/portfolio/hemas.jpg" alt="" />
                            <div class="single-content">
                                <div class="fancy-table">
                                    <div class="table-cell">
                                        <div class="zoom-icon">
                                            <a class="lightbox" href="../../resources/libs/essence_templete/img/portfolio/hemas.jpg"><i class="lni-zoom-in item-icon"></i></a>
                                        </div>
                                        <a href="#">View Hospital</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-6 col-xs-12 mix development">
                    <div class="portfolio-item">
                        <div class="shot-item">
                            <img src="../../resources/libs/essence_templete/img/portfolio/nevil.png" alt="" />
                            <div class="single-content">
                                <div class="fancy-table">
                                    <div class="table-cell">
                                        <div class="zoom-icon">
                                            <a class="lightbox" href="../../resources/libs/essence_templete/img/portfolio/nevil.png"><i class="lni-zoom-in item-icon"></i></a>
                                        </div>
                                        <a href="#">View Hospital</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-6 col-xs-12 mix development design">
                    <div class="portfolio-item">
                        <div class="shot-item">
                            <img src="../../resources/libs/essence_templete/img/portfolio/nawaloka.png" alt="" />
                            <div class="single-content">
                                <div class="fancy-table">
                                    <div class="table-cell">
                                        <div class="zoom-icon">
                                            <a class="lightbox" href="../../resources/libs/essence_templete/img/portfolio/nawaloka.png"><i class="lni-zoom-in item-icon"></i></a>
                                        </div>
                                        <a href="#">View Hospital</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-6 col-xs-12 mix development">
                    <div class="portfolio-item">
                        <div class="shot-item">
                            <img src="../../resources/libs/essence_templete/img/portfolio/lanka.png" alt="" />
                            <div class="single-content">
                                <div class="fancy-table">
                                    <div class="table-cell">
                                        <div class="zoom-icon">
                                            <a class="lightbox" href="../../resources/libs/essence_templete/img/portfolio/lanka.jpg"><i class="lni-zoom-in item-icon"></i></a>
                                        </div>
                                        <a href="#">View Hospital</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-6 col-xs-12 mix print design">
                    <div class="portfolio-item">
                        <div class="shot-item">
                            <img src="../../resources/libs/essence_templete/img/portfolio/durdance.png" alt="" />
                            <div class="single-content">
                                <div class="fancy-table">
                                    <div class="table-cell">
                                        <div class="zoom-icon">
                                            <a class="lightbox" href="../../resources/libs/essence_templete/img/portfolio/durdance.png"><i class="lni-zoom-in item-icon"></i></a>
                                        </div>
                                        <a href="#">View Hospital</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Container Ends -->
    </section>
    <!-- Portfolio Section Ends -->

    <!-- Start Pricing Table Section -->
    <div id="pricing" class="section pricing-section">
        <div class="container">
            <div class="section-header">
                <h2 class="section-title">Pricing Plans</h2>
                <span>Pricing</span>
            </div>

            <div class="row pricing-tables">
                <div class="col-lg-4 col-md-4 col-xs-12">
                    <div class="pricing-table">
                        <div class="pricing-details">
                            <h2>Starter Plan</h2>
                            <div class="price">Rs 50 000</div>
                            <ul>
                                <li>Laboratory Service</li>
                                <li>Report Analysis</li>
                                <li>Notification Sender</li>
                                <li>Online check ups</li>
                            </ul>
                        </div>
                        <div class="plan-button">
                            <a href="#" class="btn btn-common btn-effect">Get Plan</a>
                        </div>
                    </div>
                </div>

                <div class="col-lg-4 col-md-4 col-xs-12">
                    <div class="pricing-table pricing-big">
                        <div class="pricing-details">
                            <h2>Premium Plan</h2>
                            <div class="price">Rs 10 0000</div>
                            <ul>
                                <li>Laboratory Service</li>
                                <li>Report Analysis</li>
                                <li>Room Booking</li>
                                <li>Attendant Service</li>
                                <li>Notification Sender</li>
                                <li>Online check ups</li>
                            </ul>
                        </div>
                        <div class="plan-button">
                            <a href="#" class="btn btn-common btn-effect">Buy Now</a>
                        </div>
                    </div>
                </div>

                <div class="col-lg-4 col-md-4 col-xs-12">
                    <div class="pricing-table">
                        <div class="pricing-details">
                            <h2>Popular Plan</h2>
                            <div class="price">Rs 75 000</div>
                            <ul>
                                <li>Laboratory Service</li>
                                <li>Report Analysis</li>
                                <li>Room Booking<li>
                                <li>Notification Sender</li>
                                <li>Online check ups</li>
                            </ul>
                        </div>
                        <div class="plan-button">
                            <a href="#" class="btn btn-common btn-effect">Buy Now</a>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
    <!-- End Pricing Table Section -->

    <!-- Counter Section Start -->
    <div class="counters section bg-defult">
        <div class="container">
            <div class="row">
                <div class="col-sm-6 col-md-6 col-lg-3">
                    <div class="facts-item">
                        <div class="icon">
                            <i class="lni-rocket"></i>
                        </div>
                        <div class="fact-count">
                            <h3><span class="counter">100</span>%</h3>
                            <h4>Faster</h4>
                        </div>
                    </div>
                </div>
                <div class="col-sm-6 col-md-6 col-lg-3">
                    <div class="facts-item">
                        <div class="icon">
                            <i class="lni-user"></i>
                        </div>
                        <div class="fact-count">
                            <h3><span class="counter">700</span>+</h3>
                            <h4>Doctors</h4>
                        </div>
                    </div>
                </div>
                <div class="col-sm-6 col-md-6 col-lg-3">
                    <div class="facts-item">
                        <div class="icon">
                            <i class="lni-user"></i>
                        </div>
                        <div class="fact-count">
                            <h3><span class="counter">10000</span>+</h3>
                            <h4>Active Clients</h4>
                        </div>
                    </div>
                </div>
                <div class="col-sm-6 col-md-6 col-lg-3">
                    <div class="facts-item">
                        <div class="icon">
                            <i class="lni-heart"></i>
                        </div>
                        <div class="fact-count">
                            <h3><span class="counter">1689</span></h3>
                            <h4>Peoples Love</h4>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Counter Section End -->

    <!-- Testimonial Section Start -->
    <section class="testimonial section">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <div id="testimonials" class="touch-slider owl-carousel">
                        <div class="item">
                            <div class="testimonial-item">
                                <div class="author">
                                    <div class="img-thumb">
                                        <img src="../../resources/libs/essence_templete/img/testimonial/member.png" alt="">
                                    </div>
                                    <div class="author-info">
                                        <h2><a href="#">Shalitha Senanayak</a></h2>
                                        <span>Leader</span>
                                    </div>
                                </div>
                                <div class="content-inner">
                                    <p class="description">...</p>
                                    <span><i class="lni-star-filled"></i></span>
                                    <span><i class="lni-star-filled"></i></span>
                                    <span><i class="lni-star-filled"></i></span>
                                    <span><i class="lni-star-filled"></i></span>
                                    <span><i class="lni-star-filled"></i></span>
                                </div>
                            </div>
                        </div>
                        <div class="item">
                            <div class="testimonial-item">
                                <div class="author">
                                    <div class="img-thumb">
                                        <img src="../../resources/libs/essence_templete/img/testimonial/member.png" alt="">
                                    </div>
                                    <div class="author-info">
                                        <h2><a href="#">Chehan Nethsara</a></h2>
                                        <span>Member</span>
                                    </div>
                                </div>
                                <div class="content-inner">
                                    <p class="description">...</p>
                                    <span><i class="lni-star-filled"></i></span>
                                    <span><i class="lni-star-filled"></i></span>
                                    <span><i class="lni-star-filled"></i></span>
                                    <span><i class="lni-star"></i></span>
                                    <span><i class="lni-star"></i></span>
                                </div>
                            </div>
                        </div>
                        <div class="item">
                            <div class="testimonial-item">
                                <div class="author">
                                    <div class="img-thumb">
                                        <img src="../../resources/libs/essence_templete/img/testimonial/member.png" alt="">
                                    </div>
                                    <div class="author-info">
                                        <h2><a href="#">Aloka Kaluarachchi</a></h2>
                                        <span>Member</span>
                                    </div>
                                </div>
                                <div class="content-inner">
                                    <p class="description">...</p>
                                    <span><i class="lni-star-filled"></i></span>
                                    <span><i class="lni-star-filled"></i></span>
                                    <span><i class="lni-star-filled"></i></span>
                                    <span><i class="lni-star"></i></span>
                                    <span><i class="lni-star"></i></span>
                                </div>
                            </div>
                        </div>
                        <div class="item">
                            <div class="testimonial-item">
                                <div class="author">
                                    <div class="img-thumb">
                                        <img src="../../resources/libs/essence_templete/img/testimonial/member.png" alt="">
                                    </div>
                                    <div class="author-info">
                                        <h2><a href="#">Thimasha Nethmini</a></h2>
                                        <span>Member</span>
                                    </div>
                                </div>
                                <div class="content-inner">
                                    <p class="description">...</p>
                                    <span><i class="lni-star-filled"></i></span>
                                    <span><i class="lni-star-filled"></i></span>
                                    <span><i class="lni-star-filled"></i></span>
                                    <span><i class="lni-star"></i></span>
                                    <span><i class="lni-star"></i></span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Testimonial Section End -->



    <!-- Team section Start -->
    <section id="team" class="section">
        <div class="container">
            <div class="section-header">
                <h2 class="section-title">Our Team</h2>
                <span>Team</span>
                <p class="section-subtitle">None of us is as smart as all of us.</p>
            </div>
            <div class="row">
                <div class="col-lg-3 col-md-6 col-xs-12">
                    <div class="single-team">
                        <img src="../../resources/libs/essence_templete/img/clients/docc.jpg" alt="">
                        <div class="team-details">
                            <div class="team-inner">
                                <h4 class="team-title">Shalitha Senanayaka</h4>
                                <p>Team Leader</p>
                                <ul class="social-list">
                                    <li class="facebook"><a href="#"><i class="lni-facebook-filled"></i></a></li>
                                    <li class="twitter"><a href="#"><i class="lni-twitter-filled"></i></a></li>
                                    <li class="google-plus"><a href="#"><i class="lni-google-plus"></i></a></li>
                                    <li class="linkedin"><a href="#"><i class="lni-linkedin-fill"></i></a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6 col-xs-12">
                    <div class="single-team">
                        <img src="../../resources/libs/essence_templete/img/clients/images.jpg" alt="">
                        <div class="team-details">
                            <div class="team-inner">
                                <h4 class="team-title">Chehan Nethsara</h4>
                                <p>Team Member</p>
                                <ul class="social-list">
                                    <li class="facebook"><a href="#"><i class="lni-facebook-filled"></i></a></li>
                                    <li class="twitter"><a href="#"><i class="lni-twitter-filled"></i></a></li>
                                    <li class="google-plus"><a href="#"><i class="lni-google-plus"></i></a></li>
                                    <li class="linkedin"><a href="#"><i class="lni-linkedin-fill"></i></a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6 col-xs-12">
                    <div class="single-team">
                        <img src="../../resources/libs/essence_templete/img/clients/doc4jpg.jpg" alt="">
                        <div class="team-details">
                            <div class="team-inner">
                                <h4 class="team-title">Aloka Kaluarachchi</h4>
                                <p>Team Member</p>
                                <ul class="social-list">
                                    <li class="facebook"><a href="#"><i class="lni-facebook-filled"></i></a></li>
                                    <li class="twitter"><a href="#"><i class="lni-twitter-filled"></i></a></li>
                                    <li class="google-plus"><a href="#"><i class="lni-google-plus"></i></a></li>
                                    <li class="linkedin"><a href="#"><i class="lni-linkedin-fill"></i></a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6 col-xs-12">
                    <div class="single-team">
                        <img src="../../resources/libs/essence_templete/img/clients/doc2jpg.jpg" alt="">
                        <div class="team-details">
                            <div class="team-inner">
                                <h4 class="team-title">Thimasha Nethmini</h4>
                                <p>Team Member</p>
                                <ul class="social-list">
                                    <li class="facebook"><a href="#"><i class="lni-facebook-filled"></i></a></li>
                                    <li class="twitter"><a href="#"><i class="lni-twitter-filled"></i></a></li>
                                    <li class="google-plus"><a href="#"><i class="lni-google-plus"></i></a></li>
                                    <li class="linkedin"><a href="#"><i class="lni-linkedin-fill"></i></a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Team section End -->

    <!-- Blog Section -->
    <section id="blog" class="section">
        <!-- Container Starts -->
        <div class="container">
            <div class="section-header">
                <h2 class="section-title">At Your Doorstep</h2>
                <span>Doorstep</span>

            </div>
            <div class="row">
                <div class="col-lg-4 col-md-6 col-xs-12 blog-item">
                    <!-- Blog Item Starts -->
                    <div class="blog-item-wrapper">
                        <div class="blog-item-img">
                            <a href="single-post.html">
                                <img src="../../resources/libs/essence_templete/img/blog/hos.jpg" alt="">
                            </a>
                        </div>
                        <div class="blog-item-text">

                            <h3><a href="single-post.html">TELL DOCTOR Service</a></h3>
                            <br>

                        </div>
                    </div>
                    <!-- Blog Item Wrapper Ends-->
                </div>
                <div class="col-lg-4 col-md-6 col-xs-12 blog-item">
                    <!-- Blog Item Starts -->
                    <div class="blog-item-wrapper">
                        <div class="blog-item-img">
                            <a href="single-post.html">
                                <img src="../../resources/libs/essence_templete/img/blog/phy.jpg" alt="">
                            </a>
                        </div>
                        <div class="blog-item-text">

                            <h3><a href="single-post.html">MEDICINE to your Doorstep</a></h3>
                            <br>

                        </div>
                    </div>
                    <!-- Blog Item Wrapper Ends-->

                </div>
                <div class="col-lg-4 col-md-6 col-xs-12 blog-item">
                    <!-- Blog Item Starts -->
                    <div class="blog-item-wrapper">
                        <div class="blog-item-img">
                            <a href="single-post.html">
                                <img src="../../resources/libs/essence_templete/img/blog/hosw.jpg" alt="">
                            </a>
                        </div>
                        <div class="blog-item-text">

                            <h3><a href="single-post.html">HEALTHCARE to your Doorstep</a></h3>

                        </div>
                    </div>
                    <!-- Blog Item Wrapper Ends-->
                </div>

                <div class="col-lg-4 col-md-6 col-xs-12 blog-item">
                    <!-- Blog Item Starts -->
                    <div class="blog-item-wrapper">
                        <div class="blog-item-img">
                            <a href="single-post.html">
                                <img src="../../resources/libs/essence_templete/img/blog/lab.jpg" alt="">
                            </a>
                        </div>
                        <div class="blog-item-text">

                            <h3><a href="single-post.html">LAB REPORT at your fingertips</a></h3>

                        </div>
                    </div>
                    <!-- Blog Item Wrapper Ends-->
                </div>

                <div class="col-lg-4 col-md-6 col-xs-12 blog-item">
                    <!-- Blog Item Starts -->
                    <div class="blog-item-wrapper">
                        <div class="blog-item-img">
                            <a href="single-post.html">
                                <img src="../../resources/libs/essence_templete/img/blog/hoss.jpg" alt="">
                            </a>
                        </div>
                        <div class="blog-item-text">

                            <h3><a href="single-post.html">MY HEALTH Record</a></h3>

                        </div>
                    </div>
                    <!-- Blog Item Wrapper Ends-->
                </div>
            </div>
        </div>
    </section>
    <!-- blog Section End -->

    <!-- Clients Section -->
    <div id="clients" class="section">
        <!-- Container Ends -->
        <div class="container">
            <!-- Row and Scroller Wrapper Starts -->
            <div class="row" id="clients-scroller">
                <div class="client-item-wrapper">
                    <img src="../../resources/libs/essence_templete/img/clients/docc.jpg" alt="">
                </div>
                <div class="client-item-wrapper">
                    <img src="../../resources/libs/essence_templete/img/clients/doc2jpg.jpg" alt="">
                </div>
                <div class="client-item-wrapper">
                    <img src="../../resources/libs/essence_templete/img/clients/images.jpg" alt="">
                </div>
                <div class="client-item-wrapper">
                    <img src="../../resources/libs/essence_templete/img/clients/doc4jpg.jpg" alt="">
                    <br>
                </div>
                <div class="client-item-wrapper">
                    <img src="../../resources/libs/essence_templete/img/clients/images1.jpg" alt="">
                </div>
                <div class="client-item-wrapper">
                    <img src="../../resources/libs/essence_templete/img/clients/nus3.jpg" alt="">
                </div>
            </div>
        </div>
    </div>
    <!-- Client Section End -->

    <!-- Contact Section Start -->
    <section id="contact" class="section">
        <div class="contact-form">
            <div class="container">
                <div class="section-header">
                    <h2 class="section-title">Get In Touch</h2>
                    <span>Contact</span>
                    <p class="section-subtitle">Share your thourghts with us.</p>
                </div>
                <div class="row">
                    <div class="col-lg-9 col-md-9 col-xs-12">
                        <div class="contact-block">
                            <form id="contactForm">
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <input type="text" class="form-control" id="name" name="name" placeholder="Your Name" required data-error="Please enter your name">
                                            <div class="help-block with-errors"></div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <input type="text" placeholder="Your Email" id="email" class="form-control" name="name" required data-error="Please enter your email">
                                            <div class="help-block with-errors"></div>
                                        </div>
                                    </div>
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <input type="text" placeholder="Subject" id="msg_subject" class="form-control" required data-error="Please enter your subject">
                                            <div class="help-block with-errors"></div>
                                        </div>
                                    </div>
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <textarea class="form-control" id="message" placeholder="Your Message" rows="7" data-error="Write your message" required></textarea>
                                            <div class="help-block with-errors"></div>
                                        </div>
                                        <div class="submit-button">
                                            <button class="btn btn-common btn-effect" id="submit" type="submit">Send Message</button>
                                            <div id="msgSubmit" class="h3 hidden"></div>
                                            <div class="clearfix"></div>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-3 col-xs-12">
                        <div class="contact-deatils">
                            <!-- Content Info -->
                            <div class="contact-info_area">
                                <div class="contact-info">
                                    <i class="lni-map"></i>
                                    <h5>Location</h5>
                                    <p>No.25 Royal plaza Colombo 7</p>
                                </div>
                                <!-- Content Info -->
                                <div class="contact-info">
                                    <i class="lni-star"></i>
                                    <h5>E-mail</h5>
                                    <p>365Care@gmail.com</p>
                                </div>
                                <!-- Content Info -->
                                <div class="contact-info">
                                    <i class="lni-phone"></i>
                                    <h5>Phone</h5>
                                    <p>+94 11 2 143 434</p>
                                </div>
                                <!-- Icon -->
                                <ul class="footer-social">
                                    <li><a class="facebook" href="#"><i class="lni-facebook-filled"></i></a></li>
                                    <li><a class="twitter" href="#"><i class="lni-twitter-filled"></i></a></li>
                                    <li><a class="linkedin" href="#"><i class="lni-linkedin-fill"></i></a></li>
                                    <li><a class="google-plus" href="#"><i class="lni-google-plus"></i></a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Contact Section End -->




    <!-- Go To Top Link -->
    <a href="#" class="back-to-top">
        <i class="lni-arrow-up"></i>
    </a>

    <div id="loader">
        <div class="spinner">
            <div class="double-bounce1"></div>
            <div class="double-bounce2"></div>
        </div>
    </div>

    <%@include file="site-master/footer.jsp"%>

    <!-- jQuery first, then Tether, then Bootstrap JS. -->
    <script src="../../resources/libs/essence_templete/js/jquery-min.js"></script>

    <script src="../../resources/libs/essence_templete/js/popper.min.js"></script>
    <script src="../../resources/libs/essence_templete/js/bootstrap.min.js"></script>
    <script src="../../resources/libs/essence_templete/js/classie.js"></script>
    <script src="../../resources/libs/essence_templete/js/color-switcher.js"></script>
    <script src="../../resources/libs/essence_templete/js/jquery.mixitup.js"></script>
    <script src="../../resources/libs/essence_templete/js/nivo-lightbox.js"></script>
    <script src="../../resources/libs/essence_templete/js/owl.carousel.js"></script>
    <script src="../../resources/libs/essence_templete/js/jquery.stellar.min.js"></script>
    <script src="../../resources/libs/essence_templete/js/jquery.nav.js"></script>
    <script src="../../resources/libs/essence_templete/js/scrolling-nav.js"></script>
    <script src="../../resources/libs/essence_templete/js/jquery.easing.min.js"></script>
    <script src="../../resources/libs/essence_templete/js/wow.js"></script>
    <script src="../../resources/libs/essence_templete/js/jquery.vide.js"></script>
    <script src="../../resources/libs/essence_templete/js/jquery.counterup.min.js"></script>
    <script src="../../resources/libs/essence_templete/js/jquery.magnific-popup.min.js"></script>
    <script src="../../resources/libs/essence_templete/js/waypoints.min.js"></script>
    <script src="../../resources/libs/essence_templete/js/form-validator.min.js"></script>
    <script src="../../resources/libs/essence_templete/js/contact-form-script.js"></script>
    <script src="../../resources/libs/essence_templete/js/main.js"></script>

    <script src="../../resources/libs/jquery-confirm/jquery-confirm.min.js"></script>
    <link rel="stylesheet" href="../../resources/libs/jquery-confirm/jquery-confirm.min.css">
    <script src="../../resources/libs/jquery.toaster-master/jquery.toaster.js"></script>

    <script src="../../resources/libs/bootstrap-select/bootstrap-select.min.js"></script>
    <link rel="stylesheet" href="../../resources/libs/bootstrap-select/bootstrap-select.min.css">

    <link rel="stylesheet" href="../../resources/libs/jquery/jquery-ui.css">

<script>
    $(document).ready(function() {

        loadDoctors();
        loadHospitals();

        $('.selectpicker').selectpicker({
            liveSearch: true,
            showSubtext: true
        });
    });
</script>

</body>
</html>