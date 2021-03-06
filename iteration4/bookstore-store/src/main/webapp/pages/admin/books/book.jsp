<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ page session="false" %>

<div>

    <div class="row">
        <div class="col-lg-12">
        <h3 class="page-header">
            <c:choose>
                <c:when test="${book.id!=null && book.id>0}">
                    <s:message code="label.book.editbook" text="Edit Book"/>
                </c:when>
                <c:otherwise>
                    <s:message code="label.book.createbook" text="Create Book"/>
                </c:otherwise>
            </c:choose>

        </h3>
        <br/>
        </div>
    </div>

    <c:url var="saveBook" value="/admin/book/save"/>


    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-6">
                            <form:form method="POST" enctype="multipart/form-data" commandName="book" action="${saveBook}">

                                <form:errors id="book.error" path="*" cssClass="alert alert-error" element="div"/>
                                <div id="bookError" class="alert alert-error" style="display:none;"></div>
                                <div id="bookSuccess" class="alert alert-success"
                                     style="
                                     <c:choose>
                                     <c:when test="${success!=null}">display:block;</c:when>
                                     <c:otherwise>display:none;</c:otherwise></c:choose>">
                                    <s:message code="message.success" text="Request successful"/>
                                </div>

                                <form:hidden id="bookId" path="id"/>

                                <div class="form-group">
                                    <label><s:message code="label.book.isbn" text="Book ISBN"/></label>
                                    <div>
                                        <form:input cssClass="form-control" path="isbn"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label><s:message code="label.book.available" text="Book Available"/></label>
                                    <div>
                                        <form:checkbox path="available" />
                                    </div>
                                </div>


                                <div class="form-group">
                                    <label><s:message code="label.book.publisher" text="Publisher"/></label>
                                    <div>
                                        <form:select items="${publishers}" cssClass="form-control" itemValue="id" itemLabel="descriptions[0].name" path="publisher.id">
                                            <form:options/>
                                        </form:select>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label><s:message code="label.book.qtyavailable" text="Quantity Available"/></label>
                                    <div>
                                        <form:input cssClass="form-control" path="bookQuantity"/>
                                    </div>
                                </div>

                                <hr>
                                <div class="form-actions">
                                    <div class="pull-right">
                                        <button type="submit" class="btn btn-success"><s:message
                                                code="button.label.submit"
                                                text="Save"/></button>
                                    </div>
                                </div>
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    $(document).ready(function () {
    });
</script>

