<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/lib/css/questionindex.css" />
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
<script src="<%=request.getContextPath()%>/lib/js/questionindex.js"
	type="text/javascript" charset="utf-8"></script>
</head>
<body class="easyui-layout">

	<div data-options="region:'west',collapsible: false" style="width: 180px; overflow: hidden; background: #eee;">
		<ul class="menubox">
			<li><a id="addnewj" onclick="showtabs('创建问卷','<%=request.getContextPath()%>/question/createquestion.shtml')">创建问卷/问卷匹配题目</a>
			</li>
			<li><a id="selectjk" onclick="showtabs('问卷库','<%=request.getContextPath()%>/qnqr/select.shtml')">查看问卷库</a>
			</li>
			<li><a id="selecttk" onclick="showtabs('题库','<%=request.getContextPath()%>/qa/select.shtml')">查看题库</a>
			</li>
			<li><a id="teachj" onclick="showtabs('评分','<%=request.getContextPath()%>/answer/select.shtml')">答卷评分</a>
			</li>
		</ul>
	</div>
	<div data-options="region:'center',collapsible: false">
		<div id="tt">
			<div title="首页" style="padding: 20px; display: none;">
				<div class="qnindex">
				   <a>问&nbsp;&nbsp;&nbsp;卷&nbsp;&nbsp;&nbsp;管&nbsp;&nbsp;&nbsp;理</a>
				   <div></div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>