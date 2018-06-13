<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!doctype html>
<html lang="en">
<head>
	<title>Register an account</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<!-- Reference Bootstrap files -->
	<link rel="stylesheet"
		 href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	 <!-- Custom styles for this template   -->
    <link href="${pageContext.request.contextPath}/resources/css/login.css" rel="stylesheet">
</head>
<body>
	<div class="container_fluid" >
		<div id="loginbox" style="margin-top: 50px;"
			class="mainbox col-md-3 col-md-offset-2 col-sm-6 col-sm-offset-2 col-xs-10 col-xs-offset-1">
			<div class="panel panel-default panel-transparent">
				<div class="panel-heading">
					<div class="panel-title">Register New User</div>
				</div>
				<div style="padding-top: 30px" class="panel-body">
					<!-- Registration Form -->
					<form:form action="${pageContext.request.contextPath}/register/process-registration-form" 
						  	   modelAttribute="newUser"
						  	   class="form-horizontal">
						<!-- User name -->
						<div style="margin-bottom: 25px; margin-top: 15px" class="input-group">
							<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span> 
							
							<form:input path="userName" placeholder="username" class="form-control" />
						</div>
						<c:if test="${userAlreadyExistsError != null}">
							<div class="alert alert-danger col-xs-offset-1 col-xs-10">
								${userAlreadyExistsError}
					    	</div>
						</c:if>
						<form:errors path="userName" class="alert alert-danger col-xs-12" />
						<!-- Password -->
						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span> 
							
							<form:password path="password" placeholder="password" class="form-control" />
						</div>
						<form:errors path="password"  class="alert alert-danger col-xs-12"/>
						<!-- Register Button -->
						<div style="margin-top: 10px" class="form-group">						
							<div class="col-sm-6 controls">
								<button type="submit" class="btn btn-default">Register</button>
							</div>
						</div>																	
					</form:form>						
				</div>
			</div>
		<p>
			<a href="${pageContext.request.contextPath}/login-page" 
			class="btn btn-default" role="button">Back to Login Page</a>
		</p>
		<br/>
		<p>
			<a href="${pageContext.request.contextPath}/" 
			class="btn btn-default" role="button">Back to Home Page</a>
		</p>
		</div>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
	<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>		
	</div>
</body>
</html>