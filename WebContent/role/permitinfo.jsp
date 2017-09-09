<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:include page="/common/common.jsp"></jsp:include>
<body class="easyui-layout">

			<div data-options="region:'west',title:'',split:false"
				style="width: 180px;">
				<div id="leftpanel" class="easyui-panel" title="权限管理" data-options="fit:true,border:false">
					<ul id="permitTree">
					</ul>
				</div>
			</div>
			<div data-options="region:'center',title:'所属权限菜单'">
				<table id="actioninfo"></table>
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
		$(function() {
			$("#leftpanel").panel({
				tools:[{iconCls:'icon-save',handler:function(){
					save();
				}},{iconCls:'icon-remove',handler:function(){
					del();
				}}]
			});
			
			tree = $("#permitTree").tree({
				url : myObjectPath + '/role/getPermitInfos.action',
				onContextMenu : function(e, node) {
					$("#permitTree").tree("select",node.target);
					e.preventDefault();//屏蔽系统右键菜单
					$("#mm").menu('show', {
						left : e.pageX,
						top : e.pageY
					});
				},onLoadSuccess:function(v,data){
					if(data[0].id!=0){
						var node= $('#permitTree').tree('find', data[0].id);
						$("#permitTree").tree("select",node.target);
						showactioninfo();
					}
				},onDblClick:function(node){
					$("#permitTree").tree("beginEdit",node.target);
				},onClick:function(node){
					showactioninfo();
				},onBeforeEdit:function(node){
					nodeText=node.text;
				},	onAfterEdit:function(node){
					$.ajax({
						url:myObjectPath + '/role/addPermitInfo.action',
						type:'post',
						data:{
							'permit.perId':node.id,
							'permit.perName':node.text
						},
						dataType:'json',
						success:function(data){
							if(data.msg!='success'){
								showMessage("提示",data);
								$("#permitTree").tree("update",{target:node.target,text:nodeText});
							}else{
								nodeIndex=0;
								$("#permitTree").tree("update",{target:node.target,id:data.index});
							}
						}
					});
				}
			});
		});
		function menuHandler(item){
			switch(item.name){
			case 'save' :
				save();
				break;
			case 'edit' :
				var node = $("#permitTree").tree("getSelected");
				$("#permitTree").tree("beginEdit",node.target);
				break;
			case 'del' :
				del();
				break;
			}
		}
		
		function save(){
			if(nodeIndex==0){
				tree.tree('append', {
					data: [{
						id: nodeIndex,
						text: '新增权限'
					}]
				})
				var node = tree.tree('find', 0);
				tree.tree('beginEdit', node.target);
			}
		}
		
		function showactioninfo(){
			$.messager.progress();
			$("#actioninfo").treegrid({
				nowrap : true,
				striped : true,
				pagination : true,
				rownumbers : false,
				collapsible : false,
				pageList : [ 10, 20, 30, 40 ],
				singleSelect : false,
				url : myObjectPath + '/role/getMenusInfo.action',
				queryParams:{},
				sortOrder : 'desc',
				remoteSort : false,
				fitColumns : true,
				idField : 'actionId',
				treeField : 'actionname',
				animate:true,
				fit : true,
				toolbar : [ {
					text : "确认配置权限",
					iconCls : "icon-edit_add",
					handler : function() {
						//$("#showAddMenu").show().dialog("open");
						var node = tree.tree("getSelected");
						if(node){
							var nodes = $("#actioninfo").treegrid("getSelections");
							if(nodes){
								var ids = new Array();
								for(var i = 0;i<nodes.length;i++){
									ids.push(nodes[i].actionId);
								}
								$.ajax({
									  type: 'POST',
									  url: myObjectPath + "/role/allotmentAction.action",
									  data: {"ids":ids.join(","),'permit.perId':node.id},
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
				              {field : 'actionId',title : 'actionId',align : 'center',width : 150,sortable : true,hidden : true}, 
				              {field : 'actionname',title : '菜单名称',align : 'left',width : 100,sortable : true}, 
				              {field : 'actionParent',title : '父菜单',align : 'center',width : 100,sortable : true,
								formatter: function(value,row,index){
									if(value==0)return "";
									return row.parentName;
								}}, 
							  {field : 'actionPic',title : '图标',align : 'center',width : 100,sortable : true}, 
							  {field : 'actionUrl',title : '链接地址',align : 'center',width : 100,sortable : true},
							  {field : 'actionSort',title : '排序',align : 'center',width : 100,sortable : true}]],
				onLoadSuccess:function(data){
					$.messager.progress("close");
					var node = tree.tree("getSelected");
					if(node){
					$.ajax({
						  type: 'POST',
						  url: myObjectPath + "/role/getPermitInfosByid.action",
						  data: {"permit.perId":node.id},
						  success:function(result){
							$.each(result,function(index,v){
								$("#actioninfo").treegrid("select",v);
							});
						  },
						  dataType: "json"
						});
					}
					
				},onLoadError:function(){
					$.messager.progress("close");
				},onSelect:function(node){
					if(node.actionParent!=0){
						$("#actioninfo").treegrid("select",node.actionParent);
					}
				}
			});
		}
		function del(){
			var node = tree.tree("getSelected");
			if(node){
				$.messager.confirm('操作提示', '您确定要删除此项菜单吗？', function(r){
					if (r){
						$.ajax({
							  type: 'POST',
							  url: myObjectPath + "/role/deletePermit.action",
							  data: {"permit.perId":node.id},
							  success:function(result){
								  showMessage("操作提示",result);
								  tree.tree("remove",node.target);
							  },
							  dataType: "text"
							});
					}
				});
			}
		}
	</script>
	

</body>


