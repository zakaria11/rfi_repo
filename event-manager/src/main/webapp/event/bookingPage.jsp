<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="/includes/header.jsp"></jsp:include>
<script type="text/javascript">
/*function initPaymentMethod(){
		$("#paymentType").fadeOut("fast", function() {
			$("#paymentType").fadeIn("fast");
		});
}*/
</script>
</head>
<body>
	<jsp:include page="/includes/head_menu.jsp"></jsp:include>
	<div class="container" id="main_container">
	  	<fieldset>
    		<legend><s:text name="i18n.global.payment.info" /></legend>
    		<form id="paymentMethodForm" class="form-horizontal" action="<%=request.getContextPath()%>/event/booking" method="POST">
   			<div class="control-group">
				<label class="control-label" for="inputPaymentMethod"><s:text name="i18n.global.payment.method" /></label>
				<div class="controls">
					<select id="inputPaymentMethod" class="span3" name="paymentMethod">
					<s:iterator value="paymentMethods" var="paymentMethod">
						<option  value="<s:property value="key"/>"><s:property value="value"/></option>
					</s:iterator>
					</select>					
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputEventPrice">Prix</label>
				<div class="controls">
					<s:textfield id="inputEventPrice" placeholder="${bookedEvent.price} DH" disabled="true" />
				</div>					
			</div>
						
<%-- 			<div class="control-group">
				<label class="control-label" for="inputCNE"><s:text name="i18n.global.cne" /></label>
				<div class="controls">
					<s:textfield id="inputCNE" placeholder="CNE" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputCIN"><s:text name="i18n.global.cin" /></label>
				<div class="controls">
					<s:textfield id="inputCIN" placeholder="CIN" />
				</div>
			</div>
 --%>			
			</form>
  		</fieldset>
  		<fieldset id="userInformations">
    		<legend>Event Informations :</legend>
  			<div class="form-horizontal">

	  			<div class="control-group">
					<label class="control-label" for="inputEventId">Id</label>
					<div class="controls">
						<s:textfield id="inputEventId" placeholder="${bookedEvent.id}" disabled="true" />
					</div>					
				</div>
	  			<div class="control-group">
					<label class="control-label" for="inputEventName">Nom de l'evenement</label>
					<div class="controls">
						<s:textfield id="inputEventName" placeholder="${bookedEvent.name}" disabled="true" />
					</div>					
				</div>
	  			<div class="control-group">				
					<label class="control-label" for="inputEventDescription"><s:text name="Description" /></label>
					<div class="controls">
						<s:textarea id="inputEventDescription" placeholder="${bookedEvent.description}" disabled="true" />
					</div>
				</div>
				<div class="control-group">
					<div class="controls">
						<a class="btn" onclick="bookingStep();" ><s:text name="i18n.global.subscribe"></s:text></a>
					</div>
				</div>
				
			</div>    		
    	</fieldset>
	</div>
	<jsp:include page="/includes/footer.jsp"></jsp:include>
	<script type="text/javascript">
	function bookingStep(){
		$("#paymentMethodForm").submit();			
	}
	</script>
</body>
</html>
