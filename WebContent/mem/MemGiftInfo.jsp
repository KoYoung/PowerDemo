<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:include page="/common/common.jsp"></jsp:include>
<html>
<head>
<script>
var editIndex;//行数
var editRow;//行数据
$(function(){
	$("#addMemGift").dialog({
		title: "添加礼物",
        width: 600,
        modal: true,
        shadow: true,//如果设置为true，显示窗口的时候将显示阴影。
        closed: true,
        height: 300,
        resizable:false,
        collapsible:false,
        minimizable:false,
        maximizable:false,
        icon:'icon-save',
        buttons:[{
			text:'确定',
			plain:true,
			iconCls:'icon-edit_add',
			handler:function(){
				savememCard();
			}
		},
		{
			text:'取消',
			plain:true,
			iconCls:'icon-no',
			handler:function(){
				$("#addMemGift").dialog("close");
			}
		}]
    });
	$("#showMemGift").datagrid({
		title:'积分详情',
		nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取。
		striped : true,//设置为true将交替显示行背景。
		pagination : true,//显示分页
		rownumbers : false,//设置为true将显示行数。
		collapsible : false,//定义是否显示折叠按钮。
// 		singleSelect:true,//设置为true将只允许选择一行。
		sortable:true,//设置为true允许对该列排序
		pageList : [2,10,15,20],
		url : myObjectPath + '/mem/queryMemGiftt.action',
		sortOrder : 'asc',
		remoteSort : false,//定义是否通过远程服务器对数据排序。
		fitColumns : true,//设置为true将自动使列适应表格宽度以防止出现水平滚动。
		idField : 'giftId',//id字段名称.
		fit : true,
		toolbar:[{text:"添加信息",iconCls:"icon-edit_add",handler:function(){
			$("#addMemGift").dialog("open");
		}},'-',
		{
			text : "删除信息",
			iconCls : "icon-remove",
			handler : function() {
				var rows = $('#showMemGift').datagrid("getSelections");
				var ids = new Array();//传到后台的一个id集合
				if (rows.length > 0) {
					$.messager.confirm(
									'请确认','您确定要删除要选择的信息吗？',
									function(b) {
										if (b) {
											for ( var i = 0; i < rows.length; i++) {
												ids.push(rows[i].giftId);
											}
											$.ajax({
														type : 'post',
														url : myObjectPath + '/mem/delMemGift.action',
														data : {ids : ids.join(',')
														//返回字符串,用逗号分隔
														},
														success : function(
																data) {
															if (data == 'success') {
																$('#showMemGift').datagrid('load');
																$('#showMemGift').datagrid('unselectAll');//取消检查所有当前页面的行
																$.messager.show({
																			title : '提示',
																			msg : '刪除成功!'
																		});
															} else {
																$.messager.show({
																			title : '错误',
																			msg : '请选择一项进行修改!'
																		});
															}
														}
													});
										}
									});
				} else {
					$.messager.alert('提示','请选择要删除的行！','error');
				}
			}
		},
	'-',{text:"保存编辑",
		  iconCls:"icon-save",
		  handler: function(){
					$('#showMemGift').datagrid("endEdit",editIndex,editRow);
// 					alert(editRow.giftId);
					$.ajax({
						  type:'post',
						  url:myObjectPath +"/mem/updateMemGift.action",
						  data:{					
							  'memGift.giftId':editRow.giftId,
						      'memGift.giftName':editRow.giftName,
						      'memGift.dealMark':editRow.dealMark,
						      'memGift.giftPic':editRow.giftPic,
						      'memGift.giftIntegral':editRow.giftIntegral,

						     }, 
							success:function(data){
								if(data=='success'){
									$('#showMemGift').datagrid('load');
									$('#showMemGift').datagrid('unselectAll');//取消检查所有当前页面的行
									$.messager.show({
						               	 title:'提示',
						               	 msg:'修改成功!'
						               	 });
								}
								else{
									$.messager.show({
						               	 title:'错误',
						               	 msg:'请选择一项进行修改!'
						               	 });
								       }
							    }
						     });
			       	
				
		}},'-',{text:"修改信息",
			  iconCls:"icon-edit",
			  handler: function(){
					$('#showMemGift').datagrid("beginEdit",editIndex,editRow);
			  }
		},'-',{text:"取消编辑",
			  iconCls:"icon-redo",
			  handler: function(){
				  $('#showMemGift').datagrid('rejectChanges');//回滚自创建以来或自上次调用AcceptChanges，所有的变化数据。
				  $('#showMemGift').datagrid('unselectAll');//取消检查所有当前页面的行
			  }
			 }
		],
		columns : [[ 
		            {field : 'giftId',	title : 'rankId',	align : 'center',width : 150,sortable : true,hidden:true}, 
					 {field : 'giftName',	title : '礼品名称',	align : 'center',width : 100,sortable : true,editor:"text"}, 
					 {field : 'dealMark',	title : '礼品数量',	align : 'center',width : 100,sortable : true,editor:"text"},
					 {field : 'giftPic',title : '礼品图片',	align : 'center',width : 100,sortable : true,editor:"false",
						formatter:function(value ,row,index){
// 							alert(value);
								return "<img src='"+value+"' width=50px,height=50px />" ;
						}
					 },
					 {field : 'giftIntegral',	title : '所需积分',	align : 'center',width : 100,sortable : true,editor:"text"}
// 					 {field : 'giftIntegral',title : '积分',	align : 'center',width : 100,sortable : true,editor:"text"}
		              ]],
		              onClickRow:function(rowIndex,rowDate){
		            	  editIndex=rowIndex;
		            	  editRow=rowDate;
		              }
	}); 
});
//清除
function Clear(){
	$('#searchForm').form('clear');
};



function savememCard(){
	$('#menuForm').form('submit', {   
	    url:myObjectPath +"/mem/savaMemGift.action",   
	    onSubmit: function(){   
	    	return $(this).form("validate");
	    },
	    success:function(data){
	       if(data=='success'){
	    	   showMessage("成功提示","礼物添加成功...")
	    	   $("#addMemGift").dialog("close");
	    	   $("#menuForm").form('clear');//关闭datagrid时 清空
	    	   $("#showMemGift").datagrid("reload");
	       }else{
	    	   showMessage("提示",data) ;
	       }
	    }   
	});  
}

</script>
</head>
<body>
<table id="showMemGift"></table>
<div data-options="region:'north'" title="过滤" style="height:110px; overflow:hidden;border:'false'">
<form id="searchForm">
<table style="width:100%;height:100%; align:center;" class="datagrid-toolbar">
<tr><td>卡号：<input name="name"/></td></tr>
</table>
</form>
</div>
<table id="showMemGift" class="easyui-datagrid"></table>
<div id="addMemGift">
<form method="post" id="menuForm" enctype="multipart/form-data">
			<table class="table_form">
				<tr>
				<td>礼品名称:</td>
				<td><input type="text" name="memGift.giftName"></td>
			</tr>
			<tr>
				<td>礼品数量</td>
				<td><input type="text" name="memGift.dealMark"></td>
			</tr>
			<tr>
				<td>礼品图片</td>
				<td><input type="file" name="pic"></td>
			</tr>
				<tr>
				<td>积分</td>
				<td><input type="text" name="memGift.giftIntegral"></td>
			</tr>
			</table>
		</form>
</div>
</body>
</html>