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
	<jsp:include page="/includes/userDialog.jsp"></jsp:include>

    <div class="page-content">
        <div class="flex-grid no-responsive-future" style="height: 100%;">
            <div class="row" style="height: 100%">
                <jsp:include page="/includes/left_menu.jsp"></jsp:include>
                
                <div class="cell auto-size padding20 bg-white" id="cell-content">
                    <h1 class="text-light">Utilisateur</h1>
                    <hr class="thin bg-grayLighter">
                    
                    <button class="button primary" onclick="initCreateEntity('user')"><span class="mif-plus"></span> Créer</button>
              		<button id="userEditButton" class="button success" onclick="initEditEntity('user',adminSelectedItem,{});" style="display: none;"><span class="mif-pencil"></span> Modifier l'utilidateur n°<b></b></button>
                    <button id="userDeleteButton" class="button alert" onclick="initDeleteEntity('user',adminSelectedItem);" style="display: none;"><span class="mif-cancel"></span> Supprimer l'utilidateur n°<b></b></button>
                    
                    <hr class="thin bg-grayLighter">
                    <table id="userAdminTable" class="dataTable border bordered no-footer hovered"></table>
   					<div class="error"><s:property value="errorNotification" /></div>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="/includes/footer.jsp"></jsp:include>
	<script type="text/javascript">	
		dataTables_User();
	</script>
</body>
</html>
 
 