<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" uri="http://www.springframework.org/tags" %>
<%@ page isELIgnored = "false" %>
<c:url var="actionUrl" value="/login" />
<c:url var="logout" value="/logout" />
<c:url var="registerUser" value="/user/getRegisterForm" />
<c:url var="saveContact" value="/contact/contactForm"/>
<c:url var="home" value="/home"/>
<c:url var="contactList" value="/contact/getAll"/>
<c:url var="allUsers" value="/contact/getAllUsers"/>



<c:if test="${sessionScope.personId == null}">
    <%--    Guest user here--%>
    <nav>
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="${home}">ContactApp</a>
                </div>
                <ul class="nav navbar-nav">
                    <li class="active"><a href="${home}">Home</a></li>
                    <li><a href="#">About</a></li>
                    <li><a href="#">Services</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="${registerUser}"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
                    <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
                </ul>
            </div>
        </nav>
    </nav>
</c:if>

<c:if test="${sessionScope.personId != null && sessionScope.personRole == 1}">

    <%--    ADMIN LOGGED IN HERE--%>
    <nav>
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="${home}">ContactApp</a>
                </div>
                <ul class="nav navbar-nav">
                    <li class="active"><a href="${home}">Home</a></li>
                    <li><a href="${allUsers}">UserList</a></li>
                    <li><a href="#">Services</a></li>
                    <li><a href="#">About</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="${logout}"><span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
                </ul>
            </div>
        </nav>
    </nav>
</c:if>

<c:if test="${sessionScope.personId !=null && sessionScope.personRole == 2}">
<%--    User Logged in here --%>
    <nav>
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="${home}">ContactApp</a>
                </div>
                <ul class="nav navbar-nav">
                    <li class="active"><a href="${home}">Home</a></li>
                    <li><a href="${saveContact}">Add Contact</a></li>
                    <li><a href="${contactList}">Contact List</a></li>
                    <li><a href="#">Services</a></li>
                    <li><a href="#">About</a></li>

                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="${logout}"><span class="glyphicon glyphicon-log-in"></span> Logout</a></li>

                </ul>
            </div>
        </nav>
    </nav>

</c:if>
