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
        <title>数据维护-商品计量单位</title>
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
                	
                	window.location.href="dataManage/dataUnit_deleteDataUnit?dataUnit.dataUnitID="+id;
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
                    <li><a href="dataManage/payItemList" class="tab_on" title="商品类型维护">支出项目数据维护</a></li>
                    <li><a href="dataManage/payDescList" class="tab_out" title="维护计量单位">支出用途数据维护</a></li>
                </ul>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <input type="button" value="增加单位" class="btn_add" 
                onclick="location.href='lrhtransition/transition_toDataUnitAdd';" />
                <s:property value="message"/>
            </div>            
            <div class="report_box">
                <!--数据区域：用表格展示数据-->
                <div id="report_data">
                    <table id="datalist">
                        <tr>                            
                            <th>计量单位ID</th>
                            <th>计量单位名称</th>
                            <th></th>
                        </tr>
<s:iterator value="dataUnitList">
	 					<tr>
                            <td><s:property value="dataUnitID" /></td>
                            <td><s:property value="dataUnitName" /></td>
                            <td>
                            <input type="button" value="删除" class="btn_delete" 
                            onclick="deleteFee(<s:property value="dataUnitID" />);" />
                            </td>
                        </tr>
</s:iterator>     
                    </table>
                </div>
                <!--分页-->
                <div id="pages">
<s:if test="page>1">
	<a href="dataManage/dataUnit_dataUnitList?page=<s:property value="page-1" />">上一页</a>
</s:if>
<s:else>
上一页
</s:else>
&nbsp;&nbsp;&nbsp;&nbsp;
<s:if test="page<totalPages">
	<a href="dataManage/dataUnit_dataUnitList?page=<s:property value="page+1" />">下一页</a>
</s:if>
<s:else>
下一页
</s:else>
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
