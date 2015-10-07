 <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
 <div class="app-bar fixed-top darcula" data-role="appbar">
        <a href="<%=request.getContextPath()%>/index.jsp" class="app-bar-element branding">Gestion de billettrie</a>
        <span class="app-bar-divider"></span>
        <ul class="app-bar-menu">
            <li><a href="<%=request.getContextPath()%>/admin/manageUsers.jsp">Rôles et Utilisateurs</a></li>
            <li><a href="<%=request.getContextPath()%>/admin/manageClient.jsp">Clients</a></li>
            <li><a href="<%=request.getContextPath()%>/admin/manageRoom.jsp">Salles</a></li>
            <li><a href="<%=request.getContextPath()%>/admin/manageEvent.jsp">Événements</a></li>
            <sec:authorize access="hasRole('REPORTING')">
            	<li><a href="<%=request.getContextPath()%>/reporting/index.jsp">Reporting</a></li>
            </sec:authorize>
            <%--  <li>
                <a href="" class="dropdown-toggle">Help</a>
                <ul class="d-menu" data-role="dropdown">
                    <li><a href="">Community support</a></li>
                    <li class="divider"></li>
                    <li><a href="">About</a></li>
                </ul>
            </li>--%>
        </ul>

        <div class="app-bar-element place-right">
            <span class="dropdown-toggle"><span class="mif-cog"></span>
            <sec:authorize access="isAuthenticated()"><sec:authentication property="principal.username" /></sec:authorize>
            	<sec:authorize access="!isAuthenticated()">Invité</sec:authorize>
            </span>
            <div class="app-bar-drop-container padding10 place-right no-margin-top block-shadow fg-dark" data-role="dropdown" data-no-close="true" style="width: 220px">
                <!-- <h2 class="text-light">Quick settings</h2> -->
                <ul class="unstyled-list fg-dark">
                    <li><a href="<%=request.getContextPath()%>/j_spring_security_logout" class="fg-black">
                    <span class="mif-switch">
                    Se déconnecter
                    </a></li>
                    <%-- <li><a href="" class="fg-white2 fg-hover-yellow">Security</a></li>
                    <li><a href="" class="fg-white3 fg-hover-yellow">Exit</a></li>--%>
                </ul>
            </div>
        </div>
    </div>