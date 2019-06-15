<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Jekyll v3.8.5">
    <title>CoZa Sign In</title>

 <link href="https://fonts.googleapis.com/css?family=Ramabhadra&display=swap" rel="stylesheet">
	<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js" integrity="sha384-xrRywqdh3PHs8keKZN+8zzc5TX0GRTLCcmivcbNJWm2rs5C8PRhcEn3czEjhAO9o" crossorigin="anonymous"></script>
  	<jsp:include page="bootstraplogin/signin.jsp"></jsp:include>
  </head>
  <body class="text-center">
    <form class="form-signin" action="LoginController" method="POST">
    
   <img src="images/icons/logo-01.png" alt="IMG-LOGO" class="imglogo" onclick="location.href='CustomerController'">
  

  <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
  
  <c:if test="${message != null}">
    			<div class="alert alert-danger" role="alert">
					<c:out value="${message }"></c:out>
				</div>
</c:if>
  <c:if test="${messageSuccess != null}">
    			<div class="alert alert-success" role="alert">
					<c:out value="${messageSuccess }"></c:out>
				</div>
</c:if>
  <label for="inputAccountName" class="sr-only">Email address</label>
  
  <input type="text" id="inputAccountName" name="accountname" class="form-control" placeholder="Your Email" required autofocus>
  
  <label for="inputPassword" class="sr-only">Password</label>
  
  <input type="password" id="inputPassword" name="password" class="form-control" placeholder="Password" required>
  
  <div class="checkbox mb-3">
    <label>
      <input type="checkbox" value="remember-me"> Remember me
    </label>
  </div>
  
  
  <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
  
  
  <p class="mt-5 mb-3 text-muted">&copy;CoZa Team 2018-2019</p>
</form>
</body>
</html>
