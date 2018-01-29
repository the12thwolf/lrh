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
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>基础数据维护</title>
    <link type="text/css" rel="stylesheet" media="all" href="styles/global.css" />
    <link type="text/css" rel="stylesheet" media="all" href="styles/global_color.css" />
    <script language="javascript" type="text/javascript">
        $(function(){
            $.ajax({
                url: "ajax/payItemList",
                // 数据发送方式
                type: "post",
                // 接受数据格式
                dataType : "json",
                success:function(payItemList){

                    $('#itemName').empty();
                    var json = eval(payItemList);
                    for(var i=0;i<json.length;i++){
                        var payItem = json[i];
                        $('#itemName').append('<option value="'+payItem.itemId+'">'+payItem.itemName+'</option>');
                    }
                },

                'error':function(){
                    $('#itemName').empty();
                }
            });
        });
    </script>
    <script type="text/javascript">
        function testAjax()
        {
            var rep=$.ajax({
                type: "POST",
                url: "ajax/ajaxTest",
                data: { name: "王五", password: "Boston" },
                success:function(data)
                {
                    var jsons = data;
                    $("#userNameErrMsg").text(jsons.msg)

                }
            });
        }
    </script>

</head>
<body>
<!--Logo区域开始-->
<div id="header">
    <img src="images/header_bg.png" alt="logo" class="left"/>
    <!--  <a href="#">[退出]</a>-->
</div>
<!--Logo区域结束-->
<!--导航区域开始-->
<div id="navi">
    <ul id="menu">
        <li><a href="user_backIndex" class="index_off"></a></li>
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
<%--查询的操作界面--%>
<div>
    <form action="/dataManage/paySubitemList" id="paySubitemList">
        <select id="itemName" name="itemName">
            <option value="0">请选择</option>
        </select>
        <input type="button" value="查询" class="btn_add" />
    </form>
</div>
<input type="button" value="testAjax" onclick="testAjax()"/>
<div id="userNameErrMsg"></div>
<!--主要区域开始-->
<div id="report_main">
    <div class="tabs">
        <ul onclick="changeTab(event,this);">
            <li><a href="dataManage/payItemList" class="tab_on" title="支出项目数据维护">支出项目数据维护</a></li>
            <li><a href="dataManage/toPaySubitemList" class="tab_out" title="支出项目数据维护">支出用途数据维护</a></li>
        </ul>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <input type="button" value="增加记录" class="btn_add"
               onclick="location.href='dataManage/toPayItemAdd';" />
        <s:property value="message"/>
    </div>
    <div class="report_box">
        <!--数据区域：用表格展示数据-->
        <div id="report_data">
            <table id="datalist">
                <tr>
                    <th>支持类型 ID</th>
                    <th>支出类型描述</th>
                    <th>操作类型</th>
                </tr>
            </table>
        </div>
        <!--分页-->
    </div>
</div>
<!--主要区域结束-->
<div id="footer">
    <p>我希望一切都好</p>
    <p>the12thwolf@126.com </p>
</div>
</body>

</html>
