<#ftl output_format="HTML" />
<#import "/spring.ftl" as spring />
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <title>This is the input page.</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script>
        $(function () {
            $("#log").click(function () {
                $("input").each(function () {
                    console.log($(this).attr("name") + " : " + $(this).val());
                });
            })
        });

    </script>
</head>
<body>
<#if student.id??>
    <#assign action="/update" />
<#else>
    <#assign action="/add" />
</#if>
<form action="<@spring.url action />" method="post">
    <label for="id">id : </label>
    <input name="id" value="${student.id!}" <#if (student.id)??>readonly</#if> /><br/>
    <label for="name">name : </label><@spring.formInput path="student.name" /><br>
    <label for="admissionDate">admission date : </label>
    <input name="admissionDate" type="date" value="${student.admissionDate!}"/><br>
    <label for="teacherId">teacher id : </label><@spring.formInput path="student.teacherId" /><br>
    <button>submit</button>
    <button id="log" type="button">Log Information</button>
</form>
</body>
</html>