<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/templates/admin/inc/header.jsp"%>
<script type="text/javascript">
	$(document).ready(function (){
		$('#formadd').validate({
			rules:{
				username:{
					required: true,
					minlength: 4,
					maxlength: 10,
				},
				fullname:{
					required: true,
					minlength: 4,
					maxlength: 20,
				},
				password:{
					required: true,
					minlength: 4,
					maxlength: 10,
				},
				re_password:{
					equalTo: "#pass",
				},
				email: {
					email: true,
				},
				re_mail: {
					equalTo: "#eml",
				},
			},
			messages:{
				username:{
					required: "<p class='vali-error'>Vui lòng nhập username</p>",
					minlength: "<p class='vali-error'>Vui lòng nhập ít nhất 4 ký tự</p>",
					maxlength: "<p class='vali-error'>Vui lòng nhập nhiều nhất 10 ký tự</p>"
				},
				fullname:{
					required: "<p class='vali-error'>Vui lòng nhập fullname</p>",
					minlength: "<p class='vali-error'>Vui lòng nhập ít nhất 4 ký tự</p>",
					maxlength: "<p class='vali-error'>Vui lòng nhập nhiều nhất 20 ký tự</p>"
				},
				password:{
					required: "<p class='vali-error'>Vui lòng nhập password</p>",
					minlength: "<p class='vali-error'>Vui lòng nhập ít nhất 4 ký tự</p>",
					maxlength: "<p class='vali-error'>Vui lòng nhập nhiều nhất 10 ký tự</p>"
				},
				re_password:{
					equalTo: "<p class='vali-error'>Vui lòng nhập trùng password</p>",
				},
				email: {
					email: "<p class='vali-error'>Vui lòng nhập email hợp lệ</p>",
				},
				re_mail: {
					equalTo:  "<p class='vali-error'>Vui lòng nhập trùng email</p>",
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
						<h4 class="title">Thêm người dùng</h4>
					</div>
					<div class="content">
					<%
					User sobjUser = (User) session.getAttribute("sobjUser");
					if(request.getParameter("msg") != null){
                		int msg = Integer.parseInt(request.getParameter("msg"));
                		switch(msg){
                			case 0:
                				%>
                					<p style="color: red; font-weight: bold">Tên đăng nhập đã tồn tại</p>	
                				<%
                				break;
                		}
                	}
					%>
						<form id="formadd" action="<%=request.getContextPath() %>/admin/user/add" method="post">
							<div class="row">
								<div class="col-md-4">
									<div class="form-group">
										<label>Tên đăng nhập</label> 
										<input type="text" name="username" class="form-control border-input" placeholder="username" value="" />
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label>Tên đầy đủ</label> 
										<input type="text" name="fullname" class="form-control border-input" placeholder="fullname" value="" />
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label>Cấp bậc</label> 
										<select name="rank" class="form-control border-input">
										<%
										if("admin".equals(sobjUser.getUsername())){
										%>
											<option value="1">Admin</option>
											<option value="2">Mod</option>
											<option value="3">Trial Mod</option>
										<%} else if("Admin".equals(sobjUser.getRank())){ %>
											<option value="2">Mod</option>
											<option value="3">Trial Mod</option>
										<%} else if("Mod".equals(sobjUser.getRank())){%>
											<option value="3">Trial Mod</option>
										<%} %>
										</select>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label>Nhập mật khẩu</label> 
										<input type="password" id="pass" name="password" class="form-control border-input" placeholder="password" value="" />
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label>Nhập lại mật khẩu</label> 
										<input type="password" name="re_password" class="form-control border-input" placeholder="" value="" />
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label>Nhập email</label> 
										<input type="text" name="email" class="form-control border-input" placeholder="email" value="" />
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label>Nhập lại email</label> 
										<input type="text" id="eml" name="re_mail" class="form-control border-input" placeholder="" value="" />
									</div>
								</div>
							</div>
							<div class="text-center">
								<input type="submit" class="btn btn-info btn-fill btn-wd"
									value="Thêm" />
							</div>
							<div class="clearfix"></div>
						</form>
					</div>
				</div>
			</div>


		</div>
	</div>
</div>
<%@include file="/templates/admin/inc/footer.jsp"%>
