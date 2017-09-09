<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:include page="/common/common.jsp"></jsp:include>
<html>
<head>
<script>
var editIndex;
var editRow;
$(function(){
	$("#addMember").dialog({
		title: "添加会员",
        width: 700,
        modal: true,
        shadow: true,//如果设置为true，显示窗口的时候将显示阴影
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
				saveMember();
			}
		},
		{
			text:'取消',
			plain:true,
			iconCls:'icon-no',
			handler:function(){
				$("#addMember").dialog("close");
				$("#addMember").form('clear');
			}
		}]
    });
	//**************************
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
	//**************************
	$("#showMember").datagrid({
		title:'会员详情',
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
		url : myObjectPath + '/mem/queryAllMem.action',
		sortOrder : 'asc',
		remoteSort : false,//定义是否通过远程服务器对数据排序。
		fitColumns : true,//设置为true将自动使列适应表格宽度以防止出现水平滚动。
		idField : 'memId',//id字段名称.
		fit : true,
		toolbar:[{text:"添加会员",iconCls:"icon-edit_add",handler:function(){
			$("#addMember").dialog("open");
		}},'-',{
			text : "删除信息",
			iconCls : "icon-remove",
			handler : function() {
				var rows = $('#showMember').datagrid("getSelections");
				var ids = new Array();//传到后台的一个id集合
				if (rows.length > 0) {
					$.messager.confirm(
									'请确认',
									'您确定要删除要选择的信息吗？',
									function(b) {
										if (b) {
											for ( var i = 0; i < rows.length; i++) {
												ids.push(rows[i].memId);
											}
											$.ajax({
														type : 'post',
														url : myObjectPath + '/mem/deleteMember.action',
														data : {
															ids : ids.join(',')//返回字符串,用逗号分隔
														},
														success : function(data) {
															if (data == 'success') {
																$('#showMember').datagrid('load');
																$('#showMember').datagrid('unselectAll');//取消检查所有当前页面的行
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
				}  else {
					$.messager.alert('提示','请选择要删除的行！','error');
				}
			}
		},
	'-',{text:"保存编辑",
		  iconCls:"icon-save",
		  handler: function(){
					$('#showMember').datagrid("endEdit",editIndex,editRow);
					$.ajax({
						  type:'post',
						  url:myObjectPath +"/mem/addMember.action",
						  data:{					
					       'member.memId':editRow.memId,
					       'member.memName':editRow.memName,
					       'member.memSex':editRow.memSex,
					       'member.memAge':editRow.memAge,
					       'member.memBirthday':editRow.memBirthday,
					       'member.memAddress':editRow.memAddress,
					       'member.memQq':editRow.memQq,
					       'member.memTel':editRow.memTel,
					       'member.memEmail':editRow.memEmail,
					       'member.memStatus':editRow.memStatus,
					       'member.memPic':editRow.memPic,
					       'member.handlerName':editRow.handlerName
						     }, 
							success:function(data){
								if(data=='success'){
									$('#showMember').datagrid('load');
									$('#showMember').datagrid('unselectAll');//取消检查所有当前页面的行
									$.messager.show({
						               	 title:'提示',
						               	 msg:'修改成功!'
						               	 });
								}else{
									$.messager.show({
						               	 title:'错误',
						               	 msg:'请选择一项进行修改!'
						               	 });
								       }
							    }
						     });
		}},'-',{text:"修改信息",iconCls:"icon-edit",
			  handler: function(){
					$('#showMember').datagrid("beginEdit",editIndex,editRow);
			  }
		},'-',{text:"取消编辑",iconCls:"icon-redo",
			  handler: function(){
				  $('#showMember').datagrid('rejectChanges');//回滚自创建以来或自上次调用AcceptChanges，所有的变化数据。
				  $('#showMember').datagrid('unselectAll');//取消检查所有当前页面的行
			  }
			 },'-',{text:"添加会员卡",iconCls:"icon-edit_add",handler:function(){
					$("#addMemCard").dialog("open");
					$("#menuFormCard").form('load',{
						'memCard.memMember.memId':editRow.memId
						
					});
				}}
			 ],
		columns : [[ 
		            {field:'ck',checkbox:true},
		              {field : 'memId',	title : '会员id',align :'center',width : 150,sortable : true,hidden : true}, 
		              {field : 'memName',	title : '会员姓名',	align : 'center',width : 100,sortable : true,editor:"text"}, 
		              {field : 'memSex',title : '性别',align : 'center',width : 100,sortable:true,editor:{type:'combobox',options:{
		            	  	value:'男',
							valueField:'id',
							textField:'sex',
							data:[{id:'100101',sex:'男'},{id:'100102',sex:'女'}],
							required:true
						}},
		              		 formatter:function(value,row){
	            		 	 	switch(value){
	            		  		case 100101:return '男' ;break;
	            		  		case 100102:return '女' ;break;
	            		  		default:return '无性别';
	            		  		}
	            	  }}, 
		              {field : 'memAge',	title : ' 年龄',	align : 'center',width : 100,sortable : true,editor:{type:'text',options:{required:true}}}, 
		              {field : 'memBirthday',	title : '生日',	align : 'center',width : 100,sortable : true,editor:{type:'datebox',options:{required:false}}}, 
		              {field : 'memAddress',	title : '会员住址',	align : 'center',width : 100,sortable : true,editor:{type:'text',options:{required:true}}},
		              {field : 'memQq',	title : '会员QQ',	align : 'center',width : 100,sortable : true,editor:{type:'text',options:{required:true}}},
		              {field : 'memTel',	title : '会员电话',	align : 'center',width : 100,sortable : true,editor:{type:'text',options:{required:true}}},
		              {field : 'memEmail',	title : 'Email',	align : 'center',width : 100,sortable : true,editor:{type:'text',options:{required:true}}},
		              {field : 'memStatus',	title : '会员状态',	align : 'center',width : 100,sortable : true,editor:{type:'text',options:{required:true}}},
		              {field : 'memPic',	title : '会员头像',	align : 'center',width : 100,sortable : true,editor:{type:'text',options:{required:true}}},
		              {field : 'handlerName',	title : '操作员',	align : 'center',width : 100,sortable : true,editor:{type:'text',options:{required:true}}},
		              {field : 'handle',title : '操作',width : 120,sortable : true,
			  			    formatter : function(value, row, index) {
			  						var btns = "";//格式化输出linkbutton
									btns = "<a onClick='showCard()'>详情</a>";
			  						return btns;
			  						}
			  		        }
		              ]],
		              onLoadSuccess:function(data){
		      		},onLoadError:function(){ 
		      			$.messager.progress("close");
		      		},onClickRow:function(rowIndex,rowDate){
		            	  editIndex=rowIndex;
		            	  editRow=rowDate;
		              }
	}); 
});
//保存卡信息
function savememCard(){
	$('#menuFormCard').form('submit',{
	    url:myObjectPath +"/mem/addMemberCard.action",   
	    onSubmit: function(){   
	    	return $(this).form("validate");
	    },
	    success:function(data){
	       if(data=='success'){
	    	   showMessage("成功提示","会员卡添加成功...")
	    	   $("#addMemCard").dialog("close");
	    	   $("#menuFormCard").form('clear');//关闭datagrid时清空
	       }else{
	    	   showMessage("提示",data) ;
	       }
	    }   
	});  
}


//显示卡详情
var memId0 =0;
function showCard(){
	if(editRow){
		$("#info0").datagrid("load",{
			memId:editRow.memId
		});
		$("#Cardinfo").dialog().show();
	}
	
};
//清除
function Clear(){
	$('#searchForm').form('clear');
};

//保存
function saveMember(){
	$('#menuForm').form('submit', {
	    url:myObjectPath +"/mem/addMember.action",   
	    onSubmit: function(){   
	    	return $(this).form("validate");
	    },
	    success:function(data){
	       if(data=='success'){
	    	   showMessage("成功提示","会员添加成功...")
	    	   $("#addMember").dialog("close");
	    	   $("#menuForm").form('clear');//关闭datagrid时 清空
	    	   $("#showMember").datagrid("reload");
	       }else{
	    	   showMessage("提示",data);
	       }
	    }   
	});  
}
//根据姓名模糊查询
function queryMemberByName(){
	$('#showMember').datagrid({
		queryParams: {
			'member.memName':$("#memName").val()
		}
	});
}
</script>
</head>
<body>
<div class="easyui-layout" data-options="fit:true,border:false">
 <div data-options="region:'north'" title="查询"style="height:80px; overflow: hidden; border: 'false'">
	<form id="searchForm">
			<table style="width: 100%; height:100%; align: center;" class="datagrid-toolbar">
				<tr>
					<td>会员全名或者部分名：<input name="member.memName" id="memName" /></td>
				</tr>
				<tr>
					<td>
					 <a href="javascript:void(0);" class="easyui-linkbutton"onclick="queryMemberByName()">查询</a> 
					 <a href="javascript:void(0);"class="easyui-linkbutton" onclick="Clear()">清空</a></td>
				</tr>
			</table>
		</form>
</div>
<div data-options="region:'center',border:false">
	<table id="showMember" class="easyui-datagrid"></table>
	</div>
	</div>
	<div id="addMember">
<form method="post" id="menuForm">
<table class="table_form" width=100%>
<tr><th>会员姓名：</th><td><input type="text" name="member.memName" data-options="required:true"></td>
	<th>性别：</th><td>
				<input id="memSex" name="member.memSex" class="easyui-combobox" value="" data-options="valueField:'id',textField:'text',panelHeight:50,data: [{id:100101,text:'男'},{id:100102,text:'女'}]">
			</td></tr>
<tr><th>年龄：</th><td><input type="text" name="member.memAge"class="easyui-text" data-options="validType:'length(0,100)'"></td>
	<th>会员生日：</th><td><input type="text"id="dd"class="easyui-datebox" name="member.memBirthday" data-options="editable:'true'"></td></tr>
<tr><th>会员QQ：</th><td><input type="text" name="member.memQq">
	<th>会员电话：</th><td><input type="text"class="easyui-numberbox"  name="member.memTel"></td></tr>
<tr><th>Email：</th><td ><input type="text" name="member.memEmail" class="easyui-validatebox" data-options="validType:'email'" ></td>
	<th>会员状态：</th><td ><input type="text" name="member.memStatus"></td></tr>
<tr><th>会员照片：</th><td colspan="1"><input type="text" name="member.memPic"></td>
	<th>操作员：</th><td colspan="1"><input type="text" name="member.handlerName"></td></tr>
</table>
</form>
</div>
<!-- 添加会员卡信息 -->
<div id="addMemCard">
<form method="post" id="menuFormCard">
<table class="table_form" width="100%">
<!-- <tr><th>卡号：</th><td colspan="3"><input type="text" name="memCard.cardNo"/></td></tr> -->
<tr><th>会员id</th><td colspan="3"><input type="text" name="memCard.memMember.memId"/></td></tr>
<tr><th>卡密码：</th><td colspan="3"><input type="text" name="memCard.cardPass"></td></tr><!-- //密码框 -->
<tr><th>等级：</th><td><input type="text" name="memCard.cardLevel" class="easyui-combobox" data-options="valueField:'cardLevel',textField:'text',panelHeight:50,data: [{cardLevel:100201,text:'铜卡'},{cardLevel:100203,text:'金卡'},{cardLevel:100202,text:'银卡'}]"></td></tr>
<tr><th>类型：</th><td><input type="text" name="memCard.cardType" class="easyui-combobox" data-options="valueField:'cardType',textField:'text',panelHeight:50,data: [{cardType:100401,text:'积分充值卡'},{cardType:100402,text:'充值卡'},{cardType:100403,text:'积分卡'}]"></td></tr>
<tr><th>到期时间：</th><td><input type="text"id="dd"class="easyui-datebox"  name="memCard.cardIndate" data-options="editable:'true'"> </td></tr>
<tr><th>卡备注：</th><td><input type="text"class="easyui-numberbox"  name="memCard.cardRemark"></td></tr>
</table>
</form>
</div>

<div id="Cardinfo">
 <table class="easyui-datagrid" style="width:500px;height:500px"id="info0"
  	data-options="url:myObjectPath+'/mem/queryCardId.action',queryParams:{'memId':memId0},fitColumns:true,singleSelect:true">
  <thead>
        <tr>
            <th data-options="field:'cardNo',width:100">卡号</th>
            <th data-options="field:'cardPass',width:100" >密码</th>
        </tr>
    </thead>  
</table>
</div> 
</body>
</html>