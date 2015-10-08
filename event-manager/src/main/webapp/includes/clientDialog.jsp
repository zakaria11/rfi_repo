	<form id="clientAddDialog">
		<div data-role="dialog" class="padding20" data-close-button="true" data-overlay="true" data-overlay-color="op-dark">
			<h1>Créer un Client</h1>
				<div>
					<div class="cell">
						<label>Nom</label>
						<div class="input-control text full-size" data-role="input">
							<input style="padding-right: 41px;" type="text" name="firstName">
							<button type="butt	on" tabindex="-1" class="button helper-button clear"><span class="mif-cross"></span></button>
						</div>
					</div>
					<div class="cell">
						<label>Prénom</label>
						<div class="input-control text full-size" data-role="input">
							<input style="padding-right: 41px;" type="text" name="lastName">
							<button type="button" tabindex="-1" class="button helper-button clear"><span class="mif-cross"></span></button>
						</div>
					</div>
					<div class="cell">
						<label>CNE</label>
						<div class="input-control text full-size" data-role="input">
							<input style="padding-right: 41px;" type="text" name="cne">
							<button type="button" tabindex="-1" class="button helper-button clear"><span class="mif-cross"></span></button>
						</div>
					</div>
					<div class="cell">
						<label>CIN</label>
						<div class="input-control text full-size" data-role="input">
							<input style="padding-right: 41px;" type="text" name="cin">
							<button type="button" tabindex="-1" class="button helper-button clear"><span class="mif-cross"></span></button>
						</div>
					</div>
					<div class="cell place-right">
						<button type="button" class="button primary" onclick="createEntity('client');">Créer</button>
					</div>
				</div>
		</div>
	</form>
	
	
	<form id="clientEditDialog">
		<div data-role="dialog" class="padding20" data-close-button="true" data-overlay="true" data-overlay-color="op-dark">
			<h1>Modifier un Client</h1>
				<div>
					<div class="cell">
						<label>Id</label>
						<div class="input-control text full-size" data-role="input">
							<input id="id" style="padding-right: 41px;" type="text" name="id" readonly>
							<button type="button" tabindex="-1" class="button helper-button clear"><span class="mif-cross"></span></button>
						</div>
					</div>
				
					<div class="cell">
						<label>Nom</label>
						<div class="input-control text full-size" data-role="input">
							<input id="firstName" style="padding-right: 41px;" type="text" name="firstName">
							<button type="button" tabindex="-1" class="button helper-button clear"><span class="mif-cross"></span></button>
						</div>
					</div>
					<div class="cell">
						<label>Prénom</label>
						<div class="input-control text full-size" data-role="input">
							<input id="lastName" style="padding-right: 41px;" type="text" name="lastName">
							<button type="button" tabindex="-1" class="button helper-button clear"><span class="mif-cross"></span></button>
						</div>
					</div>
					<div class="cell">
						<label>CNE</label>
						<div class="input-control text full-size" data-role="input">
							<input id="cne" style="padding-right: 41px;" type="text" name="cne">
							<button type="button" tabindex="-1" class="button helper-button clear"><span class="mif-cross"></span></button>
						</div>
					</div>
					<div class="cell">
						<label>CIN</label>
						<div class="input-control text full-size" data-role="input">
							<input id="cin" style="padding-right: 41px;" type="text" name="cin">
							<button type="button" tabindex="-1" class="button helper-button clear"><span class="mif-cross"></span></button>
						</div>
					</div>
					
					<div class="cell place-right">
						<button type="button" class="button primary" onclick="editEntity('client');">Modifier</button>
					</div>
				</div>
		</div>
	</form>
	
<div id="clientDeleteDialog">
	<div data-role="dialog" class="padding20" data-close-button="true" data-overlay="true" data-overlay-color="op-dark">
		<h1>êtes vous sur de vouloir supprimer l'enregistrement</h1>
		<div>
			<div class="cell place-right">
				<!-- <button type="button" class="button primary">Annuler</button> -->
				<button type="button" class="button alert" onclick="deleteEntity('client',adminSelectedItem);">Supprimer</button>
			</div>
		</div>
	</div>
</div>