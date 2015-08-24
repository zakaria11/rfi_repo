<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html lang="en">
  <head>
	<jsp:include page="/includes/header.jsp"></jsp:include>
  </head>
  <body>
  	<jsp:include page="/includes/head_menu.jsp"></jsp:include>
    <div class="container">
		<fieldset>
    		<legend><h1>Information sur la réservation</h1></legend>
    		<div class="form-horizontal">
    			<h4>ticket N°<span><s:property value="subscription.id"/></span></h4>
    		    <h4>Type de paiement : <span><s:property value="subscription.type"/></span></h4>
	   			<h4>Prix : <span><s:property value="bookedEvent.price"/> DH</span></h4>
	   			<h4>Evenement : <span><s:property value="bookedEvent.name"/></span></h4>
	   			<h4>Description : <span><s:property value="bookedEvent.description"/></span></h4>
	   			<h4>Date : <span><s:property value="bookedEvent.date"/></span></h4>	
				<div class="control-group">
					<a class="btn" onclick="printTicket();" >Imprimer le ticket</a>
				</div>
			</div>
  		</fieldset>
    </div> 
	<jsp:include page="/includes/footer.jsp"></jsp:include>
  </body>
</html>