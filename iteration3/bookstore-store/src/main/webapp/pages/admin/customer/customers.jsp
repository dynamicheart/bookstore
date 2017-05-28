<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ page session="false"%>


<div class="tabbable">

    <div class="tab-content">

        <div class="tab-pane active" id="catalogue-section">

            <div>


                <h3><s:message code="label.customer.list" text="Customer list" /></h3>
                <br/><br/>

                <table id="dataTable" class="display" cellspacing="0" width="100%">
                    <thead>
                        <tr>
                            <th>Id</th>
                            <th>Gender</th>
                            <th>DataOfBirth</th>
                            <th>EmailAddress</th>
                            <th>Nick</th>
                        </tr>
                    </thead>

                </table>
            </div>

        </div>

    </div>
</div>