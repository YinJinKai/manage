$(document).ready(function(){
	//主表
	$('#dg').datagrid({    
    url:project.name+'attendance/loadinfor.do', 
    fit:true,
    fitColumns:true,
    toolbar:'#menu',
    striped:true,
    columns:[[    
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

//文本框
$('#classNumber').textbox({    
    readonly:true
});

$('#username').textbox({    
     readonly:true
});

//下拉框
$('#time').combobox({    
    url:project.name+'attendance/loadTime.do',    
    limitToList:true,
    valueField:'timeValue',    
    textField:'timeText'   
});  

//查询
$('#btn').linkbutton({    
    iconCls: 'icon-search',
    right:0
}); 
//加载学生信息
loadUserInfor();
});

function loadUserInfor(){
	  $.ajax({
			url:project.name+'user/loadinfor.do',
			data : {
		    
			},
			contentType : "application/x-www-form-urlencoded;charset=utf-8",
			dataType : "json",
			success : function(data) {
				$('#classNumber').textbox('setValue',data[0].className);
				$('#username').textbox('setValue',data[0].trueName)
				return;
			}

		});
}
//查询
function searcha(){
	var time=$('#time').combobox('getValue');
	$('#dg').datagrid('load',{
	  time:time
	});
//	$('#dg').datagrid('getData');
}