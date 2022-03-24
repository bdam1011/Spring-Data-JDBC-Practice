<#ftl output_format="HTML" />
<#import "/spring.ftl" as spring />
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="">

<head>
    <title>This is the input page.</title></head>
<body>
<#if teacher.id??>
    <#assign action="/teacher/update" />
<#else>
    <#assign action="/teacher/add" />
</#if>
<form action="<@spring.url action />" method="post">
    <label for="id">id : </label><label>
        <input name="id" value="${teacher.id!}" <#if (teacher.id)??>readonly</#if> />
    </label><br/>
    <label for="name">name : </label><@spring.formInput path="teacher.name" /><br>
    <label for="teaching-score">teaching score : </label><@spring.formInput path="teacher.teachingScore" /><br>
    <#--    <label for="teaching-score">teaching score : </label>-->
    <#--    <input name="teaching-score" value="${teacher.teachingScore!}"/><br>-->
    <button>submit</button>
</form>
</body>
</html>