$(document).ready(function() {
	loadMenu();
	$('#uptb').textbox({

	});
	$('#addtb').textbox({

	});
	$('#upurl').textbox({

	});
	$('#addurl').textbox({

	});
	$('#upqx').combobox({
		url : project.name + 'role/loadper.do',
		valueField : 'rolevalue',
		textField : 'roletext'
	});
	$('#addqx').combobox({
		url : project.name + 'role/loadper.do',
		valueField : 'rolevalue',
		textField : 'roletext'
	});
	$('#addbtn').linkbutton({
		iconCls : 'icon-add'
	});
	$('#upbtn').linkbutton({
		iconCls : 'icon-ok'
	});
	$('#updatemenu').dialog({
		title : '修改',
		width : 400,
		height : 350,
		closed : true,
		cache : false,
		modal : true
	});
	$('#addmenu').dialog({
		title : '添加',
		width : 400,
		height : 350,
		closed : true,
		cache : false,
		modal : true
	});

});
//刷新
function frash(){
	loadMenu();
}
// 加载权限菜单
function loadMenu() {
	$
			.ajax({
				url : project.name + "menu/loadAllMenu.do",
				data : {},
				contentType : "application/x-www-form-urlencoded;charset=utf-8",
				dataType : "json",
				success : function(data) {
					var h = "";

					for (var i = 0; i < data.length; i++) {
						var x = project.name + data[i].url;
						h += "<div onclick='page(\""
								+ data[i].permissionName
								+ "\",\""
								+ x
								+ "\")' class='shortcut' id='shortcut"
								+ i
								+ "'>"
								+ "<img class='icon' src='/manage/lib/img/xin.png'><div class='title'>"
								+ data[i].permissionName
								+ "</div><a style='display:none' id='id" + i
								+ "'>" + data[i].id + "</a></div>";

					}
					$("#appDesk").html(h);
				}
			});

}
// 显示删除按钮
function deletMenu() {
	$
			.ajax({
				url : project.name + "menu/loadAllMenu.do",
				data : {},
				contentType : "application/x-www-form-urlencoded;charset=utf-8",
				dataType : "json",
				success : function(data) {
					var h = "";

					for (var i = 0; i < data.length; i++) {
						var x = project.name + data[i].url;
						h += "<div onclick='page(\""
								+ data[i].permissionName
								+ "\",\""
								+ x
								+ "\")' class='shortcut' id='shortcut"
								+ i
								+ "'>"
								+ "<div class='deletemenu' onclick='deleteMenuCell("
								+ i
								+ ")'></div><img class='icon' src='/manage/lib/img/xin.png'><div id='title"
								+ i + "' class='title'>"
								+ data[i].permissionName
								+ "</div><a style='display:none' id='id" + i
								+ "'>" + data[i].id + "</a></div>";

					}
					$("#appDesk").html(h);
				}
			});

}
// 显示更新按钮
function updateMenu() {
	$
			.ajax({
				url : project.name + "menu/loadAllMenu.do",
				data : {},
				contentType : "application/x-www-form-urlencoded;charset=utf-8",
				dataType : "json",
				success : function(data) {
					var h = "";

					for (var i = 0; i < data.length; i++) {
						var x = project.name + data[i].url;
						h += "<div onclick='page(\""
								+ data[i].permissionName
								+ "\",\""
								+ x
								+ "\")' class='shortcut' id='shortcut"
								+ i
								+ "'>"
								+ "<div class='updateMenu' onclick='updateMenuCell("
								+ i
								+ ")'></div><img class='icon' src='/manage/lib/img/xin.png'><div id='title"
								+ i + "' class='title'>"
								+ data[i].permissionName
								+ "</div><a style='display:none' id='id" + i
								+ "'>" + data[i].id + "</a></div>";

					}
					$("#appDesk").html(h);
				}
			});

}
// 显示修改框
function updateMenuCell(a) {
	var id=$("#id"+a).html();
	$.ajax({
		url : project.name + "menu/selectAllMenu.do",
		data : {
			id:id
			
		},
		contentType : "application/x-www-form-urlencoded;charset=utf-8",
		dataType : "json",
		success : function(data) {
		
		  if(data.length>1){
			  $('#upqx').combobox("setValue","")
		  }else{
				$('#upqx').combobox("setValue",data[0].roleCode)
		  }
			$('#upid').html(data[0].id);
			$('#uptb').textbox("setValue",data[0].permissionName);
			$('#upurl').textbox("setValue",data[0].url);
		
			$('#updatemenu').window('open');
		}
	});


}
function upsave() {
	
	var id=$('#upid').html();
	var PermissionName=$('#uptb').textbox("getValue");
	var url=$('#upurl').textbox("getValue");
	var roleCode=$('#upqx').combobox("getValue");
	if(id==""){
		return;
	}
	if(PermissionName==""){
		return;
	}
	if(url==""){
		return;
	}
	if(roleCode==""){
		roleCode="-1"
	}
	alert(roleCode);
	$.ajax({
		url : project.name + "menu/updateMenu.do",
		data : {
			id:id,
			PermissionName:PermissionName,
			url:url,
			roleCode:roleCode
			
		},
		contentType : "application/x-www-form-urlencoded;charset=utf-8",
		dataType : "json",
		success : function(data) {
			if(data=="2"){
				alert("菜单名重复!")
				return;
			}
			loadMenu();
			$('#updatemenu').window('close');
		}
	});
	
}
// 显示增加框
function addMenu() {
	loadMenu();
	$('#addmenu').window('open');

}
// 删除
function deleteMenuCell(a) {
	var title2 = $("#title" + a).html();
	var id=$("#id"+a).html();
	$.messager.confirm("确认", "您确认想要删除&nbsp;&nbsp;<a style='color:red;'>"
			+ title2 + "</a>&nbsp;&nbsp;吗？", function(r) {
		if (r) {
			$.ajax({
				url : project.name + "menu/delete.do",
				data : {
					id:id
					
				},
				contentType : "application/x-www-form-urlencoded;charset=utf-8",
				dataType : "json",
				success : function(data) {
					deletMenu();
				}
			});   
		}
	});

}

function addsave() {
	var PermissionName = $("#addtb").val();
	var roleCode = $("#addqx").val();
	var url = $("#addurl").val();
	if (PermissionName == "") {
		return;
	}
	if (roleCode == "") {
		roleCode="-1"
	}
	$
	.ajax({
		url : project.name + "menu/insert.do",
		data : {
			PermissionName:PermissionName,
			roleCode:roleCode,
			url:url
			
		},
		contentType : "application/x-www-form-urlencoded;charset=utf-8",
		dataType : "text",
		success : function(data) {
			if(data=="0"){
				alert("菜单名重复!")
				return;
			}
			loadMenu();
			$('#addmenu').window('close');
			$("#addtb").textbox("setValue","");
			$("#addqx").textbox("setValue","");
			$("#addurl").textbox("setValue","");
			
		}
	});
}