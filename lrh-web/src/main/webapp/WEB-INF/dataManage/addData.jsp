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
<script type="text/javascript" src="js/prototype-1.6.0.3.js"></script>
<script type="text/javascript" src="js/jquery-1.4.3.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>增加商品</title>
		<link type="text/css" rel="stylesheet" media="all" href="styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="styles/global_color.css" /> 
        <script language="javascript" type="text/javascript">
            //保存结果的提示
            function sumbit() {
                document.getElementById('form1').submit();
            }
            
           function checkProductName(){
        	   var productName = $('#productName').val();
        	   if(productName ==""){
        		$("#nameMsg").text("产品名称不能为空.").addClass("error_msg");
           		return;
        	   }
        	   $.post(
        			   "lrhpublicPackage/checkProductName",
        			   {"product.productName":productName},
        			   function(data){
        				   var info = data;
        				   $("#nameMsg").text(info.checkMessage);
        				   if(info.alreadyExist){
        					   $("#nameMsg").addClass("error_msg");
        				   }else{
        					   $("#nameMsg").removeClass("error_msg");
        				   }
        			   }
        	   );
           }
            //获取productType 
            //$("#nameMsg").addClass("error_msg")
            //$("#nameMsg").removeClass("error_msg");
            $(function(){
            	$.ajax({
            		url:"lrhpublicPackage/jsonProductType.action",
            		type:"post",
            		dataType : "json",
            		success:function(data){
            			$('#productType').empty();
            			var json = eval(data);
                	   	for(var i=0;i<json.length;i++){
                	   		var s = json[i];
                	   		$('#productType').append(
                					'<option value="'+s.datatypeName+'">'
                					+s.datatypeName+'</option>'
                			);
                	   	}
            		},
            		error:function(data){
            			
            		}
            	});
            });
            
            
            $(function(){
            	$.ajax({
            		url:"lrhpublicPackage/jsonProductUnit.action",
            		type:"post",
            		dataType : "json",
            		success:function(data){
            			$('#productUnit').empty();
            			var json = eval(data);
            			//不需要转换 服务器传过来的就是json对象
                	   	for(var i=0;i<data.length;i++){
                	   		var s = data[i];
                	   		$('#productUnit').append(
                					'<option value="'+s.dataUnitName+'">'+s.dataUnitName+'</option>'
                			);
                	   	}
            		},
            		error:function(data){
            			
            		}
            	});
            });
            
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
            <form action="dataManage/data_addData" method="post" class="main_form" id="form1">
                <div class="text_info clearfix"><span>商品分类：</span></div>
                <div class="input_info">
                    <select id="productType" name="product.productType">
                    <option value="0">请选择</option>
                    </select>
                    <span class="required">*</span>
                    <div class="validate_msg_short"></div>
                </div>
                
                <div class="text_info clearfix"><span>商品名称：</span></div>
                <div class="input_info">
                    <input type="text" class="width300" value="" name="product.productName" 
                    id="productName" onblur="checkProductName();"/>
                    <span class="required">*</span>
                    <div class="validate_msg_short"  id="nameMsg"></div>
                </div>
                
                <div class="text_info clearfix"><span>计量单位：</span></div>
                <div class="input_info">
                    <select id="productUnit" name="product.productUnit">
                    <option value="0">请选择</option>
                    </select>
                    <span class="required">*</span>
                    <div class="validate_msg_short"></div>
                </div>
                
                 <div class="text_info clearfix"><span>规格：</span></div>
                <div class="input_info">
                    <input type="text" class="width300" value="" name="product.productFormat"/>
                    <span class="required"></span>
                    <div class="validate_msg_short"></div>
                </div>
                
                 <div class="text_info clearfix"><span>进货单价：</span></div>
                <div class="input_info">
                    <input type="text" class="width300" value="" name="product.productBuyPrice"/>
                    <span class="required">*</span>
                    <div class="validate_msg_short"></div>
                </div>
                
                 <div class="text_info clearfix"><span>出货单价：</span></div>
                <div class="input_info">
                    <input type="text" class="width300" value="" name="product.productSalePrice"/>
                    <span class="required"></span>
                    <div class="validate_msg_short"></div>
                </div>
                
                <div class="text_info clearfix"><span>备注：</span></div>
                <div class="input_info">
                    <textarea class="width300 height30" name="product.productDesc"></textarea>
                    <span class="required"></span>
                    <div class="validate_msg_short"></div>
                </div>
                
                <br/>
                <div class="button_info clearfix">
                    <input type="button" value="保存" class="btn_save"  onclick="sumbit();" />
                    <input type="button" value="取消" class="btn_save" onclick="window.history.go(-1);"/>
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
