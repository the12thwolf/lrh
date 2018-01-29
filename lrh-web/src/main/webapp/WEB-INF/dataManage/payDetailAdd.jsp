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

        <script type="text/javascript">

            //页面初始化的时候组织下拉框菜单
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
                        if(json.length>0){
                            itemNameInit=json[0].itemName;
                        }else{
                            itemNameInit="121";
                        }
                        for(var i=0;i<json.length;i++){
                            var payItem = json[i];
                            $('#itemName').append('<option value="'+payItem.itemName+'">'+payItem.itemName+'</option>');
                        }
                    },
                    'error':function(){
                        $('#itemName').empty();
                    }
                });

                //var itemName=document.getElementById("itemName").value;
                getSubPayitemsByItemNameInit(itemNameInit);

            });


            function getSubPayitemsByItemNameInit(itemNameInit){
                //二级联动菜单
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

                //var itemName=document.getElementById("itemName").value;
                getSubPayitemsByItemNameInit(itemNameInit);

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
<title>新增支出数据</title>
<link type="text/css" rel="stylesheet" media="all" href="styles/global.css" />
<link type="text/css" rel="stylesheet" media="all" href="styles/global_color.css" />

<script language="javascript" type="text/javascript">
//保存结果的提示
function isMoney(payAmount) {
    //判断是否是有金额
    //var regu="^[0-9]+[\.][0-9]{0,3}$";
    var regu=/^[0-9]*(\.[0-9]{1,2})?$/;
    var re=new RegExp(regu);
    if(re.test(payAmount)){
        return true;
    }else{
        return false;
    }

}
function sumbit(itemNameOld,subitemNameOld,payPersonOld) {
        var itemName = $('#itemName').val();
        var subitemName = $('#subitemName').val();
        var payPerson = $('#payPerson').val();
        var payAmount=  $('#payAmount').val();
        /*var checks = $("input[name='payPerson']:checked");*/
        var payDate=$('#payDate').val();
        //复选框的值
        if(subitemName ==""){
            $("#message").text("项目名不能为空").addClass("error_msg");
            return;
        }else if(payAmount==""){
            $("#payAmountMessageInfo").text("金额不能为空").addClass("error_msg");
            return;
        }else if(!isMoney(payAmount)){
            $("#payAmountMessageInfo").text("请输入有效金额").addClass("error_msg");
            return;
        }
        else if(payPerson == ""){
            $("#payPersonMessageInfo").text("至少选择一个支出人").addClass("error_msg");
            return;
        }

        /*else if(checks.length == 0){
            $("#payPersonMessageInfo").text("至少选择一个支出人").addClass("error_msg");
            return;
        }*/
        else if(payDate == ""){
            $("#payDateMessageInfo").text("请选择支出日期").addClass("error_msg");
            return;
        }
        else{
            //把几个涉及到汉字的字段编码
            /*itemNameOld=encodeURIComponent(encodeURIComponent(itemNameOld));
            subitemNameOld=encodeURIComponent(encodeURIComponent(subitemNameOld));
            payPersonOld=encodeURIComponent(encodeURIComponent(payPersonOld));
            document.getElementById('itemNameOld').setAttribute('value',itemNameOld);
            document.getElementById('subitemNameOld').setAttribute('value',subitemNameOld);
            document.getElementById('payPersonOld').setAttribute('value',payPersonOld);*/
            document.getElementById('form1').submit();
        }
    }

function quxiao() {
    /*document.getElementById('form1').submit();*/
    /*$("#itemNameErrMsg").text("数据不能为空").addClass("error_msg");*/
    var url ='payDetail/toPayDetailList';
    document.getElementById('form1').setAttribute('action',url);
    //document.getElementById('form1').setAttribute('method','get');
    //document.getElementById('itemName').setAttribute('value',itemNameOld);
    document.getElementById('form1').submit();
}

function selectAll() {
    if ($("#selectAll").attr("checked")) {
        $("input[name='payPerson']").attr("checked", true);
    } else {
        $("input[name='payPerson']").attr("checked", false);
    }
}

function choseAll() {
    $("input[name='payPerson']").attr("checked", true);
}
function choseNone() {
    $("input[name='payPerson']").attr("checked", false);
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



</script>
    </head>
    <body>
        <!--导航区域开始-->
        <div id="navi">
            <!-- 主页-->	    <li><a href="user_backIndex" class="index_off"></a></li>
            <!-- 支出查询 --><li><a href="payDetail/toPayDetailList" class="role_off"></a></li>
            <!-- 数据维护 --><li><a href="dataManage/payItemList" class="admin_off"></a></li>
            <!-- 修改密码 --><li><a href="lrhpassword/password_toChangePwdPage" class="password_off"></a></li>
        </div>
        <!--导航区域结束-->
        <!--主要区域开始-->
        <div id="main">
            <form action="payDetail/addPayDetail" method="post" class="main_form" id="form1">
                <table>
                    <tr>
                        <td>
                <span >项目名称&nbsp;&nbsp;</span>
                <select id="itemName" name="itemName" onchange="getSubPayitemsByItemName();">

                </select>&nbsp;&nbsp;
                <span >用途&nbsp;&nbsp;</span>
                <select id="subitemName" name="subitemName">
                    <option value="0">请选择</option>
                </select>
                <span class="required">*</span>&nbsp;&nbsp;&nbsp;&nbsp;
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <div class="input_info">
                            </div>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <div class="input_info">
                                <span >金额(元):&nbsp;&nbsp;&nbsp;</span>
                                <input type="text" class="width150" name="payAmount" id="payAmount"  />
                                <span class="required">*</span>
                                <span class="required" id="payAmountMessage">${message}</span>
                                <div class="validate_msg_short"  id="payAmountMessageInfo"></div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                    <span >支出主体:&nbsp;&nbsp;</span>
                    <select id="payPerson" name="payPerson">
                        <option value="0">请选择</option>
                    </select>
                    <span class="required">*</span>&nbsp;&nbsp;&nbsp;&nbsp;
                        </td>
                    </tr>
                    <%--<tr>
                        <td>
                <div class="input_info">
                    <span >支出人员:&nbsp;&nbsp;&nbsp;</span>
                    <input type="text" class="width300" name="payPerson" id="payPerson" readonly/>
                    <span class="required">*</span>
                    <span class="required" id="payPersonMessage">${payPersonMessage}</span>
                    <div class="validate_msg_short"  id="payPersonMessageInfo"></div>
                </div>
                        </td>
                    </tr>--%>

                    <%--<tr>
                        <td>
                            <div >
                                <span >支出人员:&nbsp;&nbsp;&nbsp;</span>
                                <input type="checkbox" name="payPerson" value="all" >all&nbsp;
                                <input type="checkbox" name="payPerson" value="p1" >p1&nbsp;
                                <input type="checkbox" name="payPerson" value="p2" >p2&nbsp;
                                <input type="checkbox" name="payPerson" value="p3" >p3&nbsp;
                                <input type="checkbox" name="payPerson" value="p4" >p4&nbsp;
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <input type="button" value="全选" onclick="choseAll();" />&nbsp;&nbsp;
                                <input type="button" value="全不选" onclick="choseNone();" />
                                <span class="required">*</span>
                                <div class="validate_msg_short"  id="payPersonMessageInfo"></div>&nbsp;&nbsp;&nbsp;&nbsp;
                            </div>
                        </td>
                    </tr>--%>

                    <tr>
                        <td>
                            支出日期:
                            <input readonly class="layinput" name="payDate" id="payDate">
                            <div class="laydate-icon " onclick="laydate({elem: '#payDate'});" style="width:10px;display:inline-block;border:none;"></div>
                            <span class="required">*</span>
                            <span class="required" id="payDateMessage">${message}</span>
                            <div class="validate_msg_short"  id="payDateMessageInfo"></div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div class="input_info">
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                <div class="input_info">
                    <span >备注:&nbsp;&nbsp;&nbsp;</span>
                    <textarea name="contant" id="contant" cols="90" rows="5"></textarea>
                    <%--<input type="texta" class="width300" name="contant" id="contant"  />--%>
                </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                <div class="input_info">
                    <input type="hidden" name="oldFlag" id="oldFlag" value="1" />
                    <%--oldFlag 0-查询跳转的页面 1-其他操作跳转的页面--%>
                    <input type="hidden" name="crruntPage" id="crruntPage" value="${crruntPage}" />
                    <input type="hidden" name="itemNameOld" id="itemNameOld" value="${itemNameOld}" />
                    <input type="hidden" name="subitemNameOld" id="subitemNameOld" value="${subitemNameOld}" />
                    <input type="hidden" name="payPersonOld" id="payPersonOld" value="${payPersonOld}" />
                    <input type="hidden" name="payDateStartOld" id="payDateStartOld" value="${payDateStartOld}" />
                    <input type="hidden" name="payDateEndOld" id="payDateEndOld" value="${payDateEndOld}" />
                </div>
                <div class="button_info clearfix">&nbsp;&nbsp;&nbsp;<br><br></div>
                <div class="button_info clearfix">
                    <input type="button" value="保存" class="btn_save"  onclick="sumbit('${itemNameOld}','${subitemNameOld}','${payPersonOld}');" />
                    <input type="button" value="取消" class="btn_save" onclick="quxiao();"/>
                </div>
                        </td>
                    </tr>
                    <tr>
                </table>
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
