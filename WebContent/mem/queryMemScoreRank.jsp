<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:include page="../common/common.jsp"></jsp:include>
<html>
<head>

<!-- 网页输入汉字 乱码，修改数据 数据库不变 -->
<script>
var editIndex;
var editRow;
	$(function() {
		$("#addRank").dialog({
			title : "积分添加",
			width : 500,
			modal : true,
			shadow : true,//如果设置为true，显示窗口的时候将显示阴影。
			closed : true,
			height : 200,
			resizable : false,
			collapsible : false,
			minimizable : false,
			maximizable : false,
			icon : 'icon-save',
			buttons : [ {
				text : '确定',
				plain : true,
				iconCls : 'icon-edit_add',
				handler : function() {
					saveMenu();
				}
			}, {
				text : '取消',
				plain : true,
				iconCls : 'icon-no',
				handler : function() {
					$("#addRank").dialog("close");
				}
			} ]
		});
		$("#showRank")
				.datagrid(
						{
						title : '信息',
						nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取。
						striped : true,//设置为true将交替显示行背景。
						pagination : true,//显示分页
						rownumbers : false,//设置为true将显示行数。
						collapsible : false,//定义是否显示折叠按钮。
// 						singleSelect : true,//设置为true将只允许选择一行。
						sortable : true,//设置为true允许对该列排序
						pageSize : 2,
						pageNums : 2,
						pageList : [ 2, 4, 6, 8 ],
						url : myObjectPath + '/mem/getAll.action',
						sortOrder : 'asc',
						remoteSort : false,//定义是否通过远程服务器对数据排序。
						fitColumns : true,//设置为true将自动使列适应表格宽度以防止出现水平滚动。
						idField : 'rankId',//id字段名称.
						fit : true,
						toolbar : [
								{
									text : "添加信息",
									iconCls : "icon-edit_add",
									handler : function() {
									$("#addRank").dialog("open");
									}
									},
									'-',
									{
								text : "删除信息",
							iconCls : "icon-remove",
							handler : function() {
							var rows = $('#showRank').datagrid("getSelections");
							var ids = new Array();//传到后台的一个id集合
							if (rows.length > 0) {
							$.messager.confirm(
								'请确认',
							'您确定要删除要选择的信息吗？',
							function(b) {
						if (b) {
					for ( var i = 0; i < rows.length; i++) {
					ids.push(rows[i].ruleId);
					}
					$.ajax({
					type : 'post',
			url : myObjectPath + '/mem/deleteMemScoreRak.action',
					data : {
				ids : ids
					.join(',')
					//返回字符串,用逗号分隔
								},
				success : function(
				data) {
			if (data == 'success') {
				$(
				'#showRank')
			.datagrid(
			'load');
			$(
				'#showRank')
				.datagrid(
		'unselectAll');//取消检查所有当前页面的行
		$.messager
		.show({
		title : '提示',
		msg : '刪除成功!'
		});
	} else {
	$.messager
	.show({
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
								'-',
					{
					text : "保存编辑",
					iconCls : "icon-save",
					handler : function() {
					var rows = $('#showRank')
					.datagrid("getSelections");
					if (rows.length == 1) {// 只选择一行
					var index = $('#showRank')
					.datagrid(
					"getRowIndex",
						rows[0]);
				$('#showRank').datagrid(
					"endEdit", editIndex,editRow);
				alert(editRow.orderNo);
							}
					$.ajax({
					  type:'post',
			  url:myObjectPath +"/mem/updateMSR.action",
				  data:{					
				  'memScoreRule.ruleId':editRow.ruleId,
				       'memScoreRule.money':editRow.money,
					       'memScoreRule.score':editRow.score,
						       'memScoreRule.typeId':editRow.typeId,
							     }, 
						success:function(data){
					if(data=='success'){
				$('#showMemRank').datagrid('load');
				$('#showMemRank').datagrid('unselectAll');//取消检查所有当前页面的行
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
				    } );
						}
					},
					'-',
						{
						text : "积分修改",
					iconCls : "icon-edit",
					handler : function() {
						$('#showRank').datagrid("beginEdit",editIndex,editRow);
									}
								},
							'-',
								{
								text : "取消编辑",
							iconCls : "icon-redo",
						handler : function() {
						$('#showRank').datagrid(
						'rejectChanges');//回滚自创建以来或自上次调用AcceptChanges，所有的变化数据。
						$('#showRank').datagrid(
						'unselectAll');//取消检查所有当前页面的行
									}
							} ],
					columns : [[ 
				  {field : 'ruleId',	title : 'rankId',	align : 'center',width : 150,sortable : true,hidden:true}, 
				  {field : 'money',	title : '金额',	align : 'center',width : 100,sortable : true,editor:"text"}, 
				  {field : 'score',	title : '积分额度',	align : 'center',width : 100,sortable : true,editor:"text"} ,
				  {field : 'typeId',title : '积分类型',	align : 'center',width : 100,sortable : true,editor:"text"}
				             ]],
				            onClickRow:function(rowIndex,rowDate){
				        	  editIndex=rowIndex;
				       	  editRow=rowDate;
				             }
					});
	});

	function saveMenu() {
		$('#menuForm').form('submit', {
			url :myObjectPath + '/mem/addMemScoreRule.action',
			onSubmit : function() {
				return $(this).form("validate");
			},
			success : function(data) {
				if (data == 'success') {
					showMessage("成功提示", "积分规则添加成功...")
					$("#addRank").dialog("close");
					$("#showRank").datagrid("reload");
				} else {
					showMessage("提示", data);
				}
			}
		});
	}
</script>
</head>
<body>
<div class="easyui-layout" data-options="fit:true,border:false">
<!-- 	<table id="showRank" class="easyui-datagrid"></table> -->
<!--	<div data-options="region:'north'" title="过滤"
		style="height: 110px; overflow: hidden; border: 'false'">
		<form action="queryMemRank.action" method="memRank">

		</form>
	</div>
	-->
	<div data-options="region:'center',border:false">
	<table id="showRank" ></table>
	</div>
	</div>
	<div id="addRank">
		<form method="post" id="menuForm">
		<input type="hidden" name="memScoreRule.ruleId">
			<table class="table_form">
				<tr>
				<td>金额:</td>
				<td><input type="text" name="memScoreRule.money"></td>
			</tr>
			<tr>
				<td>积分额度:</td>
				<td><input type="text" name="memScoreRule.score"></td>
			</tr>
			<tr>
				<td>积分类型</td>
				<td><input type="text" name="memScoreRule.typeId"></td>
			</tr>
			</table>
		</form>
	</div>
</body>
</html>