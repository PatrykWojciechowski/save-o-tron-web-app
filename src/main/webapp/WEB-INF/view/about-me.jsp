<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Save-o-tron</title>
    <!-- Bootstrap 4 css -->
  	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/resources/css/cover.css" rel="stylesheet">
    <!-- font awesome -->
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" 
	integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
</head>
<body>
    <div class="site-wrapper">
      <div class="site-wrapper-inner">
        <div class="cover-container">
          <div class="masthead clearfix">
            <div class="inner">
              <h3 class="masthead-brand"><i class="far fa-save"></i> Save-o-tron</h3>
              <nav class="nav nav-masthead">
                <security:authorize access="isAnonymous()">
                <a class="nav-link" href="${pageContext.request.contextPath}/main">Login</a>
                <a class="nav-link" href="${pageContext.request.contextPath}/register/showRegistrationForm">Register</a>
               </security:authorize>
                <security:authorize access="hasRole('USER')">
               <a class="nav-link" href="${pageContext.request.contextPath}/logout">Logout</a>
               <a class="nav-link" href="${pageContext.request.contextPath}/main">Repository</a>
               </security:authorize>
                <a class="nav-link active" href="${pageContext.request.contextPath}/aboutMe">About</a>
              </nav>
            </div>
          </div>
		  <div class=jumbotron>
				Hi! I am Patryk and I am a student of Mechanical Engineering at University of Technology in Lublin.
				I became interested in Java over a year ago and since then I am intensively learning programming.
				I would like to change path of my career and became a Java developer in the future.
				I also believe that working with projects like this will help me develop variety of skills. <br>
				<a href="https://github.com/PatrykWojciechowski" class="btn btn-dark" style="margin-top: 1rem;">
				 <i class="fab fa-github"></i> Github account</a>
		  </div>
		  <p class="lead">
             <a href="${pageContext.request.contextPath}/" class="btn btn-md btn-secondary"><b>Go Back</b></a>
          </p>
          <div class="mastfoot">
            <div class="inner">
              <p>2018 © Patryk Wojciechowski</p>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
  </body>
</html>
