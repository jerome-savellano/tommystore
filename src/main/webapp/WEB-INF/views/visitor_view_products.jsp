<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script type="text/javascript"
	src="<c:url value="/resources/js/visitor_view_products.js" />"></script>
<div class="row"
	style="padding: 1%; padding-left: 5%; padding-right: 5%;">
	<div class="form-inline">
		<form id="searchForm" action="findProduct" method="get">
			<input id="searchProduct" name="name" type="text"
				class="form-control" />
			<button type="submit" class="btn btn-default">Search product</button>

			<div class="dropdown form-group">
				<button class="btn btn-default dropdown-toggle" type="button"
					id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="true">
					Filter by category <span class="caret"></span>
				</button>
				<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
					<li><a
						href="${pageContext.request.contextPath}/viewProducts?category=">Show
							all</a></li>
					<c:forEach items="${categories}" var="category" varStatus="status">
						<li><a
							href="${pageContext.request.contextPath}/viewProducts?category=${category.getName()}">${category.getName()}</a></li>
					</c:forEach>
				</ul>
			</div>
		</form>
	</div>


	<div class="wowowee">
		<c:choose>
			<c:when test="${not empty products}">
				<c:forEach items="${products}" var="product" varStatus="varStatus">
					<div class="col-xs-6 col-sm-3" style="padding: 2%;">
						<div class="card">
							<img class="card-img-top"
								src="${pageContext.request.contextPath}/image?prodId=${product.getProductId()}"
								alt="Card image cap"
								style="height: 200px; width: 100%; display: block; margin: auto;">
							<form:form class="viewProduct" action="addToCart" method="POST"
								modelAttribute="cartProduct">
								<form:input type="hidden" path="product.productId"
									value="${product.getProductId()}" />
								<div class="card-block" style="overflow: hidden; padding: 2%">
									<div class="form-inline">
										<h5 style="white-space: nowrap">
											<a href="#" class=" form-group text-primary card-title">${product.getName()}</a>
										</h5>

										<form:select class="pull-right"
											style="height: 30px; width: 60px;" path="quantity">
											<c:forEach begin="1" end="20" varStatus="loop">
												<option value="${loop.index}">${loop.index}</option>
											</c:forEach>
										</form:select>
									</div>
									<div>
										<p class="card-text text-danger">&#8369;
											${product.getPrice()}</p>
										<p>
									</div>
									<div class="success-message-container">
										<span class="text-success add-to-cart"><strong>&nbsp;</strong></span>
									</div>
									<div class="success-message vertical-center"
										style="display: none;">
										<span class="text-success add-to-cart"><strong>Added
												to cart!</strong></span>
									</div>
									<form:button id="addToCart" class="btn btn-block btn-primary">Add to cart</form:button>
									<a id="viewCart" href="viewCart"
										class="btn btn-block btn-warning"
										style="display: none; margin: 0px;">View cart</a>
								</div>
							</form:form>
						</div>
					</div>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<h3 class="text-center text-muted">No product/s found.</h3>
			</c:otherwise>
		</c:choose>
	</div>
</div>