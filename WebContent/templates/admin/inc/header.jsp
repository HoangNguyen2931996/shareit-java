<%@page import="model.bean.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

 <!doctype html>
 <%@ taglib uri="http://cksource.com/ckfinder" prefix="ckfinder" %>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <link rel="apple-touch-icon" sizes="76x76" href="<%=request.getContextPath() %>/templates/admin/images/apple-icon.png">
        <link rel="icon" type="image/png" sizes="96x96" href="<%=request.getContextPath() %>/templates/admin/images/favicon.png">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
        <title>AdminCP - VinaEnter</title>
        <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />
        <meta name="viewport" content="width=device-width" />
        <!-- Bootstrap core CSS     -->
        <link href="<%=request.getContextPath() %>/templates/admin/css/bootstrap.min.css" rel="stylesheet" />
        <!-- Animation library for notifications   -->
        <link href="<%=request.getContextPath() %>/templates/admin/css/animate.min.css" rel="stylesheet"/>
        <!--  Paper Dashboard core CSS    -->
        <link href="<%=request.getContextPath() %>/templates/admin/css/paper-dashboard.css" rel="stylesheet"/>
        <!--  CSS for Demo Purpose, don't include it in your project     -->
        <link href="<%=request.getContextPath() %>/templates/admin/css/demo.css" rel="stylesheet" />
        <!--  Fonts and icons     -->
        <link href="http://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css" rel="stylesheet">
        <link href='https://fonts.googleapis.com/css?family=Muli:400,300' rel='stylesheet' type='text/css'>
        <link href="<%=request.getContextPath() %>/templates/admin/css/themify-icons.css" rel="stylesheet">
        <script type="text/javascript" src="<%=request.getContextPath() %>/library/js/jquery-3.1.1.min.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath() %>/library/js/jquery.validate.min.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath() %>/library/ckeditor/ckeditor.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath() %>/ckfinder/ckfinder.js"></script>
    </head>
    <body>
        <div class="wrapper">
            <div class="sidebar" data-background-color="white" data-active-color="danger">
                <div class="sidebar-wrapper">
                    <div class="logo">
                    <%
                    	if(session.getAttribute("sobjUser") != null){
                    		User sobjUser = (User) session.getAttribute("sobjUser");
                    	
                    %>
                        <a href="<%=request.getContextPath() %>/admin/user/edit?uid=<%=sobjUser.getId() %>" class="simple-text"><%=sobjUser.getFullname() %></a>
                     <%} %>
                    </div>
                    <ul class="nav">
                        <li <%if("/shareit/admin/indexCat.jsp".equals(request.getRequestURI()) || "/shareit/admin/addCat.jsp".equals(request.getRequestURI()) || "/shareit/admin/editCat.jsp".equals(request.getRequestURI()) ) {out.print("class='active'");} %>>
                            <a href="<%=request.getContextPath() %>/admin/cat">
                                <i class="ti-map"></i>
                                <span>DANH MỤC</span>
                            </a>
                        </li>
                        <li <%if("/shareit/admin/indexNews.jsp".equals(request.getRequestURI()) || "/shareit/admin/addNews.jsp".equals(request.getRequestURI()) || "/shareit/admin/editNews.jsp".equals(request.getRequestURI()) || "/shareit/admin/addNews.jsp".equals(request.getRequestURI()) || "/shareit/admin/editNews.jsp".equals(request.getRequestURI())) {out.print("class='active'");} %>>
                            <a href="<%=request.getContextPath() %>/admin/news">
                                <i class="ti-view-list-alt"></i>
                                <span>BÀI VIẾT</span>
                            </a>
                        </li>
                        <li <%if("/shareit/admin/indexComment.jsp".equals(request.getRequestURI()) || "/shareit/admin/resultSearchCmt.jsp".equals(request.getRequestURI()) ) {out.print("class='active'");} %>>
                            <a href="<%=request.getContextPath() %>/admin/comment">
                                <i class="ti-panel"></i>
                                <span>BÌNH LUẬN</span>
                            </a>
                        </li>
                        <li <%if("/shareit/admin/indexUser.jsp".equals(request.getRequestURI()) || "/shareit/admin/addUser.jsp".equals(request.getRequestURI()) || "/shareit/admin/editUser.jsp".equals(request.getRequestURI())) {out.print("class='active'");} %>>
                            <a href="<%=request.getContextPath() %>/admin/user">
                                <i class="ti-user"></i>
                                <span>QUẢN TRỊ VIÊN</span>
                            </a>
                        </li>
                        <li <%if("/shareit/admin/indexAdv.jsp".equals(request.getRequestURI()) || "/shareit/admin/addAdv.jsp".equals(request.getRequestURI()) || "/shareit/admin/editAdv.jsp".equals(request.getRequestURI())) {out.print("class='active'");} %>>
                            <a href="<%=request.getContextPath() %>/admin/adv">
                                <i class="ti-view-list-alt"></i>
                                <span>QUẢNG CÁO</span>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="main-panel">
                <nav class="navbar navbar-default">
                    <div class="container-fluid">
                        <div class="navbar-header">
                            <button type="button" class="navbar-toggle">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar bar1"></span>
                            <span class="icon-bar bar2"></span>
                            <span class="icon-bar bar3"></span>
                            </button>
                            <a class="navbar-brand" href="/admin">Trang quản lý</a>
                        </div>
                        <div class="collapse navbar-collapse">
                            <ul class="nav navbar-nav navbar-right">
                                <li>
                                    <a href="<%=request.getContextPath() %>/logout">
                                        <i class="ti-unlock"></i>
                                        <p>Logout</p>
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </nav>