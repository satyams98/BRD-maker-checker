<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Customer</title>
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

</head>
<body>
<h1>Add User</h1>
  			<br />
  				<div class="form-group form">
              			<form:form action = "/add" method="POST" modelAttribute= "customer">
                          			<!-- 2 column grid layout with text inputs for the first and last names -->
                     <div class="row mb-4">
                       <div class="col">
                         <div class="form-outline">
                           <label class="form-label" for="name">Name</label>
                           <form:input path="customerName" type="text" id="name" class="form-control" placeholder="Name"/>

                         </div>
                       </div>
                       <div class="col">
                         <div class="form-outline">

                           <label class="form-label" for="address1">Address 1</label>
                           <form:input path="add1" type="text" id="address1" class="form-control" placeholder="Address1"/>
                         </div>
                       </div>
                     </div>
                     <div class="col">
                         <div class="form-outline">

                           <label class="form-label" for="address2">Address 2</label>
                           <form:input path = "add2" type="text" id="address2" class="form-control" placeholder="Address2"/>
                        </div>
                       </div>

                     <div class="col">
                         <div class="form-outline">

                           <label class="form-label" for="pinCode">Pin Code</label>
                           <form:input path="pinCode" type="text" id="pinCode" class="form-control" placeholder="Pin Code" value=""/>
                         </div>
                       </div>


                     <!-- Email input -->
                     <div class="form-outline mb-4">

                       <label class="form-label" for="email">Email address</label>
                       <form:input path="emailAdd" type="email" id="email" class="form-control" placeholder="name@example.com" />
                     </div>
                     <div class="form-outline mb-4">

                         <label class="form-label" for="contact">Contact</label>
                         <form:input path="contactNo" type="text" id="contact" class="form-control" placeholder="Contact"/>
                       </div>
                       <div class="form-outline mb-4">

                         <label class="form-label" for="primaryContactPerson">Primary Contact Person</label>
                         <form:input path="primaryContactPerson" type="text" id="primaryContactPerson" class="form-control" />
                       </div>
                        <input type="hidden" name="newCustomer" value="${newCustomer}"/>
                         <input type="hidden" name="table" value="${table}"/>
                     <!-- Submit button -->
                     <button type="submit" id = "form-btn" class="btn btn-primary btn-block mb-4">Submit</button>
                   </form:form>
                     </div>
        </body>
</html>