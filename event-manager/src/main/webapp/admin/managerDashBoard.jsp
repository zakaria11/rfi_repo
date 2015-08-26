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
           			<h1>Utilisateurs</h1>
           			<img src="<%=request.getContextPath()%>/img/????" class="img-rounded" />
				</div>
			</a>
    	</div>
    </div> 
	<jsp:include page="/includes/footer.jsp"></jsp:include>
  </body>
</html>