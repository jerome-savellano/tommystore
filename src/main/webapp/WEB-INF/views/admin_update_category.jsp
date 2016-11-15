<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="row">
	<div class="col-md-12" style="padding-left: 5%; padding-right: 5%;">
		<h1 class="page-header" style="padding-below: 1%;">Update
			category</h1>
		<c:if test="${not empty duplicateCategory}">
			<div class="alert alert-danger fade in">
				<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
				<strong>Failed!</strong> Category <strong>${duplicateCategory.getName()}</strong>
				already exists. Please try again.
			</div>
		</c:if>
		<c:if test="${not empty updatedCategory}">
			<div class="alert alert-success fade in">
				<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
				<strong>Success!</strong> Category <strong>${updatedCategory.getName()}</strong>
				successfully updated!
			</div>
		</c:if>
		<form:form action="${pageContext.request.contextPath}/admin/updateCategory"
			method="post" modelAttribute="category">
			<spring:bind path="name">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<label for="email"> Name </label>
					<form:input type="text" class="form-control" path="name" />
					<form:input type="hidden" class="form-control" path="categoryId" />
					<form:errors path="name" cssClass="text-danger"></form:errors>
				</div>
			</spring:bind>
					
			<div class="form-inline">
				<button type="submit" class="btn btn-primary">Submit</button>
				<a href="viewCategories" class="btn btn-success">View categories</a>
			</div>
		</form:form>
	</div>
</div>