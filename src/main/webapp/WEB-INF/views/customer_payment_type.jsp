<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="row"
	style="padding: 2%; padding-left: 5%; padding-right: 5%; margin-top: -3em;">
	<div class="col-md-12">
		<h1 class="page-header">Payment type</h1>

		<form:form modelAttribute="paymentType" action="paymentType"
			method="post">
			<form:select class="form-control payment-type" items="${paymentTypes}" var="type" path="paymentType"/>
			<form:button class="btn btn-primary" style="margin-top: 10px;">Submit</form:button>
		</form:form>

	</div>
</div>
