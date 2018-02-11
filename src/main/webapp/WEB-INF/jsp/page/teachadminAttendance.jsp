<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生考勤详情</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/lib/css/teacher_attendance.css" />
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
<script src="<%=request.getContextPath()%>/lib/js/teacher_attendance.js"
	type="text/javascript" charset="utf-8"></script>
	<script src="<%=request.getContextPath()%>/lib/js/echarts.js"
	type="text/javascript" charset="utf-8"></script>
</head>
<body>
	<table id="dg"></table>
	<div id="menu" style="padding: 10px 5px;width:100%; text-align: center;">
	 班级:&nbsp;<input id="classNumber" type="text"style="width: 100px;">&nbsp;&nbsp;&nbsp;&nbsp;
	 学生:&nbsp;<input id="username" type="text" style="width: 100px">&nbsp;&nbsp;&nbsp;&nbsp;
	时间范围查询:&nbsp;<input id="startTime" type="text" name="startTime"></input>--<input id="endTime" type="text" name="endTime"></input>&nbsp;&nbsp;
	 <a id="btn" href="#" onclick="searcha()" >查询</a>
	 <a id="tablebtn"  onclick="showtable()" style="margin-left:60px;" >查看考勤统计</a>
	  <a id="nowbtn" href="#" onclick="creatNow()" style="margin-left:100px;" >创建今天的考勤</a>
	</div>
	<div id="dd">
			<div class="upbox"><a class="title titler">处理:</a><input id="uprole" type="text"></div>
			<div class="gradebtn levelbox">
				<a id="gradebtn" onclick="tijiao()">确定</a>
			</div>
		</div>
	<div id="statTable">
	   <div id="main" style="width: 680px;height:400px; margin: 0 auto;"></div>
	</div>
	 <script type="text/javascript">
// 基于准备好的dom，初始化echarts实例
var myChart = echarts.init(document.getElementById('main'));

// 指定图表的配置项和数据
var option = {
	    title : {
	        text: '考勤统计',
	        subtext: 'by 尹金凯'
	    },
	    tooltip : {
	        trigger: 'axis'
	    },
	    legend: {
	        data:['缺勤','正常出勤','请假']//三个状态【请假 缺勤 正常出勤】
	    },
	    toolbox: {
	        show : true,
	        feature : {
	            mark : {show: true},
	            dataView : {show: true, readOnly: false},
	            magicType : {show: true, type: ['line', 'bar']},
	            restore : {show: true},
	            saveAsImage : {show: true}
	        }
	    },
	    calculable : true,
	    xAxis : [
	        {
	            type : 'category',
	            data : [] //日期
	        }
	    ],
	    yAxis : [
	        {
	            type : 'value'
	        }
	    ],
	    series : [
	        {
	            name:'缺勤',
	            type:'bar',
	            data:[]//每个状态的数值
	         
	       
	        },
	        {
	            name:'正常出勤',
	            type:'bar',
	            data:[]
	        }
	        ,
	        {
	            name:'请假',
	            type:'bar',
	            data:[]
	           
	        }
	    ]
	};
	       

// 使用刚指定的配置项和数据显示图表。
      myChart.setOption(option);
      myChart.showLoading();// 加载动画
      $.ajax({
          url:"<%=request.getContextPath()%>/attendance/selecttable.do",
          type:"POST",
          data: 'action=echarts',
          dataType:"json",
          contentType: 'application/x-www-form-urlencoded; charset=UTF-8',//防止乱码
          success:function(data){
        	  myChart.hideLoading();
        	  var option = {
        			    title : {
        			        text: '考勤统计',
        			        subtext: 'by 尹金凯'
        			    },
        			    tooltip : {
        			        trigger: 'axis'
        			    },
        			    legend: {
        			        data:['缺勤','正常出勤','请假']//三个状态【请假 缺勤 正常出勤】
        			    },
        			    toolbox: {
        			        show : true,
        			        feature : {
        			            mark : {show: true},
        			            dataView : {show: true, readOnly: false},
        			            magicType : {show: true, type: ['line', 'bar']},
        			            restore : {show: true},
        			            saveAsImage : {show: true}
        			        }
        			    },
        			    calculable : true,
        			    xAxis : [
        			        {
        			            type : 'category',
        			            data :data.timeList//日期
        			        }
        			    ],
        			    yAxis : [
        			        {
        			            type : 'value'
        			        }
        			    ],
        			    series : [
        			        {
        			            name:'缺勤',
        			            type:'bar',
        			            data:data.queqinlist//每个状态的数值
        			        },
        			        {
        			            name:'正常出勤',
        			            type:'bar',
        			            data:data.zhengchanglist
        			        }
        			        ,
        			        {
        			            name:'请假',
        			            type:'bar',
        			            data:data.qingjialist
        			        }
        			    ]
        			};
        	// 使用刚指定的配置项和数据显示图表。
              myChart.setOption(option);
          }
      });
</script>
</body>
</html>