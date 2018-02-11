$(document).ready(function() {
	loadClassInfor();
	//通过
	$('#btn').linkbutton({
		iconCls: 'icon-add'
	});
	//驳回
	$('#btn2').linkbutton({
		iconCls: 'icon-remove'
	});
	//主表
	$('#dg').datagrid({
		url: project.name+ 'leave/teacheradminselect.do',
		fit: true,
		border: 0,
		ctrlSelect: true,
		pagination: true,
		pageSize:2,
		pageList:[2,10,15,25,30],
		fitColumns: true,
		striped: true,
		toolbar: '#dgtoolbar',
		columns: [
			[{
				field: 'id',
				title: 'ID',
				hidden:true,
				width: 100
			},{
				field: 'trueName',
				title: '姓名',
				width: 100
			}, {
				field: 'why',
				title: '事由',
				width: 100
			}, {
				field: 'createTime',
				title: '请假时间',
				width: 100
			}, {
				field: 'startTime',
				title: '开始时间',
				width: 100
			}, {
				field: 'endTime',
				title: '结束时间',
				width: 100
			}, {
				field: 'status',
				title: '状态',
				width: 100
			}, {
				field: 'statusName',
				title: '状态',
				width: 100
			}]
		]
	});
	//开始日期
	$('#startTime').datetimebox({
		currentText:'Today',
		showSeconds: false
	});
	//结束日期
	$('#endTime').datetimebox({
		currentText:'Today',
		showSeconds: false
	});
	//事由
	$('#why').textbox({
			multiline: true
		})
		//取消按钮
	$('#addclose').linkbutton({
		width: 90,
		height: 30
	});
	//保存按钮
	$('#addsave').linkbutton({
		width: 90,
		height: 30
	});
	//菜单栏按钮
	$('#myleave').linkbutton({
		plain: true,
		width: 180,
		height: 30
	});
	$('#noleave').linkbutton({
		plain: true,
		width: 180,
		height: 30
	});
	$('#passleave').linkbutton({
		plain: true,
		width: 180,
		height: 30
	});
	$('#adminnoleave').linkbutton({
		plain: true,
		width: 180,
		height: 30
	});
	$('#teacherpass').linkbutton({
		plain: true,
		width: 180,
		height: 30
	});
//	下拉框查询
	$('#classNumber').textbox({    
	    readonly:true
	});
	$('#username').combobox({
		url: project.name+ 'leave/loadStudent.do',
		valueField: 'userName',
		textField: 'trueName'
	});
$('#timeSearch').linkbutton({    
    iconCls: 'icon-search'   
});  
//姓名查询
$('#username').combobox({
	onChange:function(newValue, oldValue){
		var username=$('#username').val();
		var startTime=$("#startTime").val();
		var endTime=$("#endTime").val();
			$('#dg').datagrid('load',{
				startTime:startTime,
				userName:username,
				endTime:endTime
				});
		
		
	}
});
});
//显示对话框
function showAdd() {
	var name=$("#username").val();
	alert(name);

}
//加载班级信息
function loadClassInfor(){
	  $.ajax({
			url:project.name+'leave/teacheradminselect.do',
			data : {
		    
			},
			contentType : "application/x-www-form-urlencoded;charset=utf-8",
			dataType : "json",
			success : function(data) {
				$('#classNumber').textbox('setValue',data.rows[0].classInfor.className);
				return;
			}

		});
}
//请假时间查询
function searchTime(){
	var username=$('#username').val();
	var startTime=$("#startTime").val();
	var endTime=$("#endTime").val();
	$('#dg').datagrid('load',{
		startTime:startTime,
		userName:username,
		endTime:endTime
		});
	
}
//请假表的查询
function selectLeave(a){
	var classNumber=$("#classNumber").val();
	var username=$("#username").val();
	$('#dg').datagrid('load',{
		classNumber:classNumber,
		userName:username,
		status:a
		});
}
 
//处理按钮
function pass(a){
	var idlist="";
	var status=a;
	var opts = $('#dg').datagrid('getSelections'); 
	for (var i = 0; i < opts.length; i++) {
		if(opts[i].status=="a2"){
			
			alert(opts[i].trueName+"的"+opts[i].createTime+"这个教务已通过，您不能再修改了");
			continue;
		}
		if(opts[i].status=="t2"){
			if(a=="t2"){
				alert(opts[i].trueName+"的"+opts[i].createTime+"这个您已处理过该申请了");
				continue;
			}
			
		}
		if(opts[i].status=="a1"){
			alert(opts[i].trueName+"的"+opts[i].createTime+"这个教务已驳回，您无权修改");
			continue;
		}
		idlist+=""+opts[i].id+",";
	}
	 $.ajax({
			url:project.name+'leave/updateLeave.do',
			data : {
				idlist:idlist,
				status:status
			},
			contentType : "application/x-www-form-urlencoded;charset=utf-8",
			dataType : "json",
			success : function(data) {
				
				$('#dg').datagrid('reload');  
			}

		});
}
























