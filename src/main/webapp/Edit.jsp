<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
    
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
            <title>Edit</title>
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        </head>

        <body>

            <header>
                <nav class="navbar navbar-expand-md navbar-dark" style="background-color: green">
                    <div>
                        <a class="navbar-brand"> 修改 </a>
                    </div>

                    <ul class="navbar-nav">
                        <li><a href="<%=request.getContextPath()%>/list" class="nav-link"></a></li>
                    </ul>
                </nav>
            </header>
            <br>
            <div class="container col-md-5">
                <div class="card">
                    <div class="card-body">
                       <form action=".\RegisterServlet" method="get">
						<c:forEach var="activity" items="${listActivity}">
						<fieldset class="form-group">
                            <input type="hidden" name="id" value="<c:out value='${activity.id}' />" />
                        </fieldset>
                        <fieldset class="form-group">
                            <label>帳號</label> <input type="text" value="<c:out value='${activity.username}' />" class="form-control" name="username" required="required">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Email</label> <input type="text" value="<c:out value='${activity.s_email}' />" class="form-control" name="s_email">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>活動主題</label> <input type="text" value="<c:out value='${activity.topic}' />" class="form-control" name="topic">
                        </fieldset>
                        
                        <fieldset class="form-group">
                            <label class="t1 ">類型<span class="mi">*</span></label></br>
                					
                                    <label>
                                        <input type="checkbox" name="style" value="體驗">體驗
                                    </label>
                                    <label>
                                        <input type="checkbox" name="style" value="宣傳活動">宣傳活動 
                                    </label>
                                    <label>
                                        <input type="checkbox" name="style" value="比賽">比賽
                            </label>
                        </fieldset>
                        
                        <fieldset class="form-group">
                            <label>活動地點</label> <input type="text" value="<c:out value='${activity.address}' />" class="form-control" name="address" required="required">
                        </fieldset>
                        
                        <fieldset class="form-group">
                            <label>參與費用</label> <input type="text" value="<c:out value='${activity.cost}' />" class="form-control" name="cost" required="required">
                        </fieldset>
                        
                        <fieldset class="form-group">
                            <label>相關連結</label> <input type="text" value="<c:out value='${activity.link}' />" class="form-control" name="link" required="required">
                        </fieldset>
                        
                        <fieldset class="form-group">
                            <label>活動介紹</label>
                            <input class="form-control" id="exampleFormControlTextarea1" value="<c:out value='${activity.introduce}' />" name="introduce" rows="3" required="required"></input>
                        </fieldset>
                        
                        <fieldset class="form-group">
                            <label>活動名額</label> <input type="text" value="<c:out value='${activity.quota}' />" class="form-control" name="quota" required="required">
                        </fieldset>
                        
                        <fieldset class="form-group">
                            <label>活動行程</label><p>開始時間-結束時間：</p>
                            <input type="text" value="<c:out value='${activity.schedule}' />" class="form-control" name="schedule" required="required">
                        </fieldset>
                        
                        <fieldset class="form-group">
                            <label>注意事項</label>
                            <input class="form-control" id="exampleFormControlTextarea1" value="<c:out value='${activity.notice}' />" name="notice" rows="3" required="required"></input>
                        </fieldset>
						
						<fieldset class="form-group">
                            <label>點數</label>
                            <input type="text" value="<c:out value='${activity.bonus_point}' />" class="form-control" name="bonus_point" required="required">
                        </fieldset>
                        
                        </c:forEach>
                        <button type="submit" name="edit" class="btn btn-success"onclick="location.href='ActivityRegister.jsp'">確認送出</button>
                        </form>
                        <button type="submit" class="btn btn-success" value="返回" onclick="location.href='ActivityRegister.jsp'">返回</button>
                    </div>
                </div>
            </div>
           
        </body>

        </html>