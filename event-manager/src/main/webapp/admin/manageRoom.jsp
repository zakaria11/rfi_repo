<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	
	plotGrid_Room = function(roomsList){
		var grid = jQuery("#grid").jqGrid({
			url : contextPath+'/room/list',
			datatype : "json",
			colNames : [ 'Id', 'Nom', 'Description','Statut'],
			colModel : [ {name : 'id',key : true,index : 'id',width : 40, editable : false,search:false}, 
			             {name : 'name',key : true,width : 150,editable : true},
			             {name : 'description',width : 380,editable : true,edittype:"textarea",editable : true},
			             {name : 'state',width : 100,editable : true,editable : true}
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
			caption : "liste des Salles",
			height : '65%',width : '100%',
			jsonReader : {repeatitems : false, root : "list"},
			onSelectRow: function(id){}

		});

		grid.jqGrid('navGrid', '#navGrid', {
			edit : true,
			add : true,
			del : true,
			search : false
		},{/*Edit options*/width: 700}, { width: 700 }, 
		{url : contextPath+"/room/edit"}, 
		{url : contextPath+"/room/add"}, 
		{url : contextPath+"/room/del"}, {});
		
		grid.jqGrid('filterToolbar',{searchOperators : true});

	}
	
	plotGrid_Room();

	</script>
  </body>
</html>
