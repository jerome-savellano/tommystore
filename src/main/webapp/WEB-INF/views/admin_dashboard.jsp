<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script type="text/javascript"
	src="<c:url value="/resources/js/admin_dashboard.js" />"></script>
<div class="row" style="padding-left: 5%; padding-right: 5%;">
	<div class="col-md-12">
		<h2 class="page-header">Product stock monitor</h2>
		<form:form method="post" class="form-inline"
			action="updateStockMonitor" modelAttribute="stockMonitor">
			<div class="form-group">
				<form:input path="id" type="hidden" value="${stockMonitor.getId()}" />
				<label for="stock">Stock: </label>
				<form:input path="stock" type="text" class="form-control"
					style="width: 20%;" />
				<button type="submit" class="btn btn-primary">Change</button>
			</div>
		</form:form>
		<c:if test="${not empty invalidStockMonitor}">
			<h6 class="text-danger">${invalidStockMonitor}</h6>
		</c:if>
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
									<td><span class="text-danger">${inventory.getStock()}</span></td>
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
<div class="row" style="padding-left: 5%; padding-right: 5%;">
	<div class="col-md-12">
		<h2 class="page-header">New users (last 24 hours)</h2>
		<c:choose>
			<c:when test="${not empty users}">
				<table class="table table-striped">
					<thead class="bg-primary">
						<tr>
							<th>Email</th>
							<th>User type</th>
							<th>Date registered</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${users}" var="eachUser" varStatus="status">
							<tr>
								<td>${eachUser.getEmail()}</td>
								<td>${eachUser.getUserType()}</td>
								<td>${eachUser.getDateCreated()}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:when>
			<c:otherwise>
				<h3 class="text-center text-muted">No new users</h3>
			</c:otherwise>
		</c:choose>
	</div>
</div>