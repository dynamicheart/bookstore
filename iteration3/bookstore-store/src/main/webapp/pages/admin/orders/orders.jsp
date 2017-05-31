<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ page session="false"%>

<c:set value="/api/admin/orders" var="fetchUrl" scope="request"/>
<c:set value="/admin/order/detail" var="editUrl" scope="request"/>
<c:set value="/api/admin/order" var="deleteUrl" scope="request"/>

<div class="tabbable">

    <div class="tab-content">

        <div class="tab-pane active" id="catalogue-section">

            <div>

                <h3 class="page-header"><s:message code="label.order.list" text="Order List" /></h3>
                <br/><br/>

                <table id="orderTable" class="display" cellspacing="0" width="100%">
                    <thead>
                        <tr>
                            <th>Id</th>
                            <th>Customer Name</th>
                            <th>Total</th>
                            <th>Order Date</th>
                            <th>Status</th>
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
        $('#orderTable').DataTable( {
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
                {"data": "customerName"},
                {"data": "total"},
                {"data": "date"},
                {"data": "status"},
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

    $("#orderTable").on('draw.dt',function(){
        $(".dt-edit").each(function(){
            $(this).on('click',function(){
                var table = $('#orderTable').DataTable();
                var data = table.row( $(this).parents('tr') ).data();
                var url = '<c:url value="${editUrl}" />';
                var queryString = '?id=' + data.id;
                var locationUrl = url + queryString;
                window.location= locationUrl;
            });
        });
        $(".dt-delete").each(function(){
            $(this).on('click',function(){
                var table = $('#orderTable').DataTable();
                var data = table.row( $(this).parents('tr') ).data();
                if(confirm("Are you really want to delete the order" + data.title)){
                    $.ajax({
                        url: '<c:url value="${deleteUrl}" />'+ data.id,
                        type:'delete',
                        dataType: "json",
                        cache: "false",
                        success:function(data){
                            table.row( $(this).parents('tr') ).remove().draw(false);

                        },
                        error:function(err){
                        }
                    });
                }
            });
        });
    });
</script>