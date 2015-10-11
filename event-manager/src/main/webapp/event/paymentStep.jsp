<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
<head lang="en">
	
	<jsp:include page="/includes/header.jsp"></jsp:include>
	<jsp:include page="/includes/dialog.jsp"></jsp:include>
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
    </script>
</head>
<body style="overflow-y: hidden;">
    <div class="tile-area fg-white tile-area-scheme-lightBlue" style="height: 100%; max-height: 100% !important;">	
	<jsp:include page="/includes/head_menu.jsp"></jsp:include>
        <div class="tile-group four" id="paymentTypesGroup">
            <span class="tile-group-title">Moyen de paiement</span>
            <div class="tile-container">
                
            <s:iterator value="paymentMethods" var="paymentMethod">
				<a onclick="executePayment('<s:property value="key"/>',null);"  class="tile bg-orange fg-white" data-role="tile">
                    <div class="tile-content iconic">
                        <span class="icon mif-coins"></span>
                    </div>
                    <span class="tile-label"><s:property value="value"/></span>
                </a>
			</s:iterator>
			</div>
		</div>
			
		<!-- ticket Info -->
		<div class="tile-group double" id="ticketInfosGroup" style="display: none;">
            <span class="tile-group-title">Informations sur le ticket</span>
            <div class="tile-container">
                <a href="<%=request.getContextPath()%>/admin/manageEvent.jsp" class="tile-large bg-teal fg-white" data-role="tile">
                    <div class="tile-content iconic">
	                     <div class="padding10" id="ticketInfoArea">
						</div>
                    </div>
                </a>
                <div class="tile bg-lightRed fg-white" data-role="tile" onclick="printTicket();">
            		<div class="tile-content iconic">
						<span class="icon mif-file-download"></span>
					</div>
					<span class="tile-label">Imprimer le ticket</span>
				</div>
            </div>
        </div>
        <div class="tile-group double">
        <span class="tile-group-title">Options</span>
        <div class="tile-container">
        	<div class="tile bg-darkBlue fg-white" data-role="tile" onclick="goHome();">
            	<div class="tile-content iconic">
					<span class="icon mif-home"></span>
				</div>
			<span class="tile-label">Accueil</span>
		</div>
        </div>
        </div>
    </div>
</body>
</html>