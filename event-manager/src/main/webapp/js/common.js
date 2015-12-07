function loading(){
    $('#loadingDiv').show(); 
}

function unloading(){
    $('#loadingDiv').hide(); 
}

onRateEvent = function(value, star, widget){
	$('#eventRatingInput').val(value);
	$('#rating').val(value);
	
};


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
		colModel : [ {name : 'id',key : true,index : 'id',width : 40, editable : false,search : false,order:"desc"}, 
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

goToPaymentStep = function(){
	window.location = contextPath+'/booking/paymentChoice?selectedClientId='+selectedClientId;
};

showTicketInfos = function(resp){
	var infosDiv = $('#ticketInfoArea');
	infosDiv.empty(); 
	infosDiv.append('<p>ticket N °<span>'+resp.subscription.id+'</span></p>');
	infosDiv.append('<p>Nom de l\'événement : <span>'+resp.subscription.event.name+'</span></p>');
	infosDiv.append('<p>Prix : <span>'+resp.subscription.event.price+'</span></p>');
	infosDiv.append('<p>Salle : <span>'+resp.subscription.event.room.name+'</span></p>');
	infosDiv.append('<p>Description : <span>'+resp.subscription.event.description+'</span></p>');
	
	$("#paymentTypesGroup").animate({opacity: 'hide', height: 'hide'}, 'fast', function() {
		$("#ticketInfosGroup").animate({opacity: 'show', height: 'show'}, 'fast');
	  });
	
};
      
executePayment = function(PaymentType,eventId){
	$.ajax({
		url: contextPath+'/booking/executePayment',
		data: {
			paymentMethod:PaymentType,
			eventId : eventId
		},
		success: function(resp){
			$('#dialog2').data('dialog').close();
			if(resp.isOK == "1"){
				//dialog.removeClass("alert");
				//dialog.addClass("success");
				//dialog.find("#content1").html('<button class="button" onclick="identificationStep()">Informations sur le ticket</button>');    					
				showTicketInfos(resp);
			}else{
				var dialog = $('#dialog');
				dialog.addClass("alert");
				dialog.find("h1:first").html(resp.message);
					
				var dialogData = dialog.data('dialog');
					dialogData.open();    					
			}
		},
		error : function(){
			$('#dialog2').data('dialog').close();
			var dialog = $('#dialog');
				var dialogData = dialog.data('dialog');
				dialog.addClass("alert");
				dialog.find("h1:first").html("Une erreur est survenu, merci de reessayer ultérieurement");
				dialogData.open();
		},
		dataType: 'json'
	});
};


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
    var editdialog= $('#'+entityName+'AddDialog');
	editdialog.find("input[type=text], textarea").val("");
	var dialog = editdialog.children().data('dialog');
	
	//call custom functions
	funcName = entityName+'CustomInitCreat';			
	if (typeof window[funcName] == 'function') {
		eval(funcName+'(entityName);');
	}
		
	dialog.open();	
    
};






addRoomSelectHundler = function(){
    $(".roomsList").select2({
        ajax: {
          url: contextPath+'/room/list',
          dataType: 'json',
          delay: 250,
          data: function (params) {
            return {
              name: params.term, // search term
              page: params.page,
              _search : 'true'
            };
          },
          processResults: function (data, page) {
            return {
              results: data.data
            };
          },
          cache: true
        },
        escapeMarkup: function (markup) { return markup; },
        minimumInputLength: 1,
        templateResult: function(repo){
            if (repo.loading) return repo.text;
            var markup = '<div class="clearfix"><div>'+repo.name +'</div>';
            return markup;
        },
        templateSelection: function(repo) {
        	return repo.name;
        } 
	});
};
addStateSelectHundler = function(){
    $(".statesList").select2({
        ajax: {
          url: contextPath+'/state/list',
          dataType: 'json',
          delay: 250,
          data: function (params) {
            return {
              description: params.term, // search term
              page: params.page,
              _search : 'true'
            };
          },
          processResults: function (data, page) {
            return {
              results: data.data
            };
          },
          cache: true
        },
        escapeMarkup: function (markup) { return markup; },
        minimumInputLength: 1,
        templateResult: function(repo) {
            if (repo.loading) return repo.text;
            var markup = '<div class="clearfix"><div>'+repo.name +'</div>';
            return markup;
        },
        templateSelection: function(repo){
        	return repo.name;
        } 
	});
}

