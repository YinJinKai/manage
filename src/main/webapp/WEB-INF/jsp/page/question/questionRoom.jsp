<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/lib/css/questionRoom.css" />
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
<script src="<%=request.getContextPath()%>/lib/js/questionRoom.js"
	type="text/javascript" charset="utf-8"></script>
</head>
<body>
	<table id="dg"></table>
	<div id="menu" style="padding: 20px 20px; background: #eee;">
		<a class="title">题目类型:</a><input id="cctm" name="dept" value="">&nbsp;&nbsp;&nbsp;&nbsp;<input
			id="stb" type="text"> &nbsp;&nbsp;&nbsp;&nbsp;
				
		        <a id="add" href="#" style="width: 100px; height: 30px; float: right;"
			onclick="tianjia()">添加</a>
	</div>
	<div id="addqn">
	   	<div id="tt">
		<div title="客观题">
               <div class="zhubox"><input id="tm" type="text" style="height:80px;"></div>   
               <div class="zhubox"><input id="axx" type="text"></div>
               <div class="zhubox"><input id="bxx" type="text"></div>
               <div class="zhubox"><input id="cxx" type="text"></div>
               <div class="zhubox"><input id="dxx" type="text"></div>
               <div class="zhubox"><input id="daan" type="text"></div>
               <div class="zhubox" style="text-align: center"><a id="kgsave"onclick="kgsave()">保存</a><a id="kgcode" style="display:none;">1</a></div>
		</div>
		<div title="主观题">
		<div class="zhubox"><input id="kgtm" type="text" style="width:200px;height:100px;"></div>  
		 <div class="zhubox" style="text-align: center"><a id="zgsave"onclick="zgsave()">保存</a><a id="zgcode" style="display:none;">2</a></div>
		</div>
	</div>
	</div>
	<div id="doqn" style="text-align: center;line-height:150px">
         <a id="qaupdate"  onclick="qaupdate()">修改</a>
         &nbsp;&nbsp;&nbsp;&nbsp;
	     <a id="qadelete"  onclick="qadelete()">删除</a>
	   
	</div>
		<div id="updateqn">
	   	<div id="uptt">
		<div title="客观题">
               <div class="zhubox"><input id="uptm" type="text" style="height:80px;"></div>   
               <div class="zhubox"><input id="upaxx" type="text"></div>
               <div class="zhubox"><input id="upbxx" type="text"></div>
               <div class="zhubox"><input id="upcxx" type="text"></div>
               <div class="zhubox"><input id="updxx" type="text"></div>
               <div class="zhubox"><input id="updaan" type="text"></div>
               <div class="zhubox" style="text-align: center"><a id="upkgsave"onclick="upkgsave()">保存</a></div>
		</div>
		<div title="主观题">
		<div class="zhubox"><input id="upkgtm" type="text" style="width:200px;height:100px;"></div>  
		 <div class="zhubox" style="text-align: center"><a id="upzgsave"onclick="upzgsave()">保存</a></div>
		</div>
	</div>
	</div>
</body>
</html>