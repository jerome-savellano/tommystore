<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="row">
	<div class="col-md-12" style="padding-left: 5%; padding-right: 5%;">
		<h1 class="page-header" style="padding-below: 1%;">Add new
			administrator</h1>
		<c:if test="${not empty duplicateUser}">
			<div class="alert alert-danger fade in">
				<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
				<strong>Failed!</strong> User <strong>${duplicateUser.getEmail()}</strong>
				already exists. Please try again.
			</div>
		</c:if>
		<c:if test="${not empty newUser}">
			<div class="alert alert-success fade in">
				<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
				<strong>Success!</strong> User <strong>${newUser.getEmail()}</strong>
				successfully created!
			</div>
		</c:if>
		<form:form action="${pageContext.request.contextPath}/admin/addAdmin"
			method="post" modelAttribute="registerUser">
			<spring:bind path="email">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<label for="email"> Email address </label>
					<form:input type="email" class="form-control" path="email" />
					<form:errors path="email" cssClass="text-danger"></form:errors>
				</div>
			</spring:bind>
			<spring:bind path="password">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<label for="password"> Password </label>
					<form:input type="password" class="form-control" path="password" />
					<form:errors path="password" cssClass="text-danger"></form:errors>
				</div>
			</spring:bind>
			<spring:bind path="confirmPassword">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<label for="confirmPassword">Confirm password </label>
					<form:input type="password" class="form-control"
						path="confirmPassword" />
					<form:errors path="confirmPassword" cssClass="text-danger"></form:errors>
				</div>
			</spring:bind>
			<spring:bind path="firstName">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<label for="firstName"> First Name </label>
					<form:input type="text" class="form-control" path="firstName" />
					<form:errors path="firstName" cssClass="text-danger"></form:errors>
				</div>
			</spring:bind>
			<spring:bind path="lastName">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<label for="lastName"> Last name </label>
					<form:input type="text" class="form-control" path="lastName" />
					<form:errors path="lastName" cssClass="text-danger"></form:errors>
				</div>
			</spring:bind>
			<spring:bind path="contactNumber">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<label for="contactNumber"> Contact number </label>
					<form:input type="contactNumber" class="form-control"
						path="contactNumber" />
					<form:errors path="contactNumber" cssClass="text-danger"></form:errors>
				</div>
			</spring:bind>
			<div class="form-inline">
				<button type="submit" class="btn btn-primary">Submit</button>
				<a href="viewUsers" class="btn btn-success">View users</a>
			</div>
		</form:form>
	</div>
</div>