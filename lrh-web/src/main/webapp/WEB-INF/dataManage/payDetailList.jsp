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
    <script type="text/javascript" src="js/jquery-3.2.1.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <style>
        *{margin:0;padding:0;list-style:none;}
        html{background-color:#E3E3E3; font-size:14px; color:#000; font-family:'微软雅黑'}
        h2{line-height:30px; font-size:20px;}
        a,a:hover{ text-decoration:none;}
        pre{font-family:'微软雅黑'}
        .box{width:970px; padding:10px 20px; background-color:#fff; margin:10px auto;}
        .box a{padding-right:20px;}
        .demo1,.demo2,.demo3,.demo4,.demo5,.demo6{margin:10px 0;}
        h3{margin:10px 0;}
        .layinput{height: 22px;line-height: 22px;width: 150px;margin: 0;}
    </style>
    <title>基础数据维护</title>
    <link type="text/css" rel="stylesheet" media="all" href="styles/global.css" />
    <link type="text/css" rel="stylesheet" media="all" href="styles/global_color.css" />
    <script language="javascript" type="text/javascript">
        function changeTab(e,ulObj) {
            var obj = e.srcElement || e.target;
            if (obj.nodeName == "A") {
                var links = ulObj.getElementsByTagName("a");
                for (var i = 0; i < links.length; i++) {
                    if (links[i].innerHTML == obj.innerHTML) {
                        links[i].className = "tab_on";
                    }
                    else {
                        links[i].className = "tab_out";
                    }
                }
            }
        }
        //页面初始化的时候组织支出主体数据
        $(function(){
            $.ajax({
                url: "ajax/payPersonList",
                // 数据发送方式
                type: "post",
                // 接受数据格式
                dataType : "json",
                success:function(payPersonList){
                    //$("input[name='payPerson']").attr("checked", true);
                    $("#payPerson").empty();
                    $('#payPerson').append('<option value="">全部</option>');
                    var json = eval(payPersonList);
                    for(var i=0;i<json.length;i++){
                        var payPerson = json[i];
                        $('#payPerson').append('<option value="'+payPerson.payPersonName+'">'+payPerson.payPersonName+'</option>');
                    }
                },
                'error':function(){
                    $("#payPerson").empty();
                }
            });
        });

        function modifyPayDetail(payId,itemName,subitemName,payPerson,payDateStart,payDateEnd,crruntPage){
            itemNameNew=encodeURIComponent(encodeURIComponent(itemName));
            subitemNameNew=encodeURIComponent(encodeURIComponent(subitemName));
            payPersonNew=encodeURIComponent(encodeURIComponent(payPerson));
            //涉及到查询的汉字字段转码
            window.location.href="payDetail/toModifyPayDetail?payId="+payId+"&itemName="+itemNameNew+"&subitemName="+subitemNameNew+"&payPerson="+payPersonNew+"&payDateStart="+payDateStart+"&payDateEnd="+payDateEnd+"&crruntPage="+crruntPage;
        }


        function deleteFee(payId,itemName,subitemName,payPerson,payDateStart,payDateEnd,crruntPage) {
        /*function deleteFee(payId,itemName,subitemName,payPerson) {*/
            var r = window.confirm("确定要删除此记录吗？");
            if(r){
                itemNameNew=encodeURIComponent(encodeURIComponent(itemName));
                subitemNameNew=encodeURIComponent(encodeURIComponent(subitemName));
                payPersonNew=encodeURIComponent(encodeURIComponent(payPerson));
                //相当于手动访问地址
                /*window.location.href="payDetail/deletePayDetail?payId="+payId+"&itemName="+itemName;*/
                window.location.href="payDetail/deletePayDetail?payId="+payId+"&itemName="+itemNameNew+"&subitemName="+subitemNameNew+"&payPerson="+payPersonNew+"&payDateStart="+payDateStart+"&payDateEnd="+payDateEnd+"&crruntPage="+crruntPage;

                //window.location.href="payDetail/deletePayDetail?payId="+payId+"&itemName="+itemName+"&subitemName="+subitemName+"&payPerson="+payPerson;
            }
            document.getElementById("operate_result_info").style.display = "block";
        }

        //初始化下拉框菜单
        $(function(){
            var itemNameInit='itemNameInit';
            $.ajax({
                url: "ajax/payItemList",
                // 数据发送方式
                type: "post",
                // 接受数据格式
                dataType : "json",
                success:function(payItemList){
                    $('#itemName').empty();
                    var json = eval(payItemList);
                    $('#itemName').append('<option value="">全部</option>');
                    for(var i=0;i<json.length;i++){
                        var payItem = json[i];
                        $('#itemName').append('<option value="'+payItem.itemName+'">'+payItem.itemName+'</option>');
                    }
                },
                'error':function(){
                    $('#itemName').empty();
                }
            });
            //getSubPayitemsByItemNameInit(itemNameInit);
            //初始化二级联动的，查询页面可以不需要
        });


        function getSubPayitemsByItemNameInit(itemNameInit){
            //二级联动菜单

            /*var itemName=document.getElementById("itemName").value;*/
            /*var itemName = $('#itemName').val();*/
            var itemName=itemNameInit;
            $.ajax({
                url: "ajax/getPaySubitemsByItemName",
                // 数据发送方式
                type: "post",
                data:{"itemName":itemName},
                // 接受数据格式
                dataType : "json",
                success:function(paySubitemList){
                    $('#subitemName').empty();
                    var json = eval(paySubitemList);
                    $('#subitemName').append('<option value="">全部</option>');
                    for(var i=0;i<json.length;i++){
                        var paySubitem = json[i];
                        $('#subitemName').append('<option value="'+paySubitem.subitemName+'">'+paySubitem.subitemName+'</option>');
                    }
                },
                'error':function(){
                    $('#subitemName').empty();
                }
            });

        }

        function getSubPayitemsByItemName(){
            //二级联动菜单
            var itemName=document.getElementById("itemName").value;

            $.ajax({
                url: "ajax/getPaySubitemsByItemName",
                // 数据发送方式
                type: "post",
                data:{"itemName":itemName},
                // 接受数据格式
                dataType : "json",
                success:function(paySubitemList){
                    $('#subitemName').empty();

                    var json = eval(paySubitemList);
                    $('#subitemName').append('<option value="">全部</option>');
                    for(var i=0;i<json.length;i++){
                        var paySubitem = json[i];
                        $('#subitemName').append('<option value="'+paySubitem.subitemName+'">'+paySubitem.subitemName+'</option>');
                    }
                },
                'error':function(){
                    $('#subitemName').empty();
                }
            });
        }



        function toPayDetailListSubmit(){
            document.getElementById('toPayDetailList').submit();
        }

        function nextPage(itemName,subitemName,payPerson,payDateStart,payDateEnd,crruntPage) {
            itemNameNew=encodeURIComponent(encodeURIComponent(itemName));
            subitemNameNew=encodeURIComponent(encodeURIComponent(subitemName));
            payPersonNew=encodeURIComponent(encodeURIComponent(payPerson));
            //相当于手动访问地址
            var url="payDetail/toPayDetailList?itemName="+itemNameNew+"&subitemName="+subitemNameNew+"&payPerson="+payPersonNew+"&payDateStart="+payDateStart+"&payDateEnd="+payDateEnd+"&crruntPage="+crruntPage;
            //alert(url);
            window.location.href="payDetail/toPayDetailList?itemName="+itemNameNew+"&subitemName="+subitemNameNew+"&payPerson="+payPersonNew+"&payDateStart="+payDateStart+"&payDateEnd="+payDateEnd+"&crruntPage="+crruntPage;

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
        <!-- 主页-->	    <li><a href="user_backIndex" class="index_off"></a></li>
        <!-- 支出查询 --><li><a href="payDetail/toPayDetailList" class="role_off"></a></li>
        <!-- 数据维护 --><li><a href="dataManage/payItemList" class="admin_off"></a></li>
        <!-- 修改密码 --><li><a href="lrhpassword/password_toChangePwdPage" class="password_off"></a></li>
    </ul>
</div>
<!--导航区域结束-->
<%--查询的操作界面--%>
<div align="center">
    <form action="payDetail/toPayDetailList" method="post" id="toPayDetailList">
        <span >项目名称:</span>
        <select id="itemName" name="itemName" onchange="getSubPayitemsByItemName();">
            <option value="">全部</option>
        </select>&nbsp;&nbsp;
        <span >用途:</span>
        <select id="subitemName" name="subitemName">
            <option value="">全部</option>
        </select>
        <span >支出主体:</span>
        <select id="payPerson" name="payPerson">
            <option value="">全部</option>
        </select>&nbsp;&nbsp;&nbsp;&nbsp;
        开始日期:
        <input readonly class="layinput" name="payDateStart" id="payDateStart">
        <div class="laydate-icon " onclick="laydate({elem: '#payDateStart'});" style="width:10px;display:inline-block;border:none;"></div>
        结束日期:
        <input readonly class="layinput" name="payDateEnd" id="payDateEnd">
        <div class="laydate-icon " onclick="laydate({elem: '#payDateEnd'});" style="width:10px;display:inline-block;border:none;"></div>
        <span class="required" id="payDateEndMessage">${message}</span>
        <div class="validate_msg_short"  id="payDateEndMessageInfo"></div>

        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    </form>

    <div >
    <form action="payDetail/toPayDetailAdd" method="post" id="toPayDetailAdd">
        <input type="hidden" name="oldFlag" id="oldFlag" value="1" />
        <%--oldFlag 0-查询跳转的页面 1-其他操作跳转的页面--%>
    <input type="hidden" name="crruntPage" id="crruntPage" value="${payDetailDataForPage.crruntPage}" />
    <input type="hidden" name="itemNameOld" id="itemNameOld" value="${payDetailDataForPage.itemName}" />
    <input type="hidden" name="subitemNameOld" id="subitemNameOld" value="${payDetailDataForPage.subitemName}" />
    <input type="hidden" name="payPersonOld" id="payPersonOld" value="${payDetailDataForPage.payPerson}" />
    <input type="hidden" name="payDateStartOld" id="payDateStartOld" value="${payDetailDataForPage.payDateStart}" />
    <input type="hidden" name="payDateEndOld" id="payDateEndOld" value="${payDetailDataForPage.payDateEnd}" />
    <input type="button" value="查询" class="btn_add" id="queryList" onclick="toPayDetailListSubmit();"/>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <input type="submit" value="增加记录" class="btn_add">
    </form>
    </div>

</div>
<!--主要区域开始-->
<div id="report_main">
    <div class="report_box">
        <!--数据区域：用表格展示数据-->
        <div id="report_data">
            <table id="datalist">
                <tr>

                    <th>支出项目名称</th>
                    <th>支出用途</th>
                    <th>支出金额(元)</th>
                    <th>人员</th>
                    <th>支出日期</th>
                    <th>备注</th>
                    <th>操作类型</th>
                </tr>
                <c:forEach var="payDetail" items="${payDetailListLimit}" varStatus="status">
                    <tr>
                        <td>${payDetail.itemName}</td>
                        <td>${payDetail.subitemName}</td>
                        <td>${payDetail.payAmount}</td>
                        <td>${payDetail.payPerson}</td>
                        <td>${payDetail.payDate}</td>
                        <td>${payDetail.contant}</td>
                        <td>
                            <input type="button" value="删除" class="btn_delete" onclick="deleteFee('${payDetail.payId}','${payDetailDataForPage.itemName}','${payDetailDataForPage.subitemName}','${payDetailDataForPage.payPerson}','${payDetailDataForPage.payDateStart}','${payDetailDataForPage.payDateEnd}','${crruntPage}');" />
                            <input type="button" value="修改" class="btn_modify"
                                   onclick="modifyPayDetail('${payDetail.payId}','${payDetailDataForPage.itemName}','${payDetailDataForPage.subitemName}','${payDetailDataForPage.payPerson}','${payDetailDataForPage.payDateStart}','${payDetailDataForPage.payDateEnd}','${crruntPage}');" />
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <!--分页-->
        <div id="pages">
            <c:choose>
                <c:when test="${crruntPage>1}">
                    <a href="javascript:nextPage('${payDetailDataForPage.itemName}','${payDetailDataForPage.subitemName}','${payDetailDataForPage.payPerson}','${payDetailDataForPage.payDateStart}','${payDetailDataForPage.payDateEnd}','${crruntPage-1}');">
                        上一页</a>
                </c:when>
                <c:otherwise>上一页</c:otherwise>
            </c:choose>
            &nbsp;&nbsp;&nbsp;&nbsp;
            <c:choose>
                <c:when test="${crruntPage<totalPages}">
                    <a href="javascript:nextPage('${payDetailDataForPage.itemName}','${payDetailDataForPage.subitemName}','${payDetailDataForPage.payPerson}','${payDetailDataForPage.payDateStart}','${payDetailDataForPage.payDateEnd}','${crruntPage+1}');"
                       >下一页</a>
                </c:when>
                <c:otherwise>下一页</c:otherwise>
            </c:choose>
        </div>

    </div>
</div>
<!--主要区域结束-->
<div id="footer">
    <p>我希望一切都好</p>
    <p>the12thwolf@126.com </p>
</div>
</body>

</html>
