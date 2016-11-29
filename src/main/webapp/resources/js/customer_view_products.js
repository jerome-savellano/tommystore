$(document).ready(function() {

	var form = $("form");
	var url = form.attr("action");
	var formMethod = form.attr("method");

	form.submit(function(event) {

		event.preventDefault();

		var thisForm = this;

		$.ajax({

			url : url,
			data : $(this).serialize(),
			type : "POST",
			success : function(cartProduct) {
				$(thisForm).find('.success-message-container').remove();
				$(thisForm).find('.success-message').show();
			}
		});
	});
	
	//View product by category
	$('#categoryLink').click(function() {
					
		var category = $("#category option:selected").text();
		$(this).attr("href", "");		
		$(this).attr("href", "${pageContext.request.contextPath}/customer/viewProducts?category=" + category);

	});
});