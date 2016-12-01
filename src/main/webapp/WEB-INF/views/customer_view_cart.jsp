<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script type="text/javascript"
	src="<c:url value="/resources/js/customer_view_cart.js"/>">
	
</script>
<div class="row"
	style="padding: 2%; padding-left: 5%; padding-right: 5%; margin-top: -3em;">
	<div class="col-md-12">
		<h1 class="page-header">Cart</h1>
		<div class="cart-container">
			<c:choose>
				<c:when test="${not empty cartProducts}">
					<div id="customerCart">
						<div class="form-inline pull-right">
							<a href="selectShippingAddress" class="form-group btn btn-primary">Check
								out</a>
							<form class="form-group"
								action="clearCart"
								method="post">
								<button class="form-group btn btn-warning">Clear cart</button>
							</form>
						</div>
						<div style="padding-top: 1%;">
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
									<c:forEach items="${cartProducts}" var="product"
										varStatus="status">
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
														<h6 class="text-warning">Only ${product.getStock()}
															left</h6>
													</c:when>
													<c:otherwise>
														<h6 class="text-danger">No stock</h6>
													</c:otherwise>
												</c:choose> <form:form modelAttribute="cartProduct"
													action="removeFromCart"
													method="post"
													class="delete-form">
													<form:input type="hidden" path="product.productId"
														value="${product.getProduct().getProductId()}" />
													<button class="btn btn-warning btn-xs remove-button">Delete</button>
												</form:form></td>
											<td><h4 class="text-danger"
													style="margin: 0px; padding: 0px;">&#8369;
													${product.getProduct().getPrice()}</h4></td>
											<td><form:form 
													modelAttribute="cartProduct"
													action="addToCart"
													method="post"
													class="quantity-update-form">
													<form:input type="hidden" path="product.productId"
														value="${product.getProduct().getProductId()}" />
													<form:select
														style="height: 30px; width: 60px;" path="quantity" class="quantityList">
														<c:forEach begin="1" end="20" varStatus="loop">
															<option value="${loop.index}" <c:if test="${product.getQuantity() == loop.index}">selected="selected"</c:if>>${loop.index}</option>
														</c:forEach>
													</form:select>
												</form:form></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</c:when>
				<c:otherwise>
					<div class="cart-empty">
						<h1 class="text-center text-muted">No items in cart. Start
							shopping!</h1>
						<div class="text-center">
							<a href="${pageContext.request.contextPath}/customer/home"
								class="align-center btn btn-primary btn-lg">Home</a>
						</div>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</div>
