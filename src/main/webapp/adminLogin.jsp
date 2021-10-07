<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>聚點時刻 管理員登入</title>
<link rel="stylesheet" href=".\registerStyle.css">
</head>
<body>
	<h1>聚點時刻 管理員登入</h1>
	<hr>
	<a href="index.jsp">首頁</a>
	<hr>
	<div style="text-align: center">
		<h1>管理員登入</h1>
		<form id="loginForm" action=".\Adminlogin" method="post">
			<label for="email1"><span id="must">*</span>Email:</label> <input name="email" id="email1" size="30" autofocus required/> <br>
			<br> <label for="username1"><span id="must">*</span>帳號:</label> <input
				type="text" name="username" id="username1" size="30" required/> <br>           
			<br> <label for="password1"><span id="must">*</span>密碼:</label> <input
				type="password" name="password" id="password1" size="30" required/> <br>${message} <br>
			<br>           
			<button type="submit">登入</button>
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
			});

		});
	</script>
</body>
</html>