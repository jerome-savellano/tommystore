$(document).ready(function() {

	var form = $("form");
	var url = form.attr("action");

	form.submit(function(event) {

		event.preventDefault();
		
		var thisForm = this;

		$.ajax({
			
			url : url,
			data : $(this).serialize(),
			dataType : 'json',
			type : "POST",
			success : function(cartProduct) {
				$("#saa").toggle();
				$(thisForm).trigger("reset");
			}
		});
	});
});