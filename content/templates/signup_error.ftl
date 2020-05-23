<!doctype html>
<#include "css/bootstrap.min.css">
<#include "css/style.css">

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="img/favicon.ico">

    <title>Signin Template for Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="css/style.css">
</head>

<body class="text-center">
<script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.all.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
    $(document).ready(function () {
        swal('please try again', 'email is already taken !', 'error');
    });
</script>
<form class="form-signin" method="post">
    <img class="mb-4" src="https://getbootstrap.com/assets/brand/bootstrap-solid.svg" alt="" width="72" height="72">
    <h1 class="h3 mb-3 font-weight-normal">Please sign up</h1>
    <label for="inputName" class="sr-only">Name</label>
    <input type="text" id="inputName" class="form-control" name="name" placeholder="Name" required autofocus><br/>
    <label for="inputSurname" class="sr-only">Surname</label>
    <input type="text" id="inputSurname" class="form-control" name="surname" placeholder="Surname" required
           autofocus><br/>
    <label for="inputImage" class="sr-only">Image</label>
    <input type="text" id="inputImage" class="form-control" name="imageUrl" placeholder="Image Url" required
           autofocus><br/>
    <label for="inputEmail" class="sr-only">Email address</label>
    <input type="email" id="inputEmail" class="form-control" name="email" placeholder="Email address" required
           autofocus><br/>
    <label for="inputPassword" class="sr-only">Password</label>
    <input type="password" id="inputPassword" class="form-control" name="password" placeholder="Password" required
           autofocus><br/>
    <label for="inputJob" class="sr-only">Job</label>
    <input type="text" id="inputJob" class="form-control" name="job" placeholder="Job" required><br/>
    <button class="btn btn-lg btn-primary btn-block" type="submit">Sign up</button>
    <a href="/login">Log in</a>
    <p class="mt-5 mb-3 text-muted">&copy; Tinder 2020</p>
</form>
</body>
</html>