<%--
  Created by IntelliJ IDEA.
  User: shalithasenanayaka
  Date: 2019-10-07
  Time: 08:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <link href="../../../resources/libs/bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="../../../resources/libs/bootstrap-3.3.7/js/bootstrap.min.js"></script>
        <script src="../../../resources/libs/jquery/jquery-3.4.1.min.js"></script>

        <link rel="stylesheet" type="text/css" href="../../../resources/styles/style-payemt.css" />

        <meta charset="ISO-8859-1">
        <title>Insert title here</title>
    </head>
    <body>
        <div class="container" style="position: absolute;
    /* width: 300px; */
    /* height: 200px; */
    z-index: 15;
    top: 40%;
    left: 43%;
    margin: -100px 0 0 -150px;">
            <div class="row">
                <div class="col-xs-12 col-md-4">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">
                                Payment Details
                            </h3>
                            <div class="checkbox pull-right">
                                <label>
                                    <input type="checkbox" />
                                    Remember
                                </label>
                            </div>
                        </div>
                        <div class="panel-body">
                            <form  method="post" action="payment">
                                <div class="form-group">
                                    <label for="cardNumber" name="cardNum">
                                        CARD NUMBER</label>
                                    <div class="input-group">
                                        <input type="text" class="form-control" id="cardNumber" placeholder="Valid Card Number"
                                               required autofocus />
                                        <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-xs-7 col-md-7">
                                        <div class="form-group">
                                            <label for="expityMonth">
                                                EXPIRY DATE</label>
                                            <div class="col-xs-6 col-lg-6 pl-ziro">
                                                <input type="text" class="form-control" id="expityMonth" placeholder="MM" required />
                                            </div>
                                            <div class="col-xs-6 col-lg-6 pl-ziro">
                                                <input type="text" class="form-control" id="expityYear" placeholder="YY" required /></div>
                                        </div>
                                    </div>
                                    <div class="col-xs-5 col-md-5 pull-right">
                                        <div class="form-group">
                                            <label for="cvCode">
                                                CV CODE</label>
                                            <input type="password" class="form-control" id="cvCode" placeholder="CV" required />
                                        </div>
                                    </div>
                                </div>


                                <ul class="nav nav-pills nav-stacked" name = "finalPay">
                                    <li class="active"><a href="#"><span class="badge pull-right"><span class="glyphicon glyphicon-usd"></span>4200</span> Final Payment</a>
                                    </li>
                                    <input type="hidden" name="amount" value="4200">
                                </ul>
                                <br/>

                                <input type="submit" class="btn btn-success btn-lg btn-block" role="button"/>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>




    </body>
</html>
