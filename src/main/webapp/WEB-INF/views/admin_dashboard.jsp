<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="row"
	style="padding: 2%; padding-left: 5%; padding-right: 5%;">
	<div class="col-md-12">
		<h1>Dashboard</h1>
	</div>
	<div class="col-md-6">
		<h2 class="page-header">Heading</h2>
		<p>Donec id elit non mi porta gravida at eget metus. Fusce
			dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut
			fermentum massa justo sit amet risus. Etiam porta sem malesuada magna
			mollis euismod. Donec sed odio dui.</p>
		<p>
			<a class="btn" href="#">View details »</a>
		</p>
	</div>
	<div class="col-md-6">
		<h2 class="page-header">New users (last 24 hours)</h2>
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
	</div>
</div>