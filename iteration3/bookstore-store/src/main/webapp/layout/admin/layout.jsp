<%
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
    response.setHeader("Cache-Control", "no-cache");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", -1);
%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ page import="java.util.Calendar" %>
<%@page contentType="text/html; charset=UTF-8" %>
<%@page pageEncoding="UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<html xmlns="http://www.w3.org/1999/xhtml">

<head>


    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <title><s:message code="label.storeadministration" text="Store administration"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <link rel="icon" href="<c:url value="/static/favicon.ico"/>">


    <jsp:include page="/common/adminLinks.jsp"/>

</head>

<body class="body">

<div id="wrapper">

    <div>
        <tiles:insertAttribute name="body"/>
    </div>


    <hr>
    <footer>
        <p>&copy; dynamicheart 2017-<%=Calendar.getInstance().get(Calendar.YEAR)%>
        </p>
    </footer>

</div>


<!-- Le javascript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script>
    $(document).ready(function() {
        $('#dataTable').DataTable( {
            "processing": true,
            "serverSide": true,
            "pageLength": 10,
            "ordering": false,
            "searching": false,
            "lengthMenu": [5,10,15,30],
            "ajax": {
                "url": ${url}
            },
            "columns":[
                <c:forEach items="${columns}" var="column">
                    {"data": "${column}"},
                </c:forEach>
            ]
        } );
    } );
</script>

</body>
</html>