<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>餐點清單管理</title>
<link rel="stylesheet"
	href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
	integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p"
	crossorigin="anonymous" />
<link rel="stylesheet" href="./yu.css">
</head>
<style>
#proimg {
	width: 150px;
	height: 150px;
	/* position: absolute; */
	/* float: left; */
}

table {
	margin: auto;
	border: 3px solid gray;
}

td, th {
	/* 兩個不同的標籤有相同的屬性 */
	border: 2px solid gray;
	/* 框粗細 */
}
</style>
</head>

<body>
	
	<header>
	<h1>餐點清單管理</h1>
	</header>

	<form action="./MenuEditServlet" method="get" name="list"
		enctype="multipart/form-data">
		<input type="hidden" id="fn" name="turn" value="">		
		<input type="hidden" id="productname" name="productname" value="">
		<input type="hidden" id="remark" name="remark" value="">
		<input type="hidden" id="price" name="price" value="">
		<!-- <input type="submit" value="submit 1"> -->
		</form>
		<table>
			<thead>
				<tr>
					<th>菜名</th>
					<!-- <th>照片</th> -->
					<th>菜色描述</th>
					<th>單價</th>
					<th colspan="4">編輯</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach var="menu" items="${sessionScope.findallMenu}">  
				<tr> 
				<td>${menu.productName}</td>
				<td>${menu.remark}</td>
				<td>${menu.price}</td>
				<td><input type="submit" id="alt" name="alt" value="修改"> 
					<input type="submit" id="del" name="del" value="刪除"></td>
				</tr>
				</c:forEach>
<%-- 				<td><jsp:getProperty name="menu" property="productName" /></td> --%>
<%-- 				<td><jsp:getProperty name="menu" property="remark" /></td> --%>
<%-- 				<td><jsp:getProperty name="menu" property="price" /></td> --%>
			</tbody>
		</table>
	

	<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
	<script>
		$(function() {

			$('tbody').on('click', '#del', function() {
				let productname = $(this).parent().parent().children().eq(0).text()						
				$('#productname').val(productname)	
				
				let remark = $(this).parent().parent().children().eq(1).text()				
				$('#remark').val(remark)	
				
				let price =	$(this).parent().parent().children().eq(2).text()				
				$('#price').val(price)	

				$("#fn").val('fn1')
				document.forms[0].submit()

			})			

			$('tbody').on('click', '#alt', function () {
				let productname = $(this).parent().parent().children().eq(0).text()						
				$('#productname').val(productname)	
				
				let remark = $(this).parent().parent().children().eq(1).text()				
				$('#remark').val(remark)	
				
				let price =	$(this).parent().parent().children().eq(2).text()				
				$('#price').val(price)	

				$("#fn").val('fn')				
				document.forms[0].submit()
                })

		})
	</script>

</body>

</html>