addTagSelectHundler = function(){
    $(".tagsList").select2({
        ajax: {
          url: contextPath+'/tag/list',
          dataType: 'json',
          delay: 250,
          data: function (params) {
            return {
              name: params.term, // search term
              page: params.page,
              _search : 'true'
            };
          },
          processResults: function (data, page) {
            return {
              results: data.data
            };
          },
          cache: true
        },
        escapeMarkup: function (markup) { return markup; },
        minimumInputLength: 1,
        templateResult: function(repo) {
            if (repo.loading) return repo.text;
            var markup = '<div class="clearfix"><div>'+repo.name +'</div>';
            return markup;
        },
        templateSelection: function(repo){
        	return repo.name;
        } 
	});
};


eventCustomInitCreat = function(eventEntityName){
	addRoomSelectHundler();
	addStateSelectHundler();
	addTagSelectHundler();
};

eventCustomInitEdit = function(eventEntity){
	var score = eventEntity.rating;
	var rating = $('#eventRatingEdit').data('rating');
	rating.value(score);
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

			var $select = editDialog.find('.js-select');
			$select.each(function(i,item){
				var dataSource = $(item).attr("dataSource");
				var select2options = {
				        //placeholder : showedText,
						ajax: {
				          url: contextPath+'/'+dataSource+'/list',
				          dataType: 'json',
				          delay: 250,
				          data: function (params) {
				            return {
				              name: params.term, // search term
				              page: params.page,
				              _search : 'true'
				            };
				          },
				          processResults: function (data, page) {
				            return {
				              results: data.data
				            };
				          },
				          cache: true
				        },
				        escapeMarkup: function (markup) { return markup; },
				        minimumInputLength: 1,
				        templateResult: function(repo){
				            if (repo.loading) return repo.text;
				            var markup = '<div class="clearfix"><div>'+repo.name +'</div>';
				            return markup;
				        },
				        templateSelection: function(repo) {
				        	return repo.name || repo.text;
				        } 
					};			
					var option = $('<option selected></option>');
					option.text("");
					option.attr({"value":""});
					$(item).append(option);
					$(item).select2(select2options);
					$(item).trigger('change');
			});

			
			for (var key in entity) {
				var value = entity[key];
				//if typeof val == object
				if(entity.hasOwnProperty(key) && typeof entity[key] == "object"){					
					// loop on attributes and replace . it with _
					// then test if the target input equals <select>
					for (var subKey in entity[key]){
						var subValue = entity[key][subKey];
						var inputTag = editDialog.find('#'+key+"_"+subKey);

						if(inputTag.prop("type") == "select-one"){
							var option = $('<option selected></option>');
							var showedText= entity[key]['name'];

							option.text(showedText);
							option.attr({"value":entity[key][subKey]});							
							inputTag.empty();
							inputTag.append(option);
							var dataSource = inputTag.attr("dataSource");
							var select2options = {
						        //placeholder : showedText,
								ajax: {
						          url: contextPath+'/'+dataSource+'/list',
						          dataType: 'json',
						          delay: 250,
						          data: function (params) {
						            return {
						              name: params.term, // search term
						              page: params.page,
						              _search : 'true'
						            };
						          },
						          processResults: function (data, page) {
						            return {
						              results: data.data
						            };
						          },
						          cache: true
						        },
						        escapeMarkup: function (markup) { return markup; },
						        minimumInputLength: 1,
						        templateResult: function(repo){
						            if (repo.loading) return repo.text;
						            var markup = '<div class="clearfix"><div>'+repo.name +'</div>';
						            return markup;
						        },
						        templateSelection: function(repo) {
						        	return repo.name || repo.text;
						        } 
							};
							inputTag.select2(select2options);
							inputTag.trigger('change');
							
//							$.fn.select2.amd.require(
//							    	['select2/data/array', 'select2/utils'],
//							    	function (ArrayData, Utils) {
//						    		 function CustomData ($element, options) {
//						    		    CustomData.__super__.constructor.call(this, $element, options);
//						    		  }
//						    		  Utils.Extend(CustomData, ArrayData);
//						    		  CustomData.prototype.current = function (callback) {
//						    			  console.log(subKey);
//						    		    var data = [];
//						    		      data.push({
//						    		        id: "ee",
//						    		        text: "ss"
//						    		      });
//						    		    callback(data);
//						    		  };
//						    		  $('#state_id').select2({
//						    		    dataAdapter: CustomData
//						    		  });
//							});
						}						
					}
			
				}else{
					editDialog.find('#'+key).val(value);
				}
			}		    
//			$.each( columns, function( key, value ) {
//				var evalStr = 'var att = entity.'+value+';';
//				eval(evalStr);
//				editDialog.find('#'+key).val(att);
//			});
					
			//call custom functions
			funcName = entityName+'CustomInitEdit';			
			if (typeof window[funcName] == 'function') {
				eval(funcName+'(entity);');
			}
						
			editDialog.data('dialog').open();
		},
		dataType: 'json'
	});
	
};

