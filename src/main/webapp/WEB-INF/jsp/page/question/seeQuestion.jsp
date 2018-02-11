<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/lib/css/seeq.css" />
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
<script src="<%=request.getContextPath()%>/lib/js/seeq.js"
	type="text/javascript" charset="utf-8"></script>
</head>
<body>
<a style="display:none"  id="createQuestionCode">${createQuestionCode}</a>
<a style="display:none"  id="userName">${userName}</a>
<a style="display:none" id="titleid">${titleid}</a>
	<div class="context" id="qnContext">
	
	</div>
	<div class="dobtn">
	  <div class="no-select-tip" id="J_noSelectTip">
			点击右上角<i class="arrow arrow-a"></i><i class="arrow arrow-b"></i>	 
	  </div> 
	  <a class="bbtn close" onclick="closeqq()">取消</a><a class="bbtn save" onclick="save()">交卷</a>
	</div>
</body>
</html>