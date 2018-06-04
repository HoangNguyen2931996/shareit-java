<%@page import="model.bean.User"%>
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
                        <h4 class="title">Danh sách người dùng</h4>
                        <%
                        User SobjUser = (User) session.getAttribute("sobjUser");
                        if(!"Trial Mod".equals(SobjUser.getRank())){
                        %>
                        <a href="<%=request.getContextPath() %>/admin/user/add" class="addtop">
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
                        			case 5:
                        				%>
                    						<p style="color: red; font-weight: bold">Bạn không có quyền sửa hoặc xóa</p>	
                    					<%
            							break;
                        		}
                        	}
                        %>
                    </div>
                    <div class="content table-responsive table-full-width">
                        <table class="table table-striped">
                            <thead>
                            	<tr>
	                            	<th>ID</th>
	                                <th>Username</th>
	                                <th>Fullname</th>
	                                <th>Email</th>
	                                <th>Cấp bậc</th>
	                                <th>Chức năng</th>
                            	</tr>
                            </thead>
                            <%
                            User sobjUser = (User) session.getAttribute("sobjUser");
                           	if(request.getAttribute("alUser") != null){
                           		ArrayList<User> alUser = (ArrayList<User>) request.getAttribute("alUser");
                           		if(alUser.size() > 0){
                           			for(User objUser: alUser){
                            %>
                            <tbody>
                                <tr>
                                    <td><%=objUser.getId() %></td>
                                    <td><a href="<%=request.getContextPath() %>/admin/user/edit?uid=<%=objUser.getId() %>"><%=objUser.getUsername() %></a></td>
                                	<td><%=objUser.getFullname() %></td>
                                    <td><%=objUser.getEmail() %></td>
                                    <td><%=objUser.getRank() %></td>
                                    <td>
                                    <%
                                    if("Admin".equals(sobjUser.getRank())){
                                    	if(!"admin".equals(sobjUser.getUsername())){
                                    		if("Trial Mod".equals(objUser.getRank()) || "Mod".equals(objUser.getRank())){
                                    %>
                                        <a href="<%=request.getContextPath() %>/admin/user/edit-<%=objUser.getId() %>"><img src="<%=request.getContextPath() %>/templates/admin/images/edit.gif" alt="" /> Sửa</a> &nbsp;||&nbsp;
                                        <a href="<%=request.getContextPath() %>/admin/user/del?uid=<%=objUser.getId() %>"><img src="<%=request.getContextPath() %>/templates/admin/images/del.gif" alt="" /> Xóa</a>
                                    <%} else if(sobjUser.getId() == objUser.getId()){
                                    %>
                                    	<a href="<%=request.getContextPath() %>/admin/user/edit-<%=objUser.getId() %>"><img src="<%=request.getContextPath() %>/templates/admin/images/edit.gif" alt="" /> Sửa</a> &nbsp;&nbsp;
                                    <%  
                                    }}} else if("Mod".equals(sobjUser.getRank())){
                                    		if("Trial Mod".equals(objUser.getRank())){
                                    %>
	                                    	<a href="<%=request.getContextPath() %>/admin/user/edit?uid=<%=objUser.getId() %>"><img src="<%=request.getContextPath() %>/templates/admin/images/edit.gif" alt="" /> Sửa</a> &nbsp;||&nbsp;
	                                        <a href="<%=request.getContextPath() %>/admin/user/del?uid=<%=objUser.getId() %>"><img src="<%=request.getContextPath() %>/templates/admin/images/del.gif" alt="" /> Xóa</a>
                                        <%} else if(sobjUser.getId() == objUser.getId()){ %>
                                        	<a href="<%=request.getContextPath() %>/admin/user/edit?uid=<%=objUser.getId() %>"><img src="<%=request.getContextPath() %>/templates/admin/images/edit.gif" alt="" /> Sửa</a> &nbsp;&nbsp;
                                        <%} %>
                                    <%} else if("Trial Mod".equals(sobjUser.getRank()) && sobjUser.getId() == objUser.getId()){%>
                                    	<a href="<%=request.getContextPath() %>/admin/user/edit?uid=<%=objUser.getId() %>"><img src="<%=request.getContextPath() %>/templates/admin/images/edit.gif" alt="" /> Sửa</a> &nbsp;&nbsp;
                                    <%}%>
                                    <%if("admin".equals(sobjUser.getUsername())){%>
                                    	<a href="<%=request.getContextPath() %>/admin/user/edit-<%=objUser.getId() %>"><img src="<%=request.getContextPath() %>/templates/admin/images/edit.gif" alt="" /> Sửa</a> &nbsp;||&nbsp;
                                        <a href="<%=request.getContextPath() %>/admin/user/del?uid=<%=objUser.getId() %>"><img src="<%=request.getContextPath() %>/templates/admin/images/del.gif" alt="" /> Xóa</a>
                                    <%} %>
                                    </td>
                                </tr>
                            </tbody>
                            <%} } }%>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="/templates/admin/inc/footer.jsp" %>  