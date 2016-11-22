<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="row" style="padding: 2%;">
	<h1 class="page-header">Update inventory</h1>
	<c:if test="${not empty inventoryUpdate}">
		<div class="alert alert-success fade in">
			<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			${inventoryUpdate}
		</div>
	</c:if>
	<div class="col-md-6">
		<img
			src="${pageContext.request.contextPath}/image?name=${inventory.getProduct().getName()}"
			alt="Card image cap" style="max-height: 100%; max-width: 100%;">
	</div>
	<div class="col-md-6">
		<h2>${inventory.getProduct().getName()}</h2>
		<h4><strong>Update by:</strong> <span class="text-success">${inventory.getUpdater().getFirstName()}&nbsp;${inventory.getUpdater().getLastName()}</span></h4>
		<h4><strong>Last updated:</strong> <span class="text-success">${inventory.getDateUpdated()}</span></h4>
		<form:form
			action="${pageContext.request.contextPath}/admin/updateInventory"
			method="post" modelAttribute="inventory" enctype="multipart/form-data">
			<form:input path="id" type="hidden" value="${inventory.getId()}"/>
			<form:input path="updater.email" type="hidden" value="${user.getEmail()}"/>
			<spring:bind path="stock">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<label for="stock">Stock</label>
					<form:input path="stock" type="number"
						class="form-control"
						placeHolder="Enter stock." />
					<form:errors path="stock" cssClass="text-danger"></form:errors>
				</div>
			</spring:bind>
			<div class="form-inline">
				<button type="submit" class="form-group btn btn-primary">Update
					product</button>
				<a href="viewProducts?category=" class="form-group btn btn-success">View
					products</a>
			</div>
		</form:form>
	</div>
</div>