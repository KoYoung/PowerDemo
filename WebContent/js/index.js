
window.onload = function() {
	$('#loading-mask').fadeOut();
}
var onlyOpenTitle = "欢迎使用";// 不允许关闭的标签的标题
var _menus="";
$(function() {
	
})
function getMenus(){
	$.ajax({
		url:myObjectPath + '/getMenus.action',
		data:{time:new Date()},
		dataType:'json',
		success:function(data){
			_menus=data;
			InitLeftMenu();
			tabClose();
			tabCloseEven();
			var opentabs = $('#tabs').tabs('tabs');
			if(opentabs.length>0){
				for(var i =opentabs.length-1;i>0;i--){
					var title = opentabs[i].panel('options').title;
					if(title!='欢迎使用'){
						$('#tabs').tabs('close',title)
					}
				}
			}
		}
	});
}

// 初始化左侧
function initLeftChilren(n,menulist){
	$.each(n.children, function(j, o) {
		var url = o.actionUrl.indexOf("http")>=0?o.actionUrl:myObjectPath+"/"+o.actionUrl;
		menulist += '<li><div><a ref="' + o.actionId + '" href="#" rel="'+url+'" ><span class="icon ' + o.actionPic
				+ '" >&nbsp;</span><span class="nav">' + o.actionname
				+ '</span></a></div> ';
		if (o.children && o.children.length > 0) {
			menulist += '<ul class="third_ul">';
			menulist=initLeftChilren(o,menulist);
			menulist += '</ul>';
		}
		menulist += '</li>';
	})
	return menulist;
}


function InitLeftMenu() {
	$("#nav").accordion({
		animate : false,
		fit : true,
		border : false
	});
	var panels = $("#nav").accordion("panels");
	if(panels.length>0 ){
		for(var i=(panels.length-1);i>=0;i--){
			var index = $("#nav").accordion("getPanelIndex",panels[i]);
			$("#nav").accordion("remove",index);
		}
	}
	var selectedPanelname = '';
	$.each(_menus.rows, function(i, n) {
		var menulist = '';
		menulist += '<ul class="navlist">';
		menulist=initLeftChilren(n,menulist);
		menulist += '</ul>';

		$('#nav').accordion('add', {
			title : n.actionname,
			content : menulist,
			border : false,
			iconCls : 'icon ' + n.actionPic
		});

		if (i == 0)
			selectedPanelname = n.actionname;

	});
	$('#nav').accordion('select', selectedPanelname);

	$('.navlist li a').click(function() {
		var tabTitle = $(this).children('.nav').text();
		var url = $(this).attr("rel");
		var menuid = $(this).attr("ref");
		var icon = $(this).find('.icon').attr('class');
		var third = find(menuid);
		if (third && third.children && third.children.length > 0) {
			$('.third_ul').slideUp();
			var ul = $(this).parent().next();
			if (ul.is(":hidden"))
				ul.slideDown();
			else
				ul.slideUp();
		} else {
			addTab(tabTitle, url, icon);
			$('.navlist li div').removeClass("selected");
			$(this).parent().addClass("selected");
		}
	}).hover(function() {
		$(this).parent().addClass("hover");
	}, function() {
		$(this).parent().removeClass("hover");
	});

	// 选中第一个
	// var panels = $('#nav').accordion('panels');
	// var t = panels[0].panel('options').title;
	// $('#nav').accordion('select', t);
}
// 获取左侧导航的图标
function getIcon(menuid) {
	var icon = 'icon ';
	$.each(_menus.rows, function(i, n) {
		$.each(n.children, function(j, o) {
			if (o.actionId == menuid) {
				icon += o.icon;
			}
		})
	})

	return icon;
}

