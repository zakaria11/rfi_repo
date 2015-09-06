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
	
	plotGrid_Event = function(roomsList){
		var grid = jQuery("#grid").jqGrid({
			url : contextPath+'/event/listJson',
			datatype : "json",
			colNames : [ 'Id', 'date', 'price','name','description','state','places','Salles'],
			colModel : [ {name : 'id',key : true,index : 'id',width : 40, editable : false,search : false}, 
			             {name : 'date',key : true,width : 150,editable : true
							/*,editoptions : {size:20,dataInit:function(el){jQuery(el).datepicker({dateFormat:'yy-mm-dd'});}}*/
							,search : false
						 },
			             {name : 'price',width : 90,editable : true,search : false},
			             {name : 'name',width : 100,editable : true},
			             {name : 'description',width : 150,editable : true},
			             {name : 'state',width : 100,editable : true},
			             {name : 'places',width : 100,editable : true,search : false},
			             {name : 'room.name',width : 100,editable : true,edittype:"select",editoptions:{value:roomsList},search : false}
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
			caption : "liste des Événements",
			height : '65%',width : '100%',
			jsonReader : {repeatitems : false, root : "list"},
			onSelectRow: function(id){
				console.log(id);
			}

		});

		grid.jqGrid('navGrid', '#navGrid', {
			edit : true,
			add : true,
			del : true,
			search : false
		}, 
		{url : contextPath+"/event/edit"}, 
		{url : contextPath+"/event/add"}, 
		{url : contextPath+"/event/del"}, {});
		
		grid.jqGrid('filterToolbar',{searchOperators : true});

	}

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
