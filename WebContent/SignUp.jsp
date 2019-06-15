<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>CoZa Sign Up</title>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<jsp:include page="cssSignUp.jsp"></jsp:include>
</head>
<body>
<div class="register">
                <div class="row">
                    <div class="col-md-3 register-left">
                        <img src="https://image.ibb.co/n7oTvU/logo_white.png" alt=""/>
                        <h3>Welcome</h3>
                        <p>We have what of the times and you have all of ours !</p>
<c:if test="${idEmployee == null}">
  <input type="button" name="" onclick="location.href='Login.jsp'"  value="Login"/><br/>
</c:if>
                      
                    </div>
                    <div class="col-md-9 register-right">
                        <div class="tab-content" id="myTabContent">
                            <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">                         		
 <c:if test="${idEmployee == null}">
   <img src="images/icons/logo-01.png" alt="IMG-LOGO" class="imglogo" onclick="location.href='CustomerController'">
					<form action="CustomerController" method="POST">
</c:if>
				<c:if test="${idEmployee != null}">
				  <img src="images/icons/logo-01.png" alt="IMG-LOGO" class="imglogo" onclick="location.href='AdminController'">
					<form action="AdminController" method="POST">
					<input type="hidden" name="page" value="signUpAdmin">
</c:if>
                               		
                               		
                               		  
                                <div class="row register-form">
                                <c:if test="${message != null}">
    			<div id="messageeror" class="alert alert-danger" role="alert">
					<c:out value="${message }"></c:out>
				</div>
</c:if>
                                <c:if test="${messageSuccess != null}">
    			<div id="messageeror" class="alert alert-success" role="alert">
					<c:out value="${messageSuccess }"></c:out>
				</div>
</c:if>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <input type="text" class="form-control" minlength="10" placeholder="Full Name *" name="fullname" required autofocus/>
                                        </div>
                                        <div class="form-group">
                                            <input type="text" class="form-control" placeholder="Address *" name="address" required="required"/>
                                        </div>
                                        <div class="form-group">
                                            <input type="password" class="form-control" placeholder="Password *" name="password" required="required" />
                                        </div>
                                        <div class="form-group">
                                            <input type="password" class="form-control"  placeholder="Confirm Password *" name="passwordagain" required="required" />
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <input type="email" class="form-control" placeholder="Your Email *" name="email" required="required" />
                                        </div>
                                        <div class="form-group">
                                            <input type="text" minlength="10" maxlength="10" name="phonenumber" class="form-control" placeholder="Your Phone *" required="required" />
                                        </div>
                                        <div class="form-group">
                                            <select class="form-control">
                                                <option class="hidden"  selected disabled>Please select your Sequrity Question</option>
                                                <option>What is your Birthdate?</option>
                                                <option>What is Your old Phone Number</option>
                                                <option>What is your Pet Name?</option>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <input type="text" class="form-control" placeholder="Enter Your Answer"  />
                                        </div>
                                        <input type="submit" class="btnRegister"  value="Register"/>
                                    </div>
                                </div>
                                		</form>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
</body>
</html>