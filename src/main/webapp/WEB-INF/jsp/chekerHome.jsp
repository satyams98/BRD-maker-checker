<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>  
<%@ page isELIgnored="false" %>
<%@ include file = "checkerHeader.html"%> 
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    
</head>
<body>
<sql:setDataSource var="db" driver="oracle.jdbc.OracleDriver"  
     url="jdbc:oracle:thin:@//localhost:1521/XE"  
     user="c##_brd"  password="password"/>  

<script>
$(document).ready( function () {
    $('#myTable').DataTable();
} );
</script>


</body>
</html>