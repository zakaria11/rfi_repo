 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%@ page pageEncoding="UTF-8"%>

<%--
<!DOCTYPE html>
<html lang="en">
  <head>
	<jsp:include page="/includes/header.jsp"></jsp:include>
	<script type="text/javascript">
	</script>
  </head>
  <body>
  	<jsp:include page="/includes/head_menu.jsp"></jsp:include>
    <div class="container" id="main_container" style="display: none;">
    	<h1>Événements</h1>
 		<table id="list" class="table table-hover table-bordered dataTable no-footer">
              <thead>
                <tr>
                </tr>
              </thead>
              <tbody>
		    	
              </tbody>
		</table>
		<div class="btn-group">
   			<a class="btn" href="#" onclick="initBooking()">Reserver</a>
   		</div>		
   			<div class="error"><s:property value="errorNotification" /></div>
    </div>
    <div id="modalContainer"></div>
	<jsp:include page="/includes/footer.jsp"></jsp:include>
	<script>
	var selectedId = null;
	var table= $('#list').DataTable({
	    ordering: true,
	    "drawCallback": function( settings ) {
			$("#main_container").fadeIn("fast");
	    }
	});
	
	selectTableRow = function(rowDiv){
		selectedId = $(rowDiv).attr("id");
		$("#list tr").removeClass("hovered");
		$(rowDiv).addClass("hovered");
	};

/* 	showEditPopUp = function(id){		
		$.ajax({
			url : contextPath+'/event/initEdit',
			method  : 'POST',
			data : {eventId:id},
			success : function(response){
				$("#modalContainer").html(response);		
				$("#editEvent").modal();
			},
			error : function(){
			}
		});		
	}; */
	
	showAddPopUp = function(){
	    $("#modalContainer").load( "add.jsp" ,function() {
	    	$("#addEvent").modal();
	    });
	}
	initBooking = function(){
		window.location.href = contextPath+'/booking/initBooking?eventId='+selectedId;
	};
	
	del = function(id){
		$.ajax({
			url : contextPath+'/event/del',
			method  : 'POST',
			data : {eventId:id},
			success : function(){
				window.location = contextPath+'/event/list';				
			},
			error : function(){
			}
		});
	};
	
	</script>
  </body>
</html>

 --%>


<!DOCTYPE html>
<html>
<head>

	<jsp:include page="/includes/header.jsp"></jsp:include>

    <style>
        html, body {
            height: 100%;
        }
        body {
        }
        .page-content {
            padding-top: 3.125rem;
            min-height: 100%;
            height: 100%;
        }
        .table .input-control.checkbox {
            line-height: 1;
            min-height: 0;
            height: auto;
        }

        @media screen and (max-width: 800px){
            #cell-sidebar {
                flex-basis: 52px;
            }
            #cell-content {
                flex-basis: calc(100% - 52px);
            }
        }
    </style>

    <script>
        function pushMessage(t){
            var mes = 'Info|Implement independently';
            $.Notify({
                caption: mes.split("|")[0],
                content: mes.split("|")[1],
                type: t
            });
        }

        $(function(){
            $('.sidebar').on('click', 'li', function(){
                if (!$(this).hasClass('active')) {
                    $('.sidebar li').removeClass('active');
                    $(this).addClass('active');
                }
            })
        })
    </script>
</head>
<body class="bg-steel">
	<jsp:include page="/includes/admin_header.jsp"></jsp:include>

   

    <div class="page-content">
        <div class="flex-grid no-responsive-future" style="height: 100%;">
            <div class="row" style="height: 100%">
            	<jsp:include page="/includes/left_menu.jsp"></jsp:include>
                <div class="cell auto-size padding20 bg-white" id="cell-content">
                    <h1 class="text-light">Événements</span></h1>
                    <hr class="thin bg-grayLighter">
                    <button class="button primary" onclick="pushMessage('info')"><span class="mif-plus"></span> Create...</button>
                    <button class="button success" onclick="pushMessage('success')"><span class="mif-play"></span> Start</button>
                    <button class="button warning" onclick="pushMessage('warning')"><span class="mif-loop2"></span> Restart</button>
                    <button class="button alert" onclick="pushMessage('alert')">Stop all machines</button>
                    <hr class="thin bg-grayLighter">
                    <table class="dataTable border bordered" data-role="datatable" data-auto-width="false">
                        <thead>
                        <tr>
		                  <th>Id</th>
		                  <th>Nom</th>
		                  <th>Prénom</th>
		                  <th>CNE</th>
		                  <th>CIN</th>
                        </tr>
                        </thead>
                        	<tbody>
                        		<s:iterator value="list">
					            	<tr id="<s:property value="id"/>" onclick="selectTableRow(this);">
					                  <td><s:property value="id"/></td>
					                  <td><s:property value="date"/></td>
					                  <td><s:property value="price"/> DH</td>
					                  <td><s:property value="room.name"/></td>
					                  <td><s:property value="name"/></td>
					                  <td><s:property value="description"/></td>
					        	</tr>
							</s:iterator>
						</tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
