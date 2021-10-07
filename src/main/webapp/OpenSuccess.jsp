<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
response.setContentType("text/html;charset=UTF-8");
response.setHeader("Cache-Control","no-cache"); // HTTP 1.1
response.setHeader("Pragma","no-cache"); // HTTP 1.0
response.setDateHeader ("Expires", -1); // Prevents caching at the proxy server
%>
		<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
		<html>

		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
			<title>Open Success!</title>
			<link rel="stylesheet" href="./yu.css">
		</head>

		<body>
			<header>
				<h1>餐廳建立</h1>
			</header>
			<h1 style="text-align: center; margin: 30px;">恭喜您，您的餐廳已建立完成！</h1>
			<a href="MenuEdit.jsp" title="點下去創建新菜單"><img src="Images/restaurant.png" style="width: 550px; height: 550px; display: block; margin: auto;"></a>
			
			<div style="text-align: center;">
				<button onclick="self.location.href='MenuEdit.jsp'"
					　style="width:200px;height:30px; font-size: medium;">直接創建菜單去！</button>
			</div>

		</body>

		</html>