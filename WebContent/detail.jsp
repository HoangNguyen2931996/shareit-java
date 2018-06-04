<%@page import="library.LibraryString"%>
<%@page import="model.bean.Comment"%>
<%@page import="model.bean.News"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/templates/public/inc/header.jsp"%>
<!-- content-section-starts-here -->
<div class="main-body">
	<div class="wrap">
		<ol class="breadcrumb">
		<%
		News objnews = (News) request.getAttribute("objNews");
		ArrayList<Cat> listCat = (ArrayList<Cat>) request.getAttribute("listCat");
		%>
			<li><a href="<%=request.getContextPath()%>">Trang chủ</a></li>
		<%
		if(objnews.getIdParent() != 0){
			for(Cat objcat: listCat){
				if(objcat.getIdCat() == objnews.getIdParent()){
		%>
			<li class="active"><%=objcat.getNameCat() %></li>
		<%} }}%>	
			<li class="active"><%=objnews.getNameCat() %></li>
		</ol>
		<div class="single-page">
			<div class="col-md-2 share_grid">
				<h3>SHARE</h3>
				<ul>
					<li>
						<div class="fb-share-button" data-href="http://localhost:8080/shareit/<%=LibraryString.makeSlug(objnews.getNameNews()) %>-<%=objnews.getIdNews() %>.html" data-layout="button_count" data-size="small" data-mobile-iframe="true"><a class="fb-xfbml-parse-ignore" target="_blank" href="https://www.facebook.com/sharer/sharer.php?u=http%3A%2F%2Flocalhost%3A8080%2Fshareit%2F&amp;src=sdkpreparse">Chia sẻ</a></div>
					</li>
					<li>
						<script type="text/javascript" src="https://apis.google.com/js/plusone.js" ></script>
						<g:plusone size="medium" ></g:plusone>
					</li>
				</ul>
			</div>
			<div class="col-md-6 content-left single-post">
				<div class="blog-posts">
				<%
				News objNews = null;
				if(request.getAttribute("objNews") != null){
					objNews = (News) request.getAttribute("objNews");
				%>
					<h3 class="post"><%=objNews.getNameNews() %></h3>
					<div class="last-article">
						<p class="artext"><%=objNews.getDetail() %></p>
						<div class="clearfix"></div>
				<% }%>
						<!--related-posts-->
						<div class="row related-posts">
							<h4>Bài viết liên quan</h4>
							<%
							if(request.getAttribute("alNewsLQ") != null){
								ArrayList<News> alNewsLQ = (ArrayList<News>) request.getAttribute("alNewsLQ");
								if(alNewsLQ.size() > 0){
									for(News objNewsLQ: alNewsLQ){
							%>
							<div class="col-xs-6 col-md-3 related-grids">
								<a href="<%=request.getContextPath() %>/detail?nid=<%=objNewsLQ.getIdNews() %>" class="thumbnail">
								<%
								if(!"".equals(objNewsLQ.getPicture())){
								%> 
									<img src="<%=request.getContextPath()%>/files/<%=objNewsLQ.getPicture() %>" alt="" />
								<%} else{ %>
									<img src="<%=request.getContextPath() %>/templates/public/images/no-image.png" alt="" />
								<%} %>
								</a>
								<h5>
								<%
								String detailNameNews = objNewsLQ.getNameNews().substring(0, 16);
								%>
									<a href="<%=request.getContextPath() %>/detail?nid=<%=objNewsLQ.getIdNews() %>"><%=detailNameNews %>...</a>
								</h5>
							</div>
							<%}}} %>
						</div>
						<!--//related-posts-->
						<h4 style="color: #000000; font-size: 1.5em; font-weight: 600; margin-bottom:1.5em;">Bình luận</h4>
						<div class="response">
						<%
						if(request.getAttribute("alComment") != null){
							ArrayList<Comment> alComment = (ArrayList<Comment>) request.getAttribute("alComment");
							for(Comment objComment: alComment){
								 if(objComment.getId_news() == objNews.getIdNews()){ 
						%>
							<div class="media response-info list_cmt">
								<div class="media-left response-text-left">
									<a href=""> 
										<img class="media-object" src="<%=request.getContextPath()%>/templates/public/images/noavatar.png" alt="" />
									</a>
									<h5>
										<a href=""><%=objComment.getUsername() %></a>
									</h5>
								</div>
								
								<div class="media-body response-text-right" id="rep_<%=objComment.getId_cmt() %>">
									<p><%=objComment.getContent() %></p>
									<ul>
										<li><%=LibraryString.getDateTime(objComment.getDate_cmt()) %></li>
										<li id="status_<%=objComment.getId_cmt()%>"><a href="javascript:void(0)" onclick="reply_show(<%=objComment.getId_cmt() %>)">Trả lời</a></li>
									</ul>
									<%
									if(request.getAttribute("alReply") != null){
										ArrayList<Comment> alReply = (ArrayList<Comment>) request.getAttribute("alReply");
										for(Comment objReply: alReply){
											 if(objReply.getId_parent() == objComment.getId_cmt()){ 
									%>
										<div class="media response-info">
												<div class="media-left response-text-left">
													<a href="">
														<img class="media-object" src="<%=request.getContextPath()%>/templates/public/images/noavatar.png" alt="" />
													</a>
													<h5>
														<a href="#"><%=objReply.getUsername() %></a>
													</h5>
												</div>
												<div class="media-body response-text-right">
													<p><%=objReply.getContent() %></p>
													<ul>
														<li><%=LibraryString.getDateTime(objReply.getDate_cmt()) %></li>
													</ul>
												</div>
												<div class="clearfix"></div>
										</div>
									<%} }} %>
									<div class="coment-form" id="reply_form_<%=objComment.getId_cmt() %>" style="display: none">
										<h4>Trả lời</h4>
										<form id="form_TL" action="javascript:void(0)">
											<input type="text" name="rep_name" id="rep_name_<%=objComment.getId_cmt() %>"  value="Name " onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Name';}"  /> 
											<input type="email" name="rep_email" id="rep_email_<%=objComment.getId_cmt() %>"  value="Email (will not be published)*" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Email (will not be published)*';}" /> 
											<input type="text" name="rep_website" id="rep_website_<%=objComment.getId_cmt() %>"  value="Website" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Website';}" />
											<textarea id="rep_content_<%=objComment.getId_cmt() %>"  onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Your Comment...';}" >Your Comment...</textarea>
											<input type="submit" value="Submit Comment">
										</form>
									</div>
									<script type="text/javascript">
									$(document).ready(function (){
										$('#form_TL').validate({
											rules:{
												rep_name:{
													required: true,
													minlength: 4,
													maxlength: 8,
												},
												rep_email:{
													required: true,
													email: true,
												},
												rep_website:{
													required: true,
													url: true,
												},
											},
											messages:{
												rep_name:{
													required: "<p class='vali-error'>Vui lòng nhập tên</p>",
													minlength: "<p class='vali-error'>Vui lòng nhập ít nhất 4 ký tự</p>",
													maxlength: "<p class='vali-error'>Vui lòng nhập nhiều nhất 8 ký tự</p>",
												},
												rep_email:{
													required: "<p class='vali-error'>Vui lòng nhập email</p>",
													email: "<p class='vali-error'>Vui lòng nhập email hợp lệ</p>",
												},
												rep_website:{
													required: "<p class='vali-error'>Vui lòng nhập mô tả</p>",
													url: "<p class='vali-error'>Vui lòng nhập url website hợp lệ</p>",
												},
											},
											submitHandler: function(form) {
												reply(<%=objComment.getId_cmt() %>);
											} ,
										});
									});
									</script>
								</div>
								<div class="clearfix"></div>
							</div>
							
							<%} }}%>
						</div>
						<div class="coment-form">
							<h4>Leave your comment</h4>
							<form id="form_BL" action="javascript:void(0)">
								<input type="text" name="cmt_name" class="cmt_name" value="Name " onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Name';}"  /> 
								<input type="email" name="cmt_email" class="cmt_email" value="Email (will not be published)*" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Email (will not be published)*';}" /> 
								<input type="text" name="cmt_website" class="cmt_website" value="Website" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Website';}" />
								<textarea class="cmt_content" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Your Comment...';}" >Your Comment...</textarea>
								<input type="submit" value="Submit Comment">
							</form>
						</div>
						<div class="clearfix"></div>
					</div>
				</div>

			</div>
			<%@include file="/templates/public/inc/right_bar.jsp" %>
			<div class="clearfix"></div>
		</div>
	</div>
	<script type="text/javascript">
	$(document).ready(function (){
		$('#form_BL').validate({
			rules:{
				cmt_name:{
					required: true,
					minlength: 4,
					maxlength: 8,
				},
				cmt_email:{
					required: true,
					email: true,
				},
				cmt_website:{
					required: true,
					url: true,
				},
			},
			messages:{
				cmt_name:{
					required: "<p class='vali-error'>Vui lòng nhập tên</p>",
					minlength: "<p class='vali-error'>Vui lòng nhập ít nhất 4 ký tự</p>",
					maxlength: "<p class='vali-error'>Vui lòng nhập nhiều nhất 8 ký tự</p>",
				},
				cmt_email:{
					required: "<p class='vali-error'>Vui lòng nhập email</p>",
					email: "<p class='vali-error'>Vui lòng nhập email hợp lệ</p>",
				},
				cmt_website:{
					required: "<p class='vali-error'>Vui lòng nhập mô tả</p>",
					url: "<p class='vali-error'>Vui lòng nhập url website hợp lệ</p>",
				},
			},
			submitHandler: function(form) {
				comment();
			} ,
		});
	});

	function comment(){
   		var name = $(".cmt_name").val();
		var email = $(".cmt_email").val();
		var website = $(".cmt_website").val();
		var content = $(".cmt_content").val();
		var idNews = <%=objNews.getIdNews()%>;
		$.ajax({
			url: '<%=request.getContextPath()%>/comment',
			type: 'POST',
			cache: false,
			data: {
				Name: name,
				Email: email,
				Website: website,
				Content: content,
				Idnews: idNews,
				Idparent: 0
			},
			success: function(data){
				if($(".response .list_cmt").length == 0){
					$(".response").html(data);
				}else{
					$(".response .list_cmt:eq(0)").before(data);
				}
				
				$(".cmt_name").val("");
				$(".cmt_email").val("");
				$(".cmt_website").val("");
				$(".cmt_content").val("");
			},
			error: function (){
				
			}
		});   
	}
	function reply(idComment){
		var name = $("#rep_name_"+idComment).val();
		var email = $("#rep_email_"+idComment).val();
		var website = $("#rep_website_"+idComment).val();
		var content = $("#rep_content_"+idComment).val();
		var idNews = <%=objNews.getIdNews()%>;
		$.ajax({
			url: '<%=request.getContextPath()%>/reply',
			type: 'POST',
			cache: false,
			data: {
				Name: name,
				Email: email,
				Website: website,
				Content: content,
				Idnews: idNews,
				Idparent: idComment
			},
			success: function(data){
				$("#rep_"+idComment+" #reply_form_"+idComment).before(data);
				$("#rep_name_"+idComment).val("");
				$("#rep_email_"+idComment).val("");
				$("#rep_website_"+idComment).val("");
				$("#rep_content_"+idComment).val("");
			},
			error: function (){
				
			}
		});
	}
	function reply_show(idComment){
		$("#reply_form_"+idComment).show();
		$.ajax({
			url: '<%=request.getContextPath()%>/show-hide',
			type: 'POST',
			cache: false,
			data: {
				status: 0,
				idcmt: idComment,
			},
			success: function(data){
				$("#rep_"+idComment+" ul #status_"+idComment).html(data);
			},
			error: function (){
				
			}
		});
	}
	function reply_hide(idComment){
		$("#reply_form_"+idComment).hide();
		$.ajax({
			url: '<%=request.getContextPath()%>/show-hide',
			type: 'POST',
			cache: false,
			data: {
				status: 1,
				idcmt: idComment,
			},
			success: function(data){
				$("#rep_"+idComment+" ul #status_"+idComment).html(data);
			},
			error: function (){
				
			}
		});
	}
	</script>
</div>
<!--

//-->
</script>
<!-- content-section-ends-here -->
<!-- footer-section-starts-here -->
<%@include file="/templates/public/inc/footer.jsp" %>