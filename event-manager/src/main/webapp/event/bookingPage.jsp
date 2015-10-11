<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="/includes/header.jsp"></jsp:include>
<script type="text/javascript">

var selectedId = null;

showSearchClientGrid = function(){
	$("#searchClientModal").modal();
};

/* proceedPayment = function(){
	$.ajax({
		url : contextPath+'/booking/executePayment',
		method  : 'POST',
		data : {paymentMethod : $("#inputPaymentMethod").val()},
		dataType : "html",
		success : function(res){
			$("#paymentResponseModal").modal();
			$("#printTicketSection").html(res);
			$("#printTicketSection").animate({opacity: 'show', height: 'show'}, 'slow');
		},
		error : function(){}
	});
}; */

paymentStep = function(){	
	$.ajax({
		url : contextPath+'/booking/paymentChoice',
		method  : 'POST',
		data : {selectedClientId : selectedId},
		dataType : "html",
		success : function(res){
			$("#identificationSection").html(res);
			//$("#bookedEventSection").animate({opacity: 'hide', height: 'hide'}, 'slow');
		},
		error : function(){}
	});
};
</script>
</head>
<body>
	<jsp:include page="/includes/head_menu.jsp"></jsp:include>
	
	<div class="container" id="main_container">
  		<fieldset >
    		<legend><img src="<%=request.getContextPath()%>/img/magnifying.png"> Identification</legend>
  			<div id="bookedEventSection" class="form-horizontal">

				<ul class="nav nav-tabs nav-tabs-header">
  					<li class="active">
    					<a href="#">Informations sur l'événemenet</a>
  					</li>	  			
  				</ul>
	  			<div class="row bordered-box">
		  			<div>
						<div class="span1">Id : <span><s:property value="bookedEvent.id"/></span></div>
						<div class="span3">Nom de l'evenement : <span><s:property value="bookedEvent.name"/></span></div>
			  			<div class="span2">Prix : <span><s:property value="bookedEvent.price"/></span></div>
			  			<div class="spa3">Description : <span><s:property value="bookedEvent.description" /></span></div>
		  			</div>
		  		</div>

				<ul class="nav nav-tabs nav-tabs-header">
  					<li class="active">
    					<a href="#">Recherche client</a>
  					</li>	  			
  				</ul>
	  			<div class="row bordered-box">
					
					<%--  Search criteria inputs --%>
		  			<div id="searchCriteriaSection">
						<input name="cne" placeholder="CNE" type="text"/>
						<input name="cin" placeholder="CIN" type="text"/>
						<input name="firstName" placeholder="Nom" type="text"/>
						<input name="lastName" placeholder="Prénom" type="text"/>
						<button class="btn btn-primary" onclick="searchClient();">Rechercher</button>
					</div>
			  		
			  		<%--  Search results --%>
			  		<div style="margin-top: 30px;margin-bottom: 20px;">
						 <table id="clientsSearchResultTable" class="table table-hover table-bordered dataTable no-footer">
				            <thead>
								<tr>
				                  <th>Id</th>
				                  <th>Nom</th>
				                  <th>Prénom</th>
				                  <th>CNE</th>
				                  <th>CIN</th>
                				</tr>  
				            </thead>
				            <tbody id="searchTbody"></tbody>
						</table>
		  			</div>
					<a class="btn disabled" id="goToPaymentStep" onclick="paymentStep();" >Inscription</a>
		  		</div>
			</div>  	
    	</fieldset>
    	<fieldset>
    		<legend><img src="<%=request.getContextPath()%>/img/commerce1.png"> Paiement</legend>
    		 <div id="identificationSection" class="form-horizontal" ><%-- Loaded by ajax from identificationOnglet.jsp --%></div>
    	</fieldset>
       	<fieldset id="paymentSection">
    		  <div class="form-horizontal"><%-- Loaded by ajax from paymentChoice --%></div>  
    	</fieldset>
       	<fieldset>
    		<legend><img src="<%=request.getContextPath()%>/img/tag79.png"> Ticket</legend>
    	     <div id="printTicketSection" class="form-horizontal" style="margin-left: 50px;">
    			<%-- Loaded by ajax from executePayment --%>
			</div> 
    	</fieldset>
	</div>
	<jsp:include page="/includes/footer.jsp"></jsp:include>
	<script type="text/javascript">
	var table= $('#clientsSearchResultTable').DataTable({
	    ordering: true,
	    "drawCallback": function(settings) {}
	});
	
	var criteriasDiv = $('#searchCriteriaSection');

	function searchClient(){
		$("#goToPaymentStep").addClass("disabled");
		var fields = criteriasDiv.find('input');
		var criteriaList = $(fields).serializeArray();
		criteriaList.push({ name : '_search' , value : 'true'});
		
		$.ajax({
			url : contextPath+'/client/list',
			method  : 'POST',
			data : criteriaList,
			dataType : "json",
			success : function(searchResult){
				table.clear();
				$(searchResult).each(function(index, element) {
					row = searchResult[index];
					table.row.add([row.id,row.firstName,row.lastName,row.cne,row.cin]);
				});
				table.draw();
				
				var trs = $("tr");
				trs.click(function() {
					selectedId = $(this).find("td").first().html();
					trs.removeClass("hovered");
					$(this).addClass("hovered");
					$("#goToPaymentStep").removeClass("disabled");
				});
			},
			error : function(){
				table.clear();
			}
		});
	};
	</script>
	
	<%-- Modals --%>
	<jsp:include page="/includes/searchClientPopUP.jsp"></jsp:include>
	<jsp:include page="/includes/paymentResponseModal.jsp"></jsp:include>
</body>
</html>
