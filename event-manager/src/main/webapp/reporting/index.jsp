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
    <div class="page-content">
        <div class="flex-grid no-responsive-future" style="height: 100%;">
            <div class="row" style="height: 100%">
                <jsp:include page="/includes/left_menu.jsp"></jsp:include>
                
                <div class="cell auto-size padding20 bg-white" id="cell-content">
                    <h1 class="text-light"><%-- Reporting<span class="mif-drive-eta place-right"></span> --%></h1>
                    <%-- <hr class="thin bg-grayLighter">
                    <button class="button primary" onclick="initCreateEntity('event')"><span class="mif-plus"></span> Action1</button> --%>
                    <hr class="thin bg-grayLighter">
                    <div id="graph1" style="height: 70%;"></div>
   					<div class="error"><s:property value="errorNotification" /></div>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="/includes/footer.jsp"></jsp:include>
    <jsp:include page="/includes/reportingLibrary.jsp"></jsp:include>
	<script type="text/javascript">
		plotGraph('graph1');
	</script>
</body>
</html>
	