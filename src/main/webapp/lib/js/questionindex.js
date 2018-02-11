$(document).ready(function() {
	$('#tt').tabs({
		border: false,
		fit:true,
		plain:true,
		tabPosition:'left',
		headerWidth:80
	});
	//菜单栏按钮
	$('#addnewj').linkbutton({
		plain: true,
		width: 180,
		height: 30
	});
	$('#addnewt').linkbutton({
		plain: true,
		width: 180,
		height: 30
	});
	$('#selectjk').linkbutton({
		plain: true,
		width: 180,
		height: 30
	});
	$('#selecttk').linkbutton({
		plain: true,
		width: 180,
		height: 30
	});
	$('#teachj').linkbutton({
		plain: true,
		width: 180,
		height: 30
	});

	
	

});

	function showtabs(titleText, url) {
		var content="<iframe src='"+url+"' frameborder=0 scrolling='auto' style='width:100%;height:100%'/>"
			if($('#tt').tabs("exists", titleText)) {
            $('#tt').tab("select", titleText);
//            var tab = $('#tt').tabs('getSelected');  // 获取选择的面板
//            $('#tt').tabs('update', {
//            	tab: tab,
//            	options: {
//            		href:url  // 新内容的URL
//            	}
//            });

			} else {
				
				$('#tt').tabs('add', {
					title: titleText,
					content: content,
					closable: true //可关闭
				});

			}

		}