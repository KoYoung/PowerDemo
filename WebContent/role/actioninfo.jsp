<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:include page="/common/common.jsp"></jsp:include>
<table id="actioninfo"></table>
<div id="showAddMenu" style="diaplay:none">
	<form method="post" id="menuForm">
		<table class="table_form" width="100%">
			<tr>
			<input id="actionid" type="hidden" name="actioninfo.actionId" />
				<th>菜单名称</th>
				<td><input id="actionname" name="actioninfo.actionname"
					class="easyui-validatebox" data-options="required:true" value=" "></td>
				<th>排序</th>
				<td><input id="ss" class="easyui-numberspinner"
					style="width: 50px;" required="required" value="1"
					data-options="min:1,max:10,editable:false"
					name="actioninfo.actionSort" id="actionSort"></td>
			</tr>
			<tr>
				<th>父菜单</th>
				<td><input type="hidden" name="actioninfo.actionParent"
					id="parent"><input type="text" name="parentname"
					id="parentname" readonly="readonly"></td>
				<th>选择图标</th><td><input name="actioninfo.actionPic" id="pic" onclick="initPic()"/></td>
			</tr>
			<tr>
				<th>链接地址</th>
				<td colspan="3"><input name="actioninfo.actionUrl" id="url" size='50'></td>
			</tr>
		</table>
	</form>
</div>
<div id="mm" class="easyui-menu" style="width:120px" data-options="onClick:menuHandler">
	<div data-options="name:'save',iconCls:'icon-edit_add'">增加下级菜单</div>
	<div data-options="name:'top',iconCls:'icon-edit_add'">增加顶级菜单</div>
	<div data-options="name:'edit',iconCls:'icon-edit'">修改菜单</div>
	<div data-options="name:'del',iconCls:'icon-remove'">删除</div>
