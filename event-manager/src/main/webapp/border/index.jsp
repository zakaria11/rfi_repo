<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
<head lang="en">
    <script>
	    var selectedEventId = null;
    </script>

	<jsp:include page="/includes/header.jsp"></jsp:include>
	<jsp:include page="/includes/dialog.jsp"></jsp:include>

    <script>
    	updateMetroUiTiles();      
    </script>
</head>
<body style="overflow-y: hidden;">
    
    <div class="tile-area fg-white tile-area-scheme-lightBlue" style="height: 100%; max-height: 100% !important;">
	
	<jsp:include page="/includes/border_head_menu.jsp"></jsp:include>
        <div id="borderCalednar" class="tile-group double">
            <span class="tile-group-title">Détails</span>
            <div class="tile-container">
                <div href="#" class="tile-large fg-white fg-white" data-role="tile">
					<div data-role="calendar" data-week-start="1" data-locale="fr" data-day-click="reloadEventsBorderChoiceByDay" data-month-click="reloadEventsBorderChoiceByMonth"></div>
                </div>
                
				
				<a id="identificationStepButton" onclick="executePayment('BORDER',selectedEventId);" class="tile bg-lightPink fg-white" data-role="tile" style="display: none;">
                    <div class="tile-content iconic">
                        <span class="icon mif-tag"></span>
                    </div>
                    <span class="tile-label">Réserver</span>
                </a>
            </div>
        </div>
        <div id="mainContainer"></div>
    </div>

<script type="text/javascript">
reloadEventsBorderChoice();
</script>
</body>
</html>