<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.css">
    <c:url value="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.css" var="dataCss"/>
    <link href="{dataCss}" rel="stylesheet" />
    <c:url value="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.js" var="dataJs"/>
    <script src="{dataJs}"></script>
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.js"></script>
</head>
<body>

<%@ include file="newRecordTableHtml.html" %>

<div class="container">
    <table id="Record" class="table table-striped table-bordered" style="width: auto;">

        <thead>
        <tr>
            <th>Customer ID</th>
            <th>Customer Name</th>
            <th>Email Address</th>
            <th>Contact No</th>
            <th>Primary Contact Person</th>
            <th>Record Status</th>
            <th>Active</th>
            <c:if test = "${entity=='maker'}">
               <th></th>
            </c:if>

        </tr>
        </thead>
        <tbody>
        <c:forEach items ="${customerList}" var="customer">
            <tr>
                <td><c:out value="${customer.customerCode}"/></td>
                <td><c:out value="${customer.customerName}"/></td>
                <td><c:out value="${customer.emailAdd}"/></td>
                <td><c:out value="${customer.contactNo}"/></td>
                <td><c:out value="${customer.primaryContactPerson}"/></td>
                <td><c:out value="${customer.recordStatus}"/></td>
                <td><c:out value="${customer.activeInactiveFlag}"/></td>
                 <c:if test = "${entity=='maker'}">
                       <form:form action="/makerCheckerActions" method="post">
                            <th> <button type="submit" name = "btn" value="modify">Modify</button></th>
                            <th><button type="submit" name="btn" value="delete">Delete</button></th>
                        </form:form>
                  </c:if>

            </tr>
        </c:forEach>
        </tbody>

    </table>
</div>


</body>
</html>