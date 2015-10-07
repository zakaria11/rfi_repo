<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html lang="en">
  <head>
	<jsp:include page="/includes/header.jsp"></jsp:include>
  </head>
  <body>
  	<jsp:include page="/includes/head_menu.jsp"></jsp:include>
    <div class="container">

		<div class="row">
			<a href="manageUsers.jsp" >
				<div class="thumbnail tile tile-orange">
           			<h1>Rôles et Utilisateurs</h1>
           			<img src="<%=request.getContextPath()%>/img/users6.png" class="img-rounded" />
				</div>
			</a>
			<a href="manageRoom.jsp" >
				<div class="thumbnail tile tile-blue">
           			<h1>Salles</h1>
           			<img src="<%=request.getContextPath()%>/img/buildings5.png" class="img-rounded" />
				</div>
			</a>
			<a href="manageEvent.jsp" >
				<div class="thumbnail tile tile-lime">
           			<h1>Événements</h1>
           			<img src="<%=request.getContextPath()%>/img/calendar146.png" class="img-rounded" />
				</div>
			</a>
			<a href="manageClient.jsp">
				<div class="thumbnail tile tile-magenta">
           			<h1>Clients</h1>
           			<img src="<%=request.getContextPath()%>/img/business12.png" class="img-rounded" />
				</div>
			</a>

			<a href="<%=request.getContextPath()%>/reporting/index.jsp">
				<div class="thumbnail tile tile-magenta">
           			<h1>Reporting</h1>
           			<img src="<%=request.getContextPath()%>/img/education20.png" class="img-rounded" />
				</div>
			</a>
			
    	</div>
    </div> 
	<jsp:include page="/includes/footer.jsp"></jsp:include>
  </body>
</html>