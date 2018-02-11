$(document).ready(function() {
	// 答题框
	$('#seeQnq').dialog({
		title : '答题中......',
		width : 800,
		height : 520,
		closed : true,
		cache : false,
		maximizable : true,
		modal : true
	});
	loadUserInfor();
	// 主表
	$('#dg').datagrid({
		url : project.name + 'stcheck/loadQninfor.do',
		fit : true,
		singleSelect : true,
		fitColumns : true,
		toolbar : '#menu',
		columns : [ [ {
			field : 'id',
			title : 'titleid',
			width : 100,
			hidden : true
		}, {
			field : 'title',
			title : '标题',
			width : 100
		}, {
			field : 'createQuestionInfor.questionName',
			title : '名称',
			width : 100,
			formatter : function(value, row, index) {
				return row.createQuestionInfor.questionName;
			}
		}, {
			field : 'createQuestionCode',
			title : 'qncode',
			width : 100,
			hidden : true
		}, {
			field : 'time',
			title : '日期区间',
			width : 100
		}, {
			field : 'remark',
			title : '备注',
			width : 100
		} ] ]
	});
	// 文本框
	$('#classNumber').textbox({
		readonly : true
	});

	$('#username').textbox({
		readonly : true
	});

	// 下拉框
	$('#time').combobox({
		url : 'combobox_data.json',
		valueField : 'id',
		textField : 'text'
	});

	// 查询
	$('#datibtn').linkbutton({
		iconCls : 'icon-add'
	});
	$('#checkbtn').linkbutton({
		iconCls : 'icon-search'
	});
	$('#closebtn').linkbutton({
		iconCls : 'icon-clear'
	});
	$('#dd').dialog({
		title : '选择',
		width : 300,
		height : 300,
		closed : true,
		cache : false,
		openAnimation : 'slide',
		closeAnimation : 'show',
		modal : true
	});
	$('#level').dialog({
		title : '查看等级',
		width : 300,
		height : 300,
		closed : true,
		cache : false,
		openAnimation : 'slide',
		closeAnimation : 'show',
		modal : true
	});
	// 点击显示答题
	$('#dg').datagrid({
		onClickRow : function(index, row) {
			$('#dd').window('open');
			var createQuestionCode = row.createQuestionCode;
			var id = row.id;
			$("#titleid").html(id);
			$("#qncode").html(createQuestionCode);
		}
	});

});
function closebtn() {
	$('#dd').window('close');
}
function checkbtn() {
	$('#level').window('open');
	var userName=$("#userCode").html();
	var titleid=$("#titleid").html();
	var createQuestionCode=$("#qncode").html();
	 $.ajax({
			url:project.name+'checkWitch/selectAll.do',
			data : {
				userName: userName,
				titleid: titleid,
				createQuestionCode:createQuestionCode
			},
			contentType : "application/x-www-form-urlencoded;charset=utf-8",
			dataType : "json",
			success : function(data) {
				if(data==""){
					$("#levelinfor").html("您还未作答!");
					return;
				}
				var level=data[0].level;
				if(level==""||level==null){
					$("#levelinfor").html("教务审批中...");
						return;
				}
				if(level=="1"){
					$("#levelinfor").html("你的评分为:  A");
						return;
				}
				if(level=="2"){
					$("#levelinfor").html("你的评分为:  B");
						return;
				}
				if(level=="3"){
					$("#levelinfor").html("你的评分为:  C");
						return;
				}
			}


		});
	

}
function datibtn() {
	var id = $("#titleid").html();
	var createQuestionCode = $("#qncode").html();
	var userName = $("#userCode").html();
	$
			.ajax({
				url : project.name + 'checkWitch/checkisre.do',
				data : {
					titleid : id,
					createQuestionCode : createQuestionCode,
					userName : userName
				},
				contentType : "application/x-www-form-urlencoded;charset=utf-8",
				dataType : "json",
				success : function(data) {
					if (data[0] == "1") {
						alert("已回答")
						return;
					}
					var url = project.name
							+ 'qnqr/seeQnPage.shtml?createQuestionCode='
							+ createQuestionCode + '&userName=' + userName
							+ '&titleid=' + id + '';
					var content = "<iframe src='"
							+ url
							+ "' frameborder=0 scrolling='auto' style='width:100%;height:100%;'/>";
					$('#seeQnq').panel({
						content : content

					});

					$('#seeQnq').window('open');
					closebtn();
					return;
				}

			});

}

// 加载学生信息
function loadUserInfor() {
	$.ajax({
		url : project.name + 'user/loadinfor.do',
		data : {

		},
		contentType : "application/x-www-form-urlencoded;charset=utf-8",
		dataType : "json",
		success : function(data) {
			$('#classNumber').textbox('setValue', data[0].className);
			$('#username').textbox('setValue', data[0].trueName);
			$('#userCode').html(data[0].userName);
			return;
		}

	});
}