editEntity = function(entityName){
	form = $('#'+entityName+'EditDialog');
    var params = new FormData();
	$.each(form.prop('elements'), function(key, tag){
		if($(tag).prop("tagName").toLowerCase() != "button"){
			var inputName = $(tag).attr("name");
			var inputType = $(tag).attr("type");
			var inputValue = $(tag).val();
			if(inputName.indexOf("_") > -1){
				var re = new RegExp('_', 'g');
				inputName = inputName.replace(re, '.');
			}
			if(inputType == "file"){
				if(files != null){
					params.append(inputName, files[0]);
				}
			}else{
				params.append(entityName+'.'+inputName, inputValue);
			}				
		}
	});
	params.append('entityName', entityName);
	
	$.ajax({
		url : contextPath+'/entity/modify',
		type  : 'POST',
		data : params,
		dataType : "json",
		cache: false,
		processData: false,
		contentType: false,
		success : function(response){
			form.children().data('dialog').close();
			var table = $('#'+entityName+'AdminTable').DataTable();
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
        	var table = $('#'+entityName+'AdminTable').DataTable();
        	  table.ajax.reload();
			dialog.close();		
		},
		error : function(resp){
			dialog.close();		
		},
		dataType: 'json'
	});		
};

prepareUpload = function(event){
  files = event.target.files;
}

createEntity = function(entityName){
	form = $('#'+entityName+'AddDialog');
	
    var params = new FormData();
	$.each(form.prop('elements'), function(key, tag){
		if($(tag).prop("tagName").toLowerCase() != "button"){
			var inputName = $(tag).attr("name");
			var inputType = $(tag).attr("type");
			var inputValue = $(tag).val();
			if(inputName.indexOf("_") > -1){
				var re = new RegExp('_', 'g');
				inputName = inputName.replace(re, '.');
			}
			if(inputType == "file"){
				if(files != null){
					params.append(inputName, files[0]);
				}
			}else{
				params.append(entityName+'.'+inputName, inputValue);
			}

		}
	});
	params.append('entityName', entityName);
	
	if(form.data("validator").validateForm()){
		$.ajax({
			url : contextPath+'/entity/add',
			type  : 'POST',
			data : params,
			dataType : "json",
			cache: false,
			processData: false,
			contentType: false,
			success : function(response){
				form.children().data('dialog').close();
				var table = $('#'+entityName+'Table').DataTable();
				table.ajax.reload();
			},
			error : function(response){
				//form.children().data('dialog').close();
			}
		});
	}
};

crudHundlers = function(entityName){
	var table = $('#'+entityName+'AdminTable').DataTable();
	$('#'+entityName+'AdminTable tbody').on('click', 'tr', function () {
        var data = table.row(this).data();
        adminSelectedItem = data.id;
        if(adminSelectedItem != null){
        	$("#"+entityName+"EditButton b").html(adminSelectedItem);
        	$("#"+entityName+"EditButton").animate({opacity: 'show', height: 'show'}, 'fast');
        	$("#"+entityName+"DeleteButton b").html(adminSelectedItem);
        	$("#"+entityName+"DeleteButton").animate({opacity: 'show', height: 'show'}, 'fast');
        }
        $("tr").removeClass("selected");
    	$(this).addClass("selected");
    });
};

