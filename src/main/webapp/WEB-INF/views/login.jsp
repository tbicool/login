<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page import="org.apache.shiro.authc.ExcessiveAttemptsException"%>
<%@ page import="org.apache.shiro.authc.IncorrectCredentialsException"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>

<head>
	<title>登录页</title>

<!-- BEGIN PAGE LEVEL SCRIPTS -->
<link href="${ctx}/static/assets/css/pages/login.css" rel="stylesheet" type="text/css"/>
<script>
if (top.location != self.location){
	top.location=self.location;
	top.location = "${ctx}/main";
}
var Login = function () {
	var handleLogin = function() {
		$('.login-form').validate({
	            errorElement: 'span', //default input error message container
	            errorClass: 'help-block', // default input error message class
	            focusInvalid: false, // do not focus the last invalid input
	            rules: {
	                username: {
	                    required: true
	                },
	                password: {
	                    required: true
	                },
	                remember: {
	                    required: false
	                }
	            },

	            messages: {
	                username: {
	                    required: "请输入用户名."
	                },
	                password: {
	                    required: "请输入密码."
	                }
	            },

	            invalidHandler: function (event, validator) { //display error alert on form submit   
	                $('.alert-danger', $('.login-form')).show();
	            },

	            highlight: function (element) { // hightlight error inputs
	                $(element)
	                    .closest('.form-group').addClass('has-error'); // set error class to the control group
	            },

	            success: function (label) {
	                label.closest('.form-group').removeClass('has-error');
	                label.remove();
	            },

	            errorPlacement: function (error, element) {
	                error.insertAfter(element.closest('.input-icon'));
	            },

	            submitHandler: function (form) {
	                form.submit();
	            }
	        });

	        $('.login-form input').keypress(function (e) {
	            if (e.which == 13) {
	                if ($('.login-form').validate().form()) {
	                    $('.login-form').submit();
	                }
	                return false;
	            }
	        });
	}

	
    
	return {
        //main function to initiate the module
        init: function () {
            handleLogin();
        }

    };

}();

jQuery(document).ready(function() {
  Login.init();
  App.init();
});

</script>
<style type="text/css">
.login {
  background-color: #777 !important;
}


.login .logo {
  margin: 0 auto;   
  margin-top:120px;
  padding: 15px;
  text-align: center;
}

.login .content {
  background-color:#fff; 
  width: 380px;
  margin: 0 auto; 
  margin-bottom: 0px;
  padding: 30px;  
  padding-top: 20px;  
  padding-bottom: 15px;  
}
</style>
</head>
<body class="login">
<!-- BEGIN LOGO -->
	<div class="logo">
		
	</div>
	<!-- END LOGO -->
	<!-- BEGIN LOGIN -->
	<div class="content">
		<!-- BEGIN LOGIN FORM -->
		<form class="login-form" id="loginForm" action="${ctx}/login" method="post">
			<h3 class="form-title">XX登陆后台管理系统</h3>
			<%
				String error = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM);
				if(error != null){
			%>
				<div class="alert alert-danger">
					<button class="close" data-dismiss="alert">×</button><span>用户名或者密码错误，登录失败，请重试.</span>
				</div>
			<%
				}
			%>
			
			<div class="form-group">
				<!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
				<label class="control-label visible-ie8 visible-ie9">Username</label>
				<div class="input-icon">
					<i class="fa fa-user"></i>
					<input class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="用户名" id="username" name="username"/>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label visible-ie8 visible-ie9">Password</label>
				<div class="input-icon">
					<i class="fa fa-lock"></i>
					<input class="form-control placeholder-no-fix" type="password" autocomplete="off" placeholder="密码" id="password" name="password"/>
				</div>
			</div>
			<div class="form-actions">
				<label class="checkbox">
				<!-- 
				<input type="checkbox" id="rememberMe" name="rememberMe"/> 记住我  --></label>
				<button type="submit" class="btn blue pull-right">
				登陆 <i class="m-icon-swapright m-icon-white"></i>
				</button>
			</div>
			
		</form>
	</div>
	<!-- END LOGIN -->
	<!-- BEGIN COPYRIGHT -->
	<div class="copyright">
		 2014 &copy; XX登陆后台管理系统 by stm.
	</div>
</body>

</html>
