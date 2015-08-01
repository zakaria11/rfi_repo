<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="/includes/header.jsp"></jsp:include>
<script type="text/javascript">
	function initPaymentMethod(){
		$("#paymentType").fadeOut("fast", function() {
			$("#paymentType").fadeIn("fast");
		});			
	}

</script>
</head>
<body>
	<jsp:include page="/includes/head_menu.jsp"></jsp:include>
	<div class="container" id="main_container">
	  	<fieldset>
    		<legend><s:text name="i18n.global.payment.info" /></legend>
    		<form class="form-horizontal">
   			<div class="control-group">
				<label class="control-label" for="inputPaymentMethod"><s:text name="i18n.global.payment.method" /></label>
				<div class="controls">
					<select id="inputPaymentMethod" class="span3" onchange="initPaymentMethod();">
					<s:iterator value="paymentMethods" var="paymentMethod">
						<option><s:text name="paymentMethod"></s:text></option>
					</s:iterator>
					</select>
				</div>
			</div>			
			<div class="control-group">
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
			
			</form>
  		</fieldset>
		<fieldset id="userInformations">
    		<legend><s:text name="i18n.global.user.inofrmations"></s:text> :</legend>
    		<form class="form-horizontal">
				<div class="control-group">
					<label class="control-label" for="inputEventName"><s:text name="i18n.global.event.name" /></label>
					<div class="controls">
						<s:textfield id="inputEventName" placeholder="${bookedEvent.name}" disabled="true" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="inputFirstName"><s:text name="i18n.global.firstname" /></label>
					<div class="controls">
						<s:textfield  id="inputFirstName" placeholder="${getText('i18n.global.firstname')}" disabled="true"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="inputLastName"><s:text name="i18n.global.lastname" /></label>
					<div class="controls">
						<s:textfield id="inputLastName" placeholder="${getText('i18n.global.lastname')}" disabled="true" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="inputEmail"><s:text name="i18n.global.email" /></label>
					<div class="controls">
						<s:textfield id="inputEmail" placeholder="${getText('i18n.global.email')}" disabled="true"/>
					</div>
				</div>				
				<div class="control-group">
					<div class="controls">
						<a class="btn"><s:text name="i18n.global.subscribe"></s:text></a>
					</div>
				</div>
			</form>
  		</fieldset>
	</div>
	<jsp:include page="/includes/footer.jsp"></jsp:include>
</body>
</html>
