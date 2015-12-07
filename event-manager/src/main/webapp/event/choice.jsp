<%@ page pageEncoding="UTF-8"%>
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

        var selectedEventId = null;

        printEventDetails = function(entityId){
        	selectedEventId = entityId;
    		$.ajax({
    			url: contextPath+'/entity/load',
    			data: {entityName:'event',entityId : entityId},
    			beforeSend : function(){
    				$("#eventAttributesSpinner").animate({opacity: 'show', height: 'show'}, 'fast');
    				$('#eventAttributes').animate({opacity: 'hide', height: 'hide'}, 'fast');
    				$("#identificationStepButton").animate({opacity: 'hide', height: 'hide'}, 'fast');
    			},
    			complete : function(){
    				$("#eventAttributesSpinner").animate({opacity: 'hide', height: 'hide'}, 'fast');
    			},
    			success: function(entity){
    				/*$.each(entity, function( key, value ) {
    					var eventAttrDiv = '<p class="no-margin text-shadow">'+entity.key+'<span class="text-bold  padding10">'+entity.val+'</span></p>';
        				$('#eventAttributes').append(eventAttrDiv);
    				});*/
    				var evntAttrsDiv = $('#eventAttributes');
    				evntAttrsDiv.empty();
    				evntAttrsDiv.append('<p class="no-margin text-shadow"><span class="text-bold">Id</span><span class="padding10">'+entity.id+'</span></p>');
    				evntAttrsDiv.append('<p class="no-margin text-shadow"><span class="text-bold">Nom</span><span class="padding10">'+entity.name+'</span></p>');
    				evntAttrsDiv.append('<p class="no-margin text-shadow"><span class="text-bold">Prix</span><span class="padding10">'+entity.price+'</span></p>');
    				evntAttrsDiv.append('<p class="no-margin text-shadow"><span class="text-bold">Description</span><span class="padding10">'+entity.description+'</span></p>');
    				evntAttrsDiv.animate({opacity: 'show', height: 'show'}, 'fast');
    				$("#identificationStepButton").animate({opacity: 'show', height: 'show'}, 'fast');
    			},
    			dataType: 'json'
    		});
        };
        
        
        var identificationStep = function(){
        	console.log(selectedEventId);
        	window.location = contextPath+'/booking/identification?eventId='+selectedEventId;
        };
        
        //"scrollY":"200px"

    </script>
</head>
<body style="overflow-y: hidden;">
    <div data-role="charm" id="charmSearch">
        <h1 class="text-light">Search</h1>
        <hr class="thin"/>
        <br />
        <div class="input-control text full-size">
            <label>
                <span class="dropdown-toggle drop-marker-light">Anywhere</span>
                <ul class="d-menu" data-role="dropdown">
                    <li><a>Anywhere</a></li>
                    <li><a>Options</a></li>
                    <li><a>Files</a></li>
                    <li><a>Internet</a></li>
                </ul>
            </label>
            <input type="text">
            <button class="button"><span class="mif-search"></span></button>
        </div>
    </div>

    <div class="tile-area fg-white tile-area-scheme-lightBlue" style="height: 100%; max-height: 100% !important;">
	
	<jsp:include page="/includes/head_menu.jsp"></jsp:include>
	
        
        <div id="eventsList" class="tile-group"  >
            <span class="tile-group-title">Événements</span>
            <div class="tile-container">
                <a href="#" class="tile-large bg-white super-tile" data-role="tile" data-transform="false">
					<div class="cell auto-size padding20 bg-white" id="cell-content">
                    <h1 class="text-light">Événements</h1>
                    <!-- <hr class="thin bg-grayLighter"> -->
                    <hr class="thin bg-grayLighter">
                    <table id="eventChoiceTable" class="dataTable border bordered no-footer hovered"></table>
                </div>
                </a>                   
            </div>
        </div>
        
        
        <div class="tile-group double">
            <span class="tile-group-title">Détails</span>
            <div class="tile-container">
                <%-- <a href="<%=request.getContextPath()%>/admin/manageEvent.jsp" class="tile bg-teal fg-white" data-role="tile">
                    <div class="tile-content iconic">
                        <span class="icon mif-cog"></span>
                    </div>
                    <span class="tile-label">Administration</span>
                </a>
                --%>
                <div id="eventDetails" href="#" class="tile-large bg-magenta fg-white" data-role="tile">
                    <div class="tile-content iconic">
                        <span id="eventAttributesSpinner" class="icon mif-loop2 mif-ani-spin mif-ani-fast" style="display:none;"></span>
                        <div id="eventAttributes" class="padding20"></div>
                    </div>
                </div>
                
				
				<a id="identificationStepButton" onclick="identificationStep();" class="tile bg-lightPink fg-white" data-role="tile" style="display: none;">
                    <div class="tile-content iconic">
                        <span class="icon mif-tag"></span>
                    </div>
                    <span class="tile-label">Réserver</span>
                </a>
            </div>
        </div>
    </div>

<script type="text/javascript">
dataTables_Choice_Event();
</script>
</body>
</html>