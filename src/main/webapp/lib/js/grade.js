
	$(document).ready(function() {
//		联动下拉框
		$('#cctm').combobox({
			url : project.name
					+ 'question/loadQuestionName.do',
			onSelect : function(rec) {
				var url = project.name
						+ 'checkWitch/loadtitle.do?createQuestionCode='
						+ rec.questionvalue;
				$('#cctitle').combobox('reload',url);
			},
			valueField : 'questionvalue',
			textField : 'questionText',
			limitToList : true
	});
	$('#cctitle').combobox({
		onSelect : function(rec) {
			var url = project.name
					+ 'checkWitch/loadtitleusername.do?titleid='
					+ rec.titleid;
			$('#st').combobox('reload',url);
		},
		valueField: 'titleid',
		textField: 'titletext'
	});
	$('#st').combobox({
		valueField: 'userName',
		textField: 'trueName'
	});

	$('#st').combobox({
		onLoadSuccess: function(){
			var oncreateQuestionCode=$('#cctm').combobox('getValue');
			var ontitleid=$('#cctitle').combobox('getValue');
			var onuserName=$('#st').combobox('getValue');
			loaddatagarid(onuserName,ontitleid,oncreateQuestionCode);
			checkishasgrade(onuserName,ontitleid,oncreateQuestionCode);
		}
	});
	$('#st').combobox({
		onChange: function(){
			var oncreateQuestionCode=$('#cctm').combobox('getValue');
			var ontitleid=$('#cctitle').combobox('getValue');
			var onuserName=$('#st').combobox('getValue');
			loaddatagarid(onuserName,ontitleid,oncreateQuestionCode);
			checkishasgrade(onuserName,ontitleid,oncreateQuestionCode);
			pingfenstate();
		}
	});


			$('#level').combobox({
				valueField: 'label',
				textField: 'value',
				data: [{
					label: '1',
					value: 'A'
				},{
					label: '2',
					value: 'B'
				},{
					label: '3',
					value: 'C'
				}]
		});
		$('#btn').linkbutton({
			iconCls: 'icon-search'
		});
		$('#gradebtn').linkbutton({
			iconCls: 'icon-search'
		});
		$('#dd').dialog({
			title: '评分',
			closeAnimation: 'fade',
			width: 250,
			height: 200,
			closed: true,
			cache: false,
			modal: true
		});
		
	});


function pingfenstate() {
	var state=$('#st').combobox('getValue');
	if(state=="-1"){
		$('#btn').linkbutton('disable');
      }else{
    	  $('#btn').linkbutton('enable');
      }
	
}
function tijiao() {
	var id;
	var pp=$('#dg').datagrid("getRows");
	if(pp==""){
		return;
	}
	for (var i = 0; i < pp.length; i++) {
		id=pp[i].id;
	}
	var level=$('#level').combobox('getValue');
	if(level==""){
		return;
	}
	 $.ajax({
			url:project.name+'checkWitch/update.do',
			data : {
				id:id,
				level:level
			},
			contentType : "application/x-www-form-urlencoded;charset=utf-8",
			dataType : "json",
			success : function(data) {
				
				aftertijiao(); 
			}

		});
	
}
function aftertijiao(){
	$('#btn').linkbutton({
		text:'已评分'
	});
	$('#btn').linkbutton('disable');
	$('#dd').window('close');
}
function nothasgrade(){
	$('#btn').linkbutton({
		text:'评分'
	});
	$('#btn').linkbutton('enable');
	$('#dd').window('close');
}
function showbox(){
		$('#dd').window('open');
}
//检查是否已评分
function checkishasgrade(a,b,c) {
	 $.ajax({
			url:project.name+'checkWitch/selectAll.do',
			data : {
				userName: a,
				titleid: b,
				createQuestionCode:c
			},
			contentType : "application/x-www-form-urlencoded;charset=utf-8",
			dataType : "json",
			success : function(data) {
				var level = data[0].level;
				if(level!=null){
					aftertijiao();
					
				}else{
					nothasgrade();
				}
				 
			}

		});
}
function loaddatagarid(a,b,c){
//	主表
	$('#dg').datagrid({
		url: project.name
		+ 'checkWitch/selectAll.do',
		queryParams: {
			userName: a,
			titleid: b,
			createQuestionCode:c
		},
		fitColumns: true,
		toolbar: '#menu',
		border: 0,

		columns: [
			[  {
				field: 'id',
				title: 'ID',
				width: 100
			},{
				field: 'questionQaInfor.tm',
				title: '题目',
				width: 100,
				formatter : function(value, row, index) {
					return row.questionQaInfor.tm;
				}
			}, {
				field: 'questionQaInfor.a',
				title: 'A',
				width: 100,
				formatter : function(value, row, index) {
					return row.questionQaInfor.a;
				}
			}, {
				field: 'questionQaInfor.b',
				title: 'B',
				width: 100,
				formatter : function(value, row, index) {
					return row.questionQaInfor.b;
				}
			}, {
				field: 'questionQaInfor.c',
				title: 'C',
				width: 100,
				formatter : function(value, row, index) {
					return row.questionQaInfor.c;
				}
			}, {
				field: 'questionQaInfor.d',
				title: 'D',
				width: 100,
				formatter : function(value, row, index) {
					return row.questionQaInfor.d;
				}
			}, {
				field: 'questionQaInfor.daan',
				title: '本题答案',
				width: 100,
				formatter : function(value, row, index) {
					return row.questionQaInfor.daan;
				}
			}, {
				field: 'answerInfor.daName',
				title: '他的答案',
				width: 100,
				formatter : function(value, row, index) {
					return row.answerInfor.daName;
				}
			}]
		]
	});
}