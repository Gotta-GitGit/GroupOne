<%@page import="mvc.bean.ActivityBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
response.setContentType("text/html;charset=UTF-8");
response.setHeader("Cache-Control","no-cache"); // HTTP 1.1
response.setHeader("Pragma","no-cache"); // HTTP 1.0
response.setDateHeader ("Expires", -1); // Prevents caching at the proxy server
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
   	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
   	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>

<jsp:useBean id="reg_activity" class="mvc.bean.ActivityBean" scope="session" />

<body>
<h2>
報名資料如下請確認
</h2>
 
<form action=".\RegisterServlet" method="post">

<table  cellspacing="2" cellpadding="1" border="1" width="100%">
<tr bgcolor="#FFFFE1">
    <td>帳號:</td>
    <td><jsp:getProperty name="reg_activity" property="username" /></td>
</tr>
<tr bgcolor="#F2F4FB">
    <td>Email:</td>
    <td>
    <jsp:getProperty name="reg_activity" property="s_email" />     
    </td>
</tr>
<tr bgcolor="#FFFFE1">
    <td>活動主題:</td>
    <td><jsp:getProperty name="reg_activity" property="topic" /></td>
</tr>

<tr bgcolor="#F2F4FB">
    <td>類型:</td>
    <td>
     <%
    
     ActivityBean activityBean=(ActivityBean)request.getAttribute("activityBean");
     if(activityBean==null){
    	activityBean=new ActivityBean();
     	request.setAttribute("activityBean", activityBean);
     }
     activityBean.setStyle(request.getParameterValues("style"));
    for (int i=0;i<activityBean.getStyle().length;i++) 
        out.println(i+1+"."+activityBean.getStyle()[i]+"<p>");
    %>
    </td>
</tr>

<tr bgcolor="#FFFFE1">
    <td>活動地點:</td>
    <td><jsp:getProperty name="reg_activity" property="address" /></td>
</tr>

<tr bgcolor="#F2F4FB">
    <td>參與費用:</td>
    <td>
    <jsp:getProperty name="reg_activity" property="cost" />     
    </td>
</tr>

<tr bgcolor="#FFFFE1">
    <td>相關連結:</td>
    <td><jsp:getProperty name="reg_activity" property="link" /></td>
</tr>

<tr bgcolor="#F2F4FB">
    <td>活動介紹:</td>
    <td>
    <jsp:getProperty name="reg_activity" property="introduce" />     
    </td>
</tr>

<tr bgcolor="#FFFFE1">
    <td>活動名額:</td>
    <td><jsp:getProperty name="reg_activity" property="quota" /></td>
</tr>

<tr bgcolor="#F2F4FB">
    <td>活動行程:</td>
    <td>
    <jsp:getProperty name="reg_activity" property="schedule" />     
    </td>
</tr>

<tr bgcolor="#FFFFE1">
    <td>注意事項:</td>
    <td><jsp:getProperty name="reg_activity" property="notice" /></td>
</tr>


<tr bgcolor="#F2F4FB">
    <td>點數:</td>
    <td>
    <jsp:getProperty name="reg_activity" property="bonus_point" />     
    </td>
</tr>
</table>
<center>
<input type="submit" name="confirm" value="確認" >
<input type="button" value="返回主頁面" onclick="location.href='ActivityRegister.jsp'">
</center>

</form>

</body>
</html>