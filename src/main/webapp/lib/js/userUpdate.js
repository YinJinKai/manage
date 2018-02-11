
	$(document).ready(function() {
		$('#dg').datagrid({
			url:  project.name+ 'user/selectAllUser.do',
			fitColumns: true,
			toolbar: '#menu',
			border: 0,
			pagination : true,
			pageSize : 10,
			striped: true,
			ctrlSelect: true,
			pageList : [2, 10, 15, 25, 30 ],
			fit : true,
			columns: [
				[{
					field: 'id',
					title: 'ID',
					width: 100
				}, {
					field: 'userName',
					title: '账号',
					width: 100
				}, {
					field: 'trueName',
					title: '姓名',
					width: 100
				}, {
					field: 'password',
					title: '密码',
					width: 100
				}, {
					field: 'className',
					title: '班级',
					width: 100
				}, {
					field: 'roleName',
					title: '角色',
					width: 100
				}]
			]
		});
		$('#tName').textbox({
			buttonIcon: 'icon-search',
			iconCls: 'icon-man',
			iconAlign: 'left'
		})
		$('#nameCode').textbox({
			buttonIcon: 'icon-search',
			iconCls: 'icon-man',
			iconAlign: 'left'
		})
		$('#cctm').combobox({
			url:  project.name+ 'role/loadper.do',
			valueField: 'rolevalue',
			textField: 'roletext'
		});
		$('#cctm').combobox({
			onChange: function(){
				var roleCode=$("#cctm").val();
				var classCode=$("#cctitle").val();
				$('#dg').datagrid('load',{
					roleCode:roleCode,
					classCode:classCode
				});
			}
		});

		$('#cctitle').combobox({
			url: project.name+ 'leave/adminSelectClass.do',
			valueField: 'classCode',
			textField: 'className'
		});
		$('#cctitle').combobox({
			onChange: function(){
				var roleCode=$("#cctm").val();
				var classCode=$("#cctitle").val();
				$('#dg').datagrid('load',{
					roleCode:roleCode,
					classCode:classCode
				});
			}
		});
		$('#tName').textbox({
			onClickButton : function() {
				var tm = $("#tName").val();
				$('#dg').datagrid('load', {
					trueName:tm
				});

			}
		});
		$('#nameCode').textbox({
			onClickButton : function() {
				var tm = $("#nameCode").val();
				$('#dg').datagrid('load', {
					userName:tm
				});

			}
		});
		$('#upclass').combobox({
			url: project.name+ 'leave/adminSelectClass.do',
			valueField: 'classCode',
			textField: 'className'
		});
		$('#gradebtn').linkbutton({
			iconCls: 'icon-ok'
		});
//		修改框
		$('#upusername').textbox({    
		         
		});
		$('#uppassword').textbox({    
		         
		});
		$('#uprole').combobox({
			url:  project.name+ 'role/loadper.do',
			valueField: 'rolevalue',
			textField: 'roletext'
		});
		$('#dd').dialog({
			title: '修改信息',
			closeAnimation: 'fade',
			width: 350,
			height: 350,
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

function tijiao(){
	var rows=$('#dg').datagrid('getSelected');
	var id=rows.id;
	var trueName=$("#upusername").textbox("getValue")
	var password=$("#uppassword").textbox("getValue")
	var roleCode=$("#uprole").val();
	var classCode=$("#upclass").val();
	if(id==""){
		return;
	}
	if(trueName==""){
		return;
	}
	if(password==""){
		return;
	}
	if(roleCode==""){
		return;
	}
	if(classCode==""){
		return;
	}
	$.ajax({
		url:project.name+'user/updateuser.do',
		data : {
			id: id,
			trueName:trueName,
			password:password,
			roleCode:roleCode,
			classCode:classCode
		},
		contentType : "application/x-www-form-urlencoded;charset=utf-8",
		dataType : "json",
		success : function(data) {
			$('#dg').datagrid('reload'); 
			$('#dd').window('close');
			
		}


	});
	
}

function showbox() {
	var rows=$('#dg').datagrid('getSelected');
	var id=rows.id;
	$.ajax({
		url:project.name+'user/selectAllUser.do',
		data : {
			id: id
		},
		contentType : "application/x-www-form-urlencoded;charset=utf-8",
		dataType : "json",
		success : function(data) {
			$("#upusername").textbox("setValue",data.rows[0].trueName);
			$("#uppassword").textbox("setValue",data.rows[0].password);
			$("#uprole").combobox("setValue",data.rows[0].roleCode);
			$('#upclass').combobox("setValue",data.rows[0].classCode);
			$('#dd').window('open');
			
		}


	});
	
}