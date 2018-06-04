<%@page import="library.LibraryString"%>
<%@page import="model.bean.News"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/templates/public/inc/header.jsp"%>
<!-- content-section-starts-here -->
<div class="main-body">
	<div class="wrap">

		<ol class="breadcrumb">
			<li><a href="<%=request.getContextPath()%>">Trang chủ</a></li>

		</ol>
		<div class="col-md-8 content-left">
			<div class="articles sports">
				<header>
					<h3 class="title-head">Kết quả tìm kiếm</h3>
				</header>
				<%
				CommentDao cmtDao = new CommentDao();
				if(request.getAttribute("alNews") != null){
					ArrayList<News> alNews = (ArrayList<News>) request.getAttribute("alNews");
					if(alNews.size() > 0 ){
						for(News objNews: alNews){
				%>
				<div class="article">
					<div class="article-left">
						<a href="<%=request.getContextPath() %>/detail?nid=<%=objNews.getIdNews() %>">
						<%
						if(!"".equals(objNews.getPicture())){
						%>
							<img src="<%=request.getContextPath() %>/files/<%=objNews.getPicture() %>" alt="" />
						<%} else{ %>
							<img src="<%=request.getContextPath() %>/templates/public/images/no-image.png" alt="" />
						<%} %>
						</a>
					</div>
					<div class="article-right">
						<div class="article-title">
							<p>
								<%=LibraryString.getDate(objNews.getDateCreate()) %>
								<a class="span_link" href=""><span class="glyphicon glyphicon-comment"></span><%=cmtDao.countComment(objNews.getIdNews()) %> </a>
								<a class="span_link" href=""><span class="glyphicon glyphicon-eye-open"></span><%=objNews.getReadNews() %> </a>
								<a class="span_link" href=""><span class="glyphicon glyphicon-user"></span><%=objNews.getCreateBy() %></a>
							</p>
							<a class="title" href="<%=request.getContextPath() %>/detail?nid=<%=objNews.getIdNews() %>"><%=objNews.getNameNews() %></a>
						</div>
						<div class="article-text">
							<p><%=objNews.getPreview() %></p>
							<a href="<%=request.getContextPath() %>/detail?nid=<%=objNews.getIdNews() %>">
								<img src="<%=request.getContextPath()%>/templates/public/images/more.png" alt="" />
							</a>
							<div class="clearfix"></div>
						</div>
					</div>
					<div class="clearfix"></div>
				</div>
				<%} } else{
					%>
					<p style="color: red; padding-left: 15px; padding-top: 20px; font-size: 20px;">Không tìm thấy kết quả. <a href="<%=request.getContextPath()%>">click here</a> để quay trở lại</p>
					<%
				}} %>
				
			
			</div>
		</div>
		<%@include file="/templates/public/inc/right_bar.jsp" %>
		<div class="clearfix"></div>
	</div>
</div>
<!-- content-section-ends-here -->
<!-- footer-section-starts-here -->
<%@include file="/templates/public/inc/footer.jsp"%>