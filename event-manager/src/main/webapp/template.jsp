<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%@ page pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
  <head>
	<jsp:include page="/includes/header.jsp"></jsp:include>
	<script type="text/javascript">
	</script>
  </head>
  <body>
  	<jsp:include page="/includes/head_menu.jsp"></jsp:include>
    <div class="container" id="main_container">
    	<h1>Title</h1>
    	<div>Content</div>	
   		<div class="error"><s:property value="errorNotification" />Errors ...</div>
    </div>
    <div id="modalContainer"></div>
	<jsp:include page="/includes/footer.jsp"></jsp:include>
	<script type="text/javascript"></script>
  </body>
</html>
