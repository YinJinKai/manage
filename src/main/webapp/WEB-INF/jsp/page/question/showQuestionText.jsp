<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/lib/css/showquestiontext.css" />
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
<script src="<%=request.getContextPath()%>/lib/js/showquestiontext.js"
	type="text/javascript" charset="utf-8"></script>
</head>
<body>
	<table id="dg"></table>
	<div id="menu" style="padding: 20px 80px; background: #eee;">
		<a class="title">选择问卷:</a><input id="cctm" name="dept"> <a
			class="title titler">创建人:</a><input id="tb" type="text"> <a
			id="btn" onclick="seeQn('预览问卷')"
			style="float: right; width: 100px; height: 30px;">预览问卷</a>
		<div style="margin-top: 10px">
			<a id="delete" onclick="deleteqn()">移除题目</a>&nbsp;&nbsp;&nbsp;<a
				id="order" onclick="setOrder()">设置题目顺序</a>&nbsp;&nbsp;&nbsp;<a
				id="addqn" onclick="addqn()">发布此问卷</a><a id="deleteQbtn"
				onclick="deleteThisQn()"
				style="float: right; width: 100px; height: 30px;">移除该问卷</a>
		</div>

	</div>
	<!-- 	选择顺序框 -->
	<div id="dd">
		<div class="chooseOrder">
			<a class="title">选择题目顺序:</a><input id="orderBy" name="orderBy">
		</div>
		<div style="width: 78px; height: 30px; margin: 0 auto;">
			<a id="conirm" onclick="conirm()">确认</a>
		</div>

	</div>
	<!-- 预览框 -->
	<div id="seeQnq"></div>
	<!-- 	发布框 -->
	<div id="addQnq">
		<div class="addBox" style="margin-top:40px;">
			<a class="title3">标题:</a><a style="float: right;position: relative;">
				<div class="no-select-tip" id="J_noSelectTip">
				 
				 </div> <input
				id="addqntitle" type="text">
			</a>
		</div>
		<div class="addBox">
			<a class="title3">时间区间:</a>
			<div style="float: right; margin-top: 10px">
				<input id="startTmie" type="text"></input>--<input id="endTime"
					type="text"></input>
			</div>
		</div>
		<div class="addBox" style="margin-top: 50px;">
			<a class="title3">备注:</a><a style="float: right;"><input
				id="addqnbeizhu" type="text"
				style="width: 200px; height: 90px; margin-left: 20px;"></a>
		</div>
		<div class="addBox" style="margin-top: 90px; text-align: center;">
			<a id="closed" onclick="closed()">取消</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
				id="fabu" onclick="fabu()">发布</a>
		</div>
	</div>
</body>
</html>