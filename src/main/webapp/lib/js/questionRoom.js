$(document).ready(function(){
	//主观题
	$('#tm').textbox({    
		label: '题目:' ,
		labelPosition: 'top',
		multiline:true
	});
	$('#axx').textbox({    
		label: 'A:' ,
		labelPosition: 'top',
	});
	$('#bxx').textbox({    
		label: 'B:' ,
		labelPosition: 'top',
	});
	$('#cxx').textbox({    
		label: 'C:' ,
		labelPosition: 'top',
	});
	$('#dxx').textbox({    
		label: 'D:' ,
		labelPosition: 'top',
	});
	$('#daan').textbox({    
		label: '答案:' ,
		labelPosition: 'top',
	});
	//客观
	$('#kgtm').textbox({    
		label: '题目:' ,
		labelPosition: 'top',
		multiline:true
	});
//	修改
	$('#uptm').textbox({    
		label: '题目:' ,
		labelPosition: 'top',
		multiline:true
	});
	$('#upaxx').textbox({    
		label: 'A:' ,
		labelPosition: 'top',
	});
	$('#upbxx').textbox({    
		label: 'B:' ,
		labelPosition: 'top',
	});
	$('#upcxx').textbox({    
		label: 'C:' ,
		labelPosition: 'top',
	});
	$('#updxx').textbox({    
		label: 'D:' ,
		labelPosition: 'top',
	});
	$('#updaan').textbox({    
		label: '答案:' ,
		labelPosition: 'top',
	});
	//客观
	$('#upkgtm').textbox({    
		label: '题目:' ,
		labelPosition: 'top',
		multiline:true
	});
	$('#tt').tabs({
		border : false,
		fit : true,
		justified : true,
		pill : true,
		narrow : true,
		plain : true,
		tabHeight : 30
	});
	$('#uptt').tabs({
		border : false,
		fit : true,
		justified : true,
		pill : true,
		narrow : true,
		plain : true,
		tabHeight : 30
	});
	$('#dg').datagrid({
		url : project.name + 'qa/selectQa.do',
		fitColumns : true,
		pagination : true,
		pageSize : 10,
		pageList : [ 10, 15, 25, 30 ],
		fit : true,
		border : 0,
		striped : true,
		ctrlSelect:true,
		toolbar : '#menu',

		columns : [ [ {
			field : 'id',
			title : 'ID',
			width : 100
		}, {
			field : 'questionQaCode',
			title : 'code',
			width : 100,
			hidden:true
		}, {
			field : 'tm',
			title : '题目',
			width : 100
		}, {
			field : 'a',
			title : 'A',
			width : 100
		}, {
			field : 'b',
			title : 'B',
			width : 100
		}, {
			field : 'c',
			title : 'C',
			width : 100
		}, {
			field : 'd',
			title : 'D',
			width : 100
		}, {
			field : 'daan',
			title : '答案',
			width : 100
		}, {
			field : 'tmtype',
			title : '类型',
			width : 100
		} ] ]
	});
	$('#cctm').combobox({

		valueField : 'label',
		textField : 'value',
		data : [ {
			label : '',
			value : '全部'
		}, {
			label : '1',
			value : '客观题'
		}, {
			label : '2',
			value : '主观题'
		} ]
	});
	$('#stb').textbox({
		buttonIcon : 'icon-search',
		prompt : '查询题目'
	});
	$('#add').linkbutton({
		iconCls : 'icon-add'
	});
	$('#zgsave').linkbutton({
		iconCls : 'icon-add'
	});
	$('#kgsave').linkbutton({
		iconCls : 'icon-add'
	});
	$('#upzgsave').linkbutton({
		iconCls : 'icon-add'
	});
	$('#upkgsave').linkbutton({
		iconCls : 'icon-add'
	});
	$('#qaupdate').linkbutton({
		iconCls : 'icon-remove'
	});
	$('#qadelete').linkbutton({
		iconCls : 'icon-cancel',
		disabled:true
	});
	$('#saveUpdate').linkbutton({
		iconCls : 'icon-add'
	});
//	添加题目
	$('#addqn').dialog({
		title : '添加试题',
		width : 500,
		height : 550,
		closed : true,
		cache : false,
		openAnimation : 'slide',
		closeAnimation : 'show',
		modal : true
	});
//	操作题目
	$('#doqn').dialog({
		title : '操作',
		width : 200,
		height : 200,
		closed : true,
		cache : false,
		openAnimation : 'slide',
		closeAnimation : 'show',
		modal : true
	});
//	更新题目
	$('#updateqn').dialog({
		title : '修改题目',
		width : 500,
		height : 550,
		closed : true,
		cache : false,
		openAnimation : 'slide',
		closeAnimation : 'show',
		modal : true
	});
	// 下拉框查询
	$('#cctm').combobox({
		onChange : function() {
			var tmtype = $("#cctm").val();
			$('#dg').datagrid('load', {
				tmtype : tmtype
			});

		}
	});
	// 点击显示答题
	$('#dg').datagrid({
		onClickRow : function(index, row) {
			$('#doqn').window('open');
		}
	});
	// 查询题目
	$('#stb').textbox({
		onClickButton : function() {
			var tm = $("#stb").val();
			$('#dg').datagrid('load', {
				tm : tm
			});

		}
	});

});
//添加试题
function tianjia() {
	$('#addqn').window('open');
}
//添加客观题
function kgsave() {
	var tm=$("#tm").val();
	var a=$("#axx").val();
	var b=$("#bxx").val();
	var c=$("#cxx").val();
	var d=$("#dxx").val();
	var daan=$("#daan").val();
	var tmtype=$("#kgcode").html();
	if(tm==""){
		return;
	}
	if(tmtype==""){
		return;
	}
	$.ajax({
		url:project.name+'qa/insertqn.do',
		data : {
			tm: tm,
			a: a,
			b:b,
			c:c,
			d:d,
			daan:daan,
			tmtype:tmtype
		},
		contentType : "application/x-www-form-urlencoded;charset=utf-8",
		dataType : "text",
		success : function(data) {
			alert("添加成功");
			$('#dg').datagrid('reload'); 
			$('#addqn').window('close');
			$("#tm").textbox("setValue","");
			$("#axx").textbox("setValue","");
			$("#bxx").textbox("setValue","");
			$("#cxx").textbox("setValue","");
			$("#dxx").textbox("setValue","");
			$("#daan").textbox("setValue","");
		}


	});
}
//添加主观题
function zgsave() {
	var tm=$("#kgtm").val();
	var tmtype=$("#zgcode").html();
	if(tm==""){
		return;
	}
	if(tmtype==""){
		return;
	}
	$.ajax({
		url:project.name+'qa/insertqn.do',
		data : {
			tm: tm,
			tmtype:tmtype
		},
		contentType : "application/x-www-form-urlencoded;charset=utf-8",
		dataType : "text",
		success : function(data) {
			alert("添加成功");
			$('#dg').datagrid('reload'); 
			$('#addqn').window('close');
			$("#kgtm").textbox("setValue","");
		}


	});
}
//修改试题
function qaupdate() {
	$('#doqn').window('close');
	var rows=$('#dg').datagrid('getSelected');
	var id=rows.id;
	$.ajax({
		url:project.name+'qa/selectQa.do',
		data : {
			id: id
		},
		contentType : "application/x-www-form-urlencoded;charset=utf-8",
		dataType : "json",
		success : function(data) {
			var type=data.rows[0].tmtype;
			if(type=="主观题"){
				$("#upkgtm").textbox("setValue",data.rows[0].tm);
				
				$('#updateqn').window('open');
				$('#uptt').tabs('select',"主观题");
				
				
				
				
				return;
			}
			if(type=="客观题"){
				$("#uptm").textbox("setValue",data.rows[0].tm);
				$("#upaxx").textbox("setValue",data.rows[0].a);
				$("#upbxx").textbox("setValue",data.rows[0].b);
				$("#upcxx").textbox("setValue",data.rows[0].c);
				$("#updxx").textbox("setValue",data.rows[0].d);
				$("#updaan").textbox("setValue",data.rows[0].daan);
				$('#updateqn').window('open');
				$('#uptt').tabs('select',"客观题");
				
				return;
			}
			
			
			
			
		}


	});
	
}
//保存修改客观题
function upkgsave() {
	var rows=$('#dg').datagrid('getSelected');
	var id=rows.id;
	var tm=$("#uptm").textbox("getValue");
	var a=$("#upaxx").textbox("getValue");
	var b=$("#upbxx").textbox("getValue");
	var c=$("#upcxx").textbox("getValue");
	var d=$("#updxx").textbox("getValue");
	var daan=$("#updaan").textbox("getValue");
	if(tm==""){
		return;
	}
	$.ajax({
		url:project.name+'qa/updateqn.do',
		data : {
			id: id,
			tm:tm,
			a:a,
			b:b,
			c:c,
			d:d,
			daan:daan
		},
		contentType : "application/x-www-form-urlencoded;charset=utf-8",
		dataType : "json",
		success : function(data) {
				$("#uptm").textbox("setValue","");
				$("#upaxx").textbox("setValue","");
				$("#upbxx").textbox("setValue","");
				$("#upcxx").textbox("setValue","");
				$("#updxx").textbox("setValue","");
				$("#updaan").textbox("setValue","");
				$('#updateqn').window('close');
				$('#dg').datagrid('reload'); 
		}


	});
}
//保存修改主观题
function upzgsave() {
	var rows=$('#dg').datagrid('getSelected');
	var id=rows.id;
	var tm=$("#upkgtm").textbox("getValue");
	if(tm==""){
		return;
	}
	$.ajax({
		url:project.name+'qa/updateqn.do',
		data : {
			id: id,
			tm:tm
		},
		contentType : "application/x-www-form-urlencoded;charset=utf-8",
		dataType : "json",
		success : function(data) {
				$("#upkgtm").textbox("setValue","");
				$('#updateqn').window('close');
				$('#dg').datagrid('reload'); 
		}


	});
}
//删除试题
function qadelete() {
	var rows2=$('#dg').datagrid('getSelected');
	var tm=rows2.tm
	$.messager.confirm('删除', '确认删除[ '+tm+' ]？', function(r){
		if (r){
			var rows=$('#dg').datagrid('getSelected');
			var id=rows.id;
			if(rows==""){
				return;
			}
			$.ajax({
				url:project.name+'qa/deleteqn.do',
				data : {
					id: id
				},
				contentType : "application/x-www-form-urlencoded;charset=utf-8",
				dataType : "json",
				success : function(data) {
					$('#doqn').window('close');
						$('#dg').datagrid('reload'); 
				}


			});
		    return;
		}
	});

}






























