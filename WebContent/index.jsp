<%@page import="library.LibraryString"%>
<%@page import="model.bean.Adv"%>
<%@page import="model.bean.News"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/templates/public/inc/header.jsp"%>
<!-- content-section-starts-here -->
<div class="main-body">
	<div class="wrap">
		<div class="col-md-8 content-left">
			<div class="slider">
				<div class="callbacks_wrap">
				<%
				if(request.getAttribute("alNewsSlide") != null){
					ArrayList<News> alNewsSlide = (ArrayList<News>) request.getAttribute("alNewsSlide");
				%>
					<ul class="rslides" id="slider">
					<%
					if(alNewsSlide.size() > 0){
						for(News objNewsSlide: alNewsSlide){
					%>
						<li>
						<%
						if(!"".equals(objNewsSlide.getPicture())){
						%>
							<img src="<%=request.getContextPath()%>/files/<%=objNewsSlide.getPicture() %>" alt="" />
						<%} else{ %>
							<img src="<%=request.getContextPath() %>/templates/public/images/no-image.png" alt="" />
						<%} %>
							<div class="caption">
								<a href="<%=request.getContextPath() %>/detail?nid=<%=objNewsSlide.getIdNews() %>"><%=objNewsSlide.getNameNews() %></a>
							</div>
						</li>
					<%} %>
					</ul>
				<%} } %>
				</div>
			</div>
			<div class="articles">
				<header>
					<h3 class="title-head">Post</h3>
				</header>
				<%
				CommentDao cmtDao = new CommentDao();
				if(request.getAttribute("alNews") != null){
					ArrayList<News> alNews = (ArrayList<News>) request.getAttribute("alNews");
					if(alNews.size() > 0){
						for(News objNews: alNews){
							int id = objNews.getIdNews();
							String name = objNews.getNameNews();
							String slug = LibraryString.makeSlug(name);
							String url = request.getContextPath()+"/"+slug+"-"+id+".html";
				%>
				<div class="article">
					<div class="article-left">
						<a href="<%=url %> ">
						<%
						if(!"".equals(objNews.getPicture())){
						%>
							<img src="<%=request.getContextPath()%>/files/<%=objNews.getPicture() %>" alt=""/>
						<%} else{ %>
							<img src="<%=request.getContextPath() %>/templates/public/images/no-image.png" alt="" />
						<%} %>
						</a>
					</div>
					<div class="article-right">
						<div class="article-title">
							<p>
								<%=LibraryString.getDate(objNews.getDateCreate()) %> <a class="span_link" href=""><span
									class="glyphicon glyphicon-comment"></span><%=cmtDao.countComment(objNews.getIdNews()) %> </a><a
									class="span_link" href=""><span
									class="glyphicon glyphicon-eye-open"></span><%=objNews.getReadNews() %> </a><a
									class="span_link" href=""><span
									class="glyphicon glyphicon-user"></span><%=objNews.getCreateBy() %></a>
							</p>
							<a class="title" href="<%=url %>  "><%=objNews.getNameNews() %></a>
						</div>
						<div class="article-text">
							<p><%=objNews.getPreview() %></p>
							<a href="<%=url %> "><img
								src="<%=request.getContextPath()%>/templates/public/images/more.png"
								alt="" /></a>
							<div class="clearfix"></div>
						</div>
					</div>
					<div class="clearfix"></div>
				</div>
				<%} } }%>
				<div class="paginator">
			
					<%
								int sumPage = (Integer) request.getAttribute("sumPage");
								int currentPage = (Integer) request.getAttribute("currentPage");
								int pageNum = 3;
								int numLink = (int) Math.floor((float)pageNum/2);
								int pageStart = 0;
								int pageEnd = 0;
								if(currentPage>1 && sumPage > 0){
									%><a href="<%=request.getContextPath() %>?page=<%=currentPage-1%>">previous</a> <%
								}
								if(currentPage>=pageNum){
									pageStart = currentPage-numLink;
									if(sumPage>(currentPage+numLink)){
										pageEnd = currentPage+numLink;
									} else if(currentPage<=sumPage && currentPage>(sumPage-(pageNum-1))){
										pageStart = sumPage - (pageNum-1);
										pageEnd = sumPage;
									} else{
										pageEnd = sumPage;
									}
								} else{
									pageStart = 1;
									if(sumPage>pageNum){
										pageEnd = pageNum;
									}else{
										pageEnd = sumPage;
									}
								}
								String active = "";
								for(int i=pageStart; i<=pageEnd; i++){
									if(currentPage == i){
										active = "class='active'";
									}
									else{
										active = "";
									}
							%>
								<a <%=active %> href="<%=request.getContextPath() %>?page=<%=i %> "><%=i %></a>
							<%} %>
							<%
								if(currentPage < sumPage && sumPage > 1){
									%><a href="<%=request.getContextPath() %>?page=<%=currentPage+1%>">Next</a> <%
								}
							%>
				</div>
			</div>
			<div class="photos">
				<header>
					<h3 class="title-head">Website liên quan</h3>
				</header>
				<div class="course_demo1">
				<%
				if(request.getAttribute("alAdv") != null){
					ArrayList<Adv> alAdv = (ArrayList<Adv>) request.getAttribute("alAdv");
				%>
					<ul id="flexiselDemo">
					<%
					if(alAdv.size() > 0){
						for(Adv objAdv: alAdv){
					%>
						<li>
							<a href="<%=objAdv.getLink()%>">
							<%
							if(!"".equals(objAdv.getPicture())){
							%>
								<img src="<%=request.getContextPath()%>/files/<%=objAdv.getPicture() %>" alt="" />
							<%} else{ %>
								<img src="<%=request.getContextPath() %>/templates/public/images/no-image.png" alt="" />
							<%} %>
							</a>
						</li>
					<%} }%>
					</ul>
					
				<%} %>
				</div>
				<link rel="stylesheet" href="<%=request.getContextPath()%>/templates/public/css/flexslider.css" type="text/css" media="screen" />
				<script type="text/javascript">
					$(window).load(function() {
						$("#flexiselDemo").flexisel({
							visibleItems : 4,
							animationSpeed : 1000,
							autoPlay : true,
							autoPlaySpeed : 3000,
							pauseOnHover : true,
							enableResponsiveBreakpoints : true,
							responsiveBreakpoints : {
								portrait : {
									changePoint : 480,
									visibleItems : 2
								},
								landscape : {
									changePoint : 640,
									visibleItems : 2
								},
								tablet : {
									changePoint : 768,
									visibleItems : 3
								}
							}
						});

					});
				</script>
				<script type="text/javascript"
					src="<%=request.getContextPath()%>/templates/public/js/jquery.flexisel.js"></script>
			</div>
		</div>
		<%@include file="/templates/public/inc/right_bar.jsp"%>
		<div class="clearfix"></div>
	</div>
</div>
<!-- content-section-ends-here -->
<!-- footer-section-starts-here -->
<%@include file="/templates/public/inc/footer.jsp"%>