dataTables_Event = function(){
	
	 var table = $('#eventAdminTable').dataTable({
		"order": [[ 0, 'desc']],
		"ajax": {
            "url": contextPath+"/event/list",
            "data": function ( d ){
                d.sourceView = "ADMIN";
            }
		},
		"columns": [
		    { "data": "id", "title": "Id" ,"width": "70px",
		    	"fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
		    		$(nTd).html("<a href='"+contextPath+"/entity/load?entityFormat=htmlTableDetails&entityName=event&entityId="+oData.id+"'>"+oData.id+"</a>");
		    	}
		    },
		    { "data": "name", "title": "Nom"},
		    { "data": "date", "title": "Date", "width": "140px",
		      "render": function (data) {
		            var date = new Date(data);
		            var month = date.getMonth() + 1;
		            return date.getDate() + "/" + month + "/" +  date.getFullYear();
		        }
		    },
		    { "data": "price", "title": "Prix"},
		    { "data": "description", "title": "Description"},
		    { "data": "rating", "title": "Importance", "width": "70px"},
		    { "data": "places", "title": "Nbr places"},
		    { "data": "room.name","sDefaultContent": "-", "title": "Salle"},
		    { "data": "state.name","sDefaultContent": "-", "title": "Statut"}
		  ]
	});
	crudHundlers('event');	
};
dataTables_Room = function(){
	$('#roomAdminTable').dataTable({
		"ajaxSource": contextPath+"/room/list",
		"columns": [
		    { "data": "id", "title": "Id" ,"width": "70px",
		    	"fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
		    		$(nTd).html("<a href='"+contextPath+"/entity/load?entityFormat=htmlTableDetails&entityName=room&entityId="+oData.id+"'>"+oData.id+"</a>");
		    	}
		    },
		    { "data": "name", "title": "Name", "width": "140px" },
		    { "data": "description", "title": "Description"},
		    { "data": "state", "title": "State"}
		  ]
	});
	crudHundlers('room');
};

listFormatter = function ( data, type, row ) {
	var ulDiv = '<div class="list-group-content">';
	$.each(row.userRole, function( k,v ){
		ulDiv+= '<div class="list"><div class="list-content"><span class="list-title">';
		ulDiv+= v.role;
		ulDiv+= '</span></div></div>';
	});
	ulDiv += '</ul>';
	return ulDiv;
  };

dataTables_User = function(){
	$('#userAdminTable').dataTable({
		"ajaxSource": contextPath+"/user/list",
		"columns": [
		    { "data": "id", "title": "Id" ,"width": "70px",
		    	"fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
		    		$(nTd).html("<a href='"+contextPath+"/entity/load?entityFormat=htmlTableDetails&entityName=user&entityId="+oData.id+"'>"+oData.id+"</a>");
		    	}
		    },
		    { "data": "username", "title": "Utilisateur"},
		    { "data": "userRole", "title": "Rôls","render": listFormatter},
		    { "data": "mail", "title": "Email"},
		    { "data": "name", "title": "Nom", "width": "140px" },
		    { "data": "description", "title": "Description"}
		  ]
	});
	crudHundlers('user');
};

dataTables_Client = function(){
	$('#clientAdminTable').dataTable({
		"ajaxSource": contextPath+"/client/list",
		"columns": [
		    { "data": "id", "title": "Id" ,"width": "70px",
		    	"fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
		    		$(nTd).html("<a href='"+contextPath+"/entity/load?entityFormat=htmlTableDetails&entityName=client&entityId="+oData.id+"'>"+oData.id+"</a>");
		    	}
		    },
		    { "data": "firstName", "title": "Nom", "width": "140px" },
		    { "data": "lastName", "title": "Prénom"},
		    { "data": "cne", "title": "CNE"},
		    //{ "data": "cin", "title": "CIN"},
		    { "data": "orderNumber", "title": "Numéro D'ordre"},
		    { "data": "mail", "title": "Mail"},
		    { "data": "gender", "title": "Civil"}
		  ]
	});
	crudHundlers('client');
};

showEventDetailsPopUp = function(eventId){
	$.ajax({
		url: contextPath+'/entity/load',
		data: {entityName:'event',entityId : eventId,entityFormat:'json'},
		beforeSend : function(){
			//TODO
		},
		complete : function(){
			//TODO
		},
		success: function(data){
			var dialog = $('#dialog2');
			var dialogData = dialog.data('dialog');
			dialog.addClass("info");
			dialog.find("h1:first").empty();
			dialog.find("h1:first").html(data.name+" #"+eventId);
			dialog.find("p:first").empty();
			$.each(data.attributes, function( key, value ) {
				if(value.label == "Image"){
					dialog.find("img:first").attr({"src":"../uploads/event/images/"+value.value});
				}else{
					dialog.find("p:first").append(value.label+" : "+value.value+'<br />');
				}
			});
			dialog.find("#controls").empty();
			var executeButton = $('<button class="shortcut-button bg-white bg-active-darkBlue fg-cyan place-right"></button>');
			executeButton.on( "click", function() {
				executePayment('BORDER',eventId);
			});
			executeButton.append('<span class="icon mif-rocket"></span><span class="title">Réserver</span>')
			dialog.find("#controls").append(executeButton);			
			dialogData.open();
		},
		dataType: 'json'
	});
};

