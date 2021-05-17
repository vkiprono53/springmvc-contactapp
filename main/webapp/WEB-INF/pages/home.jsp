
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored = "false" %>
<%@ page session="true" %>
<%--  Created by IntelliJ IDEA.--%>
<%--  User: vkiprono--%>
<%--  Date: 3/29/21--%>
<%--  Time: 10:01 AM--%>
<%--  To change this template use File | Settings | File Templates.--%>
<%--&ndash;%&gt;--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/style.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
      integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<head>
    <title> | Home | </title>
</head>
<body>
<header>
<jsp:include page="includes/navbar.jsp"/>
</header>
<div class="container">
    <div class="card">
        <div class="card-body">
            <h2>Login Here</h2>
            <c:url var="actionUrl" value="/login" />

            <c:if test="${param.action == 'lo'}">
                <p class="alert-success">Successfully Logged out!!!</p>
            </c:if>

            <c:if test="${param.action == 're'}">
                <p class="alert-success">You have successfully registered. You can now log in.</p>
            </c:if>
         <form:form method="post" action="${actionUrl}" modelAttribute="command">
<%--         <form:form method="post" action="#" modelAttribute="command">--%>
                <div class="form-group row">

                    <form:label path="userName" class="col-sm-2 col-form-label">UserName</form:label>
                    <div class="col-sm-7">
                        <form:input path="userName" class="form-control" name="userName" id="userName"
                                    placeholder="Enter Your userName"/>
                        <form:errors path="userName"/>
                    </div>
                </div>

                <div class="form-group row">
                    <form:label path="password" class="col-sm-2 col-form-label">Password</form:label>

                    <div class="col-sm-7">
                        <form:password path="password" class="form-control" name="password" id="password"
                                       placeholder="**************"/>
                        <form:errors path="password"/>

                    </div>
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form:form>
        </div>
    </div>
    <jsp:include page="includes/footer.jsp"/>

</div>
</body>
</html>

