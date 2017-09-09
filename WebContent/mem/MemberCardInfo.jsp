<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:include page="/common/common.jsp"></jsp:include>
<html>
<head>
<script>
var editIndex;
var editRow;
$(function(){
	$("#addMemCard").dialog({
		title: "添加会员卡",
        width: 600,
        modal: true,
        shadow: true,//如果设置为true，显示窗口的时候将显示阴影。
        closed: true,
        height: 300,
        resizable:false,
        collapsible:false,
        minimizable:false,
        maximizable:false,
        icon:'icon-save' ,
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
				$("#addMemCard").dialog("close");
			}
		}]
    });
	$("#convertGift").dialog({
		title: "兑换礼品",
        width: 600,
        modal: true,
        shadow: true,//如果设置为true，显示窗口的时候将显示阴影。
        closed: true,
        height: 300,
        resizable:false,
        collapsible:false,
        minimizable:false,
        maximizable:false,
        icon:'icon-save' ,
        buttons:[{
        	text : "确定兑换",
			iconCls : "icon-remove",
			handler : function() {
				var rows = $('#showGift').datagrid("getSelections");
				if(rows.length==1){
					$.messager.confirm('请确认','您确定要兑换吗',
						
					function(b) {
					      $.ajax({	
					        	type : 'post',
								url : myObjectPath + '/mem/updateGiftCard.action?time='+new Date(),
								data : {
									"memGift.giftId":rows[0].giftId,
									"cardno":editRow.cardNo
									},
							    success : function(data) {
								if (data == 'success') {
									$('#showGift').datagrid('load');
									$('#showGift').datagrid('unselectAll');//取消检查所有当前页面的行
									$.messager.show({
												title : '提示',
												msg : '兑换成功!'
											});
								} else {
									$.messager.show({
												title : '错误',
												msg : '兑换失败!'
											});
								}
									}
							}); });
				}
		}
		}]
    });
	
	
	$("#showMemCard").datagrid({
		title:'会员卡详情',
		nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取。
		striped : true,//设置为true将交替显示行背景。
		pagination : true,//显示分页
		rownumbers : false,//设置为true将显示行数。
		collapsible : false,//定义是否显示折叠按钮。
// 		singleSelect:true,//设置为true将只允许选择一行。
		sortable:true,//设置为true允许对该列排序
		pageSize:5,
		pageNums:5,
		pageList : [5,10,15,20],
		url : myObjectPath + '/mem/queryAllMemCard.action',
		sortOrder : 'asc',
		remoteSort : false,//定义是否通过远程服务器对数据排序。
		fitColumns : true,//设置为true将自动使列适应表格宽度以防止出现水平滚动。
		idField : 'cardNo',//id字段名称.
		fit : true,
		
		toolbar:[ 
{text:"添加信息",iconCls:"icon-edit_add",handler:function(){
			$("#addMemCard").dialog("open");
		}},'-',
		{
			text : "删除信息",
			iconCls : "icon-remove",
			handler : function() {
				var rows = $('#showMemCard').datagrid("getSelections");
				var ids = new Array();//传到后台的一个id集合
				if (rows.length > 0) {
					$.messager.confirm(
									'请确认',
									'您确定要删除要选择的信息吗？',
									function(b) {
										if (b) {
											for ( var i = 0; i < rows.length; i++) {
												ids.push(rows[i].cardNo);
											}
											$.ajax({
														type : 'post',
														url : myObjectPath + '/mem/deleteMemberCard.action',
														data : {ids : ids.join(',')
														//返回字符串,用逗号分隔
														},
														success : function(
																data) {
															if (data == 'success') {
																$('#showMemCard').datagrid('load');
																$('#showMemCard').datagrid('unselectAll');//取消检查所有当前页面的行
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
					$.messager.alter('提示',
							'请选择要删除的行！', 'error');
				}
			}
		},
	'-',{text:"保存编辑",
		  iconCls:"icon-save",
		  handler: function(){
					$('#showMemCard').datagrid("endEdit",editIndex,editRow);
					$.ajax({
						  type:'post',
						  url:myObjectPath +"/mem/update.action",
						  data:{					
					       'memCard.cardNo':editRow.cardNo,
					       'memCard.cardPass':editRow.cardPass,
					       'memCard.cardLevel':editRow.cardLevel,
					       'memCard.cardType':editRow.cardType,
					       'memCard.createTime':editRow.memBirthday,
					       'memCard.cardIndate':editRow.cardIndate,
					       'memCard.cardMoney':editRow.cardMoney,
					       'memCard.cardScore':editRow.cardScore,
					       'memCard.deptId':editRow.deptId,
					       'memCard.cardStatus':editRow.cardStatus,
					       'memCard.cardRemark':editRow.cardRemark
						     }, 
							success:function(data){
								if(data=='success'){
									$('#showMemCard').datagrid('load');
									$('#showMemCard').datagrid('unselectAll');//取消检查所有当前页面的行
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
					$('#showMemCard').datagrid("beginEdit",editIndex,editRow);
			  }
		},'-',{text:"取消编辑",
			  iconCls:"icon-redo",
			  handler: function(){
				  $('#showMemCard').datagrid('rejectChanges');//回滚自创建以来或自上次调用AcceptChanges，所有的变化数据。
				  $('#showMemCard').datagrid('unselectAll');//取消检查所有当前页面的行
			  }
			 }
		],		columns : [[ 
		            {field:'ck',checkbox:true},
		              {field : 'cardNo',	title : '卡号',align :'center',width : 150,sortable : true,}, 
		              {field : 'cardPass',	title : '密码',	align : 'center',width : 100,sortable : true,editor:"text"}, 
		              {field : 'cardLevel',	title : ' 等级',	align : 'center',width : 100,sortable : true,editor:{type:'combobox',options:{
							valueField:'id',
							textField:'cardLevel',
							data:[{id:'100201',cardLevel:'铜卡'},{id:'100203',cardLevel:'金卡'},{id:'100202',cardLevel:'银卡'}],
							required:true
						}},
						              formatter:function(value,row){
				          		 	 	switch(value){
				          		  		case 100201:return '铜卡' ;break;
				          		  		case 100203:return '金卡' ;break;
				          		  	    case 100202:return '银卡' ;break;
				          		  		default:return '无卡等级';
				          		  		}
				          	  }
		              },
		              {field : 'cardType',	title : '卡类型',	align : 'center',width : 100,sortable : true,editor:{type:'combobox',options:{
							valueField:'id',
							textField:'cardType',
							data:[{id:'100401',cardType:'充值积分卡'},{id:'100402',cardType:'积分卡'},{id:'100403',cardType:'充值卡'}],
							required:true
						}},
		            	    formatter:function(value,row){
	            		 	 	switch(value){
	            		  		case 100401:return '充值积分卡' ;break;
	            		  		case 100403:return '充值卡' ;break;
	            		  		case 100402:return '积分卡' ;break;
	            		  		default:return '无卡类型';
	            		  		}
	            	  }
		            },
		              {field : 'createTime',	title : '办卡时间',	align : 'center',width : 100,sortable : true,editor:{type:'datebox',options:{required:true}}},
		              {field : 'cardIndate',	title : '到期时间',	align : 'center',width : 100,sortable : true,editor:{type:'datebox',options:{required:true}}},
		              {field : 'cardScore',	title : '卡内积分',	align : 'center',width : 100,sortable : true,editor:{type:'text',options:{required:true}}},
		              {field : 'cardStatus',	title : '卡状态',	align : 'center',width : 100,sortable : true,editor:{type:'combobox',options:{
							valueField:'id',
							textField:'cardStatus',
							data:[{id:'100301',cardStatus:'正常'},{id:'100302',cardStatus:'挂失'},{id:'100303',cardStatus:'失效'}],
							required:true
						}},
	            	    formatter:function(value,row){
          		 	 	switch(value){
          		  		case 100301:return '正常' ;break;
          		  		case 100302:return '失效' ;break;
          		  		case 100303:return '挂失' ;break;
          		  		default:return '正常';
          		  		}
          	  }
		              }
		              ]],
		              onClickRow:function(rowIndex,rowDate){
		            	  editIndex=rowIndex;
		            	  editRow=rowDate;
		              }
 		 , 
		    //双击一行时出现礼物详情
		  onDblClickRow: function(rowIndex, rowData){
			  editRow=rowData;
				$("#showGift").datagrid({
					nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取。
					striped : true,//设置为true将交替显示行背景。
					pagination : true,//显示分页
					rownumbers : false,//设置为true将显示行数。
					collapsible : false,//定义是否显示折叠按钮。
//			 		singleSelect:true,//设置为true将只允许选择一行。
					sortable:true,//设置为true允许对该列排序
					pageList : [5,10,15,20],
					url : myObjectPath + '/mem/queryMemGift.action?jiFen='+editRow.cardScore+'&cardno='+editRow.cardNo,
					sortOrder : 'asc',
					remoteSort : false,//定义是否通过远程服务器对数据排序。
					fitColumns : true,//设置为true将自动使列适应表格宽度以防止出现水平滚动。
					idField : 'giftId',//id字段名称.
					fit : true,
					columns : [[ 
					            {field : 'giftId',	title : 'rankId',	align : 'center',width : 150,sortable : true,hidden:true}, 
								{field : 'giftName',	title : '礼品名称',	align : 'center',width : 100,sortable : true,editor:"text"}, 
								{field : 'dealMark',	title : '礼品数量',	align : 'center',width : 100,sortable : true,editor:"text"} ,
								{field : 'giftPic',title : '礼品图片',	align : 'center',width : 100,sortable : true,editor:"text",
									formatter:function(value ,row,index){
											return "<img src='"+value+"' width=50px,height=50px>" ;
									}
								 },
								{field : 'giftIntegral',title : '积分',	align : 'center',width : 100,sortable : true,editor:"text"}
					              ]]
				}); 
				 $("#convertGift").dialog("open");
		  }
	}); 
});

function savememCard(){
	$('#menuForm').form('submit', {   
	    url:myObjectPath +"/mem/addMemberCard.action",   
	    onSubmit: function(){   
	    	return $(this).form("validate");
	    },
	    success:function(data){
	       if(data=='success'){
	    	   showMessage("成功提示","会员卡添加成功...")
	    	   $("#addMemCard").dialog("close");
	    	   $("#showMemCard").datagrid("reload");
	       }else{
	    	   showMessage("提示",data) ;
	       }
	    }   
	});  
}
</script>
</head>
<body>
<table id="showMemCard"></table>
<div data-options="region:'north'" title="过滤" style="height:110px; overflow:hidden;border:'false'">
<form id="searchForm">
<table style="width:100%;height:100%; align:center;" class="datagrid-toolbar">
<tr><td>卡号：<input name="name"/></td></tr>
</table>
</form>
</div>
<table id="showMemCard" class="easyui-datagrid"></table>
<div id="addMemCard">
<form method="post" id="menuForm">
<table class="table_form" width="100%">
<tr><th>卡密码：</th><td colspan="3"><input type="text" name="memCard.cardPass"></td></tr><!-- //密码框 -->
<tr><th>等级：</th><td><input type="text" name="memCard.cardLevel" class="easyui-combobox" data-options="valueField:'cardLevel',textField:'text',panelHeight:50,data: [{cardLevel:100201,text:'铜卡'},{cardLevel:100203,text:'金卡'},{cardLevel:100202,text:'银卡'}]"></td></tr>
<tr><th>类型：</th><td><input type="text" name="memCard.cardType" class="easyui-combobox"  data-options="valueField:'cardType',textField:'text',panelHeight:50,data: [{cardType:100401,text:'积分充值卡'},{cardType:100402,text:'充值卡'},{cardType:100403,text:'积分卡'}]"></td></tr>
<tr><th>到期时间：</th><td><input type="text"id="dd"class="easyui-datebox" required="required" name="memCard.cardIndate" data-options="editable:'true'"> </td></tr>
</table>
</form>
</div>
<div id="convertGift">
<table id="showGift" class="easyui-datagrid">
</table>
</div>
</body>
</html>