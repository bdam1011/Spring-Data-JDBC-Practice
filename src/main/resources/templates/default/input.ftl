<#ftl output_format="HTML" />
<#import "/spring.ftl" as spring />
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
</head>
<body>
<#if teacher.id??>
    <#assign action="/update" />
<#else>
    <#assign action="/add" />
</#if>
<form action="<@spring.url action />" method="post">
    <label for="id">id : </label><input name="id" value="${teacher.id}" <#if teacher.id??>readonly</#if> /><br/>
    <label for="name">name : </label><@spring.formInput path="teacher.name" />
    <button>submit</button>
</form>
</body>
</html>