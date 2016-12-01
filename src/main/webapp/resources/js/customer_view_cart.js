$(document).ready(function(){
	
	var deleteForm = $(".delete-form");
	var deleteUrl = deleteForm.attr("action");
	
	deleteForm.submit(function(event) {

		event.preventDefault();
		
		var thisForm = this;
		
		var thisTableRow = $(this).closest('tr');

		
		$.ajax({
			
			url : deleteUrl,
			data : $(this).serialize(),
			dataType : 'json',
			type : "POST",
			success : function(callback) {
				
				$(thisTableRow).hide('fast', function(){ 
					$(this).remove(); 
				});
				
				if(callback === 0){
					location.reload();
				}
			}
		});
	});
	
	var quantityUpdateForm = $(".quantity-update-form");
	var quantityUpdateUrl = quantityUpdateForm.attr("action");
	
	quantityUpdateForm.submit(function(event) {

		event.preventDefault();

		var thisForm = this;
		
		$.ajax({
			
			url : quantityUpdateUrl,
			data : $(this).serialize(),
			dataType : 'json',
			type : "POST"
		});
	});
	
	$('.quantityList').change(function() {
	    quantityUpdateForm.submit();
	});
});