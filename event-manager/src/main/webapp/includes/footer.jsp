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
<%-- <script src="<%=request.getContextPath()%>/js/jquery-1.9.1.js"></script> --%>
<%-- <script src="<%=request.getContextPath()%>/js/jquery-ui-1.10.2.custom.js"></script> --%>

<script src="<%=request.getContextPath()%>/js/select2.js"></script>
<script src="<%=request.getContextPath()%>/js/select2_locale_fr.js"></script>

<script src="<%=request.getContextPath()%>/js/jquery.dataTables-1.10.7.js"></script>
<%-- <script src="<%=request.getContextPath()%>/js/DT_bootstrap.js"></script> --%>
<script src="<%=request.getContextPath()%>/js/metro.js"></script>




<script src="<%=request.getContextPath()%>/js/i18n/grid.locale-fr.js"></script>
<script type="text/javascript">
	$.jgrid.no_legacy_api = true;
	$.jgrid.useJSON = true;
</script>
<script src="<%=request.getContextPath()%>/js/jquery.jqGrid.src.js" type="text/javascript"></script>	

