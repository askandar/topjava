<%@ page import="ru.javawebinar.topjava.util.TimeUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<link rel="stylesheet" href="webjars/datatables/1.10.11/css/jquery.dataTables.min.css">
<link rel="stylesheet" href="webjars/datetimepicker/2.5.1/jquery.datetimepicker.css">
<section>
    <%--http://stackoverflow.com/questions/10327390/how-should-i-get-root-folder-path-in-jsp-page--%>

    <div class="jumbotron">
        <div class="container">
            <div class="shadow">
                <h3><a href="${pageContext.request.contextPath}">Home</a></h3>
                <h3><fmt:message key="meals.title"/></h3>

                <%--
                                <form method="post" action="meals/filter">
                                    <dl>
                                        <dt>From Date:</dt>
                                        <dd><input type="date" name="startDate" value="${startDate}"></dd>
                                    </dl>
                                    <dl>
                                        <dt>To Date:</dt>
                                        <dd><input type="date" name="endDate" value="${endDate}"></dd>
                                    </dl>
                                    <dl>
                                        <dt>From Time:</dt>
                                        <dd><input type="time" name="startTime" value="${startTime}"></dd>
                                    </dl>
                                    <dl>
                                        <dt>To Time:</dt>
                                        <dd><input type="time" name="endTime" value="${endTime}"></dd>
                                    </dl>
                                    <button type="submit">Filter</button>
                                </form>
                --%>
                <div class="view-box">
                    <form id="filter" role="form" class="form-horizontal" action="/meals" method="post">
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="startDate">From Date:</label>

                            <div class="col-sm-2">
                                <input type="date" name="startDate" id="startDate" class="date-picker">
                            </div>

                            <label class="control-label col-sm-2" for="endDate">To Date:</label>

                            <div class="col-sm-2">
                                <input type="date" name="endDate" id="endDate" class="date-picker">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="startTime">From Time:</label>

                            <div class="col-sm-2">
                                <input type="time" name="startTime" id="startTime" class="time-picker">
                            </div>

                            <label class="control-label col-sm-2" for="endTime">To Time:</label>

                            <div class="col-sm-2">
                                <input type="time" name="endTime" id="endTime" class="time-picker">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-8">
                                <button type="submit" class="btn btn-primary pull-right">Filter</button>
                            </div>
                        </div>
                    </form>

                    <hr>
                    <%-- <a href="meals/create">Add Meal</a>--%>
                    <a class="btn btn-sm btn-info" id="add"><fmt:message key="meals.add"/></a>
                    <hr>
                    <table class="table table-striped display" id="datatable">
                        <thead>
                        <tr>
                            <th>Date</th>
                            <th>Description</th>
                            <th>Calories</th>
                            <th></th>
                            <th></th>
                        </tr>
                        </thead>
                        <c:forEach items="${mealList}" var="meal">
                            <jsp:useBean id="meal" scope="page"
                                         type="ru.javawebinar.topjava.to.UserMealWithExceed"/>
                            <tr class="${meal.exceed ? 'exceeded' : 'normal'}">
                                <td>
                                        <%--${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}--%>
                                    <%=TimeUtil.toString(meal.getDateTime())%>
                                </td>
                                <td>${meal.description}</td>
                                <td>${meal.calories}</td>
                                    <%--
                                                    <td><a href="meals/update?id=${meal.id}">Update</a></td>
                                                    <td><a href="meals/delete?id=${meal.id}">Delete</a></td>
                                                    --%>
                                <td><a class="btn btn-xs btn-primary edit" id="${meal.id}">Edit</a></td>
                                <td><a a class="btn btn-xs btn-danger delete" id="${meal.id}">Delete</a></td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>
    </div>
</section>


<div class="modal fade" id="editRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title"><fmt:message key="meals.edit"/></h2>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" method="post" id="detailsForm">
                    <input type="hidden" id="id" name="id">

                    <div class="form-group">
                        <label for="datetime" class="control-label col-xs-3">Date</label>

                        <div class="col-xs-9">
                            <input type="datetime-local" class="form-control" id="datetime"
                                   name="datetime" placeholder="Date">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="description" class="control-label col-xs-3">Description</label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="description" name="description"
                                   placeholder="Description">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="calories" class="control-label col-xs-3">Calories</label>

                        <div class="col-xs-9">
                            <input type="number" class="form-control" id="calories" name="calories"
                                   placeholder="2000">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-9">
                            <button type="submit" class="btn btn-primary">Save</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>


<%--<td><fmt:formatDate value="${user.registered}" pattern="dd-MMMM-yyyy"/></td>
<td><a class="btn btn-xs btn-primary edit" id="${user.id}">Edit</a></td>
<td><a class="btn btn-xs btn-danger delete" id="${user.id}">Delete</a></td>--%>
<jsp:include page="fragments/footer.jsp"/>
</body>
<script type="text/javascript" src="webjars/jquery/2.2.3/jquery.min.js"></script>
<script type="text/javascript" src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script type="text/javascript" src="webjars/datetimepicker/2.5.1/jquery.datetimepicker.js"></script>
<script type="text/javascript" src="webjars/datatables/1.10.11/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="webjars/noty/2.3.8/js/noty/packaged/jquery.noty.packaged.min.js"></script>
<%--<script type="text/javascript" src="resources/js/datatablesMealUtils.js"></script>--%>
<script type="text/javascript" src="resources/js/datatablesUtil.js"></script>
<script type="text/javascript">

    var ajaxUrl = '/ajax/profile/meals/';
    var datatableApi;
    var oTable_datatable;
    var oTable_datatable_params;

    function updateTable(){
        $.ajax({
            type: "POST",
            url: ajaxUrl + 'filter',
            data: $('#filter').serialize(),
            success: function (data){
                updateTableByData(data);
            }
        });
        return false;
    }

    $(function () {
        datatableApi = $('#datatable').dataTable(
                {
                    "bPaginate": false,
                    "bInfo": true,
                    "aoColumns": [
                        {
                            "mData": "dateTime"
                        },
                        {
                            "mData": "description"
                        },
                        {
                            "mData": "calories"
                        },
                        {
                            "sDefaultContent": "Edit",
                            "bSortable": false
                        },
                        {
                            "sDefaultContent": "Delete",
                            "bSortable": false
                        }
                    ],
                    "aaSorting": [
                        [
                            0,
                            "desc"
                        ]
                    ]
                });

        $('#filter').submit(function () {
            updateTable();
            return false;
        });
        makeEditable();
    });


</script>
</html>
