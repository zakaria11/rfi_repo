	<form id="userAddDialog" data-role="validator">
		<div data-role="dialog" class="padding20" data-close-button="true" data-overlay="true" data-overlay-color="op-dark">
			<h1>Créer un utilisateur</h1>
				<div>
					<div class="cell">
						<label>Nom</label>
						<div class="input-control text full-size" data-role="input">
							<input data-validate-func="required" style="padding-right: 41px;" type="text" name="name">
							<button type="button" tabindex="-1" class="button helper-button clear"><span class="mif-cross"></span></button>
						</div>
					</div>
					<div class="cell">
						<label>Login</label>
						<div class="input-control text full-size" data-role="input">
							<input data-validate-func="required" style="padding-right: 41px;" type="text" name="username">
							<button type="button" tabindex="-1" class="button helper-button clear"><span class="mif-cross"></span></button>
						</div>
					</div>
					<div class="cell">
						<label>Password</label>
						<div class="input-control text full-size" data-role="input">
							<input style="padding-right: 41px;" type="password" name="password">
							<button type="button" tabindex="-1" class="button helper-button clear"><span class="mif-cross"></span></button>
						</div>
					</div>
					<div class="cell">
						<label>E-Mail</label>
						<div class="input-control text full-size" data-role="input">
							<input  data-validate-func="email" style="padding-right: 41px;" type="text" name="mail">
							<button type="button" tabindex="-1" class="button helper-button clear"><span class="mif-cross"></span></button>
						</div>
					</div>
					
					<div class="cell padding10" style="border: 1px solid #D9D9D9;">
						<label>Rôles</label>
						<br />
						<label class="input-control checkbox">
                            <input value="ADMIN" name="roles" type="checkbox" checked ><span class="check"></span><span class="caption"> Administer</span>
                        </label>
                        <br />
                        <label class="input-control checkbox">
                            <input value="BORDER" name="roles" type="checkbox"><span class="check"></span><span class="caption"> Borne</span><br>
                        </label>
                        <br />
                        <label class="input-control checkbox">
                            <input value="REPORTING" name="roles" type="checkbox"><span class="check"></span><span class="caption"> Visualiser les repports</span>
                        </label>
                        <br />
                        <label class="input-control checkbox">
                            <input value="EVENT" name="roles" type="checkbox"><span class="check"></span><span class="caption"> Réserver les événements</span>
                        </label>                  
                          
					</div>
										
					<div class="cell place-right">
						<button type="button" class="button primary" onclick="createEntity('user');">Créer</button>
					</div>
				</div>
		</div>
	</form>
	
		<form id="userEditDialog" data-role="validator">
		<div data-role="dialog" class="padding20" data-close-button="true" data-overlay="true" data-overlay-color="op-dark">
			<h1>Modifier une utilisateur</h1>
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
							<input id="name" data-validate-func="required" style="padding-right: 41px;" type="text" name="name">
							<button type="button" tabindex="-1" class="button helper-button clear"><span class="mif-cross"></span></button>
						</div>
					</div>
					<div class="cell">
						<label>Login</label>
						<div class="input-control text full-size" data-role="input">
							<input id="username" data-validate-func="required" style="padding-right: 41px;" type="text" name="username">
							<button type="button" tabindex="-1" class="button helper-button clear"><span class="mif-cross"></span></button>
						</div>
					</div>
					
					<div class="cell">
						<label>Password</label>
						<div class="input-control text full-size" data-role="input">
							<input id="password" data-validate-func="required" style="padding-right: 41px;" type="password" name="password">
							<button type="button" tabindex="-1" class="button helper-button clear"><span class="mif-cross"></span></button>
						</div>
					</div>

					<div class="cell">
						<label>E-Mail</label>
						<div class="input-control text full-size" data-role="input">
							<input id="mail" data-validate-func="email" style="padding-right: 41px;" type="text" name="mail">
							<button type="button" tabindex="-1" class="button helper-button clear"><span class="mif-cross"></span></button>
						</div>
					</div>
					
					<div class="cell padding10" style="border: 1px solid #D9D9D9;">
						<label>Rôles</label>
						<br />
						<label class="input-control checkbox">
                            <input value="ADMIN" name="roles" type="checkbox" checked ><span class="check"></span><span class="caption"> Administer</span>
                        </label>
                        <br />
                        <label class="input-control checkbox">
                            <input value="BORDER" name="roles" type="checkbox"><span class="check"></span><span class="caption"> Borne</span><br>
                        </label>
                        <br />
                        <label class="input-control checkbox">
                            <input value="REPORTING" name="roles" type="checkbox"><span class="check"></span><span class="caption"> Visualiser les repports</span>
                        </label>
                        <br />
                        <label class="input-control checkbox">
                            <input value="EVENT" name="roles" type="checkbox"><span class="check"></span><span class="caption"> Réserver les événements</span>
                        </label>                  
                          
					</div>
					
					<div class="cell place-right">
						<button type="button" class="button primary" onclick="editEntity('user');">Modifier</button>
					</div>
				</div>
		</div>
	</form>

<div id="userDeleteDialog">
	<div data-role="dialog" class="padding20" data-close-button="true" data-overlay="true" data-overlay-color="op-dark">
		<h1>êtes vous sur de vouloir supprimer l'enregistrement</h1>
		<div>
			<div class="cell place-right">
				<!-- <button type="button" class="button primary">Annuler</button> -->
				<button type="button" class="button alert" onclick="deleteEntity('user',adminSelectedItem);">Supprimer</button>
			</div>
		</div>
	</div>
</div>