<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-12" style="padding: 5%;">
				<h1 class="page-header">Register</h1>
				<form:form action="${pageContext.request.contextPath}/register"
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
						<a href="${pageContext.request.contextPath}/initial"
						   class="btn btn-secondary">Back to home</a>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>