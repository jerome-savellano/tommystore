<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="row" style="padding: 1%; padding-left: 5%; padding-right: 5%;">
	<form:form class="form-inline" action="viewProducts?category="
		method="get">
		<label for="usertype">Filter by category: </label>
		<select class="form-control" name="category">
			<option selected>All</option>
			<c:forEach items="${categories}" var="category" varStatus="status">
				<option>${category.getName()}</option>
			</c:forEach>
		</select>
		<button type="submit" class="btn btn-primary">View</button>
	</form:form>
	<c:forEach items="${inventories}" var="inventory" varStatus="varStatus">
		<div class="col-xs-6 col-sm-3" style="padding: 2%;">
			<div class="card">
				<img class="card-img-top"
					src="${pageContext.request.contextPath}/image?prodId=${inventory.getProduct().getProductId()}"
					alt="Card image cap"
					style="height: 200px; width: 100%px; display: block; margin: auto;">
				<div class="card-block" style="overflow: hidden; padding: 2%">
					<h5 style="white-space: nowrap"><a href="#" class="text-primary card-title">${inventory.getProduct().getName()}</a></h5>
					<p class="card-text text-danger">&#8369; ${inventory.getProduct().getPrice()}</p>
					<p class="text-success">In stock</p>
				</div>
			</div>
		</div>
	</c:forEach>
</div>