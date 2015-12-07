<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
    
<h1 class="tile-area-title">
	<button class="button" onclick="reloadEventsBorderChoice('TAG');"><span class="mif-checkmark"></span> Thématique</button>
	<button class="button" onclick="showEventsBorderCalendar();"><span class="mif-checkmark"></span> Date</button>
	<button class="button" onclick="reloadEventsBorderChoice('ROOM');"><span class="mif-checkmark"></span> Salle</button>
	<button class="button" onclick="reloadEventsBorderChoice('RATING');"><span class="mif-checkmark"></span> Importance</button>
</h1>
<div class="tile-area-controls"></div>