<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
</head>
<body>
	<div class="jumbotron text-center header">
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
        <div class="col-lg-10 content">
		  <br/>
		  <div class="alert alert-info">
			Welcome, <security:authentication property="principal.username"/>! What would you like to do?
		  </div>   
          <h1 class="my-4">Code snippets: 
          	<a href="${pageContext.request.contextPath}/add-new-snippet" class="btn btn-success btn-lg px-5 ">Add new &rarr;</a>
          </h1>
		  <!--  loop over and print code snippets as cards-->
		  <c:forEach var="codeSnippet" items="${codeSnippets}">
		  <!-- construct an "show-details" links with snippet id -->
		  <c:url var="showDetails" value="/show-details">
			<c:param name="snippetId" value="${codeSnippet.id}" />
		  </c:url>					
          <div class="card mb-4">
            <div class="card-body">
              <h2 class="card-title">${codeSnippet.title}</h2>
              <p class="card-text"> ${codeSnippet.description}</p>
			  <!-- display the show more button -->
              <a href="${showDetails}" class="btn btn-dark">Show more &rarr;</a>
            </div>
            <div class="card-footer text-muted">
              Posted on <fmt:formatDate value="${codeSnippet.updateTime}" pattern="yyyy-MM-dd HH:mm:ss" /> by
              <a href="#"><security:authentication property="principal.username"/></a>
            </div>
          </div>
          </c:forEach>
      <nav aria-label="Page navigation">
		  <ul class="pagination justify-content-center">
		    <c:if test="${page != 1}">
		    	<c:set var = "previousPage" scope = "session" value = "${page-1}"/>
		    	<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/main/?page=${previousPage}">Previous</a></li>
		    </c:if>
			  <c:forEach begin="1" end="${maxPages}" step="1" varStatus="p">
			  	<c:if test="${p.index<=maxPages}">
			  			<li class="page-item 
			  				<c:if test="${p.index==page}">
			  						active
			  				</c:if>
			  			"><a class="page-link" href="${pageContext.request.contextPath}/main/?page=${p.index}">${p.index}</a></li>
			  	</c:if>
		         </c:forEach>
		   	<c:if test = "${page<maxPages}">
		   		<c:set var = "nextPage" scope = "session" value = "${page+1}"/>
		   		<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/main/?page=${nextPage}">Next</a></li>
		   	</c:if>      
		   </ul>
	   </nav>
	</div>
	</div> <!-- /.row -->
	</div><!-- /. main container -->
	<footer class="footer">
		<div class="container">
			<p class="footp">2018 © Patryk Wojciechowski</p>
		</div>
	</footer>
  <!-- Bootstrap 4 js -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
</body>
</html>