showEventsBorderCalendar = function(){
	$('#mainContainer').empty();
	$('#borderCalednar').show();
	var currentDate = new Date();
	reloadEventsBorderChoiceByMonth(currentDate.getMonth()+1,currentDate.getFullYear());	
};


reloadEventsBorderChoiceByDay = function(short, full){
	$.ajax({
		cache: false,
		url: contextPath+'/event/list',
		data: {grouped:'YES',groupBy : 'DATE',zoom :'day',selectedDate: short,sourceView : "BORDER"},
		beforeSend : function(){
			$('#mainContainer').empty();
			//$('#borderCalednar').hide();
			//TODO loading
		},
		complete : function(){
			//TODO unloading
		},
		success: function(data){
			printEventsGrouped(data,'DATE');
		},
		dataType: 'json'
	});

	console.log(full);
};

reloadEventsBorderChoiceByMonth = function(month,year){
	month
	$.ajax({
		cache: false,
		url: contextPath+'/event/list',
		data: {grouped:'YES',groupBy : 'DATE',zoom :'month',selectedDate: year+'-'+month,sourceView : "BORDER"},
		beforeSend : function(){
			$('#mainContainer').empty();
			//$('#borderCalednar').hide();
			//TODO loading
		},
		complete : function(){
			//TODO unloading
		},
		success: function(data){
			printEventsGrouped(data,'DATE');
		},
		dataType: 'json'
	});
};

printEventsGrouped = function(data,groupBy){
	$.each(data.groups, function(k1, v1){
		var title = v1.title;
		if(groupBy == 'RATING'){
			title = '<div data-size="large" data-static="true" class="rating" data-role="rating" data-show-score="false" data-value="'+title+'"></div>';
		}
		var tilesGroup = $('<div class="tile-group four"><span class="tile-group-title">'+title+'</span></div>');
		var tilesGroupContainer = $('<div class="tile-container"></div>'); 
		tilesGroup.append(tilesGroupContainer);
		$('#mainContainer').append(tilesGroup);
		$.each(v1.list, function(k2, v2){
			var tile = $('<div class="tile-wide bg-yellow fg-white" data-role="tile" onclick="showEventDetailsPopUp('+v2.id+')"></div>');
			var img = v2.imageName;
			if(img == null || img == 'undefined' || img == ''){
				img = 'default.jpg';
			}
			var desc = v2.description.substring(0, 70);
			tile.append('<div class="tile-content"><img src="'+contextPath+'/uploads/event/images/'+img+'" class="icon" /></div>');
			tile.append('<div class="tile-label"><p>Description : '+desc+'</p><p>Nom : '+v2.name+'</p></div></div>');
			tilesGroupContainer.append(tile);
		});
	});
	updateMetroUiTiles();
}
reloadEventsBorderChoice = function(groupBy){
	
	$.ajax({
		cache: false,
		url: contextPath+'/event/list',
		data: {grouped:'YES',groupBy : groupBy,sourceView : "BORDER"},
		beforeSend : function(){
			$('#mainContainer').empty();
			$('#borderCalednar').hide();
			//TODO loading
		},
		complete : function(){
			//TODO unloading
		},
		success: function(data){
			printEventsGrouped(data,groupBy);
		},
		dataType: 'json'
	});
//	 var table = $('#eventChoiceTable').dataTable({
//			"ajaxSource": contextPath+"/event/list",
//			//"scrollY":"470px",
//			"searching":false,
//			"pageLength": 8,
//		    "bLengthChange": false,
//		    "bFilter": true,
//	        "scrollCollapse": true,
//			"columns": [
//			    { "data": "id", "title": "Id" ,"width": "70px"},
//			    { "data": "date", "title": "Name", "width": "140px" },
//			    { "data": "price", "title": "Prix"},
//			    { "data": "name", "title": "Nom"},
//			    { "data": "state", "title": "Statut"},
//			    { "data": "places", "title": "Nbr places","width": "70px"},
//			    { "data": "remainingPlaces", "title": "Nbr places restantes","width": "70px"},
//			    { "data": "room.name","sDefaultContent": "-", "title": "Salle"}
//			  ]
//		});
//		
//	 	var table = $('#eventChoiceTable').DataTable();
//	    $('#eventChoiceTable tbody').on('click', 'tr', function () {
//		    var data = table.row(this).data();
//       	$("tr").removeClass("selected");
//       	$(this).addClass("selected");
//       	printEventDetails(data.id);
//		});
	
};

