<%@ page pageEncoding="UTF-8"%>

<c:set var="context" value="${request.getContextPath()}" />

<!-- hidden divs -->
<div id="loadingDiv" class="loading-spinner" style="display: none;">
	<img src="<%=request.getContextPath()%>/img/indicator.gif">
</div>

<!-- Le javascript
================================================== 
-->
<!-- Placed at the end of the document so the pages load faster -->
<script src="<%=request.getContextPath()%>/js/jquery-1.9.1.js"></script>
<script src="<%=request.getContextPath()%>/js/bootstrap.js"></script>
<script src="<%=request.getContextPath()%>/js/bootstrap-datepicker.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery.dataTables-1.10.7.js"></script>
<script src="<%=request.getContextPath()%>/js/DT_bootstrap.js"></script>
<script src="<%=request.getContextPath()%>/js/common.js"></script>

