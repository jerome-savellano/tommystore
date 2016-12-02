<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script type="text/javascript"
	src="<c:url value="/resources/js/customer_create_shipping_address.js"/>">
</script>
<div class="row"
	style="padding: 2%; padding-left: 5%; padding-right: 5%; margin-top: -3em;">
	<div class="col-md-12">
		<h1 class="page-header">Checkout</h1>
		<c:if test="${not empty checkoutFailed}">
			<div class="alert alert-success fade in">
				<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
				<strong>Success!</strong> Checkout done! Continue shopping at Tommy's Store!
			</div>
		</c:if>
		<div>
			<h3 class="page-header">Cart</h3>
			<table class="table">
				<thead>
					<tr>
						<th class="col-md-1"></th>
						<th class="col-md-2"></th>
						<th class="col-md-2">Price</th>
						<th class="col-md-2">Quantity</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${cartProducts}" var="product" varStatus="status">
						<tr id="cartRow">
							<td><img class="card-img-top img-responsive"
								src="${pageContext.request.contextPath}/image?prodId=${product.getProduct().getProductId()}"
								alt="Card image cap" style="height: 120px; width: 100%;"></td>
							<td><h4 class="text-primary"
									style="padding: 0px; margin: 0px;">
									<a href="#">${product.getProduct().getName()}</a>
								</h4> <c:choose>
									<c:when test="${product.getStock() >= 50}">
										<h6 class="text-success">In stock</h6>
									</c:when>
									<c:when
										test="${product.getStock() < 50 && product.getStock() > 1}">
										<h6 class="text-warning">Only ${product.getStock()} left</h6>
									</c:when>
									<c:otherwise>
										<h6 class="text-danger">No stock</h6>
									</c:otherwise>
								</c:choose></td>
							<td><h4 class="text-danger"
									style="margin: 0px; padding: 0px;">&#8369;
									${product.getProduct().getPrice()}</h4></td>
							<td><span class="text-success"><strong>${product.getQuantity()}</strong></span></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>
<div class="row"
	style="padding: 2%; padding-left: 5%; padding-right: 5%; margin-top: -3em;">
	<div class="col-md-6">
		<h3 class="page-header">Shipping address</h3>
		<div style="padding-left: 5%; padding-right: 5%;">
			<div class="panel-group">
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
				</div>
			</div>
		</div>
	</div>
	<div class="col-md-6">
		<h3 class="page-header">Payment type</h3>
		<div style="padding-left: 5%; padding-right: 5%;">
			<c:choose>
				<c:when test="${not empty creditCard}">
					<div class="panel-group">
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
								style="background: white; color: white;"></div>
						</div>
					</div>
				</c:when>
				<c:otherwise>
					<h2 class="text-center text-success">Cash</h2>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</div>
<div class="row"
	style="padding: 2%; padding-left: 5%; padding-right: 5%; margin-top: -3em;">
	<div class="form-inline pull-right">
		<form
			action="checkout"
			method="post">
			<button class="btn btn-lg btn-primary">Checkout</button>
		</form>
		<a href="viewCart" class="btn btn-lg btn-warning">Back to cart</a>
	</div>
</div>
