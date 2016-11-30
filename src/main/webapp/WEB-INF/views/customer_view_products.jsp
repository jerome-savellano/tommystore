<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script type="text/javascript"
	src="<c:url value="/resources/js/customer_view_products.js" />"></script>
<div class="row"
	style="padding: 1%; padding-left: 5%; padding-right: 5%;">
	<div class="dropdown">
		<button class="btn btn-default dropdown-toggle" type="button"
			id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true"
			aria-expanded="true">
			Filter by category <span class="caret"></span>
		</button>
		<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
			<li><a href="${pageContext.request.contextPath}/customer/viewProducts?category=">Show all</a></li>
			<c:forEach items="${categories}" var="category" varStatus="status">
				<li><a href="${pageContext.request.contextPath}/customer/viewProducts?category=${category.getName()}">${category.getName()}</a></li>
			</c:forEach>
		</ul>
	</div>
	<c:forEach items="${inventories}" var="inventory" varStatus="varStatus">
		<div class="col-xs-6 col-sm-3" style="padding: 2%;">
			<div class="card">
				<img class="card-img-top"
					src="${pageContext.request.contextPath}/image?prodId=${inventory.getProduct().getProductId()}"
					alt="Card image cap"
					style="height: 200px; width: 100%; display: block; margin: auto;">
				<form:form id="add_to_cart_form" action="addToCart" method="POST"
					modelAttribute="cartProduct">
					<form:input type="hidden" path="product.productId"
						value="${inventory.getProduct().getProductId()}" />
					<div class="card-block" style="overflow: hidden; padding: 2%">
						<h5 style="white-space: nowrap">
							<a href="#" class="text-primary card-title">${inventory.getProduct().getName()}</a>
						</h5>
						<p class="card-text text-danger">&#8369;
							${inventory.getProduct().getPrice()}</p>
						<p>
						<div class="success-message-container">
							<span class="text-success add-to-cart"><strong>&nbsp;</strong></span>
						</div>
						<div class="success-message vertical-center"
							style="display: none;">
							<span class="text-success add-to-cart"><strong>Added
									to cart!</strong></span>
						</div>
						<form:button id="addToCart" class="btn btn-block btn-warning">Add to cart</form:button>
						<a id="viewCart" href="viewCart" class="btn btn-block btn-warning" style="display: none;">View cart</a>
					</div>
				</form:form>
			</div>
		</div>
	</c:forEach>
</div>