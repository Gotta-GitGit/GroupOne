<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
response.setContentType("text/html;charset=UTF-8");
response.setHeader("Cache-Control", "no-cache"); // HTTP 1.1
response.setHeader("Pragma", "no-cache"); // HTTP 1.0
response.setDateHeader("Expires", -1); // Prevents caching at the proxy server
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./style26.css">
<title>預覽文章</title>
</head>
<style>
table{
width: 700px;
            border: 1px solid black;
            margin: auto;
}
tr td{
border:1px solid black;
}
</style>
<body>
	<jsp:useBean id="new_article" class="mvcdemo.bean.ArticleBean"
		scope="session" />
		<header>
	<h2 style="text-align:center">新增文章</h2>
	<form action=".\ArticleServlet" method="post">
	</header>
		<table>
			<tr>
				<td style="width:100px">標題:</td>
				<td><jsp:getProperty name="new_article" property="title" /></td>
			</tr>
			<tr>
				<td>分類:</td>
				<td><jsp:getProperty name="new_article" property="category" /></td>
			</tr>
			<tr>
				<td>文章內容:</td>
				<td><jsp:getProperty name="new_article" property="content" /></td>
			</tr>
			<tr>
				<td>餐聽:</td>
				<td><jsp:getProperty name="new_article" property="restaurant" /></td>
			</tr>
		</table>
		<center>
		<input type ="button" onclick="history.back()" value="繼續編輯"></input>
		<button type="submit" name="confirm" >確定新增</button>
		</center>
		
</body>
</html>