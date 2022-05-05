<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Checker</title>
    <script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
   <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.css">
   <link type="text/css" href="/resources/css/bootstrap.css" rel = 'stylesheet' />
       <script src="/resources/js/bootstrap.bundle.js"></script>
  <script type="text/javascript" src="../../resources/js/responsive.js"></script>
 <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.js"></script>
 </head>
<body>
  <nav class="navbar navbar-expand-lg navbar-light bg-light">
      <div class="container-fluid">
          <a class="navbar-brand" href="#">Checker</a>
          <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
              <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse" id="navbarSupportedContent">
              <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                  <li class="nav-item">
                      <a class="nav-link " aria-current="page" href="/newRecords" id ="new">Approve Pending</a>
                  </li>
                  <li class="nav-item">
                      <a class="nav-link" href="/authorizedRecords" id ="auth">Authorized Records</a>
                  </li>
                  <li class="nav-item">
                      <a class="nav-link" aria-current="page" href="/rejectedRecords" id = "rejected">Rejected Records</a>
                  </li>
                  <li class="nav-item">
                      <a class="nav-link" href="/deletedRecords" id = "deleted">Delete Pending</a>
                  </li>

              </ul>
              <form class="d-flex" action = "/logoutChecker">
                  <button class="btn btn-outline-success" type="submit">Log Out</button>
              </form>
          </div>
      </div>
  </nav>
  </br>

</body>
</html>