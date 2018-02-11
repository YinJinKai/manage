//得到当前日期
formatterDate = function(date) {
var day = date.getDate() > 9 ? date.getDate() : "0" + date.getDate();
var month = (date.getMonth() + 1) > 9 ? (date.getMonth() + 1) : "0"
+ (date.getMonth() + 1);
return date.getFullYear() + '-' + month + '-' + day;
};
$(document).ready(function(){
	$('#nowbtn').linkbutton({    
	    iconCls: 'icon-add'
	    
	}); 
 
//班级联动查询
	$('#classNumber').combobox({
		url: project.name+ 'leave/adminSelectClass.do',
		valueField: 'classCode',
		textField: 'className',
		   onSelect: function(rec){    
	            var url = project.name+ 'leave/adminSelectClassStName.do?classCode='+rec.classCode;    
	            $('#username').combobox('reload', url);    
	        }
	});
	$('#username').combobox({
		valueField: 'userName',
		textField: 'trueName'
	});

	//开始日期
	$('#startTime').datebox({
		currentText:'Today'
	});
	$('#startTime').datebox('setValue', formatterDate(new Date()));
	//结束日期
	$('#endTime').datebox({
		currentText:'Today'
	});  
	$('#endTime').datebox('setValue', formatterDate(new Date()));

//查询
$('#btn').linkbutton({    
    iconCls: 'icon-search',
    right:0
}); 
$('#gradebtn').linkbutton({
	iconCls: 'icon-ok'
});
$('#tablebtn').linkbutton({
	iconCls: 'icon-ok'
});
$('#uprole').combobox({
	
	valueField: 'value',
	textField: 'label',
	data: [{
		label: '请假',
		value: '2'
	}]
});
$('#dd').dialog({
	title: '修改信息',
	width: 350,
	height: 350,
	closed: true,
	cache: false,
	modal: true
});
$('#statTable').dialog({
	title: '考勤统计',
	width: 700,
	height: 450,
	closed: true,
	cache: false,
	modal: true
});
//主表
var startTime=$("#startTime").val();
var endTime=$("#endTime").val();
$('#dg').datagrid({    
  queryParams: {
	  startTime:startTime,
	  endTime: endTime
  },
url:project.name+'attendance/adminloadinfor.do', 
fit:true,
fitColumns:true,
toolbar:'#menu',
striped:true,
ctrlSelect: true,
pagination: true,
pageSize:5,
pageList:[5,10,15,25,30],
columns:[[ 
	{field:'id',title:'Id',width:100}, 
	{field:'trueName',title:'姓名',width:100}, 
    {field:'time',title:'日期',width:100},    
    {field:'oneCheck',title:'早晨',width:100},    
    {field:'twoCheck',title:'午饭前',width:100},    
    {field:'threeCheck',title:'午饭后',width:100},    
    {field:'fourCheck',title:'晚饭前',width:100}, 
    {field:'fiveCheck',title:'晚饭后',width:100},   
    {field:'sixCheck',title:'晚自习',width:100},
    {field:'score',title:'成绩',width:100}
]]    
});
$('#dg').datagrid({
	onClickRow : function(index, row) {
		showbox();
	}
});
});

//查询
function searcha(){
	var classNumber=$('#classNumber').combobox('getValue');
	var username=$('#username').combobox('getValue');
	var startTime=$("#startTime").val();
	var endTime=$("#endTime").val();
	$('#dg').datagrid('load',{
		userName:username,
		endTime:endTime,
		startTime:startTime,
	  classNumber:classNumber,
	  userName:username
	});
}
function creatNow() {
	$.ajax({
		url:project.name+'attendance/createAll.do',
		data : {
		},
		contentType : "application/x-www-form-urlencoded;charset=utf-8",
		dataType : "json",
		success : function(data) {
			if(data=="1"){
				$('#dg').datagrid('reload'); 
				return;
			}
			alert("未知错误!");
				
		}


	});
	
	
	
	
	
		
}
function showbox() {
	$('#dd').window('open');
}
function showtable() {
	$('#statTable').window('open');
}
function tijiao() {
	var row=$('#dg').datagrid('getSelected');
	var id=row.id;
    if(id==""){
    	return;
    }
	var qingjia=$("#uprole").val();
	if(qingjia==""){
		return;
	}
	alert(qingjia);
	$.ajax({
		url:project.name+'attendance/updatestateTime.do',
		data : {
			id:id,
			qingjia:qingjia
		},
		contentType : "application/x-www-form-urlencoded;charset=utf-8",
		dataType : "json",
		success : function(data) {
			$('#dd').window('close');
				$('#dg').datagrid('reload'); 
				
			
				
		}


	});
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}