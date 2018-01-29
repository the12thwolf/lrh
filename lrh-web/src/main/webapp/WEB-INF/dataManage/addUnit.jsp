<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
<base href="<%=basePath%>" />
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>增加商品计量单位</title>
		<link type="text/css" rel="stylesheet" media="all" href="styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="styles/global_color.css" /> 
        <script language="javascript" type="text/javascript">
            //保存结果的提示
            function sumbit() {
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
            <form action="dataManage/dataUnit_addDataUnit" method="post" class="main_form" id="form1">
                <div class="text_info clearfix"><span>计量单位名称：</span></div>
                <div class="input_info">
                    <input type="text" class="width300" value="" name="dataUnit.dataUnitName" 
                    id="dataUnitName"/>
                    <span class="required">*</span>
                    <div class="validate_msg_short"></div>
                </div>
                
                <div class="button_info clearfix">
                    <input type="button" value="保存" class="btn_save"  onclick="sumbit();" />
                    <input type="button" value="取消" class="btn_save" />
                </div>
            </form>  
        </div>
        <!--主要区域结束-->
        <div id="footer">
            <span>我希望一切都好</span>
            <br />
            <span>邮箱:the12thwolf@126.com  &nbsp;&nbsp;&nbsp;&nbsp; QQ:361091308 &nbsp;&nbsp;&nbsp;&nbsp;
		微信:361091308 &nbsp;&nbsp;&nbsp;&nbsp; 手机:13952023017 </span>
        </div>
    </body>
</html>
