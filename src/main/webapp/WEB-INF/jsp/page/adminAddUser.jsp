<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="<%=request.getContextPath()%>/lib/js/jquery-3.2.1.min.js"
	type="text/javascript" charset="utf-8"></script>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/lib/css/adduser.css" />
<script src="<%=request.getContextPath()%>/lib/js/tools.js"
	type="text/javascript" charset="utf-8"></script>
<script src="<%=request.getContextPath()%>/lib/js/adduser.js"
	type="text/javascript" charset="utf-8"></script>
		<script src="<%=request.getContextPath()%>/lib/js/echarts.js"
	type="text/javascript" charset="utf-8"></script>
</head>
<body>
	<div class="download">
	    <div class="ta3"><a onclick="showtongji()">用户统计表<em></em></a></div>
		<div class="ta1">
			<a href="<%=request.getContextPath()%>/addUser/export.do">下载用户表 <em></em></a>
		</div>
		<div class="ta2">
			<a onclick="showexport()" >导出用户表 <em></em></a>
			<div id="databox">
			   <a class="databox" href="<%=request.getContextPath()%>/addUser/exportAll.do">全部用户<em></em></a>
			   <a class="databox" href="<%=request.getContextPath()%>/addUser/exportAll.do?roleCode=1">学生表<em></em></a>
			   <a class="databox" href="<%=request.getContextPath()%>/addUser/exportAll.do?roleCode=2">教师表<em></em></a>
			   <a class="databox" href="<%=request.getContextPath()%>/addUser/exportAll.do?roleCode=3">教务表<em></em></a>
			</div>
		</div>
	</div>
	<div class="upload">
		<div class="uptitle">上传用户表</div>
		<div class="upbox">
			<form action="<%=request.getContextPath()%>/addUser/import.do"	method="post" enctype="multipart/form-data"	onsubmit="return check();">
				<a class="fb"> <input class="filename" id="filename" type="text" value="选择文件" /> <input class="change" id="excel_file"
					type="file" name="filename" accept="xlsx" size="30" onchange="f1()" /> <input
					class="fbbtn" id="excel_button" type="submit" value="上传Excel" />
                </a>
            </form>
		</div>
		  
		<div class="tongjibiao">
		  <em class="closetongji" onclick="closetongji()"></em>
		    <div id="main" style="width:400px;height:400px; margin: 0 auto;"></div>
		</div>
	</div>

	</div>
	<div class="bg-layer"></div>
	 <script type="text/javascript">
// 基于准备好的dom，初始化echarts实例
var myChart = echarts.init(document.getElementById('main'));
var dataStyle = {
	    normal: {
	        label: {show:false},
	        labelLine: {show:false}
	    }
	};
	var placeHolderStyle = {
	    normal : {
	        color: 'rgba(0,0,0,0)',
	        label: {show:false},
	        labelLine: {show:false}
	    },
	    emphasis : {
	        color: 'rgba(0,0,0,0)'
	    }
	};
// 指定图表的配置项和数据
var option = {
	    title: {
	        text: '用户人数',
	        subtext: 'by  尹金凯',
	        x: 'center',
	        y: 'center',
	        itemGap: 20,
	        textStyle : {
	            color : 'rgba(30,144,255,0.8)',
	            fontFamily : '微软雅黑',
	            fontSize : 35,
	            fontWeight : 'bolder'
	        }
	    },
	    tooltip : {
	        show: true,
	        formatter: "{a} <br/>{b} : {c} ({d}%)"
	    },
	    legend: {
	        orient : 'vertical',
	        x : document.getElementById('main').offsetWidth / 2,
	        y : 45,
	        itemGap:12,
	        textStyle : {
	            color : 'rgba(255,255,255,0.8)',
	            fontFamily : '微软雅黑',
	            fontSize : 15
	        },
	        data:[]//用户种类
	    },
	    toolbox: {
	        show : true,
	        feature : {
	            mark : {show: true},
	            dataView : {show: true, readOnly: false},
	            restore : {show: true},
	            saveAsImage : {show: true}
	        }
	    },
	    series : [
	        {
	            name:'1',
	            type:'pie',
	            clockWise:false,
	            radius : [125, 150],
	            itemStyle : dataStyle,
	            data:[]
	        },
	        {
	            name:'2',
	            type:'pie',
	            clockWise:false,
	            radius : [100, 125],
	            itemStyle : dataStyle,
	            data:[]
	        },
	        {
	            name:'3',
	            type:'pie',
	            clockWise:false,
	            radius : [75, 100],
	            itemStyle : dataStyle,
	            data:[]
	        }
	    ]
	};
	       

// 使用刚指定的配置项和数据显示图表。
      myChart.setOption(option);
      myChart.showLoading();// 加载动画
      $.ajax({
          url:"<%=request.getContextPath()%>/user/statistics.do",
          type:"POST",
          data: 'action=echarts',
          dataType:"json",
          contentType: 'application/x-www-form-urlencoded; charset=UTF-8',//防止乱码
          success:function(data){
//               alert(data.text)
              myChart.hideLoading();
              var option = {
            		    title: {
            		    	text: '用户人数',
            		        subtext: 'by  尹金凯',
            		        x: 'center',
            		        y: 'center',
            		        itemGap: 20,
            		        textStyle : {
            		            color : 'rgba(30,144,255,0.8)',
            		            fontFamily : '微软雅黑',
            		            fontSize : 35,
            		            fontWeight : 'bolder'
            		        }
            		    },
            		    tooltip : {
            		        show: true,
            		        formatter: "{a} <br/>{b} : {c} ({d}%)"
            		    },
            		    legend: {
            		        orient : 'vertical',
            		        x : 200,
            		        y : 45,
            		        itemGap:12,
            		        textStyle : {
            		            color : 'rgba(255,255,255,0.8)',
            		            fontFamily : '微软雅黑',
            		            fontSize : 15
            		        },
            		        data:data.userCountInfor
            		    },
            		    toolbox: {
            		        show : true,
            		        feature : {
            		            mark : {show: true},
            		            dataView : {show: true, readOnly: false},
            		            restore : {show: true},
            		            saveAsImage : {show: true}
            		        }
            		    },
            		    series : [
            		        {
            		            name:'1',
            		            type:'pie',
            		            clockWise:false,
            		            radius : [125, 150],
            		            itemStyle : dataStyle,
            		            data:data.xuesheng
            		            
            		        },
            		        {
            		            name:'2',
            		            type:'pie',
            		            clockWise:false,
            		            radius : [100, 125],
            		            itemStyle : dataStyle,
            		            data:data.jiangshi
            		        },
            		        {
            		            name:'3',
            		            type:'pie',
            		            clockWise:false,
            		            radius : [75, 100],
            		            itemStyle : dataStyle,
            		            data:data.jiaowu
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