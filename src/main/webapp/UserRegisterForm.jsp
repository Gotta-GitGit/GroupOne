<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>買家用戶註冊</title>
    <link rel="stylesheet" href=".\registerStyle.css">
    <style>
        
    </style>
</head>

<body>
	 <h1>買家用戶註冊</h1>
	<hr>
	<a href="index.jsp">首頁</a>
	<hr>
	<form method="post" action=".\Register?action=insertUser">
        <fieldset>
            <legend>買家資料一<span id="must">*<em>(必填)</em></span></legend>
            <!--語意標籤<em>會讓文字便鞋體，但不同於font-family屬性的效果-->
            <div class="st1">
                <label for="username1" class="t1"><span id="must">*</span>帳號：</label><input type="text" id="username1"
                    name="username" autofocus required autocomplete="off" size=20>
            </div>
            <div class="st1">
                <label for="password1" class="t1"><span id="must">*</span>密碼：</label><input type="password"
                    id="password1" name="password" required maxlength="20" autocomplete="off">
            </div>
            <div class="st1">
                <label for="fullname1" class="t1"><span id="must">*</span>姓名：</label><input type="text" id="fullname1"
                    name="fullname" placeholder="李小龍" autocomplete="off" size=10>
            </div>
            <div class="st1">
                <label for="" class="t1"><span id="must">*</span>性別：</label>
                <input type="radio" name="gender" value="男" id="m1"><label for="m1">男</label>
                <label>
                    <!--***新作法***將<input>元素放入<label>標籤的做法已經內含<label for="m1">對應<input id="m1">的效果-->
                    <input type="radio" name="gender" value="女">女
                </label>
            </div>
            <div class="st1">
                <label for="" class="t1"><span id="must">*</span>生日：</label>
                <input type="date" id="datefield" name="dob" min="1900-01-01" max="2050-12-31" maxlength="10" required autocomplete="off">
            </div>
            <div class="st1">
                <label for="" class="t1"><span id="must">*</span>Email：</label>
                <input type="email" name="email" required maxlength="20" autocomplete="off">
            </div>
            <div class="st1">
                <label for="phoneNumber1" class="t1"><span id="must">*</span>手機號碼：</label><input type="text"
                    id="phoneNumber1" name="phoneNumber" placeholder="0988888888" required maxlength="10"
                    autocomplete="off" size=10>
            </div>
            <div class="st1">
                <label for="homeNumber1" class="t1">住家電話：</label><input type="text"
                    id="homeNumber1" name="homeNumber" placeholder="0288888888" maxlength="10"
                    autocomplete="off" size=10>
            </div>
            <!-- <div class="st1">
            <label for="" class="t1">身分證字號：</label>
            <input type="text" name="idcode" maxlength="10" pattern="[a-z,A-Z]{1}[1-2,8-9]{1}\d{8}">
        </div> -->
        </fieldset>
        <fieldset>
            <legend>買家資料上傳</legend>
            <div class="st1">
                <label for="" class="t1">個人大頭貼：</label>
                <input type="file" name="file1" accept=".jpg">
                <br>
                <span style="margin: 20px; text-decoration: underline;"><b>(JPG格式)</b></span>
            </div>
        </fieldset>
        <span style="color: red; font-weight: bold;">
		<%=(request.getAttribute("errMessage") == null) ? ""
	         : request.getAttribute("errMessage")%>
		</span>
	    <br>
        <div class="sub">
            <input type="submit" value="送出">
            <input type="reset" value="清除">
            <!-- <input type="reset" value="清除" disabled> -->
        </div>
    </form>
    <script>
		// 設定date的max屬性為today
		/*
		var today = new Date();
		var dd = today.getDate();
		var mm = today.getMonth() + 1; //January is 0!
		var yyyy = today.getFullYear();
	
		if (dd < 10) {
		   dd = '0' + dd;
		}
	
		if (mm < 10) {
		   mm = '0' + mm;
		} 
		    
		today = yyyy + '-' + mm + '-' + dd;
		
		document.getElementById("datefield").setAttribute("max", today);
		*/
		
		// maxDate計算生日對應年紀為18歲以上
		var today = new Date();
		var dd = today.getDate();
		var mm = today.getMonth()+1; //January is 0!
		var yyyy = today.getFullYear()-18;
		 if(dd<10){
		        dd='0'+dd
		    } 
		    if(mm<10){
		        mm='0'+mm
		    } 

		maxDate = yyyy+'-'+mm+'-'+dd;
		document.getElementById("datefield").setAttribute("max", maxDate);

		// minDate計算生日對應年紀為18歲以上
		var today = new Date();
		var dd = today.getDate();
		var mm = today.getMonth()+1; //January is 0!
		var yyyy = today.getFullYear()-100;
		 if(dd<10){
		        dd='0'+dd
		    } 
		    if(mm<10){
		        mm='0'+mm
		    } 

		minDate = yyyy+'-'+mm+'-'+dd;
		document.getElementById("datefield").setAttribute("min", minDate);
	</script>
</body>

</html>