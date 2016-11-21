<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="row" style="padding: 2%;">
	<h1 class="page-header">Update product</h1>
	<c:if test="${not empty duplicateProduct}">
			<div class="alert alert-danger fade in">
				<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
				<strong>Failed!</strong> Product <strong>${duplicateProduct.getName()}</strong>
				already exists. Please try again.
			</div>
		</c:if>
		<c:if test="${not empty updatedProduct}">
			<div class="alert alert-success fade in">
				<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
				<strong>Success!</strong> Product <strong>${updatedProduct.getName()}</strong>
				successfully updated!
			</div>
		</c:if>
	<div class="col-md-6">
		<img
			src="${pageContext.request.contextPath}/image?name=${product.getName()}"
			alt="Card image cap" style="max-height: 100%; max-width: 100%;">
	</div>
	<div class="col-md-6">
		<form:form
			action="${pageContext.request.contextPath}/admin/updateProduct"
			method="post" modelAttribute="product" enctype="multipart/form-data">
			<form:input path="productId" type="hidden"/>
			<spring:bind path="name">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<label for="Name">Name </label>
					<form:input path="name" type="" class="form-control"
						placeHolder="Enter name" />
					<form:errors path="name" cssClass="text-danger" />
				</div>
			</spring:bind>
			<spring:bind path="category">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<label for="category">Category</label>
					<form:select cssClass="form-control" path="category">
						<c:forEach items="${categories}" var="item">
							<option ${item.getName() == product.getCategory() ? 'selected' : ''}>${item.getName()}</option>
						</c:forEach>
					</form:select>
					<form:errors path="category" cssClass="text-danger"></form:errors>
				</div>
			</spring:bind>
			<spring:bind path="price">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<label for="price">Price</label>
					<form:input path="price" type="number" step="any"
						class="form-control"
						placeHolder="Enter price. Must be greater than 0 and contain 2 decimal places max." />
					<form:errors path="price" cssClass="text-danger"></form:errors>
				</div>
			</spring:bind>

			<spring:bind path="image">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<label for="image">Upload image</label>
					<form:input path="image" type="file" class="form-control-file"
						aria-describedby="fileHelp" />
					<form:errors path="image" cssClass="text-danger" />
					<small id="fileHelp" class="form-text text-muted">Accepted
						formats are .jpg, .jpeg, .png. Please make sure you upload a valid
						image.</small>
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