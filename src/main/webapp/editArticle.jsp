 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<sql:setDataSource var="ds" dataSource="jdbc/DB" />
<sql:query sql="select * from article where id='${articleBean.getId()}'" var="rs" dataSource="${ds}" /> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% 
response.setContentType("text/html;charset=UTF-8");
response.setHeader("Cache-Control","no-cache"); // HTTP 1.1
response.setHeader("Pragma","no-cache"); // HTTP 1.0
response.setDateHeader ("Expires", -1); // Prevents caching at the proxy server
%>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./style26.css">
    <title>修改文章</title>
    
    <style>
        table {
            width: 700px;
            border: 1px solid black;
            margin: auto;
        }

        #content {
            width: 500px;
        }
    </style>
</head>

<body>
	<header>
    <h2 style="text-align: center;">修改文章</h2>
    </header>
    <form action=".\ArticleServlet" method="post">
    <table>
    <c:forEach var="row" items="${rs.rows}">
    	  <input type="hidden" name="id" value="${row.id}">
        <tr>
            <td>標題:</td>
            <td><input required type="text" name="title" size="35" maxlength="20" value="<c:out value="${row.title}"/>"></td>
        </tr>
        
        <tr>
            <td>分類:</td>
            <td>
                <input type="radio" name="category" id="category1" value="中式" checked>中式
                <input type="radio" name="category" id="category2" value="義式">義式
                <input type="radio" name="category" id="category3" value="火鍋">火鍋
                <input type="radio" name="category" id="category4" value="牛排">牛排
                <input type="radio" name="category" id="category6" value="速食">速食
                <input type="radio" name="category" id="category5" value="飲料冰品">飲料冰品
            </td>
        </tr>
        <tr>
            <td>文章內容:</td>
            <td>
                <textarea required name="content" id="content" cols="30" rows="10" ><c:out value="${row.content}"/></textarea>
            </td>
        </tr>    
        <tr>
            <td>餐廳:</td>
            <td><input type="text" name="restaurant" size="35" maxlength="20" value="<c:out value="${row.restaurant}"/>"></td>
        </tr>
        </c:forEach>
    </table>
    <center>
        <input type ="button" onclick="history.back()" value="取消"></input>
        <button type="submit" name="update" >確定修改</button></center>
    </form>
</body>

</html>