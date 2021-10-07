<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

			<sql:setDataSource var="ds" dataSource="jdbc/DB" />
			<sql:query sql="select PRODUCT_ID,PRODUCT_NAME,PRICE,PEOPLE_ENOUGH,END_DATE from ProductImformation"
				var="rs" dataSource="${ds}" />
			<!DOCTYPE html>
			<html>

			<head>
				<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
				<meta name="viewport" content="width=device-width, initial-scale=1.0">
				<title>Customer</title>
				<link rel="stylesheet" href="./style06.css">
				<style>
					.div {
						display: inline-block;
						background-color: white;
						margin: 10px;
						width: 300px;
						height: 300px;
						font-size: medium;
						line-height: 30px;
						text-align: center;
						border: black solid 3px;
					}

					p {
						line-height: 30px;
						font-size: medium;
					}
				</style>
			</head>

			<body>
				<header>
					<h1>customer</h1>
				</header>
				<div class="box" style="text-align: center;">
					<form action="./WatchAllCartServlet" method="POST">
						<input type="submit" name="cart" value="購物車">
						<input type="submit" name="select" value="歷史紀錄">
					</form>
				</div>
				<c:forEach var="row" items="${rs.rows}">
					<div class="div">
						<p>名稱:${row.PRODUCT_NAME}</p>
						<p>價格:${row.PRICE}</p>
						<p>人數:${row.PEOPLE_ENOUGH}</p>
						<p>截止日:${row.END_DATE}</p>
						<form action="./CustomerServelet" method="get">
							id:<input type="text" id="number" name="id" value="${row.PRODUCT_ID}" readonly> <br>
							<label for="number">數量:</label><input type="number" id="number" name="number"> <br>
							<input type="submit" name="Cart" value="送出">
						</form>
					</div>
				</c:forEach>

			<a href="SellerMain.html">賣家主畫面</a>

			</body>

			</html>