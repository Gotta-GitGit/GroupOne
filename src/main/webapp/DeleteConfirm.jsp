
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<sql:setDataSource var="ds" dataSource="jdbc/DB" />


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% 
response.setContentType("text/html;charset=UTF-8");
response.setHeader("Cache-Control","no-cache"); // HTTP 1.1
response.setHeader("Pragma","no-cache"); // HTTP 1.0
response.setDateHeader ("Expires", -1); // Prevents caching at the proxy server
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset="UTF-8">
<title>新增活動</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
   	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
   	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
   	
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<style>
	body{
	 	background-color: #e9e9e9;
	}
    .st01{
        width: 1050px;
        /* min-height: calc(100vh - 100px); */
        height: 1700px;
        margin: 20px auto 30px;
        background-color: rgb(228, 231, 236);
        padding: 100px 0 46px;
    }
    .st02{
        padding-top: 0;
        display: flex;
        flex-direction: column;
        align-items: center;
    }
    
    .st1 {
        width: 450px;
        border-bottom:3px dashed rgb(236, 211, 211);
        margin: 20px;
        padding-bottom: 10px;
        
    }

    .t1 {
        width: 150px;
        float: left;
        text-align: left;
    } 

    .mi {
        color: red;
    }

    .hint {
        text-align:center;
        color: grey;
        font-size: 50%;
        margin-left: 60px;
    }
    .btn {
        display: inline-block;
        padding: 10px 20px;
        font-size: 10px;
        cursor: pointer;
        text-align: center;
        text-decoration: none;
        outline: none;
        color: #fff;
        background-color: #3f18cf;
        border: none;
        border-radius: 10px;
        box-shadow: 0 5px #999;
        margin-left: 50px;
    }
    .btn:hover {background-color: #2e7fdb} 

    .btn:active {
        background-color: #3e8e41;
        box-shadow: 0 5px #666;
        transform: translateY(4px);
    }
    .btn1 {
        display: inline-block;
        padding: 10px 20px;
        font-size: 10px;
        text-align: center;
        text-decoration: none;
        color: #fff;
        background-color: #3f18cf;
        border: none;
        border-radius: 10px;
        margin-left: 50px;
    }
    
    
    .topnav .search-container {
	  float: right;
	}
    .topnav input[type=text] {
	  padding: 6px;
	  margin-top: 8px;
	  font-size: 14px;
	  border: none;
	}
	.topnav .search-container button {
	  float: right;
	  padding: 6px 10px;
	  margin-top: 8px;
	  margin-right: 16px;
	  background: #ddd;
	  font-size: 17px;
	  border: none;
	  cursor: pointer;
	}
	.topnav .search-container button:hover {
	  background: #ccc;
	}
	@media screen and (max-width: 600px) {
	  .topnav .search-container {
	    float: none;
	  }
	  
	  .topnav a, .topnav input[type=text], .topnav .search-container button {
	    float: none;
	    display: block;
	    text-align: left;
	    width: 100%;
	    margin: 0;
	    padding: 14px;
	  }
	  .topnav input[type=text] {
	    border: 1px solid #ccc;  
	  }
	
</style>
<body>


<br>
<div class="topnav">
		<form action=".\RegisterServlet" method="get">
			<table class="table" id="list_table">
	         <thead class="text-light" style="background-color:rgb(167, 201, 66);">
	         <tr>
	           <th>舉辦活動序號</th>
	           <th>廠商帳號</th>
	           <th>廠商email</th>
	           <th>活動主題</th>
	           <th>類型</th>
	           <th>活動地點</th>
	           <th>活動名額</th>
	           <th>點數欄位</th>
	           <th>Actions</th>
	           <th width="70px"></th>
	          </tr>
	          </thead>
	          <tbody>
                 <c:forEach var="activity" items="${listActivity}">
					 
                    <tr>
                      <td>
                          <c:out value="${activity.id}" />
                      </td>
                      <td>
                          <c:out value="${activity.username}" />
                      </td>
                      <td>
                          <c:out value="${activity.s_email}" />
                      </td>
                      <td>
                          <c:out value="${activity.topic}" />
                      </td>
                      <td>
                          <!--<c:out value="${activity.style}" /> -->
                          <c:forEach var="styles" items="${activity.style}">
						      <c:out value="${styles}"></c:out>
						  </c:forEach>
                      </td>
                      <td>
                          <c:out value="${activity.address}" />
                      </td>
                      <td>
                          <c:out value="${activity.quota}" />
                      </td>
                      <td>
                          <c:out value="${activity.bonus_point}" />
                      </td>
                      <td>
                       <a href="confirmDelete?id=<c:out value='${activity.id}' />">確認</a></td>
                    </tr>
                    
                 </c:forEach>
                            
               </tbody>
	    </table>
	    <input type="button" class="btn" value="返回" onclick="location.href='ActivityRegister.jsp'">
	    
		</form>
</div>

</body>
</html>