<%@page import="model.bean.Adv"%>
<%@page import="model.dao.CatDao"%>
<%@page import="model.bean.Cat"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/admin/inc/header.jsp" %>
<script type="text/javascript">
	$(document).ready(function (){
		$('#formedit').validate({
			rules:{
				name:{
					required: true,
					minlength: 5,
					maxlength: 30,
				},
				link:{
					required: true,
					url: true,
				},
			},
			messages:{
				name:{
					required: "<p class='vali-error'>Vui lòng nhập tên tin</p>",
					minlength: "<p class='vali-error'>Vui lòng nhập ít nhất 5 ký tự</p>",
					maxlength: "<p class='vali-error'>Vui lòng nhập nhiều nhất 30 ký tự</p>"
				},
				link:{
					required: "<p class='vali-error'>Vui lòng nhập tên tin</p>",
					url: "<p class='vali-error'>Vui lòng nhập đúng url web</p>",
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
                        <h4 class="title">Thêm quảng cáo</h4>
                    </div>
                    <div class="content">
                    <%
                    	if(request.getAttribute("objAdv") != null){
                    		Adv objAdv = (Adv) request.getAttribute("objAdv");
                    %>
                        <form id="formedit" action="<%=request.getContextPath() %>/admin/adv/edit?aid=<%=objAdv.getIdAdv() %>" enctype="multipart/form-data" method="post">
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Tên quảng cáo</label>
                                        <input type="text" name="name" class="form-control border-input" placeholder="Tên quảng cáo" value="<%=objAdv.getNameAdv() %>">
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>link</label>
                                        <input type="text" name="link" class="form-control border-input" placeholder="http://" value="<%=objAdv.getLink() %>">
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Hình ảnh</label>
                                        <input style="display: block;" type="file" name="picture" class="form-control" placeholder="Chọn ảnh" />
                                        <%
                                        if(!"".equals(objAdv.getPicture())){
                                        %>
                                        	<img style="width: 150px; height: 100px" src="<%=request.getContextPath() %>/files/<%=objAdv.getPicture() %>" />	
                                     	<%} %>
                                    </div>
                                </div>
                            </div>
                            <div class="text-center">
                                <input type="submit" class="btn btn-info btn-fill btn-wd" value="Thêm" />
                            </div>
                            <div class="clearfix"></div>
                        </form>
                        <%} %>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="/templates/admin/inc/footer.jsp" %>  