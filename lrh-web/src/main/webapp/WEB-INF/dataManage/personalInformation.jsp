<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <base href="<%=basePath%>" />
    <script type="text/javascript" src="js/prototype-1.6.0.3.js"></script>
    <script type="text/javascript" src="js/jquery-1.4.3.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>修改密码</title>
    <link type="text/css" rel="stylesheet" media="all" href="styles/global.css" />
    <link type="text/css" rel="stylesheet" media="all" href="styles/global_color.css" />
    <script language="javascript" type="text/javascript">
        //保存结果的提示
        function sumbit() {
            var newPasswordCoform = $('#newPasswordCoform').val();
            var newPassword = $('#newPassword').val();
            if(newPasswordCoform ==""){
                $("#newPasswordCoformMsg").text("不能为空").addClass("error_msg");
                return;
            }else if(newPasswordCoform != newPassword){
                $("#newPasswordCoformMsg").text("两次密码输入不一致").addClass("error_msg");
                return;
            }else{
                document.getElementById('form1').submit();
            }

        }

        /**
         检查旧密码的输入是否符合规范
         这里暂时检查是否为空
         */
        function checkOldPassword(){
            var oldPassword = $('#oldPassword').val();
            if(oldPassword ==""){
                $("#oldPasswordMsg").text("请输入密码").addClass("error_msg");
                //window.location.reload(true);
                return;
            }
        }
        /**
         检查新密码的是否符合规范
         可以检查是否有特殊字符 等等
         这里只暂时检查是否是空
         */
        function checkNewPassword(){
            var newPassword = $('#newPassword').val();
            if(newPassword ==""){
                $("#newPasswordMsg").text("新密码不能为空").addClass("error_msg");
                return;
            }
        }

        /**
         检查新密码的是否符合规范
         可以检查是否有特殊字符 等等
         这里只暂时检查是否是空 是否和上面输入的一致
         */
        function checkNewPasswordCoform(){
            var newPasswordCoform = $('#newPasswordCoform').val();
            var newPassword = $('#newPassword').val();
            if(newPasswordCoform ==""){
                $("#newPasswordCoformMsg").text("不能为空").addClass("error_msg");
                return;
            }
            if(newPasswordCoform != newPassword){
                $("#newPasswordCoformMsg").text("两次密码输入不一致").addClass("error_msg");
                return;
            }
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
        <!-- 个人信息 --><li><a href="personal/personalInformation" class="information_on"></a></li>
        <!-- 修改密码 --><li><a href="lrhpassword/password_toChangePwdPage" class="password_off"></a></li>
    </ul>
</div>
<!--导航区域结束-->
<!--主要区域开始-->

<div id="main">

    <form name="picture" action="personal/uploadPersonalPhoto" method="post"  enctype="multipart/form-data">
        <img height="70" width="70" sizes="3" srcset="" src="<%=basePath%>/images/1.png" alt="">
        <h6>修改头像</h6>
        <input type="file" name="picture">
        <input type="submit" value="上传"/>
    </form>


    <form action="lrhpassword/password_modifyPassword" method="post" class="main_form" id="form1">
        <div class="text_info clearfix"><span>原密码：</span></div>
        <div class="input_info">
            <input type="password" value="" name="password.oldPassword"
                   id="oldPassword" onblur="checkOldPassword();"/>
            <span class="required">*</span>
            <div class="validate_msg_short"  id="oldPasswordMsg"></div>
        </div>

        <div class="text_info clearfix"><span>新密码：</span></div>
        <div class="input_info">
            <input type="password" class="width300" value="" name="password.newPassword"
                   id="newPassword" onblur="checkNewPassword();"/>
            <span class="required">*</span>
            <div class="validate_msg_short"  id="newPasswordMsg"></div>
        </div>

        <div class="text_info clearfix"><span>新密码确认：</span></div>
        <div class="input_info">
            <input type="password" class="width300" value="" name="password.newPasswordCoform"
                   id="newPasswordCoform" onblur="checkNewPasswordCoform();"/>
            <span class="required">*</span>
            <div class="validate_msg_short"  id="newPasswordCoformMsg"></div>
        </div>

        <br/>
        <div class="button_info clearfix">
            <input type="button" value="确定" class="btn_save" onclick="sumbit()" />
            <input type="button" value="取消" class="btn_save" onclick="window.history.go(-1);"/>
            <font color="red">${returnMess }</font>
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
