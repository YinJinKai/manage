<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert here</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/lib/css/adminClass.css" />
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
<script src="<%=request.getContextPath()%>/lib/js/adminClass.js"
	type="text/javascript" charset="utf-8"></script>
	<script src="<%=request.getContextPath()%>/lib/js/echarts.js"
	type="text/javascript" charset="utf-8"></script>
</head>
<body>
	<table id="dg"></table>
	<div id="menu" style="padding: 10px 5px; text-align: center">
		<a id="btn" href="#" onclick="addclass()">添加班级</a> <a id="countbtn"
			style="margin-left: 250px" onclick="selectcounttable()">查看班级人数分布</a>
	</div>
	<div id="dd">
		<div class="datibox">
			<a id="datibtn" onclick="checkcount()" style="width: 110px;">查看班级人数</a>
		</div>
		<div class="datibox">
			<a id="checkbtn" onclick="upclass()" style="width: 110px;">修改班级信息</a>
		</div>
		<div class="datibox">
			<a id="closebtn" onclick="closebtn()" style="width: 110px;">取消</a>
		</div>
	</div>
	<div id="da">
		<div class="upbox">
			<a class="title titler">班级:</a><input id="upusername" type="text">
		</div>
		<div class="upbox">
			<a class="title titler">讲师:</a><input id="uprole" type="text">
		</div>
		<div class="gradebtn levelbox">
			<a id="gradebtn" onclick="addtijiao()">确定</a>
		</div>
	</div>
	<div id="upda">
	   <div class="upbox">
			<a class="title titler">班级:</a><input id="upupusername" type="text">
		</div>
		<div class="upbox">
			<a class="title titler">讲师:</a><input id="upuprole" type="text">
		</div>
		<div class="gradebtn levelbox">
			<a id="upgradebtn" onclick="uptijiao()">确定</a>
		</div>
	</div>
	<div id="ccount">
	   <div class="countInfor">
	       <a id="cninfor"></a>
	   </div>
	</div>
<!-- 统计框 -->
	<div id="statisticsbox">
	  <div id="main" style="width: 680px;height:400px; margin: 0 auto;"></div>
	</div>
	 <script type="text/javascript">
// 基于准备好的dom，初始化echarts实例
var myChart = echarts.init(document.getElementById('main'));

// 指定图表的配置项和数据
var option = {
	    title : {
	        text: '班级人数统计图',
	        subtext: 'by 尹金凯',
	        x:'center'
	    },
	    tooltip : {
	        trigger: 'item',
	        formatter: "{a} <br/>{b} : {c} ({d}%)"
	    },
	    legend: {
	        x : 'center',
	        y : 'bottom',
	        data:[] //班级名
	    },
	    toolbox: {
	        show : true,
	        feature : {
	            mark : {show: true},
	            dataView : {show: true, readOnly: false},
	            magicType : {
	                show: true, 
	                type: ['pie', 'funnel']
	            },
	            restore : {show: true},
	            saveAsImage : {show: true}
	        }
	    },
	    calculable : true,
	    series : [
	        {
	            name:'半径模式',
	            type:'pie',
	            radius : [20, 110],
	            center : ['25%', 200],
	            roseType : 'radius',
	            width: '40%',       // for funnel
	            max: 40,            // for funnel
	            itemStyle : {
	                normal : {
	                    label : {
	                        show : false
	                    },
	                    labelLine : {
	                        show : false
	                    }
	                },
	                emphasis : {
	                    label : {
	                        show : true
	                    },
	                    labelLine : {
	                        show : true
	                    }
	                }
	            },
	            data:[]//各班人数值
	        },
	        {
	            name:'面积模式',
	            type:'pie',
	            radius : [30, 110],
	            center : ['75%', 200],
	            roseType : 'area',
	            x: '50%',               // for funnel
	            max: 40,                // for funnel
	            sort : 'ascending',     // for funnel
	            data:[] //各班人数值
	        }
	    ]
	};
	       

// 使用刚指定的配置项和数据显示图表。
      myChart.setOption(option);
      myChart.showLoading();// 加载动画
      $.ajax({
          url:"<%=request.getContextPath()%>/userclass/statistics.do",
          type:"POST",
          data: 'action=echarts',
          dataType:"json",
          contentType: 'application/x-www-form-urlencoded; charset=UTF-8',//防止乱码
          success:function(data){
//               alert(data.text)
              myChart.hideLoading();
              var option = {
            		    title : {
            		        text: '班级人数统计图',
            		        subtext: 'by 尹金凯',
            		        x:'center'
            		    },
            		    tooltip : {
            		        trigger: 'item',
            		        formatter: "{a} <br/>{b} : {c} ({d}%)"
            		    },
            		    legend: {
            		        x : 'center',
            		        y : 'bottom',
            		        data:data.className //班级名
            		    },
            		    toolbox: {
            		        show : true,
            		        feature : {
            		            mark : {show: true},
            		            dataView : {show: true, readOnly: false},
            		            magicType : {
            		                show: true, 
            		                type: ['pie', 'funnel']
            		            },
            		            restore : {show: true},
            		            saveAsImage : {show: true}
            		        }
            		    },
            		    calculable : true,
            		    series : [
            		        {
            		            name:'半径模式',
            		            type:'pie',
            		            radius : [20, 110],
            		            center : ['25%', 200],
            		            roseType : 'radius',
            		            width: '40%',       // for funnel
            		            max: 40,            // for funnel
            		            itemStyle : {
            		                normal : {
            		                    label : {
            		                        show : false
            		                    },
            		                    labelLine : {
            		                        show : false
            		                    }
            		                },
            		                emphasis : {
            		                    label : {
            		                        show : true
            		                    },
            		                    labelLine : {
            		                        show : true
            		                    }
            		                }
            		            },
            		            data:data.classCountValue//各班人数值
            		        },
            		        {
            		            name:'面积模式',
            		            type:'pie',
            		            radius : [30, 110],
            		            center : ['75%', 200],
            		            roseType : 'area',
            		            x: '50%',               // for funnel
            		            max: 40,                // for funnel
            		            sort : 'ascending',     // for funnel
            		            data:data.classCountValue//各班人数值
            		        }
            		    ]
            		}

              // 使用刚指定的配置项和数据显示图表。
              myChart.setOption(option);
          }
      });
</script>
</body>
</html>