</div>
<div id="initpics">
<img src='../js/themes/icons/add_square.png' id='icon-add_square'>
<img src='../js/themes/icons/airmail.png' id='icon-airmail'>
<img src='../js/themes/icons/back.png' id='icon-back'>
<img src='../js/themes/icons/binocular.png' id='icon-binocular'>
<img src='../js/themes/icons/black_board.png' id='icon-black_board'>
<img src='../js/themes/icons/blank.png' id='icon-blank'>
<img src='../js/themes/icons/blue_lego.png' id='icon-blue_lego'>
<img src='../js/themes/icons/bonsai.png' id='icon-bonsai'>
<img src='../js/themes/icons/bookmark.png' id='icon-bookmark'>
<img src='../js/themes/icons/calculator.png' id='icon-calculator'>
<img src='../js/themes/icons/calendar.png' id='icon-calendar'>
<img src='../js/themes/icons/calendar1.png' id='icon-calendar1'>
<img src='../js/themes/icons/cancel.png' id='icon-cancel'>
<img src='../js/themes/icons/caution.png' id='icon-caution'>
<img src='../js/themes/icons/charged.png' id='icon-charged'>
<img src='../js/themes/icons/charge_100.png' id='icon-charge_100'>
<img src='../js/themes/icons/charge_20.png' id='icon-charge_20'>
<img src='../js/themes/icons/charge_50.png' id='icon-charge_50'>
<img src='../js/themes/icons/charge_75.png' id='icon-charge_75'>
<img src='../js/themes/icons/charging.png' id='icon-charging'>
<img src='../js/themes/icons/clapperboard.png' id='icon-clapperboard'>
<img src='../js/themes/icons/clapperboard_open.png' id='icon-clapperboard_open'>
<img src='../js/themes/icons/clipped_id.png' id='icon-clipped_id'>
<img src='../js/themes/icons/close.png' id='icon-close'>
<img src='../js/themes/icons/close_square.png' id='icon-close_square'>
<img src='../js/themes/icons/compact_disc.png' id='icon-compact_disc'>
<img src='../js/themes/icons/compose.png' id='icon-compose'>
<img src='../js/themes/icons/computer-keyboard.png' id='icon-computer-keyboard'>
<img src='../js/themes/icons/contacts-alt.png' id='icon-contacts-alt'>
<img src='../js/themes/icons/contacts.png' id='icon-contacts'>
<img src='../js/themes/icons/counter.png' id='icon-counter'>
<img src='../js/themes/icons/cut.png' id='icon-cut'>
<img src='../js/themes/icons/denied.png' id='icon-denied'>
<img src='../js/themes/icons/direction_board.png' id='icon-direction_board'>
<img src='../js/themes/icons/document.png' id='icon-document'>
<img src='../js/themes/icons/done_square.png' id='icon-done_square'>
<img src='../js/themes/icons/door.png' id='icon-door'>
<img src='../js/themes/icons/draft.png' id='icon-draft'>
<img src='../js/themes/icons/edit_add.png' id='icon-edit_add'>
<img src='../js/themes/icons/edit_remove.png' id='icon-edit_remove'>
<img src='../js/themes/icons/envelope.png' id='icon-envelope'>
<img src='../js/themes/icons/filesave.png' id='icon-filesave'>
<img src='../js/themes/icons/filter.png' id='icon-filter'>
<img src='../js/themes/icons/floppy.png' id='icon-floppy'>
<img src='../js/themes/icons/folder.png' id='icon-folder'>
<img src='../js/themes/icons/green_chalkboard.png' id='icon-green_chalkboard'>
<img src='../js/themes/icons/hd.png' id='icon-hd'>
<img src='../js/themes/icons/hd_win.png' id='icon-hd_win'>
<img src='../js/themes/icons/help.png' id='icon-help'>
<img src='../js/themes/icons/help_circle.png' id='icon-help_circle'>
<img src='../js/themes/icons/highlighter.png' id='icon-highlighter'>
<img src='../js/themes/icons/home.png' id='icon-home'>
<img src='../js/themes/icons/id.png' id='icon-id'>
<img src='../js/themes/icons/images.png' id='icon-images'>
<img src='../js/themes/icons/images_night_scene.png' id='icon-images_night_scene'>
<img src='../js/themes/icons/inbox.png' id='icon-inbox'>
<img src='../js/themes/icons/inbox_full.png' id='icon-inbox_full'>
<img src='../js/themes/icons/internal_hd.png' id='icon-internal_hd'>
<img src='../js/themes/icons/key.png' id='icon-key'>
<img src='../js/themes/icons/large_chart.png' id='icon-large_chart'>
<img src='../js/themes/icons/large_clipart.png' id='icon-large_clipart'>
<img src='../js/themes/icons/large_picture.png' id='icon-large_picture'>
<img src='../js/themes/icons/large_shapes.png' id='icon-large_shapes'>
<img src='../js/themes/icons/large_smartart.png' id='icon-large_smartart'>
<img src='../js/themes/icons/library_occupied.png' id='icon-library_occupied'>
<img src='../js/themes/icons/lock.png' id='icon-lock'>
<img src='../js/themes/icons/magic_wand.png' id='icon-magic_wand'>
<img src='../js/themes/icons/mail.png' id='icon-mail'>
<img src='../js/themes/icons/mail_new.png' id='icon-mail_new'>
<img src='../js/themes/icons/mail_open.png' id='icon-mail_open'>
<img src='../js/themes/icons/manual.png' id='icon-manual'>
<img src='../js/themes/icons/maximize_square.png' id='icon-maximize_square'>
<img src='../js/themes/icons/minimize_square.png' id='icon-minimize_square'>
<img src='../js/themes/icons/mini_add.png' id='icon-mini_add'>
<img src='../js/themes/icons/mini_edit.png' id='icon-mini_edit'>
<img src='../js/themes/icons/mini_refresh.png' id='icon-mini_refresh'>
<img src='../js/themes/icons/monitor.png' id='icon-monitor'>
<img src='../js/themes/icons/monitor_off.png' id='icon-monitor_off'>
<img src='../js/themes/icons/movies.png' id='icon-movies'>
<img src='../js/themes/icons/mp3_player_black.png' id='icon-mp3_player_black'>
<img src='../js/themes/icons/mp3_player_white.png' id='icon-mp3_player_white'>
<img src='../js/themes/icons/music.png' id='icon-music'>
<img src='../js/themes/icons/newspaper.png' id='icon-newspaper'>
<img src='../js/themes/icons/no.png' id='icon-no'>
<img src='../js/themes/icons/note-add.png' id='icon-note-add'>
<img src='../js/themes/icons/note-remove.png' id='icon-note-remove'>
<img src='../js/themes/icons/note.png' id='icon-note'>
<img src='../js/themes/icons/notepad.png' id='icon-notepad'>
<img src='../js/themes/icons/ok.png' id='icon-ok'>
<img src='../js/themes/icons/pencil.png' id='icon-pencil'>
<img src='../js/themes/icons/popcorn_bucket.png' id='icon-popcorn_bucket'>
<img src='../js/themes/icons/popcorn_full.png' id='icon-popcorn_full'>
<img src='../js/themes/icons/preferences.png' id='icon-preferences'>
<img src='../js/themes/icons/print.png' id='icon-print'>
<img src='../js/themes/icons/printer.png' id='icon-printer'>
<img src='../js/themes/icons/rc.png' id='icon-rc'>
<img src='../js/themes/icons/recycle_bin_empty.png' id='icon-recycle_bin_empty'>
<img src='../js/themes/icons/recycle_bin_full.png' id='icon-recycle_bin_full'>
<img src='../js/themes/icons/redo.png' id='icon-redo'>
<img src='../js/themes/icons/reload.png' id='icon-reload'>
<img src='../js/themes/icons/removable_drive.png' id='icon-removable_drive'>
<img src='../js/themes/icons/remove_square.png' id='icon-remove_square'>
<img src='../js/themes/icons/rss.png' id='icon-rss'>
<img src='../js/themes/icons/ruled_page.png' id='icon-ruled_page'>
<img src='../js/themes/icons/search.png' id='icon-search'>
<img src='../js/themes/icons/send.png' id='icon-send'>
<img src='../js/themes/icons/shortkey.png' id='icon-shortkey'>
<img src='../js/themes/icons/soccer_ball.png' id='icon-soccer_ball'>
<img src='../js/themes/icons/sofa.png' id='icon-sofa'>
<img src='../js/themes/icons/spam_mail.png' id='icon-spam_mail'>
<img src='../js/themes/icons/speaker.png' id='icon-speaker'>
<img src='../js/themes/icons/speaker_mute.png' id='icon-speaker_mute'>
<img src='../js/themes/icons/speaker_volume_control.png' id='icon-speaker_volume_control'>
<img src='../js/themes/icons/sum.png' id='icon-sum'>
<img src='../js/themes/icons/tag.png' id='icon-tag'>
<img src='../js/themes/icons/text_edit.png' id='icon-text_edit'>
<img src='../js/themes/icons/ticket_golden.png' id='icon-ticket_golden'>
<img src='../js/themes/icons/ticket_golden_punched.png' id='icon-ticket_golden_punched'>
<img src='../js/themes/icons/ticket_red.png' id='icon-ticket_red'>
<img src='../js/themes/icons/ticket_red_punched.png' id='icon-ticket_red_punched'>
<img src='../js/themes/icons/tip.png' id='icon-tip'>
<img src='../js/themes/icons/trash.png' id='icon-trash'>
<img src='../js/themes/icons/trash_full.png' id='icon-trash_full'>
<img src='../js/themes/icons/tree.png' id='icon-tree'>
<img src='../js/themes/icons/undo.png' id='icon-undo'>
<img src='../js/themes/icons/usb.png' id='icon-usb'>
<img src='../js/themes/icons/videos.png' id='icon-videos'>
<img src='../js/themes/icons/volume_level_1.png' id='icon-volume_level_1'>
<img src='../js/themes/icons/volume_level_2.png' id='icon-volume_level_2'>
<img src='../js/themes/icons/volume_lvl_3.png' id='icon-volume_lvl_3'>
<img src='../js/themes/icons/wand.png' id='icon-wand'>
<img src='../js/themes/icons/white_lego.png' id='icon-white_lego'>
<img src='../js/themes/icons/wireless_keyboard.png' id='icon-wireless_keyboard'>
<img src='../js/themes/icons/yellow_lego.png' id='icon-yellow_lego'>
<img src='../js/themes/icons/zip.png' id='icon-zip'>
</div>
<script>
var gridTree;
$(function() {
	showWin();
	gridTree = $("#actioninfo").treegrid({
		nowrap : true,
		striped : true,
		pagination : true,
		rownumbers : false,
		collapsible : false,
		pageList : [ 10, 20, 30, 40 ],
		singleSelect : true,
		url : myObjectPath + '/role/getMenusInfo.action',
		sortOrder : 'desc',
		remoteSort : false,
		fitColumns : true,
		idField : 'actionId',
		treeField : 'actionname',
		animate:true,
		fit : true,
		toolbar : [ {
			text : "添加信息",
			iconCls : "icon-edit_add",
			handler : function() {
				$("#parent").val(0);
				$("#showAddMenu").show().dialog("open");
			}
		},"-",{
			text : "<input id='sreachName'>"
		} ],
		columns : [ [  {field:'ck',checkbox:true},
		              {field : 'actionId',title : 'actionId',align : 'center',width : 150,sortable : true,hidden : true}, 
		              {field : 'actionname',title : '菜单名称',align : 'left',width : 100,sortable : true}, 
		              {field : 'actionParent',title : '父菜单',align : 'center',width : 100,sortable : true,
						formatter: function(value,row,index){
							if(value==0)return "";
							return row.parentName;
						}}, 
					  {field : 'parentName',title : '上级菜单',align : 'center',width : 100,sortable : true,hidden:true}, 
					  {field : 'actionPic',title : '图标',align : 'center',width : 100,sortable : true,
						  formatter: function(value,row,index){
							  	value=value.replace("icon-","");
								return "<img src='../js/themes/icons/"+value+".png'>";
							}}, 
					  {field : 'actionUrl',title : '链接地址',align : 'center',width : 100,sortable : true},
					  {field : 'actionSort',title : '排序',align : 'center',width : 100,sortable : true}]],
		onContextMenu:function(e,row){
			$("#actioninfo").treegrid("select",row.actionId);//选中右键所在行
			$('#mm').menu('show',{
				 left: e.pageX,
				 top: e.pageY
			}); 
			e.preventDefault();
		},onLoadSuccess:function(row,data){//grid加载成功后的事件
			$("#sreachName").searchbox({
				searcher:function(value,name){ 
					gridTree.treegrid({
						queryParams: {
							"actioninfo.actionname":value
						}
					});
			    }
			});
		},onDblClickRow:function(row){
			toedit();
		}
	});
})

