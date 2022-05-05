<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.css">
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.js"></script>
</head>

<body>
<%@ include file="newRecordTableHtml.html" %>

 <c:choose>
  		<c:when test="${role eq 'maker'}">
  			<%@ include file="makerHome.jsp" %>
  		</c:when>
  		<c:otherwise>
  			<%@ include file="checkerHome.jsp" %>
  		</c:otherwise>

  	</c:choose>
  	<c:if test="${not empty msg}">
    		<div class="alert alert-${css} alert-dismissible fade show" role="alert">
              <strong>${msg}</strong>
              <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
    	</c:if>

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
            <c:choose>
                <c:when test="${role eq 'maker'}">
                    <th>Update</th>
                    <th>Delete</th>
                </c:when>
                <c:otherwise>
                <c:if test= "${table eq 'N' or table eq 'D'}">
                    <th>Authorize</th>
                    <th>Reject</th>
                 </c:if>
                </c:otherwise>
            </c:choose>
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
                <c:choose>
                    <c:when test="${role eq 'maker'}">
                        <form:form action="/update" method="GET" >
                            <td> <button type="submit" name = "btn" value="update">Modify</button>
                                <input type="hidden" name="table" value="${table}"/>
                                <input id ="customerCode" type="hidden" name="customerCode" value="${customer.customerCode}"/>
                            </td>
                        </form:form>
                        <form:form action="/deleteCustomer" method="post" onSubmit="return confirm('Do you want to
                        delete customer with id: ${customer.customerCode}?') ">
                            <td>
                                <button type="submit" name="btn" value="delete" >Delete</button>
                                <input type="hidden" name="table" value="${table}"/>
                                  <input type="hidden" name="customerCode" value="${customer.customerCode}"/>
                            </td>
                        </form:form>

                    </c:when>
                    <c:otherwise>
                      <c:if test= "${table eq 'N' or table eq 'D'}">
                        <form:form action="/authorize" method="post">
                            <td> <button type="submit" name = "btn" value="authorize">Authorize</button>
                                <input type="hidden" name="table" value="${table}"/>
                                <input type="hidden" name="customerCode" value="${customer.customerCode}"/>
                                <input type="hidden" name="recordStatus" value="${customer.recordStatus}"/>
                            </td>
                        </form:form>
                        <form:form action="/reject" method="post">
                            <td> <button type="submit" name = "btn" value="reject">Reject</button>
                                <input type="hidden" name="table" value="${table}"/>
                                 <input type="hidden" name="customerCode" value="${customer.customerCode}"/>
                            </td>
                        </form:form>
                        </c:if>
                    </c:otherwise>
                </c:choose>
            </tr>
        </c:forEach>
        </tbody>

    </table>
</div>
</body>
</html>