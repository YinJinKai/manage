<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/lib/css/userUpdate.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/lib/easyui/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/lib/easyui/themes/icon.css" />
<script src="<%=request.getContextPath()%>/lib/js/jquery-3.2.1.min.js"
	type="text/javascript" charset="utf-8"></script>
<script
	src="<%=request.getContextPath()%>/lib/easyui/jquery.easyui.min.js"
	type="text/javascript" charset="utf-8"></script>
<script
	src="<%=request.getContextPath()%>/lib/easyui/locale/easyui-lang-zh_CN.js"
	type="text/javascript" charset="utf-8"></script>
<script src="<%=request.getContextPath()%>/lib/js/tools.js"
	type="text/javascript" charset="utf-8"></script>
<script src="<%=request.getContextPath()%>/lib/js/userUpdate.js"
	type="text/javascript" charset="utf-8"></script>
</head>
<body>
<table id="dg"></table>
		<div id="menu" style="padding: 20px 80px;background:#eee; ">
			<a class="title">权限查询:</a><input id="cctm" name="dept">
			<a class="title titler">班级查询:</a><input id="cctitle" type="text">
			<a class="title titler">姓名查询:</a><input id="tName" type="text">
			<a class="title titler">账号查询:</a><input id="nameCode" type="text">
		</div>
		<div id="dd">
			<div class="upbox"><a class="title titler">姓名:</a><input id="upusername" type="text"></div>
			<div class="upbox"><a class="title titler">密码:</a><input id="uppassword" type="text"></div>
			<div class="upbox"><a class="title titler">班级:</a><input id="upclass" type="text"></div>
			<div class="upbox"><a class="title titler">角色:</a><input id="uprole" type="text"></div>
			<div class="gradebtn levelbox">
				<a id="gradebtn" onclick="tijiao()">确定</a>
			</div>
		</div>
</body>
</html>