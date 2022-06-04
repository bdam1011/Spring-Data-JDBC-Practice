<#ftl output_format="HTML" />
<#import "/spring.ftl" as spring />
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="">

<head>
    <title>This is the input page.</title></head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
    $(function () {
        $('#log').click(function () {
            $('input').each(function () {
                console.log($(this).attr('name') + " : " + $(this).val());
            });
        });

        $('#fill').click(function () {
            console.log($('#query'));
            $.getJSON('/course/get-one/' + $('#query').val(), function (data) {
                console.log(data);
                $('input[name="id"]').val(data.id);
                $('input[name="name"]').val(data.name);
                $('input[name="fee"]').val(data.fee);

            });
        });

        $('#query-all').click(function () {
            $.getJSON('/course/get-all', function (data) {
                console.log(data);
                $('.container').append('<table>');
                $('table').append('<tr><th>Id</th><th>Name</th>' +
                    '<th>Fee</th><th>Update time</th>')
                    .addClass('course-table');

                for (var teacher of data) {
                    $('.course-table').append($('<tr></tr>').append(
                        $('<td></td>').text(teacher.id),
                        $('<td></td>').text(teacher.name),
                        $('<td></td>').text(teacher.fee),
                        $('<td></td>').text(teacher.updateTime)));
                }
                $('td').css('text-align', 'center');
                $('table,th,td').css('border', '1px solid')


            });
        });
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
<label for="query-id">Fill With ID : </label><input id="query" name="query-id"/>
<button id="fill" type="button">Fill in</button>
<br>
<button id="query-all" type="button">Query ALL</button>
<div class="container"></div>
<br>
</body>
</html>