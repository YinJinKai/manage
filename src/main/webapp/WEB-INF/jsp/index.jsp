<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户信息管理界面</title>

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/lib/css/frame.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/lib/easyui/themes/black/easyui.css" />
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
<script src="<%=request.getContextPath()%>/lib/js/frame.js"
	type="text/javascript" charset="utf-8"></script>
	<script src="<%=request.getContextPath()%>/lib/js/echarts.js"
	type="text/javascript" charset="utf-8"></script>

</head>
<body class="desk"
	style="background-image: url(<%=request.getContextPath()%>/lib/img/start.jpg);">
	<div id="logintip" class="logintip">
	    <div class="closetip">
	        <em onclick="closeLogintip()"></em>
	    </div>
	    <div class="tipInfor">
	       <div class="tip1">
	          <a>欢迎</a>&nbsp;&nbsp;<a>${trueName}</a>&nbsp;&nbsp;<a>登录!</a>
	       </div>
	       <div class="tip2">
	           <em></em>
	       </div>
	    </div>
	</div>
	<div class="header">
		<a style="display: none" id="roleCode">${roleCode}</a>
		<div class="container">
			<div class="c-nav">
				<a>信息管理系统</a>	
			</div>
			<div id="nowTime" class="nowTime"></div>
			<div class="c-nav linfor" id="userinfor"></div>
		</div>
		
	</div>
	
	<div class="under_menu">
		<div class="in_menu">
			<div class="setting">
				<div class="dao">
					<a class="biao">设置</a>
				</div>
				<div class="dao daofirst" onclick="loginout()">
					<em class="loginout"></em><a class="dao_font">退出登录</a>
				</div>
			</div>
			<div id="mainbox" class="main">
			   <div id="main" style="width: 300px;height:300px; margin: 0 auto;"></div>
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
	            detail : {},
	            data:[],
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
      myChart.showLoading();// 加载动画
      $.ajax({
          url:"<%=request.getContextPath()%>/user/getlogininguser.do",
          type:"POST",
          data: 'action=echarts',
          dataType:"json",
          contentType: 'application/x-www-form-urlencoded; charset=UTF-8',//防止乱码
          success:function(data){
//               alert(data.text)
              myChart.hideLoading();
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
            		        {
            		        	show : true,
            		            name:'在线人数',
            		            type:'gauge',
            		            detail : {formatter:'{value}位',
            		            	textStyle: {
            		 
            			                color: '#333',
            			                fontSize : 10
            			            }},
            		            data:data.userdata,
            		            
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
          }
      });
</script>
			</div>
		</div>
   
	</div>
	<!--菜单选项-->
	<div class="menu_div">
		<div class="app2" onclick="setting()">
			<img class="icon" src="/manage/lib/img/Settings.png">
		</div>
	</div>
	<div class="qiandao_mask" id="qiandao_mask"></div>
	<div class="loginbody">
		<div class="App_desk" id="appDesk"></div>
		<div class="tcombo">
			<div class="dao">
				<a class="biao">签到</a>
			</div>
			<div class="dao">
				<a class="qian" id="qianTime"></a><a id="timecode"
					style="display: none"></a><a id="signed" style="display: none"></a><a
					class="qiand" id="qiand"><input id="qiandao"></a>
			</div>
		</div>
	</div>
	<div class="y_frame">

		<div id="p">
			<iframe src='' frameborder=0 scrolling='auto' style='width: 100%;' />
		</div>




	</div>
</body>
</html>