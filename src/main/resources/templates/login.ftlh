<!DOCTYPE html>
<html>
<head>
    <title>BUYSELL</title>
</head>

<body>
<h1>BUYSELL</h1><hr>
<h4>Авторизация</h4>
<form action = "/login" method="post">
    Эл.почта: <label>
        <input type="email" name="username">
    </label><br><br>
    Пароль: <label>
        <input type="password" name="password">
    </label><br><br>
    <input type="hidden" name="_csrf" value="{csrfToken}">
    <input type="submit" value="Вход"/>

</form>
<a href="/registration">Зарегистрироваться</a>
</body>
</html>