<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>聚點時刻</title>
<!-- <link rel="stylesheet" href="link.css"> -->
<link rel="stylesheet" href="//cdn.datatables.net/1.11.1/css/jquery.dataTables.min.css">
<style>
	header {
	   	background: #333;
    	color: lightgreen;
	    padding: 20px;
		text-align: center;
    	margin-bottom: 10px;
	}
	#container {
    	width: 666px;
	    margin: auto;
    	border: 3px solid black;
	}
</style>
</head>

<body>
	<jsp:useBean id="all_lists" class="shopping.AllCartsBean"
		scope="session" />
	<header>
		<h1>細項查詢</h1>
	</header>
	<div id="container">
		<form action="" method="POST">
			<input type="hidden" id="itemNo" name="itemNo" value="">
		</form>
		<table id="findAll" class="display">
			<thead>
				<tr>
					<th>產品名稱</th>
					<th>單價</th>
					<th>數量</th>
                    <th>合計</th>
				</tr>
			</thead>
			<tbody>	
			 <c:choose>
                <c:when test="${fn:length(all_lists.getvCart()) != 0}">
					<c:forEach var="i" begin="0" end="${fn:length(all_lists.getvCart())/3-1}" step="1">
					<tr>
						<td>${all_lists.getvCart()[0+i*3]}</td>
						<td>${all_lists.getvCart()[1+i*3]}</td>
						<td>${all_lists.getvCart()[2+i*3]}</td>
                        <td>${all_lists.getvCart()[1+i*3]*all_lists.getvCart()[2+i*3]}</td>
					</tr>
					</c:forEach>               		
            	</c:when>           
            </c:choose>
			</tbody>
		</table>
	</div>
	<script src="jquery-3.6.0.js"></script>
	<script src="//cdn.datatables.net/1.11.1/js/jquery.dataTables.min.js"></script>
	<script>
		$(function() {
			$('#findAll').DataTable({
				"scrollY":        "300px",
     			"scrollCollapse": true,
    		    "paging":         false,
				"searching": false
			});
		})
	</script>
</body>

</html>