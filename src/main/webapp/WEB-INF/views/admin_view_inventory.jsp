<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script type="text/javascript"
	src="<c:url value="/resources/js/admin_view_inventory.js" />"></script>
<div class="row" style="padding-left: 5%; padding-right: 5%;">
	<div class="col-md-12">
		<h2 class="page-header">Inventory</h2>
		<c:choose>
			<c:when test="${not empty inventories}">
				<div style="padding-top: 1%;">
					<table class="table table-bordered table-striped">
						<thead class="bg-primary">
							<tr>
								<th class="col-xs-2">Product</th>
								<th class="col-xs-2">Stock</th>
								<th class="col-xs-2">Updated by</th>
								<th class="col-xs-2">Last updated</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${inventories}" var="inventory"
								varStatus="status">
								<tr>
									<td><a
										href="updateInventory?name=${inventory.getProduct().getName()}">${inventory.getProduct().getName()}</a></td>
									<td><span class="text-success">${inventory.getStock()}</span></td>
									<td>${inventory.getUpdater().getFirstName()}&nbsp;
										${inventory.getUpdater().getLastName()}</td>
									<td>${inventory.getDateUpdated()}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</c:when>
			<c:otherwise>
				<h3 class="text-center text-muted">No products to show</h3>
			</c:otherwise>
		</c:choose>
	</div>
</div>