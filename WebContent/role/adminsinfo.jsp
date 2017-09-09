<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:include page="/common/common.jsp"></jsp:include>
<body class="easyui-layout">
	<div data-options="region:'west',title:'部门信息',split:false"
			style="width: 180px;">
			<ul id="deptTree"></ul>
			</div>
	<div data-options="region:'center',title:''">
		<div class="easyui-layout" data-options="fit:true,border:false">
			<div data-options="region:'west',title:'',split:false"
					style="width: 180px;">
				<div id="employeepanel" class="easyui-panel" title="员工信息" data-options="fit:true,border:false">
					<ul id="empTree"></ul>
				</div>
			</div>
			<div data-options="region:'center',title:'',border:false">
				<table id="roleinfo"></table>
			</div>
		</div>
	</div>
<script>
var  nodeText='';
var nodeIndex=0;
var employeeTree;
var depttree;
	$(function() {
		//加载部门树
		initDeptTree();
	});
	//加载用户
	function initEmployeeTree(){
		var nodeSelect= depttree.tree('getSelected');
		 employeeTree = $("#empTree").tree({
			url : myObjectPath + '/dept/getEmpTrees.action?deptid='+nodeSelect.id,
			onLoadSuccess:function(v,data){
				if(data[0].id!=0){
					var node= $('#empTree').tree('find', data[0].id);
					$("#empTree").tree("select",node.target);
					initRoleData();
				}
			},onClick:function(node){
				initRoleData();
			}
		}); 
	}
	//部门加载
	function initDeptTree(){
	depttree = $("#deptTree").tree({
			url : myObjectPath + '/dept/getAllDepts.action',
			onLoadSuccess:function(v,data){
				if(data[0].id!=0){
					var node= $('#deptTree').tree('find', data[0].id);
					$("#deptTree").tree("select",node.target);
					initEmployeeTree();
				}
			},onClick:function(node){
				initEmployeeTree();
			}
		});
	}
	//加载所有角色，并选中用户所拥有的角色
	function initRoleData(){
		$.messager.progress();
		$("#roleinfo").datagrid({
			nowrap : true,striped : true,rownumbers : false,
			singleSelect : false,url : myObjectPath + '/role/getRoles.action',
			fitColumns : false,idField : 'id',
			fit : true,
			toolbar : [ {
				text : "确认配置角色",
				iconCls : "icon-edit_add",
				handler : function() {
					var node = employeeTree.tree("getSelected");
					if(node){
						var nodes = $("#roleinfo").datagrid("getSelections");
						if(nodes){
							var ids = new Array();
							for(var i = 0;i<nodes.length;i++){
								ids.push(nodes[i].id);
							}
							$.ajax({
								  type: 'post',
								  url: myObjectPath + "/allotmentRoles.action",
								  data: {"ids":ids.join(","),'admin.adminName':node.attributes.adminName},
								  success:function(result){
									if(result=='success'){
										showMessage("操作提示","配置角色完成");
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
			              {field : 'id',title : '角色ID',align : 'center',width : 150,sortable : true}, 
			              {field : 'text',title : '角色名称',align : 'left',width : 100,sortable : true}]],
			onLoadSuccess:function(data){$.messager.progress("close");
				var node = employeeTree.tree("getSelected");
				if(node){
				$("#roleinfo").datagrid("unselectAll");
				$.ajax({
					  type: 'post',
					  url: myObjectPath + "/getRolesByempid.action",
					  data: {"admin.adminName":node.attributes.adminName},
					  success:function(result){
						$.each(result,function(index,v){
							$("#roleinfo").datagrid("selectRow",$("#roleinfo").datagrid("getRowIndex",v));
						});
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


