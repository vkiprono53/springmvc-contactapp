<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
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
    <title> | Register | </title>
</head>
<body>
<header>
    <jsp:include page="includes/navbar.jsp"/>
</header>
<div class="container">
    <div class="card">
        <div class="card-body">
            <c:if test="${error !=null}">
                <p class="alert-danger">${error}</p>
            </c:if>
            <h2>Register Here</h2>
            <c:url var="register" value="/user/register"/>


            <form:form method="post" action="${register}" modelAttribute="personCommand">
                <%--         <form:form method="post" action="#" modelAttribute="command">--%>
                <div class="form-group row">
                    <form:label path="firstName" class="col-sm-2 col-form-label">FirstName</form:label>
                    <div class="col-sm-7">
                        <form:input path="firstName" class="form-control" name="firstName" id="firstName"
                                    placeholder="Enter Your firstName"/>
                        <form:errors path="firstName"/>
                    </div>

                </div>
                <div class="form-group row">

                    <form:label path="lastName" class="col-sm-2 col-form-label">LastName</form:label>
                    <div class="col-sm-7">
                        <form:input path="lastName" class="form-control" name="lastName" id="lastName"
                                    placeholder="Enter Your lastName"/>
                        <form:errors path="lastName"/>
                    </div>
                </div>

                <div class="form-group row">
                    <form:label path="phone" class="col-sm-2 col-form-label">Phone</form:label>
                    <div class="col-sm-7">
                        <form:input path="phone" class="form-control" name="phone" id="phone"
                                    placeholder="Enter Your phone"/>
                        <form:errors path="phone"/>
                    </div>
                </div>

                <div class="form-group row">
                    <form:label path="email" class="col-sm-2 col-form-label">Email</form:label>
                    <div class="col-sm-7">
                        <form:input path="email" class="form-control" name="email" id="email"
                                    placeholder="Enter Your email"/>
                        <form:errors path="email"/>
                    </div>
                </div>

                <div class="form-group row">
                    <form:label path="address" class="col-sm-2 col-form-label">Address</form:label>
                    <div class="col-sm-7">
                        <form:input path="address" class="form-control" name="address" id="address"
                                    placeholder="Enter Your address"/>
                        <form:errors path="address"/>
                    </div>
                </div>

                <div class="form-group row">
                    <form:label path="loginName" class="col-sm-2 col-form-label" o="">LoginName</form:label>
                    <div class="col-sm-7">
                        <form:input path="loginName" class="form-control" name="loginName" id="loginName"
                                    placeholder="Enter Your LoginName"/>
                        <form:errors path="loginName"/>
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
                <div class="form-group row">
                    <button type="submit" class="btn btn-primary">Register</button>
                </div>
            </form:form>
        </div>
    </div>
    <jsp:include page="includes/footer.jsp"/>

</div>
</body>
</html>

