<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ page session="false" %>

<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>

<c:url var="searchStatistics" value="/admin/statistics/search"/>

<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
            </div>
            <div class="panel-body">
                <div class="row">
                    <div class="col-lg-6">
                        <form:form method="POST" commandName="criteria" action="${searchStatistics}">

                            <div class="form-group">
                                <label><s:message code="label.customer.name" text="Customer"/></label>
                                <div>
                                    <form:select items="${customers}" multiple="true" cssClass="form-control" itemValue="id" itemLabel="nick" path="customerIds">
                                        <form:options/>
                                    </form:select>
                                </div>
                            </div>

                            <div class="form-group">
                                <label><s:message code="label.order.date" text="Start Date"/></label>
                                <div>
                                    <form:input type="date" pattern="dd/MM/yyyy" path="startDate" cssClass="date-picker"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label><s:message code="label.order.date" text="End Date"/></label>
                                <div>
                                    <form:input type="date" pattern="dd/MM/yyyy" path="endDate" cssClass="date-picker"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label><s:message code="label.book.title" text="Book Title"/></label>
                                <div>
                                    <form:select items="${books}" multiple="true" cssClass="form-control" itemValue="id" itemLabel="descriptions[0].name" path="bookIds">
                                        <form:options/>
                                    </form:select>
                                </div>
                            </div>

                            <div class="form-group">
                                <label><s:message code="label.categories.title" text="Category Title"/></label>
                                <div>
                                    <form:select items="${categories}" multiple="true" cssClass="form-control" itemValue="id" itemLabel="descriptions[0].name" path="categoryIds">
                                        <form:options/>
                                    </form:select>
                                </div>
                            </div>

                            <hr>

                            <div class="form-actions">
                                <div class="pull-right">
                                    <button type="submit" class="btn btn-success"><s:message
                                            code="button.label.submit2"
                                            text="Summit"/></button>
                                </div>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>