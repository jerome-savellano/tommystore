$(document).ready(function(){
	
	var form = $("form");
	var url = form.attr("action");
	var formMethod = form.attr("method");
	
	form.submit(function(event) {

		event.preventDefault();
		
		var thisForm = this;

		$.ajax({
			
			url : url,
			data : $(this).serialize(),
			dataType : 'json',
			type : "POST",
			success : function(cartProduct) {
				$(thisForm).closest("div").hide("slow");
			}
		});
	});
});