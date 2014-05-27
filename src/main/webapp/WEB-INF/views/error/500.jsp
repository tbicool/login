<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="org.slf4j.Logger,org.slf4j.LoggerFactory" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%	
	//设置返回码200，避免浏览器自带的错误页面
	response.setStatus(200);
	//记录日志
	Logger logger = LoggerFactory.getLogger("500.jsp");
	logger.error(exception.getMessage(), exception);
%>

<!DOCTYPE html>
<!-- 
Template Name: Metronic - Responsive Admin Dashboard Template build with Twitter Bootstrap 3.0.3
Version: 1.5.5
Author: KeenThemes
Website: http://www.keenthemes.com/
Purchase: http://themeforest.net/item/metronic-responsive-admin-dashboard-template/4021469?ref=keenthemes
-->
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<head>
<title></title>
<!--
 metronic css
-->
<!-- BEGIN GLOBAL MANDATORY STYLES -->
<link href="${ctx}/static/assets/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/static/assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/static/assets/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>
<!-- END GLOBAL MANDATORY STYLES -->
<!-- BEGIN PAGE LEVEL STYLES -->
<link rel="stylesheet" type="text/css" href="${ctx}/static/assets/plugins/select2/select2_metro.css"/>
<!-- END PAGE LEVEL SCRIPTS -->
<!-- BEGIN PAGE LEVEL PLUGIN STYLES -->
<link href="${ctx}/static/assets/plugins/gritter/css/jquery.gritter.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/static/assets/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/static/assets/plugins/fullcalendar/fullcalendar/fullcalendar.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/static/assets/plugins/jqvmap/jqvmap/jqvmap.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/static/assets/plugins/jquery-easy-pie-chart/jquery.easy-pie-chart.css" rel="stylesheet" type="text/css"/>
<!-- END PAGE LEVEL PLUGIN STYLES -->
<!-- BEGIN THEME STYLES -->
<link href="${ctx}/static/assets/css/style-metronic.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/static/assets/css/style.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/static/assets/css/style-responsive.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/static/assets/css/plugins.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/static/assets/css/themes/default.css" rel="stylesheet" type="text/css" id="style_color"/>
<link href="${ctx}/static/assets/css/custom.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/static/assets/css/pages/error.css" rel="stylesheet" type="text/css"/>
<!-- END COPYRIGHT -->
<html>
<head>
	<title>500 - 系统内部错误</title>
</head>

<body style="background-color: #fff !important">
	<!-- BEGIN PAGE CONTENT-->
	<div class="col-md-12 page-500">
			<div class=" number">
				 500
			</div>
			<div class=" details">
				<h3>哎呀！服务出错了.</h3>
				<p>
					 我们会及时修复!<br/>
					请稍候，给您带来的不便，深感抱歉.<br/><br/>
				</p>
			</div>
		</div>
</body>
</html>
