<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
response.setContentType("text/html;charset=UTF-8");
response.setHeader("Cache-Control", "no-cache"); // HTTP 1.1
response.setHeader("Pragma", "no-cache"); // HTTP 1.0
response.setDateHeader("Expires", -1); // Prevents caching at the proxy server
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="./yu.css">
<title>Double Check Your Information</title>
</head>
<body>
	<jsp:useBean id="open_rst" class="Restaurant.RestaurantBean" scope="session" />
	<header>
	<h1>餐廳建立</h1>
	</header>
	<h1 style="text-align: center; color: firebrick;">餐廳資料如下請再次確認</h1>
	<form action="./NewARestaurantServlet" method="get">
     <!--enctype="multipart/form-data"-->
		<br>

		<fieldset>
			<legend>
				基本資料<em>(必填)</em>
			</legend>

			<!-- restaurant縮寫rst -->
			<div class="st1">
				<label for="username">經營者姓名：</label> 
				<jsp:getProperty name="open_rst" property="userName" />
				<span class="ps">(1.必填 2.至少兩個字 3.必為中文)</span>
			</div>

			<div class="st1">
				<label for="">經營者電話：</label>
				<jsp:getProperty name="open_rst" property="mobile" />
                <span class="ps">(必填)</span>
			</div>

			<div class="st1">
				<span id="rstnamesp"></span> <span class="ps">(必填)</span>
				<jsp:getProperty name="open_rst" property="rstname" />
			</div>

			<div class="st1">
				<label for="license">證照號碼：</label> 
				<jsp:getProperty name="open_rst" property="license" />
                <span class="ps">(必填)</span>
			</div>

			<!-- <div class="st1">
				<label class="t1">執照照片:</label>
				
			</div> -->

			<div class="st1">
				<label for="rstaddress">餐廳地址：</label> 
				<jsp:getProperty name="open_rst" property="rstaddress" />
				<span class="ps">(1.必填 2.必為中文 3.至少8個字)</span>
			</div>

			<div class="st1">
				<label for="">餐廳電話：</label> 
				<jsp:getProperty name="open_rst" property="rsttel" />
                <span class="ps">(必填)</span>

			</div>

			<div class="st1">
				<label for="">餐廳類型：</label>
				<span class="ps">(日式、中式、義式、韓式、台式、美式...etc.)</span>
				<jsp:getProperty name="open_rst" property="type" />
			</div>

			<div class="st1">
				<label for="">營業時間<span class="ps">(請以24小時制填寫)：</span></label>
				<jsp:getProperty name="open_rst" property="businesshour" />
				


			</div>

		</fieldset>
		<div class="sub">
			<input type="submit" name="confirm"
				style="width: 200px; height: 30px; font-size: medium;"
				value="確認所有資料無誤，送出！">
		</div>
	</form>


</body>
</html>