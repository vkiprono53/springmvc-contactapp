<%--
  Created by IntelliJ IDEA.
  User: vkiprono
  Date: 3/29/21
  Time: 10:01 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored = "false" %>
<%@ taglib prefix="u" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/style.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
      integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<head>
    <title> | ADMIN  | </title>
</head>
<body>
<%--<jsp:include page="includes/navbar.jsp"/>--%>

<header>
    <jsp:include page="includes/navbar.jsp"/>
</header>
<div class="container">

    <h3>Welcome Admin!!!!</h3>
    <jsp:include page="includes/footer.jsp"/>

</div>
</body>


</html>
