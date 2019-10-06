<%--
  Created by IntelliJ IDEA.
  User: shalithasenanayaka
  Date: 2019-09-19
  Time: 08:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

    <head>
        <script src="../../../resources/libs/jquery/jquery-3.4.1.min.js"></script>



        <link href="../../../resources/libs/bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="../../../resources/libs/bootstrap-3.3.7/js/bootstrap.min.js"></script>





        <title>365Care - Admin Panel</title>

        <style>
            @import url(http://fonts.googleapis.com/css?family=Roboto+Condensed:400,700);
            .board{
                width: 75%;
                margin: 60px auto;
                height: 500px;
                background: #fff;
                /*box-shadow: 10px 10px #ccc,-10px 20px #ddd;*/
            }
            .board .nav-tabs {
                position: relative;
                /* border-bottom: 0; */
                /* width: 80%; */
                margin: 40px auto;
                margin-bottom: 0;
                box-sizing: border-box;

            }

            .panel-resize{
                padding-left: 1% !important;
                padding-right: 1% !important;
            }

            .board > div.board-inner{
                background: #fafafa url(http://subtlepatterns.com/patterns/geometry2.png);
                background-size: 30%;
            }

            p.narrow{
                width: 60%;
                margin: 10px auto;
            }

            .liner{
                margin-right: 3% !important;
                height: 2px;
                background: #ddd;
                position: absolute;
                width: 80%;
                margin: 0 auto;
                left: 0;
                right: 0;
                top: 50%;
                z-index: 1;
            }

            .nav-tabs > li.active > a, .nav-tabs > li.active > a:hover, .nav-tabs > li.active > a:focus {
                color: #555555;
                cursor: default;
                /* background-color: #ffffff; */
                border: 0;
                border-bottom-color: transparent;
            }

            span.round-tabs{
                width: 70px;
                height: 70px;
                line-height: 70px;
                display: inline-block;
                border-radius: 100px;
                background: white;
                z-index: 2;
                position: absolute;
                left: 0;
                text-align: center;
                font-size: 25px;
            }

            span.round-tabs.one{
                color: rgb(34, 194, 34);border: 2px solid rgb(34, 194, 34);
            }

            li.active span.round-tabs.one{
                background: #fff !important;
                border: 2px solid #ddd;
                color: rgb(34, 194, 34);
            }

            span.round-tabs.two{
                color: #febe29;border: 2px solid #febe29;
            }

            .bootstrap-select .bs-ok-default::after {
                width: 0.3em;
                height: 0.6em;
                border-width: 0 0.1em 0.1em 0;
                transform: rotate(45deg) translateY(0.5rem);
            }

            .btn.dropdown-toggle:focus {
                outline: none !important;
            }

            li.active span.round-tabs.two{
                background: #fff !important;
                border: 2px solid #ddd;
                color: #febe29;
            }

            span.round-tabs.three{
                color: #3e5e9a;border: 2px solid #3e5e9a;
            }

            li.active span.round-tabs.three{
                background: #fff !important;
                border: 2px solid #ddd;
                color: #3e5e9a;
            }

            span.round-tabs.four{
                color: #f1685e;border: 2px solid #f1685e;
            }

            li.active span.round-tabs.four{
                background: #fff !important;
                border: 2px solid #ddd;
                color: #f1685e;
            }

            span.round-tabs.five{
                color: #999;border: 2px solid #999;
            }

            li.active span.round-tabs.five{
                background: #fff !important;
                border: 2px solid #ddd;
                color: #999;
            }

            .nav-tabs > li.active > a span.round-tabs{
                background: #fafafa;
            }
            .nav-tabs > li {
                width: 20%;
            }
            /*li.active:before {
                content: " ";
                position: absolute;
                left: 45%;
                opacity:0;
                margin: 0 auto;
                bottom: -2px;
                border: 10px solid transparent;
                border-bottom-color: #fff;
                z-index: 1;
                transition:0.2s ease-in-out;
            }*/
            .nav-tabs > li:after {
                content: " ";
                position: absolute;
                left: 75%;
                opacity:0;
                margin: 0 auto;
                bottom: 0px;
                border: 5px solid transparent;
                border-bottom-color: #ddd;
                transition:0.1s ease-in-out;

            }
            .nav-tabs > li.active:after {
                content: " ";
                position: absolute;
                left: 70%;
                opacity:1;
                margin: 0 auto;
                bottom: 0px;
                border: 10px solid transparent;
                border-bottom-color: #ddd;

            }
            .nav-tabs > li a{
                width: 70px;
                height: 70px;
                margin: 20px auto;
                border-radius: 100%;
                padding: 0;
            }

            .nav-tabs > li a:hover{
                background: transparent;
            }

            .tab-content{
            }
            .tab-pane{
                position: relative;
                padding-top: 50px;
            }
            .tab-content .head{
                font-family: 'Roboto Condensed', sans-serif;
                font-size: 25px;
                text-transform: uppercase;
                padding-bottom: 10px;
            }
            .btn-outline-rounded{
                padding: 10px 40px;
                margin: 20px 0;
                border: 2px solid transparent;
                border-radius: 25px;
            }

            .btn.green{
                background-color:#5cb85c;
                /*border: 2px solid #5cb85c;*/
                color: #ffffff;
            }



            @media( max-width : 585px ){

                .board {
                    width: 90%;
                    height:auto !important;
                }
                span.round-tabs {
                    font-size:16px;
                    width: 50px;
                    height: 50px;
                    line-height: 50px;
                }
                .tab-content .head{
                    font-size:20px;
                }
                .nav-tabs > li a {
                    width: 50px;
                    height: 50px;
                    line-height:50px;
                }

                .nav-tabs > li.active:after {
                    content: " ";
                    position: absolute;
                    left: 35%;
                }

                .btn-outline-rounded {
                    padding:12px 20px;
                }
            }

            .custom-top-mar{
                margin-top: 25%;
            }

            .custom-right-mar{
                margin-right: 10% !important;
            }

        </style>

        <script>
            $(function(){
                $('a[title]').tooltip();
            });

            $(document).ready(function () {


                LoadHospitals();

                $("#li_Doctors").click(function(){

                    setTimeout(LoadDoctors, 1000);

                });


            });


            function addNewMedicalCenter() {
                $.ajax({
                    url: 'adminPanel',
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
            }

        </script>

    </head>

    <body>

        <%@include file="../site-master/header.jsp"%>

        <section style="background:#efefe9;height: 100%;">
            <div class="container">
                <div class="row">
                    <div class="board ">
                        <!-- <h2>Welcome to IGHALO!<sup>™</sup></h2>-->
                        <div class="board-inner">
                            <ul class="nav nav-tabs custom-right-mar" id="myTab">
                                <div class="liner"></div>
                                <li class="active">
                                    <a href="#home" data-toggle="tab" title="hospitals">
                      <span class="round-tabs one">
                              <i class="glyphicon glyphicon-home custom-top-mar"></i>
                      </span>
                                    </a></li>

                                <li><a href="#profile" data-toggle="tab" id="li_Doctors" title="doctors">
                     <span class="round-tabs two">
                         <i class="glyphicon glyphicon-user custom-top-mar"></i>
                     </span>
                                </a>
                                </li>
                                <li><a href="#messages" data-toggle="tab" title="bootsnipp goodies">
                     <span class="round-tabs three">
                          <i class="glyphicon glyphicon-gift custom-top-mar"></i>
                     </span> </a>
                                </li>

                                <li><a href="#settings" data-toggle="tab" title="blah blah">
                         <span class="round-tabs four">
                              <i class="glyphicon glyphicon-comment custom-top-mar"></i>
                         </span>
                                </a></li>

                                <li><a href="#doner" data-toggle="tab" title="completed">
                         <span class="round-tabs five">
                              <i class="glyphicon glyphicon-ok custom-top-mar"></i>
                         </span> </a>
                                </li>

                            </ul></div>

                        <div class="tab-content ">
                            <div class="tab-pane fade in active" id="home">

                                <div class="panel panel-primary">
                                    <div class="panel-heading form-inline">
                                        <h3 class="panel-title">List Of Medical Centers</h3>
                                        <div class="pull-right">
                                            <input type="button" onclick="addHospital()" class="btn btn-success " value="Add New Hospital/Medical Center" style="margin-top: -10%;">
                                        </div>
                                    </div>
                                    <div class="panel-body">

                                    </div>
                                    <table class="table table-hover" id="tbl_hospital">
                                        <thead>
                                            <tr>
                                                <th>Hospital ID</th>
                                                <th>Description</th>
                                                <th>Address</th>
                                                <th>Active</th>
                                                <th>Edit</th>
                                                <th>Remove</th>
                                            </tr>
                                        </thead>
                                        <tbody>

                                        </tbody>
                                    </table>
                                </div>

                            </div>
                            <div class="tab-pane fade" id="profile">
                                <div class="panel panel-primary">
                                    <div class="panel-heading form-inline">
                                        <h3 class="panel-title">List Of Doctors</h3>
                                        <div class="pull-right">
                                            <input type="button" onclick="AddDoctor()" class="btn btn-success " value="Add New Doctor" style="margin-top: -18%;">
                                        </div>
                                    </div>
                                    <div class="panel-body">

                                    </div>
                                    <table class="table table-hover" id="tbl_doctors">
                                        <thead>
                                            <tr>
                                                <th>Doctor ID</th>
                                                <th>Doctor Name</th>
                                                <th>Doctor Speciality</th>
                                                <th>Active</th>
                                                <th>Edit</th>
                                                <th>Remove</th>
                                            </tr>
                                        </thead>
                                        <tbody>

                                        </tbody>
                                    </table>
                                </div>

                            </div>
                            <div class="tab-pane fade" id="messages">

                            </div>
                            <div class="tab-pane fade" id="settings">

                            </div>
                            <div class="tab-pane fade" id="doner">

                            </div>
                            <div class="clearfix"></div>
                        </div>

                    </div>
                </div>
            </div>
        </section>

        <script src="../../../resources/libs/jquery-datatables/datatables.min.js"></script>
        <link href="../../../resources/libs/jquery-datatables/datatables.min.css" rel="stylesheet" >

        <script src="../../../resources/scripts/script-admin-panel.js"></script>

        <link href="../../../resources/styles/style-default.css" rel="stylesheet" />

        <%@include file="../site-master/footer.jsp"%>
    </body>



</html>
