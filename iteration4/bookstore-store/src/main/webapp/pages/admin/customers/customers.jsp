<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ page session="false"%>

<c:set value="/api/admin/customers" var="fetchUrl" scope="request"/>
<c:set value="/admin/customer/detail" var="editUrl" scope="request"/>
<c:set value="/admin/customer/create" var="createUrl" scope="request"/>
<c:set value="/api/admin/customer/" var="deleteUrl" scope="request"/>

<div class="tabbable">

    <div class="tab-content">

        <div class="tab-pane active" id="catalogue-section">

            <div>

                <h3 class="page-header"><s:message code="label.customer.list" text="Customer List" /></h3>
                <br/><br/>

                <div class="pull-right">
                    <div class="btn-group">
                        <button class="btn btn-info dropdown-toggle" data-toggle="dropdown"><s:message code="label.generic.moreoptions" text="More options"/> ... <span class="caret"></span></button>
                        <ul class="dropdown-menu">
                            <li><a href="<c:url value="${createUrl}" />"><s:message code="label.customer.createcustomer" text="Create"/></a></li>
                        </ul>
                    </div>
                </div>

                <table id="customerTable" class="display" cellspacing="0" width="100%">
                    <thead>
                        <tr>
                            <th>Id</th>
                            <th>Nick</th>
                            <th>Gender</th>
                            <th>Email Address</th>
                            <th width="10%">&nbsp;</th>
                        </tr>
                    </thead>
                </table>
            </div>

        </div>

    </div>
</div>


<script src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.js"></script>
<script>
    $(document).ready(function() {
        $('#customerTable').DataTable( {
            "processing": true,
            "serverSide": true,
            "pageLength": 10,
            "ordering": false,
            "searching": false,
            "lengthMenu": [5,10,15,30],
            "ajax": {
                "url": '<c:url value="${fetchUrl}" />'},
            "columns":[
                {"data": "id"},
                {"data": "nick"},
                {"data": "gender"},
                {"data": "emailAddress"},
                { "": "" }
            ],
            "columnDefs": [
                {
                    "data": null,
                    "targets": -1,
                    "defaultContent":   "<div class='btn-group'>"+
                                        "<button class='dt-edit btn btn-default' type='button'><i class='fa fa-edit'></i></button>"+
                                        "<button class='dt-delete btn btn-default' type='button'><i class='fa fa-trash-o'></i></button>"+
                                        "</div>"
                }
            ]
        });
    } );

    $("#customerTable").on('draw.dt',function(){
        $(".dt-edit").each(function(){
            $(this).on('click',function(){
                var table = $('#customerTable').DataTable();
                var data = table.row( $(this).parents('tr') ).data();
                var url = '<c:url value="${editUrl}" />';
                var queryString = '?id=' + data.id;
                var locationUrl = url + queryString;
                window.location= locationUrl;
            });
        });
        $(".dt-delete").each(function(){
            $(this).on('click',function(){
                var table = $('#customerTable').DataTable();
                var data = table.row( $(this).parents('tr') ).data();
                if(confirm("Are you really want to delete the user whose id is " + data.id)){
                    $.ajax({
                        url: '<c:url value="${deleteUrl}" />'+ data.id,
                        type:'delete',
                        dataType: "json",
                        cache: "false",
                        success:function(data){
                            table.row( $(this).parents('tr') ).remove().draw(true);
                            location.reload();
                        },
                        error:function(err){
                        }
                    });
                }
            });
        });
    });
</script>