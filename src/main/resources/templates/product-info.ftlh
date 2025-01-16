<!DOCTYPE HTML>
<html>
<head>
    <title>BUYSELL</title>
</head>
<body>
<h1>BUYSELL</h1><hr>
<h4>Подробная информация о товаре</h4>
<#list images as img>
    <img src="/images/${img.id}" height="60px"><br><br>
</#list>

<b> Название  :</b>${product.title}<br><br>
<b> Описание  :</b>${product.description}<br><br>
<b> Цена  :</b>${product.price}<br><br>
<b> Город  :</b>${product.city}<br><br>
<b> Автор :</b>${product.author}<br><br>


<hr>

    <form action="/product/delete/${product.id}" method="post">
    <input type="submit" value="Удалить товар"/>
    </form>
<hr>
     <form action="/product/back" method="get">
     <input type="submit" value="Вернуться"/>
     </form>

</body>
</html>