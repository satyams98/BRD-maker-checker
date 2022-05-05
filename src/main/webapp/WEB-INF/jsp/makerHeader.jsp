<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Maker</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</head>


<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Maker</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link " aria-current="page" href="/newRecords" id ="new">New/Modified Records</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/authorizedRecords" id ="auth">Authorized Records</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="/rejectedRecords" id = "rejected">Rejected Records</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/deletedRecords" id = "deleted">Delete Request Records</a>
                </li>
                 <li class="nav-item">
                <a class="btn btn-outline-success" href="/add" id = "add">Create Record</a>
                </li>
            </ul>
            <form class="d-flex" action ="/logoutMaker">
                <button class="btn btn-outline-warning" type="submit">Log Out</button>
            </form>
        </div>
    </div>
</nav>
</br>
</body>
</html>