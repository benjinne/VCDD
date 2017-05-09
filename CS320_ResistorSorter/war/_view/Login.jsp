<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!-- DISCLAIMER 
All work seen in here has been copied, but modified from the Library example -->

<html>
	<head>
		<link rel="icon" href="_view/images/favicon.ico" type="image/x-icon">
		<title>Login</title>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="google-signin-client_id" content="486265042204-irblv200fagfgejidat6bdnqivrvonha.apps.googleusercontent.com">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<script src="https://apis.google.com/js/platform.js" async defer></script>
		<link href= "_view/css/styles.css" rel= "stylesheet" type= "text/css">
	</head>

	<body>
		<div class="container">
		<div class="row">
	
		  <div class="jumbotron">
		    <h1>Login</h1> 
		    <p>Welcome to The Voltage Current Divider Divider</p>
		    <p>Now introducing... Sign in, with your Google account: </p><div class="g-signin2" data-onsuccess="onSignIn"></div> 
		    <form id="loginForm" action="${pageContext.servletContext.contextPath}/Login" method="post">
		    <input class="btn btn-primary" type="Submit" name="login" value="Login">
		    <input type="hidden" name="email" id="sendId"/>
		    </form>
		  </div>
	    	<c:if test="${! empty errorMessage}">
				<div class="alert alert-danger alert-dismissable fade in">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					<strong>Error: </strong>${errorMessage}
				</div>
			</c:if>
			</div>
		</div>
		
	</body>

<script type='text/javascript'>
function onSignIn(googleUser) {
  var profile = googleUser.getBasicProfile();
  console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
  console.log('Name: ' + profile.getName());
  console.log('Image URL: ' + profile.getImageUrl());
  console.log('Email: ' + profile.getEmail()); // This is null if the 'email' scope is not present.
  
  var anchor = document.getElementById("sendId");
  var att = document.createAttribute("value");
  att.value = profile.getEmail();
  anchor.setAttributeNode(att);
  //document.forms["loginForm"].submit();
} 

function signOut() {
    var auth2 = gapi.auth2.getAuthInstance();
    auth2.signOut().then(function () {
      console.log('User signed out.');
    });
  }

</script>
</html>