<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户登录</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/lib/css/login.css" />
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
<script src="<%=request.getContextPath()%>/lib/js/login.js"
	type="text/javascript" charset="utf-8"></script>
	<script src="<%=request.getContextPath()%>/lib/js/echarts.js"
	type="text/javascript" charset="utf-8"></script>

</head>

<body class="desk"
	style="background-image: url(<%=request.getContextPath()%>/lib/img/start1.jpg);">
        <%
            Integer hitsCount = (Integer) application.getAttribute("hitCounter");
            if (hitsCount == null || hitsCount == 0) {
                hitsCount = 1;
            } else {
                hitsCount += 1;
            }
            application.setAttribute("hitCounter", hitsCount);
        %>
	<div class="header">
		<div class="container">
			<div class="c-nav">
				<a>信息管理系统</a>
			</div>
			<div id="nowTime" class="nowTime"></div>
	
		</div>
		
	</div>
	<div class="under_menu">
		<div class="in_menu"></div>
	</div>
	<!--菜单选项-->
	<div class="menu_div">
	   	<div class="app2 menuCon" onclick="showCalen()">
			<img class="icon" src="/manage/lib/img/cal.png">
		</div>
		<div class="app2 menuCon" onclick="">
			<img class="icon" src="/manage/lib/img/Settings.png">
		</div>
	
	</div>
	<!-- 	桌面 -->
	<div class="App_desk" id="appDesk">
		<div class="app2" onclick="login(1)">
			<img class="icon" src="/manage/lib/img/ph.png">
			<div class="title">学生登录</div>
		</div>
		<div class="app2" onclick="login(2)">
			<img class="icon" src="/manage/lib/img/game.png">
			<div class="title">教师登录</div>
		</div>
		<div class="app2" onclick="login(3)">
			<img class="icon" src="/manage/lib/img/me.png">
			<div class="title">教务登录</div>
		</div>
	
		 <div id="timePanel">
		    <div id="calendar" style="width:100%;height:100%;"></div>
		 </div>
		 	<div class="mapBox">
		   <div class="closebox" onclick="showtable()"></div>
		   <div class="inbox">
		      <div id="main" class="main">
		     			   
		   </div>
		   </div>
		   
		</div>
	
	</div>
	<!--     登录 -->
	<div class="loginbody_mask" id="loginbody_mask"></div>
	  <div class="loading"></div>
	<div class="login_box" id="loginBox">
      
		<div class="login_header"></div>
		<div class="login_input">
			<input id="username" name="userName" type="text">
		</div>
		<div class="error" id="userErrorBox">
			<em class="error_pc"></em><span id="user_error" class="error_font"></span>
		</div>
		<div class="login_input">
			<input id="password" name="password" type="text">
		</div>
		<div class="error" id="passwordErrorBox">
			<em class="error_pc"></em><span id="password_error"
				class="error_font"></span>
		</div>
		<div class="toorBar">
			<a class="user-close" id="userClose" onclick="switchUser()"></a> <a
				class="user-login" id="userlogin" onclick="userLogin()"></a>
		</div>
	</div>
    
<script type="text/javascript">
// 基于准备好的dom，初始化echarts实例
var myChart = echarts.init(document.getElementById('main'));

// 指定图表的配置项和数据
var option = {
	    tooltip : {
	        formatter: "{a} <br/>{b} : {c}%"
	    },
	    toolbox: {
	        show : true,
	        feature : {
	            mark : {show: true},
	            restore : {show: true},
	            saveAsImage : {show: true}
	        }
	    },
	    series : [
	        {   show : true,
	            name:'',
	            type:'gauge',
	            detail : {formatter:'{value}位'},
	            data:[{"name":"浏览量","value":"<%=hitsCount%>"}],
	            title:{
	            	textStyle: {
		                color: '#333',
		                fontSize : 10
		            }
	            }
	        }
	    ]
	};
	       

// 使用刚指定的配置项和数据显示图表。
      myChart.setOption(option);
//       myChart.showLoading();// 加载动画
//       $.ajax({
<%--           url:"<%=request.getContextPath()%>/user/getlogininguser.do", --%>
//           type:"POST",
//           data: 'action=echarts',
//           dataType:"json",
//           contentType: 'application/x-www-form-urlencoded; charset=UTF-8',//防止乱码
//           success:function(data){
// //               alert(data.text)
//               myChart.hideLoading();
//               var option = {
//             		    tooltip : {
//             		        formatter: "{a} <br/>{b} : {c}%"
//             		    },
//             		    toolbox: {
//             		        show : true,
//             		        feature : {
//             		            mark : {show: true},
//             		            restore : {show: true},
//             		            saveAsImage : {show: true}
//             		        }
//             		    },
//             		    series : [
//             		        {
//             		        	show : true,
//             		            name:'在线人数',
//             		            type:'gauge',
//             		            detail : {formatter:'{value}位',
//             		            	textStyle: {
            		 
//             			                color: '#333',
//             			                fontSize : 10
//             			            }},
//             		            data:data.userdata,
            		            
//             		            title:{
//             		            	textStyle: {
            		            		
//                 		                color: '#333',
//                 		                fontSize : 10
//                 		            }
//             		            }
//             		        }
//             		    ]
//             		};

//               // 使用刚指定的配置项和数据显示图表。
//               myChart.setOption(option);
//           }
//       });
</script>
</body>
</html>