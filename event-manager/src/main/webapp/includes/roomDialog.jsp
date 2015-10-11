	<form id="roomAddDialog" data-role="validator">
		<div data-role="dialog" class="padding20" data-close-button="true" data-overlay="true" data-overlay-color="op-dark">
			<h1>Créer une salle</h1>
				<div>
					<div class="cell">
						<label>Nom</label>
						<div class="input-control text full-size" data-role="input">
							<input style="padding-right: 41px;" type="text" name="name">
							<button type="button" tabindex="-1" class="button helper-button clear"><span class="mif-cross"></span></button>
						</div>
					</div>
					<div class="cell">
                    	<label class="input-control radio">
                        	<input name="state" checked="" value="ENABLED" type="radio">
                            <span class="check"></span>
                            <span class="caption">Activée</span>
                        </label>
                        <label class="input-control radio">
                        	<input name="state" value="DISABLED" type="radio">
                            <span class="check"></span>
                            <span class="caption">Desactivé</span>
                       	</label>
					</div>
					<div class="cell">
						<label>Description</label>
						<div class="input-control textarea full-size">
							<textarea name="description"></textarea>
						</div>
					</div>
					<div class="cell place-right">
						<button type="button" class="button primary" onclick="createEntity('room');">Créer</button>
					</div>
				</div>
		</div>
	</form>
	
		<form id="roomEditDialog" data-role="validator">
		<div data-role="dialog" class="padding20" data-close-button="true" data-overlay="true" data-overlay-color="op-dark">
			<h1>Modifier une salle</h1>
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
							<input id="name" style="padding-right: 41px;" type="text" name="name">
							<button type="button" tabindex="-1" class="button helper-button clear"><span class="mif-cross"></span></button>
						</div>
					</div>
					<div class="cell">
                    	<label class="input-control radio">
                        	<input name="state" checked="" value="ENABLED" type="radio">
                            <span class="check"></span>
                            <span class="caption">Activée</span>
                        </label>
                        <label class="input-control radio">
                        	<input name="state" value="DISABLED" type="radio">
                            <span class="check"></span>
                            <span class="caption">Desactivé</span>
                       	</label>
					</div>
					<div class="cell">
						<label>Description</label>
						<div class="input-control textarea full-size">
							<textarea id="description" name="description"></textarea>
						</div>
					</div>
					<div class="cell place-right">
						<button type="button" class="button primary" onclick="editEntity('room');">Modifier</button>
					</div>
				</div>
		</div>
	</form>

<div id="roomDeleteDialog">
	<div data-role="dialog" class="padding20" data-close-button="true" data-overlay="true" data-overlay-color="op-dark">
		<h1>êtes vous sur de vouloir supprimer l'enregistrement</h1>
		<div>
			<div class="cell place-right">
				<!-- <button type="button" class="button primary">Annuler</button> -->
				<button type="button" class="button alert" onclick="deleteEntity('room',adminSelectedItem);">Supprimer</button>
			</div>
		</div>
	</div>
</div>