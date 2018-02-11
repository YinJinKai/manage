<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/lib/css/creatquestion.css" />
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
<script src="<%=request.getContextPath()%>/lib/js/creatquestion.js"
	type="text/javascript" charset="utf-8"></script>
</head>
<body>
	<div id="tt">
		<div title="添加问卷名">
			<div class="tab_context">
				<div class="choosenew">
					<a class="left"><label for="">创建一个前所未有的问卷:</label>&nbsp;&nbsp;<input type="radio" name="do" id="" value="1" /></a>
					<a class="right">
					    <div class="no-select-tip" id="J_noSelectTip">
							
						</div>
						<input id="tb" type="text" style="width: 150px"></a>
				</div>
				<div class="choosehas">
					<a class="left"><label for="">选择已有问卷:</label>&nbsp;&nbsp;<input
						type="radio" name="do" id="" value="0" /></a> <a class="right"><input
						id="extistcc" name="dept" value=""></a>
				</div>
				<div class="searchcombox switchbtn">
					<a id="qiandao"></a>
				</div>
			</div>

		</div>
		<div title="匹配试题">
			<table id="dg"></table>
		</div>
	</div>
	<div id="menu" style="padding: 20px 20px; background: #eee;">
		<a class="title">题目类型:</a><input id="cctm" name="dept" value="">&nbsp;&nbsp;&nbsp;&nbsp;<input
			id="stb" type="text"> &nbsp;&nbsp;&nbsp;&nbsp;
			<a class="title">当前问卷名称:</a>&nbsp;&nbsp;<input id="qn" type="text" style="width: 120px"><a id="qncode" style="display: none;"></a></a>
			<a id="add" href="#" style="width: 100px; height: 30px;float: right;" onclick="tianjia()">添加</a>
	</div>

	</div>
</body>
</html>