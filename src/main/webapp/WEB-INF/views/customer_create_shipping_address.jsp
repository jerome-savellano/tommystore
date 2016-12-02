<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script type="text/javascript"
	src="<c:url value="/resources/js/customer_create_shipping_address.js"/>">
	
</script>
<div class="row"
	style="padding: 2%; padding-left: 5%; padding-right: 5%; margin-top: -3em;">
	<div class="col-md-12">
		<h1 class="page-header">Add shipping address</h1>
		<div id="saas" class="alert alert-success fade in"
			style="display: none;">
			<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			<strong>Success!</strong> Shipping address has been added!
		</div>
		<div id="saaf" class="alert alert-danger fade in"
			style="display: none;">
			<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			<strong>Failed!</strong> Please check your format. Make sure fields are not empty. No special
			characters for addresses and numbers only for the zip code!
		</div>
		<div style="padding-left: 5%; padding-right: 5%;">
			<form:form action="addShippingAddress" method="post"
				modelAttribute="shippingAddress">
				<form:input type="hidden" path="user.email"
					value="${user.getEmail()}" />
				<spring:bind path="address1">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<label for="email"> Address 1 </label>
						<form:input type="text" class="form-control" path="address1" />
						<form:errors path="address1" cssClass="text-danger"></form:errors>
					</div>
				</spring:bind>
				<spring:bind path="address2">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<label for="address2"> Address 2 </label>
						<form:input type="text" class="form-control" path="address2" />
						<form:errors path="address2" cssClass="text-danger"></form:errors>
					</div>
				</spring:bind>
				<spring:bind path="zipCode">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<label for="zipCode"> Zip code </label>
						<form:input type="text" class="form-control" path="zipCode" />
						<form:errors path="zipCode" cssClass="text-danger"></form:errors>
					</div>
				</spring:bind>
				<form:select class="form-control" items="${countries}" var="type"
					path="country" />
				<div class="form-inline" style="padding-top: 2%;">
					<button type="submit" class="btn btn-primary">Submit</button>
					<a href="selectShippingAddress" class="btn btn-secondary">Select
						shipping address</a>
				</div>
			</form:form>
		</div>
	</div>
</div>
