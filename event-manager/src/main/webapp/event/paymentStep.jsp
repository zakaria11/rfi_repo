<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%--
<form id="paymentMethodForm" class="form-horizontal" action="<%=request.getContextPath()%>/event/booking" method="POST">
	<div class="control-group">
		<label class="control-label" for="inputPaymentMethod"></label>
		<div class="controls">
			<select id="inputPaymentMethod" class="span3" name="paymentMethod">
			</select>					
		</div>
	</div>
	<div class="control-group">
		<div class="controls">
			<a class="btn">Annuler</a>
			<a class="btn" onclick="proceedPayment();">Procéder au paiement</a>
		</div>
	</div>
</form> --%>

<!DOCTYPE html>
<html>
<head lang="en">
	
	<jsp:include page="/includes/header.jsp"></jsp:include>

    <script></script>

    <script>
        $(function(){
            $.StartScreen();

            var tiles = $(".tile, .tile-small, .tile-sqaure, .tile-wide, .tile-large, .tile-big, .tile-super");

            $.each(tiles, function(){
                var tile = $(this);
                setTimeout(function(){
                    tile.css({
                        opacity: 1,
                        "-webkit-transform": "scale(1)",
                        "transform": "scale(1)",
                        "-webkit-transition": ".3s",
                        "transition": ".3s"
                    });
                }, Math.floor(Math.random()*500));
            });

            $(".tile-group").animate({
                left: 0
            });
        });
                
        var identificationStep = function(){
        	console.log(selectedEventId);
        	window.location = contextPath+'/booking/identification?eventId='+selectedPaymentId;
        };

    </script>
</head>
<body style="overflow-y: hidden;">
    <div class="tile-area fg-white tile-area-scheme-lightBlue" style="height: 100%; max-height: 100% !important;">	
	<jsp:include page="/includes/head_menu.jsp"></jsp:include>
        <div class="tile-group four">
            <span class="tile-group-title">Moyen de paiement</span>
            <div class="tile-container">
                
            <s:iterator value="paymentMethods" var="paymentMethod">
				<a id="<s:property value="key"/>" href="<%=request.getContextPath()%>/booking/payTicket?=selectedPaymentId=<s:property value="key"/>" class="tile bg-orange fg-white" data-role="tile">
                    <div class="tile-content iconic">
                        <span class="icon mif-coins"></span>
                    </div>
                    <span class="tile-label"><s:property value="value"/></span>
                </a>
			</s:iterator>
            </div>
        </div>
    </div>
</body>
</html>