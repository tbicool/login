<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page import="org.apache.shiro.authc.ExcessiveAttemptsException"%>
<%@ page import="org.apache.shiro.authc.IncorrectCredentialsException"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>

<head>
	<title>登录页</title>
<link rel="stylesheet" href="${ctx}/static/assets/plugins/data-tables/DT_bootstrap.css"/><base>
	<script type="text/javascript" src="${ctx}/static/assets/plugins/data-tables/jquery.dataTables.js"></script>
	<script type="text/javascript" src="${ctx}/static/assets/plugins/data-tables/DT_bootstrap.js"></script>

<script>
$(document).ready(function() {
	mz_datatbles.dt();
});

var oTable;
mz_datatbles = {
	dt:function(){
		if($('#listTable').length) {
			var aColumns = [
				{'name':'loginName','value':'登录名'},	
				{'name':'userName','value':'用户名'},
				{'name':'email','value':'用户邮箱'}
			];
			oTable = $("#listTable").dataTable({
	    		"fnServerParams": function ( aoData ) {
	    			jQuery.each( $("form#listForm").serializeArray(), function(i, field){
	    				aoData.push( field );    
					});
	    		} ,    
	        	"bScrollCollapse": false,
	        	"bServerSide": true,
	        	"bProcessing": true,
	        	"bSort" : true, 
	        	"sPaginationType": "bootstrap",
	        	"sDom" : "<'row'<'col-md-6 col-sm-12'l>r>t<'row'<'col-md-5 col-sm-12'i><'col-md-7 col-sm-12'p>>", 
	        	"sAjaxSource": "${ctx}/user/user/list",
	        	"oLanguage": {  
	        		"sProcessing": "数据加载中",
	        		"sLoadingRecords": "Please wait - loading...",
	        		"sLengthMenu": "每页显示 _MENU_ 行&nbsp;",
	        		"sSearch": "_INPUT_",
	        		"oPaginate": {       "sFirst": "首页" ,  "sPrevious": "前页" ,"sNext": "下页", "sLast": "尾页"     },
	        		"sZeroRecords": "未找到符合条件的数据",
	        		"sEmptyTable": "未找到符合条件的数据"
	        	} ,
	       	 	"aoColumns": [
					{ "mDataProp": "loginName", "sName": "loginName", "sClass": "center" },
					{ "mDataProp": "userName", "sName": "userName", "sClass": "center" },
					{ "mDataProp": "email", "sName": "email", "sClass": "center" },
					{ "bSortable": false, "mDataProp": null, "sClass": "center",
						"fnRender" : function(oObj) {
							var html = '<shiro:hasPermission name="auth:edit">' +
								'<a title="修改"   href="${ctx}/user/user/update/'+oObj.aData.userId+'">修改</a>&nbsp;'+
								'<a title="删除" onclick="return delConfirm()"  href="${ctx}/user/user/delete/'+oObj.aData.userId+'">删除</a>&nbsp;'+
								'</shiro:hasPermission>'
								
							return html; 
						}
					}
				] ,
				"fnInfoCallback": function( oSettings, iStart, iEnd, iMax, iTotal, sPre) {
	        		getFrameHeight();
	        		return "&nbsp;"+(iTotal>0?iStart:0)+" ~ "+iEnd+" 行 总共"+iTotal+"行 ";  
	        	} 
	    	});
		}
	}
};
var message = '${message}';
if(message!=null && message!=""){
	alert(message);
}

function delConfirm(){
	if(confirm("是否真的确定删除该用户?")){
		return true;
	}else{
		return false;
	}
}

function toSave(){
	window.location.href = "${ctx}/user/user/create";
}
</script>
</head>

<body  style="background-color: #fff !important">
<!-- BEGIN CONTAINER -->
<div class="page-container">
	<!-- BEGIN CONTENT -->
	<div class="page-content-wrapper">
		<div class="page-content" style="margin-left: auto;">
			<div class="row">
				<div class="col-md-12">
					<div class="tabbable tabbable-custom boxless">
						<div class="tab-content">
							<div class="tab-pane active" id="tab_0">
								<div class="portlet box blue">
									<div class="portlet-title">
										<div class="caption">
											<i class="fa fa-reorder"></i>用户查询条件
										</div>
									</div>
									<div class="portlet-body form">
										<!-- BEGIN FORM-->
										<form name="listForm" id="listForm" action="" method="post">
											<div class="form-body">
												<h3 class="form-section"></h3>
												<div class="row">
													<div class="col-md-6">
														<div class="form-group">
															<label class="control-label col-md-3">用户名</label>
															<div class="col-md-9">
																<input type="text" class="form-control" name="userName">
															</div>
														</div>
													</div>
													<!--/span-->
												</div>
												
											</div>
											<div class="form-actions fluid">
												<div class="row">
													<div class="col-md-6">
														<div class="col-md-offset-3 col-md-9">
															<button class="btn green" type="button" onclick="oTable.fnDraw();">查询</button>
															<button class="btn default" type="reset">重置</button>
														</div>
													</div>
													<div class="col-md-6" align="right">
														<shiro:hasPermission name="user:edit">
															<button class="btn blue"  type="button" onclick="toSave()">新增用户</button>
														</shiro:hasPermission>
													</div>
												</div>
											</div>
										</form>
										<!-- END FORM-->
									</div>
								</div>
								<div class="row">
									<div class="col-md-12">
										<!-- BEGIN EXAMPLE TABLE PORTLET-->
										<div class="portlet box blue">
											<div class="portlet-title">
												<div class="caption">
													<i class="fa fa-globe"></i>列表内容
												</div>
												<div class="actions">
													<div class="btn-group">
														<a class="btn default" href="#" data-toggle="dropdown">
														导出
														</a>
													</div>
												</div>
											</div>
											<div class="portlet-body">
												<table class="table table-striped table-bordered table-hover table-full-width"  id="listTable">
												<thead>
													<tr>
														<th>登录名</th>
														<th>用户名</th>
														<th>用户邮箱</th>
														<th>操作</th>
													</tr>
												</thead>
												<tbody>
												
												</tbody>
												</table>
											</div>
										</div>
									<!-- END EXAMPLE TABLE PORTLET-->
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- END CONTENT -->
</div>
</body>

</html>
