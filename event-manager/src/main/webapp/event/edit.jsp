<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div id="editEvent" class="modal hide fade" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">x</button>
		<h3 id="myModalLabel">edit Event</h3>
	</div>
	<div class="modal-body">
		<form class="form-horizontal" id="editEventForm">
			<div class="control-group">
				<label class="control-label" for="inputDate" >Date</label>
				<div class="controls">
					<input type="text" id="inputDate" placeholder="Event date" name="event.date"  value="<s:property value="event.date" />">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputPrice">Price</label>
				<div class="controls">
					<input type="text" id="inputPrice" placeholder="Price" name="event.price" validations="number required" value="<s:property value="event.price" />"/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputName">Name</label>
				<div class="controls">
					<input type="text" id="inputName" name="event.name" placeholder="Name" validations="required" value="<s:property value="event.name" />"/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputDescription">Description</label>
				<div class="controls">
					<input type="text" id="inputDescription"  name="event.description" placeholder="Description" value="<s:property value="event.description" />"/>
				</div>
			</div>			
		</form>
	</div>
	<div class="modal-footer">
		<button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
		<button class="btn btn-primary" onclick="editEvent();">Edit</button>
	</div>
</div>
<script>
$('#inputDate').datepicker({
	todayHighlight: true
});


function editEvent(){
	if(validateForm('#editEventForm')){
		console.log();
		$.ajax({
			url : contextPath+'/event/edit',
			method  : 'POST',
			data : $('#editEventForm').serializeArray(),
			dataType : "json",
			success : function(){
				$('#editEvent').modal('hide');
				window.location = contextPath+'/event/list';
			},
			error : function(){
			}
		});
	}
}
</script>