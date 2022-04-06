<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta content="301645777112-2rlc9gth0f5d4reimjcm9bf0kj7ahec0.apps.googleusercontent.com"
          name="google-signin-client_id">
    <title>Login / Register</title>
    <link href="https://fonts.googleapis.com" rel="preconnect">
    <link crossorigin href="https://fonts.gstatic.com" rel="preconnect">
    <link
            href="https://fonts.googleapis.com/css2?family=Lobster&family=Roboto:wght@300&display=swap"
            rel="stylesheet">
    <script crossorigin="anonymous"
            src="https://kit.fontawesome.com/3204349982.js"></script>
    <script async defer src="https://apis.google.com/js/platform.js"></script>
    <link href="index.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Roboto"
          rel="stylesheet" type="text/css">
    <script src="https://apis.google.com/js/api:client.js"></script>

</head>
<body>
<!-- TODO -->
<header>
  		<nav>
  			<a href="index.jsp" class="no-style" id="logo">SalEats!</a>
  			<span id="buttons">
	  			<a href="index.jsp" class="no-style" id="home">Home</a>
	  			<a href="auth.jsp" class="no-style">Login / Register</a>
  			</span>
  		</nav>
  	</header>
  	<main>
  		<div class="login-form-container">
  			<div>
	  			<form class="login-form" action="LoginDispatcher" method="GET">
	  				<div>
		  				<h1 class="form-header">Login</h1>
		  			</div>
	  				<label>Email</label>
	  				<input type="email" class="textbox" name="email" required>
	  				<label>Password</label>
	  				<input type="password" class="textbox" name="password" autoComplete="current-password" required pattern="\S(.*\S)?">
	  				<button type="submit" name="login" class="login-button"><i class="fas fa-sign-in-alt"></i>Sign In</button>
	  			</form>
				<div class="g-signin2" data-onsuccess="onSignIn" data-width="425" data-height="40" data-longtitle="true" data-theme="dark"></div>
  			</div>
  			<form class="login-form" action="RegisterDispatcher" method="GET">
	  				<div>
		  				<h1 class="form-header">Register</h1>
		  			</div>
  				<label>Email</label>
  				<input type="text" class="textbox" name="user_email" required>
  				<label>Name</label>
  				<input type="text" class="textbox" name="user_name" required pattern="\S(.*\S)?">
  				<label>Password</label>
  				<input type="password" class="textbox" name="user_password" autoComplete="new-password" required pattern="\S(.*\S)?">
  				<label>Confirm Password</label>
  				<input type="password" class="textbox" name="confirm_password" autoComplete="new-password" required pattern="\S(.*\S)?">
  				<div>
	  				<label class="terms">
	  					<input type="checkbox" name="terms" class="terms" required>
	  					I have read and agree to all terms and conditions of SalEats.
	  				</label>
  				</div>
  				<button type="submit" name="register" class="login-button"><i class="fas fa-user-plus"></i>Create Account</button>
  			</form>
  		</div>
  	</main>
</body>
</html>