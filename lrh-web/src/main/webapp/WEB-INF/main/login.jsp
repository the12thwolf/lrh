<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!-- 使用 c 标签 -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<base href="<%=basePath%>" />
		<title>用户登陆</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css"
			href="styles/style.css" />
	</head>

	<body>
		<div id="wrap">
			<div id="top_content">
<%@ include file="header.jsp" %>
				<div id="content">
					<p id="whereami">
					</p>
					<h1>
						login
					</h1>
					<form action="user_login" method="post">
						<table cellpadding="0" cellspacing="0" border="1"
							class="form_table">
							<tr>
								<td valign="middle" align="right">
									username:
								</td>
								<td valign="middle" align="left">
									<input type="text" class="inputgri" name="userName" />
								</td>
								
								<%
								String errorMessage = 
								(String)session.getAttribute("errorMessage");
								if(errorMessage!=null){
								%>
								<td width="290px" rowspan="2">
								<font color="#f33" size="3"><%=errorMessage %></font>
								</td>
								<%}else{%>
								
								<td width="200px" rowspan="2">
								
								</td>
								<%}%>
								<td width="200px"></td>
							</tr>
							<tr>
								<td valign="middle" align="right">
									password:
								</td>
								<td valign="middle" align="left">
									<input type="password" class="inputgri" name="password" />
								</td>
								<td width="200px"></td>
							</tr>
							
						</table>
						<p>
							<input type="submit" class="button" value="登 陆 &raquo;" />
							&nbsp;&nbsp;&nbsp;<a href="user_toRegist">注册新用户</a>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<!-- 错误提示信息 -->
${message }
						</p>
					</form>
				</div>
			</div>
<%@ include file="bottom.jsp"%>
		</div>
	</body>
</html>
