<#ftl output_format="HTML" />
<#import "/spring.ftl" as spring />
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <title>This is the input page.</title></head>
<body>
<#if student.id??>
    <#assign action="/update" />
<#else>
    <#assign action="/add" />
</#if>
<form action="<@spring.url action />" method="post">
    <label for="id">id : </label>
    <label>
        <input name="id" value="${student.id!}" <#if (student.id)??>readonly</#if> />
    </label><br/>
    <label for="name">name : </label><@spring.formInput path="student.name" /><br>

    <label for="admission-date">admission date : </label>
    <input name="admission-date" type="date" value="${student.admissionDate!}"/><br>

    <label for="teacher-id">teacher id : </label><@spring.formInput path="student.teacherId" /><br>
    <button>submit</button>
</form>
</body>
</html>