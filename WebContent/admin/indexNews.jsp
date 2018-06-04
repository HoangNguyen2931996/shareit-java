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
                        <h4 class="title">Danh sách bài viết</h4>
                        <%
                        User SobjUser = (User) session.getAttribute("sobjUser");
                        if(!"Trial Mod".equals(SobjUser.getRank())){
                        %>
                        <a href="<%=request.getContextPath() %>/admin/news/add" class="addtop">
                        	<img src="<%=request.getContextPath() %>/templates/admin/images/add.png" alt="" />
                         	Thêm
                         </a>
                         <%} %>
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
                        <form action="<%=request.getContextPath() %>/admin/search/news" method="post">
                            <div class="row">
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <input type="text" name="name_news" class="form-control border-input" placeholder="Tên bài viết" value="">
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <select name="cat_list" class="form-control border-input">
                                            <option value="0">-- Chọn danh mục --</option>
                                            <%
                                        	String disabled = "";
                                        	String selected = "";
                                        	CatDao catDao = new CatDao();
                                       		String str = new String();
                                       		if(request.getAttribute("alCat") != null){
                                       			ArrayList<Cat> alCat = (ArrayList<Cat>)request.getAttribute("alCat");
                                     				if(alCat.size()>0){
                                     					for(Cat objCat: alCat){
                                     						if(objCat.getIdParent()==0){
                                     							if(catDao.checkChild(objCat.getIdCat()).size()>0){
                                     								disabled = "disabled";
                                     							}else{
                                     								disabled = "";
                                     							}
                                     							
                                     						%>
                                     							<option <%=disabled %>  value="<%=objCat.getIdCat()%>"><%=objCat.getNameCat() %></option>
                                     							<%
                                     							if(catDao.checkChild(objCat.getIdCat()).size()>0){
                                     								for(Cat itemCat: alCat){
                                     									
                                     									if(itemCat.getIdParent()==objCat.getIdCat()){
                                     										
                                     										%>
                                     										<option value="<%=itemCat.getIdCat()%>">|--------<%=itemCat.getNameCat() %></option>
                                     										<%
                                     									}
                                     								}
                                     							}
                                     							%>
                                     						<%
                                     						}
                                     					}
                                     				}
                                       		}
                                        	%>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <input type="submit" name="search" value="Tìm kiếm" class="is" />
                                        <input type="reset" name="reset" value="Hủy tìm kiếm" class="is" />
                                    </div>
                                </div>
                            </div>
                         </form>
                    </div>
                    <div class="content table-responsive table-full-width">
                    <form action="<%=request.getContextPath()%>/admin/news/del" method="post">
                        <table class="table table-striped">
                            <thead>
                            	<tr>
	                            	<th style="width:4%; text-align: center;">ID</th>
	                                <th>Tên bài viết</th>
	                                <th style="width:13%; text-align: center;">Hình ảnh</th>
	                                <th style="width:13%; text-align: center;">Ngày tạo</th>
	                                <th style="width:13%; text-align: center;">Danh mục</th>
	                                <th style="width:13%; text-align: center;">Trạng thái</th>
	                                <th style="width:13%; text-align: center;">Chức năng</th>
	                                <th>
	                                	Chọn
	                                	
	                                		<input type="submit" value="Xóa" />
	                                	
	                                </th>
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
                            				String urlEdit = request.getContextPath() + "/admin/news/edit-" + idNews;
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
                                    <td>
                                    	<input type="checkbox" name="id_news" value="<%=idNews %>" />
                                    </td>
                                </tr>
                                <%} } }%>
                            </tbody>
                        </table>
                        </form>
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
                            <div class="paginator">
							<%
								int sumPage = (Integer) request.getAttribute("sumPage");
								int currentPage = (Integer) request.getAttribute("currentPage");
								int pageNum = 3;
								int numLink = (int) Math.floor((float)pageNum/2);
								int pageStart = 0;
								int pageEnd = 0;
								if(currentPage>1 && sumPage > 0){
									%><a href="<%=request.getContextPath() %>/admin/news?page=<%=currentPage-1%>">previous</a> <%
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
								<a <%=active %> href="<%=request.getContextPath() %>/admin/news?page=<%=i %> "><%=i %></a>
							<%} %>
							<%
								if(currentPage < sumPage && sumPage > 1){
									%><a href="<%=request.getContextPath() %>/admin/news?page=<%=currentPage+1%>">Next</a> <%
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
<%@include file="/templates/admin/inc/footer.jsp" %>  