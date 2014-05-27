<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page import="org.apache.shiro.authc.ExcessiveAttemptsException"%>
<%@ page import="org.apache.shiro.authc.IncorrectCredentialsException"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>

<head>
	<title>登录页</title>
<script src="${ctx}/static/easyui/jquery.easyui.min.js" type="text/javascript"></script>
<link href="${ctx}/static/easyui/easyui.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/static/easyui/icon.css" rel="stylesheet" type="text/css"/>

<script>
	//定义全局的map
	
	jQuery(document).ready(function() {
	   App.init(); // initlayout and core plugins
	   $('#tt').tabs({
			tabHeight:50,
			border:false,   
	        onSelect:function(title){  
	        	//修改左侧菜单的选择方式
	        	$(".active").removeClass("active");
	        	var id = "#"+title;
	        	$(id).addClass("active");
	        	var module = $(id).parent().parent();
	        	module.addClass("active");
	        	$(id).parent().css("display","block");
	        	
	        }
		});
	});
	
	
	function setWinHeight(obj)

	{
		var win=obj;
		if (document.getElementById)
		{
			if (win && !window.opera)
			{	
				if (win.contentDocument && win.contentDocument.body.offsetHeight) 
					win.height = win.contentDocument.body.offsetHeight; 
				else if(win.Document && win.Document.body.scrollHeight)
					win.height = win.Document.body.scrollHeight;
			}
		}
		

	}
	
	function setTab(){
		$(".panel-body-noheader").css("width","auto");
	}
	
	function resizeIframe(iHeight){
   		var mainIframeObj = document.getElementById("rightFrame");
  		mainIframeObj.style.height = iHeight+"px";
  	}
	
	function addTab(title, url){
	    if ($('#tt').tabs('exists', title)){
	        $('#tt').tabs('select', title);
	    } else {
	        var content = '<iframe  width="100%" height="550" id="rightFrame" frameborder="0"  src="'+url+' "></iframe>';
	        $('#tt').tabs('add',{
	            title:title,
	            content:content,
	            closable:true
	        });
	    }
	   
	}
</script>
</head>
<!-- BEGIN BODY -->
<body class="page-header-fixed">
<!-- BEGIN HEADER -->
<div class="header navbar navbar-inverse navbar-fixed-top">
	<!-- BEGIN TOP NAVIGATION BAR -->
	<div class="header-inner">
		<!-- BEGIN LOGO -->
		<a class="navbar-brand" href="${ctx}/main">
		<img src="${ctx}/static/assets/img/logo.png" alt="logo" class="img-responsive"/>
		</a>
		<!-- END LOGO -->
		<!-- BEGIN RESPONSIVE MENU TOGGLER -->
		<a href="javascript:;" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
		<img src="${ctx}/static/assets/img/menu-toggler.png" alt=""/>
		</a>
		<!-- END RESPONSIVE MENU TOGGLER -->
		<!-- BEGIN TOP NAVIGATION MENU  右上侧导航-->
		<ul class="nav navbar-nav pull-right">
			<!-- BEGIN USER LOGIN DROPDOWN -->
			<li class="dropdown user">
				<a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
					<img alt="" src="${ctx}/static/assets/img/avatar1_small.jpg"/>
					<span class="username">
						${username}
					</span>
				</a>
			</li>
			<!-- login out -->
			<li class="dropdown" id="header_task_bar">
				<a href="${ctx}/logout" class="dropdown-toggle" >
				<i class="fa fa-key"></i>
				<span class="username">
					退出登陆
				</span>
				</a>
			</li>
			<!-- END login out -->
		</ul>
	</div>
	<!-- END TOP NAVIGATION BAR -->
