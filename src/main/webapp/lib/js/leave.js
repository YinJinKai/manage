$(document).ready(function() {
	//添加按钮
	$('#btn').linkbutton({
		iconCls: 'icon-add'
	});
	//主表
	$('#dg').datagrid({
		url:project.name+ "leave/selectLeave.do",
		fit:true,
		fitColumns: true,
		border:0,
//		pagination: true,
//		pagePosition:'top',
		striped:true,
		toolbar: '#menu',
		columns: [
			[{
				field: 'name',
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
				hidden:true,
				width: 100
			}, {
				field: 'statusName',
				title: '状态',
				width: 100
			}]
		]
	});
	//	添加对话框
	$('#p').panel({
		width: 500,
		height: 400,
		left: 500,
		top: 80,
		title: '学生请假申请',
		iconCls: 'icon-save',
		closable: true,
		closed: true,
		closeAnimation: 'fade',
		openAnimation: 'show'
	});
	$('#p').panel('panel').css('position', 'absolute');
	//	关闭对话框
	$('#p').panel({
		onClose: function() {
			$("#loginbody_mask").hide();
			return
		}

	});
	//开始日期
	$('#startTime').datetimebox({
		value: '3/4/2010 2:3',
		required: true,
		showSeconds: false
	});
	//结束日期
	$('#endTime').datetimebox({
		value: '3/4/2010 2:3',
		required: true,
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
	$('#leaving').linkbutton({
		plain: true,
		width: 180,
		height: 30
	});
	$('#adminleaving').linkbutton({
		plain: true,
		width: 180,
		height: 30
	});
	$('#adminnoleave').linkbutton({
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

});
//显示对话框
function showAdd() {
	$("#loginbody_mask").show();
	$('#p').panel('open')

}
//取消
function closeAdd() {

	$("#why").textbox('setValue','');
	$("#loginbody_mask").hide();
}
//保存
function save() {
 var startTime=$("#startTime").val();
 var endTime=$("#endTime").val();
 var why=$("#why").val();
 if(startTime==""){
	 return;
 }
 if(endTime==""){
	 return;
 }
 if(why==""){
	 return;
 }
	$.ajax({
		url: project.name+"leave/addNewLeave.do",
		data:{
			startTime:startTime,
			endTime:endTime,
			why:why
		},
	    contentType: "application/x-www-form-urlencoded;charset=utf-8",
	    dataType: "text",
	    success: function(data){
	      closeAdd();
	      $('#dg').datagrid('loadData');  
//	      alert("succeed");
	    }
});
}

//请假表的查询
function selectLeave(a){
	$('#dg').datagrid('load',{
		status:a
		});
}


































