$(document)
		.ready(
				function() {
					$('#tb').textbox({
						prompt : '输入答卷名称'
					});
					$('#stb').textbox({
						buttonIcon : 'icon-search',
						prompt : '查询题目'
					});
					$('#qn').textbox({
						readonly : true
					});
					$('#extistcc').combobox({
						
						url : project.name + 'question/loadQuestionName2.do',
						valueField : 'questionvalue',
						textField : 'questionText',
						limitToList : true
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
					$('#add').linkbutton({
						iconCls : 'icon-add'
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

					$("#qiandao").switchbutton({
						onText : '可匹配',
						offText : '不能匹配',
						readonly : true,
						width : 90,
						height : 30,
						value : '1'

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
						}, {
							field : 'check',
							checkbox : true,
							width : 100
						}, ] ]
					});
					$('#tt').tabs('disableTab', '匹配试题'); // 页面加载时禁用，当填写问卷名时可用
					// 判断可否匹配题
					$('#qiandao').switchbutton({
						onChange : function() {
							getQuestionName();//得到问卷名

						}
					});
					// 已有问卷名选择
					$('#extistcc').combobox({
						onChange : function() {
							var tmtype = $("#extistcc").val();
							if (tmtype == "") {
								$('#qiandao').switchbutton('uncheck'); //
								$('#tt').tabs('disableTab', '匹配试题');// 禁止匹配题目的组合操作
								return;
							}
							getQuestionName();
							$('#qiandao').switchbutton('check');

						}
					});
					// 新问卷名检查按钮
					$('#tb')
							.textbox(
									{
										onChange : function() {
											var qname = $("#tb").val();
											if (qname == "") {
												$('#qiandao').switchbutton('uncheck');
												$('#tt').tabs('disableTab','匹配试题');// 禁止匹配题目的组合操作
												return;
											}
											$
													.ajax({
														url : project.name
																+ "question/checkName.do",
														data : {
															questionName : qname
														},
														contentType : "application/x-www-form-urlencoded;charset=utf-8",
														dataType : "text",
														success : function(data) {
															if (data == "1") {
																hideshowtishi();
																$('#qiandao').switchbutton('check');
																
																return;
															}
															showtishi();
															$('#qiandao').switchbutton('uncheck');
															$('#tt').tabs('disableTab','匹配试题');// 禁止匹配题目的组合操作
														}
													});
										}
									});
					// 单选选项
					$('#tb').textbox("disable");
					$('#extistcc').combobox("disable");
					$(":radio").click(function() {
						if ($(this).val() == 1) {
							$('#tb').textbox("enable");
							$('#extistcc').combobox("disable");
							$('#extistcc').combobox("setValue","");
							return;
						}
						if ($(this).val() == 0) {
							$('#tb').textbox("disable");
							$('#tb').textbox("setValue","");
							$('#extistcc').combobox("enable");
							return;
						}
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
function showtishi() {
	var h = "已存在!<i class='arrow arrow-a'></i><i class='arrow arrow-b'></i>";
	$("#J_noSelectTip").html(h);
	$("#J_noSelectTip").slideDown();
}
function hideshowtishi() {
	$("#J_noSelectTip").fadeOut();
}
//新问卷和就问卷匹配题目
function tianjia() {
	var questionName=$("#qn").val();
	var codelist = "";
	var qncode=$("#qncode").html();
	var opts = $('#dg').datagrid('getSelections');
	if(opts==""){
		alert("请选择!");
		return;
	}
	for (var i = 0; i < opts.length; i++) {
		codelist += opts[i].questionQaCode + ","
	}
	$.ajax({
		url : project.name + "qnqr/insert.do",
		data : {
			questionName:questionName,
			createQuestionCode:qncode,
			codelist:codelist
		},
		contentType : "application/x-www-form-urlencoded;charset=utf-8",
		dataType : "json",
		success : function(data) {
			if(data!=""){
				for (var i = 0; i < data.length; i++) {
					alert(data[i].questionQaInfor.tm+"这道题在"+data[i].createQuestionInfor.questionName+"里已有！");
				}
				return;
			}
			$('#dg').datagrid('clearChecked');
			alert("添加成功!");
		}
	});
}
//得到要加题的问卷名
function getQuestionName(){
	var newqn = $("#tb").val();
	var hasqn = $("#extistcc").combobox('getText');
	var hasqnvalue = $("#extistcc").val();
	if (newqn == "" || hasqn != "") {
		$("#qn").textbox("setValue", hasqn);
		$("#qncode").html(hasqnvalue);
		$('#tt').tabs('enableTab', '匹配试题');
		return;
	}
	if (hasqn == "" || newqn != "") {
		$("#qn").textbox("setValue", newqn);
		$('#tt').tabs('enableTab', '匹配试题');
		return;
	}
	
	
	
	
}