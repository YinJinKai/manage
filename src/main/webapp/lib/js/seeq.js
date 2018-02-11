$(document).ready(function() {

	loadQn();

});
function closeqq() {
$("#J_noSelectTip").slideDown();
}
function loadQn() {
	var createQuestionCode = $("#createQuestionCode").text();
	if (createQuestionCode == "") {
		return;
	}
	$
			.ajax({
				url : project.name + "qnqr/selectQn.do",
				data : {

					createQuestionCode : createQuestionCode

				},
				contentType : "application/x-www-form-urlencoded;charset=utf-8",
				dataType : "json",
				success : function(data) {
					var h = "<div id='qnName' class='q_title'>"
							+ data[0].createQuestionInfor.questionName
							+ "</div>";// 问卷名
					var a = "";
					var b = "";
					for (var i = 0; i < data.length; i++) {
						var tmtype = data[i].questionQaInfor.tmtype;
						if (tmtype == "客观题") {
							b = "objectives"; // 标志为客观题
							a = "<div class='q_xx left'><label>选项:</label>"
									+ "<div class='t_xx'><div>A."
									+ data[i].questionQaInfor.a
									+ "</div><div>B."
									+ data[i].questionQaInfor.b
									+ "</div><div>C."
									+ data[i].questionQaInfor.c
									+ "</div><div>D."
									+ data[i].questionQaInfor.d
									+ "</div></div></div>"
									+ "<div class='q_hd left'><label>回答:</label>"
									+ "<div class='t_xx'><ul id='xxtmbox"
									+ i
									+ "'><li id='aa"
									+ i
									+ "' onclick='choose(\"aa"
									+ i
									+ "\","
									+ i
									+ ")'>A</li><li id='ab"
									+ i
									+ "' onclick='choose(\"ab"
									+ i
									+ "\","
									+ i
									+ ")'>B</li><li id='ac"
									+ i
									+ "' onclick='choose(\"ac"
									+ i
									+ "\","
									+ i
									+ ")'>C</li><li id='ad"
									+ i
									+ "' onclick='choose(\"ad"
									+ i
									+ "\","
									+ i
									+ ")'>D</li></ul></div></div>";
						}
						if (tmtype == "主观题") {
							b = "subjectivity";// 标志为主观题
							a = "<div class='q_hdtext left'><label>说吧:</label><textarea id='objecttmbox"
									+ i
									+ "' class='qtextbox'></textarea>"
									+ "</div>";
						}
						h += "<div id='tmbox"
								+ i
								+ "' class='q_tm "
								+ b
								+ "'><a id='qQcodetmbox"
								+ i
								+ "' style='display:none;'>"
								+ data[i].questionQaCode
								+ "</a>"
								+ "<div class='q_th'>"
								+ data[i].orderByName
								+ "</div>"
								+ "<div class='q_tc left'><label>题目:</label><a class='tm'>"
								+ data[i].questionQaInfor.tm + "</a></div>" + a
								+ "</div>";
					}
					$("#qnContext").html(h);
				}
			});
}
function choose(a, b) {

	if ($("#xxtmbox" + b).find("li").hasClass("active")) {
		$("#xxtmbox" + b).find("li").removeClass("active")
	}
	$("#" + a).addClass("active");
	$("#xxtmbox"+b).addClass("selected");//判断选没选中

}

function save() {
	var userName = $("#userName").html();
	var createQuestionCode = $("#createQuestionCode").html();
	var titleid = $("#titleid").html();
	var questionQaCodelist = "";
	var daNamelist = "";
	$("div[id^='tmbox']").each(function() {
		var idcode = $(this).attr("id");
		questionQaCodelist += $("#qQcode" + idcode).html() + ",";
		if ($(this).hasClass("objectives")) {
			$("#xx" + idcode).find("li").each(function() {
				if ($(this).hasClass("active")) {
					daNamelist += $(this).html() + ",";
				}
			});
		} else {

			daNamelist += $("#object" + idcode).val() + ",";
		}

	});
	var t=true;
	$("ul[id^='xxtmbox']").each(function(){
		if(!$(this).hasClass("selected")){
			$(this).find("li").addClass("eli");
			t=false;
		}
	})
	if(!t){
		alert("有漏选！");
		return; //检验有没有漏选
	}
	if ($("div[id^='tmbox']").hasClass("subjectivity")) {//检验主观题是否为空
		if ($("textarea[id^='objecttmbox']").val() == "") {
			$("textarea[id^='objecttmbox']").addClass("texterror");
			return;
		}
	}

	$.ajax({
		url : project.name + "checkWitch/insert.do",
		data : {

			createQuestionCode : createQuestionCode,
			userName : userName,
			titleid : titleid,
			questionQaCodelist : questionQaCodelist,
			daNamelist : daNamelist

		},
		contentType : "application/x-www-form-urlencoded;charset=utf-8",
		dataType : "json",
		success : function(data) {
			window.location.href = project.name+"checkWitch/tosuccess.shtml";
		}
	});
}