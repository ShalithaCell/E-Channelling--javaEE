<%--
  Created by IntelliJ IDEA.
  User: shalithasenanayaka
  Date: 2019-08-10
  Time: 09:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <script src="../../../resources/libs/jquery/jquery-3.4.1.min.js"></script>

        <link href="../../../resources/libs/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet">

        <script src="../../../resources/libs/MDB/js/popper.min.js"></script>
        <link href="../../../resources/libs/MDB/css/mdb.min.css" rel="stylesheet">

        <script src="../../../resources/libs/bootstrap-4.3.1/js/bootstrap.min.js"></script>
        <link href="../../../resources/libs/bootstrap-4.3.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">


        <link href="../../../resources/styles/navbar-master.css" rel="stylesheet">


    </head>
    <body>
        <nav class="navbar navbar-icon-top navbar-expand-lg navbar-dark bg-dark">
            <a class="navbar-brand" href="#">Navbar</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="#">
                            <i class="fa fa-home"></i>
                            Home
                            <span class="sr-only">(current)</span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">
                            <i class="fa fa-envelope-o">
                                <span class="badge badge-danger">11</span>
                            </i>
                            Link
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link disabled" href="#">
                            <i class="fa fa-envelope-o">
                                <span class="badge badge-warning">11</span>
                            </i>
                            Disabled
                        </a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <i class="fa fa-envelope-o">
                                <span class="badge badge-primary">11</span>
                            </i>
                            Dropdown
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="#">Action</a>
                            <a class="dropdown-item" href="#">Another action</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="#">Something else here</a>
                        </div>
                    </li>
                </ul>
                <ul class="navbar-nav ">
                    <li class="nav-item">
                        <a class="nav-link" href="#">
                            <i class="fa fa-bell">
                                <span class="badge badge-info">11</span>
                            </i>
                            Test
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">
                            <i class="fa fa-globe">
                                <span class="badge badge-success">11</span>
                            </i>
                            Test
                        </a>
                    </li>
                </ul>
                <form class="form-inline my-2 my-lg-0">
                    <input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search">
                    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                </form>
            </div>
        </nav>
    </body>
</html>