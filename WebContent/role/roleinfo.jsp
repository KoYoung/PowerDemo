<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:include page="/common/common.jsp"></jsp:include>
<body class="easyui-layout">
<div data-options="region:'west',title:'',split:false"
		style="width: 180px;">
	<div id="roleleftpanel" class="easyui-panel" title="角色信息" data-options="fit:true,border:false">
		<ul id="roleTree"></ul>
	</div>
</div>
<div data-options="region:'center',title:'所属权限菜单'">
	<table id="permitinfo"></table>
</div>
<div id="mm" class="easyui-menu" style="width: 120px"
	data-options="onClick:menuHandler">
	<div data-options="name:'save',iconCls:'icon-add'">增加</div>
	<div data-options="name:'edit',iconCls:'icon-edit'">修改</div>
	<div data-options="name:'del',iconCls:'icon-remove'">删除</div>
</div>
<script>
var  nodeText='';
var nodeIndex=0;
var tree;
var roletree;
	$(function() {
		//角色左边菜单的panel
		$("#roleleftpanel").panel({
			tools:[{iconCls:'icon-save',handler:function(){
				save();
			}},{iconCls:'icon-remove',handler:function(){
				del();
			}}]
		});
		//角色左边的菜单
		initRoleTree();
	});
	
	//加载角色
	function initRoleTree(){
		roletree = $("#roleTree").tree({
			url : myObjectPath + '/role/getRoles.action',
			onContextMenu : function(e, node) {
				$("#roleTree").tree("select",node.target);
				e.preventDefault();//屏蔽系统右键菜单
				$("#mm").menu('show', {
					left : e.pageX,
					top : e.pageY
				});
			},onLoadSuccess:function(v,data){
				if(data[0].id!=0){
					var node= $('#roleTree').tree('find', data[0].id);
					$("#roleTree").tree("select",node.target);
					showpermitinfo();
				}
			},	onDblClick:function(node){
				$("#roleTree").tree("beginEdit",node.target);
			},onClick:function(node){
				showpermitinfo();
			},onBeforeEdit:function(node){
				nodeText=node.text;
			},	onAfterEdit:function(node){
				$.ajax({
					url:myObjectPath + '/role/addRoles.action',
					type:'post',
					data:{
						'role.roleId':node.id,
						'role.roleName':node.text
					},
					dataType:'json',
					success:function(data){
						if(data.msg!='success'){
							showMessage("提示",data);
							$("#roleTree").tree("update",{target:node.target,text:nodeText});
						}else{
							nodeIndex=0;
							$("#roleTree").tree("update",{target:node.target,id:data.index});
						}
					}
				});
			}
		});
	}
	//右键的菜单操作
	function menuHandler(item){
		switch(item.name){
		case 'save' :
			save();
			break;
		case 'edit' :
			var node = $("#roleTree").tree("getSelected");
			$("#roleTree").tree("beginEdit",node.target);
			break;
		case 'del' :
			del();
			break;
		}
	}
	//保存角色的操作
	function save(){
		if(nodeIndex==0){
			roletree.tree('append', {
				data: [{
					id: nodeIndex,
					text: '新增角色'
				}]
			})
			var node = roletree.tree('find', 0);
			roletree.tree('beginEdit', node.target);
		}
	}
	//删除角色的操作
	function del(){
		var node = roletree.tree("getSelected");
		if(node){
			$.messager.confirm('操作提示', '您确定要删除此项菜单吗？', function(r){
				if (r){
					$.ajax({
						  type: 'POST',
						  url: myObjectPath + "/role/deleteRole.action",
						  data: {"role.roleId":node.id},
						  success:function(result){
							  showMessage("操作提示",result);
							  roletree.tree("remove",node.target);
						  },
						  dataType: "text"
						});
				}
			});
		}
	}
	//加载菜单，actioninfo菜单
	function showpermitinfo(){
		$.messager.progress();
		$("#permitinfo").datagrid({
			nowrap : true,
			striped : true,
			rownumbers : false,
			singleSelect : false,
			url : myObjectPath + '/role/getPermitInfos.action',
			fitColumns : false,
			idField : 'id',
			animate:true,
			fit : true,
			toolbar : [ {
				text : "确认配置权限",
				iconCls : "icon-edit_add",
				handler : function() {
					var node = roletree.tree("getSelected");
					if(node){
						var nodes = $("#permitinfo").datagrid("getSelections");
						if(nodes){
							var ids = new Array();
							for(var i = 0;i<nodes.length;i++){
								ids.push(nodes[i].id);
							}
							$.ajax({
								  type: 'POST',
								  url: myObjectPath + "/role/allotmentPermit.action",
								  data: {"ids":ids.join(","),'role.roleId':node.id},
								  success:function(result){
									if(result=='success'){
										showMessage("操作提示","配置菜单完成");
									}else{
										showMessage("操作提示",result);
									}
								  },
								  dataType: "text"
								});
						}
					}
				}
			} ],
			columns : [ [  {field:'ck',checkbox:true},
			              {field : 'id',title : '权限ID',align : 'center',width : 150,sortable : true}, 
			              {field : 'text',title : '权限名称',align : 'left',width : 100,sortable : true}]],
			onLoadSuccess:function(data){
				var node = roletree.tree("getSelected");
				if(node){
				$.ajax({
					  type: 'POST',
					  url: myObjectPath + "/role/getRolesByid.action",
					  data: {"role.roleId":node.id},
					  success:function(result){
						  $("#permitinfo").datagrid("unselectAll");
						$.each(result,function(index,v){
							$("#permitinfo").datagrid("selectRow",$("#permitinfo").datagrid("getRowIndex",v));
						});
						$.messager.progress("close");
					  },
					  dataType: "json"
					});
				}
				
			},onLoadError:function(){
				$.messager.progress("close");
			}
		});
	}
</script>
</body>


