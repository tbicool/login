<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<title>权限管理</title>
	<style type="text/css">
		span{
			display:inline-block;
		}
	</style>
	<script>
		$(document).ready(function() {
			$("#authCode").focus();
			var actionId = '${actionId}';
			if(actionId=="1"){
				$("input:radio[value=A]").attr('checked','true');
			}
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
   					authCode: {
						required:true,
						rangelength:[1,30],
						remote:{ 
　　		 					type: "get",  //数据发送方式   
                            dataType: "json",       //接受数据格式      
　　							url:"${ctx}/user/auth/checkCode",
　　	 						data:{
　　								authCode:function(){
									return $("#authCode").val();
　　								}
							}
						}                             
					},
					authName:{
						required:true
					}
  				},
        		messages: {
   					authCode: {
						required:"权限编码不能为空！",
						rangelength:jQuery.format("权限编码位数必须在{0}到{1}字符之间！"),
						remote:jQuery.format("权限编码已经被使用")
					},
					authName:{
						required:"权限名称不能为空！"
					}
  				}
			});
			$("#submitBtn").click(function(){
		    	if($("#inputForm").valid()){
		    		if(!checkRadio()){
						alert("权限类型为必填项,请选择");
						return false;
					}
					if(actionId=="1"){
						document.inputForm.action = "${ctx}/user/auth/save";
					}else if(actionId=="2"){
						document.inputForm.action = "${ctx}/user/auth/update";
					}
		     		$("#inputForm").submit();
		 		}
			});
		});
		
		function checkRadio(){
			var val=$('input:radio[name="authType"]:checked').val(); 
			if(val==null){ 
                return false; 
            } 
           	return true;
		}
		
		function back(){
			window.location.href="${ctx}/user/auth";
		}
	</script>
</head>

<body  style="background-color: #fff !important">
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
											<i class="fa fa-reorder"></i>权限信息
										</div>
									</div>
									<div class="portlet-body form">
										<!-- BEGIN FORM-->
										<form:form id="inputForm" name="inputForm" modelAttribute="auth" cssClass="form-horizontal"
											action="" method="post">
											<input type="hidden" name="authId" id="authId" value="${auth.authId}"/>	
											<div class="form-body">
												<div class="form-group">
													<label class="col-md-3 control-label"><font color="red">*</font>权限编码:</label>
													<c:if test="${actionId=='1'}">
														<div class="col-md-4">
															<input type="text" class="form-control" id="authCode" name="authCode"  value="${auth.authCode}" placeholder="权限编码不能为空">
														</div>
													</c:if>
													<c:if test="${actionId=='2'}">
														<div class="col-md-9">
															<p class="form-control-static">
																${auth.authCode}
																<input type="hidden" name="authCode"  id="authCode1"  value="${auth.authCode}"/>
															</p>
														</div>
													</c:if>
												</div>
												<div class="form-group">
													<label class="col-md-3 control-label"><font color="red">*</font>权限名称:</label>
													<div class="col-md-4">
														<input type="text" class="form-control" id="authName" name="authName"  value="${auth.authName}" placeholder="权限名称不能为空">
													</div>
												</div>
												<div class="form-group">
													<label class="col-md-3 control-label"><font color="red">*</font>权限类型:</label>
													<div class="col-md-4">
														<div class="radio-list">
															<form:radiobuttons path="authType" items="${allAuthType}" itemLabel="typeText" itemValue="typeValue" />
														</div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-md-3 control-label">权限父编码:</label>
													<div class="col-md-4">
														<input type="text" class="form-control" id="authParentCode" name="authParentCode"  value="${auth.authParentCode}" >
													</div>
												</div>
												<div class="form-group">
													<label class="col-md-3 control-label">菜单地址:</label>
													<div class="col-md-4">
														<input type="text" class="form-control" id="menuAddr" name="menuAddr"  value="${auth.menuAddr}" >
													</div>
												</div>
											</div>
											<div class="form-actions fluid">
												<div class="col-md-offset-3 col-md-9">
													<button type="button" class="btn blue" id="submitBtn">保 存</button>
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
	<!-- END CONTENT -->
</div>
<!-- END CONTAINER -->
</body>
</html>
