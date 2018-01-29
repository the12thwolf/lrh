<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="/struts-tags" prefix="s" %>
    <head>
		<base href="<%=basePath%>" />
		<script type="text/javascript" src="js/regist.js"></script>
		<script type="text/javascript" src="js/prototype-1.6.0.3.js"></script>
		<script type="text/javascript" src="js/jquery-1.4.3.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>食堂采购明细</title>
        <link type="text/css" rel="stylesheet" media="all" href="styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="styles/global_color.css" />
<script language="javascript" type="text/javascript">
            //排序按钮的点击事件
	function sort(btnObj) {
		if (btnObj.className == "sort_desc")
			btnObj.className = "sort_asc";
		else
			btnObj.className = "sort_desc";
	}

            //启用
            function startFee() {
                var r = window.confirm("确定要启用此资费吗？资费启用后将不能修改和删除。");
                document.getElementById("operate_result_info").style.display = "block";
            }
            //删除
            function deleteFee() {
                var r = window.confirm("确定要删除此资费吗？");
                document.getElementById("operate_result_info").style.display = "block";
            }


/**
 * 页面上获取商品类别的函数
 */
            $(function(){
        		$.ajax({
                    url: "utilPackage/dataTypeList.action",
                    // 数据发送方式
                    type: "post",
                    // 接受数据格式
                    dataType : "json",
                    // 回调函数，接受服务器端返回给客户端的值，即result值
                    /*注意这里的data 是action 传递过来的 就是  
                     *<param name="root">result</param> result 对应的那个值
        				这里不一定要result 可以用任何字符串代替 注意返回的是一个字符串 不是
        				json对象 千万注意 需要进行转换 
                    */
                    success:function(data){
                    	//服务器传过来的收一个json字符串 不是json对象  需要转换
                    	 $('#dataType').empty();
                    	//将json字符串转换成 json对象
                    	    var json = eval(data);
                    	    $('#dataType').append(
                					'<option value="全部">全部</option>'
                			);
                    	   	for(var i=0;i<json.length;i++){
                    	   		var s = json[i];
                    	   		$('#dataType').append(
                    					'<option value="'+s.datatypeName+'">'+s.datatypeName+'</option>'
                    			);
                    	   	}
                    },
                    'error':function(){
                    	$('#dataType').empty();
            		}
                });
        });
            
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
<!-- 主页-->	    <li><a href="userManage/pageChange_backIndex" class="index_off"></a></li>
<!-- 采购管理 --><li><a href="payDetail/toPayDetailList" class="role_off"></a></li>
				<li><a href="../admin/admin_list.html" class="saleManage_off"></a></li>
                <li><a href="stock/stock_stockList" class="account_off"></a></li>
<!-- 数据维护 --><li><a href="dataManage/data_dataList" class="admin_off"></a></li>
                <li><a href="fee/fee_list.action" class="fee_off"></a></li>
                <li><a href="../report/report_list.html" class="report_off"></a></li>
<!-- 修改密码 --><li><a href="lrhpassword/password_toChangePwdPage" class="password_on"></a></li>
            </ul>      
        </div>
        <!--导航区域结束-->
       <div id="report_main">
        	<div class="tabs">
    	        <ul onclick="changeTab(event,this);">
        	        <li><a href="purchasingManage/purchasing_refectoryPurchasingList" 
        	        class="tab_out" title="采购数据汇总">食堂采购数据汇总</a></li>
                    <li><a href="purchasingManage/purchasing_purchasingDetails" class="tab_on" 
                    title="采购明细查询">食堂采购明细查询</a></li>
                   <li><a href="purchasingManage/purchasingStore_purchasingStoreList" 
        	        class="tab_out" title="采购数据汇总">小店采购数据汇总</a></li>
                    <li><a href="purchasingManage/purchasingStore_purchasingStoreDetails" class="tab_out" 
                    title="采购明细查询">小店采购明细查询</a></li>
                </ul>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
               
                <s:property value="message"/>
            </div>
            
             <div class="search_add">
                    <div>
                       	 商品类别：
                        <select name="" id="dataType" class="select_search">
                            <option value="0">全部</option>
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
                    <div><input type="button" value="搜索" class="btn_search"/></div>
                    
                    <div>订单编号：<input type="text" value="" class="text_search width200" /></div>
                    <div><input type="button" value="搜索" class="btn_search"/></div>
                    
                    <input type="button" value="增加记录" class="btn_add" 
                    onclick="location.href='lrhtransition/transition_toDataAdd';" />
                
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
                        	<th>采购编号</th>
                        	<th>订单编号</th>
                            <th>商品ID</th>
                            <th>商品类别</th>
                            <th class="width100">商品名称</th>
                            <th>单位</th>
                            <th>数量</th>
                            <th>单价</th>
                            <th>金额</th>
                            <th>入库时间</th>
                            <th>操作</th>
                        </tr>                      
                        <tr>
                        	<td>1</td>
                            <td>A001</td>
                            <td>1</td>
                            <td><a href="fee_detail.html">食物</a></td>
                            <td>肋排</td>
                            <td>斤</td>
                            <td>5</td>
                            <td>15</td>
                            <td>75</td>
                            <td>2014-03-03</td>
                            <td>
                                <input type="button" value="修改" class="btn_modify" onclick="location.href='fee_modi.html';" />
                                <input type="button" value="删除" class="btn_delete" onclick="deleteFee();" />
                            </td>
                        </tr>
                        <tr>
                       		 <td>6</td>
                        	<td>B2020ty</td>
                           <td>2</td>
                            <td><a href="fee_detail.html">调料</a></td>
                            <td>盐</td>
                            <td>袋</td>
                            <td>100</td>
                            <td>1.2</td>
                            <td>120</td>
                            <td>2014-03-03</td>
                            <td>
                                <input type="button" value="修改" class="btn_modify" onclick="location.href='fee_modi.html';" />
                                <input type="button" value="删除" class="btn_delete" onclick="deleteFee();" />                                
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
