var nodeText='';
var nodeIndex=0;
var parentIndex=0;
var tree;
$(function() {
	showwin();//加载弹出窗口
	$("#leftpanel").panel({
		tools:[{iconCls:'icon-save',handler:function(){
			save();
		}},{iconCls:'icon-remove',handler:function(){
			del();
		}}]
	});
	$.ajax({
		url:myObjectPath + '/dept/getAllDepts.action?time='+new Date(),
		dataType:'json',
		success:function(treeData){
			initDeptInfo(treeData);
		}
	});
});

function initDeptInfo(treeData){
	$("#deptInfo").treegrid({
		data:treeData,animate:true,
		fit : true,fitColumns : true,
	    idField:'id', rownumbers : false,
	    treeField:'text', singleSelect : true,
	    columns:[[ 
	        {field:'ck',checkbox:true},
	        {field:'id',title:'编号',width:60,align:'center'},    
	        {field:'text',title:'部门名称',width:80,align:'left'},   
	    ]]    
	});
	
	
	$("#deptTree").tree({
		data:treeData,
		onContextMenu : function(e, node) {
			$("#deptTree").tree("select",node.target);
			e.preventDefault();//屏蔽系统右键菜单
			$("#mm").menu('show', {
				left : e.pageX,
				top : e.pageY
			});
		},onLoadSuccess:function(v,data){
			if(data[0].id!=0){
				var node= $('#deptTree').tree('find', data[0].id);
				$("#deptTree").tree("select",node.target);
				showStaffinfo();
			}
		},onDblClick:function(node){
			$("#deptTree").tree("beginEdit",node.target);
		},onClick:function(node){
			showStaffinfo();
		},onBeforeEdit:function(node){
			if(node.id!=0){parentIndex=$("#deptTree").tree("getParent",node.target).id;}
			nodeText=node.text;
		},	onAfterEdit:function(node){
			$.ajax({
				url:myObjectPath + '/dept/addDeptInfo.action',
				type:'post',
				data:{
					'dept.deptParent':parentIndex,
					'dept.deptId':node.id,
					'dept.deptName':node.text
				},
				dataType:'json',
				success:function(data){
					if(data.msg!='success'){
						parentIndex=0;
						showMessage("提示",data);
						if(node.id==0){
							$("#deptTree").tree("remove",node.target);
						}else{
							$("#deptTree").tree("update",{target:node.target,text:nodeText});
						}
					}else{
						nodeIndex=0;
						parentIndex=0;
						$("#deptTree").tree("update",{target:node.target,id:data.index});
					}
				}
			});
		}
	});
}

function menuHandler(item){
	switch(item.name){
	case 'save' :
		var selected = $("#deptTree").tree('getSelected');
		parentIndex=selected.id;
		save(selected);
		break;
	case 'edit' :
		var node = $("#deptTree").tree('getSelected');
		$("#deptTree").tree("beginEdit",node.target);
		break;
	case 'del' :
		del();
		break;
	}
}
		
function save(node){
	if(nodeIndex==0){
		if(node){
			$("#deptTree").tree('append', {
				parent: node.target,
				data: [{id: nodeIndex,text: '新增部门'}]
			})
		}
		else{
			$("#deptTree").tree('append', {
				data: [{id: nodeIndex,text: '新增部门'}]
			})
		}
		var node = $("#deptTree").tree('find', 0);
		$("#deptTree").tree('beginEdit', node.target);
	}
}
function saveEmp(){
	$.messager.progress()
	$("#empForm").form('submit',{
		url:myObjectPath+"/dept/addStaff.action",
		onSubmit: function(parem){
			var node = $("#deptTree").tree('getSelected');
			parem.deptid=node.id;
			var isValid = $(this).form('validate');
			if (!isValid){
				$.messager.progress('close');	// 当form不合法的时候隐藏工具条
			}
			return isValid;	// 返回false将停止form提交 
		},
		success: function(data){
			$.messager.progress('close');	// 当成功提交之后隐藏进度条
			if(data=='success'){
				showMessage("操作提示","添加完成");
				$("#actioninfo").datagrid("reload");
				$("#showAddEmp").dialog("close");
			}else{
				showMessage("操作提示",data);
			}
		}
	});
}

