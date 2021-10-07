
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<sql:setDataSource var="ds" dataSource="jdbc/DB" />
<sql:query sql="select username from Activity " var="rs" dataSource="${ds}" /> 

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
<a value="前端" href="ActivityPage.jsp">前端</a>
<form action=".\RegisterServlet" name="testform" method="post">
        <div class="col-md-12 text-center">
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal" data-whatever="@getbootstrap">新增活動</button>
        </div>
        <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-xl">
            <div class="modal-content">
                <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">開始建立活動資訊！</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                </div>
                <div class="modal-body">
                        <main class="st01">
                            <div class="st02 ">
                                <div class="form-group">
                                    <label class="t1">帳號<span class="mi">*</span> </label>
                                    <input type="text" class="form-control" name ="username">
                                    <span id="" style="color: black;"></span>
                                    <span id="" style="color: red;"></span><br/>
                                </div>
                
                                <div class="form-group">
                                    <label class="t1" for="exampleFormControlInput1">Email<span class="mi">*</span></label> 
                                    <input type="email" class="form-control" id="exampleFormControlInput1"name ="s_email" placeholder="name@example.com">
                                    <span id="" style="color: black;"></span>
                                    <span id="" style="color: red;"></span><br/>
                                </div>
                                
                                <div class="form-group">
                                    <label class="t1">活動主題<span class="mi">*</span> </label>
                                    <input type="text" class="form-control" id=""  name="topic">
                                    <span id="" style="color: black;"></span>
                                    <span id="" style="color: red;"></span><br/>
                                </div>
                
                                <div class="form-group">
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
                                    <span id="" style="color: black;"></span>
                                    <span id="" style="color: red;"></span><br/>
                                </div>
                
                                <div class="form-group">
                                    <label class="t1 text-left">活動地點<span class="mi">*</span></label>
                                    <input type="text"class="form-control" id=""  name="address">
                                    <span id="" style="color: black;"></span>
                                    <span id="" style="color: red;"></span><br/>
                                </div>
                
                                <div class="form-group">
                                    <label class="t1">參與費用<span class="mi">*</span></label>
                                    <input type="text" class="form-control" id=""  name="cost">
                                    <span id="" style="color: black;"></span>
                                    <span id="" style="color: red;"></span><br/>
                                </div>
                
                                <div class="form-group">
                                    <label class="t1">相關連結</label>
                                    <input type="text"  class="form-control" id=""  name="link">  
                                    <span id="" style="color: black;"></span>
                                    <span id="" style="color: red;"></span><br/>
                                    <small class="hint"> <加上參考網站如 Facebook 或 Instagram，讓使用者更認識你> </small>
                                </div>
                
                                <div class="form-group">
                                    <label for="exampleFormControlTextarea1" class="t1">活動介紹<span class="mi">*</span></label>
                                    <textarea class="form-control" id="exampleFormControlTextarea1" name="introduce" rows="3"></textarea>
                                    <span id="" style="color: black;"></span>
                                    <span id="" style="color: red;"></span><br/>
                                </div>
                
                                <div class="form-group">
                                    <label  class="t1">活動名額<span class="mi">*</span></label>
                                    <input type="text" class="form-control" id=""  name="quota">
                                    <span id="" style="color: black;"></span>
                                    <span id="" style="color: red;"></span><br/>
                                </div>              
                             
                                <div>
                                <label class="t1 ">活動行程<span class="mi">*</span></label>
                                <p>開始時間-結束時間：</p>
                                <input type="text" class="form-control" id=""  name="schedule">
                                <span id="" style="color: black;"></span>
                                <span id="" style="color: red;"></span><br/>
                                </div>
                                
                
                                <div class="form-group">
                                    <label for="exampleFormControlTextarea1" class="t1">注意事項<span class="mi">*</span></label>
                                    <textarea class="form-control" id="exampleFormControlTextarea1" name="notice" rows="3"></textarea>
                                    <span id="" style="color: black;"></span>
                                    <span id="" style="color: red;"></span><br/>
                                </div>  
                                <div class="form-group">
                                    <label  class="t1">點數<span class="mi">*</span></label>
                                    <input type="text" class="form-control" id=""  name="bonus_point">
                                    <span id="" style="color: black;"></span>
                                    <span id="" style="color: red;"></span><br/>
                                </div>           
                            </div>
                        </main>
                </div>
                <div class="modal-footer">
                    <div class="col-md-12 text-center">
                        <button type="submit" name="submit" onClick="validateForm(this.form)"  class="btn btn-primary">提交表單</button type="reset">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        
                    </div>
                </div>
            </div>
          </div>
        </div>
</form>

<br>

<div>
<div class="search-container">
<form action=".\RegisterServlet" method="get" name="searchTopic">
		<input type="text" placeholder="Search.." name="search">
		<button type="submit"  class="btn1 btn-primary">取得資料</button>
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
                      <td><a href="edit?id=<c:out value='${activity.id}' />">Edit</a>
                       &nbsp;&nbsp;&nbsp;&nbsp; 
                       <a href="delete?id=<c:out value='${activity.id}' />">Delete</a></td>
                    </tr>
                    
                 </c:forEach>
                            
               </tbody>
	    </table>
  </form>
</div>
</div>

<!-- <h2>Results</h2>
  <table border="1" >
    <th>username</th>
    <c:forEach var="row" items="${rs.rows}">
    <tr>
    <td>${row.username}</td>
    </tr>
    </c:forEach>
  </table>
-->
</body>
<script>

	function checkName(control) {
		if (control.value == "") {
			validatePrompt(control, "請填寫帳號！");
			return (false);
		}
		return (true);
	}
	function checkS_email(control) {
		if (control.value == "") {
			validatePrompt(control, "請填寫Email！");
			return (false);
		}
		return (true);
	}
	function checkTopic(control) {
		if (control.value == "") {
			validatePrompt(control, "請填寫活動主題！");
			return (false);
		}
		return (true);
	}
	function checkAddress(control) {
		if (control.value == "") {
			validatePrompt(control, "請填寫活動地址！");
			return (false);
		}
		return (true);
	}
	function checkCost(control) {
		if (control.value == "") {
			validatePrompt(control, "請填寫費用！");
			return (false);
		}
		return (true);
	}
	function checkIntroduce(control) {
		if (control.value == "") {
			validatePrompt(control, "請填寫活動介紹！");
			return (false);
		}
		return (true);
	}
	function checkQuota(control) {
		if (control.value == "") {
			validatePrompt(control, "請填寫參加人數！");
			return (false);
		}
		return (true);
	}
	function checkSchedule(control) {
		if (control.value == "") {
			validatePrompt(control, "請填寫活動日程！");
			return (false);
		}
		return (true);
	}
	function checkNotice(control) {
		if (control.value == "") {
			validatePrompt(control, "注意事項必填！");
			return (false);
		}
		return (true);
	}

function validateForm(form) {

	
	if (!checkName(form.username)) return;
	if (!checkS_email(form.s_email)) return;
	if (!checkTopic(form.topic)) return;
	if (!checkAddress(form.address)) return;
	if (!checkCost(form.cost)) return;
	if (!checkIntroduce(form.introduce)) return;
	if (!checkQuota(form.quota)) return;
	if (!checkSchedule(form.schedule)) return;
	if (!checkNotice(form.notice)) return;
	
	alert ("\n全部資料通過驗證！\n表單即將送出！！！");
	document.testform.submit();	// Submit form
}

function validatePrompt(control, promptStr) {
	alert(promptStr);
	control.focus();
	return;
}

</script>
</html>