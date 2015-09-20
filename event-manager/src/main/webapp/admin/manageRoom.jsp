<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%@ page pageEncoding="UTF-8"%>

<%--
<script>
	var selectedId = null;
	var table= $('#list').DataTable({
	    ordering: true,
	    "drawCallback": function( settings ) {
			$("#main_container").fadeIn("fast");
	    }
	});
	
	selectTableRow = function(rowDiv){
		selectedId = $(rowDiv).attr("id");
		$("#list tr").removeClass("hovered");
		$(rowDiv).addClass("hovered");
	};

/* 	showEditPopUp = function(id){		
		$.ajax({
			url : contextPath+'/event/initEdit',
			method  : 'POST',
			data : {eventId:id},
			success : function(response){
				$("#modalContainer").html(response);		
				$("#editEvent").modal();
			},
			error : function(){
			}
		});		
	}; */
	
	showAddPopUp = function(){
	    $("#modalContainer").load( "add.jsp" ,function() {
	    	$("#addEvent").modal();
	    });
	}
	initBooking = function(){
		window.location.href = contextPath+'/booking/initBooking?eventId='+selectedId;
	};
	
	del = function(id){
		$.ajax({
			url : contextPath+'/event/del',
			method  : 'POST',
			data : {eventId:id},
			success : function(){
				window.location = contextPath+'/event/list';				
			},
			error : function(){
			}
		});
	};
	</script>
 --%>
<!DOCTYPE html>
<html>
<head>

	<jsp:include page="/includes/header.jsp"></jsp:include>
	<jsp:include page="/includes/administration_common.jsp"></jsp:include>

</head>
<body class="bg-steel">
	<jsp:include page="/includes/admin_header.jsp"></jsp:include>
	<jsp:include page="/includes/roomDialog.jsp"></jsp:include>
	
	<div style="width: auto; height: auto; visibility: hidden;" data-role="dialog" id="dialog9" class="padding20 dialog" data-close-button="true" data-overlay="true" data-overlay-color="op-dark">
		<h1>Simple dialog</h1>
		<p>This dialog with colored overlay</p>
		<span class="dialog-close-button"></span>
	</div>
        
    <div class="page-content">
        <div class="flex-grid no-responsive-future" style="height: 100%;">
            <div class="row" style="height: 100%">
                <jsp:include page="/includes/left_menu.jsp"></jsp:include>
                <div class="cell auto-size padding20 bg-white" id="cell-content">
                    <h1 class="text-light">Salles<span class="mif-drive-eta place-right"></span></h1>
                    <hr class="thin bg-grayLighter">
                    <button class="button primary" onclick="initCreateEntity('room')"><span class="mif-plus"></span> Create</button>
                    <%-- <button class="button success" onclick="pushMessage('success')"><span class="mif-play"></span> Start</button> --%>
                    <%-- <button class="button warning" onclick="pushMessage('warning')"><span class="mif-loop2"></span> Restart</button> --%>
                    <!-- <button class="button alert" onclick="pushMessage('alert')">Stop all machines</button> -->
                    <hr class="thin bg-grayLighter">
                    <table id="roomTable" class="dataTable border bordered no-footer hovered"></table>
   					<div class="error"><s:property value="errorNotification" /></div>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="/includes/footer.jsp"></jsp:include>
	<script type="text/javascript">	
	
	dataTables_Room = function(){
		$('#roomTable').dataTable({
			"ajaxSource": contextPath+"/room/list",
			"columns": [
			    { "data": "id", "title": "Id" ,"width": "70px"},
			    { "data": "name", "title": "Name", "width": "140px" },
			    { "data": "description", "title": "Description"},
			    { "data": "state", "title": "State"}
			  ]
		});
	};
	
	dataTables_Room();
	//plotGrid_Room();
	
	</script>
</body>
</html>
 