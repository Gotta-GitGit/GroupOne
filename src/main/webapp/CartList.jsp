<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>聚點時刻</title>
	<link rel="stylesheet" href="link.css">
	<style>
		.number , .tdalign{
			text-align: center;
		}
		.tap2{
			float: right;
		}
		.confirm{
			clear: both;
			text-align: center;
		}
		.tp{
			padding-left: 5px;
		}
	</style>
</head>

<body>
	<jsp:useBean id="shop_bean" class="shopping.ShoppingBean" scope="session" />
	<header>
		<h1>購物車</h1>
	</header>
	<div id="container">
		<form action="./PayProcessServlet" method="POST" name="shoppingCarts">
			<input type="hidden" name="username" value="<jsp:getProperty name="shop_bean" property="username" />">
			<input type="hidden" name="itemNo" value="<jsp:getProperty name="shop_bean" property="itemNo" />">
			<hr>
			<div>
				<table>
					<thead>
						<tr>
							<th class="pn">商品</th>
							<th class="pr">單價(NT$)</th>
							<th class="nu">數量</th>
							<th style=" width: 185px;"></th>
						</tr>
					</thead>
					<tbody id="item">
					<c:forEach var="i" begin="0" end="${fn:length(shop_bean.getShoppingList())/3-1}" step="1">
						<tr class="itr">
							<input type="hidden" class="productName" name="productName" value="${shop_bean.getShoppingList()[0+i*3]}">
							<td class="tp"></td>
							<input type="hidden" class="price" name="price" value="${shop_bean.getShoppingList()[1+i*3]}">
							<td class="tdalign"></td>
							<td>
								<button type="button" class="minus">－</button>
								<input type="text" class="number"  size="5" min="1" max="" name="number" value="${shop_bean.getShoppingList()[2+i*3]}">
								<button type="button" class="plus">＋</button>
							</td>
							<td class="tdB"><button class="delete"><i class="fas fa-trash-alt"></i></button></td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
				</div>
				<table class="tap2">
					<tr>
						<th class="ap">總金額(NT$)</th>
						<input type="hidden" id="amount" name="amount" value="">
						<th class="ap" id="apa"></th>
						<th class="ap">點數</th>
						<input type="hidden" id="point" name="point" value="">
						<th class="ap" id="app"></th>
					</tr>
				</table>
		</form>
		<div class="confirm">
				<button id="updata">
					<i class="fas fa-edit">確認訂單內容</i>
				</button>
				<button id="pay" disabled>
					<i class="fas fa-shopping-basket">付款</i>
				</button>
		</div>
		</div>
		<script src="jquery-3.6.0.js"></script>
		<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
		<script src="cart.js"></script>
		</body>

</html>