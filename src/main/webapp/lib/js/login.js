
$(document).ready(function() {
	$('#username').textbox({
		iconCls: 'icon-man',
		iconAlign: 'right',
		width: 200,
		height: 30,
		prompt: '用户名'

	});
	$("#password").passwordbox({ 
        prompt: 'Password', 
        width: 200,
		height: 30,
        showEye: true 
    }); 
	//日历
	$('#calendar').calendar({    
	    current:new Date()    
	}); 
	//日历面板
	$('#timePanel').panel({
		width: 250,
		height:300,
		left:1140,
		top:0,
		closed:true,
		title: '日历',
		iconCls: 'icon-ok',
		closable: true,
		collapsible: true,
//		minimizable: true,
//		openDuration:1000,
//		closeDuration:1000,
		closeAnimation: 'slide',
		openAnimation:'slide'
	});
	$('#timePanel').panel('panel').css('position', 'absolute');
});
//打开日历
function showCalen() {
	$('#timePanel').panel('open')
}
//权限变量
var a;
//显示登录
function login(b){
	$(".in_menu").fadeOut();
	$(".app2").fadeOut();
	$(".mapBox").fadeOut();
	$("#loginbody_mask").fadeIn();
	$("#loginBox").fadeIn();
	a=b;
}
//切换账号
function switchUser(){
	$("#username").textbox("setValue","");
	$("#password").textbox("setValue","");
    $("#loginbody_mask").fadeOut();
	$("#loginBox").fadeOut();
	$("#userErrorBox").hide();
	$("#passwordErrorBox").hide();
	$(".app2").fadeIn();
	$(".in_menu").fadeIn();
	$(".mapBox").fadeIn();
	
}
//登录
function showloading(){
	$(".loading").fadeIn();
	$(".login_box").fadeOut();
} 
function hideloading(){
 	$(".loading").fadeOut();
 	$(".login_box").fadeIn();
}
function userLogin(){
	showloading();
	
	var username=$("#username").val();
	var role=a;
	if(username==""){
		hideloading();
		$("#user_error").html("请填写用户名!");
		$("#userErrorBox").fadeIn();
		return
	}
	var password=$("#password").val();
	if(password==""){
		hideloading();
		$("#password_error").html("请填写密码!");
		$("#passwordErrorBox").fadeIn();
		return
	}
	$.ajax({
		url: project.name+"user/login.do",
		data:{
			userName:username,
			password:password,
			roleCode:role
		},
	    contentType: "application/x-www-form-urlencoded;charset=utf-8",
	    dataType: "text",
	    success: function(data){
	   
	    	if(data==0){
	    		hideloading();
	    		$("#user_error").html("用户名或密码有误!");
	    		$("#userErrorBox").fadeIn();
	    		return
	    	}
	    	if(data=="a"){
	    		hideloading();
	    		$("#user_error").html("该账号无此权限");
	    		$("#userErrorBox").fadeIn();
	    		return
	    	}
	    	var id=data;
	        if(data!=""){
	        	window.location.href = project.name+"user/index.shtml?roleCode="+id+"";
	        	return
	        }
	    }
		
		
		
		
		
	});
	
	
}
function showtable(){
	if($(".inbox").hasClass("hide")){
		$(".inbox").removeClass("hide");
		return;
	}
	$(".inbox").addClass("hide");
}
function showTime(){
    nowtime=new Date();
    year=nowtime.getFullYear();
    month=nowtime.getMonth()+1;
    date=nowtime.getDate();
    document.getElementById("nowTime").innerText=year+"/"+month+"/"+date+" "+nowtime.toLocaleTimeString();
}

setInterval("showTime()",1000);