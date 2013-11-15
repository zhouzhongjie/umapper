<%@ page language="java" import="java.util.*,java.net.URL" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Hello World</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
    <form action="../FileManager?action=upload" method="post"
        enctype="multipart/form-data">
        <input type="file" name="file1" id="file1" />
        <input type="file" name="file2" id="file2" />
        <input type="submit" value="上传" />        
    </form>
    
    <br><br>
    <form action="../FileManager?action=delete" method="post" >
    	URL:<input type="text"  name="url"/>
    	<input type="submit" value="删除" />
    </form>
  </body>
  </body>
</html>
