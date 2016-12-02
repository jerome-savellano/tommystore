<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="row"
	style="padding: 2%; padding-left: 5%; padding-right: 5%; margin-top: -3em;">
	<div class="col-md-12">
		<h1 class="page-header">Select shipping address</h1>
		<c:choose>
			<c:when test="${not empty shippingAddresses}">
				<div style="padding-left: 5%; padding-right: 5%;">
					<div class="panel-group">
						<c:forEach items="${shippingAddresses}" var="shippingAddress">
							<form:form
								action="selectShippingAddress"
								method="post"
								modelAttribute="shippingAddress"
								style="padding: 1%">
								<form:input path="id" type="hidden" value="${shippingAddress.getId()}" />
								<div class="panel panel-primary">
									<div class="panel-heading">Shipping address
										#${shippingAddress.getId()}</div>
									<div class="panel-body">
										<h4 class="text-muted"
											style="padding: 0px; margin: 0px; padding-bottom: 10px;">Address
											1</h4>
										<h3 class="form-control" style="margin: 0px;">${shippingAddress.getAddress1()}</h3>
										<h4 class="text-muted"
											style="padding: 0px; margin: 0px; padding-bottom: 10px; padding-top: 10px;">Address
											2</h4>
										<h3 class="form-control" style="margin: 0px;">${shippingAddress.getAddress2()}</h3>
										<h4 class="text-muted"
											style="padding: 0px; margin: 0px; padding-bottom: 10px; padding-top: 10px;">Zip
											code</h4>
										<h3 class="form-control" style="margin: 0px;">${shippingAddress.getZipCode()}</h3>
										<h4 class="text-muted"
											style="padding: 0px; margin: 0px; padding-bottom: 10px; padding-top: 10px;">Country
										</h4>
										<h3 class="form-control" style="margin: 0px;">${shippingAddress.getCountry()}</h3>
									</div>
									<div class="panel-footer"
										style="background: white; color: white;">
										<button class="btn btn-success">Use address</button>
									</div>
								</div>
							</form:form>
						</c:forEach>
					</div>
					<a href="createShippingAddress"
						class="form-group btn btn-primary pull-right">Add new shipping
						address</a>
				</div>
			</c:when>
			<c:otherwise>
				<div class="text-center">
					<h3 class="text-center text-muted">No saved shipping address.
						Start adding one!</h3>
					<a href="createShippingAddress" class="form-group btn btn-primary">Add
						shipping address</a>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
</div>
