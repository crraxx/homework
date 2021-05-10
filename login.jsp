<%--
  Created by IntelliJ IDEA.
  User: 17509
  Date: 2021/3/31
  Time: 10:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="js/jquery.1.8.3.min.js"></script>
    <script>
        $(function () {
            var arr = document.cookie.split(";");
            for (let i = 0; i < arr.length; i++) {
                var str = arr[i];
                var strarr = str.split("=");
                if (strarr[0] == "acccok") {
                    $("#acountId").val(strarr[1]);
                }
                if (strarr[0].trim() == "pswck") {
                    $("#passwordId").val(strarr[1]);
                }
            }
        })
    </script>
</head>
<body>

<form action="login " method="post">
    账户:<input type="text" name="account" value="" id="acountId"><br>
    密码:<input type="password" name="password" value="" id="passwordId"> <br>
    记住密码:<input type="checkbox" name="ck" value="ck"><br>
    <input type="submit" value="登录">
</form>
</body>
</html>
