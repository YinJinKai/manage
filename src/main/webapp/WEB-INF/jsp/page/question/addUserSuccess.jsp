<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/lib/css/addUserSuccess.css" />
<script src="<%=request.getContextPath()%>/lib/js/jquery-3.2.1.min.js"
	type="text/javascript" charset="utf-8"></script>
<script src="<%=request.getContextPath()%>/lib/js/tools.js"
	type="text/javascript" charset="utf-8"></script>
<script src="<%=request.getContextPath()%>/lib/js/addUserSuccess.js"
	type="text/javascript" charset="utf-8"></script>
</head>
<body>
	<a id="witchinfor" style="display: none">${tip}</a>
	<a id="userName" style="display: none">${msg2}</a>
	<div class="context">
		<div class="tip" id="tipinfor">
			<div class="tishi"></div>
			<div class="errorusername"></div>
		</div>
	</div>
</body>
</html>