function find(menuid) {
	var obj = null;
	$.each(_menus.rows, function(i, n) {
		obj=findChildren(n,menuid,obj);
	});

	return obj;
}
function findChildren(n,menuid,obj){
	$.each(n.children, function(j, o) {
		if (o.actionId == menuid) {
			obj = o;
		}else{
			obj=findChildren(o,menuid,obj)
		}
	});
	return obj;
}
function addTab(subtitle, url, icon) {
	if (!$('#tabs').tabs('exists', subtitle)) {
		$('#tabs').tabs('add', {
			title : subtitle,
			content : createFrame(url),
			closable : true,
			icon : icon
		});
	} else {
		$('#tabs').tabs('select', subtitle);
		$('#mm-tabupdate').click();
	}
	tabClose();
}

function createFrame(url) {
	var s = '<iframe name="MyFrame" scrolling="auto" frameborder="0"  src="' + url
			+ '" style="width:100%;height:100%;"></iframe>';
	return s;
}

function tabClose() {
	/* 双击关闭TAB选项卡 */
	$(".tabs-inner").dblclick(function() {
		var subtitle = $(this).children(".tabs-closable").text();
		$('#tabs').tabs('close', subtitle);
	})
	/* 为选项卡绑定右键 */
	$(".tabs-inner").bind('contextmenu', function(e) {
		$('#mm').menu('show', {
			left : e.pageX,
			top : e.pageY
		});

		var subtitle = $(this).children(".tabs-closable").text();

		$('#mm').data("currtab", subtitle);
		$('#tabs').tabs('select', subtitle);
		return false;
	});
}

// 绑定右键菜单事件
function tabCloseEven() {

	$('#mm').menu({
		onClick : function(item) {
			closeTab(item.id);
		}
	});

	return false;
}

function closeTab(action) {
	var alltabs = $('#tabs').tabs('tabs');
	var currentTab = $('#tabs').tabs('getSelected');
	var allTabtitle = [];
	$.each(alltabs, function(i, n) {
		allTabtitle.push($(n).panel('options').title);
	})

	switch (action) {
	case "refresh":
		var iframe = $(currentTab.panel('options').content);
		var src = iframe.attr('src');
		$('#tabs').tabs('update', {
			tab : currentTab,
			options : {
				content : createFrame(src)
			}
		})
		break;
	case "close":
		var currtab_title = currentTab.panel('options').title;
		$('#tabs').tabs('close', currtab_title);
		break;
	case "closeall":
		$.each(allTabtitle, function(i, n) {
			if (n != onlyOpenTitle) {
				$('#tabs').tabs('close', n);
			}
		});
		break;
	case "closeother":
		var currtab_title = currentTab.panel('options').title;
		$.each(allTabtitle, function(i, n) {
			if (n != currtab_title && n != onlyOpenTitle) {
				$('#tabs').tabs('close', n);
			}
		});
		break;
	case "closeright":
		var tabIndex = $('#tabs').tabs('getTabIndex', currentTab);

		if (tabIndex == alltabs.length - 1) {
			alert('后边没有啦 ^@^!!');
			return false;
		}
		$.each(allTabtitle, function(i, n) {
			if (i > tabIndex) {
				if (n != onlyOpenTitle) {
					$('#tabs').tabs('close', n);
				}
			}
		});

		break;
	case "closeleft":
		var tabIndex = $('#tabs').tabs('getTabIndex', currentTab);
		if (tabIndex == 1) {
			alert('左边已经没有菜单了   ^@^!!');
			return false;
		}
		$.each(allTabtitle, function(i, n) {
			if (i < tabIndex) {
				if (n != onlyOpenTitle) {
					$('#tabs').tabs('close', n);
				}
			}
		});

		break;
	case "exit":
		$('#closeMenu').menu('hide');
		break;
	}
}

// 弹出信息窗口 title:标题 msgString:提示信息 msgType:信息类型 [error,info,question,warning]
function msgShow(title, msgString, msgType) {
	$.messager.alert(title, msgString, msgType);
}
// 屏蔽右键菜单
$(document).bind("contextmenu", function(e) {
	return false;
});
