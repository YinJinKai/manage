$(document)
		.ready(
				function() {
					// 预览框
					$('#seeQnq').dialog({
						title : '预览问卷',
						width : 800,
						height : 520,
						closed : true,
						cache : false,
						maximizable : true,
						modal : true
					});
					$('#cctm')
							.combobox(
									{
										url : project.name
												+ 'question/loadQuestionName.do',
										onSelect : function(rec) {
											var url = project.name
													+ 'qnqr/selectQnCount.do?createQuestionCode='
													+ rec.questionvalue;
											$('#orderBy').combobox('reload',
													url);
										},
										valueField : 'questionvalue',
										textField : 'questionText',
										limitToList : true
									});
					// 加载题目序号(和问卷名下拉框关联)
					$('#orderBy').combobox({
						valueField : 'orderbyvalue',
						textField : 'orderbyText',
						limitToList : true
					});
					$('#dg').datagrid({
						url : project.name + 'qnqr/selectQn.do',
						fitColumns : true,
						fit : true,
						toolbar : '#menu',
						border : 0,
						ctrlSelect : true,
						columns : [ [ {
							field : 'id',
							title : 'ID',
							width : 100
						}, {
							field : 'createQuestionInfor.id',
							title : '问卷ID',
							formatter : function(value, row, index) {
								return row.createQuestionInfor.id;
							},
							width : 100
						}, {
							field : 'createQuestionInfor.questionName',
							title : '所属问卷',
							width : 100,
							formatter : function(value, row, index) {
								return row.createQuestionInfor.questionName;
							}

						}, {
							field : 'orderByName',
							title : '题号',
							width : 100
						}, {
							field : 'orderBy',
							title : '题号code',
							width : 100
						}, {
							field : 'questionQaInfor.tm',
							title : '题目',
							width : 100,
							formatter : function(value, row, index) {
								return row.questionQaInfor.tm;
							}

						}, {
							field : 'questionQaInfor.a',
							title : 'A',
							width : 100,
							formatter : function(value, row, index) {
								return row.questionQaInfor.a;
							}
						}, {
							field : 'questionQaInfor.b',
							title : 'B',
							width : 100,
							formatter : function(value, row, index) {
								return row.questionQaInfor.b;
							}
						}, {
							field : 'questionQaInfor.c',
							title : 'C',
							width : 100,
							formatter : function(value, row, index) {
								return row.questionQaInfor.c;
							}
						}, {
							field : 'questionQaInfor.d',
							title : 'D',
							width : 100,
							formatter : function(value, row, index) {
								return row.questionQaInfor.d;
							}
						}, {
							field : 'questionQaInfor.daan',
							title : '答案',
							width : 100,
							formatter : function(value, row, index) {
								return row.questionQaInfor.daan;
							}
						}, {
							field : 'questionQaInfor.tmtype',
							title : '类型',
							width : 100,
							formatter : function(value, row, index) {
								return row.questionQaInfor.tmtype;
							}

						} ] ]
					});

					$('#tb').textbox({
						readonly : true
					});
					$('#btn').linkbutton({
						iconCls : 'icon-print'
					});
					$('#delete').linkbutton({
						iconCls : 'icon-remove'
					});
					$('#closed').linkbutton({
						iconCls : 'icon-clear'
					});
					$('#fabu').linkbutton({
						iconCls : 'icon-ok'
					});
					$('#deleteQbtn').linkbutton({
						iconCls : 'icon-remove'
					});
					$('#order').linkbutton({
						iconCls : 'icon-ok'
					});
					$('#conirm').linkbutton({
						iconCls : 'icon-ok'
					});
					$('#addqn').linkbutton({
						iconCls : 'icon-add'
					});
					$('#addqntitle').textbox({
						
					});
                    $('#addqnbeizhu').textbox({
                    	multiline:true
					});
                    $('#startTmie').datebox({    
                        required:true   
                    });  
                    $('#endTime').datebox({    
                        required:true   
                    });  
					// 下拉框查询问卷
					$('#cctm')
							.combobox(
									{
										onChange : function() {
											var createQuestionCode = $("#cctm")
													.val();
											$('#dg')
													.datagrid(
															'load',
															{
																createQuestionCode : createQuestionCode
															});
											$
													.ajax({
														url : project.name
																+ 'qnqr/selectQn.do',
														data : {
															createQuestionCode : createQuestionCode
														},
														contentType : "application/x-www-form-urlencoded;charset=utf-8",
														dataType : "json",
														success : function(data) {
															$("#tb")
																	.textbox(
																			"setValue",
																			data[0].userInfor.trueName);
														}
													});

										}
									});

					// 排序选择框
					$('#dd').dialog({
						title : '请选择顺序',
						width : 400,
						height : 300,
						closed : true,
						cache : false,
						modal : true
					});
					$('#addQnq').dialog({
						title : '设置发布信息',
						width : 400,
						height : 350,
						closed : true,
						cache : false,
						modal : true
					});
					$('#addQnq').dialog({
						onClose: function() {
							$('#addqntitle').textbox('setValue','');
							$('#addqnbeizhu').textbox('setValue','');
							$("#J_noSelectTip").hides();
							return
						}

					});
					checkTitle();
				});
