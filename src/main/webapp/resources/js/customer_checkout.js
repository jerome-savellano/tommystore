$(document).ready(function(){
	
	var form = $('form');
	var url = form.attr('action');
	
	form.submit(function(event){
		
		event.preventDefault();
		
		$.ajax({
			
			url : url,
			method : "POST",
			success : function(){
				$('.checkout-success').toggle();
				$('.checkout-container').hide('fast', function(){
					$(this).remove();
				});
			},
			error : function(){
				$('.checkout-failed').toggle();
			}
		});
	});
});