<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/lib/css/leave.css" />
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
<script src="<%=request.getContextPath()%>/lib/js/leave.js"
	type="text/javascript" charset="utf-8"></script>
</head>
<body class="easyui-layout">
		<div class="add_new" data-options="region:'south',collapsible: false" style="width: 100%;height: 60px;background:#eee;text-align: center;">
			<a id="btn" href="#" onclick="showAdd()" style="width: 100px;height: 30px;margin-top: 14px;">新的申请</a>
		</div>
		<div data-options="region:'west',collapsible: false" style="width:180px;overflow: hidden;background:#eee;">
			<ul class="menubox">
				<li><a id="myleave"  onclick="selectLeave()">我的申请</a></li>
				<li><a id="leaving"  onclick="selectLeave(1)">老师审批中</a></li>
				<li><a id="adminleaving"  onclick='selectLeave("t2")'>教务审批中</a></li>
				<li><a id="noleave"      onclick='selectLeave("t1")'>老师驳回的申请</a></li>
				<li><a id="adminnoleave"  onclick='selectLeave("a1")'>教务驳回的申请</a></li>
				<li><a id="passleave"  onclick='selectLeave("a2")'>申请已通过</a></li>
			</ul>
		</div>
		<div data-options="region:'center',collapsible: false">
			<table id="dg"></table>
		</div>

		<div class="loginbody_mask" id="loginbody_mask">
			<div id="p">
				<div class="addbox startTime">
					<a class="lable">开始日期:</a>
					<div class="inbox">
						<input id="startTime" type="text" name="startTime" style="width:300px; height: 30px;"></input>
					</div>

				</div>

				<div class="addbox endTime">
					<a class="lable">结束日期:</a>
					<div class="inbox">
						<input id="endTime" type="text" name="endTime" style="width:300px; height: 30px;"></input>
					</div>

				</div>
				<div class="addbox why">
					<a class="lable">事由:</a>
					<div class="inbox">
						<input id="why" type="text" name="why" style="width:300px; height: 120px;"></input>
					</div>

				</div>
				<div class="addbox">
					<div class="leave_btn">
						<a id="addclose" onclick="closeAdd()" href="#">取消</a>
						<a id="addsave" onclick="save()" href="#">保存</a>
					</div>

				</div>
			</div>
		</div>
	</body>
</html>