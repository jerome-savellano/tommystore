<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="row"
	style="padding: 2%; padding-left: 5%; padding-right: 5%;">
	<div class="col-md-12">
		<h1 class="page-header">Users</h1>
		<form:form 
			class="form-inline"
			action="viewUsers"
			method="get">
			<label for="usertype">Filter by type: </label> <select
				class="form-control" name="userType">
				<option selected>ALL</option>
				<option>ADMINISTRATOR</option>
				<option>CUSTOMER</option>
			</select>
			<button type="submit" class="btn btn-primary">View</button>
		</form:form>
		<div style="padding-top: 1%">
			<table class="table table-striped">
				<thead class="bg-primary">
					<tr>
						<th>Email</th>
						<th>Name</th>
						<th>User type</th>
						<th>Date registered</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${users}" var="eachUser" varStatus="status">
						<tr>
							<td>${eachUser.getEmail()}</td>
							<td>${eachUser.getFirstName()} &nbsp; ${eachUser.getLastName()}</td>
							<td>${eachUser.getUserType()}</td>
							<td>${eachUser.getDateCreated()}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>