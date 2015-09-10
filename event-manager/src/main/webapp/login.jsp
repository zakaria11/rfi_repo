 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%@ page pageEncoding="UTF-8"%>
				
<!DOCTYPE html>
<html>
<head>
 	<jsp:include page="/includes/header.jsp"></jsp:include>
    <style>
        .login-form {
            width: 25rem;
            height: 18.75rem;
            position: fixed;
            top: 50%;
            margin-top: -9.375rem;
            left: 50%;
            margin-left: -12.5rem;
            background-color: #ffffff;
            opacity: 0;
            -webkit-transform: scale(.8);
            transform: scale(.8);
        }
    </style>

    <script>
        $(function(){
            var form = $(".login-form");
            form.css({
                opacity: 1,
                "-webkit-transform": "scale(1)",
                "transform": "scale(1)",
                "-webkit-transition": ".5s",
                "transition": ".5s"
            });
        });
    </script>
</head>
<body class="bg-darkTeal">
    <div class="login-form padding20 block-shadow">
        <form name='loginForm' action="<c:url value='j_spring_security_check' />" method='POST'>
            <h1 class="text-light">Authentification</h1>
            <hr class="thin"/>
            <br />
            <div class="input-control text full-size" data-role="input">
                <label for="inputUsername">Utilisateur:</label>
                <input type="text" name="username" id="inputUsername">
                <button class="button helper-button clear"><span class="mif-cross"></span></button>
            </div>
            <br />
            <br />
            <div class="input-control password full-size" data-role="input">
                <label for="inputPassword">Mot de passe :</label>
                <input type="password" name="password" id="inputPassword">
                <button class="button helper-button reveal"><span class="mif-looks"></span></button>
            </div>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            <br />
            <br />
            <div><s:property value="error"/></div>
            <div class="form-actions">
                <button type="submit" name="submit" class="button primary">Valider</button>
            </div>
        </form>
    </div>
</body>
</html>
		
		
		
		
		
		
		
		
		