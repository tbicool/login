<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
<link href="${ctx}/static/assets/blueprint/1.0.1/zTreeStyle/zTreeStyle.css" type="text/css" rel="stylesheet" />
<script src="${ctx}/static/assets/jquery-ztree/jquery.ztree.core-3.2.js" type="text/javascript"></script>
<script src="${ctx}/static/assets/jquery-ztree/jquery.ztree.excheck-3.2.js" type="text/javascript"></script>
	<title>部门管理</title>
	<style type="text/css">
	span{
		display:inline-block;
	}
	
	
	</style>
	<script>
		var zNodes;

		var setting = {
			check: {
				enable: true,
				chkboxType: { "Y": "ps", "N": "ps" }
			},
		     data: {
		         simpleData: {
		             enable: true
		         }
		     }
		};
		
		$(document).ready(function(){
			 $.ajax({
		          	type: "POST",    
		          	url: '${ctx}/user/dept/tree/${dept.deptId}',                   
		          	dataType: "text",
		          	global: false,
		          	async: false,                      
		           	success: function (data) 
		           	{   
		          		zNodes=eval(data);
		          	},          
		          	error: function () {
		          		alert("Ajax请求数据失败!");
		        	}
			 });
			$.fn.zTree.init($("#tree"), setting, zNodes);
			getFrameHeight();
		});
		
		function validateForm(){
			return $("#inputForm").validate({
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
   					deptName: "required"
  				},
        		messages: {
   					deptName: "请输入名称"
  				}
  				
    		}).form();
		}
		
		function getNodes(){
			var treeObj = $.fn.zTree.getZTreeObj("tree");
			var nodes = treeObj.getCheckedNodes(true);
			var authCodeString = "";
			for(i=0;i<nodes.length;i++){
				authCodeString = authCodeString + nodes[i].id;
				if(nodes.length-i>1){
					authCodeString = authCodeString+",";
				}
			}
			$("#authCodeString").attr("value",authCodeString);
		}
		
		
		function doSubmit(){
			getNodes();
			var value = $("#authCodeString").val();
			if(validateForm()){
				if(value=="" || value==null){
					alert("权限不能为空");
					return false;
				}
				var actionId = '${actionId}';
				if(actionId=="1"){
					document.inputForm.action = "${ctx}/user/dept/save";
				}else if(actionId=="2"){
					document.inputForm.action = "${ctx}/user/dept/update";
				}
				document.inputForm.submit();
			}
		}
		
		function back(){
			window.location.href="${ctx}/user/dept";
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
											<i class="fa fa-reorder"></i>部门信息
										</div>
									</div>
									<div class="portlet-body form">
										<!-- BEGIN FORM-->
										<form:form id="inputForm" name="inputForm" modelAttribute="dept" cssClass="form-horizontal"
											action="" method="post">
											<input type="hidden" name="deptId" id="deptId" value="${dept.deptId}"/>
											<input type="hidden" name="authCodeString" id="authCodeString" value=""/>
											<div class="form-body">
												<div class="form-group">
													<label class="col-md-3 control-label"><font color="red">*</font>部门名称:</label>
													<div class="col-md-4">
														<input type="text" class="form-control" id="deptName" name="deptName"  value="${dept.deptName}" placeholder="部门名称不能为空">
													</div>
												</div>
												<div class="form-group">
													<label class="col-md-3 control-label"><font color="red">*</font>部门权限:</label>
													<div class="col-md-4">
														<div class="zTreeDemoBackground left">
															<ul id="tree" class="ztree"></ul>
														</div>
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
	<!-- END CONTENT -->
</div>
<!-- END CONTAINER -->                   
	
</body>
</html>