</div>
<!-- BEGIN CONTAINER -->
<div class="page-container">
	<!-- BEGIN SIDEBAR -->
	<div class="page-sidebar-wrapper">
		<div class="page-sidebar navbar-collapse collapse">
			<!-- BEGIN SIDEBAR MENU -->
			<ul class="page-sidebar-menu">
				<li class="sidebar-toggler-wrapper">
					<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
					<div class="sidebar-toggler hidden-phone" onclick="setTab()">
					</div>
					<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
				</li>
				<li class="sidebar-search-wrapper">
					<br/>
				</li>
				<c:forEach items="${menu}" var="m" varStatus="mNo">
					<li class="" >
						<a href="javascript:;">
						<i class="fa"></i>
						<span class="title">
						${m.modelName}
						</span>
						<span class="selected">
						</span>
						<span class="arrow open">
						</span>
						</span>
						</a>
						<ul class="sub-menu" style="display: block">
							<c:forEach items="${m.list}" var="ah" varStatus="ahNo">
								<li id="${ah.authName }">
									<a href="#" onclick="addTab('${ah.authName }','${ctx}${ah.menuAddr}')">
									${ah.authName }
									</a>
								</li>
							</c:forEach>
						</ul>
					</li>
					
				</c:forEach>
				<!-- 
				<li class="" >
					<a href="javascript:;">
					<i class="fa"></i>
					<span class="title">
						系统管理
					</span>
					<span class="selected">
					</span>
					<span class="arrow open">
					</span>
					</span>
					</a>
					<ul class="sub-menu" style="display: block">
						<li id="用户管理">
							<a href="#" onclick="addTab('用户管理','${ctx}/user/user')">
							用户管理</a>
							
						</li>
						<li id="权限管理">
							<a href="#" onclick="addTab('权限管理','${ctx}/user/auth')">						
							权限管理</a>
						</li>
						<li id="角色管理">
							<a href="#" onclick="addTab('角色管理','${ctx}/user/dept')">						
							角色管理</a>
						</li>
					</ul>
				</li>
				 -->
			</ul>
			<!-- END SIDEBAR MENU -->
		</div>
	</div>
	<!-- END SIDEBAR -->
	<!-- BEGIN CONTENT -->
	<div class="page-content-wrapper">
		<div class="page-content">
			<!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
			<div class="modal fade" id="portlet-config" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
							<h4 class="modal-title">Modal title</h4>
						</div>
						<div class="modal-body">
							 Widget settings form goes here
						</div>
						<div class="modal-footer">
							<button type="button" class="btn blue">Save changes</button>
							<button type="button" class="btn default" data-dismiss="modal">Close</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<!-- /.modal -->
			<!-- END SAMPLE PORTLET CONFIGURATION MODAL FORM-->
			<!-- BEGIN STYLE CUSTOMIZER -->
			<div class="theme-panel hidden-xs hidden-sm">
				<div class="toggler">
				</div>
				<div class="toggler-close">
				</div>
				<div class="theme-options">
					<div class="theme-option theme-colors clearfix">
						<span>
							主题颜色
						</span>
						<ul>
							<li class="color-black current color-default" data-style="default">
							</li>
							<li class="color-blue" data-style="blue">
							</li>
							<li class="color-brown" data-style="brown">
							</li>
							<li class="color-purple" data-style="purple">
							</li>
							<li class="color-grey" data-style="grey">
							</li>
							<li class="color-white color-light" data-style="light">
							</li>
						</ul>
					</div>
					<div class="theme-option">
						<span>
							菜单栏
						</span>
						<select class="sidebar-option form-control input-small">
							<option value="fixed">固定</option>
							<option value="default" selected="selected">默认</option>
						</select>
					</div>
					<div class="theme-option">
						<span>
							菜单位置
						</span>
						<select class="sidebar-pos-option form-control input-small">
							<option value="left" selected="selected">居左</option>
							<option value="right">居右</option>
						</select>
					</div>
					<div class="theme-option">
						<span>
							页脚
						</span>
						<select class="footer-option form-control input-small">
							<option value="fixed">固定</option>
							<option value="default" selected="selected">默认</option>
						</select>
					</div>
				</div>
			</div>
			<!-- END STYLE CUSTOMIZER -->
			<!-- BEGIN PAGE HEADER-->
			<div id="tt" class="easyui-tabs" style="height:auto">
				<div class="row" title="后台主页" >
				<div class="col-md-12">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
					XX后台管理系统 <small>系统显示各个功能</small>
					</h3>
					<ul class="page-breadcrumb breadcrumb">
						<li>
							<i class="fa fa-home"></i>
							<a href="${ctx}/main">Home</a>
							<i class="fa fa-angle-right"></i>
						</li>
						<li>
							<a href="#">主页</a>
						</li>
					</ul>
				</div>
				</div>
			</div>
		</div>
	</div>
	<!-- END CONTENT -->
</div>
<!-- END CONTAINER -->
<!-- BEGIN FOOTER -->
<div class="footer">
	<div class="footer-inner">
		 2014 &copy; xx后台登陆管理系统.
	</div>
	<div class="footer-tools">
		<span class="go-top">
			<i class="fa fa-angle-up"></i>
		</span>
	</div>
</div>
</body>

</html>
