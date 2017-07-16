<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<c:set value="/store/customer/profile/update" var="update_url" scope="request"/>
<c:set var="upload_url" value="/store/customer/profile/avatar/upload"/>

<div id="main-content" class="container clearfix row-fluid">
    <div class="row">
        <div class="col-sm-6 col-md-6 col-lg-6">
            <form:form method="post" action="${update_url}" commandName="customer">
                <form:errors path="*" cssClass="alert alert-error alert-danger form-group" element="div" />
                <div class="form-group">
                    <label><s:message code="label.customer.email" text="Email"/></label>
                    <div>
                        <form:input cssClass="form-control" maxlength="96" path="emailAddress"/>
                        <span class="help-inline"><form:errors path="emailAddress"
                                                               cssClass="error"/></span>
                    </div>
                </div>
                <div class="form-group">
                    <label><s:message code="label.generic.username" text="User Name"/></label>
                    <div>
                        <form:input cssClass="form-control" path="userName"/>
                        <form:errors path="userName" cssClass="error" />
                    </div>
                </div>
                <div class="form-group">
                    <label><s:message code="label.customer.gender" text="Gender"/></label>
                    <div>
                        <form:select cssClass="form-control" path="gender">
                            <form:options/>
                        </form:select>
                        <form:errors path="gender" cssClass="error" />
                    </div>
                </div>
                <div class="form-actions">
                    <div class="pull-right">
                        <input id="submitRegistration" type="submit" value="<s:message code="button.label.submit2" text="Submit"/>" name="Submit" class="btn btn-primary btn-large">
                    </div>
                </div>
            </form:form>
        </div>
        <div class="col-sm-6 col-md-6 col-lg-6">
            <img id="avatar" src="${customer.image.imageUrl}" class="img-responsive avatar"/>

            <form method="POST" action="${upload_url}" enctype="multipart/form-data" >
                <div class="form-group">
                    <input type="file" name="file"/>
                </div>
                <div class="form-group">
                    <input type="submit" value="Submit"/>
                </div>
            </form>
        </div>
    </div>
</div>

<script>
</script>