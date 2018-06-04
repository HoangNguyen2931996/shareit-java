<%@page import="model.bean.Cat"%>
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
                        <h4 class="title">DANH MỤC</h4>
                        <%
                        User SobjUser = (User) session.getAttribute("sobjUser");
                        if(!"Trial Mod".equals(SobjUser.getRank())){
                        %>
                        <a href="<%=request.getContextPath() %>/admin/cat/add" class="addtop">
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
                    </div>
                    <div class="content table-responsive table-full-width">
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th style="width: 70%">Danh mục</th>
                                    <th>Chức năng</th>
                                </tr>
                            </thead>
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
                            		str += "<tr>" + "<td style='padding-left:" + distance + "px'><a href='" + request.getContextPath() + "/admin/cat/edit?cid=" + objLoop.getIdCat()+ "'>" + objLoop.getNameCat() + "</a></td>" + "<td>"
                    						+ "<a href='" + request.getContextPath() + "/admin/cat/edit-" + objLoop.getIdCat()
                    						+ "'><img src='" + request.getContextPath()
                    						+ "/templates/admin/images/edit.gif' alt='' /> Sửa</a> &nbsp;||&nbsp;" + "<a href='"
                    						+ request.getContextPath() + "/admin/cat/del?cid=" + objLoop.getIdCat() + "'><img src='"
                    						+ request.getContextPath() + "/templates/admin/images/del.gif' alt='' /> Xóa</a>" + "</td>"
                    						+ "</tr>";
                            		str = menu(request, alContinuous, objLoop.getIdCat(), distance + 60, str);
                            	
                            	}
                        		return str;
                        	}
                            %>
                            <tbody>
							<%
								String str = new String();
								if(request.getAttribute("alCat") != null){
	                        		ArrayList<Cat> alCat = (ArrayList<Cat>) request.getAttribute("alCat");
	                        		if(alCat.size()>0){
	                        			str = menu(request, alCat, 0, 20, str);
	                        			out.print(str);
	                        		}
	                        	}
							%>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="/templates/admin/inc/footer.jsp" %>  