<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<c:url var="base" value="/"></c:url>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>mail_info</title>
</head>
<body>
<h1>发送邮件</h1>
<form action="singlemailsend" method="post">
    <label>发件人邮箱: </label>
    <select name="fajianren">
        <option value ="the12thwolf@126.com">the12thwolf@126.com</option>
        <option value ="361091308@qq.com">361091308@qq.com</option>
    </select><br>
    收件人:<textarea rows="1" name="shoujianren"></textarea><font color="red"> (多个收件人请用英文逗号","分隔)</font><br>
    抄送:<textarea rows="1" name="chaosong"></textarea><font color="red"> (多个收件人请用英文逗号","分隔)</font><br>
    邮件主题:<textarea rows="1" name="mailtitle"></textarea><br>
    邮件正文内容:<textarea  rows="15" cols="90" name="mailtext"></textarea><br>
    </div>

    <input type="submit" value="发送" />
</form>
<h2><a href="list">返回主页</a></h2>
<div>
    <h6>
        获取服务器传过来的值:
        <br>
        获取服务器传过来的值:
    </h6>
</div>

</body>
</html>