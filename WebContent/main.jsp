<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:include page="/common/common.jsp"></jsp:include>
<head>
<title></title>
<script type="text/javascript" src="js/index.js"></script>
</head>
<body class="easyui-layout" style="overflow-y: hidden" fit="true"
	scroll="no">
	<noscript>
		<div
			style="position: absolute; z-index: 100000; height: 2046px; top: 0px; left: 0px; width: 100%; background: white; text-align: center;">
			<img src="images/noscript.gif" alt='抱歉，请开启脚本支持！' />
		</div>
	</noscript>

	<div id="loading-mask"
		style="position: absolute; top: 0px; left: 0px; width: 100%; height: 100%; background: #D2E0F2; z-index: 20000">
		<div id="pageloading"
			style="position: absolute; top: 50%; left: 50%; margin: -120px 0px 0px -120px; text-align: center; border: 2px solid #8DB2E3; width: 200px; height: 40px; font-size: 14px; padding: 10px; font-weight: bold; background: #fff; color: #15428B;">
			<img src="images/loading.gif" align="absmiddle" /> 正在加载中,请稍候...
		</div>
	</div>

	<div region="north" split="true" border="false"
		style="overflow: hidden; height: 40px; background: url(images/layout-browser-hd-bg.gif) #7f99be repeat-x center 50%; line-height: 30px; color: #fff; font-family: Verdana, 微软雅黑, 黑体">
		<span style="float: right; padding-right: 20px;" class="head">欢迎
			<span id="showusername">${user.adminName}</span>&nbsp;&nbsp;&nbsp;<a href="#" id="editUser">更换用户</a> <a href="#" id="editpass">修改密码</a> <a href="#" id="loginOut">安全退出</a>
		</span> <span style="padding-left: 10px; font-size: 16px;"><img
			src="images/blocks.gif" width="20" height="20" align="absmiddle" />
			 </span>
	</div>
	<div region="south" split="true"
		style="height: 30px; background: #D2E0F2;">
		<div class="footer">By GS334</div>
	</div>
	<div region="west" split="true" title="导航菜单" style="width: 180px;"
		id="west">
		<div id="nav">
			<!--  导航内容 -->

		</div>

	</div>
	<div id="mainPanle" region="center"
		style="background: #eee; overflow-y: hidden">
		<div id="tabs" class="easyui-tabs" fit="true" border="false">
			<div title="欢迎使用"
				style="padding: 20px; overflow: hidden; color: red;">
				<h1 style="font-size: 40px;"></h1>
				
			</div>
		</div>
	</div>


	<!--登陆窗口-->
	<div id="w" class="easyui-window" title="用户登录" collapsible="false"
		minimizable="false" maximizable="false" icon="icon-save"
		style="width: 300px; height: 150px; padding: 5px; background: #fafafa;">
		<div class="easyui-layout" fit="true">
			<div region="center" border="false"
				style="padding: 10px; background: #fff; border: 1px solid #ccc;">
				<table cellpadding=3>
					<tr>
						<td>用户名：</td>
						<td><input id="adminName" type="text" class="txt01" /></td>
					</tr>
					<tr>
						<td>密码：</td>
						<td><input id="adminPassword" type="password" class="txt01" />
						</td>
					</tr>
				</table>
			</div>
			<div region="south" border="false"
				style="text-align: right; height: 30px; line-height: 30px;">
				<a id="btnEp" class="easyui-linkbutton" icon="icon-ok"
					href="javascript:void(0)"> 确定</a> <a id="btnCancel"
					class="easyui-linkbutton" icon="icon-cancel"
					href="javascript:void(0)">取消</a>
			</div>
		</div>
	</div>
	<div id="editPassWin" class="easyui-window" title="修改密码" collapsible="false" minimizable="false"
        maximizable="false" icon="icon-save"  style="width: 300px; height: 150px; padding: 5px;
        background: #fafafa;">
        <div class="easyui-layout" fit="true">
            <div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">
                <table cellpadding=3 width="100%" class="table_form">
                 <tr>
                        <td>原密码：</td>
                        <td><input id="pass" type="password" class="txt01" /></td>
                    </tr>
                    <tr>
                        <td>新密码：</td>
                        <td><input id="txtNewPass" type="password" class="txt01" /></td>
                    </tr>
                    <tr>
                        <td>确认密码：</td>
                        <td><input id="txtRePass" type="password" class="txt01" /></td>
                    </tr>
                </table>
            </div>
            <div region="south" border="false" style="text-align: right; height: 30px; line-height: 30px;">
                <a id="btnPass" class="easyui-linkbutton" icon="icon-ok" href="javascript:void(0)" >
                    确定</a> <a id="btnPassResult" class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0)">取消</a>
            </div>
        </div>
    </div>

	<div id="mm" class="easyui-menu" style="width: 150px;">
		<div id="tabupdate">刷新</div>
		<div class="menu-sep"></div>
		<div id="close">关闭</div>
		<div id="closeall">全部关闭</div>
		<div id="closeother">除此之外全部关闭</div>
		<div class="menu-sep"></div>
		<div id="closeright">当前页右侧全部关闭</div>
		<div id="closeleft">当前页左侧全部关闭</div>
		<div class="menu-sep"></div>
		<div id="exit">退出</div>
	</div>
	<script>
		//设置登录窗口
		function openPwd() {
			$('#w').window({
				title : '用户登录',
				width : 300,
				modal : true,
				shadow : true,
				closed : true,
				height : 160,
				resizable : false
			});
		}
		 function openEditPwd() {
	            $('#editPassWin').window({
	                title: '修改密码',
	                width: 320,
	                modal: true,
	                shadow: true,
	                closed: true,
	                height: 200,
	                resizable:false
	            });
	        }
		//关闭登录窗口
		function closePwd() {
			$('#w').window('close');
		}
		function closePwdWin() {
			$('#editPassWin').window('close');
		}
		//修改密码
		function serverLogin() {
			var $username = $('#adminName');
			var $rePass = $('#adminPassword');

			if ($username.val() == '') {
				msgShow('系统提示', '请输入用户名！', 'warning');
				return false;
			}
			if ($rePass.val() == '') {
				msgShow('系统提示', '请输入密码！', 'warning');
				return false;
			}
			$.ajax({
				url:myObjectPath + '/checkUser.action',
				type:'post',
				dataType:'text',
				data:{'admin.adminName':$username.val(),'admin.adminPassword':$rePass.val(),'time':new Date()},
				success:function(data){
					if(data=='success'){
						$("#showusername").html($username.val());//放置用户名到右上角
						$username.val('');
						$rePass.val('');
						closePwd();
						showMessage("操作提示","登录系统成功");
						getMenus();//加载菜单
					}else{
						msgShow('系统提示', data, 'warning');
					}
				}
			});		
		}
		function serverLoginPass() {
			 	var $newpass = $('#txtNewPass');
	            var $repeatPass = $('#txtRePass');
				var $pass = $('#pass');
				 if ($pass.val() == '') {
		                msgShow('系统提示', '请输入原密码！', 'warning');
		                return false;
		            }
	            if ($newpass.val() == '') {
	                msgShow('系统提示', '请输入密码！', 'warning');
	                return false;
	            }
	            if ($repeatPass.val() == '') {
	                msgShow('系统提示', '请在一次输入密码！', 'warning');
	                return false;
	            }

	            if ($newpass.val() != $repeatPass.val()) {
	                msgShow('系统提示', '两次密码不一至！请重新输入', 'warning');
	                return false;
	            }
	            $.ajax({
					url:myObjectPath + '/editUserpass.action',
					type:'post',
					dataType:'text',
					data:{'admin.adminPassword':$pass.val(),'newpassword':$newpass.val(),'time':new Date()},
					success:function(data){
						if(data=='success'){
							$pass.val('');
							$newpass.val('');
							$repeatPass.val('');
							closePwdWin();
							showMessage("操作提示","用户登录密码修改完毕。");
						}else{
							msgShow('系统提示', data, 'warning');
						}
					}
				});		
		}

		$(function() {
			openPwd();
			openEditPwd();
			//判断有没有登陆信息
			if(${user==null}){$('#w').window('open');}else{getMenus();}
			$('#editUser').click(function() {
				$('#w').window('open');
			});
			$('#editpass').click(function() {
				$('#editPassWin').window('open');
			})
			$('#btnEp').click(function() {
				serverLogin();
			})
			$('#btnPass').click(function() {
				serverLoginPass();
			})
			$('#btnCancel').click(function() {
				$('#adminPassword').val('');
				closePwd();
			})
			$('#btnPassResult').click(function() {
				$('#txtNewPass').val('');
				closePwdWin();
			})

			$('#loginOut').click(function() {
				$.messager.confirm('系统提示', '您确定要退出本次登录吗?', function(r) {
					if (r) {
						location.href =myObjectPath+ '/loginout.action';
					}
				});
			})
		});
		
	</script>
</body>
