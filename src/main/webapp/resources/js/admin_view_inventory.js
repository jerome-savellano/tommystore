$(document).ready(function() {   
    $('table').DataTable( {
        "scrollY"        : "30em",
        "scrollCollapse" : true,
        "paging"         : true,
        "order"          : [[ 0, "asc" ]]
    } );
} );