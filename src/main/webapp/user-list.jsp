<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>聚點時刻 買家用戶管理系統</title>
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
			style="background-color: teal"> <!-- default is tomato -->
			<div>
				<a href="user-list.jsp" class="navbar-brand"> 
					聚點時刻 買家用戶管理系統 </a>
			</div>

			<ul class="navbar-nav">
				<li><a href=".\UserAll?action=listuser"
					class="nav-link"><b>買家</b></a></li>
				<li><a href="seller-list.jsp"
					class="nav-link">賣家</a></li>
				<li><a href=".\Adminlogout"
					class="nav-link" style="color:red">登出</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="container-fluid">
			<h3 class="text-center">買家用戶名單</h3>
			<hr>
			<div class="container text-left">
				<a href=".\UserAll?action=newuser" class="btn btn-success" >Add
					New User</a>

				<a href=".\UserAll?action=listuser" class="btn btn-success" >Select
					All Users</a>
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
						<th>homeNumber</th>
						<th>bonusPoint</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<!--   for (Todo todo: todos) {  -->
					<c:forEach var="user" items="${listUser}">

						<tr>
							<td><c:out value="${user.id}" /></td>
							<td><c:out value="${user.username}" /></td>
							<td><c:out value="${user.fullname}" /></td>
							<td><c:out value="${user.dob}" /></td>
							<td><c:out value="${user.gender}" /></td>
							<td><c:out value="${user.email}" /></td>
							<td><c:out value="${user.phoneNumber}" /></td>
							<td><c:out value="${user.homeNumber}" /></td>
							<td><c:out value="${user.bonusPoint}" /></td>
							<td><a href=".\UserAll?action=edituser&id=<c:out value='${user.id}' />">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href=".\UserAll?action=deleteuser&id=<c:out value='${user.id}' />">Delete</a></td>
						</tr>
					</c:forEach>
					<!-- } -->
				</tbody>

			</table>
		</div>
	</div>
</body>
</html>
