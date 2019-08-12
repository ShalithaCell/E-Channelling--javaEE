<%--
  Created by IntelliJ IDEA.
  User: shalithasenanayaka
  Date: 2019-08-11
  Time: 22:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <script src="../../../resources/libs/jquery/jquery-3.4.1.min.js"></script>

        <link href="../../../resources/libs/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet">

        <script src="../../../resources/libs/MDB/js/popper.min.js"></script>
        <link href="../../../resources/libs/MDB/css/mdb.min.css" rel="stylesheet">

        <script src="../../../resources/libs/bootstrap-4.3.1/js/bootstrap.min.js"></script>
        <link href="../../../resources/libs/bootstrap-4.3.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">

        <link href="../../../resources/styles/style-login.css" rel="stylesheet">
        <script src="../../../resources/scripts/script-login.js"></script>

        <script src="../../../resources/scripts/script-default.js"></script>

        <script type="text/javascript">
            $( document ).ready(function() {
                var session = '<%= session.getAttribute("page") %>';
                sessionManagement(name);
            });
        </script>

    </head>
    <body>

        <%@include file="../site-master/header.jsp"%>

        <div style="height: 100%">
        <div class="cotn_principal">
            <div class="cont_centrar">

                <div class="cont_login">
                    <div class="cont_info_log_sign_up">
                        <div class="col_md_login">
                            <div class="cont_ba_opcitiy">

                                <h2>LOGIN</h2>
                                <p></p>
                                <button class="btn_login" onclick="cambiar_login()">LOGIN</button>
                            </div>
                        </div>
                        <div class="col_md_sign_up">
                            <div class="cont_ba_opcitiy">
                                <h2>SIGN UP</h2>


                                <p></p>

                                <button class="btn_sign_up" onclick="cambiar_sign_up()">SIGN UP</button>
                            </div>
                        </div>
                    </div>


                    <div class="cont_back_info">
                        <div class="cont_img_back_grey">
                            <img src="https://www.gtagangwars.de/suite/images/styleLogo-6bd77433ddf78bd8477ea7306e804f677bc925d0.png" alt="" />
                        </div>

                    </div>
                    <div class="cont_forms" >
                        <div class="cont_img_back_">
                            <img src="https://www.gtagangwars.de/suite/images/styleLogo-6bd77433ddf78bd8477ea7306e804f677bc925d0.png" alt="" />
                        </div>
                        <div class="cont_form_login">
                            <a href="#" onclick="ocultar_login_sign_up()" ><i class="material-icons"></i></a>
                            <h2>LOGIN</h2>
                            <input type="text" placeholder="Email" />
                            <input type="password" placeholder="Password" />
                            <button class="btn_login" onclick="cambiar_login()">LOGIN</button>
                        </div>

                        <div class="cont_form_sign_up">
                            <a href="#" onclick="ocultar_login_sign_up()"><i class="material-icons"></i></a>
                            <h2>SIGN UP</h2>
                            <input type="text" placeholder="Email" />
                            <input type="password" placeholder="Password" />
                            <input type="password" placeholder="Confirm Password" />
                            <button class="btn_sign_up" onclick="cambiar_sign_up()">SIGN UP</button>

                        </div>

                    </div>

                </div>
            </div>
        </div>
        </div>


    </body>
</html>
