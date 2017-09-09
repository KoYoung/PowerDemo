<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:include page="/common/common.jsp"></jsp:include>
<body class="easyui-layout">
	<div data-options="region:'west',title:'',split:false"
		style="width: 180px;">
		<div id="leftpanel" class="easyui-panel" title="部门管理"
			data-options="fit:true,border:false">
			<ul id="deptTree">
			</ul>
		</div>
	</div>
	<div data-options="region:'center',title:'员工信息'">
		<table id="actioninfo"></table>
	</div>
	<div id="mm" class="easyui-menu" style="width: 120px"
		data-options="onClick:menuHandler">
		<div data-options="name:'save',iconCls:'icon-edit_add'">增加</div>
		<div data-options="name:'edit',iconCls:'icon-edit'">修改</div>
		<div data-options="name:'del',iconCls:'icon-remove'">删除</div>
	</div>
<div id="empmm" class="easyui-menu" style="width: 120px"
		data-options="onClick:empHandler">
		<div data-options="name:'addrole',iconCls:'icon-add'">分配角色</div>
		<div data-options="name:'save',iconCls:'icon-edit_add'">增加</div>
		<div data-options="name:'edit',iconCls:'icon-edit'">修改</div>
		<div data-options="name:'del',iconCls:'icon-del'">删除</div>
		<div data-options="name:'lock',iconCls:'icon-remove'">冻结/解冻</div>
	</div>
<div id="deptRemoveWin" style="diaplay: none">
	<ul id="deptInfo"></ul>
</div>
	<div id="showAddEmp" style="diaplay: none">
		<form method="post" id="empForm">
			<table class="table_form" width="100%">
				<tr>
					<input id="staffId" type="hidden" name="staff.staffId"/>
					<input id="status" type="hidden" name="staff.status"/>
					<th>名称</th>
					<td><input id="staffName" name="staff.staffName"
						class="easyui-validatebox" data-options="required:true" value=""></td>
					<th>性别</th>
					<td>
					 <input id="staffSex" name="staff.staffSex" class="easyui-combobox" value=""
						data-options="valueField:'id',textField:'text',panelHeight:50,data: [{id:100101,text:'男'},{id:100102,text:'女'}]">
					</td>
				</tr>
				<tr>
					<th>登陆账号</th>
					<td colspan="3"> <input id="adminName" name="staff.adminName"><font color="red" id="resultName"></font></td>
				</tr>
				<tr>
					<th>生日</th>
					<td> <input type="text" id="staffBirthday" name="staff.staffBirthday" class="easyui-datebox"></td>
					<th>年龄</th>
					<td><input id="staffAge" name="staff.staffAge" class="easyui-numberbox" readonly="readonly"></td>
				</tr>
				<tr>
					<th>电话</th>
					<td colspan="3"><input name="staff.staffTel" id="staffTel" size="50"/></td>
				</tr>
				<tr>
					<th>地址</th>
					<td colspan="3"><input name="staff.staffAddress" id="staffAddress"
						size='50'></td>
				</tr>
				<tr>
					<th>备注</th>
					<td colspan="3"><textarea rows="2" cols="70" name="staff.remark" id="remark"></textarea></td>
				</tr>
				<tr>
					<th><font color='red'>请注意</font></th>
					<td colspan="3"><font color='red'>
					1.默认情况下，登陆账号为中文名称拼音小写。<br/>
					2.您可以在<font color='blue'>新添加员工时</font>修改您的登陆账号，默认的登陆密码为：888888<br/>
					3.请您在登陆系统后第一时间修改默认密码。</font></td>
				</tr>
			</table>
		</form>
	</div>
	<script type="text/javascript"
		src='<%=request.getContextPath()%>/dept/staffinfo.js'>
	</script>
</body>


