function loading(){
    $('#loadingDiv').show(); 
}

function unloading(){
    $('#loadingDiv').hide(); 
}

plotGrid_Room = function(roomsList){
	var grid = $("#grid").jqGrid({
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
};

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

};

function showEventsEditPopUp(){
    $("#editContainer").load( "edit.jsp" ,function() {
	    $('#edit').modal();
    });
}
function initBooking(eventId){
	window.location.href = contextPath+'/event/booking?eveetId='+eventId;
}


function isNumeric(n) {
  return !isNaN(parseFloat(n)) && isFinite(n);
}
function validateForm(target){
	var isValidflag = true;
	//validation
	$(".validationError").remove();
	$(target+" input").each(function(index, element) {	
		var inputElement = $(element).attr("validations");
		if(typeof inputElement != 'undefined'){
			//check number
			if(inputElement.indexOf("number") > -1){
				if(!isNumeric($(element).val())){
					$(element).parent().append("<div class='validationError'>Invalid number</div>");
					isValidflag = false;
				}
			}
			//check required field
			if(inputElement.indexOf("required") > -1){
				if($(element).val() == ""){
					$(element).parent().append("<div class='validationError'>Field required</div>");
					isValidflag = false;
				}
			}
		}
	});
	return isValidflag;
}

printTicket = function(){
	window.location = contextPath+'/print/printTicket';
};
lineBreakFmatter = function(cellvalue, options, rowObject ){
	return cellvalue.replace(/\s/,'<br />');
};

userRoleFormatter = function(cellvalue, options, rowObject){
	var ulDiv = '<ul>';
	$.each( cellvalue, function( k,v ){
		ulDiv+= '<li>';
		ulDiv+= v.role;
		ulDiv+= '</li>';
	});
	ulDiv += '</ul>';
	return ulDiv;
};


showEvents = function(currentDiv){
	//	$(".home-tiles").animate({opacity: 'hide', height: 'hide'}, 'fast');
	// $("#eventsList").animate({opacity: 'show', height: 'show'}, 'fast');

	$("#eventsList").fadeIn("fast",function(){
		//$(".home-tiles").animate({opacity: 'hide', height: 'hide'}, 'slow');
		$(".home-tiles").animate({width:"hide",opacity:0},150,"swing");			
	});
};


(function($) {
    $.StartScreen = function(){
        var plugin = this;
        var width = (window.innerWidth > 0) ? window.innerWidth : screen.width;

        plugin.init = function(){
            setTilesAreaSize();
            if (width > 640) addMouseWheel();
        };

        var setTilesAreaSize = function(){
            var groups = $(".tile-group");
            var tileAreaWidth = 80;
            $.each(groups, function(i, t){
                if (width <= 640) {
                    tileAreaWidth = width;
                } else {
                    tileAreaWidth += $(t).outerWidth() + 80;
                }
            });
            $(".tile-area").css({
                width: tileAreaWidth
            });
        };

        var addMouseWheel = function (){
            $("body").mousewheel(function(event, delta, deltaX, deltaY){
                var page = $(document);
                var scroll_value = delta * 50;
                page.scrollLeft(page.scrollLeft() - scroll_value);
                return false;
            });
        };

        plugin.init();
    }
})(jQuery);

initCreateEntity = function(entityName){
	var dialog = $('#'+entityName+'AddDialog').children().data('dialog');
	if(entityName == 'event'){
		$.ajax({
			url: contextPath+'/room/list',
			data: {responseFormat:'SELECT2_MAP'},
			success: function(roomList) {
				console.log($("#roomList"));
				$.each( roomList, function( key, value ) {
					$("#roomList").append($('<option></option>').val(value.id).html(value.text));
				});		
			},
			dataType: 'json'
		});
	}
    dialog.open();
};

initEditEntity = function(entityName,entityId,columns){
	//Load entity using entityId
	//Fill the Edit Popup
	$.ajax({
		url: contextPath+'/entity/load',
		data: {entityName:entityName,entityId : entityId},
		beforeSend : function(){
			//TODO loading
		},
		complete : function(){
			//TODO unloading
		},
		success: function(entity){
			var editDialog = $('#'+entityName+'EditDialog').children();
			$.each( entity, function( key, value ) {
				editDialog.find('#'+key).val(value);					
			});
			
			$.each( columns, function( key, value ) {
				var evalStr = 'var att = entity.'+value+';';
				eval(evalStr);
				editDialog.find('#'+key).val(att);
			});
			

			editDialog.data('dialog').open();
		},
		dataType: 'json'
	});
	
};

editEntity = function(entityName){
	form = $('#'+entityName+'EditDialog');
	
	var params = {};
	var serArray = form.serializeArray();
	$.each(serArray, function(key, value){
		var n =serArray[key].name;
		var v =serArray[key].value;
		if(n.indexOf("_") > -1){
			var re = new RegExp('_', 'g');
			n = n.replace(re, '.');
		}
		params[entityName+'.'+n] = v;
	});
	params['entityName']=entityName;
	
	$.ajax({
		url : contextPath+'/entity/modify',
		method  : 'POST',
		data : params,
		dataType : "json",
		success : function(response){
			form.children().data('dialog').close();
			var table = $('#'+entityName+'AdminTable').DataTable();
			console.log(table);
			table.ajax.reload();
		},
		error : function(response){
			
		},
		complete : function(){
			//TODO unloading
		}
	});
};

initDeleteEntity = function(entityName,entityId){
	var dialog = $('#'+entityName+'DeleteDialog').children().data('dialog');
    dialog.open();
};

deleteEntity = function(entityName,entityId){
	var dialog = $('#'+entityName+'DeleteDialog').children().data('dialog');
	$.ajax({
		url: contextPath+'/entity/delete',
		data: {
			entityId:entityId,
			entityName:entityName
		},
		success: function(resp){
        	$("#"+entityName+"EditButton").animate({opacity: 'hide', height: 'hide'}, 'fast');
        	$("#"+entityName+"DeleteButton").animate({opacity: 'hide', height: 'hide'}, 'fast');
        	var table = $('#eventAdminTable').DataTable();
        	  table.ajax.reload();
			dialog.close();		
		},
		error : function(resp){
			dialog.close();		
		},
		dataType: 'json'
	});		
};

createEntity = function(entityName){
	form = $('#'+entityName+'AddDialog');
	
	$.ajax({
		url : contextPath+'/'+entityName+'/add',
		method  : 'POST',
		data : form.serializeArray(),
		dataType : "json",
		success : function(response){
			form.children().data('dialog').close();
			var table = $('#'+entityName+'Table').DataTable();
			table.ajax.reload();
		},
		error : function(response){
			
		}
	});
};

dataTables_Room = function(){
	$('#roomTable').dataTable({
		"ajaxSource": contextPath+"/room/list",
		"columns": [
		    { "data": "id", "title": "Id" ,"width": "70px"},
		    { "data": "name", "title": "Name", "width": "140px" },
		    { "data": "description", "title": "Description"},
		    { "data": "state", "title": "State"}
		  ]
	});
};

dataTables_Event = function(){
	
	 var table = $('#eventAdminTable').dataTable({
		"ajaxSource": contextPath+"/event/list",
		"columns": [
		    { "data": "id", "title": "Id" ,"width": "70px"},
		    { "data": "date", "title": "Name", "width": "140px" },
		    { "data": "price", "title": "Prix"},
		    { "data": "name", "title": "Nom"},
		    { "data": "description", "title": "Description"},
		    { "data": "state", "title": "Statut"},
		    { "data": "places", "title": "Nbr places"},
		    { "data": "room.name","sDefaultContent": "-", "title": "Salle"}
		  ]
	});
	
	 var table = $('#eventAdminTable').DataTable();
	    $('#eventAdminTable tbody').on('click', 'tr', function () {
	        var data = table.row(this).data();
	        adminSelectedEvent = data.id;
	        if(adminSelectedEvent != null){
	        	$("#eventEditButton b").html(adminSelectedEvent);
	        	$("#eventEditButton").animate({opacity: 'show', height: 'show'}, 'fast');
	        	$("#eventDeleteButton b").html(adminSelectedEvent);
	        	$("#eventDeleteButton").animate({opacity: 'show', height: 'show'}, 'fast');
	        }
	        $("tr").removeClass("selected");
        	$(this).addClass("selected");
	    });
};

dataTables_Choice_Event = function(){
	 var table = $('#eventChoiceTable').dataTable({
			"ajaxSource": contextPath+"/event/list",
			"scrollY":"380px",
	        "scrollCollapse": true,
			"columns": [
			    { "data": "id", "title": "Id" ,"width": "70px"},
			    { "data": "date", "title": "Name", "width": "140px" },
			    { "data": "price", "title": "Prix"},
			    { "data": "name", "title": "Nom"},
			    { "data": "state", "title": "Statut"},
			    { "data": "places", "title": "Nbr places"},
			    { "data": "remainingPlaces", "title": "Nbr places restantes"},
			    { "data": "room.name","sDefaultContent": "-", "title": "Salle"}
			  ]
		});
		
	 	var table = $('#eventChoiceTable').DataTable();
	    $('#eventChoiceTable tbody').on('click', 'tr', function () {
		    var data = table.row(this).data();
        	$("tr").removeClass("selected");
        	$(this).addClass("selected");
        	printEventDetails(data.id);
		});
};

updateRooms = function(div){
	$(div).html('');
	$.ajax({
		url: contextPath+'/room/list',
		data: {responseFormat:'SELECT2_MAP'},
		success: function(roomList) {
			$.each( roomList, function( key, value ) {
				console.log($('<option>').val(value.id).html(value.text));
				$(div).append($('<option>').val(value.id).html(value.text));
			});					
		},
		dataType: 'json'
	});
};

goHome = function(){
	window.location = contextPath+"/index.jsp";
};

/* reporting */

function getDefaultGraphOptions() {
	return {
		seriesDefaults : {
			renderer : $.jqplot.LineRenderer,
			rendererOptions : {},
			markerOptions : {
				size : 10
			}
		},
		legend : {
			placement : 'outside',
			show : true,
			xoffset : 12,
			yoffset : 12
		},
		title : '',
		animate : true,
		animateReplot : true,
		axes : {
			xaxis : {
				tickInterval : 1000 * 60 * 60 * 24,
				tickOptions : {
					angle : -50,
					fontSize : '9pt',
					formatString : '%Y-%m-%d'
				},
				renderer : $.jqplot.DateAxisRenderer,
				rendererOptions : {
					tickInset : 0.5
				},
				tickRenderer : $.jqplot.CanvasAxisTickRenderer
			},
			yaxis : {
				labelRenderer : $.jqplot.CanvasAxisLabelRenderer,
				tickOptions : {
					formatString : '%.8f'
				},
				rendererOptions : {
					tickInset : 0.5
				}
			}
		},
		cursor : {
			zoom : true,
			showTooltip : true,
			showVerticalLine : true
		}
	};
}

plotGraph = function(target) {

	$.ajax({
		url : contextPath+"/reporting/eventHistory",
		dataType : "json",
		data : '',
		success : function(response) {
			//unloading
		try{
			options = getDefaultGraphOptions();
//			if(step == 'month'){
//				options.axes.xaxis.tickInterval = 1000*60*60*24*30;	
//			}
			options.axes.yaxis.tickOptions.formatString = '%d';
			options.legend.labels = response.labels;
			options.seriesColors = response.colors;
			options.legend.location = 'n';
			options.legend.placement = 'outside';			
			$.jqplot.config.enablePlugins = true;
			$.jqplot._noToImageButton = false;
			//$("#" + target).empty();
			plot1 = $.jqplot(target,response.seriesData, options);
		}catch(ex){
			console.log(ex);
		}
		},
		error : function(errorObject){
			//unloading
		}
	});
};