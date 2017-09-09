<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%String MyObjectPath =request.getContextPath();
pageContext.setAttribute("MyObjectPath", MyObjectPath);%>
<link rel="stylesheet" type="text/css" href="${MyObjectPath}/css/default.css" />
<link rel="stylesheet" type="text/css" href="${MyObjectPath}/js/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="${MyObjectPath}/js/themes/icon.css" />

<script type="text/javascript" src="${MyObjectPath}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${MyObjectPath}/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${MyObjectPath}/js/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" type="text/css" href="${MyObjectPath}/css/mybackcss.css" />
<!--  -->
<script type="text/javascript">
	var myObjectPath="${MyObjectPath}";
	function showMessage(title,message){  
        $.messager.show({  
            title:title,  
            msg:message,  
            showType:'show'  
        });  
    }
</script>