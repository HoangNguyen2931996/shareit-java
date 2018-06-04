<%@page import="model.bean.Cat"%>
<%@page import="model.dao.CatDao"%>
<%@page import="library.LibraryString"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="model.bean.News"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/admin/inc/header.jsp" %>
<div class="content">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-12">
                <div class="card">
                    <div class="header">
                        <h4 class="title">Kết quả tìm kiêm</h4>
                    <div class="content table-responsive table-full-width">
                        <table class="table table-striped">
                            <thead>
                            	<tr>
	                            	<th>ID</th>
	                                <th>Tên bài viết</th>
	                                <th>Hình ảnh</th>
	                                <th>Ngày tạo</th>
	                                <th>Danh mục</th>
	                                <th>Trạng thái</th>
	                                <th>Chức năng</th>
                            	</tr>
                            </thead>
                            <tbody>
                            <%
                            	if(request.getAttribute("alNews") != null){
                            		ArrayList<News> alNews = (ArrayList<News>) request.getAttribute("alNews");
                            		if(alNews.size()>0){
                            			for(News objNews: alNews){
                            				int idNews = objNews.getIdNews();
                            				String nameNews = objNews.getNameNews();
                            				String picture = objNews.getPicture();
                            				String nameCat = objNews.getNameCat();
                            				Timestamp dateCreate = objNews.getDateCreate();
                            				int status = objNews.getStatus();
                            				String urlEdit = request.getContextPath() + "/admin/news/edit?nid=" + idNews;
                            				String urlDel = request.getContextPath() + "/admin/news/del?nid=" + idNews;
                            %>
                                <tr>
                                    <td><%=idNews %></td>
                                    <td><a href="<%=urlEdit %>"><%=nameNews %></a></td>
                                    <td>
                                    <%
                                    	if(!"".equals(picture)){
                                    		String urlPicture = request.getContextPath() + "/files/" + picture;
                                    %>
                                    	<img src="<%=urlPicture %>" alt="" width="100px" />
                                    	<%} else{
                                    	%>
                                    		<img style="width: 100px;" src="<%=request.getContextPath() %>/templates/admin/images/no_image.png" alt="" />
                                    	<%	
                                    	}%>
                                    </td>
                                    <td><%=LibraryString.getDateTime(dateCreate) %></td>
                                    <td><%=nameCat %></td>
                                    <td class="<%=idNews %>">
                                    <%
                                    	if(status == 1){
                                    %>
                                    	<img id="<%=idNews %>" class="turnOn" style="padding-left: 40px;" alt="" src="<%=request.getContextPath() %>/templates/admin/images/notification-tick.gif" onclick="turnOn(this.id)">
                                   <%} else{ %>
                                   		<img id="<%=idNews %>" class="turnOff" style="padding-left: 40px;" alt="" src="<%=request.getContextPath() %>/templates/admin/images/minus-circle.gif" onclick="turnOff(this.id)"> 
                                   <%} %>
                                    </td>
                                    <td>
                                        <a href="<%=urlEdit %>"><img src="<%=request.getContextPath() %>/templates/admin/images/edit.gif" alt="" /> Sửa</a> &nbsp;||&nbsp;
                                        <a href="<%=urlDel %>"><img src="<%=request.getContextPath() %>/templates/admin/images/del.gif" alt="" /> Xóa</a>
                                    </td>
                                </tr>
                                <%} }else{ 
                                	%>
                					<p style="color: red; padding-left: 15px; padding-top: 20px; font-size: 20px;">Không tìm thấy kết quả. <a href="<%=request.getContextPath()%>/admin/news">click here</a> để quay trở lại</p>
                					<%
                                }}%>
                            </tbody>
                        </table>
                        <script type="text/javascript">
                        	function turnOn(idNews){
                	 			$.ajax({
            						url: '<%=request.getContextPath()%>/admin/news',
            						type: 'POST',
            						cache: false,
            						data: {
            							idnews: idNews,
            							status: 0, 
            						},
            						success: function(data){
            							$('.'+idNews).html(data);
            						},
            						error: function (){
            						}
            					});
                        	}
                        	function turnOff(idNews){
            		 			$.ajax({
            						url: '<%=request.getContextPath()%>/admin/news',
            						type: 'POST',
            						cache: false,
            						data: {
            							idnews: idNews,
            							status: 1, 
            						},
            						success: function(data){
            							$('.'+idNews).html(data);
            						},
            						error: function (){
            						}
            					});
            		 		}
                        </script>
                        <div class="text-center">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="/templates/admin/inc/footer.jsp" %>  