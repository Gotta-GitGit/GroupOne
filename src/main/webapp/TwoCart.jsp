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
<link rel="stylesheet" href="link.css">
  <style>
        .btn {
            text-align: center;
        }
        /* .paytype{
            text-align: center;
        } */
        table{
            margin: auto;
        }
		.pn{
			border-right: 1px dashed black;
		}
    </style>
</head>

<body>
	<jsp:useBean id="now_cart" class="shopping.NowCartBean" scope="session" />
	<header>
		<h1>目前存在的購物車</h1>
	</header>
	<div id="container">
	  <div class="paytype">
            <input type="hidden" name="username" value="<jsp:getProperty name="now_cart" property="username" />">
            <table>
				<thead>
					<tr>
						<th>商品類型</th>
						<th></th>
					</tr>
				</thead>
                <tbody>
                <c:choose>
                	<c:when test="${fn:length(now_cart.getNowCart()) == 0}">
               		 	<tr>
            	       		<td>目前尚無購物內容</td>
             		    </tr>
                	</c:when>
                	<c:otherwise>
    		 	        <c:forEach var="i" begin="0" end="${fn:length(now_cart.getNowCart())/2-1}" step="1">
							<tr class="itr">
								<form action="./ShoppingCartServlet" method="POST">
								<input type="hidden" name="itemNo" value="${now_cart.getNowCart()[0+i*2]}">
								<td class="pn">${now_cart.getNowCart()[1+i*2]}</td>
								<td><button type="button" class="pay${i}">結帳</button></td>
								</form>
							</tr>
						</c:forEach>
                	</c:otherwise>
                </c:choose>
                </tbody>
            </table>
        </div>
        <div class="btn">
            <button type="button" id="foodbtn">前往訂餐</button>
            <button type="button" id="couponbtn">參加團購</button>
        </div>	
	</div>
	<script src="jquery-3.6.0.js"></script>
	<script>
		$(function() {
			$('#foodbtn').on('click', function() {
				location.href = './customer.jsp'
			})
			$('#couponbtn').on('click', function() {
				location.href = './customer.jsp'
			})
			$('.pay0').on('click', function() {
				document.forms[0].submit()
			})
			$('.pay1').on('click', function() {
				document.forms[1].submit()
			})
		})
	</script>
</body>

</html>