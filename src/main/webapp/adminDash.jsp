<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>聚點時刻 管理員首頁</title>
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
	if(session.getAttribute("admin") != null) {
	    out.println("<script>");
	    out.println("alert('您已經登入');");
	    //out.println("window.location.href='sellerCenter.jsp';");
	    out.println("</script>");
	}
	%>
	<header>
		<!-- 套用bootstrap CSS樣式 -->
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: teal"> <!-- default is tomato -->
			<div>
			<!-- 導向首頁 -->
				<a href="adminDash.jsp" class="navbar-brand"> 聚點時刻 買家及賣家用戶管理系統 </a>
			</div>
			<!-- 套用bootstrap CSS樣式 -->
			<ul class="navbar-nav">
				<li><a href="user-list.jsp"
					class="nav-link"><b>買家</b></a></li>
				<li><a href="seller-list.jsp"
					class="nav-link">賣家</a></li>
			</ul>
		</nav>
	</header>
	<br>
    <div style="text-align: center">
        <h1>歡迎回到管理員平台</h1>
        <b>${admin.fullname} | ${admin.email}</b>
        <br><br>
        <br><br>
        <br><br>
        <div>
	        <button type="button" id="manageUser" class="btn btn-outline-info" >管理<b>買家</b>用戶</button>
	        <button type="button" id="manageSeller" class="btn btn-outline-info" >管理<b>賣家</b>用戶</button>        
        </div>
        <br><br>
        <a href="ActivityRegister.jsp">活動</a>
        <a href=".\Adminlogout">登出</a>
    </div>
    
    <% /*
	if(session.getAttribute("admin") == null) {
	    out.println("<script>");
	    out.println("alert('請退出管理員登入');");
	    out.println("window.location.href='index.jsp';");
	    out.println("</script>");
	}
	if(session.getAttribute("admin") != null) {
	    out.println("<script>");
	    out.println("alert('您已經登入');");
	    //out.println("window.location.href='sellerCenter.jsp';");
	    out.println("</script>");
	} */
	%>

	<script>
        document.getElementById("manageUser").addEventListener("click", openUser)
        function openUser(){
            window.location.href='user-list.jsp';
        }

        document.getElementById("manageSeller").addEventListener("click", openSeller)
        function openSeller(){
            window.location.href='seller-list.jsp';
        }
    </script>
</body>
</html>