<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>ShareIT Signin Form</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta name="keywords"
            content="Natural Signin Form Widget Responsive, Login Form Web Template, Flat Pricing Tables, Flat Drop-Downs, Sign-Up Web Templates, Flat Web Templates, Login Sign-up Responsive Web Template, Smartphone Compatible Web Template, Free Web Designs for Nokia, Samsung, LG, Sony Ericsson, Motorola Web Design">
        <script type="application/x-javascript">
            addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } 
        </script>
        <link rel="stylesheet" href="<%=request.getContextPath() %>/templates/login/css/style.css" type="text/css" media="all">
    </head>
    <body>
        <div class="banner">
            <div class="agileinfo-dot">
                <h1>SHAREIT SIGNIN FORM</h1>
                <div class="w3layoutscontaineragileits">
                    <h2>Sign In</h2>
                    <%
                    	if(request.getParameter("msg") != null){
                    		int msg = Integer.parseInt(request.getParameter("msg"));
                    		if(msg == 1){
                    		%>
                    			<p style="color: red; font-size: 15px; margin-bottom: 20px;">Sai tên đăng nhập hoặc mật khẩu, vui lòng nhập lại</p>
                    		<%
                    		}
                    	}
                    %>
                    <form action="<%=request.getContextPath() %>/login" method="post">
                        <input type="text" Name="username" placeholder="USERNAME" required=""> 
                        <input type="password" Name="password" placeholder="PASSWORD" required="">
                        <div class="aitssendbuttonw3ls">
                            <input type="submit" value="SIGNIN">
                            <div class="clear"></div>
                        </div>
                    </form>
                </div>
                <div class="w3footeragile">
                    <p>
                        &copy; 2017 ShareIT Signin Form. All Rights Reserved | Design by <a
                            href="http://vinaenter.edu.vn" target="_blank">Nguyễn Văn
                        Hoàng</a>
                    </p>
                </div>
            </div>
        </div>
    </body>
</html>