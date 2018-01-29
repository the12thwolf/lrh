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
<script type="text/javascript" src="js/laydate/laydate.js"></script>
<script type="text/javascript" src="js/prototype-1.6.0.3.js"></script>
<script type="text/javascript" src="js/jquery-1.4.3.js"></script>


<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <style>
            *{margin:0;padding:0;list-style:none;}
            html{background-color:#E3E3E3; font-size:14px; color:#000; font-family:'微软雅黑'}
            h2{line-height:30px; font-size:20px;}
            a,a:hover{ text-decoration:none;}
            pre{font-family:'微软雅黑'}
            .box{width:970px; padding:10px 20px; background-color:#fff; margin:10px auto;}
            .box a{padding-right:20px;}
            .demo1,.demo2,.demo3,.demo4,.demo5,.demo6{margin:50px 0;}
            h3{margin:10px 0;}
            .layinput{height: 22px;line-height: 22px;width: 150px;margin: 0;}
        </style>
<title>增加商品类型</title>
<link type="text/css" rel="stylesheet" media="all" href="styles/global.css" />
<link type="text/css" rel="stylesheet" media="all" href="styles/global_color.css" />



    </head>
    <body>
        <!--导航区域开始-->
        <div id="navi">
            <ul id="menu">
<!-- 主页-->	    <li><a href="user_backIndex" class="index_off"></a></li>
<!-- 采购管理 --><li><a href="payDetail/toPayDetailList" class="role_off"></a></li>
				<li><a href="../admin/admin_list.html" class="saleManage_off"></a></li>
                <li><a href="stock/stock_stockList" class="account_off"></a></li>
<!-- 数据维护 --><li><a href="dataManage/payItemList" class="admin_off"></a></li>
                <li><a href="fee/fee_list.action" class="fee_off"></a></li>
                <li><a href="../report/report_list.html" class="report_off"></a></li>
                <li><a href="../user/user_modi_pwd.html" class="password_off"></a></li>
            </ul>      
        </div>
        <!--导航区域结束-->




































        <!--主要区域开始-->
        <div id="main">            
            <form action="dataManage/addPaySubitem" method="post" class="main_form" id="form1">
                <div class="text_info clearfix"><span></span></div>
                <div class="input_info">
                    <span class="">请选择大类:</span>
                    <select id="itemName" name="itemName">
                        <option value="0">请选择</option>
                    </select>
                </div>
                <div class="box">
                    <div class="demo6">
                        支出日期:
                        <input readonly class="layinput" name="addDate" id="hello1">
                        <div class="laydate-icon " onclick="laydate({elem: '#hello1'});" style="width:10px;display:inline-block;border:none;"></div>
                    </div>
                </div>
                <div class="input_info">
                    <span >支出描述:&nbsp;&nbsp;&nbsp;</span>
                    <input type="text" class="width300" name="subitemName" id="subitemName"  />
                    <input type="hidden" name="itemNameOld" id="itemNameOld" value="${itemNameOld}" />

                    <span class="required">*</span>
                    <span class="required" id="message">${message}</span>
                    <div class="validate_msg_short"  id="subitemNameErrMsg"></div>
                    <div class="validate_msg_short"></div>
                </div>
                <div class="button_info clearfix">&nbsp;&nbsp;&nbsp;<br><br></div>
                <div class="button_info clearfix">
                    <input type="button" value="保存" class="btn_save"  onclick="sumbit();" />
                    <input type="button" value="取消" class="btn_save" onclick="quxiao(${itemNameOld});"/>
                </div>
            </form>  
        </div>
        <!--主要区域结束-->
        <div id="footer">
            <span>我希望一切都好&nbsp;&nbsp;&nbsp;日期插件来源：<a href="http://sc.chinaz.com/" target="_blank">站长素材</a></span>
            <br />
            <span>邮箱:the12thwolf@126.com  &nbsp;&nbsp;&nbsp;&nbsp; QQ:361091308 &nbsp;&nbsp;&nbsp;&nbsp;
		微信:361091308 &nbsp;&nbsp;&nbsp;&nbsp; 手机:13952023017 </span>

        </div>
    </body>
</html>
