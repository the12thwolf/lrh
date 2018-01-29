<%@ page pageEncoding="UTF-8"  import="java.util.*,java.text.*" %>

<%
Date date = new Date();
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
String today = sdf.format(date);
%>
<div id="header">
	<div id="rightheader">
		<p>
			<font size="4"><%=today %>&nbsp;&nbsp;&nbsp;您好:&nbsp;&nbsp;${user.userName }</font>
		<br />
		</p>
	</div>
<div id="topheader">
	<h1 id="title">
		<a href="userMain.jsp">main</a></h1>
</div>
<div id="navigation"></div>
</div>
