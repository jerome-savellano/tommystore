<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="row" style="padding: 2%; margin-top: -3em; padding-left: 5%; padding-right: 5%;">
	<h1 class="page-header">Update inventory</h1>
	<c:if test="${not empty inventoryUpdate}">
		<div class="alert alert-success fade in">
			<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			${inventoryUpdate}
		</div>
	</c:if>
	<c:if test="${not empty inventoryUpdateFailed}">
		<div class="alert alert-danger fade in">
			<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			${inventoryUpdateFailed}
		</div>
	</c:if>
	<div class="col-md-6">
		<img
			src="${pageContext.request.contextPath}/image?prodId=${inventory.getProduct().getProductId()}"
			alt="Card image cap" style="max-height: 100%; max-width: 100%;">
	</div>
	<div class="col-md-6">
		<h2 class="text-primary"><strong>${inventory.getProduct().getName()}</strong></h2>
		<h4 class="text-muted">
			Update by:<span class="text-success"><strong>${inventory.getUpdater().getFirstName()}&nbsp;${inventory.getUpdater().getLastName()}</strong></span>
		</h4>
		<h4 class="text-muted">
			Last updated: <span class="text-success"><strong>${inventory.getDateUpdated()}</strong></span>
		</h4>
		<form:form
			action="${pageContext.request.contextPath}/admin/updateInventory"
			method="post" modelAttribute="inventory"
			enctype="multipart/form-data">
			<form:input path="id" type="hidden" value="${inventory.getId()}" />
			<form:input path="updater.email" type="hidden" value="${user.getEmail()}" />
			<form:input path="updater.firstName" type="hidden" value="${inventory.getUpdater().getFirstName()}" />
			<form:input path="updater.lastName" type="hidden" value="${inventory.getUpdater().getLastName()}" />
			<form:input path="product.name" type="hidden" value="${inventory.getProduct().getName()}"/>
			<form:input path="product.productId" type="hidden" value="${inventory.getProduct().getProductId()}"/>
			<form:input path="dateUpdated" type="hidden" value="${inventory.getDateUpdated()}"/>
			<spring:bind path="stock">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<label for="stock">Stock</label>
					<form:input path="stock" type="number" class="form-control"
						placeHolder="Enter stock." />
					<form:errors path="stock" cssClass="text-danger"></form:errors>
				</div>
			</spring:bind>
			<div class="form-inline">
				<button type="submit" class="form-group btn btn-primary">Update
					inventory</button>
				<a href="viewProducts?category=" class="form-group btn btn-success">View
					products</a>
			</div>
		</form:form>
	</div>
</div>