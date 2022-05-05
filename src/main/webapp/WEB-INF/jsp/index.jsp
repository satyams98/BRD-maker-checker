<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
     <link rel="stylesheet" href="../../resources/css/login.css">

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
      <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <!-- Login form creation starts-->
</head>

<body>
<c:if test="${not empty msg}">
		<div class="alert alert-${css} alert-dismissible fade show" role="alert">
          <strong>${msg}</strong>
          <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
	</c:if>
	<br/>

<section class="container-fluid">

    <!-- row and justify-content-center class is used to place the form in center -->
    <section class="row justify-content-center">
        <section class="col-12 col-sm-6 col-md-4">
            <form class="form-container" action="/login" method="post">
                <div class="form-group">
                    <h4 class="text-center font-weight-bold"> Login </h4>
                    <label for="InputEmail1">User Name</label>
                    <input type="text" class="form-control" id="InputEmail1" aria-describeby="emailHelp" name="uname" placeholder="Enter email">
                </div>
                <div class="form-group">
                    <label for="InputPassword1">Password</label>
                    <input type="password" class="form-control" id="InputPassword1" name="pass" placeholder="Password">
                </div>

                 <div class="form-check">
                   <input class="form-check-input" type="radio" name="role" id="flexRadioDefault1" value="maker">
                   <label class="form-check-label" for="flexRadioDefault1">
                     Maker
                   </label>
                 </div>
                 <div class="form-check">
                   <input class="form-check-input" type="radio" name="role" id="flexRadioDefault2" value="checker">
                   <label class="form-check-label" for="flexRadioDefault2">
                     Checker
                   </label>
                 </div>

                <button type="submit" class="btn btn-primary btn-block">Sign In</button>

                <div class="footer">
<%--                 <p>Don't have an account? <a href="Register.jsp">Sign Up</a></p> --%>
                </div>

                </div>
            </form>
        </section>
    </section>
</section>
</body>
</html>