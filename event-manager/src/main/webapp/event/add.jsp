<div id="addEvent" class="modal hide fade" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">x</button>
		<h3 id="myModalLabel">Add Event</h3>
	</div>
	<div class="modal-body">
		<form class="form-horizontal" id="addEventForm">
			<div class="control-group">
				<label class="control-label" for="inputDate" >Date</label>
				<div class="controls">
					<input type="text" id="inputDate" placeholder="Event date" name="event.date">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputPrice">Price</label>
				<div class="controls">
					<input type="text" id="inputPrice" placeholder="Price" name="event.price" validations="number required"/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputName">Name</label>
				<div class="controls">
					<input type="text" id="inputName" name="event.name" placeholder="Name" validations="required" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputDescription">Description</label>
				<div class="controls">
					<input type="text" id="inputDescription"  name="event.description" placeholder="Description">
				</div>
			</div>			
		</form>
	</div>
	<div class="modal-footer">
		<button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
		<button class="btn btn-primary" onclick="addEvent();">Add</button>
	</div>
</div>
<script>
$('#inputDate').datepicker({
	todayHighlight: true
});




function addEvent(){
	if(validateForm('#addEventForm')){
		console.log();
		$.ajax({
			url : contextPath+'/event/add',
			method  : 'POST',
			data : $('#addEventForm').serializeArray(),
			dataType : "json",
			success : function(){
				$('#addEvent').modal('hide');
				window.location = contextPath+'/event/list';
			},
			error : function(){
			}
		});
	}
}
</script>