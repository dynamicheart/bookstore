<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ page session="false" %>

<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>

<c:url var="statistics_url" value="/admin/statistics/book"/>

<div class="row">
    <div class="col-lg-6 col-md-6 col-sm-6">
        <div class="panel panel-default">
            <div class="panel-heading">
            </div>
            <div class="panel-body">
                <div class="row">
                    <div class="col-lg-6 col-sm-6 col-md-6">
                        <form:form method="POST" commandName="criteria" action="${statistics_url}">

                            <div class="form-group">
                                <label><s:message code="label.customer.name" text="Customer"/></label>
                                <div>
                                    <form:select items="${books}" cssClass="form-control" itemValue="isbn" itemLabel="descriptions[0].name" path="bookIsbn">
                                        <form:options/>
                                    </form:select>
                                </div>
                                <br>
                                <div class="form-actions">
                                    <div class="pull-right">
                                        <button type="submit" class="btn btn-success"><s:message
                                                code="button.label.submit"
                                                text="Save"/></button>
                                    </div>
                                </div>
                            </div>
                        </form:form>

                        <hr>
                        <h3>Total Sale Number:</h3><h3>${statistics.quantity}</h3>
                        <h3>Total Income</h3><h3>${statistics.total}</h3>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>