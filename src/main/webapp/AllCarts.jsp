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
    	width: 1000px;
	    margin: auto;
    	border: 3px solid black;
	}
</style>
</head>

<body>
	<jsp:useBean id="all_carts" class="shopping.AllCartsBean"
		scope="session" />
	<header>
		<h1>購買紀錄</h1>
	</header>
	<div id="container">
		<form action="./WatchAllCartServlet" method="POST" target="_blank">
			<input type="hidden" id="itemNo" name="itemNo" value="">
		</form>
		<table id="findAll" class="display">
			<thead>
				<tr>
					<th>訂單編號</th>
					<th>商品類別</th>
					<th>總金額</th>
					<th>點數增減</th>
					<th>付款日期</th>
					<th>備註</th>
				</tr>
			</thead>
			<tbody>	
			 <c:choose>
                <c:when test="${fn:length(all_carts.getvCart()) != 0}">
					<c:forEach var="i" begin="0" end="${fn:length(all_carts.getvCart())/6-1}" step="1">
					<tr>
						<td>${all_carts.getvCart()[0+i*6]}</td>
						<td>${all_carts.getvCart()[1+i*6]}</td>
						<td>NT$ ${all_carts.getvCart()[2+i*6]}</td>
						<td>${all_carts.getvCart()[3+i*6]}</td>
						<td>${all_carts.getvCart()[4+i*6]}</td>
						<td>${all_carts.getvCart()[5+i*6]}</td>
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
				"scrollY":        "500px",
     			"scrollCollapse": true,
    		    "paging":         false
			});

			var table = $('#findAll').DataTable(); 
			$('#findAll tbody').on( 'click', 'tr', function () {
				if ( $(this).hasClass('selected') ) {
					$(this).removeClass('selected');
				}else {
					table.$('tr.selected').removeClass('selected');
					$(this).addClass('selected');
				}
			} );

			$('tr').on('click', function () {
                let itemNo =$(this).children().eq(0).text()
				if(itemNo != '訂單編號'){
					console.log('td-item:'+itemNo)
					$('#itemNo').val(itemNo)
					let test = $('#itemNo').val()
					console.log(test)
					document.forms[0].submit()
				}
            })
		})
	</script>
</body>

</html>