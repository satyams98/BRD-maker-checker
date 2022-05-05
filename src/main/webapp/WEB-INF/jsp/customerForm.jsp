<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


</head>
<body>

<c:choose>
  		<c:when test="${role eq 'maker'}">
  			<%@ include file="makerHome.jsp" %>
  		</c:when>
  		<c:otherwise>
  			<%@ include file="checkerHome.jsp" %>
  		</c:otherwise>
  	</c:choose>
  	<br />
	<style>
    .form{
    margin: 0% 10%;
    padding: 2% 5% 5% 5%;
    }
    h1{
    margin: 3% 0 0% 3% ;
    }
    .form-outline{
    margin: 2%;
    }
    #form-btn{
    margin: 1% 0 1% 2.1%
    }

  	</style>

  <c:choose>
  		<c:when test="${newCustomer eq 'false'}">
  			<%@ include file="updateCustomerForm.jsp" %>
  		</c:when>
  		<c:otherwise>
  		<%@ include file="addCustomerForm.jsp" %>
  		</c:otherwise>
  	</c:choose>
</body>
</html>