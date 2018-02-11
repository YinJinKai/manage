<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/lib/css/updateMenu.css" />
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
<script src="<%=request.getContextPath()%>/lib/js/updateMenu.js"
	type="text/javascript" charset="utf-8"></script>
</head>
<body class="desk" style="background-image: url(<%=request.getContextPath()%>/lib/img/start4.jpg);">

   <div class="under_menu">
		<div class="in_menu">
		</div>

	</div>
	<!--菜单选项-->
	<div class="menu_div">
		<div class="app2" onclick="addMenu()">
			<img class="icon" src="/manage/lib/img/updateadd.png">
		</div>
		<div class="app2" onclick="updateMenu()">
			<img class="icon" src="/manage/lib/img/updateup.png">
		</div>
		<div class="app2" onclick="deletMenu()">
			<img class="icon" src="/manage/lib/img/updatetrash.png">
		</div>
		<div class="app2" onclick="frash()">
			<img class="icon" src="/manage/lib/img/couldflash.png">
		</div>
	</div>
	<div class="qiandao_mask" id="qiandao_mask"></div>
	<div class="loginbody">
		<div class="App_desk" id="appDesk"></div>
	</div>
    <div id="updatemenu">
       <a id="upid" style="display: none"></a>
       <div class="dobox"><a class="boxtitle">菜单名:</a><a style="float: right"><input id="uptb" type="text" ></a></div>
        <div class="dobox"><a class="boxtitle">修改链接绑定:</a><a style="float: right"><input id="upurl" type="text" ></a></div>
        <div class="dobox"><a class="boxtitle">修改权限:</a><a style="float: right"><input id="upqx" type="text"></a></div>
        <div class="dobox dobtn"><a id="upbtn" onclick="upsave()" >保存设置</a></div>
    </div>
    <div id="addmenu">
       <div class="dobox"><a class="boxtitle">菜单名:</a><a style="float: right"><input id="addtb" type="text" ></a></div>
       <div class="dobox"><a class="boxtitle">链接绑定:</a><a style="float: right"><input id="addurl" type="text" ></a></div>
       <div class="dobox"><a class="boxtitle">设置权限:</a><a style="float: right"><input id="addqx" type="text"></a></div>
       <div class="dobox dobtn"><a id="addbtn" onclick="addsave()">添加</a></div>
    </div>
</body>

</html>