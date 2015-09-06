<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%@ page pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
  <head>
	<jsp:include page="/includes/header.jsp"></jsp:include>
	<script type="text/javascript">
	</script>
  </head>
  <body>
  	<jsp:include page="/includes/head_menu.jsp"></jsp:include>
    <div class="container" id="main_container" style="display: none;">
    	<h1>Événements</h1>
 		<table id="list" class="table table-hover table-bordered dataTable no-footer">
              <thead>
                <tr>
                  <th>Id</th>
                  <th>Date</th>
                  <th>Prix</th>
                  <th>Salle</th>
                  <th>Name</th>
                  <th>Description</th>
                </tr>
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
		<div class="btn-group">
   			<a class="btn" href="#" onclick="initBooking()">Reserver</a>
   		</div>		
   			<div class="error"><s:property value="errorNotification" /></div>
    </div>
    <div id="modalContainer"></div>
	<jsp:include page="/includes/footer.jsp"></jsp:include>
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
  </body>
</html>
