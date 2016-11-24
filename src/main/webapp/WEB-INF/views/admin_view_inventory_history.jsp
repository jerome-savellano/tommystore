<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script type="text/javascript"
	src="<c:url value="/resources/js/admin_view_inventory_history.js" />"></script>
<div class="row"
	style="padding: 1%; padding-left: 5%; padding-right: 5%; margin-top: -2em;">
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
	<c:choose>
		<c:when test="${not empty inventoryHistories}">
			<div style="margin-top: -1em">
				<h3 class="page-header">${inventoryHistories[0].getProduct().getName()}</h3>
				<table id="dt" class="table table-bordered table-striped">
					<thead class="bg-primary">
						<tr>
							<th class="col-xs-2">Updated by</th>
							<th class="col-xs-2">Stock added</th>
							<th class="col-xs-2">Date updated</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${inventoryHistories}" var="inventoryHistory"
							varStatus="status">
							<tr>
								<td>${inventoryHistory.getUpdater().getFirstName()}&nbsp;
									${inventoryHistory.getUpdater().getLastName()}</td>
								<td><span class="text-success">${inventoryHistory.getStock()}</span></td>
								<td>${inventoryHistory.getDateUpdated()}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</c:when>
		<c:otherwise>
			<div style="padding-top: 1%;">
				<table id="dtAll" class="table table-bordered table-striped">
					<thead class="bg-primary">
						<tr>
							<th class="col-xs-2">Product</th>
							<th class="col-xs-2">Updated by</th>
							<th class="col-xs-2">Stock added</th>
							<th class="col-xs-2">Date updated</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${inventoryHistoriesAll}" var="inventoryHistory"
							varStatus="status">
							<tr>
								<td><a
									href="viewInventoryHistory?prodId=${inventoryHistory.getProduct().getProductId()}">${inventoryHistory.getProduct().getName()}</a></td>
								<td>${inventoryHistory.getUpdater().getFirstName()}&nbsp;
									${inventoryHistory.getUpdater().getLastName()}</td>
								<td><span class="text-success">${inventoryHistory.getStock()}</span></td>
								<td>${inventoryHistory.getDateUpdated()}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</c:otherwise>
	</c:choose>
</div>