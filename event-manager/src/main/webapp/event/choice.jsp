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
        selectTableRow = function(rowDiv){
        	console.log(rowDiv);
        	selectedEventId = $(rowDiv).attr("id");
        	$("tr").removeClass("selected");
        	$(rowDiv).addClass("selected");
        };
        
        var identificationStep = function(){
        	console.log(selectedEventId);
        	window.location = contextPath+'/booking/identification?eventId='+selectedEventId;
        };

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
                    <h1 class="text-light">Événements<span class="mif-database place-right"></span></h1>
                    <hr class="thin bg-grayLighter">

					<button class="shortcut-button bg-cyan bg-active-darkBlue fg-white" onclick="identificationStep();">
					    <span class="icon mif-tag"></span>
					    <span class="title">Reserver</span>
					    <span class="badge">22/10</span>
					</button>                    

                    <hr class="thin bg-grayLighter">
                    <table aria-describedby="DataTables_Table_0_info" role="grid" id="DataTables_Table_0" class="dataTable border bordered no-footer hovered" data-role="datatable" data-auto-width="false">
                        <thead>
                        <tr role="row"><td aria-label="Id: activate to sort column descending" aria-sort="ascending" colspan="1" rowspan="1" aria-controls="DataTables_Table_0" tabindex="0" class="sortable-column sort-asc sorting_asc">Id</td><td aria-label="Date: activate to sort column ascending" colspan="1" rowspan="1" aria-controls="DataTables_Table_0" tabindex="0" class="sortable-column sorting" style="width: 160px">Date</td><td aria-label="Prix: activate to sort column ascending" colspan="1" rowspan="1" aria-controls="DataTables_Table_0" tabindex="0" class="sortable-column sorting" style="width: 90px">Prix</td><td aria-label="Salle: activate to sort column ascending" colspan="1" rowspan="1" aria-controls="DataTables_Table_0" tabindex="0" class="sortable-column sorting" style="width: 70px">Salle</td><td aria-label="Name: activate to sort column ascending" colspan="1" rowspan="1" aria-controls="DataTables_Table_0" tabindex="0" class="sortable-column sorting" style="width: 240px">Name</td><td aria-label="Description: activate to sort column ascending" colspan="1" rowspan="1" aria-controls="DataTables_Table_0" tabindex="0" class="sortable-column sorting">Description</td></tr>
                        </thead>
							<tbody>
                        		<s:iterator value="list">
					            	<tr id="<s:property value="id"/>" onclick="selectTableRow(this);">
					                  <td><s:property value="id"/></td>
					                  <td><s:property value="date"/></td>
					                  <td><s:property value="price"/> DH</td>
					                  <td><s:property value="room.name"/></td>
					                  <td><s:property value="name"/></td>
					                  <td><s:property value="description"/></td>
					        	</tr>
							</s:iterator>
						</tbody>
                    </table>
                </div>
                </a>                   
            </div>
        </div>
        
        
        <div class="tile-group double">
            <span class="tile-group-title">Other</span>
            <div class="tile-container">
                <a href="<%=request.getContextPath()%>/admin/manageEvent.jsp" class="tile bg-teal fg-white" data-role="tile">
                    <div class="tile-content iconic">
                        <span class="icon mif-cog"></span>
                    </div>
                    <span class="tile-label">Administration</span>
                </a>
                <a href="<%=request.getContextPath()%>/admin/DashBoard.jsp" class="tile bg-darkGreen fg-white" data-role="tile">
                    <div class="tile-content iconic">
                        <span class="icon mif-chart-pie"></span>
                    </div>
                    <span class="tile-label">Reporting</span>
                </a>
                <div href="#" class="tile-large bg-magenta fg-white" data-role="tile">
                    <div class="tile-content iconic">
                        <span class="icon mif-earth mif-ani-ripple mif-ani-fast"></span>
                    </div>
                    <span class="tile-label">Reporting</span>
                </div>
            </div>
        </div>
    </div>


</body>
</html>