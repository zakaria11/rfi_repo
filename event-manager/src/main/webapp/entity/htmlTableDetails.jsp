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
                    <h1 class="text-light"><s:property value="viewEntity.name" /></h1>                    
                    <hr class="thin bg-grayLighter">
                    <table class="dataTable border bordered no-footer hovered">
	                    <s:iterator value="viewEntity.attributes" status="status" var="attrs">
	  						<tr>
	  							<td><s:property value="label" /></td>
	  							<td><s:property value="value" /></td>
	  						</tr>
						</s:iterator>
                    </table>
   					<div class="error"><s:property value="errorNotification" /></div>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="/includes/footer.jsp"></jsp:include>
	<script type="text/javascript">
	</script>
</body>
</html>