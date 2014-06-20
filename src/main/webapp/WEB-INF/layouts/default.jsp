<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>

<html lang="en" class="no-js">
<head>
<meta charset="utf-8"/>
<title>stm:<sitemesh:title/></title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1.0" name="viewport"/>
<meta content="" name="description"/>
<meta content="" name="author"/>
<meta name="MobileOptimized" content="320">
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
<!-- END COPYRIGHT -->


<script src="${ctx}/static/iframe.js" type="text/javascript"></script>
<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<!-- BEGIN CORE PLUGINS -->
<!--[if lt IE 9]>
	<script src="${ctx}/static/assets/plugins/respond.min.js"></script>
	<script src="${ctx}/static/assets/plugins/excanvas.min.js"></script> 
	<![endif]-->

<script src="${ctx}/static/assets/plugins/jquery-1.10.2.min.js" type="text/javascript"></script>
<script src="${ctx}/static/assets/plugins/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
<script src="${ctx}/static/assets/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${ctx}/static/assets/plugins/bootstrap-hover-dropdown/twitter-bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
<script src="${ctx}/static/assets/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
<script src="${ctx}/static/assets/plugins/jquery.blockui.min.js" type="text/javascript"></script>
<script src="${ctx}/static/assets/plugins/jquery.cokie.min.js" type="text/javascript"></script>
<script src="${ctx}/static/assets/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
<!-- END CORE PLUGINS -->
<!-- BEGIN PAGE LEVEL PLUGINS -->
<script src="${ctx}/static/assets/plugins/jqvmap/jqvmap/jquery.vmap.js" type="text/javascript"></script>
<script src="${ctx}/static/assets/plugins/jqvmap/jqvmap/maps/jquery.vmap.russia.js" type="text/javascript"></script>
<script src="${ctx}/static/assets/plugins/jqvmap/jqvmap/maps/jquery.vmap.world.js" type="text/javascript"></script>
<script src="${ctx}/static/assets/plugins/jqvmap/jqvmap/maps/jquery.vmap.europe.js" type="text/javascript"></script>
<script src="${ctx}/static/assets/plugins/jqvmap/jqvmap/maps/jquery.vmap.germany.js" type="text/javascript"></script>
<script src="${ctx}/static/assets/plugins/jqvmap/jqvmap/maps/jquery.vmap.usa.js" type="text/javascript"></script>
<script src="${ctx}/static/assets/plugins/jqvmap/jqvmap/data/jquery.vmap.sampledata.js" type="text/javascript"></script>
<script src="${ctx}/static/assets/plugins/flot/jquery.flot.js" type="text/javascript"></script>
<script src="${ctx}/static/assets/plugins/flot/jquery.flot.resize.js" type="text/javascript"></script>
<script src="${ctx}/static/assets/plugins/jquery.pulsate.min.js" type="text/javascript"></script>
<script src="${ctx}/static/assets/plugins/bootstrap-daterangepicker/moment.min.js" type="text/javascript"></script>
<script src="${ctx}/static/assets/plugins/bootstrap-daterangepicker/daterangepicker.js" type="text/javascript"></script>
<script src="${ctx}/static/assets/plugins/gritter/js/jquery.gritter.js" type="text/javascript"></script>
<script src="${ctx}/static/assets/plugins/jquery-validation/dist/jquery.validate.min.js" type="text/javascript"></script>
<script src="${ctx}/static/assets/plugins/backstretch/jquery.backstretch.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/static/assets/plugins/select2/select2.min.js"></script>
<!-- IMPORTANT! fullcalendar depends on jquery-ui-1.10.3.custom.min.js for drag & drop support -->
<script src="${ctx}/static/assets/plugins/fullcalendar/fullcalendar/fullcalendar.min.js" type="text/javascript"></script>
<script src="${ctx}/static/assets/plugins/jquery-easy-pie-chart/jquery.easy-pie-chart.js" type="text/javascript"></script>
<script src="${ctx}/static/assets/plugins/jquery.sparkline.min.js" type="text/javascript"></script>

<script language="javascript" type="text/javascript" src="${ctx}/static/My97DatePicker/WdatePicker.js"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${ctx}/static/assets/scripts/app.js" type="text/javascript"></script>
<script src="${ctx}/static/assets/scripts/index.js" type="text/javascript"></script>
<script src="${ctx}/static/assets/scripts/tasks.js" type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->
<sitemesh:head/>
</head>
<!-- 在body中加入，让子页面的onload和class属性生效 -->
<body <sitemesh:getProperty property="body.class" writeEntireProperty="true" /> 
		<sitemesh:getProperty property="body.onload" writeEntireProperty="true" />
		<sitemesh:getProperty property="body.id" writeEntireProperty="true" />
		<sitemesh:getProperty property="body.name" writeEntireProperty="true" />
		<sitemesh:getProperty property="body.style" writeEntireProperty="true" />
	<sitemesh:body/>
</body>
</html>