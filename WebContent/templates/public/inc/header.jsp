<%@page import="library.LibraryString"%>
<%@page import="model.bean.News"%>
<%@page import="model.dao.NewsDao"%>
<%@page import="model.dao.CatDao"%>
<%@page import="model.bean.Cat"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>ShareIT</title>
        <link href="<%=request.getContextPath() %>/templates/public/css/bootstrap.css" rel='stylesheet' type='text/css' />
        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="<%=request.getContextPath() %>/templates/public/js/jquery.min.js"></script> 
        <script type="text/javascript" src="<%=request.getContextPath() %>/library/js/jquery.validate.min.js"></script>
        <!-- Custom Theme files -->
        <link href="<%=request.getContextPath() %>/templates/public/css/style.css" rel="stylesheet" type="text/css" media="all" />
        <!-- Custom Theme files -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="keywords" content="Express News Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
            Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
        <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
        <!-- for bootstrap working -->
        <script type="text/javascript" src="<%=request.getContextPath() %>/templates/public/js/bootstrap.js"></script>
        <!-- //for bootstrap working -->
        <!-- web-fonts -->
        <script src="<%=request.getContextPath() %>/templates/public/js/responsiveslides.min.js"></script>
        <script>
            $(function () {
              $("#slider").responsiveSlides({
            	auto: true,
            	nav: true,
            	speed: 500,
            	namespace: "callbacks",
            	pager: true,
              });
            });
        </script>
        <script type="text/javascript" src="<%=request.getContextPath() %>/templates/public/js/move-top.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath() %>/templates/public/js/easing.js"></script>
        <!--/script-->
        <script type="text/javascript">
            jQuery(document).ready(function($) {
            	$(".scroll").click(function(event){		
            		event.preventDefault();
            		$('html,body').animate({scrollTop:$(this.hash).offset().top},900);
            	});
            });
        </script>
    </head>
    <body>
    <div id="fb-root"></div>
	<script>
		(function(d, s, id) {
		  var js, fjs = d.getElementsByTagName(s)[0];
		  if (d.getElementById(id)) return;
		  js = d.createElement(s); js.id = id;
		  js.src = "//connect.facebook.net/vi_VN/sdk.js#xfbml=1&version=v2.9&appId=666879063521688";
		  fjs.parentNode.insertBefore(js, fjs);
		}(document, 'script', 'facebook-jssdk'));
	</script>
        <!-- header-section-starts-here -->
        <div class="header">
            <div class="header-bottom">
                <div class="logo text-center">
                    <a href="<%=request.getContextPath()%>"><img src="<%=request.getContextPath() %>/templates/public/images/shareit-png-18.png" alt="" /></a>
                </div>
                <div class="navigation">
                    <nav class="navbar navbar-default" role="navigation">
                        <div class="wrap">
                            <div class="navbar-header">
                                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                                <span class="sr-only">Toggle navigation</span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                                </button>
                            </div>
                            <!--/.navbar-header-->
                            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                            <%
                            CatDao catDao = new CatDao();
                            NewsDao nDao = new NewsDao();
                        	ArrayList<Cat> alCat = catDao.getItems();
                            %>
                          	 	<div class="head-nav">
									<span class="menu"> </span>
									<ul class="cl-effect-1">
									<li <%if("/shareit/index.jsp".equals(request.getRequestURI())) {out.print("class='active'");} %>><a href="<%=request.getContextPath()%>">Trang chủ</a></li>
									<%
									int cid=0;
									int pid=0;
									int nid=0;
									if(request.getParameter("cid") != null){
										cid = Integer.parseInt(request.getParameter("cid"));
										pid = catDao.getItem(cid).getIdParent(); 
									}
									if(request.getParameter("nid") != null){
										nid = Integer.parseInt(request.getParameter("nid"));
										cid = nDao.getItem(nid).getIdCat();
										pid = catDao.getItem(cid).getIdParent(); 
									}
									for(Cat objCat: alCat){
										if(objCat.getIdParent() == 0){
											int id = objCat.getIdCat();
											String name = objCat.getNameCat();
											String slug = LibraryString.makeSlug(name);
											String url = request.getContextPath() +"/danh-muc/"+slug+"-"+id;
									%>
										<li <% if(cid == id || pid == id) {out.print("class='active'");} %>>
											<a href="<%=url %>"><%=objCat.getNameCat() %></a>
											<%
											if(catDao.checkChild(objCat.getIdCat()).size()>0){
											%>
											<ul class="level2">
											<%
											for(Cat itemCat: alCat){
												if(itemCat.getIdParent() == objCat.getIdCat()){
											%>
												<li><a href="<%=request.getContextPath() %>/cat?cid=<%=itemCat.getIdCat() %>"><%=itemCat.getNameCat() %></a></li>
											<%} }%>
											</ul>
											<%} %>
										</li>
									<%} }%>
									</ul>
								</div>
                                <div class="search">
                                
                                    <!-- start search-->
                                    <div class="search-box">
                                        <div id="sb-search" class="sb-search">
                                            <form action="<%=request.getContextPath() %>/search" method="post">
                                                <input class="sb-search-input" placeholder="Nhập từ khóa bạn muốn tìm kiếm" type="search" name="search" id="search">
                                                <input class="sb-search-submit" type="submit" value="">
                                                <span class="sb-icon-search"></span>
                                            </form>
                                        </div>
                                    </div>
                                    <!-- search-scripts -->
                                    <script src="<%=request.getContextPath() %>/templates/public/js/classie.js"></script>
                                    <script src="<%=request.getContextPath() %>/templates/public/js/uisearch.js"></script>
                                    <script>
                                        new UISearch( document.getElementById( 'sb-search' ) );
                                    </script>
                                    <!-- //search-scripts -->
                                </div>
                                <div class="clearfix"></div>
                            </div>
                        </div>
                        <!--/.navbar-collapse-->
                        <!--/.navbar-->
                </div>
                </nav>
            </div>
        </div>
        <!-- header-section-ends-here -->
        <div class="wrap">
            <div class="move-text">
                <div class="breaking_news">
                    <h2>Mới cập nhập</h2>
                </div>
                <div class="marquee">
                <%
                NewsDao newsdao = new NewsDao();
                if(newsdao.getNewsByDate().size()>0){
                	for(News objNews: newsdao.getNewsByDate()){
                		int id = objNews.getIdNews();
						String name = objNews.getNameNews();
						String slug = LibraryString.makeSlug(name);
						String url = request.getContextPath()+"/"+slug+"-"+id+".html";
                %>
                    <div class="marquee1"><a class="breaking" href="<%=url %>">>><%=objNews.getNameNews() %></a></div>
                 <%}} %>
                    <div class="clearfix"></div>
                </div>
                <div class="clearfix"></div>
                <script type="text/javascript" src="<%=request.getContextPath() %>/templates/public/js/jquery.marquee.min.js"></script>
                <script>
                    $('.marquee').marquee({ pauseOnHover: true });
                    //@ sourceURL=pen.js
                </script>
            </div>
        </div>