// 删除题目
function deleteqn() {
	var idlist = "";
	var opts = $('#dg').datagrid('getSelections');
	if (opts == null || opts == "") {
		alert("请选择!");
		return;
	}
	for (var i = 0; i < opts.length; i++) {
		idlist += opts[i].id + ","
	}

	$.ajax({
		url : project.name + 'qnqr/deleteQn.do',
		data : {
			idlist : idlist
		},
		contentType : "application/x-www-form-urlencoded;charset=utf-8",
		dataType : "json",
		success : function(data) {
			reOrderBy();
		}

	});

}
// 删除后的重新排序
function reOrderBy() {
	var createQuestionCode = $("#cctm").val();
	$.ajax({
		url : project.name + 'qnqr/reOrderBy.do',
		data : {
			createQuestionCode : createQuestionCode
		},
		contentType : "application/x-www-form-urlencoded;charset=utf-8",
		dataType : "json",
		success : function(data) {
			$('#orderBy').combobox('reload');// 刷新下拉框
			$('#dg').datagrid('reload');
			alert("移除成功!");
		}

	});
}
// 排序按钮
function setOrder() {
	var opts = $('#dg').datagrid('getSelections');
	if (opts == null || opts == "") {
		alert("请选择!");
		return;
	}
	$('#dd').window('open');// 弹出排序的对话框
}
// 确认排序按钮
function conirm() {
	var idlist = "";// 要修改的id和值
	var orderBylist = "";// 相对的id和值
	var oldorder;// 被选中的值&相对要改的值
	var opts = $('#dg').datagrid('getSelections');
	if (opts == null || opts == "") {
		return;
	}
	for (var i = 0; i < opts.length; i++) {
		idlist += opts[i].id + "," // 被选中的id
		oldorder = opts[i].orderBy; // 被选中的值&相对要改的值
	}
	var orderBy = $("#orderBy").val(); // 要改的值
	if (orderBy == "") {
		alert("请选择顺序!");
		return;
	}
	idlist += orderBy + ",";
	var rows = $('#dg').datagrid('getRows');
	var rowid;
	for (var i = 0; i < rows.length; i++) {
		var roworder = rows[i].orderBy;
		if (roworder == orderBy) {
			rowid = rows[i].id;
			orderBylist += rowid + ","; // 相对的id
			break;
		}
	}
	orderBylist += oldorder + ",";
	$.ajax({
		url : project.name + 'qnqr/update.do',
		data : {
			orderBylist : orderBylist,
			idlist : idlist
		},
		contentType : "application/x-www-form-urlencoded;charset=utf-8",
		dataType : "json",
		success : function(data) {
			$('#dg').datagrid('reload');
			alert("更新成功!");
			$('#dd').window('close');
		}

	});

}
// 移除该问卷
function deleteThisQn() {

	var createQuestionCode = $("#cctm").val();
	var idlist = "";
	var rows = $('#dg').datagrid('getRows');
	for (var i = 0; i < rows.length; i++) {
		idlist += rows[i].id + ",";
	}
	$.ajax({
		url : project.name + 'qnqr/deleteQnRel.do',
		data : {
			createQuestionCode : createQuestionCode,
			idlist : idlist
		},
		contentType : "application/x-www-form-urlencoded;charset=utf-8",
		dataType : "json",
		success : function(data) {
			alert("删除成功");
			$('#cctm').combobox('reload');// 刷新下拉框 ，重新查询表
		}

	});
}
// 预览问卷
function seeQn(a) {
	var createQuestionCode = $("#cctm").val();
	var url = project.name + 'qnqr/seeQnPage.shtml?createQuestionCode='
			+ createQuestionCode + '';
	var content = "<iframe src='"
			+ url
			+ "' frameborder=0 scrolling='auto' style='width:100%;height:100%;'/>";
	$('#seeQnq').panel({
		title : a,
		content : content

	});

	$('#seeQnq').window('open');
}
//发布该问卷
function addqn(){
	$('#addQnq').window('open');
	
	
}
//取消发布问卷
function closed() {
	$('#addqntitle').textbox('setValue','');
	$('#addqnbeizhu').textbox('setValue','');
	$('#addQnq').window('close');
}
//检查标题是否重复
function checkTitle() {
	$('#addqntitle')
	.textbox(
			{
				onChange : function() {
					var createQuestionCode = $("#cctm").val();
					if(createQuestionCode==""){
						return;
					}
					var qname = $("#addqntitle").val();
					if (qname == "") {
						return;
					}
					$
							.ajax({
								url : project.name
										+ "stcheck/checkTitle.do",
								data : {
									createQuestionCode:createQuestionCode,
									title : qname
								},
								contentType : "application/x-www-form-urlencoded;charset=utf-8",
								dataType : "text",
								success : function(data) {
									if(data=="1"){
										$('#fabu').linkbutton('enable');
										var h="可使用<i class='arrow arrow-a'></i><i class='arrow arrow-b'></i>"
										$("#J_noSelectTip").html(h);
										$("#J_noSelectTip").show();
											return;
									}
									if(data=="0"){
										$('#fabu').linkbutton('disable');
										var h="已存在<i class='arrow arrow-a'></i><i class='arrow arrow-b'></i>"
											$("#J_noSelectTip").html(h);
											$("#J_noSelectTip").show();
										return;
									}
								}
							});
				}
			});
}
function fabu(){
	var createQuestionCode = $("#cctm").val();
	if(createQuestionCode==""){
		return;
	}
	var title=$('#addqntitle').val();
	if(title==""){
		return;
	}
	var remark=$('#addqnbeizhu').val();
	if(remark==""){
		return;
	}
	var startTmie=$('#startTmie').val();
	if(startTmie==""){
		return;
	}
	var endTime= $('#endTime').val();
	if(endTime==""){
		return;
	}
	var time=startTmie+"--"+endTime;
	alert(time)
	$.ajax({
		url : project.name + 'stcheck/insert.do',
		data : {
			createQuestionCode : createQuestionCode,
			title : title,
			remark:remark,
			time:time
			
		},
		contentType : "application/x-www-form-urlencoded;charset=utf-8",
		dataType : "json",
		success : function(data) {
			closed();
			alert("发布成功");
			$('#cctm').combobox('reload');// 刷新下拉框 ，重新查询表
		}

	});
	
}
















