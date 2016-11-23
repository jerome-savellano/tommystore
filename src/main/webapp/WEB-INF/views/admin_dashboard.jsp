<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="row" style="padding-left: 5%; padding-right: 5%;">
	<div class="col-md-12">
		<h1 class="page-header">Dashboard</h1>
		<div class="col-md-6">
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
					<h5 class="text-muted">
						Product/s: <span class="text-success">${inventories.size()}
							returned</span>
					</h5>
					<div style="height: 50em; overflow: scroll;">
						<c:forEach items="${inventories}" var="inventory"
							varStatus="status">

							<div class="card card-block" style="width: 100%;">
								<h3 class="card-title text-primary">
									<strong>${inventory.getProduct().getName()}</strong>
								</h3>
								<h4 class="card-text text-muted">
									Stock left: <strong><span class="text-danger">${inventory.getStock()}</span></strong>
								</h4>
								<h5 class="card-text text-muted">
									Last updated: <strong><span class="text-success">${inventory.getDateUpdated()}</span></strong>
								</h5>
								<h5 class="ccard-text text-muted">
									Updated by: <strong><span class="text-success">${inventory.getUpdater().getFirstName()}
											&nbsp; ${inventory.getUpdater().getLastName()}</span></strong>
								</h5>
								<a
									href="updateInventory?name=${inventory.getProduct().getName()}"
									class="btn btn-success">Re-stock</a>
							</div>
						</c:forEach>
					</div>
				</c:when>
				<c:otherwise>
					<h3 class="text-center text-muted">No products to show</h3>
				</c:otherwise>
			</c:choose>
		</div>
		<div class="col-md-6">
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
</div>