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
    	<h1>Events</h1>
 		<table id="list" class="table table-striped table-bordered dataTable no-footer">
              <thead>
                <tr>
                  <th>Id</th>
                  <th>Date</th>
                  <th>Price</th>
                  <th>Name</th>
                  <th>Description</th>
                  <th style="text-align: center">Operations</th>
                </tr>
              </thead>
              <tbody>
		    	<s:iterator value="list">
	                <tr>
	                  <td><s:property value="id"/></td>
	                  <td><s:property value="date"/></td>
	                  <td><s:property value="price"/></td>
	                  <td><s:property value="name"/></td>
	                  <td><s:property value="description"/></td>
	                  <td class="span2" style="text-align: center">
	                  	<a href="#" onclick="del(<s:property value="id"/>);"><i class="icon-remove"></i></a>
	                  	<a href="#" onclick="showEditPopUp(<s:property value="id"/>);"><i class="icon-pencil"></i></a>
	                  	<a href="#" onclick="initBooking(<s:property value="id"/>);"><i class="icon-check"></i></a>
	                  </td>
	                </tr>
				</s:iterator>
              </tbody>
		</table>
		<div class="btn-group">
   			<a class="btn" href="#" onclick="showAddPopUp();">Ajouter</a>
   		</div>		
   			<div class="error"><s:property value="errorNotification" /></div>
    </div>
    <div id="modalContainer"></div>
	<jsp:include page="/includes/footer.jsp"></jsp:include>
	<script>
	$('#list').DataTable({
	    ordering: true,
	    "drawCallback": function( settings ) {
			$("#main_container").fadeIn("fast");
	    }
	});
	function showEditPopUp(id){
		
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
	}
	function showAddPopUp(){
	    $("#modalContainer").load( "add.jsp" ,function() {
	    	$("#addEvent").modal();
	    });
	}
	function initBooking(eventId){
		/*loading();
		$.ajax({
			url : contextPath+'/event/booking',
			method  : 'POST',
			data : {eventId:eventId},
			success : function(){
				unloading();
				
			},
			error : function(){
				unloading();
			}
		});*/
		window.location.href = contextPath+'/event/booking?eventId='+eventId;
	}
	function del(id){
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
	}
	</script>
  </body>
</html>
