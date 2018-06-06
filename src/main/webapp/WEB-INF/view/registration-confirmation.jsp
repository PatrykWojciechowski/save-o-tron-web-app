<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!doctype html>
<html lang="en">
<head>
	<title>Registration successful!</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<!-- Reference Bootstrap files -->
	<link rel="stylesheet"
		 href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	  <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/resources/css/login.css" rel="stylesheet">
</head>
<body>
		<div class="container_fluid" >		
		<div id="loginbox" style="margin-top: 50px;"
			class="mainbox col-md-3 col-md-offset-2 col-sm-6 col-sm-offset-2 col-xs-10 col-xs-offset-1">
			<div class="panel panel-default panel-transparent">
				<div class="panel-heading">
					<div class="panel-title">Sign In</div>
				</div>
				<div style="padding-top: 30px" class="panel-body">
					<div class="alert alert-success">
					<i class="glyphicon glyphicon-ok"></i> Registration successful!
		  			</div>
				</div>
			</div>
			<p>
				<a href="${pageContext.request.contextPath}/loginPage" 
				class="btn btn-default" role="button" aria-pressed="true">Login with username and password</a>
			</p>
			<p>
				<a href="${pageContext.request.contextPath}/" 
				class="btn btn-default" role="button" aria-pressed="true">Back to Home Page</a>
			</p>
		</div>
	</div>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
	<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>