<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- JSP基本設定 -->
<!-- 引用JSP c tag -->
<html>
<head>
<title>聚點時刻 賣家用戶管理系統</title>
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
			style="background-color: SkyBlue"> <!-- default is tomato -->
			<div>
			<!-- 導向首頁 -->
				<a href="seller-list.jsp" class="navbar-brand"> 聚點時刻 賣家用戶管理系統 </a>
			</div>
			<!-- 套用bootstrap CSS樣式 -->
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
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<!-- c:if tag判斷seller是否存在，決定顯示選項為insert或update -->
				<c:if test="${seller != null}">
					<form action=".\SellerAll?action=updateseller" method="post" >
				</c:if>
				<c:if test="${seller == null}">
					<form action=".\SellerAll?action=insertseller" method="post" >
				</c:if>

				<!-- c:if tag判斷seller是否存在，決定顯示選項為edit或add -->
				<caption>
					<h2>
						<c:if test="${seller != null}">
            			Edit Seller 修改賣家資訊
            		</c:if>
						<c:if test="${seller == null}">
            			Add New Seller 新增賣家資訊
            		</c:if>
					</h2>
				</caption>
				<!-- seller存在時，印出user id -->
				<c:if test="${seller != null}">
					<input type="hidden" name="id" value="<c:out value='${seller.id}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>username</label> <input type="text"
						value="<c:out value='${seller.username}' />" class="form-control"
						name="username" autofocus required>
				</fieldset>

				<fieldset class="form-group">
					<label>password</label> <input type="password"
						value="<c:out value='${seller.password}' />" class="form-control"
						name="password" required maxlength="20" autocomplete="off">
				</fieldset>

				<fieldset class="form-group">
					<label>fullname</label> <input type="text"
						value="<c:out value='${seller.fullname}' />" class="form-control"
						name="fullname" placeholder="李小龍" required>
				</fieldset>

				<fieldset class="form-group">
					<label>dob</label> <input type="date"
						value="<c:out value='${seller.dob}' />" id="datefield" class="form-control"
						name="dob" min="1900-01-01" max="2050-12-31" maxlength="10">
				</fieldset>

				<fieldset class="form-group">
					<label>gender</label> <input type="text"
						value="<c:out value='${seller.gender}' />" class="form-control"
						name="gender" maxlength="1">
				</fieldset>

				<fieldset class="form-group">
					<label>email</label> <input type="email"
						value="<c:out value='${seller.email}' />" class="form-control"
						name="email" required>
				</fieldset>

				<fieldset class="form-group">
					<label>phoneNumber</label> <input type="tel"
						value="<c:out value='${seller.phoneNumber}' />" class="form-control"
						name="phoneNumber" placeholder="0988888888" pattern="[0]{1}[9]{1}[0-9]{8}" required maxlength="10">
				</fieldset>

				<fieldset class="form-group">
					<label>telephoneNumber</label> <input type="text"
						value="<c:out value='${seller.telephoneNumber}' />" class="form-control"
						name="telephoneNumber" placeholder="0288888888" maxlength="10">
				</fieldset>

				<fieldset class="form-group">
					<label>extensionNumber</label> <input type="text"
						value="<c:out value='${seller.extensionNumber}' />" class="form-control"
						name="extensionNumber" placeholder="#1234" pattern="[#]{1}[0-9]{4}" maxlength="5">
				</fieldset>

				<fieldset class="form-group">
					<label>companyName</label> <input type="text"
						value="<c:out value='${seller.companyName}' />" class="form-control"
						name="companyName" required>
				</fieldset>

				<fieldset class="form-group">
					<label>companyAddress</label> <input type="text"
						value="<c:out value='${seller.companyAddress}' />" class="form-control"
						name="companyAddress" required>
				</fieldset>

				<fieldset class="form-group">
					<label>businessCert</label> <input type="text"
						value="<c:out value='${seller.businessCert}' />" class="form-control"
						name="businessCert1" readonly>
						
						<input type="file" name="businessCert" accept=".pdf" >
                		<br>
                		<span style="margin: 20px; text-decoration: underline;"><b>(PDF格式)</b></span>
				</fieldset>

				<fieldset class="form-group">
					<label>verifyStatus</label> <input type="text"
						value="<c:out value='${seller.verifyStatus}' />" class="form-control"
						name="verifyStatus">
				</fieldset>

				<button type="submit" class="btn btn-success">Save</button>
				<!-- <input type="reset"> -->
				</form>
			</div>
		</div>
	</div>
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
	
</body>
</html>
