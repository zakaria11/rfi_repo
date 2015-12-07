	<form id="eventAddDialog" data-role="validator" enctype="multipart/form-data">
		<div data-role="dialog" class="padding20" data-close-button="true" data-overlay="true" data-overlay-color="op-dark">
			<h1>Créer un Événement</h1>
				<div>
					<div class="cell">
						<label>Nom</label>
						<div class="input-control text full-size" data-role="input">
							<input style="padding-right: 41px;" data-validate-func="required" type="text" name="name">
							<button type="button" tabindex="-1" class="button helper-button clear"><span class="mif-cross"></span></button>
						</div>
					</div>
					<div class="cell">
						<label>Prix</label>
						<div class="input-control text full-size" data-role="input">
							<input style="padding-right: 41px;" data-validate-func="number" type="text" name="price">
							<button type="button" tabindex="-1" class="button helper-button clear"><span class="mif-cross"></span></button>
						</div>
					</div>
					<div class="cell">
						<label>N° places</label>
						<div class="input-control text full-size" data-role="input">
							<input style="padding-right: 41px;" data-validate-func="number" type="text" name="places">
							<button type="button" tabindex="-1" class="button helper-button clear"><span class="mif-cross"></span></button>
						</div>
					</div>
					<div class="cell">
						<label>Importance</label>
						<div class="input-control text full-size" data-role="input">
							    <div class="rating" data-role="rating" data-size="large" data-size="small" data-show-score="false" data-on-rated="onRateEvent"></div>
							    <input id="eventRatingInput" style="padding-right: 41px; visibility: hidden;" type="text" name="rating">
						</div>
					</div>
					
					<div class="cell">
						<label style="display: block;">Date</label>
						<div class="input-control text" data-role="datepicker" data-format="dd/mm/yyyy">
        					<input style="padding-right: 41px;" type="text" name="date">
        					<button class="button"><span class="mif-calendar"></span></button>
    					</div>
					</div>
					<div class="cell">
						<label style="display: block;">Image</label>
						<div class="input-control text">
        					<input id="eventImageEdit" name="upload" value="" type="file" class="button">
        					<script>$('#eventImageEdit').on('change', prepareUpload);</script>
    					</div>
					</div>
					
					<div class="cell">
						<label>Salle</label>
						<div class="input-control text full-size" data-role="input">
							<select name="room_id" class="js-select full-size roomsList" style="width: 50%" dataSource="room"></select>
						</div>
					</div>
					
					<div class="cell">
						<label>Statut</label>
						<div class="input-control text full-size" data-role="input">
						<select name="state_id" class="js-select full-size statesList" style="width: 50%" dataSource="state"></select>
						</div>
					</div>
					
					<div class="cell">
						<label>Tag</label>
						<div class="input-control text full-size" data-role="input">
							<select name="tag_id" class="js-select full-size tagsList" style="width: 50%" dataSource="tag"></select>
						</div>
					</div>
					
					<div class="cell">
						<label>Description</label>
						<div class="input-control textarea full-size">
							<textarea name="description"></textarea>
						</div>
					</div>
					
					<div class="cell place-right">
						<button type="button" class="button primary" onclick="createEntity('event');">Créer</button>
					</div>
				</div>
		</div>
	</form>
	
	
	<form id="eventEditDialog" data-role="validator" enctype="multipart/form-data">
		<div data-role="dialog" class="padding20" data-close-button="true" data-overlay="true" data-overlay-color="op-dark">
			<h1>Modifier un Événement</h1>
				<div>
					<div class="cell">
						<label>Id</label>
						<div class="input-control text full-size" data-role="input">
							<input id="id" style="padding-right: 41px;" type="text" name="id" readonly>
						</div>
					</div>
					<div class="cell">
						<label>Nom</label>
						<div class="input-control text full-size" data-role="input">
							<input id="name" style="padding-right: 41px;" data-validate-func="required" type="text" name="name">
							<button type="button" tabindex="-1" class="button helper-button clear"><span class="mif-cross"></span></button>
						</div>
					</div>
					<div class="cell">
						<label>Prix</label>
						<div class="input-control text full-size" data-role="input">
							<input id="price" style="padding-right: 41px;" data-validate-func="number" type="text" name="price">
							<button type="butt	on" tabindex="-1" class="button helper-button clear"><span class="mif-cross"></span></button>
						</div>
					</div>
					<div class="cell">
						<label>N° places</label>
						<div class="input-control text full-size" data-role="input">
							<input id="places" style="padding-right: 41px;" data-validate-func="number" type="text" name="places">
							<button type="button" tabindex="-1" class="button helper-button clear"><span class="mif-cross"></span></button>
						</div>
					</div>
					<div class="cell">
						<label>Importance</label>
						<div class="input-control text full-size" data-role="input">
							    <div id="eventRatingEdit" class="rating" data-role="rating" data-size="large" data-size="small" data-show-score="false" data-on-rated="onRateEvent"></div>
							    <input id="rating" style="padding-right: 41px; visibility: hidden;" type="text" name="rating">
						</div>
					</div>
					
					<div class="cell">
						<label style="display: block;">Date</label>
						<div class="input-control text" data-role="datepicker" data-format="dd/mm/yyyy">
        					<input id="date" style="padding-right: 41px;" type="text" name="date" />
        					<button class="button"><span class="mif-calendar"></span></button>
    					</div>
					</div>
					<div class="cell">
						<label style="display: block;">Image</label>
						<div class="input-control text">
        					<input id="eventImageAdd" name="upload" value="" type="file" class="button">
        					<script>$('#eventImageAdd').on('change', prepareUpload);</script>
    					</div>
					</div>
					
					<div class="cell">
						<label>Salle</label>
						<div class="input-control text full-size" data-role="input">
							<select id="room_id" name="room_id" class="js-select full-size roomsList" style="width: 50%" dataSource="room"></select>
						</div>
					</div>
					
					<div class="cell">
						<label>Statut</label>
						<div class="input-control text full-size" data-role="input">
							<select id="state_id" name="state_id" class="js-select full-size statesList" style="width: 50%" dataSource="state"></select>
							
						</div>
					</div>

					<div class="cell">
						<label>Tag</label>
						<div class="input-control text full-size" data-role="input">
							<select id="tag_id" name="tag_id" class="js-select full-size tagsList" style="width: 50%" dataSource="tag"></select>
						</div>
					</div>
					
					<div class="cell">
						<label>Description</label>
						<div class="input-control textarea full-size">
							<textarea id="description" name="description"></textarea>
						</div>
					</div>
					
					<div class="cell place-right">
						<button type="button" name="editEventButton" class="button primary" onclick="editEntity('event');">Modifier</button>
					</div>
				</div>
		</div>
	</form>
	
<div id="eventDeleteDialog">
	<div data-role="dialog" class="padding20" data-close-button="true" data-overlay="true" data-overlay-color="op-dark">
		<h1>êtes vous sur de vouloir supprimer l'enregistrement</h1>
		<div>
			<div class="cell place-right">
				<!-- <button type="button" class="button primary">Annuler</button> -->
				<button type="button" class="button alert" onclick="deleteEntity('event',adminSelectedItem);">Supprimer</button>
			</div>
		</div>
	</div>
</div>