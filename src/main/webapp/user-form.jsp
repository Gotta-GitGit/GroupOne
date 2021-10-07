<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- JSP基本設定 -->
<!-- 引用JSP c tag -->
<html>
<head>
<title>聚點時刻 買家用戶管理系統</title>
<!-- 引用Bootstrap CDN的CSS樣式 -->
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
		<!-- 套用bootstrap CSS樣式 -->
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: teal"> <!-- default is tomato -->
			<div>
			<!-- 導向首頁 -->
				<a href="user-list.jsp" class="navbar-brand"> 聚點時刻 買家用戶管理系統 </a>
			</div>
			<!-- 套用bootstrap CSS樣式 -->
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
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<!-- c tag判斷user是否存在，決定顯示選項為insert或update -->
				<c:if test="${user != null}">
					<form action=".\UserAll?action=updateuser" method="post">
				</c:if>
				<c:if test="${user == null}">
					<form action=".\UserAll?action=insertuser" method="post">
				</c:if>

				<!-- c tag判斷user是否存在，決定顯示選項為edit或add -->
				<caption>
					<h2>
						<c:if test="${user != null}">
            			Edit User 修改買家資訊
            		</c:if>
						<c:if test="${user == null}">
            			Add New User 新增買家資訊
            		</c:if>
					</h2>
				</caption>
				<!-- user存在時，印出user id -->
				<c:if test="${user != null}">
					<input type="hidden" name="id" value="<c:out value='${user.id}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>username</label> <input type="text"
						value="<c:out value='${user.username}' />" class="form-control"
						name="username" required="required" autofocus >
				</fieldset>

				<fieldset class="form-group">
					<label>password</label> <input type="password"
						value="<c:out value='${user.password}' />" class="form-control"
						name="password" required>
				</fieldset>

				<fieldset class="form-group">
					<label>fullname</label> <input type="text"
						value="<c:out value='${user.fullname}' />" class="form-control"
						name="fullname" required>
				</fieldset>

				<fieldset class="form-group">
					<label>dob</label> <input type="date"
						value="<c:out value='${user.dob}' />" id="datefield" class="form-control"
						name="dob" min="1900-01-01" max="2050-12-31" maxlength="10" required>
				</fieldset>

				<fieldset class="form-group">
					<label>gender</label> <input type="text"
						value="<c:out value='${user.gender}' />" class="form-control"
						name="gender" required maxlength="1">
				</fieldset>

				<fieldset class="form-group">
					<label>email</label> <input type="email"
						value="<c:out value='${user.email}' />" class="form-control"
						name="email" required>
				</fieldset>

				<fieldset class="form-group">
					<label>phoneNumber</label> <input type="tel"
						value="<c:out value='${user.phoneNumber}' />" class="form-control"
						name="phoneNumber" placeholder="0988888888" pattern="[0]{1}[9]{1}[0-9]{8}" required>
				</fieldset>

				<fieldset class="form-group">
					<label>homeNumber</label> <input type="text"
						value="<c:out value='${user.homeNumber}' />" class="form-control"
						name="homeNumber" placeholder="0288888888">
				</fieldset>

				<fieldset class="form-group">
					<label>bonusPoint</label> <input type="text"
						value="<c:out value='${user.bonusPoint}' />" class="form-control"
						name="bonusPoint">
				</fieldset>

				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
<script>
		// 設定date的max屬性為today
		/*
		var today = new Date();
		var dd = today.getDate();
		var mm = today.getMonth() + 1; //January is 0!
		var yyyy = today.getFullYear();
	
		if (dd < 10) {
		   dd = '0' + dd;
		}
	
		if (mm < 10) {
		   mm = '0' + mm;
		} 
		    
		today = yyyy + '-' + mm + '-' + dd;
		
		document.getElementById("datefield").setAttribute("max", today);
		*/
		
		// maxDate計算生日對應年紀為18歲以上
		var today = new Date();
		var dd = today.getDate();
		var mm = today.getMonth()+1; //January is 0!
		var yyyy = today.getFullYear()-18;
		 if(dd<10){
		        dd='0'+dd
		    } 
		    if(mm<10){
		        mm='0'+mm
		    } 

		maxDate = yyyy+'-'+mm+'-'+dd;
		document.getElementById("datefield").setAttribute("max", maxDate);

		// minDate計算生日對應年紀為18歲以上
		var today = new Date();
		var dd = today.getDate();
		var mm = today.getMonth()+1; //January is 0!
		var yyyy = today.getFullYear()-100;
		 if(dd<10){
		        dd='0'+dd
		    } 
		    if(mm<10){
		        mm='0'+mm
		    } 

		minDate = yyyy+'-'+mm+'-'+dd;
		document.getElementById("datefield").setAttribute("min", minDate);
		
		
	</script>
</html>
