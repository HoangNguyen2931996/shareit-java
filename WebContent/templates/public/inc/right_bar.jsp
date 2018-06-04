<%@page import="model.bean.Comment"%>
<%@page import="model.dao.CommentDao"%>
<%@page import="library.LibraryString"%>
<%@page import="model.bean.News"%>
<%@page import="model.dao.NewsDao"%>
<%@page import="model.dao.CatDao"%>
<%@page import="model.bean.Cat"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="col-md-4 side-bar">
	<div class="first_half">
<%!
   public String menu(HttpServletRequest request, ArrayList<Cat> alCat, int idParent, int distance, String str) {
   	ArrayList<Cat> alLoop = new ArrayList();
   	ArrayList<Cat> alContinuous = new ArrayList();
   	for (Cat objCat : alCat){
   		if (objCat.getIdParent() == idParent){
   			alLoop.add(objCat);
   		}else{
   			alContinuous.add(objCat);
   		}
   	}
   	for(Cat objLoop: alLoop){
 		str += 	"<ul>"+
				"<li style='padding-left: "+distance+"px'>"+
					"<a href='"+request.getContextPath() +"/danh-muc/"+LibraryString.makeSlug(objLoop.getNameCat())+"-"+objLoop.getIdCat()+"'>"+ objLoop.getNameCat() + " </a>";
		str = menu(request, alContinuous, objLoop.getIdCat(), distance + 60, str);
		str +=		"</li>"+
				"</ul>";
   		
   	}
	return str;
}
   %>
		<div class="categories">
			<header>
				<h3 class="side-title-head">Danh mục</h3>
			</header>
			<%
				String str = "";
				CatDao catdao = new CatDao();
				if(catdao.getItems().size()>0){
					str = menu(request, catdao.getItems(), 0, 0, str);
	    			out.print(str);
				}
			%>
		</div>
		<div class="list_vertical">
			<section class="accordation_menu">
				<div>
					<input id="label-1" name="lida" type="radio" checked /> 
					<label for="label-1" id="item1"><i class="ferme"> </i>
					Bài viết phổ biến<i class="icon-plus-sign i-right1"></i><i class="icon-minus-sign i-right2"></i></label>
					<div class="content" id="a1">
						<div class="scrollbar" id="style-2">
							<div class="force-overflow">
								<div class="popular-post-grids">
								<%
								CommentDao cmtdao = new CommentDao();
								NewsDao newsDao = new NewsDao();
								ArrayList<News> alNewsByRead = newsDao.getNewsByRead();
								if(alNewsByRead.size()>0){
									for(News objNewsByRead: alNewsByRead){
										int id = objNewsByRead.getIdNews();
										String name = objNewsByRead.getNameNews();
										String slug = LibraryString.makeSlug(name);
										String url = request.getContextPath()+"/"+slug+"-"+id+".html";
								%>
									<div class="popular-post-grid">
										<div class="post-img">
											<a href="<%=url %>">
											<%
											if(!"".equals(objNewsByRead.getPicture())){
											%>
												<img src="<%=request.getContextPath()%>/files/<%=objNewsByRead.getPicture() %>" alt="" />
											<%} else {%>
												<img src="<%=request.getContextPath() %>/templates/public/images/no-image.png" alt="" />
											<%} %>
											</a>
										</div>
										<div class="post-text">
											<a class="pp-title" href="<%=url%>"><%=objNewsByRead.getNameNews() %></a>
											<p>
												<%=LibraryString.getDate(objNewsByRead.getDateCreate()) %> <a class="span_link" href="#"><span
													class="glyphicon glyphicon-comment"></span><%=cmtdao.countComment(objNewsByRead.getIdNews()) %> </a><a
													class="span_link" href=""><span
													class="glyphicon glyphicon-eye-open"></span><%=objNewsByRead.getReadNews() %> </a>
											</p>
										</div>
										<div class="clearfix"></div>
									</div>
									<%} }%>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div>
					<input id="label-2" name="lida" type="radio" /> <label
						for="label-2" id="item2"><i class="icon-leaf" id="i2"></i>Bài đăng gần đây<i class="icon-plus-sign i-right1"></i><i
						class="icon-minus-sign i-right2"></i></label>
					<div class="content" id="a2">
						<div class="scrollbar" id="style-2">
							<div class="force-overflow">
								<div class="popular-post-grids">
								<%
								ArrayList<News> alNewsByDate = newsDao.getNewsByDate();
								if(alNewsByDate.size()>0){
									for(News objNewsByDate: alNewsByDate){
										int id = objNewsByDate.getIdNews();
										String name = objNewsByDate.getNameNews();
										String slug = LibraryString.makeSlug(name);
										String url = request.getContextPath()+"/"+slug+"-"+id+".html";
								%>
									<div class="popular-post-grid">
										<div class="post-img">
											<a href="<%=url%>">
											<%
											if(!"".equals(objNewsByDate.getPicture())){
											%>
												<img src="<%=request.getContextPath()%>/files/<%=objNewsByDate.getPicture() %>" alt="" />
											<%} else {%>
												<img src="<%=request.getContextPath() %>/templates/public/images/no-image.png" alt="" />
											<%} %>
											</a>
										</div>
										<div class="post-text">
											<a class="pp-title" href="<%=url %>"><%=objNewsByDate.getNameNews() %></a>
											<p>
												<%=LibraryString.getDate(objNewsByDate.getDateCreate()) %> <a class="span_link" href="#"><span
													class="glyphicon glyphicon-comment"></span><%=cmtdao.countComment(objNewsByDate.getIdNews()) %> </a><a
													class="span_link" href="#"><span
													class="glyphicon glyphicon-eye-open"></span><%=objNewsByDate.getReadNews() %> </a>
											</p>
										</div>
										<div class="clearfix"></div>
									</div>
									<%} }%>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div>
					<input id="label-3" name="lida" type="radio" /> <label
						for="label-3" id="item3"><i class="icon-trophy" id="i3"></i>Bình luận mới nhất<i
						class="icon-plus-sign i-right1"></i><i
						class="icon-minus-sign i-right2"></i></label>
					<div class="content" id="a3">
						<div class="scrollbar" id="style-2">
							<div class="force-overflow">
								<div class="response">
								<%
								CommentDao commentDao = new CommentDao();
								ArrayList<Comment> alComment = commentDao.getCommentRecent();
								if(alComment.size() > 0){
									for(Comment objCmt: alComment){
										int id = objCmt.getId_news();
										String name = objCmt.getName_news();
										String slug = LibraryString.makeSlug(name);
										String url = request.getContextPath()+"/"+slug+"-"+id+".html";
								%>
									<div class="media response-info">
										<div class="media-left response-text-left">
											<a href="<%=url%>">
												<img class="media-object" src="<%=request.getContextPath()%>/templates/public/images/noavatar.png" alt="" />
											</a>
											<h5>
												<a href="<%=request.getContextPath() %>/detail?nid=<%=objCmt.getId_news()%>"><%=objCmt.getUsername() %></a>
											</h5>
										</div>
										<div class="media-body response-text-right">
											<p><%=objCmt.getContent() %></p>
											<ul>
												<li><%=LibraryString.getDateTime(objCmt.getDate_cmt()) %></li>
												<li><a href="<%=url%> ">Trả lời</a></li>
											</ul>
										</div>
										<div class="clearfix"></div>
									</div>
									<%} }%>
								</div>
							</div>
						</div>
					</div>
				</div>
			</section>
		</div>
	</div>
	<div class="secound_half">
		<div class="popular-news" style="margin-top: 20px;">
			<header>
				<h3 class="title-popular">Bài viết ngẫu nhiên</h3>
			</header>
			<div class="popular-grids">
			<%
			ArrayList<News> alNewsRand  = newsDao.getNewsRand();
			if(alNewsRand.size()>0){
				for(News objNewsRand: alNewsRand){
					int id = objNewsRand.getIdNews();
					String name = objNewsRand.getNameNews();
					String slug = LibraryString.makeSlug(name);
					String url = request.getContextPath()+"/"+slug+"-"+id+".html";
			%>
				<div class="popular-grid">
					<a href="<%=url %>">
					<%
					if(!"".equals(objNewsRand.getPicture())){
					%>
						<img src="<%=request.getContextPath()%>/files/<%=objNewsRand.getPicture() %>" alt="" />
					<%} else {%>
						<img src="<%=request.getContextPath() %>/templates/public/images/no-image.png" alt="" />
					<%} %>						
					</a> 
					<a class="title" href="<%=url %>"><%=objNewsRand.getNameNews() %></a>
					<p>
						<%=LibraryString.getDate(objNewsRand.getDateCreate()) %> <a class="span_link" href="#"><span
							class="glyphicon glyphicon-comment"></span>0 </a><a class="span_link"
							href="#"><span class="glyphicon glyphicon-eye-open"></span><%=objNewsRand.getReadNews() %>
						</a>
					</p>
				</div>
				<%} }%>
			</div>
		</div>
	</div>
	<div class="clearfix"></div>
</div>