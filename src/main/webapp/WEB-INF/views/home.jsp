<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: AD
  Date: 3/28/2024
  Time: 1:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form:form modelAttribute="student" action="/submit" method="post">
    <form:label path="name">Name</form:label>
    <form:input path="name"/>
    <form:errors cssStyle="color: red" path="name" element="span" />
    <br>

    <form:label path="dob">Birthday</form:label>
    <form:input type="date" path="dob"/> <br>
    <form:label path="sex">Sex</form:label>
    <form:select path="sex" >
        <c:forEach items="${genders}" var="gen">
            <form:option value="${gen}">${gen==null?"Khac":(gen==true?"Nam":"Nu")}</form:option>
        </c:forEach>

    </form:select> <br>
    <form:button type="submit">Send</form:button>
</form:form>
<%--<form:form modelAttribute="fileUpload" action="/upload" method="post" enctype="multipart/form-data">--%>
<%--    <form:label path="fileList">Name</form:label>--%>
<%--    <form:input type="file" multiple="true" path="fileList"/> <br>--%>
<%--    <form:button type="submit">Send</form:button>--%>
<%--</form:form>--%>


</body>
</html>
