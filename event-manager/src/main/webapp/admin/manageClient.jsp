<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/includes/header.jsp"></jsp:include>
	<jsp:include page="/includes/administration_common.jsp"></jsp:include>
</head>
<body class="bg-white">
	<jsp:include page="/includes/admin_header.jsp"></jsp:include>
	<jsp:include page="/includes/clientDialog.jsp"></jsp:include>
    <div class="page-content">
        <div class="flex-grid no-responsive-future" style="height: 100%;">
            <div class="row" style="height: 100%">
                <jsp:include page="/includes/left_menu.jsp"></jsp:include>
                
                <div class="cell auto-size padding20 bg-white" id="cell-content">
                    <h1 class="text-light">Clients</h1>
                    <hr class="thin bg-grayLighter">
                    
                    <button class="button primary" onclick="initCreateEntity('client')"><span class="mif-plus"></span> Créer</button>
                    <button class="button warning" onclick="showUploadDialog();">Importer des clients</button>
   				    <form id="uploadClients" name="uploadClients" action="../upload/uploadClients" method="post" enctype="multipart/form-data" style="margin: 0; padding: 0;display:inline; width: 160px;">
    					<input id="selectClientsInput" name="upload" value="" type="file" class="button" onchange="initUploadClients();" style="display: none;">
					    <button id="uploadClientsButton"value="Importer" type="submit" class="button warning" style="display: none;">
					    	<span class="mif-file-upload"></span><!--  Importer les clients -->
					    </button>
					</form>
                    <button id="clientEditButton" class="button success" onclick="initEditEntity('client',adminSelectedItem,{});" style="display: none;"><span class="mif-pencil"></span> Modifier le client n°<b></b></button>
                    <button id="clientDeleteButton" class="button alert" onclick="initDeleteEntity('client',adminSelectedItem);" style="display: none;"><span class="mif-cancel"></span> Supprimer le client n°<b></b></button>
                    
                    <hr class="thin bg-grayLighter">
                    <table id="clientAdminTable" class="dataTable border bordered no-footer hovered"></table>
                    
<%--                     <br />
                    <br />
                    <hr class="thin bg-grayLighter">
                   	<h1 class="text-light"><span class="mif-file-upload place-left"></span> Importer les clients</h1>
    
				    <div class="input-control file" data-role="input">
				        <input type="file">
				        <button class="button"><span class="mif-folder"></span></button>
				    </div>
 --%>
                    
   					<div class="error"><s:property value="errorNotification" /></div>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="/includes/footer.jsp"></jsp:include>
	<script type="text/javascript">
		dataTables_Client();
	</script>
</body>
</html>