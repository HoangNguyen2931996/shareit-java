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
                        <h4 class="title">Danh sách bình luận</h4>
                         <%
                        	if(request.getParameter("msg") != null){
                        		int msg = Integer.parseInt(request.getParameter("msg"));
                        		switch(msg){
                        			case 0:
                        				%>
                        					<p style="color: red; font-weight: bold">Xảy ra lỗi trong quá trình xử lý</p>	
                        				<%
                        				break;
                        			case 1:
                        				%>
                        					<p style="color: green; font-weight: bold">Thêm thành công</p>	
                        				<%
                        				break;
                        			case 2:
                        				%>
                        					<p style="color: green; font-weight: bold">Sửa thành công</p>	
                        				<%
                        				break;
                        			case 3:
                        				%>
                        					<p style="color: green; font-weight: bold">Xóa thành công</p>	
                        				<%
                        				break;
                        		}
                        	}
                        %>
                        <form action="<%=request.getContextPath() %>/admin/search/comment" method="post">
	                          <div class="row">
	                              <div class="col-md-3">
	                                  <div class="form-group">
	                                      <input type="text" name="name_news" class="form-control border-input" placeholder="Tên bài viết" value="">
	                                  </div>
	                              </div>
	                              <div class="col-md-3">
	                                  <div class="form-group">
	                                      <input type="text" name="username" class="form-control border-input" placeholder="Username bình luận" value="">
	                                  </div>
	                              </div>
	                              <div class="col-md-3">
	                                  <div class="form-group">
	                                      <input type="text" name="content" class="form-control border-input" placeholder="Nội dung bình luận" value="">
	                                  </div>
	                              </div>
	                              <div class="col-md-3">
	                                  <div class="form-group">
	                                      <input type="submit" name="search" value="Tìm kiếm" class="is" />
	                                      <input type="reset" name="reset" value="Hủy tìm kiếm" class="is" />
	                                  </div>
	                              </div>
	                          </div>
                        </form>
                    </div>
                    <div class="content table-responsive table-full-width">
                    <form action="<%=request.getContextPath()%>/admin/comment/del" method="post">
                        <table class="table table-striped">
                            <thead>
                            	<tr>
	    				            <th>Tên bài viết</th>
	                                <th style="width:12%; text-align: center;">Tên</th>
	                                <th style="width:12%; text-align: center;">Email</th>
	                                <th style="width:12%; text-align: center;">Website</th>
	                                <th style="width:12%; text-align: center;">Bình luận</th>
	                                <th style="width:12%; text-align: center;">Trạng thái</th>
	                                <th style="width:12%; text-align: center;">Chức năng</th>
	                                <th>
	                                	Chọn
	                                	<input type="submit" value="Xóa" />
	                                </th>
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
                                    <td>
                                    	<input type="checkbox" name="id_cmt" value=<%=objCmt.getId_cmt() %> />
                                    </td>
                                </tr>
 							<%} } }%>
                            </tbody>
                        </table>
                        </form>
                        <div class="text-center">
                            <div class="paginator">
			
								<div class="paginator">
								<%
									int sumPage = (Integer) request.getAttribute("sumPage");
									int currentPage = (Integer) request.getAttribute("currentPage");
									int pageNum = 3;
									int numLink = (int) Math.floor((float)pageNum/2);
									int pageStart = 0;
									int pageEnd = 0;
									if(currentPage>1 && sumPage > 0){
										%><a href="<%=request.getContextPath() %>/admin/comment?page=<%=currentPage-1%>">previous</a> <%
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
									<a <%=active %> href="<%=request.getContextPath() %>/admin/comment?page=<%=i %> "><%=i %></a>
								<%} %>
								<%
									if(currentPage < sumPage && sumPage > 1){
										%><a href="<%=request.getContextPath() %>/admin/comment?page=<%=currentPage+1%>">Next</a> <%
									}
								%>
								</div> 
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