function showStaffinfo(){
	$.messager.progress();
	var node = $("#deptTree").tree("getSelected");
	$("#actioninfo").datagrid({
		nowrap : true,striped : true,pagination : true,rownumbers : false,collapsible : false,
		pageList : [ 30, 60, 90 ],singleSelect : false,
		url : myObjectPath + '/dept/getStaffInfo.action',
		queryParams:{'staff.shareDeptement.deptId':node.id},
		fitColumns : true,idField : 'staffId',
		animate:true,fit : true,
		onRowContextMenu:function(e, rowIndex, rowData){
			$("#actioninfo").datagrid("unselectAll");
			$("#actioninfo").datagrid("selectRow",rowIndex);
			$('#empmm').menu('show',{
				 left: e.pageX,
				 top: e.pageY
			}); 
			e.preventDefault();
		},
		toolbar : [ {text : "新增",iconCls : "icon-edit_add",
			handler : function() {
				checkAdminName();
				$('#empForm').form("clear");
				$("#showAddEmp").dialog("open");
			}
		},'-', {text : "更新",iconCls : "icon-reload",
			handler : function() {
				toEdit();
			}
		},'-', {text : "删除",iconCls : "icon-remove",
			handler : function() {
				delEmp();
			}
		},'-', {text : "冻结/解冻",iconCls : "icon-edit_add",
			handler : function() {
				lock();
			}
		},'-', {text : "调转部门",iconCls : "icon-redo",
			handler : function() {
				var rows = $("#actioninfo").datagrid("getSelections");
				if(rows.length>0){
					$("#deptRemoveWin").dialog("open");
				}
			}
		},'-',{text : "<input id='sreachName' name='sreachName' style='margin-buttom:10px'>"
		}],
		columns : [ [  {field:'ck',checkbox:true},
		              {field : 'staffId',title : 'actionId',align : 'center',width : 150,sortable : true,hidden : true}, 
		              {field : 'staffName',title : '姓名',align : 'left',width : 100,sortable : true}, 
		              {field : 'adminName',title : '登陆账号',align : 'center',width : 100,sortable : true}, 
		              {field : 'staffSex',title : '性别',align : 'center',width : 100,sortable : true,
		            	  formatter:function(value,row){
		            		  switch(value){
		            		  case 100101:return '男' ;break;
		            		  case 100102:return '女' ;break;
		            		  default:return '无性别';
		            		  }
		            	  }}, 
					  {field : 'staffAge',title : '年龄',align : 'center',width : 100,sortable : true}, 
					  {field : 'staffTel',title : '电话',align : 'center',width : 100,sortable : true},
					  {field : 'staffBirthday',title : '生日',align : 'center',width : 100,sortable : true},
					  {field : 'staffAddress',title : '住址',align : 'center',width : 100,sortable : true},
					  {field : 'status',title : '状态',align : 'center',width : 100,sortable : true,
						  formatter:function(value,row){
		            		  switch(value){
		            		  case 100201:return '正常' ;break;
		            		  case 100202:return '冻结' ;break;
		            		  default:return '';
		            		  }
		            	  }}]],
		onLoadSuccess:function(data){
			$.messager.progress("close");
			$('#sreachName').searchbox({   
			    searcher:function(value,name){   
			    	$("#actioninfo").datagrid('load',{
			    		'staff.staffName':value
			    	});
			    },   
			    prompt:'请输入姓名'  
			});  

		},onLoadError:function(){
			$.messager.progress("close");
		}
	});
}
function del(){
	var node = $("#deptTree").tree("getSelected");
	if(node){
		$.messager.confirm('操作提示', '您确定要删除此项菜单吗？', function(r){
			if (r){
				$.ajax({
					  type: 'POST',
					  url: myObjectPath + "/dept/deletedept.action",
					  data: {"dept.deptId":node.id},
					  success:function(result){
						  if(result=='success'){
							  showMessage("操作提示","部门删除完成");
							  $("#deptTree").tree("remove",node.target);
						  }else{
							  showMessage("操作提示",result);
						  }
					  },
					  dataType: "text"
					});
			}
		});
	}
}
function delEmp(){
	var rows = $("#actioninfo").datagrid("getSelections");
	if(rows.length>0){
		$.messager.confirm('危险操作提示', '您确定要删除此员工信息吗?', function(r){
			if (r){
				var ids = new Array();
				for(var i=0;i<rows.length;i++){
					ids.push(rows[i].staffId);
				}
				$.ajax({
					  type: 'POST',
					  url: myObjectPath + "/dept/delStaff.action",
					  data: {"ids":ids.join(',')},
					  success:function(result){
						  if(result=='success'){
							  showMessage("操作提示","员工删除完成");
							  $("#actioninfo").datagrid("reload");
						  }else{
							  showMessage("操作提示",result);
						  }
					  },
					  dataType: "text"
					});
			}
		});
	}
}
function lockStaff(ids){
	$.ajax({
		  type: 'POST',
		  url: myObjectPath + "/dept/lockStaff.action",
		  data: {"ids":ids.join(',')},
		  success:function(result){
			  if(result=='success'){
				  showMessage("操作提示","员工冻结/解冻操作已完成");
				  $("#actioninfo").datagrid("reload");
			  }else{
				  showMessage("操作提示",result);
			  }
		  },
		  dataType: "text"
		});
}
function showwin(){
	
	$("#staffBirthday").datebox({
		onSelect:function(birthday){
			var time = new Date().getTime()-birthday.getTime();
			var year = 60*60*24*365*1000;
			$('#staffAge').numberbox('setValue', parseInt(time/year));
		}
	});
	
	$("#showAddEmp").dialog({
		title : "员工信息",
		width : 600,height : 360,
		modal : true,shadow : true,closed : true,
		resizable : false,collapsible : false,minimizable : false,
		maximizable : false,closable:true,icon : 'icon-save',
		buttons : [ {text : '确定',plain : true,iconCls : 'icon-save',
			handler : function() {
				saveEmp();
			}
		}, {
			text : '取消',plain : true,iconCls : 'icon-no',
			handler : function() {$("#showAddEmp").dialog("close");}
		}],
		onClose:function(){
			$('#empForm').form("reset");
		}
	});
	$("#deptRemoveWin").dialog({
		title : "部门调动",
		width : 400,height : 300,
		modal : true,shadow : true,closed : true,
		resizable : false,collapsible : false,minimizable : false,
		maximizable : false,closable:true,icon : 'icon-save',
		buttons : [ {text : '确定',plain : true,iconCls : 'icon-save',
			handler : function() {
				var deptnodeInfo = $("#deptInfo").treegrid("getSelected");
				var rows = $("#actioninfo").datagrid("getSelections");
				if(rows.length>0){
					var ids = new Array();
					for(var i =0;i<rows.length;i++){
						ids.push(rows[i].staffId);
					}
					$.ajax({
						url:myObjectPath+'/dept/deptRemove.action',
						data:{'ids':ids.join(","),'deptid':deptnodeInfo.id},
						type:'post',
						dataType:'text',
						success:function(d){
							if(d=='success'){
								showMessage("友情提示",'换门调换完成!!!');
								$("#deptRemoveWin").dialog("close");
								$("#actioninfo").datagrid("reload");
							}
						}
					});
				}
			}
		}, {
			text : '取消',plain : true,iconCls : 'icon-result',
			handler : function() {$("#showAddEmp").dialog("close");}
		}]
	});
}
function toEdit(){
	var rows = $("#actioninfo").datagrid("getSelections");
	if(rows.length==1){
		row = rows[0];
		$("#staffId").val(row.staffId);
		$("#status").val(row.status);
		$("#adminName").val(row.adminName);
		$("#staffName").val(row.staffName);
		$("#staffSex").combobox("setValue",row.staffSex);
		$('#staffAge').numberbox('setValue',row.staffAge);
		$("#staffTel").val(row.staffTel);
		$('#staffBirthday').datebox('setValue', row.staffBirthday);
		$("#staffAddress").val(row.staffAddress);
		$("#remark").val(row.remark);
		$("#showAddEmp").dialog("open");
	}else{
		showMessage("友情提示",'请您选择一条记录再点击修改，谢谢!!!');
	}
}
function lock(){
	var rows = $("#actioninfo").datagrid("getSelections");
	if(rows.length>0){
		$.messager.confirm('危险操作提示', '您确定要冻结/解冻此员工吗?', function(r){
			if (r){
				var ids = new Array();
				for(var i=0;i<rows.length;i++){
					ids.push(rows[i].staffId);
				}
				lockStaff(ids);
			}
		});
	}
}
function empHandler(item){
	switch(item.name){
	case 'save' :
		checkAdminName();
		$('#empForm').form("clear");
		$("#showAddEmp").dialog("open");
		break;
	case 'edit' :
		toEdit();
		break;
	case 'del' :
		delEmp();
		break;
	case 'lock' :
		lock();
		break;
	}
}

function toAddRole(){
	var rows = $("#actioninfo").datagrid("getSelections");
	if(rows.length==1){
		row = rows[0];
	}else{
		showMessage("友情提示",'请您选择一条记录再进行分配角色操作，谢谢!!!');
	}
}

function checkAdminName(){
	$("#staffName").blur(function(){
		$.ajax({
			url:myObjectPath+'/getAdminName.action',
			type:'post',
			dataType:'json',
			data:{'adminName':this.value},
			success:function(result){
				$("#adminName").val(result.name);
				$("#resultName").html(result.value);
			}
		})
	});
	$("#adminName").blur(function(){
		$.ajax({
			url:myObjectPath+'/getAdminName.action',
			type:'post',
			dataType:'json',
			data:{'adminName':this.value},
			success:function(result){
				$("#adminName").val(result.name);
				$("#resultName").html(result.value);
			}
		})
	});
}