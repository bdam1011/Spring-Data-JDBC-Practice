<#ftl output_format="HTML" />
<#import "/spring.ftl" as spring />
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="">

<head>
    <title>This is the input page.</title></head>
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
<body>
<#if course.id??>
    <#assign action="/course/update" />
<#else>
    <#assign action="/course/add" />
</#if>
<form action="<@spring.url action />" method="post">
    <label for="id">id : </label>
    <input name="id" value="${course.id!}" <#if (course.id)??>readonly</#if> /><br/>
    <label for="name">name : </label><@spring.formInput path="course.name" /><br>
    <label for="fee">fee : </label><@spring.formInput path="course.fee" /><br>
    <button>submit</button>
    <button id="log" type="button">Log Information</button>
</form>
</body>
</html>