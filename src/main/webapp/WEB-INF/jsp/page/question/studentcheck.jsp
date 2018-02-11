<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/lib/css/student_check.css" />
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
<script src="<%=request.getContextPath()%>/lib/js/student_check.js"
	type="text/javascript" charset="utf-8"></script>
</head>
<body>
	<table id="dg"></table>
	<div id="menu" style="padding: 10px 5px;">
		&nbsp;&nbsp;&nbsp;&nbsp; 班级:&nbsp; <input id="classNumber" type="text"
			style="width: 100px;">&nbsp;&nbsp;&nbsp;&nbsp; 学生:&nbsp; <input
			id="username" type="text" style="width: 100px"><a id="userCode" style="display:none"></a>&nbsp;&nbsp;&nbsp;&nbsp;
	</div>
	<div id="dd">
	  <a id="titleid" style="display:none"></a><a id="qncode" style="display:none"></a>
      <div class="datibox"><a id="datibtn" onclick="datibtn()" style="width:90px;">答题</a></div>
      <div class="datibox"><a id="checkbtn" onclick="checkbtn()" style="width:90px;">查看评分</a></div>
      <div class="datibox"><a id="closebtn" onclick="closebtn()" style="width:90px;">取消</a></div>
	</div>  
<!-- 	答题 -->
	<div id="seeQnq"></div>
<!-- 查看评分 -->
<div id="level">
   <div id="levelinfor" class="levelinfor"></div>
</div>
</body>
</html>