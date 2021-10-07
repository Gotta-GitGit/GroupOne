<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>聚點時刻 賣家首頁</title>
</head>
<body>
    <div style="text-align: center">
        <h1>歡迎回到賣家平台</h1>
        <b>${seller.fullname} | ${seller.email}</b>
        <br><br>
        <a href="Sellerlogout">Logout</a>
        <a href="SellerMain.html">團購</a>
        <a href="NewARestaurant.jsp">餐廳</a>

		
    </div>
    <%
	if(session.getAttribute("seller") == null) {
	    out.println("<script>");
	    out.println("alert('請退出賣家登入');");
	    out.println("window.location.href='index.jsp';");
	    out.println("</script>");
	}
	if(session.getAttribute("seller") != null) {
	    out.println("<script>");
	    out.println("alert('您已經登入');");
	    //out.println("window.location.href='sellerCenter.jsp';");
	    out.println("</script>");
	}
	%>
</body>
</html>