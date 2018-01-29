<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <base href="<%=basePath%>" />
    <script type="text/javascript" src="js/regist.js"></script>
    <script type="text/javascript" src="js/prototype-1.6.0.3.js"></script>
    <script type="text/javascript" src="js/jquery-1.4.3.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script type="text/javascript">

        function checkItemNameExist(){
            var itemName = $('#itemName').val();
            if(itemName ==""){
                $("#itemNameErrMsg").text("数据不能为空").addClass("error_msg");
                return;
            }
            $.post(
                "ajax/checkItemNameExist",
                {"itemName":itemName},
                function(data){
                    var info = data;
                    $("#itemNameErrMsg").text(info.checkItemNameExistMessage);
                    if(info.alreadyExist){
                        $("#itemNameErrMsg").addClass("error_msg");
                    }else{
                        $("#itemNameErrMsg").removeClass("error_msg");
                    }
                }
            );
        }
    </script>
    <title>数据维护</title>
    <link type="text/css" rel="stylesheet" media="all" href="styles/global.css" />
    <link type="text/css" rel="stylesheet" media="all" href="styles/global_color.css" />

    <script language="javascript" type="text/javascript">
        //保存结果的提示
        function sumbit() {
            var itemName = $('#subitemName').val();
            if(itemName ==""){
                $("#itemNameErrMsg").text("项目名不能为空").addClass("error_msg");
                return;
            }else{
                document.getElementById('form1').submit();
            }
        }
        function quxiao(itemName) {
            /*document.getElementById('form1').submit();*/
            /*$("#itemNameErrMsg").text("数据不能为空").addClass("error_msg");*/
            var url ='dataManage/toPaySubitemList?itemName='+itemName;
            document.getElementById('form1').setAttribute('action',url);
            document.getElementById('form1').setAttribute('method','get');
            document.getElementById('form1').submit();
        }
    </script>
</head>
<body>
<!--导航区域开始-->
<div id="navi">
    <ul id="menu">
        <!-- 主页-->	    <li><a href="user_backIndex" class="index_off"></a></li>
        <!-- 支出查询 --><li><a href="payDetail/toPayDetailList" class="role_off"></a></li>
        <!-- 数据维护 --><li><a href="dataManage/payItemList" class="admin_off"></a></li>
        <!-- 修改密码 --><li><a href="lrhpassword/password_toChangePwdPage" class="password_off"></a></li>
    </ul>
</div>
<!--导航区域结束-->
<!--主要区域开始-->
<div id="main">
    <form action="dataManage/paySubitemModify" method="post" class="main_form" id="form1">
        <div class="text_info clearfix"><span>支出类型名称：</span></div>
        <div class="input_info">
            <input type="text" id="subitemName" class="width300" name="subitemName" value="${paySubitem.subitemName}"/>
            <input id="subitemId" type="hidden" name="subitemId" value="${paySubitem.subitemId}">
            <input id="itemName" type="hidden" name="itemName" value="${paySubitem.itemName}">
            <span class="required" id="required">*</span>
            <span class="required">${message}</span>
            <div class="validate_msg_short"  id="itemNameErrMsg"></div>
        </div>
        <div class="button_info clearfix">
            <input type="button" value="修改" class="btn_save"  onclick="sumbit();" />
            <input type="button" value="取消" class="btn_save" onclick="quxiao(${paySubitem.itemName});"/>
        </div>
    </form>
</div>
<!--主要区域结束-->
<div id="footer">
    <span>我希望一切都好</span>
    <br />
    <span>the12thwolf@126.com ,QQ:361091308 </span>
</div>
</body>
</html>
