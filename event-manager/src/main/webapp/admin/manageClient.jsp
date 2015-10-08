<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%@ page pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
  <head>
	<jsp:include page="/includes/header.jsp"></jsp:include>	
	<script type="text/javascript"></script>
  </head>
  <body>
  	<jsp:include page="/includes/head_menu.jsp"></jsp:include>
    <div class="container center" id="main_container">
    	<table id="grid"></table>
		<div id="navGrid"></div>
   		<div class="error"><s:property value="errorNotification" /></div>
    </div>
    <div id="modalContainer"></div>
	<jsp:include page="/includes/footer.jsp"></jsp:include>
	<script type="text/javascript">
	var grid = jQuery("#grid").jqGrid({
		url : contextPath+'/client/list',
		datatype : "json",
		colNames : [ 'Id', 'Nom', 'Prénom','CNE','CIN','Rôle'],
		colModel : [ {name : 'id',key : true,index : 'id',width : 100, editable : false,search:false}, 
		             {name : 'firstName',key : true,width : 100,editable : true},
		             {name : 'lastName',width : 100,editable : true},
		             {name : 'cne',width : 120,editable : true},
		             {name : 'cin',width : 100,editable : true},
		             {name : 'roleId',width : 100,editable : true}
		           ],
		rowNum : 50,
		width : 700,
		height : 700,
		rowList : [ 10, 20, 30 ],
		pager : '#navGrid',
		sortname : 'id',
		viewrecords : true,
		sortorder : "desc",
		jsonReader : { repeatitems : false},
		prmNames : { addoper : "add", deloper : "del", editoper : "edit"},
		caption : "liste des Clients",
		height : '65%',width : '100%',
		jsonReader : {repeatitems : false, root : "list"}

	});

	grid.jqGrid('navGrid', '#navGrid', {
		edit : true,
		add : true,
		del : true,
		search : false
	}, 
	{url : contextPath+"/client/edit"}, 
	{url : contextPath+"/client/add"}, 
	{url : contextPath+"/client/del"}, {});
	
	grid.jqGrid('filterToolbar',{searchOperators : true});
	</script>
  </body>
</html>
 --%>
 
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
                    <%-- <button class="button warning" onclick="alert('import')"><span class="mif-file-upload"></span> Importer les clients</button> --%>
   				    <div class="input-control file place-right" data-role="input">
				        <input type="file">
				        <button class="button"><span class="mif-file-upload"></span></button>
				    </div>                 
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