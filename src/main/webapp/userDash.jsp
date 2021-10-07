<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>聚點時刻 買家首頁</title>
    <link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>
    <div style="text-align: center">
        <h1>歡迎回到買家平台</h1>
        <b>${user.fullname} | ${user.email}</b>
        <br><br>
        <a href="Userlogout">Logout</a>
        <a href="customer.jsp">團購</a>
        <br><br>        
        <!-- 
        <a href="userUpdate.jsp">編輯買家個人資料</a>
         -->
    </div>
    <%
	if(session.getAttribute("user") == null) {
	    out.println("<script>");
	    out.println("alert('請退出買家登入');");
	    out.println("window.location.href='index.jsp';");
	    out.println("</script>");
	}
	if(session.getAttribute("user") != null) {
	    out.println("<script>");
	    out.println("alert('您已經登入');");
	    //out.println("window.location.href='sellerCenter.jsp';");
	    out.println("</script>");
	}
	%>
</body>
</html>