function saveMenu() {
	$('#menuForm').form('submit', {
		url : myObjectPath + "/role/addMenusinfo.action",
		onSubmit : function() {
			$("#actionname").val($("#actionname").val().replace(" ",""));
			return $(this).form("validate");
		},
		success : function(data) {
			if (data == 'success') {
				showMessage("成功提示", "系统菜单添加成功...")
				$("#showAddMenu").dialog("close");
				$('#menuForm').form("clear");
				$("#actionname").val(' ');
				gridTree.treegrid("reload");
			} else {
				showMessage("提示", data);
			}
		}
	});
}
function showWin(){
	$("#initpics").dialog({
		title : "",
		width : 300,
		modal : true,
		shadow : true,
		closed : true,
		height : 200,
		resizable : false,
		collapsible : false,
		minimizable : false,
		maximizable : false,
		closable:true,
		border:false
	});
	$("#showAddMenu").dialog({
		title : "菜单添加",
		width : 500,
		modal : true,
		shadow : true,
		closed : true,
		height : 200,
		resizable : false,
		collapsible : false,
		minimizable : false,
		maximizable : false,
		closable:true,
		icon : 'icon-save',
		buttons : [ {
			text : '确定',
			plain : true,
			iconCls : 'icon-save',
			handler : function() {
				saveMenu();
			}
		}, {
			text : '取消',
			plain : true,
			iconCls : 'icon-no',
			handler : function() {
				$("#showAddMenu").dialog("close");
			}
		}],
		onClose:function(){
			$('#menuForm').form("reset");
		}
	});
}
function menuHandler(item){
	switch(item.name){
	case 'save' :
		var node = gridTree.treegrid("getSelected");
		$("#parentname").val(node.actionname);
		$("#parent").val(node.actionId);
		$("#showAddMenu").show().dialog("open");
		break;
	case 'top' :
		$("#parentname").val("最上层菜单");
		$("#parent").val(0);
		$("#showAddMenu").show().dialog("open");
		break;
	case 'edit' :
		toedit();
		break;
	case 'del' :
		var node = gridTree.treegrid("getSelected");
		$.messager.confirm('操作提示', '您确定要删除此项菜单吗？', function(r){
			if (r){
				$.ajax({
					  type: 'POST',
					  url: myObjectPath + "/role/deleteMenuinfo.action",
					  data: {"actioninfo.actionId":node.actionId},
					  success:function(result){
						 if(result=='success'){
							 showMessage("操作提示","操作已成功");
						 	 gridTree.treegrid("reload");
						 }else{
							 showMessage("操作提示",result);
						 }
					  },
					  dataType: "text"
					});
			}
		});
		break;
	}
}
function initPic(){
	$("#initpics").dialog("open");
	var imgs = $("#initpics img");
	for(var i =0;i<imgs.length;i++){
		$(imgs[i]).click(function(){
			$("#pic").val(($(this).attr("id")));
			$("#initpics").show().dialog("close");
		});
	}
}
function toedit(){
	var node = gridTree.treegrid("getSelected");
	$("#actionid").val(node.actionId);
	$("#actionname").val(node.actionname);
	$("#parentname").val(node.actionParent=='0'?'':node.parentName);
	$("#parent").val(node.actionParent);
	$("#pic").val(node.actionPic);
	$("#url").val(node.actionUrl);
	$("#ss").numberspinner("setValue",node.actionSort);
	$("#showAddMenu").show().dialog("open");
}
</script>
