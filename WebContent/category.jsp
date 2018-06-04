<%@page import="library.LibraryString"%>
<%@page import="model.bean.News"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/templates/public/inc/header.jsp"%>
<!-- content-section-starts-here -->
<div class="main-body">
	<div class="wrap">
	<%
		Cat objCat = (Cat) request.getAttribute("objCat");
		ArrayList<Cat> listCat = (ArrayList<Cat>) request.getAttribute("listCat");
	%>
		<ol class="breadcrumb">
			<li><a href="<%=request.getContextPath()%>">Trang chủ</a></li>
			<%
			if(objCat.getIdParent() != 0){
				for(Cat objcat: listCat){
					if(objcat.getIdCat() == objCat.getIdParent()){
			%>
				<li class="active"><%=objcat.getNameCat() %></li>
			<%} }}%>
			<li class="active"><%=objCat.getNameCat() %></li>
		</ol>
		<div class="col-md-8 content-left">
			<div class="articles sports">
				<header>
					<h3 class="title-head"><%=objCat.getNameCat() %></h3>
				</header>
				<%
				CommentDao cmtDao = new CommentDao();
				if(request.getAttribute("alNews") != null){
					ArrayList<News> alNews = (ArrayList<News>) request.getAttribute("alNews");
					if(alNews.size() > 0 ){
						for(News objNews: alNews){
							int id = objNews.getIdNews();
							String name = objNews.getNameNews();
							String slug = LibraryString.makeSlug(name);
							String url = request.getContextPath()+"/"+slug+"-"+id+".html";
				%>
				<div class="article">
					<div class="article-left">
						<a href="<%=url%>">
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
							<a class="title" href="<%=url%>"><%=objNews.getNameNews() %></a>
						</div>
						<div class="article-text">
							<p><%=objNews.getPreview() %></p>
							<a href="<%=url %>">
								<img src="<%=request.getContextPath()%>/templates/public/images/more.png" alt="" />
							</a>
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
									%><a href="<%=request.getContextPath() %>/cat?cid=<%=objCat.getIdCat() %>&&page=<%=currentPage-1%>">previous</a> <%
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
								<a <%=active %> href="<%=request.getContextPath() %>/danh-muc/<%=LibraryString.makeSlug(objCat.getNameCat()) %>-<%=objCat.getIdCat() %>?page=<%=i %> "><%=i %></a>
							<%} %>
							<%
								if(currentPage < sumPage && sumPage > 1){
									%><a href="<%=request.getContextPath() %>/cat?cid=<%=objCat.getIdCat() %>&&page=<%=currentPage+1%>">Next</a> <%
								}
							%>
				</div>
			</div>
		</div>
		<%@include file="/templates/public/inc/right_bar.jsp" %>
		<div class="clearfix"></div>
	</div>
</div>
<!-- content-section-ends-here -->
<!-- footer-section-starts-here -->
<%@include file="/templates/public/inc/footer.jsp"%>