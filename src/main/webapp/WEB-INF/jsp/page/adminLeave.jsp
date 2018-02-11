<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/lib/css/adminleave.css" />
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
<script src="<%=request.getContextPath()%>/lib/js/adminleave.js"
	type="text/javascript" charset="utf-8"></script>
</head>
<body class="easyui-layout">
		<div class="add_new" data-options="region:'south',collapsible: false">
		 <div class="inadd">
		    <a id="btn" href="#" onclick="pass('t2')" style="width: 100px;height: 30px;">通过</a>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a id="btn2" href="#" onclick="pass('t1')" style="width: 100px;height: 30px;">驳回</a>
		 
		 </div>
			
		</div>
		<div data-options="region:'west',collapsible: false" style="width:180px;overflow: hidden;background:#eee;">
			<ul class="menubox">
				<li>
					<a id="myleave" onclick="selectLeave(1)">待审批的申请</a>
				</li>
				<li>
					<a id="noleave" onclick="selectLeave('t1')">您已驳回的申请</a>
				</li>
				<li>
					<a id="adminnoleave" onclick="selectLeave('a1')">教务已驳的回申请</a>
				</li>
				<li>
					<a id="teacherpass" onclick="selectLeave('t2')">您通过的申请</a>
				</li>
				<li>
					<a id="passleave" onclick="selectLeave('a2')">教务已通过的申请</a>
				</li>
			</ul>
		</div>
		<div data-options="region:'center',collapsible: false">
			<table id="dg"></table>
		</div>

		<div class="loginbody_mask" id="loginbody_mask"></div>
		<div id="dgtoolbar" style="width:100%;height:79px;background:#eee;">
			<div class="intool">
               <span class="search timebox">
                             时间范围查询:&nbsp;<input id="startTime" type="text" name="startTime"></input>--<input id="endTime" type="text" name="endTime"></input>&nbsp;&nbsp;
				<a id="timeSearch" onclick="searchTime()" href="#">查询</a>
               </span>
                <span  class="search classbox">
				班级:&nbsp;<input id="classNumber" type="text">&nbsp;&nbsp;
				姓名:&nbsp;<a id="firstLoad"></a><input id="username" name="dept" value="">&nbsp;&nbsp;
				</span>
				

			</div>

		</div>
		<div id="limitPage" style="background:#efefef;border:1px solid #ccc;"></div>
	</body>

</html>