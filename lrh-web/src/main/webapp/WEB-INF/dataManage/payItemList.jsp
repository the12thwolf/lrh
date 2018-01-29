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
            
            function deleteFee(id) {
                var r = window.confirm("确定要删除此记录吗？");
                if(r){
                	//相当于手动访问地址
                	window.location.href="dataManage/deletePayItem?itemId="+id;
                }
                document.getElementById("operate_result_info").style.display = "block";
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
                        <c:forEach var="payItem" items="${payItemListLimit}" varStatus="status">
                            <tr>
                                <td>${payItem.itemId}</td>
                                <td>${payItem.itemName}</td>
                                <td>
                                <input type="button" value="删除" class="btn_delete" onclick="deleteFee(${payItem.itemId});" />
                                <input type="button" value="修改" class="btn_modify"
                                           onclick="location.href='dataManage/toPayItemModify?itemId=${payItem.itemId}';" />
                                </td>
                                <%--<td>
                                <form action="dataManage/toPayItemModify">
                                &lt;%&ndash;<input type="button" value="修改" class="btn_modify"
                                onclick="location.href='dataManage/toPayItemModify?itemId=${itemId}';" />&ndash;%&gt;
                                <input id="modifyItemId" type="hidden" name="modifyItemId" value="${itemId}">
                                <input type="button" value="修改" />
                                </form>
                                </td>--%>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
                <!--分页-->
                <div id="pages">
    <c:choose>
        <c:when test="${crruntPage>1}">
            <a href="dataManage/payItemList?crruntPage=<c:out value="${crruntPage-1}"/>">上一页</a>
        </c:when>
        <c:otherwise>上一页</c:otherwise>
    </c:choose>
		&nbsp;&nbsp;&nbsp;&nbsp;
    <c:choose>
        <c:when test="${crruntPage<totalPages}">
            <a href="dataManage/payItemList?crruntPage=<c:out value="${crruntPage+1}"/>">下一页</a>
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
