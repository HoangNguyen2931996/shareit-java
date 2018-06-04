<%@page import="model.bean.Cat"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/admin/inc/header.jsp" %>
<script type="text/javascript">
	$(document).ready(function (){
		$('#formadd').validate({
			rules:{
				name_cat:{
					required: true,
					minlength: 5,
					maxlength: 15,
				},
			},
			messages:{
				name_cat:{
					required: "<p class='vali-error'>Vui lòng nhập tên tin</p>",
					minlength: "<p class='vali-error'>Vui lòng nhập ít nhất 5 ký tự</p>",
					maxlength: "<p class='vali-error'>Vui lòng nhập nhiều nhất 15 ký tự</p>"
				},
			}
		});
	});
</script>
<div class="content">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-12 col-md-12">
                <div class="card">
                    <div class="header">
                        <h4 class="title">Thêm danh mục</h4>
                    </div>
                    <div class="content">
                        <form id="formadd" action="<%=request.getContextPath() %>/admin/cat/add" method="post">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <label>Tên danh mục</label>
                                        <input type="text" name="name_cat" class="form-control border-input" placeholder="Tên danh mục" value="">
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <label>Danh mục cha</label>
                                        <select name="cat_parent" class="form-control border-input">
                                        <%!
                                        public static String menu(HttpServletRequest request, ArrayList<Cat> alCat, int idParent, String distance, String str) {
                                        	ArrayList<Cat> alLoop = new ArrayList();
                                        	ArrayList<Cat> alContinuous = new ArrayList();
                                        	for (Cat objCat : alCat){
                                        		if (objCat.getIdParent() == idParent){
                                        			alLoop.add(objCat);
                                        		}
                                        	}
                                        	for(Cat objLoop: alLoop){
                                        		str += "<option value='"+ objLoop.getIdCat() +"'>"+ distance + objLoop.getNameCat() +"</option>";
                                        	}
                                    		return str;
                                    	}
                                        %>
                                        	<option value="0">--Không có--</option>
                                        	<%
                                        		String str = new String();
                                        		if(request.getAttribute("alCat") != null){
                                        			ArrayList<Cat> alCat = (ArrayList<Cat>)request.getAttribute("alCat");
                                        					if(alCat.size()>0){
                                        						out.print(menu(request, alCat, 0, "", str));
                                        					}
                                        		}
                                        	%>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="text-center">
                                <input type="submit" class="btn btn-info btn-fill btn-wd" value="Thêm" />
                            </div>
                            <div class="clearfix"></div>
                        </form>
                    </div>
                </div>
            </div>


        </div>
    </div>
</div>
<%@include file="/templates/admin/inc/footer.jsp" %>  
