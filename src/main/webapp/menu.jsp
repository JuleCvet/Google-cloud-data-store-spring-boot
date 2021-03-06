<div id="primary-nav">
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
	
	  <a class="navbar-brand" href="${pageContext.request.contextPath}/welcome">
	  	<img style="width: 100px;" src="${pageContext.request.contextPath}/resources/img/logo.png">
 	  </a>
 	  
	  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
	    <span class="navbar-toggler-icon"></span>
	  </button>
	  
	  <div class="collapse navbar-collapse" id="navbarSupportedContent">
	    <ul class="navbar-nav w-75 justify-content-center">
	      <li class="nav-item">
	        <a class="nav-link" href="${pageContext.request.contextPath}/users" style="color:#004085;" ><b>Show all employees</b></a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="${pageContext.request.contextPath}/registration" style="color:#004085;"><b>Add new employee</b></a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="${pageContext.request.contextPath}/updateuser" style="color:#004085;"><b>Update employee</b></a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="${pageContext.request.contextPath}/delete" style="color:#004085;"><b>Delete employee</b></a>
	      </li>
	    </ul>
	    
	    <c:if test="${pageContext.request.userPrincipal.name != null}">
     	<form id="logoutForm" method="POST" action="${pageContext.request.contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        	<ul class="nav navbar-nav ml-auto w-25 justify-content-end">
	            <li class="nav-item">
	                <a class="nav-link" onclick="document.forms['logoutForm'].submit()"style="color:#004085;" href="/welcome" ><b>Logout</b></a>
	            </li>
        	</ul>
    	</c:if>
	  </div>
	</nav>
</div>