<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

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
   </script>
</head>
<body style="overflow-y: hidden;">
     <div class="tile-area fg-white tile-area-scheme-lightBlue" style="height: 100%; max-height: 100% !important;">	
	<jsp:include page="/includes/head_menu.jsp"></jsp:include>
		<div class="tile-group double">
            <%-- <span class="tile-group-title">Other</span> --%>
            <div class="tile-container">
				<div data-role="tile">
					<h4>ticket N °<span><s:property value="subscription.id"/></span></h4>
					<h4>Type de paiement : <span><s:property value="subscription.type"/></span></h4>
					<h4>Prix : <span><s:property value="bookedEvent.price"/> DH</span></h4>
					<h4>Evenement : <span><s:property value="bookedEvent.name"/></span></h4>
					<h4>Description : <span><s:property value="bookedEvent.description"/></span></h4>
					<h4>Date : <span><s:property value="bookedEvent.date"/></span></h4>	
				</div>
				
				<a href="#" class="tile bg-lightRed fg-white" data-role="tile" onclick="printTicket();">
                    <div class="tile-content iconic">
                        <span class="icon mif-file-download"></span>
                    </div>
                    <span class="tile-label">Imprimer le ticket</span>
                </a>
            </div>
        </div>
    </div>


</body>
</html>