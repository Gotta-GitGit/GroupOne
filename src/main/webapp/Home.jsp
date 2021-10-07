<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<sql:setDataSource var="ds" dataSource="jdbc/DB" />
<sql:query sql="select * from article" var="rs" dataSource="${ds}" /> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="//cdn.datatables.net/1.11.1/css/jquery.dataTables.min.css">
<link rel="stylesheet" href="./style26.css">
<title>Home</title>
</head>
<body>
<header>
 <h2>討論區</h2>
 </header>
 <form action=".\ArticleServlet" method="post">
 <button type="submit" name="new" >新增文章</button>
 <div>
  <table border=1 id="table_id" class="display">
  <thead>
  <tr>
  <th>id</th>
    <th>title</th>
    <th>category</th> 
    <th>content</th> 
    <th>restaurant</th> 
    <th>編輯</th>
    </tr>
    <tbody>
    <c:forEach var="row" items="${rs.rows}">
    
    <tr>
    <td>${row.id}</td>
    <td>${row.title}</td>
    <td>${row.category}</td>
    <td>${row.content}</td>
    <td>${row.restaurant}</td>
	
	<td><a href=".\ArticleServlet?action=edit&id=<c:out value='${row.id}' />">Edit</a>
	<a href=".\ArticleServlet?action=delete&id=<c:out value='${row.id}' />">Delete</a>
    </td>

    </tr>
    </c:forEach>
    </tbody>
  </table>
  
  </div>
    </form>
  <script src="./jquery-3.6.0.js"></script>
  <script src="//cdn.datatables.net/1.11.1/js/jquery.dataTables.min.js"></script>
    <script>
    $(function() {
        $(document).ready(function() {
            $('#table_id').DataTable();
        });
       
    })
    </script>
</body>
</html>