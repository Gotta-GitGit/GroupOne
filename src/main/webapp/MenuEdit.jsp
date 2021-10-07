<%@page import="Menu.MenuListBean"%>
<%@page import="java.util.Vector"%>
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
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>餐點編輯管理</title>
<link rel="stylesheet" href="./yu.css">
</head>
<style>
#proimg {
	width: 150px;
	height: 150px;
	/* position: absolute; */
	/* float: left; */
}

table {
	margin: auto;
}

td, th {
	/* 兩個不同的標籤有相同的屬性 */
	border: 2px solid gray;
	/* 框粗細 */
}
</style>
</head>

<body>
	<jsp:useBean id="menu" class="Menu.MenuBean" scope="session" />	
	<header>
	<h1>菜單編輯</h1>
	</header>
	<div>
		<form action=".\MenuEditServlet" method="get"
			enctype="multipart/form-data">
			<br>

			<fieldset>
				<legend>菜單 Menu</legend>

				<div class="st1">
					<label for="productname" class="t1">* 品名：</label> <input
						type="text" id="productname" name="productname" value="<jsp:getProperty name="menu" property="productName" />"
						size="20"> <span id="pronamesp"></span> <span class="ps">(必填！)</span>
				</div>

				<!-- <div class="st1">
                <label class="t1">照片:</label>
                <input type="file" accept="image/*" id="pic" name="file" multiple>
            </div> -->

				<div class="st1">
					<label for="remark" class="t1">菜色描述： </label>
					<textarea name="remark" id="remark" cols="40" rows="5"><jsp:getProperty name="menu" property="remark" /></textarea>
				</div>

				<div class="st1">
					<span id="pricesp">$NTD</span> <label for="price" class="t1">*
						單價： </label> <input type="text" id="price" name="price" value="<jsp:getProperty name="menu" property="price" />">
					<span id="prisp">(必填！)</span>
				</div>

			</fieldset>

			<table>
				<thead>
				
				</thead>

				<tbody>
					
				</tbody>
			</table>

			<div class="sub">
				<input type="submit" name="submit" value="新增"> 
				<input type="submit" name="alt" value="修改">
				<input type="reset" value="清除">
			</div>
		</form>

	</div>


	<script>
		//菜名
		document.getElementById("productname").addEventListener("blur",
				checkName);
		function checkName() {
			let theProNameObj = document.getElementById("productname");
			let theProNameObjVal = theProNameObj.value;
			let prsp = document.getElementById("pronamesp");
			let theProNameObjValLen = theProNameObjVal.length;
			let f1 = false;

			if (theProNameObjVal == "")
				prsp.innerHTML = "<img src='Images/error.png'>不可為空白";
			else if (theProNameObjValLen >= 2) {

				for (let j = 0; j < theProNameObjValLen; j++) {
					ch = theProNameObjVal.charCodeAt(j);
					console.log(ch); //10進制
					if (ch >= 0x4e00 && ch <= 0x9ffff) { //中文unicode
						console.log(true);
						f1 = true;
					} else {
						console.log(false)
						f1 = false;
						break;
					}
				}
				if (f1)
					prsp.innerHTML = "<img src='Images/correct.png'>正確"
				else
					prsp.innerHTML = "<img src='Images/error.png'>只能有中文";
			} else
				prsp.innerHTML = "<img src='Images/error.png'>至少2個字";

		}
	</script>

	<script>
		//單價
		document.getElementById("price").addEventListener("blur", checkName);
		function checkName() {
			let thePriObj = document.getElementById("price");
			let thePriObjVal = thePriObj.value;
			let prisp = document.getElementById("prisp");
			let thePriObjValLen = thePriObjVal.length;
			let f1 = false;

			if (thePriObjVal == "")
				prisp.innerHTML = "<img src='Images/error.png'>不可為空白";
		}
	</script>

</body>

</html>

