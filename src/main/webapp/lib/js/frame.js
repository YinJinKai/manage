
$(document).ready(function() {

	showLogintip();
	loadlogininfor();
	loadQianTime();
	loadMenu();
	$('#p').panel({
		headerCls:'st',
		bodyCls:'bst',
		width: 1200,
		height: 620,
		left: 127,
		top:50,
		draggable:true,
		iconCls: 'icon-save',
		closable: true,
		collapsible: true,
		minimizable: true,
		maximizable: true,
		border:0,
        // openDuration:1000,
        // closeDuration:1000,
		closeAnimation: 'slide',
		openAnimation:'fade'
	});

// 移动面板定位
	$('#p').panel('panel').css('position', 'absolute');
// 面板关闭按钮
	$('#p').panel({
		onClose: function() {
			$(".y_frame").hide();
			return
		}

	});
// 面板最小化按钮
	$('#p').panel({
		onMinimize: function() {
			$(".y_frame").hide();
			return
		}
	});
// 签到按钮
	$("#qiandao").switchbutton({
		onText:'已签',
		offText:'签到',
		value:'1'
		
	});
	//签到功能
	$('#qiandao').switchbutton({
		onChange:function(){
			$('#qiandao').switchbutton('disable');
			 var status=$('#qiandao').val();
			 var timeName=$('#timecode').html();
			 $.ajax({
					url: project.name+ "attendance/insertSign.do",
					data:{
						status:status,
						timeName:timeName
					},
					contentType : "application/x-www-form-urlencoded;charset=utf-8",
					dataType : "json",
					success : function(data) {
						alert('succeed');
					}
				});
			
			
		}
	});
	showcenter();
});
//显示登录信息
function showcenter() {
		$("#userinfor").hover(function() {
			
			$(".particulars").slideDown();
			}, function(){
				$(".particulars").slideUp();
			
			});
	}
function loadlogininfor(){
	 $.ajax({
			url: project.name+ "user/getSession.do",
			data:{
			},
			contentType : "application/x-www-form-urlencoded;charset=utf-8",
			dataType : "json",
			success : function(data) {
				var h="";
				var a="";
				h+="<em class='usericon'></em><a class='logininfor'>"+data[0].trueName+"</a><em class='roleicon'></em><a>"+data[0].roleName+"</a>";
				a+="<div class='userinfor'><img class='renicon' src='/manage/lib/img/ren.png'></div>";
				a+="<div class='userinfor'><a class='infortitle'>账号:</a><a class='invalue'>"+data[0].userName+"</a></div>";
				a+="<div class='userinfor'><a class='infortitle'>班级:</a><a class='invalue'>"+data[0].className+"</a></div>";
//				a+="<div class='userinfor'><a class='infortitle'>讲师:</a><a class='invalue'>"+data[0].password+"</a></div>";
				h+="<div class='particulars'>"+a+"</div>";
				
				
                $("#userinfor").html(h);
			}
		});
	
}
// 显示签到框
function f2(){
if($(".setting").hasClass("showSign")){
		$(".tcombo").fadeOut();
		$(".qiandao_mask").hide();
		$(".setting").removeClass("showSign");
		return
	}
	$(".setting").addClass("showSign")
	$(".qiandao_mask").show();
	$(".tcombo").fadeIn("slow");
}






// 加载权限菜单
function loadMenu() {
	var roleCode=$("#roleCode").html();
	 $.ajax({
			url: project.name+ "menu/loadMenu.do",
			data:{
				roleCode:roleCode
			},
			contentType : "application/x-www-form-urlencoded;charset=utf-8",
			dataType : "json",
			success : function(data) {
				var h="";
				if(data[0].roleCode==1){
					 h+="<div onclick='f2()' class='shortcut' style='z-index:1499;position: absolute;margin-top: 87px;'><img class='icon' src='/manage/lib/img/wh.png'>"
						+"<div class='title'>签到</div></div>";
					
				}
				
				for (var i = 0; i < data.length; i++) {
				 var x=project.name+data[i].url;
				 h+="<div onclick='page(\""+data[i].permissionName+"\",\""+x+"\")' class='shortcut' id='shortcut"+i+"'>"+
                "<img class='icon' src='/manage/lib/img/Music.png'><div class='title'>"+data[i].permissionName+"</div></div>";
				
				}
               $("#appDesk").html(h);
			}
		});
	
}
// 显示面板
function page(a,url){
var content = "<iframe src='" + url + "' frameborder=0 scrolling='auto' style='width:100%;height:100%;'/>"
$(".y_frame").fadeIn();
$('#p').panel({
	title:a,
	content:content
	
});

$('#p').panel('open')
}
// 加载签到面板
function loadQianTime(){
	$.ajax({
		url: project.name+ "attendance/loadQianTime.do",
		data:{
		},
		contentType : "application/x-www-form-urlencoded;charset=utf-8",
		dataType : "json",
		success : function(data) {
			var s=data[0].status;
			if(s=="1"){//已签到
				$('#qiandao').switchbutton('check');
				$('#qiandao').switchbutton('disable');
			}else if(s=="0"){ //迟到
				$('#qiandao').switchbutton('disable');
			}
	       $("#timecode").html(data[0].timeName);
           $("#qianTime").html(data[0].signName);
		}
	});

}
//退出登录
function loginout(){
	window.location.href = project.name+"user/loginout.shtml";
}
function setting(){
	if($(".setting").hasClass("showSign")){
		$(".setting").slideUp();
		$(".setting").removeClass("showSign");
		return
	}
	$(".setting").addClass("showSign")
	$(".setting").slideDown();
}
function closeLogintip(){
	$("#logintip").fadeOut("slow");
}
function showLogintip() {
	$("#logintip").slideDown("10000");
}
function showTime(){
    nowtime=new Date();
    year=nowtime.getFullYear();
    month=nowtime.getMonth()+1;
    date=nowtime.getDate();
    document.getElementById("nowTime").innerText=year+"/"+month+"/"+date+" "+nowtime.toLocaleTimeString();
}

setInterval("showTime()",1000);