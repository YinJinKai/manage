$(document).ready(function(){
	$("#errortip").html("");
	
});
function check (){
	  var excel_file = $("#excel_file").val();  
	  var afterName = "."+excel_file.replace(/.+\./,"");
    if (excel_file == "" || excel_file.length == 0) {  
        alert("请选择文件路径！");  
        return false;  
    } else {  
    	if(afterName!=".xls"){ //判断属性名太少
    	      alert("请上传excel文件!");  
    	        return false; 
    	}
       return true;  
    }  
}
function f1(){
	var excel_file = $("#excel_file").val();
	$("#filename").val(excel_file);
}
function showexport(){
	if($("#databox").hasClass("hide")){
		$("#databox").removeClass("hide");
		$("#databox").slideUp();
		return;
	}
	$("#databox").addClass("hide");
	$("#databox").slideDown();
}
function showtongji(){
	$(".bg-layer").show();
	$(".tongjibiao").show();
	
	
};
function closetongji(){
	
	$(".bg-layer").slideUp();
	$(".tongjibiao").hide();
	
	
	
}