<%@page import="model.bean.Adv"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/templates/admin/inc/header.jsp"%>
<div class="content">
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<div class="card">
					<div class="header">
						<h4 class="title">Danh sách quảng cáo</h4>
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
						<a href="<%=request.getContextPath() %>/admin/adv/add" class="addtop">
							<img src="<%=request.getContextPath() %>/templates/admin/images/add.png" alt="" /> 
							Thêm
						</a>
					</div>
					<div class="content table-responsive table-full-width">
						<table class="table table-striped">
							<thead>
								<th>ID</th>
								<th>Tên quảng cáo</th>
								<th>Hình ảnh</th>
								<th>link</th>
								<th>Chức năng</th>
							</thead>
							<%
								if(request.getAttribute("alAdv") != null){
									ArrayList<Adv> alAdv = (ArrayList<Adv>) request.getAttribute("alAdv");
									for(Adv objAdv: alAdv){
										int idAdv = objAdv.getIdAdv();
										String nameAdv = objAdv.getNameAdv();
										String picture = objAdv.getPicture();
										String link = objAdv.getLink();
										String urlEdit = request.getContextPath() + "/admin/adv/edit?aid="+ idAdv;
										String urlDel = request.getContextPath() + "/admin/adv/del?aid="+ idAdv;
							%>
							<tbody>
								<tr>
									<td><%=idAdv %></td>
									<td><a href="<%=urlEdit%>"><%=nameAdv %></a></td>
									
									<td>
									<%
										if(!"".equals(picture)){
									%>
										<img src="<%=request.getContextPath() %>/files/<%=picture %>" alt="" width="100px" />
									<%} else{ %>
										<img style="width: 100px;" src="<%=request.getContextPath() %>/templates/admin/images/no_image.png" alt="" />
									<%} %>
									</td>
									<td><a href="<%=urlEdit%>"><%=link %></a></td>
									<td>
										<a href="<%=urlEdit %>"><img src="<%=request.getContextPath() %>/templates/admin/images/edit.gif" alt="" /> Sửa</a> &nbsp;||&nbsp; 
										<a href="<%=urlDel %>"><img src="<%=request.getContextPath() %>/templates/admin/images/del.gif" alt="" /> Xóa</a>
									</td>
								</tr>
							</tbody>
							<%} }%>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<%@include file="/templates/admin/inc/footer.jsp" %>  