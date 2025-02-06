<!DOCTYPE HTML>
<html>
<head>
    <title>BUYSELL</title>
</head>
<body>
<h1>BUYSELL</h1><hr>
<h4>Подробная информация о товаре</h4>
<#list images as img>
    <img src="/images/${img.id?c}" height="60px"><br><br>
</#list>

<b> Название  :</b>${product.title}<br><br>
<b> Описание  :</b>${product.description}<br><br>
<b> Цена  :</b>${product.price}<br><br>
<b> Город  :</b>${product.city}<br><br>
<b> Автор :</b><a href="/user/${product.user.id?c}"> ${product.user.name}</a><br><br>


<hr>
<#if user.email??>
<#if user.email == product.user.email>
<form action="/product/delete/${product.id?c}" method="post">
    <input type="hidden" name="_csrf" value="{csrfToken}">
    <input type="submit" value="Удалить товар"/>
</form>
</#if>
</#if>
<hr>
</body>
</html>