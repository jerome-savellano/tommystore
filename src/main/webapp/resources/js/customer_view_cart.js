$(document).ready(function(){
	
	var form = $("form");
	var url = form.attr("action");
	var formMethod = form.attr("method");
	
	form.submit(function(event) {

		event.preventDefault();
		
		var thisForm = this;
		
		var thisTableRow = $(this).closest('tr');

		$.ajax({
			
			url : url,
			data : $(this).serialize(),
			dataType : 'json',
			type : "POST",
			success : function(callback) {
				
				$(thisTableRow).hide("slow");
				
				if(callback === 0){				
					setTimeout(function(){// wait for 5 secs(2)
				           location.reload(); // then reload the page.(3)
				      }, 500);
				}
			}
		});
	});
});