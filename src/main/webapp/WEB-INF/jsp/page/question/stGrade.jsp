<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/lib/css/grade.css" />
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
<script src="<%=request.getContextPath()%>/lib/js/grade.js"
	type="text/javascript" charset="utf-8"></script>
</head>
<body>
	<table id="dg"></table>
	<div id="menu" style="padding: 20px 80px; background: #eee;">
		<a class="title">选择问卷:</a><input id="cctm" name="dept"> <a
			class="title titler">标题:</a><input id="cctitle" type="text">
		<a class="title titler">答卷人:</a><input id="st" type="text"> <a
			id="btn" onclick="showbox()"
			style="width: 100px; height: 30px; float: right;">评分</a>
	</div>
	<div id="dd">
		<div class="level levelbox">
			<a class="title titler">等级:</a><input id="level" type="text">
		</div>
		<div class="gradebtn levelbox">
			<a id="gradebtn" onclick="tijiao()">确定</a>
		</div>
	</div>
</body>
</html>