dataTables_Choice_Event = function(){
	 var table = $('#eventChoiceTable').dataTable({
			//"ajaxSource": contextPath+"/event/list",
			//"scrollY":"380px",
	        "scrollCollapse": true,
			"columns": [
			    { "data": "id", "title": "Id" ,"width": "70px"},
			    { "data": "date", "title": "Name", "width": "140px" },
			    { "data": "price", "title": "Prix"},
			    { "data": "name", "title": "Nom"},
			    { "data": "state.name", "title": "Statut"},
			    { "data": "places", "title": "Nbr places","width": "70px"},
			    { "data": "remainingPlaces", "title": "Nbr places restantes","width": "70px"},
			    { "data": "room.name","sDefaultContent": "-", "title": "Salle"}
			  ],
			"ajax": {
	            "url": contextPath+"/event/list",
	            "data": function ( d ){
	                d.sourceView = "CHOICE";
	            }
			}
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


goBackward = function(){
	window.history.back();
};

goForward = function(){
	window.history.forward();
};

initUploadClients = function(){
	$('#uploadClientsButton').animate({opacity: 'show', height: 'show'}, 'fast');
};
showUploadDialog = function(){
	$('#selectClientsInput').click();
};

printEventDetails = function(entityId){
	selectedEventId = entityId;
	$.ajax({
		url: contextPath+'/entity/load',
		data: {entityName:'event',entityId : entityId},
		beforeSend : function(){
			$("#eventAttributesSpinner").animate({opacity: 'show', height: 'show'}, 'fast');
			$('#eventAttributes').animate({opacity: 'hide', height: 'hide'}, 'fast');
			$("#identificationStepButton").animate({opacity: 'hide', height: 'hide'}, 'fast');
		},
		complete : function(){
			$("#eventAttributesSpinner").animate({opacity: 'hide', height: 'hide'}, 'fast');
		},
		success: function(entity){
			/*$.each(entity, function( key, value ) {
				var eventAttrDiv = '<p class="no-margin text-shadow">'+entity.key+'<span class="text-bold  padding10">'+entity.val+'</span></p>';
				$('#eventAttributes').append(eventAttrDiv);
			});*/
			var evntAttrsDiv = $('#eventAttributes');
			evntAttrsDiv.empty();
			evntAttrsDiv.append('<p class="no-margin text-shadow"><span class="text-bold">Id</span><span class="padding10">'+entity.id+'</span></p>');
			evntAttrsDiv.append('<p class="no-margin text-shadow"><span class="text-bold">Nom</span><span class="padding10">'+entity.name+'</span></p>');
			evntAttrsDiv.append('<p class="no-margin text-shadow"><span class="text-bold">Prix</span><span class="padding10">'+entity.price+'</span></p>');
			evntAttrsDiv.append('<p class="no-margin text-shadow"><span class="text-bold">Description</span><span class="padding10">'+entity.description+'</span></p>');
			evntAttrsDiv.animate({opacity: 'show', height: 'show'}, 'fast');
			$("#identificationStepButton").animate({opacity: 'show', height: 'show'}, 'fast');
		},
		dataType: 'json'
	});
};

var identificationStep = function(){
	console.log(selectedEventId);
	window.location = contextPath+'/booking/identification?eventId='+selectedEventId;
};

updateMetroUiTiles = function(){
    $(function(){
        $.StartScreen();

        var tiles = $(".tile, .tile-small, .tile-sqaure, .tile-wide, .tile-large, .tile-big, .tile-super");

        $.each(tiles, function(){
            var tile = $(this);
            setTimeout(function(){
                tile.css({
                    opacity: 1,
                    "-webkit-transform": "scale(1)",
                    "transform": "scale(1)",
                    "-webkit-transition": ".3s",
                    "transition": ".3s"
                });
            }, Math.floor(Math.random()*500));
        });

        $(".tile-group").animate({
            left: 0
        });
    });  
    //"scrollY":"200px"
};
