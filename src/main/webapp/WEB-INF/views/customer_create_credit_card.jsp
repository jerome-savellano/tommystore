<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script type="text/javascript"
	src="<c:url value="/resources/js/customer_create_credit_card.js"/>">
</script>
<div class="row"
	style="padding: 2%; padding-left: 5%; padding-right: 5%; margin-top: -3em;">
	<div class="col-md-12">
		<h1 class="page-header">Add credit card</h1>
		<div id="saa" class="alert alert-success fade in" style="display: none;">
						<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
						<strong>Success!</strong> Credit card has been added!
		</div>
		<div style="padding-left: 5%; padding-right: 5%;">
			<form:form action="addCreditCard" method="post"
				modelAttribute="creditCard">
				<form:input type="hidden" path="user.email"
					value="${user.getEmail()}" />
				<spring:bind path="cardNumber">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<label for="cardNumber"> Card number </label>
						<form:input type="text" class="form-control" path="cardNumber" />
						<form:errors path="cardNumber" cssClass="text-cardNumber"></form:errors>
					</div>
				</spring:bind>
				<spring:bind path="securityCode">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<label for="securityCode"> Security code </label>
						<form:input type="text" class="form-control" path="securityCode" />
						<form:errors path="securityCode" cssClass="text-danger"></form:errors>
					</div>
				</spring:bind>
				<div class="form-inline" style="padding-top: 2%;">
					<button type="submit" class="btn btn-primary">Submit</button>
					<a href="selectCreditCard"
						class="btn btn-secondary">Select credit card</a>
				</div>
			</form:form>
		</div>
	</div>
</div>
