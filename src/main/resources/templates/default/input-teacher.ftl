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
        });

        $('#fill').click(function () {
            console.log($('#query'));
            $.getJSON('/teacher/get-one/' + $('#query').val(), function (data) {
                console.log(data);
                $('input[name="id"]').val(data.id);
                $('input[name="name"]').val(data.name);
                $('input[name="teachingScore"]').val(data.teachingScore);
            });
        });

        $('#query-all').click(function () {
            $.getJSON('/teacher/get-all', function (data) {
                console.log(data);
                $('.container').append('<table>');
                $('table').append('<tr><th>Id</th><th>Name</th>' +
                    '<th>Teaching Score</th><th>Update time</th>')
                    .addClass('teacher-table');

                for (var teacher of data) {
                    $('.teacher-table').append($('<tr></tr>').append(
                        $('<td></td>').text(teacher.id),
                        $('<td></td>').text(teacher.name),
                        $('<td></td>').text(teacher.teachingScore),
                        $('<td></td>').text(teacher.updateTime)));
                }
                $('td').css('text-align', 'center');
                $('table,th,td').css('border', '1px solid')


            });
        });
    });

</script>
<body>
<#if teacher.id??>
    <#assign action="/teacher/update" />
<#else>
    <#assign action="/teacher/add" />
</#if>
<form action="<@spring.url action />" method="post">
    <label for="id">id : </label>
    <input name="id" value="${teacher.id!}" <#if (teacher.id)??>readonly</#if> /><br/>
    <label for="name">name : </label><@spring.formInput path="teacher.name" /><br>
    <label for="teachingScore">teaching score : </label><@spring.formInput path="teacher.teachingScore" /><br>
    <button>submit</button>
    <button id="log" type="button">Log Information</button>
</form>
<label for="query-id">Fill With ID : </label><input id="query" name="query-id"/>
<button id="fill" type="button">Fill in</button>
<br>
<button id="query-all" type="button">Query ALL</button>
<div class="container"></div>
<br>
</body>
</html>