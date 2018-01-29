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
        <script type="text/javascript">

            //页面初始化的时候组织下拉框菜单
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
                            $('#itemName').append('<option value="'+payItem.itemName+'">'+payItem.itemName+'</option>');
                        }
                    },
                    'error':function(){
                        $('#itemName').empty();
                    }
                });
            });



            function checkItemNameExist(){
                var itemName = $('#itemName').val();
                if(itemName ==""){
                    $("#subitemNameErrMsg").text("数据不能为空").addClass("error_msg");
                    return;
                }
                $.post(
                    "ajax/checkPayitemExist",
                    {"itemName":itemName},
                    function(data){
                        var info = data;
                        $("#subitemNameErrMsg").text(info.checkPaySubitemExistMessage);
                        if(info.alreadyExist){
                            $("#subitemNameErrMsg").addClass("error_msg");
                            return;
                        }else{
                            $("#subitemNameErrMsg").removeClass("error_msg");
                        }
                    }
                );
            }
</script>
<title>增加商品类型</title>
<link type="text/css" rel="stylesheet" media="all" href="styles/global.css" />
<link type="text/css" rel="stylesheet" media="all" href="styles/global_color.css" />

<script language="javascript" type="text/javascript">
//保存结果的提示
    function sumbit() {
        /*document.getElementById('form1').submit();*/
        var itemName = $('#itemName').val();
        var subitemName = $('#subitemName').val();
        if(subitemName ==""){
            $("#message").text("项目名不能为空").addClass("error_msg");
            return;
        }else{
            $.post(
                "ajax/checkPaySubitemExist",
                {"itemName":itemName,"subitemName":subitemName},
                function(data){
                    var info = data;
                    $("#message").text(info.checkPaySubitemExistMessage);
                    if(info.alreadyExist){
                        $("#message").addClass("error_msg");
                        return;
                    }else{
                        //$("#message").removeClass("error_msg");
                        document.getElementById('form1').submit();
                    }
                }
            );

        }
    }

function quxiao(itemNameOld) {
    /*document.getElementById('form1').submit();*/
    /*$("#itemNameErrMsg").text("数据不能为空").addClass("error_msg");*/
    var url ='dataManage/toPaySubitemList?itemName='+itemNameOld;
    document.getElementById('form1').setAttribute('action',url);
    document.getElementById('form1').setAttribute('method','get');
    //document.getElementById('itemName').setAttribute('value',itemNameOld);
    $("#itemName").val(itemNameOld);

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
            <form action="dataManage/addPaySubitem" method="post" class="main_form" id="form1">
                <div class="text_info clearfix"><span></span></div>
                <div class="input_info">
                    <span class="">请选择大类:</span>
                    <select id="itemName" name="itemName">
                        <option value="0">请选择</option>
                    </select>
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
            <span>我希望一切都好</span>
            <br />
            <span>邮箱:the12thwolf@126.com  &nbsp;&nbsp;&nbsp;&nbsp; QQ:361091308 &nbsp;&nbsp;&nbsp;&nbsp;
		微信:361091308 &nbsp;&nbsp;&nbsp;&nbsp; 手机:13952023017 </span>
        </div>
    </body>
</html>
