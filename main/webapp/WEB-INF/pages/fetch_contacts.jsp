
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
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
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.3/jquery.min.js" type="text/javascript"></script>

<head>
    <title> | Home | </title>

    <script>
        $(document).ready(function (e) {
            alert("Hello From the other side!!!")
        });
    </script>
</head>
<body>
<header>
    <jsp:include page="includes/navbar.jsp"/>
</header>
<div class="container">
    <div class="card">
        <div class="card-body">
            <c:url var="deleteContact" value="/contact/delete" />
            <c:url var="editContact" value="/contact/edit" />

            <c:if test="${param.action == 'suc'}">
                <p class="alert-success">Contact successfully saved.</p>
            </c:if>

            <c:if test="${param.action == 'del'}">
                <p class="alert-success">Contact successfully Deleted.</p>
            </c:if>
            <h2>Contact List Here: </h2>

            <script>
                $(document).ready(function () {
                    $("#getTime").click(function (e) {
                        e.preventDefault();
                        getTimeController();
                    });

                });

                function getTimeController(){
                    $.ajax({
                        url:"getTime",
                        timeout : 10000,
                        success:function (data){
                            alert("---SUCCESS----")
                            console.log(data);
                          //  $("current_time").html(data);
                            document.getElementById("current_time").innerHTML  =data;
                        }
                    })
                }

            </script>
         <br>
            <button id="getTime" name="getTime">Get Time </button>
            <br />

            <p id="current_time" >

            </p>
            <br>

            <div>
                <form class="form-inline my-2 my-lg-0" action="<c:url value="/contact/search"/> ">
                    <input class="form-control mr-sm-2" type="search" name="keyword" id="keyword" placeholder="Search" value="${param.keyword}" required aria-label="Search">
                    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                    <button class="btn btn-outline-success my-2 my-sm-0" type="reset">Clear</button>
                </form>
            </div>

            <form action="<c:url value="/contact/bulkDelete" />">
                <button value="Delete Selected Contacts" type="submit" class="btn btn-info" name="deleteBulk">Delete Selected Contacts</button>
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>Select:</th>
                        <th>Firstname</th>
                        <th>Lastname</th>
                        <th>Phone</th>
                        <th>Email</th>
                        <th>Address</th>
                        <th>Remark</th>
                        <th>EDIT</th>
                        <th>DELETE</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <c:if test="${contactList.size() < 1}">
                            <p class="alert-info">No records to display!!</p>
                        </c:if>
                    </tr>
                    <c:forEach var="contact" items="${contactList}" varStatus="sno">

                        <tr>
                            <td><input type="checkbox" id="contactId" name="contactId" value="${contact.contactId}"></td>
<%--                            <td align="center"><input type="checkbox" name="cid" value="${c.contactId}"/></td>--%>
                            <td>${contact.firstName}</td>
                            <td>${contact.lastName}</td>
                            <td>${contact.phone}</td>
                            <td>${contact.email}</td>
                            <td>${contact.address}</td>
                            <td>${contact.remark}</td>
                            <td><a href="${editContact}/${contact.contactId}" type="button" class="btn btn-primary">EDIT</a></td>
                            <td><a href="${deleteContact}/${contact.contactId}" type="button" class="btn btn-danger">DELETE</a></td>
                        </tr
                    </c:forEach>
                    </tbody>
                </table>

            </form>
        </div>
        </div>
    </div>
    <jsp:include page="includes/footer.jsp"/>

</div>
</body>
</html>

