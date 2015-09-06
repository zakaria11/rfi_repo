<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags" %>


   		<div class="control-group">
			<label class="control-label" for="inputCNE">CNE</label>
			<div class="controls">
				<s:textfield id="inputCNE" placeholder="" disabled="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="inputCIN">CIN</label>
			<div class="controls">
				<s:textfield id="inputCIN" placeholder="" disabled="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="inputFirstName">Nom</label>
			<div class="controls">
				<s:textfield id="inputFirstName" placeholder="" disabled="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="inputLastName">Prénom</label>
			<div class="controls">
				<s:textfield id="inputLastName" placeholder="" disabled="true"/>
			</div>
		</div>
		<div class="control-group">
			<div class="controls">
				<a class="btn disabled">Valider</a>
				<a class="btn" onclick="showSearchClientGrid();">Rechercher un client</a>
			</div>
		</div>
