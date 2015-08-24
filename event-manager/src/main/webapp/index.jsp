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
			<a href="<%=request.getContextPath()%>/event/list" >
				<div class="thumbnail tile tile-orange">
           			<h1><s:text name="i18n.global.events" /></h1>
           			<img src="<%=request.getContextPath()%>/img/Daily_Calendar_Day_14_64.png" class="img-rounded" />
				</div>
			</a>
			<a href="#" >
				<div class="thumbnail tile tile-blue">
           			<h1><s:text name="i18n.global.configuation" /></h1>
           			<img src="<%=request.getContextPath()%>/img/Settings_Work_Tool_64.png" class="img-rounded" />
				</div>
			</a>			
    	</div>
    </div> 
	<jsp:include page="/includes/footer.jsp"></jsp:include>
  </body>
</html>