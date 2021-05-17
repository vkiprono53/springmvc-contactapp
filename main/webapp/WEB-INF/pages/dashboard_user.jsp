<%--
  Created by IntelliJ IDEA.
  User: vkiprono
  Date: 3/29/21
  Time: 10:01 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored = "false" %>

<html>
<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/style.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
      integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<head>
    <title> | User | </title>
</head>
<body>
<header>
    <jsp:include page="includes/navbar.jsp"/>
</header>
<div class="container">
    <h3>Welcome to the User Homepage</h3>
    <jsp:include page="includes/footer.jsp"/>

</div>
</body>


</html>
