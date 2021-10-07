<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>聚點時刻 賣家用戶管理系統</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>
	<%
	if(session.getAttribute("admin") == null) {
	    out.println("<script>");
	    out.println("alert('請退出管理員登入');");
	    out.println("window.location.href='index.jsp';");
	    out.println("</script>");
	}
	/*
	if(session.getAttribute("admin") != null) {
	    out.println("<script>");
	    out.println("alert('您已經登入');");
	    //out.println("window.location.href='sellerCenter.jsp';");
	    out.println("</script>");
	}
	*/
	%>
	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: SkyBlue"> <!-- default is tomato -->
			<div>
				<a href="seller-list.jsp" class="navbar-brand"> 
					聚點時刻 賣家用戶管理系統 </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="user-list.jsp"
					class="nav-link">買家</a></li>
				<li><a href=".\SellerAll?action=listseller"
					class="nav-link"><b>賣家</b></a></li>
				<li><a href=".\Adminlogout"
					class="nav-link" style="color:red">登出</a></li>
			</ul>
		</nav>
	</header>
	<br>

	<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="container-fluid">
			<h3 class="text-center">賣家用戶名單</h3>
			<hr>
			<div class="container text-left">
				<a href=".\SellerAll?action=newseller" class="btn btn-success" >Add
					New Seller</a>

				<a href=".\SellerAll?action=listseller" class="btn btn-success" >Select
					All Sellers</a>
					<br>
				  <!-- <form class="form-inline">
				    <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
				    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
				  </form>  -->
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>username</th>
						<th>fullname</th>
						<th>dob</th>
						<th>gender</th>
						<th>email</th>
						<th>phoneNumber</th>
						<th>telephoneNumber</th>
						<th>extensionNumber</th>
						<th>companyName</th>
						<th>companyAddress</th>
						<th>businessCert</th>
						<th>verifyStatus</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<!--   for (Todo todo: todos) {  -->
					<c:forEach var="seller" items="${listSeller}">

						<tr>
							<td><c:out value="${seller.id}" /></td>
							<td><c:out value="${seller.username}" /></td>
							<td><c:out value="${seller.fullname}" /></td>
							<td><c:out value="${seller.dob}" /></td>
							<td><c:out value="${seller.gender}" /></td>
							<td><c:out value="${seller.email}" /></td>
							<td><c:out value="${seller.phoneNumber}" /></td>
							<td><c:out value="${seller.telephoneNumber}" /></td>
							<td><c:out value="${seller.extensionNumber}" /></td>
							<td><c:out value="${seller.companyName}" /></td>
							<td><c:out value="${seller.companyAddress}" /></td>
							<td><c:out value="${seller.businessCert}" /></td>
							<td><c:out value="${seller.verifyStatus}" /></td>
							<td><a href=".\SellerAll?action=editseller&id=<c:out value='${seller.id}' />">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href=".\SellerAll?action=deleteseller&id=<c:out value='${seller.id}' />">Delete</a></td>
						</tr>
					</c:forEach>
					<!-- } -->
				</tbody>

			</table>
		</div>
	</div>
</body>
</html>
