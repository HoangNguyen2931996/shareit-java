<%@page import="model.bean.Comment"%>
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
                        <h4 class="title">Kết quả tìm kiếm</h4>
                    </div>
                    <div class="content table-responsive table-full-width">
                        <table class="table table-striped">
                            <thead>
                            	<tr>
	    				            <th>Tên bài viết</th>
	                                <th>Tên</th>
	                                <th>Email</th>
	                                <th>Website</th>
	                                <th>Bình luận</th>
	                                <th>Trạng thái</th>
	                                <th>Chức năng</th>
                            	</tr>
                            </thead>
                            <tbody>
                            <%
                            if(request.getAttribute("alCmt") != null){
                            	ArrayList<Comment> alCmt = (ArrayList<Comment>) request.getAttribute("alCmt");
                            	if(alCmt.size()>0){
                            		for(Comment objCmt: alCmt){
                            %>
                                <tr>
                                    <td><%=objCmt.getName_news() %></td>
                                    <td><%=objCmt.getUsername() %></td>
                                    
                                    <td><%=objCmt.getEmail() %></td>
                                    <td><%=objCmt.getWebsite() %></td>
                                    <td><%=objCmt.getContent() %></td>
                                    <td class="status_<%=objCmt.getId_cmt() %>">
                                    <%
                                    	if(objCmt.getStatus_cmt() == 1){
                                    %>
                                    	<img  class="turnOn" style="padding-left: 40px;" alt="" src="<%=request.getContextPath() %>/templates/admin/images/notification-tick.gif" onclick="turnOn(<%=objCmt.getId_cmt()%>)">
                                   <%} else{ %>
                                   		<img  class="turnOff" style="padding-left: 40px;" alt="" src="<%=request.getContextPath() %>/templates/admin/images/minus-circle.gif" onclick="turnOff(<%=objCmt.getId_cmt()%>)"> 
                                   <%} %>
                                    </td>
                                    <td>
                                        <a href="<%=request.getContextPath() %>/admin/comment/del?idCmt=<%=objCmt.getId_cmt()%>"><img src="<%=request.getContextPath() %>/templates/admin/images/del.gif" alt="" /> Xóa</a>
                                    </td>
                                </tr>
 							<%} }else{
                            	%>
								<p style="color: red; padding-left: 15px; padding-top: 20px; font-size: 20px;">Không tìm thấy kết quả. <a href="<%=request.getContextPath()%>/admin/comment">click here</a> để quay trở lại</p>
								<%
                            }}%>
                            </tbody>
                        </table>
                        <div class="text-center">
                            <div class="paginator">
							</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript">
	         	function turnOn(idCmt){
	 	 			$.ajax({
						url: '<%=request.getContextPath()%>/admin/comment',
						type: 'POST',
						cache: false,
					data: {
						idcmt: idCmt,
						status: 0, 
					},
					success: function(data){
						$('.status_'+idCmt).html(data);
					},
					error: function (){
					}
			});
	         	}
	         	function turnOff(idCmt){
					$.ajax({
						url: '<%=request.getContextPath()%>/admin/comment',
						type: 'POST',
						cache: false,
					data: {
						idcmt: idCmt,
						status: 1, 
					},
					success: function(data){
						$('.status_'+idCmt).html(data);
					},
					error: function (){
					}
					});
			}
	         </script>
</div>
<%@include file="/templates/admin/inc/footer.jsp" %>  