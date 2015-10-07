<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="cell size-x200" id="cell-sidebar" style="background-color: #71b1d1; height: 100%">
    <ul class="sidebar">
        <li><a href="<%=request.getContextPath()%>/admin/manageUsers.jsp">
            <span class="mif-security icon"></span>
            <span class="title">Rôles et Utilisateurs</span>
            <span class="counter"></span>
        </a></li>
        <li><a href="<%=request.getContextPath()%>/admin/manageClient.jsp">
            <span class="mif-user icon"></span>
            <span class="title">Clients</span>
            <span class="counter"></span>
        </a></li>
        <li><a href="<%=request.getContextPath()%>/admin/manageRoom.jsp">
            <span class="mif-library icon"></span>
            <span class="title">Salles</span>
            <span class="counter"></span>
        </a></li>
        <li><a href="<%=request.getContextPath()%>/admin/manageEvent.jsp">
            <span class="mif-tag icon"></span>
            <span class="title">Événements</span>
            <span class="counter"></span>
        </a></li>
        <sec:authorize access="hasRole('REPORTING')">
        <li><a href="<%=request.getContextPath()%>/reporting/index.jsp">
            <span class="mif-chart-dots icon"></span>
            <span class="title">Reporting</span>
            <span class="counter"></span>
        </a></li>
        </sec:authorize> 
    </ul>
</div>