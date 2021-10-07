<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>聚點時刻 買家用戶登入</title>
<link rel="stylesheet" href=".\registerStyle.css">
</head>
<body>

	<%
	/*if(session.getAttribute("loginState") == "1") {
	    out.println("<script>");
	    out.println("alert('請退出管理員登入');");
	    out.println("window.location.href='index.jsp';");
	    out.println("</script>");
	}
	if(session.getAttribute("loginState") == "0") {
	    out.println("<script>");
	    out.println("alert('您已經登入');");
	    out.println("window.location.href='userCenter.jsp';");
	    out.println("</script>");
	} */
	%>

	<h1>聚點時刻 買家用戶登入</h1>
	<hr>
	<a href="index.jsp">首頁</a>
	<hr>
	<div style="text-align: center">
		<h1>買家登入</h1>
		<form id="loginForm" action=".\Userlogin?action=userlogin"
			method="post">
			<label for="email1"><span id="must">*</span>Email:</label> <input name="email" id="email1" size="30" autofocus required/> <br>
			<br> 
			<label for="username"><span id="must">*</span>帳號:</label> <input
				type="text" name="username" id="username1" size="30" required/> <br>
			<br>
			<label for="password1"><span id="must">*</span>密碼:</label> <input
				type="password" name="password" id="password1" size="30" required/> <br>${message}<br>
			<%=(request.getAttribute("errMessage") == null) ? "" : request.getAttribute("errMessage")%><br>           
			<button type="submit">登入</button>
			<a href="UserRegisterForm.jsp">買家用戶註冊</a>
		</form>
	</div>
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"
		integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
		crossorigin="anonymous"></script>
	<script type="text/javascript"
		src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.0/dist/jquery.validate.min.js"></script>
	<script type="text/javascript">
		    $(document).ready(function() {
			$("#loginForm").validate({
				rules : {
					email : {
						required : true,
						email : true
					},
					username : "required"
					password : "required",
				},

				messages : {
					email : {
						required : "請輸入email",
						email : "請輸入格式正確的email"
					},
					username : "請輸入帳號"
					password : "請輸入密碼"
				}
				
				alert()
			});


		});
		</body>
		</html>
	</script>
	