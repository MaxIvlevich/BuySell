<!DOCTYPE HTML>
<html>
<head>
    <title>BUYSELL</title>
</head>
<body>
<h1>BUYSELL</h1><hr>
<#if user.email??>
<h3> <i>${user.name}</i></h3>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="{csrfToken}">
        <input type="submit" value="Выйти"/>
    </form>
    <#if user.idAdmin()>
        <a href = "/admin"> Панель администратора </a>
    </#if>
<#else>
    <a href = "/login">Войти</a></h1><hr>
</#if>
<h4>Товары со всей России</h4>
<form action="/" method="get">
    Поиск по названию обьявления : <label>
    <input type="text" name="title">
    </label><br>
    <input type="submit" value="Поиск"/>
</form>
<#list products as product>
    <div>
        <p><b>${product.title}</b>${product.price} руб. | <a href="/product/${product.id}">Подробнее...</a></p>
    </div>
    <#else>
    <h3> Товаров нет </h3>
</#list>
<#if user.email??>
<hr>
<h3>
    Создать новый товар
</h3>
<form action="/product/create" method="post" enctype="multipart/form-data">
    Название обьявления: <input type="text" name="title"><br><br>
    Описание обьявления: <input type="text" name="description"><br><br>
    Цена: <input type="number" name="price"><br><br>
    Город: <input type="text" name="city"><br><br>
    Первай фотография: <input type="file" name = "file1"/><br><br>
    Первай фотография: <input type="file" name = "file2"/><br><br>
    Первай фотография: <input type="file" name = "file3"/><br><br>
    <input type="hidden" name="_csrf" value="{csrfToken}">
    <input type="submit" value="Добавить товар"/>
</form>
</#if>
</body>
</html>