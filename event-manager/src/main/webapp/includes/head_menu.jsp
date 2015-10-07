<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
    
<h1 class="tile-area-title"><a href="<%=request.getContextPath()%>/index.jsp" style="color: white;">Gestion de billettrie</a></h1>
<div class="tile-area-controls">
    <button class="image-button icon-right bg-transparent fg-white bg-hover-dark no-border"><span class="sub-header no-margin text-light">
    <sec:authorize access="isAuthenticated()">
    	<sec:authentication property="principal.username" /> 
	</sec:authorize>
	<sec:authorize access="!isAuthenticated()">
	    Invité
	</sec:authorize>
	
    </span> <span class="icon mif-user"></span></button>
    <%-- <button class="square-button bg-transparent fg-white bg-hover-dark no-border" onclick="showCharms('#charmSearch')"><span class="mif-search"></span></button> --%>
    <button class="square-button bg-transparent fg-white bg-hover-dark no-border" onclick="showCharms('#charmSettings')"><span class="mif-cog"></span></button>
    <a href="<%=request.getContextPath()%>/j_spring_security_logout" class="square-button bg-transparent fg-white bg-hover-dark no-border"><span class="mif-switch"></span></a>
    
</div>