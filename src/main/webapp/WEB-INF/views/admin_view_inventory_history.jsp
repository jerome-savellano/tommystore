<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="row" style="padding: 1%; padding-left: 5%; padding-right: 5%;">
	<h1 class="page-header">Inventory history</h1>
	<form:form class="form-inline" action="viewInventoryHistory?prodId="
		method="get">
		<label for="usertype">Browse by product: </label>
		<select class="form-control" name="prodId">
			<option selected>All</option>
			<c:forEach items="${products}" var="product" varStatus="status">
				<option value="${product.getProductId()}">${product.getName()}</option>
			</c:forEach>
		</select>
		<button type="submit" class="btn btn-primary">View</button>
	</form:form>
	<div style="padding-top: 1%">
			<table class="table table-striped">
				<thead class="bg-primary">
					<tr>
						<th class="col-xs-2">Product</th>
						<th class="col-xs-2">Updated by</th>
						<th class="col-xs-2">Stock</th>
						<th class="col-xs-2">Date updated</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${inventoryHistories}" var="inventoryHistory" varStatus="status">
						<tr>
							<td>${inventoryHistory.getInventory().getProduct().getName()}</td>
							<td>${inventoryHistory.getInventory().getUpdater().getFirstName()} &nbsp; ${inventoryHistory.getInventory().getUpdater().getLastName()}</td>
							<td>${inventoryHistory.getInventory().getStock()}</td>
							<td>${inventoryHistory.getInventory().getDateUpdated()}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
</div>