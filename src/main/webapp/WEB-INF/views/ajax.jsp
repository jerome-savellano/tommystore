<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/jquery-3.1.1.min.js" />"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/v/dt/dt-1.10.12/datatables.min.css" />
<script type="text/javascript"
	src="https://cdn.datatables.net/v/dt/dt-1.10.12/datatables.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	
	var form = $("form");
	var method = form.attr("method");
	var url = form.attr("action");

	/* form.submit(function(event) {
		event.preventDefault();
		
		var email = $("#email").val();
		var json = {
			"email" : email
		}

		$.ajax({
			url : url,
		    data : form.serialize(),
			type : "POST",
			success : function(smartphone) {
				var respContent = "";
				
				respContent += "<span class='success'>Smartphone was created: [";
				respContent += smartphone.email + "]</span>";
					console.log(smartphone.product.name)
				},
			error : function() {
				alert('Something went wrong')
			}
		});
	}); */
	
	form.on("submit", function(event){
		
		event.preventDefault();
		
		$.ajax({
			url : url,
		    data : $(this).serialize(),
			type : "POST",
			success : function(smartphone) {
				console.log('success');		
			},
			error : function() {
				alert('Something went wrong')
			}
		});
	});
});
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<c:forEach items="${inventories}" var="inventory" varStatus="varStatus">
		<form:form id="myForm"
			action="${pageContext.request.contextPath}/customer/ajax"
			method="POST" modelAttribute="cartProduct">
			<div class="col-xs-6 col-sm-3" style="padding: 2%;">
				<div class="card">
					<form:input type="hidden" path="user.email"
						value="${user.getEmail()}" />
				How 	<form:input type="hidden" path="product.productId"
						value="${inventory.getProduct().getProductId()}" />
					<div class="card-block" style="overflow: hidden; padding: 2%">
						<h5 style="white-space: nowrap">
							<a href="#" class="text-primary card-title">${inventory.getProduct().getName()}</a>
						</h5>
						<p class="card-text text-danger">&#8369;
							${inventory.getProduct().getPrice()}</p>
						<p class="text-success">In stock</p>
						<p>
							<span style="">Added to cart</span>
						</p>
						<form:button class="btn btn-block btn-warning">Add to cart</form:button>
					</div>
				</div>
			</div>
		</form:form>
	</c:forEach>
</body>
</html>