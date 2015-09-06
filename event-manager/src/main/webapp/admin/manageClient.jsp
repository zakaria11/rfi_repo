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
