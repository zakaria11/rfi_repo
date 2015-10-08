<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/includes/header.jsp"></jsp:include>
	<jsp:include page="/includes/administration_common.jsp"></jsp:include>
</head>
<body class="bg-steel">
	<jsp:include page="/includes/admin_header.jsp"></jsp:include>
	<jsp:include page="/includes/roomDialog.jsp"></jsp:include>
        
    <div class="page-content">
        <div class="flex-grid no-responsive-future" style="height: 100%;">
            <div class="row" style="height: 100%">
                <jsp:include page="/includes/left_menu.jsp"></jsp:include>
                
                <div class="cell auto-size padding20 bg-white" id="cell-content">
                    <h1 class="text-light">Salles<span class="mif-drive-eta place-right"></span></h1>
                    <hr class="thin bg-grayLighter">
                    
                    <button class="button primary" onclick="initCreateEntity('room')"><span class="mif-plus"></span> Créer</button>
              		<button id="roomEditButton" class="button success" onclick="initEditEntity('room',adminSelectedItem,{});" style="display: none;"><span class="mif-pencil"></span> Modifier la salle n°<b></b></button>
                    <button id="roomDeleteButton" class="button alert" onclick="initDeleteEntity('room',adminSelectedItem);" style="display: none;"><span class="mif-cancel"></span> Supprimer la salle n°<b></b></button>
                    
                    <hr class="thin bg-grayLighter">
                    <table id="roomAdminTable" class="dataTable border bordered no-footer hovered"></table>
   					<div class="error"><s:property value="errorNotification" /></div>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="/includes/footer.jsp"></jsp:include>
	<script type="text/javascript">	
		dataTables_Room();
	//plotGrid_Room();
	</script>
</body>
</html>
 