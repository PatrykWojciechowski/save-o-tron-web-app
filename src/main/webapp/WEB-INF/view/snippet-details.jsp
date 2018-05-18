<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Save-o-tron</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- Bootstrap 4 css -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
  <!-- font awesome -->
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.11/css/all.css" 
  		integrity="sha384-p2jx59pefphTFIpeqCcISO9MdVfIm4pNnsL08A6v5vaQc4owkQqxMV8kg4Yvhaw/" 
  		crossorigin="anonymous">
  <!-- Custom styles for this template -->
  <link href="${pageContext.request.contextPath}/resources/css/afterlogin.css" rel="stylesheet">
  <!--  highlight.js -->
  <link rel="stylesheet"
	      href="//cdnjs.cloudflare.com/ajax/libs/highlight.js/9.12.0/styles/default.min.css">
  <script src="//cdnjs.cloudflare.com/ajax/libs/highlight.js/9.12.0/highlight.min.js"></script>
  <script>hljs.initHighlightingOnLoad();</script>
</head>
<body>
	<div class="jumbotron text-center header" style="margin-bottom:0">
	  <h1><i class="far fa-save"></i> Save-o-tron</h1>
	  <p>Now you can keep your code online! For Free!</p> 
	</div>
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
		<div class="container">
		  <a class="navbar-brand brandy" href="${pageContext.request.contextPath}/main"><i class="far fa-save"></i> Save-o-tron</a>
		  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
		    <span class="navbar-toggler-icon"></span>
		  </button>
		  <div class="collapse navbar-collapse justify-content-end" id="collapsibleNavbar">
		    <ul class="navbar-nav">
		      <li class="nav-item">
		        <a class="nav-link disabled" data-toggle="tooltip" title="Coming soon!" href="#">Settings</a>
		      </li>
		      <li class="nav-item">
		        <a class="nav-link" href="${pageContext.request.contextPath}/logout">Logout</a>
		      </li>    
		    </ul>
		  </div>
		</div>  
	</nav>
	<!-- Page Content -->
    <div class="container content">
      <div class="row">
        <!-- Content -->
        <div class="col-lg-10 content">
		 <br/>
		 <div class="alert alert-info">
			  Here you can check details of your code snippet.
		 </div>
		 <div class="card mb-4">
		  <div class="card-body">
		  	<c:set var="tempSnippet" value="${codeSnippet}"/>
		  	<!-- construct an "update" link with snippet id -->
			<c:url var="editLink" value="/show-edit-form">
				<c:param name="snippetId" value="${codeSnippet.id}" />
			</c:url>					
			<!-- construct an "delete" link with snippet id -->
			<c:url var="deleteLink" value="/delete">
				<c:param name="snippetId" value="${codeSnippet.id}" />
			</c:url>	
          <h2>${tempSnippet.title} <a href="${deleteLink}" 
           class="btn btn-dark float-right">Delete</a><a href="${editLink}"
           class="btn btn-dark float-right px-3 mr-2">Edit</a>
          </h2>
          	<p>${tempSnippet.description}</p>
	        	<pre class="codeSnippet"><code>${tempSnippet.textField}</code></pre> 
        	<div class="text-muted">   
	        	Last edited on ${tempSnippet.updateTime}
			</div>
		 </div>
		</div>
		<a href="${pageContext.request.contextPath}/main"
        class="btn btn-dark mb-3 px-5">Go back &rarr;</a>
		</div>
		<!--  column content -->
      </div>
      <!-- /.row -->
	</div>
	<!--  main container -->
	<footer class="footer">
		<div class="container">
			<p class="footp">2018 © Patryk Wojciechowski</p>
		</div>
	</footer>
  <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/9.12.0/highlight.min.js"></script>
  <!-- Bootstrap 4 js -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
</body>
</html>