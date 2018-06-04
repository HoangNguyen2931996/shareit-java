<%@page import="model.dao.CatDao"%>
<%@page import="model.bean.Cat"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/admin/inc/header.jsp" %>
<script type="text/javascript">
	$(document).ready(function (){
		$('#formadd').validate({
			rules:{
				name:{
					required: true,
					minlength: 17,
					maxlength: 100,
				},
				preview:{
					required: true,
					minlength: 17,
				},
			},
			messages:{
				name:{
					required: "<p class='vali-error'>Vui lòng nhập tên tin</p>",
					minlength: "<p class='vali-error'>Vui lòng nhập ít nhất 17 ký tự</p>",
					maxlength: "<p class='vali-error'>Vui lòng nhập nhiều nhất 100 ký tự</p>"
				},
				preview:{
					required: "<p class='vali-error'>Vui lòng nhập mô tả</p>",
					minlength: "<p class='vali-error'>Vui lòng nhập ít nhất 17 ký tự</p>",
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
                        <h4 class="title">Thêm bài viết</h4>
                    </div>
                    <div class="content">
                        <form id="formadd" action="<%=request.getContextPath() %>/admin/news/add" enctype="multipart/form-data" method="post">
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Tên bài viết</label>
                                        <input type="text" name="name" class="form-control border-input" placeholder="Tên bài viết" value="">
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                    	<label>Slide</label>
                                    	<p><input type="checkbox" name="slide" value="" />Chọn</p>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <label>Danh mục</label>

                                        <select name="list_cat" class="form-control border-input">
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
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Hình ảnh</label>
                                        <input type="file" name="picture" class="form-control" placeholder="Chọn ảnh" />
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <label>Mô tả</label>
                                        <textarea rows="4" class="form-control border-input" name="preview" placeholder="Mô tả tóm tắt bài viết"></textarea>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <label>Chi tiết</label>
                                        <textarea id="detail" rows="6" class="form-control border-input" name="detail" placeholder="Mô tả chi tiết bài viết"></textarea>
                                    </div>
                                </div>
                            </div>
                            <script type="text/javascript">
                            	var editor = CKEDITOR.replace('detail');
                            	CKFinder.setupCKEditor(editor,'<%=request.getContextPath() %>/ckfinder/')
                            </script>
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