function loading(){
    $('#loadingDiv').show(); 
}

function unloading(){
    $('#loadingDiv').hide(); 
}


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