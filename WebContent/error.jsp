<%@page import="library.LibraryString"%>
<%@page import="model.bean.Adv"%>
<%@page import="model.bean.News"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/templates/public/inc/header.jsp"%>
<!-- content-section-starts-here -->
<div class="main-body">
	<div class="wrap">
		<div class="col-md-8 content-left" style="padding-left: 60px; padding-top: 50px">
			<h1 class="title" style="font-size: 40px; padding-left: 70px; color: red">Trang này không khả dụng</h1>
			<p style="font-weight: bold; font-size: 20px; padding-top: 50px">
				Liên kết bạn truy cập có thể bị hỏng hoặc trang có thể đã bị xóa.
			</p>
			<p style="font-size: 20px; padding-left: 200px; margin-top: 20px;">
				<a style="font-weight: bold; text-decoration: none" href="<%=request.getContextPath() %>" title="">Quay về trang chủ</a>
			</p>
		</div>
		<%@include file="/templates/public/inc/right_bar.jsp"%>
		<div class="clearfix"></div>
	</div>
</div>
<!-- content-section-ends-here -->
<!-- footer-section-starts-here -->
<%@include file="/templates/public/inc/footer.jsp"%>