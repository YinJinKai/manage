$(document).ready(function(){
	//主表
	$('#dg').datagrid({    
    url:project.name + "userclass/selectAll.do", 
    fitColumns:true,
    striped: true,
    ctrlSelect: true,
    fit: true,
	border: 0,
    toolbar:'#menu',
    columns:[[    
        {field:'id',title:'Id',width:100},    
        {field:'className',title:'班级名',width:100},    
        {field:'classCode',title:'班级代码',width:100},    
        {field:'userName',title:'讲师账号',width:100},
        {field:'trueName',title:'讲师',width:100},
    ]]    
});  
	$('#upusername').textbox({    
        
	});
	$('#uprole').combobox({
		url:  project.name+ 'userclass/comboxteacher.do',
		valueField: 'userName',
		textField: 'trueName'
	});
	$('#gradebtn').linkbutton({
		iconCls: 'icon-ok'
	});
	$('#upupusername').textbox({    
        
	});
	$('#upuprole').combobox({
		url:  project.name+ 'userclass/comboxteacher.do',
		valueField: 'userName',
		textField: 'trueName'
	});
	$('#upgradebtn').linkbutton({
		iconCls: 'icon-ok'
	});

//查询
$('#btn').linkbutton({    
    iconCls: 'icon-add'   
}); 
//查看班级人数分布
$('#countbtn').linkbutton({    
    iconCls: 'icon-search'   
}); 
$('#datibtn').linkbutton({
	iconCls : 'icon-search'
});
$('#checkbtn').linkbutton({
	iconCls : 'icon-remove'
});
$('#closebtn').linkbutton({
	iconCls : 'icon-clear'
});
//点击行显示操作
$('#dd').dialog({
	title: '操作',
	width: 350,
	height: 350,
	closed: true,
	cache: false,
	modal: true
});
//添加框
$('#da').dialog({
	title: '添加',
	width: 350,
	height: 350,
	closed: true,
	cache: false,
	modal: true
});
//修改框
$('#upda').dialog({
	title: '修改',
	width: 350,
	height: 350,
	closed: true,
	cache: false,
	modal: true
});
//查看人数框
$('#ccount').dialog({
	title: '人数',
	width: 300,
	height: 300,
	closed: true,
	cache: false,
	modal: true
});
//查看班级人数统计框
$('#statisticsbox').dialog({
	title: '统计',
	width: 700,
	height: 500,
	closed: true,
	cache: false,
	modal: true
});
$('#dg').datagrid({
	onClickRow : function(index, row) {
		showbox();
	}
});
});
function showbox() {
	$('#dd').window('open');
}
function addclass() {
	$('#da').window('open');
}
function closebtn() {
	$('#dd').window('close');
}
function upclass() {
	var rows=$('#dg').datagrid('getSelected');
	var id=rows.id;
	if(id==""){
		return;
	}
	$.ajax({
		url: project.name+"userclass/selectAll.do",
		data:{
			
			id:id
		},
	    contentType: "application/x-www-form-urlencoded;charset=utf-8",
	    dataType: "json",
	    success: function(data){
	    	$('#upupusername').textbox("setValue",data[0].className);
	    	$('#upuprole').combobox("setValue",data[0].userName);
	    	$('#upda').window('open');
	    }
		
	});
	
	
	
	
	
	
}
//查看人数
function checkcount(){
	var rows=$('#dg').datagrid('getSelected');
	var classCode=rows.classCode;
	if(classCode==""){
		return;
	}
	$.ajax({
		url: project.name+"userclass/selectcount.do",
		data:{
			
			classCode:classCode
		},
	    contentType: "application/x-www-form-urlencoded;charset=utf-8",
	    dataType: "text",
	    success: function(data){
	    	$('#cninfor').html("班级人数(讲师+1):"+data+"人");
	    	$('#ccount').window('open');
	    }
		
		
		
		
		
	});

}
//插入信息
function addtijiao(){
	var className=$("#upusername").val();
	var userName=$("#uprole").val();
	if(className==""){
		return;
	}
//	if(userName==""){
//		return;
//	}
	$.ajax({
		url: project.name+"userclass/insertClass.do",
		data:{
			className:className,
			userName:userName
		},
	    contentType: "application/x-www-form-urlencoded;charset=utf-8",
	    dataType: "text",
	    success: function(data){
	    	if(data=="-2"){
	    		alert("该老师已分配班级");
	    		return;
	    	}
	    	$('#dg').datagrid('reload'); 
	    	$('#upusername').textbox("setValue","");
	    	$('#uprole').combobox("setValue","");
	    	$('#da').window('close');
	    }
	});
}
//修改信息
function uptijiao(){
	var rows=$('#dg').datagrid('getSelected');
	var id=rows.id;
	var className=$('#upupusername').textbox("getValue");
	var userName=$('#upuprole').combobox("getValue");
	if(id==""){
		return;
	}
	if(className==""){
		return;
	}
	if(userName==""){
		return;
	}
	$.ajax({
		url: project.name+"userclass/updateClass.do",
		data:{
			id:id,
			className:className,
			userName:userName
		},
	    contentType: "application/x-www-form-urlencoded;charset=utf-8",
	    dataType: "text",
	    success: function(data){
	    	if(data=="-2"){
	    		alert("该老师已分配班级");
	    		return;
	    	}
	    	$('#dg').datagrid('reload'); 
	    	$('#upda').window('close');
	    }
	});
}
//显示统计框
function selectcounttable(){
	$('#statisticsbox').window('open');
	
}


//统计

