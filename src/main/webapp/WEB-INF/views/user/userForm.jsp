<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<title>用户管理</title>
	<style type="text/css">
	span{
		display:block;
	}
	
	</style>
	<script>
		$(document).ready(function() {
			$("#loginName").focus();
			var actionId = '${actionId}';
			$("#inputForm").validate({
				errorElement: 'span', 
		        errorClass: 'help-block', 
		        focusInvalid: false, 
	            highlight: function (element) { 
	                $(element)
	                    .closest('.form-group').addClass('has-error'); 
	            },
	            success: function (label) {
	                label.closest('.form-group').removeClass('has-error');
	                label.remove();
	            },
	            errorPlacement: function (error, element) {
	                error.insertAfter(element.closest('.col-md-4'));
	            },
				rules: {
   					loginName: {
						required:true,
						rangelength:[1,30],
						remote:{ 
　　		 					type: "post",
                            dataType: "json",    
　　							url:"${ctx}/user/user/checkLoginName?oldLoginName="+encodeURIComponent('${user.loginName}'),
　　	 						data:{
　　								loginName:function(){
									return $("#loginName").val();
　　								}
							}
						}                             
					},
					userName:{
						required:true,
						rangelength:[1,50]
					},
					password:{
						required:true,
						rangelength:[3,50]
					},
					passwordConfirm:{
						required:true,
						equalTo:"#password"
					}
  				},
        		messages: {
   					loginName: {
						required:"登录名不能为空！",
						rangelength:jQuery.format("登录名位数必须在{0}到{1}字符之间！"),
						remote:jQuery.format("登录名已经被使用")
					},
					userName:{
						required:"用户名不能为空！",
						rangelength:jQuery.format("用户名名位数必须在{0}到{1}字符之间！")
					},
					password:{
						required:"密码不能为空！",
						rangelength:jQuery.format("密码位数必须在{0}到{1}字符之间！")
					},
					passwordConfirm:{
						required:"确认密码不能为空！",
						equalTo:"确认密码与密码不一致！"
					}
  				}
			});
			$("#submitBtn").click(function(){
		    	if($("#inputForm").valid()){
					if(actionId=="1"){
						document.inputForm.action = "${ctx}/user/user/save";
					}else if(actionId=="2"){
						document.inputForm.action = "${ctx}/user/user/update";
					}
		     		$("#inputForm").submit();
		 		}
			});
			getFrameHeight();
		});
		
		function back(){
			window.location = '${ctx}/user/user';
		}
	</script>
</head>

<body style="background-color: #fff !important">
      <!-- BEGIN CONTAINER -->
<div class="page-container">
	<!-- BEGIN CONTENT -->
	<div class="page-content-wrapper">
		<div class="page-content" style="margin-left: auto;">
			<!-- BEGIN PAGE HEADER-->
			<div class="row">
				<div class="col-md-12" align="right">
					<button type="button" class="btn blue dropdown-toggle" onclick="back()" data-toggle="dropdown" data-hover="dropdown" data-delay="1000" data-close-others="true">
						<span>
							返回
						</span>
					</button>
				</div>
			</div>
			<!-- END PAGE HEADER-->
			<!-- BEGIN PAGE CONTENT-->
			<div class="row">
				<div class="col-md-12">
					<div class="tabbable tabbable-custom boxless">
						<div class="tab-content">
							<div class="tab-pane active" id="tab_0">
								<div class="portlet box blue">
									<div class="portlet-title">
										<div class="caption">
											<i class="fa fa-reorder"></i>用户信息
										</div>
									</div>
									<div class="portlet-body form">
										<!-- BEGIN FORM-->
										<form:form id="inputForm" name="inputForm" modelAttribute="user" cssClass="form-horizontal"
											action="" method="post">
											<input type="hidden" name="userId" id="userId" value="${user.userId}"/>	
											<input type="hidden" name="authCodeString" id="authCodeString" value=""/>
											<div class="form-body">
												<div class="form-group">
													<label class="col-md-3 control-label"><font color="red">*</font>登录名:</label>
													<div class="col-md-4">
														<input type="text" class="form-control" id="loginName" name="loginName"  value="${user.loginName}" placeholder="登陆名称不能为空">
													</div>
												</div>
												<div class="form-group">
													<label class="col-md-3 control-label"><font color="red">*</font>昵称:</label>
													<div class="col-md-4">
														<input type="text" class="form-control" id="userName" name="userName"  value="${user.userName}" placeholder="昵称不能为空">
													</div>
												</div>
												<div class="form-group">
													<label class="col-md-3 control-label"><font color="red">*</font>密码:</label>
													<div class="col-md-4">
														<input type="text" class="form-control" id="password" name="password"  value="${user.password}" placeholder="密码不能为空">
													</div>
												</div>
												<div class="form-group">
													<label class="col-md-3 control-label"><font color="red">*</font>确认密码:</label>
													<div class="col-md-4">
														<input type="text" class="form-control" id="passwordConfirm" name="passwordConfirm"  value="${user.password}" placeholder="密码不能为空">
													</div>
												</div>
												<div class="form-group">
													<label class="col-md-3 control-label">邮箱:</label>
													<div class="col-md-4">
														<input type="text" class="form-control" id="email" name="email"  value="${user.email}" placeholder="邮箱不能为空">
													</div>
												</div>
												<div class="form-group">
													<label class="col-md-3 control-label"><font color="red">*</font>所属角色:</label>
													<div class="col-md-4">
														<form:checkboxes path="deptList" items="${allDepts}"  itemLabel="deptName"  itemValue="deptId" />
													</div>
												</div>
											</div>
											<div class="form-actions fluid">
												<div class="col-md-offset-3 col-md-9">
													<button type="button" class="btn blue" id="submitBtn" onclick="doSubmit()">保 存</button>
													<button type="reset" class="btn default">重 置</button>
												</div>
											</div>
										</form:form>
										<!-- END FORM-->
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- END PAGE CONTENT-->
		</div>
	</div>
	<!-- END CONTENT --
</div>
<!-- END CONTAINER -->                   
	
</body>
</html>
