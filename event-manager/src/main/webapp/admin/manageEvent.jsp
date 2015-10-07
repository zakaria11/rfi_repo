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

	$.ajax({
		url: contextPath+'/room/list',
		data: {responseFormat:'SEMICOLON_MAP'},
		success: function(data) {
			plotGrid_Event(data);
		},
		dataType: 'text'
	});

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
	<jsp:include page="/includes/eventDialog.jsp"></jsp:include>
    <div class="page-content">
        <div class="flex-grid no-responsive-future" style="height: 100%;">
            <div class="row" style="height: 100%">
                <jsp:include page="/includes/left_menu.jsp"></jsp:include>
                
                <div class="cell auto-size padding20 bg-white" id="cell-content">
                    <h1 class="text-light">Événements<span class="mif-drive-eta place-right"></span></h1>
                    <hr class="thin bg-grayLighter">
                    <button class="button primary" onclick="initCreateEntity('event')"><span class="mif-plus"></span> Create</button>
                    <%-- <button class="button success" onclick="pushMessage('success')"><span class="mif-play"></span> Start</button> --%>
                    <button id="eventEditButton" class="button success" onclick="initEditEntity('event',adminSelectedEvent);" style="display: none;"><span class="mif-pencil"></span> Modifier l'événement n°<b></b></button>
                    <button id="eventDeleteButton" class="button alert" onclick="initDeleteEntity('event',adminSelectedEvent);" style="display: none;"><span class="mif-cancel"></span> Supprimer l'événement n°<b></b></button>
                    <%-- <button class="button warning" onclick="pushMessage('warning')"><span class="mif-loop2"></span> Restart</button> --%>
                    <hr class="thin bg-grayLighter">
                    <table id="eventAdminTable" class="dataTable border bordered no-footer hovered"></table>
   					<div class="error"><s:property value="errorNotification" /></div>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="/includes/footer.jsp"></jsp:include>
	<script type="text/javascript">
		dataTables_Event('#eventAdminTable');
	</script>
</body>
</html>