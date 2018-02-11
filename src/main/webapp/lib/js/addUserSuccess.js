$(document).ready(function(){
	showWhich();
	
	
	
});
function showWhich(){
	var tip=$("#witchinfor").html();
	if(tip=="1"){
		var h="<em class='success'></em> <a>上传成功</a>";
		$(".tishi").html(h);
		var b="<a class='succfont' href='"+project.name+"addUser/select.shtml'>返&nbsp;回&nbsp;上&nbsp;传&nbsp;页</a>";
		$(".errorusername").html(b);
		return;
	}
	if(tip=="0"){
		var h="<em class='false'></em> <a>上传失败</a>";
		$(".tishi").html(h);
		var userName=$("#userName").html();
		var b="<div class='wornguser'>该&nbsp;&nbsp;<a style='color:white'>"+userName+"</a>&nbsp;&nbsp;账号，已存在!</div><a class='succfont fial' href='"+project.name+"addUser/select.shtml'>" +
				"重&nbsp;新&nbsp;上&nbsp;传</a>";
		$(".errorusername").html(b);
		return;
	}
}
