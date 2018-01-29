<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
<base href="<%=basePath%>" />
		<title>用户首页</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link type="text/css" rel="stylesheet" media="all" href="styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="styles/global_color.css" />
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
                <li><a href="../index.html" class="index_off"></a></li>
                <li><a href="../role/role_list.html" class="role_off"></a></li>
                <li><a href="../admin/admin_list.html" class="saleManage_off"></a></li>
                <li><a href="../admin/admin_list.html" class="admin_off"></a></li>
                <li><a href="fee/fee_list.action" class="fee_on"></a></li>
                <li><a href="../account/account_list.html" class="account_off"></a></li>
                <li><a href="../report/report_list.html" class="report_off"></a></li>
                <li><a href="../user/user_modi_pwd.html" class="password_off"></a></li>
            </ul>            
        </div>
        <!--导航区域结束-->



<!--主要区域开始-->
        <div id="main">
            <form action="" method="">
                <!--排序-->
                <div class="search_add">
                    <div>
                        <input type="button" value="月租" class="sort_asc" onclick="sort(this);" />
                        <input type="button" value="基费" class="sort_asc" onclick="sort(this);" />
                        <input type="button" value="时长" class="sort_asc" onclick="sort(this);" />
                    </div>
                    <input type="button" value="增加" class="btn_add" onclick="location.href='fee_add.html';" />
                </div> 
                <!--启用操作的操作提示-->
                <div id="operate_result_info" class="operate_success">
                    <img src="../images/close.png" onclick="this.parentNode.style.display='none';" />
                    删除成功！
                </div>    
                <!--数据区域：用表格展示数据-->     
                <div id="data">            
                    <table id="datalist">
                        <tr>
                            <th>资费ID</th>
                            <th class="width100">资费名称</th>
                            <th>基本时长</th>
                            <th>基本费用</th>
                            <th>单位费用</th>
                            <th>创建时间</th>
                            <th>开通时间</th>
                            <th class="width50">状态</th>
                            <th class="width200"></th>
                        </tr>                      
                        <tr>
                            <td>1</td>
                            <td><a href="fee_detail.html">包 20 小时</a></td>
                            <td>20 小时</td>
                            <td>24.50 元</td>
                            <td>3.00 元/小时</td>
                            <td>2013/01/01 00:00:00</td>
                            <td></td>
                            <td>暂停</td>
                            <td>                                
                                <input type="button" value="启用" class="btn_start" onclick="startFee();" />
                                <input type="button" value="修改" class="btn_modify" onclick="location.href='fee_modi.html';" />
                                <input type="button" value="删除" class="btn_delete" onclick="deleteFee();" />
                            </td>
                        </tr>
                        <tr>
                            <td>2</td>
                            <td><a href="fee_detail.html">包 40 小时</a></td>
                            <td>40 小时</td>
                            <td>40.50 元</td>
                            <td>3.00 元/小时</td>
                            <td>2013/01/21 00:00:00</td>
                            <td>2013/01/23 00:00:00</td>
                            <td>开通</td>
                            <td>                                
                            </td>
                        </tr>
                    </table>
                    <p>
                    业务描述 内容 现在还没有
                    </p>
                </div>
                <!--分页-->
                <div id="pages">
        	        <a href="#">上一页</a>
                    <a href="#" class="current_page">1</a>
                    <a href="#">2</a>
                    <a href="#">3</a>
                    <a href="#">4</a>
                    <a href="#">5</a>
                    <a href="#">下一页</a>
                </div>
            </form>
        </div>
        <!--主要区域结束-->











		
<%@ include file="/jsp/included/bottom.jsp"%>
	</body>
</html>
