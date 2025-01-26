<!DOCTYPE HTML>
<html>
<head>
    <title>BUYSELL</title>
</head>
<body>
<h1>BUYSELL</h1><hr>
<h4>Панель администратора</h4>
<table border="1">
    <tr>
        <th> Имя пользователя </th>
        <th> Email </th>
        <th> Номер телефона  </th>
        <th> Активность </th>
        <th> Роли </th>
        <th> Бан </th>
        <th> Редактирование </th>
        <th> Подробная информация </th>
    </tr>

    <#list users as user>
        <tr>
            <th> ${user.name} </th>
            <th> ${user.email} </th>
            <th> ${user.phoneNumber} </th>
            <th><#if user.active>true<#else>false</#if></th>
            <th> <#list  user.roles as role>${role} </#list></th>
            <th>
                <form action ="/admin/user/ban/${user.id}" method="post">
                    <input type="hidden" name="_csrf" value="{csrfToken}">
                    <#if user.isActive()>
                        <input type="submit" value="Бан">
                        <#else ><input type="submit" value="Разбанить">
                    </#if>

                </form>
            </th>
            <th><a href="/admin/user/edit/${user.id}">Редактироавние</a> </th>
            <th><a href="/user/${user.id}"> Подробная информация </a></th>
            <th>
                <form action = "/admin/user/delete/${user.id}" method="post">
                    <input type="hidden" name="_csrf" value="{csrfToken}">
                    <input type="submit" value="Удалить">
                </form>
            </th>
        </tr>
        <#else>
        <h3> Пользователей нет</h3>

    </#list>
</table>

</body>
</html>