<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
div class="row">
			<div class="col-md-12" style="padding-left: 15%; padding-right: 15%;">
				<h2 class="page-header">
					Create product <a href="${pageContext.request.contextPath}/logout"
						class="btn btn-warning btn-xs">Logout</a>
				</h2>
				<c:if test="${not empty successMessage}">
					<div class="alert alert-success fade in">
						<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
						<strong>Success!</strong> ${successMessage}
					</div>
				</c:if>
				<c:if test="${not empty errorMessage}">
					<div class="alert alert-danger fade in">
						<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
						<strong>Creation failed!</strong> ${errorMessage}
					</div>
				</c:if>
				<form:form
					action="${pageContext.request.contextPath}/${user.getUserType().getType()}/createProduct"
					method="post" modelAttribute="inventoryProductHelper">
					<spring:bind path="name">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label for="Name">Name </label>
							<form:input path="name" class="form-control"
								placeHolder="Enter name" />
							<form:errors path="name" cssClass="text-danger" />
						</div>
					</spring:bind>
					<spring:bind path="upc">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label for="UPC">UPC </label>
							<form:input path="upc" class="form-control"
								placeHolder="Enter UPC. Must contain numbers only and should be 12 characters long" />
							<form:errors path="upc" cssClass="text-danger" />
						</div>
					</spring:bind>
					<spring:bind path="categoryName">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label for="category">Category</label>
							<form:select cssClass="form-control" path="categoryName">
								<option selected disabled value="">SELECT CATEGORY</option>
								<c:forEach items="${categories}" var="item">
									<option>${item.getName()}</option>
								</c:forEach>
							</form:select>
							<form:errors path="categoryName" cssClass="text-danger"></form:errors>
						</div>
					</spring:bind>
					<spring:bind path="description">
						<div class="form-group">
							<label for="description">Description</label>
							<form:textarea path="description" class="form-control" rows="5"
								placeHolder="Enter description"></form:textarea>
						</div>
					</spring:bind>
					<spring:bind path="price">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label for="price">Price</label>
							<form:input path="price" type="number" step=".01"
								class="form-control"
								placeHolder="Enter price. Must be greater than 0 and contain 2 decimal places max." />
							<form:errors path="price" cssClass="text-danger"></form:errors>
						</div>
					</spring:bind>
					<spring:bind path="stock">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label for="stock">Stock</label>
							<form:input path="stock" type="number" class="form-control"
								placeHolder="Enter stock. Stock can be any value between 10 and 100." />
							<form:errors path="stock" cssClass="text-danger"></form:errors>
						</div>
					</spring:bind>
					<div class="form-inline">
						<button type="submit" class="form-group btn btn-success">Create
							product</button>
						<a
							href="${pageContext.request.contextPath}/${user.getUserType().getType()}"
							class="form-group btn btn-secondary"> Back to Home</a>
					</div>
				</form:form>
			</div>
		</div>