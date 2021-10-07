<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
response.setContentType("text/html;charset=UTF-8");
response.setHeader("Cache-Control","no-cache"); // HTTP 1.1
response.setHeader("Pragma","no-cache"); // HTTP 1.0
response.setDateHeader ("Expires", -1); // Prevents caching at the proxy server
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   <link rel="stylesheet" href="./yu.css">
    <title>New A Restaurant</title>
</head>

<body>
    <header>
        <h1>建立餐廳</h1>
    </header>
    <form action="./NewARestaurantServlet" method="get" enctype="multipart/form-data">
    <!-- #之後要改成sevrlet路徑 -->
    
        <br>
 
        <fieldset>
            <legend>基本資料<em>(必填)</em></legend>

            <!-- restaurant縮寫rst -->
            <div class="st1">
                <label for="username">經營者姓名：</label>
                <input type="text" id="username" value="" size="10" name="name" required>
                <span id="usernamesp"></span><br>
                <span class="ps">(1.必填 2.至少兩個字 3.必為中文)</span>
            </div>

            <div class="st1">
                <label for="">經營者電話：</label>
                <input type="text" id="mobile" onkeyup="value=value.replace(/[^\d]/g,'')" maxlength=12 name="mobile" required></input>
                <span id="mobsp"></span>
                <span class="ps">(必填)</span>
            </div>

            <div class="st1">
                <label for="rstname">餐廳名稱：</label>
                <input type="text" id="rstname" value="" size="10" name="rstname" required>
                <span id="rstnamesp"></span>
                <span class="ps">(必填)</span>
            </div>
 
            <div class="st1">
                <label for="license">證照號碼：</label>
                <input type="text" id="license" value="" size="20" name="license" required>
                <span id="licensesp"></span>
                <span class="ps">(必填)</span>
            </div> 

           <!-- <div class="st1">
                <label class="t1">執照照片:</label>
                <input type="file" accept="image/*" name="file" multiple>
            </div>-->

            <div class="st1">
                <label for="rstaddress">餐廳地址：</label>
                <input type="text" id="rstaddress" value="" size="50" name="rstaddress" required>
                <span id="rstaddsp"></span><br>
                <span class="ps">(1.必填 2.必為中文 3.至少8個字)</span>
            </div>

            <div class="st1">
                <label for="">餐廳電話：</label>
                <input type="text" id="telephone" onkeyup="value=value.replace(/[^\d]/g,'')" maxlength=12 name="rsttel" required></input>
                <span id="rsttelsp"></span>
                <span class="ps">(必填)</span>

            </div>

            <div class="st1">
                <label for="">餐廳類型：</label>
                <input type="text" id="type" value="" size=20 name="type">
                <!-- <select name="type" size="1">
                    <option value="IT">義式(義大利麵、披薩、焗烤.....等)</option>
                    <option value="TW">台式(牛肉麵、蚵仔煎、大腸麵線、炸醬麵.....等)</option>
                    <option value="CN">中式(粵菜、川菜、東北菜.....等</option>
                    <option value="JPN">日式(拉麵、壽司、蓋飯、燒肉.....等)</option>
                    <option value="USA">美式(漢堡、炸雞薯條、三明治.....等)</option>
                    <option value="KR">韓式(拌飯、炒年糕.....等)</option>
                    <option value="VN">越式(米線、河粉.....等)</option>
                    <option value="Others">其他</option>
                </select> -->
                <span class="ps">(日式、中式、義式、韓式、台式、美式...etc.)</span>
            </div>

            <div class="st1">
                <label for="">營業時間<span class="ps">(請以24小時制填寫)：</span></label>
                <input type="text" id="businesshour" value="" size="20" name="businesshour">
                
                
            </div>



        </fieldset>
        
        <div class="sub">
            <input type="submit" name="submit" style="width:200px;height:30px; font-size: medium;" value="下一步">            
            <input type="reset" style="width:200px;height:30px; font-size: medium;" value="清除">
        </div>
    </form>

 

    <script>
        //餐廳經營者
        document.getElementById("username").addEventListener("blur", checkName);
        function checkName() {
            let theNameObj = document.getElementById("username");
            let theNameObjVal = theNameObj.value;
            let nsp = document.getElementById("usernamesp");
            let theNameObjValLen = theNameObjVal.length;
            let f1 = false;

            if (theNameObjVal == "")
                nsp.innerHTML = "<img src='Images/error.png'>不可為空白";
            else if (theNameObjValLen >= 2) {

                for (let j = 0; j < theNameObjValLen; j++) {
                    ch = theNameObjVal.charCodeAt(j);
                    console.log(ch); //10進制
                    if (ch >= 0x4e00 && ch <= 0x9ffff) { //中文unicode
                        console.log(true);
                        f1 = true;
                    }
                    else {
                        console.log(false)
                        f1 = false;
                        break;
                    }
                }
                if (f1)
                    nsp.innerHTML = "<img src='Images/correct.png'>正確"
                else
                    nsp.innerHTML = "<img src='Images/error.png'>只能有中文";
            }
            else
                nsp.innerHTML = "<img src='Images/error.png'>至少2個字";

        }
    </script>

    <script>
        //經營者電話
        document.getElementById("mobile").addEventListener("blur", checkName);
        function checkName() {
            let theMobObj = document.getElementById("mobile");
            let theMobObjVal = theMobObj.value;
            let mobsp = document.getElementById("mobsp");
            let theMobObjValLen = theMobObjVal.length;
            let f1 = false;

            if (theMobObjVal == "")
                mobsp.innerHTML = "<img src='Images/error.png'>不可為空白";
        }

    </script>

    <script>
        //餐廳名稱
        document.getElementById("rstname").addEventListener("blur", checkName);
        function checkName() {
            let theRstNameObj = document.getElementById("rstname");
            let theRstNameObjVal = theRstNameObj.value;
            let rnsp = document.getElementById("rstnamesp");
            let theRstNameObjValLen = theRstNameObjVal.length;
            let f1 = false;

            if (theRstNameObjVal == "")
                rnsp.innerHTML = "<img src='Images/error.png'>不可為空白";
            else if (theRstNameObjValLen >= 1) {
                f1 = true;
            }
            if (f1)
                rnsp.innerHTML = "<img src='Images/correct.png'>正確"
        }
    </script>

    <script>
        //證照號碼
        document.getElementById("license").addEventListener("blur", checkName);
        function checkName() {
            let theLicObj = document.getElementById("license");
            let theLicObjVal = theLicObj.value;
            let licsp = document.getElementById("licensesp");
            let theLicObjValLen = theLicObjVal.length;
            let f1 = false;

            if (theLicObjVal == "")
                licsp.innerHTML = "<img src='Images/error.png'>不可為空白";
            // else if (theLicObjValLen >= 10) {
            //     f1 = true;
            // }
            // if (f1)
            // else {
            //     licsp.innerHTML = "<img src='/Images/correct.png'>正確"
            // }

        }
    </script>

    <script>
        //餐廳地址
        document.getElementById("rstaddress").addEventListener("blur", checkName);
        function checkName() {
            let theRstAddObj = document.getElementById("rstaddress");
            let theRstAddObjVal = theRstAddObj.value;
            let rasp = document.getElementById("rstaddsp");
            let theRstAddObjValLen = theRstAddObjVal.length;
            let f1 = false;

            if (theRstAddObjVal == "")
                rasp.innerHTML = "<img src='Images/error.png'>不可為空白";
            else if (theRstAddObjValLen >= 8) {

                for (let j = 0; j < theRstAddObjValLen; j++) {
                    ch = theRstAddObjVal.charCodeAt(j);
                    console.log(ch); //10進制
                    if (ch >= 0x4e00 && ch <= 0x9ffff) { //中文unicode
                        console.log(true);
                        f1 = true;
                    }
                    else {
                        console.log(false)
                        f1 = false;
                        break;
                    }
                }
                if (f1)
                    rasp.innerHTML = "<img src='/Images/correct.png'>正確"
                else
                    rasp.innerHTML = "<img src='/Images/error.png'>只能有中文";
            }
            else
                rasp.innerHTML = "<img src='/Images/error.png'>至少8個字";

        }
    </script>

    <script>
        //餐廳電話
        document.getElementById("telephone").addEventListener("blur", checkName);
        function checkName() {
            let theTelObj = document.getElementById("telephone");
            let theTelObjVal = theTelObj.value;
            let telsp = document.getElementById("rsttelsp");
            let theTelObjValLen = theTelObjVal.length;
            let f1 = false;

            if (theTelObjVal == "")
                telsp.innerHTML = "<img src='Images/error.png'>不可為空白";

        }

    </script>



</body>
</html>