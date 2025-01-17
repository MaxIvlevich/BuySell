<html>
<head>
    <title>BUYSELL</title>
</head>

<body>
<h1>BUYSELL</h1><hr>
<h4>Регистрация</h4>

<form action="/regisration" method="post">
    Имя пользователя: <input type="text" name = "name"><hr><hr>
    Эл.почта: <input type="email" name = "email"><hr><hr>
    Номер телефона: <input type="text" name = "phoneNumber"><hr><hr>
    Пароль: <input type="password" name = "password"><hr><hr>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Зарегистрироваться"/>

</form>
<#if errorMessasge??>
    <h2 style = "color: red">${errorMessage}</h2>

</#if>
</body>
</html>