<%@ page language="java" import="java.util.*"
	import="org.powerSystem.entity.mem.MemRank" pageEncoding="UTF-8"%>
<jsp:include page="/common/common.jsp"></jsp:include>
<html>
<head>
<script>
	var editIndex;
	var editRow;
	$(function() {
		$("#addOrder").dialog({
			title : "订单添加",
			width : 600,
			modal : true,
			shadow : true,//如果设置为true，显示窗口的时候将显示阴影。
			closed : true,
			height : 300,
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
					saveMember();
				}
			}, {
				text : '取消',
				plain : true,
				iconCls : 'icon-no',
				handler : function() {
					$("#addOrder").dialog("close");
				}
			} ]
		});
		$("#showOrder")
				.datagrid(
						{
							title : '订单详情',
							nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取。
							striped : true,//设置为true将交替显示行背景。
							pagination : true,//显示分页
							rownumbers : false,//设置为true将显示行数。
							collapsible : false,//定义是否显示折叠按钮。
							//singleSelect:true,//设置为true将只允许选择一行。
							sortable : true,//设置为true允许对该列排序
							pageSize:5,
							pageNums:5,
							pageList : [5,10,15,20],
							url : myObjectPath + '/queryAllOrder.action',
							sortOrder : 'asc',
							remoteSort : false,//定义是否通过远程服务器对数据排序。
							fitColumns : true,//设置为true将自动使列适应表格宽度以防止出现水平滚动。
							idField : 'orderNO',//id字段名称.
							fit : true,
							toolbar : [
									{
										text : "添加信息",
										iconCls : "icon-edit_add",
										handler : function() {
											$("#addOrder").dialog("open");
										}
									},
									'-',
									{
										text : "删除信息",
										iconCls : "icon-remove",
										handler : function() {
											var rows = $('#showOrder')
													.datagrid("getSelections");
											var ids = new Array();//传到后台的一个id集合
											if (rows.length > 0) {
												$.messager
														.confirm(
																'请确认',
																'您确定要删除要选择的信息吗？',
																function(b) {
																	if (b) {
																		for ( var i = 0; i < rows.length; i++) {
																			ids
																					.push(rows[i].orderNo);
																		}
																		$
																				.ajax({
																					type : 'post',
																					url : myObjectPath
																							+ '/delOrder.action',
																					data : {
																						ids : ids
																								.join(',')
																					//返回字符串,用逗号分隔
																					},
																					success : function(
																							data) {
																						if (data == 'success') {
																							$(
																									'#showOrder')
																									.datagrid(
																											'load');
																							$(
																									'#showOrder')
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
											$('#showOrder').datagrid("endEdit",
													editIndex, editRow);
											alert(editRow.cardNo);
											$
													.ajax({
														type : 'post',
														url : myObjectPath
																+ "/updateMemOrder.action",
														data : {
															'memOrder.orderNo' : editRow.orderNo,
															'memOrder.cardNo' : editRow.cardNo,
															'memOrder.orderDate' : editRow.orderDate,
															'memOrder.orderAllprice' : editRow.orderAllprice,
															'memOrder.orderIntegral' : editRow.orderIntegral,
															'memOrder.orderRemark' : editRow.orderRemark
														},
														success : function(data) {
															if (data == 'success') {
																$('#showOrder')
																		.datagrid(
																				'load');
																$('#showOrder')
																		.datagrid(
																				'unselectAll');//取消检查所有当前页面的行
																$.messager
																		.show({
																			title : '提示',
																			msg : '修改成功!'
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
									},
									'-',
									{
										text : "修改信息",
										iconCls : "icon-edit",
										handler : function() {
											$('#showOrder').datagrid(
													"beginEdit", editIndex,
													editRow);

											// var rows = $('#showOrder').datagrid("getSelections");
											//  if(rows.length==1){
											//	var index = $('#showOrder').datagrid("getRowIndex",rows[0]);

											//}else{
											/* $.messager.show({
											 title:'错误',
											 msg:'请选择一项进行修改!'
											 }); */
										}
									},
									'-',
									{
										text : "取消编辑",
										iconCls : "icon-redo",
										handler : function() {
											$('#showOrder').datagrid(
													'rejectChanges');//回滚自创建以来或自上次调用AcceptChanges，所有的变化数据。
											$('#showOrder').datagrid(
													'unselectAll');//取消检查所有当前页面的行
										}
									} ],
							columns : [ [ {
								field : 'ck',
								checkbox : true
							}, {
								field : 'orderNo',
								title : '订单号',
								align : 'center',
								width : 150,
								sortable : true
							}, {
								field : 'orderDate',
								title : '下单日期',
								align : 'center',
								width : 100,
								sortable : true,
								editor : "datebox"
							}, {
								field : 'orderAllprice',
								title : ' 订单总金额',
								align : 'center',
								width : 100,
								sortable : true,
								editor : {
									type : 'validatebox',
									options : {
										required : true
									}
								}
							}, {
								field : 'orderIntegral',
								title : ' 积分',
								align : 'center',
								width : 100,
								sortable : true,
								editor : {
									type : 'validatebox',
									options : {
										required : true
									}
								}
							}, {
								field : 'orderRemark',
								title : '备注',
								align : 'center',
								width : 100,
								sortable : true,
								editor : {
									type : 'validatebox',
									options : {
										required : true
									}
								}
							}, {
								field : 'cardNo',
								title : '卡号',
								align : 'center',
								width : 100,
								sortable : true,
								editor : {
									type : 'validatebox',
									options : {
										required : true
									}
								}
							}

							] ],
							onClickRow : function(rowIndex, rowDate) {
								editIndex = rowIndex;
								editRow = rowDate;
							}

						});
	});
	function saveMember() {
		$('#menuForm').form('submit', {
			//url: "../MemOrderAction/addOrder.action", class="easyui-numberbox"
			url : myObjectPath + "/addOrder.action",
			onSubmit : function() {
				return $(this).form("validate");
			},
			success : function(data) {
				if (data == 'success') {
					showMessage("成功提示", "订单添加成功...")
					 $("#addOrder").dialog("close");
			    	   $("#menuForm").form('clear');//关闭datagrid时 清空
			    	   $("#showOrder").datagrid("reload");
				} else {
					showMessage("提示", data);
				}
			}
		});
	}
	function queryMemberByCardNo() {
		$('#showOrder').datagrid({
			queryParams : {
				'memOrder.cardNo' : $("#cardNo").val()
			}
		});
	}
	
	//清除
	function Clear(){
		$('#searchForm').form('clear');
	};
	
	function diss(){
		var aaa=document.getElementById("memOrder.cardNo").value;
		$.ajax({
			type : 'post',
			url : myObjectPath+ "/jisuan.action?memcard.cardNo="+aaa,
			success:function(data){
				document.getElementById("dis").value=data;
			} 
		});
	}
	
	function ff() {
	var t = document.getElementById("orderAllprice").value;
		if (t % 10 >= 5) {
			var j = (t / 10 - t % 10 * 0.1) + 1;
			document.getElementById("orderIntegral").value = j;
		}
		if (t % 10 < 5) {
			var j = (t / 10 - t % 10 * 0.1)
			document.getElementById("orderIntegral").value = j;
		} 
	}
	
	function js(){
		var zhe = document.getElementById("dis").value;
		var t = document.getElementById("orderAllprice").value;
		var js =t*(zhe/10);
		document.getElementById("mk.getDiscount").value=js;
	}
</script>
</head>
<body>
 <div class="easyui-layout" data-options="fit:true,border:false">
 <div data-options="region:'north'" title="查询"style="height:83px; overflow: hidden; border: 'false'">
	<form id="searchForm" method="post">
			<table style="width: 100%; height: 100%; align: center;" class="datagrid-toolbar">
				<tr>
					<td>卡号：<input name="memOrder.cardNo" id="cardNo" /></td>
				</tr>
				<tr>
					<td>
					 <a href="javascript:void(0);" class="easyui-linkbutton"onclick="queryMemberByCardNo()">查询</a> <a href="javascript:void(0);"
					 class="easyui-linkbutton" onclick="Clear()">清空</a></td>
				</tr>
			</table>
		</form>
</div>
<div data-options="region:'center',border:false">
	<table id="showOrder" class="easyui-datagrid"></table>
	</div>	
	</div>
	<div id="addOrder">
	<form action="" method="post" id="menuForm">
			<table class="table_form"  width="100%">
<!-- 				<tr> -->
<!-- 					<th>订单号：</th> -->
<!-- 					<td><input type="text" name="memOrder.orderNo" -->
<!-- 						class="easyui-numberbox"></td> -->
<!-- 				</tr> -->
				<tr>
					<th>卡号：</th>
					<td><input type="text" name="memOrder.cardNo" id="memOrder.cardNo"
						class="easyui-numberbox"
						onblur="diss()">
					</td>
				</tr>
				<tr>
					<th>下单日期：</th>
					<td><input type="text" id="dd" class="easyui-datebox"
						required="required" name="memOrder.orderDate"
						data-options="editable:'true'"></td>
				</tr>
				<tr>
					<th>订单总金额：</th>
					<td><input name="memOrder.orderAllprice" type="text"
						id="orderAllprice" onKeyUp="ff()" onblur="js()" maxlength="10">
						<input type="hidden" name="dis" id="dis">
				</tr>
				<tr>
					<th>积分：</th>
					<td colspan="3"><input type="text" class="numberbox"
						id="orderIntegral" name="memOrder.orderIntegral"
						readonly="readonly"></td>
				</tr>
				<tr><th>实付：</th><td><input type="text" name="mk.getDiscount" id="mk.getDiscount"  ></td></tr>
				<tr>
					<th>备注：</th>
					<td><input type="text" name="memOrder.orderRemark" ></td>
				</tr>
				</table></form>
				</div>
				
</body>
</html>