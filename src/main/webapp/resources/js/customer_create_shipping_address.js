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
			success : function(callback) {
				if(callback.status == 'ok'){
					$("#saaf").hide();
					$("#saas").slideDown(500);
					$(thisForm).trigger("reset");
				}else{
					$("#saas").hide();	
					$("#saaf").slideDown(500);
					$(thisForm).trigger("reset");
				}
			}
		});
	});
});