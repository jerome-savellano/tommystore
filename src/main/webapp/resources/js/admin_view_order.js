$(document).ready(function() {   
    $('#dt').DataTable( {
        "scrollY"        : "30em",
        "scrollCollapse" : true,
        "paging"         : true,
        "order"          : [[ 3, "desc" ]]
    } );
} );