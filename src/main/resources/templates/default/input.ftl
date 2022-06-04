<#ftl output_format="HTML" />
<#import "/spring.ftl" as spring />
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <title>This is the input page.</title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <script>
        $(function () {
            $('#log').click(function () {
                $('input').each(function () {
                    console.log($(this).attr('name') + " : " + $(this).val());
                });
            });

            $('#fill').click(function () {
                console.log($('#query'));
                $.getJSON('/get-one/' + $('#query').val(), function (data) {
                    console.log(data);
                    $('input[name="id"]').val(data.id);
                    $('input[name="name"]').val(data.name);
                    $('input[name="admissionDate"]').val(data.admissionDate);
                    $('input[name="teacherId"]').val(data.teacherId);

                });
            });

            $('#query-all').click(function () {
                $.getJSON('/get-all', function (data) {
                    console.log(data);
                    $('.modal-body').append('<table>');
                    $('table').append('<tr><th>Id</th><th>Name</th>' +
                        '<th>Admission Date</th><th>Update time</th>' +
                        '<th>Teacher ID</th></tr>').addClass('student-table');

                    for (var student of data) {
                        $('.student-table').append($('<tr></tr>').append(
                            $('<td></td>').text(student.id),
                            $('<td></td>').text(student.name),
                            $('<td></td>').text(student.admissionDate),
                            $('<td></td>').text(student.updateTime),
                            $('<td></td>').text(student.teacherId)))
                    }
                    $('td').css('text-align', 'center');
                    $('table,th,td').css('border', '1px solid')

                });
            });
        });


    </script>
</head>
<body>
<#if student.id??>
    <#assign action="/update" />
<#else>
    <#assign action="/add" />
</#if>
<h2>Student</h2>
<form class="form-horizontal" action="<@spring.url action />" method="post">
    <div class="form-group">
        <label class="control-label col-sm-2" for="id">id : </label>
        <div class="col-sm-5">
            <input class="form-control" placeholder="id"
                   name="id" value="${student.id!}"
                   <#if (student.id)??>readonly</#if> />
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="name">name : </label>
        <div class="col-sm-5">
            <@spring.formInput path="student.name" attributes='class="form-control"'/>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="admissionDate">Admission Date : </label>
        <div class="col-sm-5">
            <input class="form-control" name="admissionDate" type="date"
                   value="${student.admissionDate!}"/>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="teacherId">Teacher ID : </label>
        <div class="col-sm-5">
            <@spring.formInput path="student.teacherId" attributes='class="form-control"' />
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-success">Submit</button>
            <button id="log" type="button" class="btn btn-default">
                Log Information
            </button>
        </div>
    </div>
</form>

<label class="control-label col-sm-2 " for="query-id">Fill With ID : </label>
<div class="col-sm-5">
    <input class="form-control" id="query" name="query-id"/>
</div>
<button class="btn btn-primary" id="fill" type="button">Fill in</button>
<br>

<!-- Trigger the modal with a button -->
<button type="button" id="query-all" class="btn btn-info btn-lg"
        data-toggle="modal" data-target="#myModal">Query ALL
</button>

<div class="container">
    <!-- Modal -->
    <div class="modal fade" id="myModal" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"/>
                    <h4 class="modal-title">Information of Students</h4>
                </div>
                <div class="modal-body">

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default"
                            data-dismiss="modal">Close
                    </button>
                </div>
            </div>

        </div>
    </div>

</div>

</body>
</html>