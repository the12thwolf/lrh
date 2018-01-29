<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
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
            
          //删除
            function deleteFee(id) {
                var r = window.confirm("确定要删除此商品吗？");
                if(r){
                	window.location.href="dataManage/data_dataDelete?product.productID="+id;//相当于手动访问地址
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
                <li><a href="user_backIndex" class="index_off"></a></li>
                <!-- 支出查询 --><li><a href="purchasingManage/purchasing_refectoryPurchasingList" class="role_off"></a></li>
                <!-- 支出管理 --><li><a href="saleManage/sale_saleList" class="saleManage_off"></a></li>
                <!-- 数据维护 --><li><a href="dataManage/data_dataList" class="admin_on"></a></li>
                <!-- 库存维护 --><li><a href="lrhstockManage/stock_stockList" class="account_off"></a></li>
                <li><a href="fee/fee_list.action" class="fee_off"></a></li>
                <li><a href="../report/report_list.html" class="report_off"></a></li>
                <!-- 修改密码 --><li><a href="lrhpassword/password_toChangePwdPage" class="password_off"></a></li>
            </ul>         
        </div>
        <!--导航区域结束-->
        <!--主要区域开始-->        
        <div id="report_main">
        	<div class="tabs">
    	        <ul onclick="changeTab(event,this);">
        	        <li><a href="dataManage/data_dataList" class="tab_on" title="商品维护">商品维护</a></li>
                    <li><a href="dataManage/dataType_dataTypeList" class="tab_out" title="商品类型维护">商品类型维护</a></li>
                    <li><a href="dataManage/dataUnit_dataUnitList" class="tab_out" title="维护计量单位">计量单位维护</a></li>
                </ul>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                
                <s:property value="message"/>
            </div>
            
             <div class="search_add">
                    <div>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                       	 商品类别：
                        <select name="" id="selModules" class="select_search">
                            <option>全部</option>
                            <option>角色管理</option>
                            <option>管理员管理</option>
                            <option>资费管理</option>
                            <option>账务账号</option>
                            <option>业务账号</option>
                            <option>账单管理</option>
                            <option>报表</option>
                        </select>
                    </div>
                    <div>
                    	商品名：
                        <select name="" id="selModules" class="select_search">
                            <option>全部</option>
                            <option>角色管理</option>
                            <option>管理员管理</option>
                            <option>资费管理</option>
                            <option>账务账号</option>
                            <option>业务账号</option>
                            <option>账单管理</option>
                            <option>报表</option>
                        </select>
                    </div>
                    <div>
                    
                    <input type="button" value="搜索" class="btn_search"/></div>
                    <input type="button" value="增加记录" class="btn_add" 
                onclick="location.href='lrhtransition/transition_toDataAdd';" />
                
                </div>
                  
            <div class="report_box">
                <!--数据区域：用表格展示数据-->
                <div id="report_data">
                    <table id="datalist">
                        <tr>                            
                            <th>商品编号</th>
                            <th>商品分类</th>
                            <th>商品名称</th>
                            <th>单位</th>
                            <th>规格</th>
                            <th>进货单价</th>
                            <th>出货单价</th>
                            <th>备注</th>
                            <th>操作</th>
                        </tr>  
<s:iterator value="productList">               
                        <tr>
                            <td><s:property value="productID" /></td>
                            <td><s:property value="productType" /></td>
                            <td><s:property value="productName" /></td>
                            <td><s:property value="productUnit" /></td>
                            <td><s:property value="productFormat" /></td>
                            <td><s:property value="productBuyPrice" />元</td>
                            <td><s:property value="productSalePrice" />元</td>
                            <td><s:property value="productDesc" /></td>
                            <td>
                            <input type="button" value="修改" class="btn_modify" 
                            onclick="location.href='dataManage/data_dataModifyTrasition?product.productID=<s:property value="productID" />';" />
                            <input type="button" value="删除" class="btn_delete" 
                            onclick="deleteFee(<s:property value="productID" />);" />
                            </td>
                        </tr>
</s:iterator> 
                    </table>
                    
                </div>
                <!--分页-->
                <div id="pages">
<s:if test="page>1">
	<a href="dataManage/data_dataList?page=<s:property value="page-1" />">上一页</a>
</s:if>
<s:else>
上一页
</s:else>
&nbsp;&nbsp;&nbsp;&nbsp;
<s:if test="page<totalPages">
	<a href="dataManage/data_dataList?page=<s:property value="page+1" />">下一页</a>
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
