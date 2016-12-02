<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script type="text/javascript"
	src="<c:url value="/resources/js/customer_view_cart.js"/>">
	
</script>
<div class="row"
	style="padding: 2%; padding-left: 5%; padding-right: 5%; margin-top: -3em;">
	<div class="col-md-12">
		<h1 class="page-header">Select credit card</h1>
		<c:choose>
			<c:when test="${not empty creditCards}">
				<div style="padding-left: 5%; padding-right: 5%;">
					<div class="panel-group">
						<c:forEach items="${creditCards}" var="creditCard">
							<form:form modelAttribute="creditCard" action="selectCreditCard"
								method="post" style="margin: 1%">
								<form:input path="id" type="hidden"
									value="${creditCard.getId()}" />
								<div class="panel panel-primary">
									<div class="panel-heading">Credit card
										#${creditCard.getId()}</div>
									<div class="panel-body">
										<h4 class="text-muted"
											style="padding: 0px; margin: 0px; padding-bottom: 10px;">Card
											number</h4>
										<h3 class="form-control" style="margin: 0px;">${creditCard.getCardNumber()}</h3>
									</div>
									<div class="panel-footer"
										style="background: white; color: white;">
										<button class="btn btn-success">Use card</button>
									</div>
								</div>
							</form:form>
						</c:forEach>
					</div>
					<a href="createCreditCard"
						class="form-group btn btn-primary pull-right">Add new credit
						card</a>
				</div>
			</c:when>
			<c:otherwise>
				<div class="text-center">
					<h3 class="text-center text-muted">No saved credit card. Start
						adding one!</h3>
					<a href="createCreditCard" class="form-group btn btn-primary">Add
						credit card</a>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
</div>
