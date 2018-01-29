<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<style>
		.s1{
			color:red;
		}
	</style>
		
<base href="<%=basePath%>" />
<script type="text/javascript" src="js/regist.js"></script>
<script type="text/javascript" src="js/prototype-1.6.0.3.js"></script>
<script type="text/javascript" src="js/jquery-1.4.3.js"></script>
<script type="text/javascript">

function checkUserNameExist(){
	   var userName = $('#userName').val();
	   if(userName ==""){
		$("#userNameErrMsg").text("用户名不能为空").addClass("error_msg");
		return;
	   }
	   $.post(
			   "ajax/checkUserNameExist",
			   {"userName":userName},
			   function(data){
				   var info = data;
				   $("#userNameErrMsg").text(info.checkUserNameExistMessage);
				   if(info.alreadyExist){
					   $("#userNameErrMsg").addClass("error_msg");
				   }else{
					   $("#userNameErrMsg").removeClass("error_msg");
				   }
			   }
	   );
	}
	
/*function checkProductName(){
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
}*/

/*$(function(){
		$.ajax({
            url: "utilPackage/companylist.action",
            // 数据发送方式
            type: "post",
            // 接受数据格式
            dataType : "json",
            // 回调函数，接受服务器端返回给客户端的值，即result值
            /!*注意这里的data 是action 传递过来的 就是
             *<param name="root">result</param> result 对应的那个值
				这里不一定要result 可以用任何字符串代替 注意返回的是一个字符串 不是
				json对象 千万注意 需要进行转换 
            *!/
            success:function(data){
            	//服务器传过来的收一个json字符串 不是json对象  需要转换
            	 $('#companyBelong').empty();
            	//将json字符串转换成 json对象
            	    var json = eval(data);
            	   	for(var i=0;i<json.length;i++){
            	   		var s = json[i];
            	   		$('#companyBelong').append(
            					'<option value="'+s.companyID+'">'+s.companyName+'</option>'
            			);
            	   	}
            },
            'error':function(){
            	$('#companyBelong').empty();
    		}
        });
});*/

</script>
		<title>用户注册</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="styles/style.css" />
	</head>
	<body>
		<div id="wrap">
			<div id="top_content">
<%@ include file="header.jsp" %>
				<div id="content">
					<p id="whereami">
					</p>
					<h1>
						welcome !
					</h1>
					<form action="user_regist" method="post">
						<table cellpadding="1" cellspacing="1" border="1"
							class="form_table">
							<%--<tr>
								<td valign="middle" align="right">
									所属公司：
								</td>
								<td valign="middle" align="left">
								<select id="companyBelong" name="user.companyID">
								<option value="0">请选择</option>
								</select>
								</td>
								<td width="290px">
								</td>
							</tr>--%>
							<tr>
								<td valign="middle" align="right">
									用户名：
								</td>
								<td valign="middle" align="left">
									<input type="text" class="inputgri" name="userName"
									id="userName" onblur="checkUserNameExist();"/>
								</td>
								<td width="290px">
								<div class="validate_msg_short"  id="userNameErrMsg"></div>
								<span id="userName_message" class="s1"></span>
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">
									真实姓名：
								</td>
								<td valign="middle" align="left">
									<input type="text" class="inputgri" name="realName"
									id="realName"/>
								</td>
								<td width="290px">
								<span id="realName_message" class="s1"></span>
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">
									密码：
								</td>
								<td valign="middle" align="left">
									<input type="password" class="inputgri" name="password"
									id="password"/>
								</td>
								<td width="290px">
								<span id="password_message" class="s1"></span>
								</td>
							</tr>
							
<!--  
							<tr>
								<td valign="middle" align="right">
									验证码：
									<img id="num" src="captcha" onclick="this.src='captcha?'+Math.random();" />
<a href="javascript:;" 
onclick="document.getElementById('num').src = 'captcha?'+(new Date()).getTime()">看不清？换一张</a>
								</td>
								<td valign="middle" align="left">
									<input type="text" class="inputgri" name="captchaFromClient" 
									id="captchaFromClient" onblur="check_captcha();" />
								</td>
								<td>
								${regditCapatchaError }
<span class="s1" id="captcha_message"></span>
								</td>
							</tr>
-->
						</table>
						<p>
							<input type="submit" class="button" value="注 册 &raquo;" />
							&nbsp;&nbsp;&nbsp;
${message }&nbsp;&nbsp;&nbsp;<font size="4" color="#0000ff"><a href="login/">已经注册的用户请登录</a></font>
						</p>
					</form>
				</div>
			</div>
<%@ include file="bottom.jsp"%>
		</div>
	</body>
</html>
