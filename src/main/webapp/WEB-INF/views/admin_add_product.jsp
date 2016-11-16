<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="row">
	<div class="col-md-12" style="padding-left: 15%; padding-right: 15%;">
		<h2 class="page-header">Add new product</h2>
		<c:if test="${not empty duplicateProduct}">
			<div class="alert alert-danger fade in">
				<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
				<strong>Failed!</strong> Product <strong>${duplicateProduct.getName()}</strong>
				already exists. Please try again.
			</div>
		</c:if>
		<c:if test="${not empty newProduct}">
			<div class="alert alert-success fade in">
				<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
				<strong>Success!</strong> Product <strong>${newProduct.getName()}</strong>
				successfully created!
			</div>
		</c:if>
		<form:form
			action="${pageContext.request.contextPath}/admin/addProduct"
			method="post" modelAttribute="product" enctype="multipart/form-data">
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
						<option selected disabled value="">SELECT CATEGORY</option>
						<c:forEach items="${categories}" var="item">
							<option>${item.getName()}</option>
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

			<spring:bind path="imageFile">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<label for="inputfile">Upload image</label>
					<form:input path="imageFile" type="file" class="form-control-file"
						aria-describedby="fileHelp" />
					<form:errors path="imageFile" cssClass="text-danger" />
					<small id="fileHelp" class="form-text text-muted">Accepted
						formats are .jpg, .jpeg, .png. Please make sure you upload a valid
						image.</small>
				</div>
			</spring:bind>
			<div class="form-inline">
				<button type="submit" class="form-group btn btn-primary">Add
					product</button>
				<a href="viewProducts" class="form-group btn btn-success">View products</a>
			</div>
		</form:form>
	</div>
</div>