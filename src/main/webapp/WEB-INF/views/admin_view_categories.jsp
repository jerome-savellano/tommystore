<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="row"
	style="padding: 2%; padding-left: 5%; padding-right: 5%;">
	<div class="col-md-12">
		<h1 class="page-header">Categories</h1>
		<c:if test="${not empty categoryNotFound}">
			<div class="alert alert-danger fade in">
				<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
				<strong>Failed!</strong> ${categoryNotFound}
			</div>
		</c:if>
		<c:if test="${not empty category}">
			<div class="alert alert-danger fade in">
				<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
				<strong>Failed!</strong> Category <strong>${category.getName()}</strong> has products under it.&nbsp; 
				<a class="btn btn-primary btn-xs" href="viewProducts?category=${category.getName()}">View products</a>
			</div>
		</c:if>
		<c:choose>
			<c:when test="${not empty categories}">
				<div style="padding-top: 1%">
					<table class="table table-striped">
						<thead class="bg-primary">
							<tr>
								<th>Name</th>
								<th></th>
								<th></th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${categories}" var="category"
								varStatus="status">
								<tr>
									<td class="col-md-4">${category.getName()}</td>
									<td class="text-center"><a href="google.com">View products</a></td>
									<td class="text-center"><a
										href="updateCategory?categoryName=${category.getName()}"
										class="btn btn-success">Update</a></td>
									<td class="text-center"><a
										href="deleteCategory?categoryName=${category.getName()}"
										class="btn btn-danger">Delete</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</c:when>
			<c:otherwise>
				<h1 class="text-muted text-center">No categories yet. Start
					creating one!</h1>
				<div class="col-md-12 text-center" style="padding-top: 1%">
					<a class="btn btn-lg btn-success btn-center" href="addCategory">Add
						category</a>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
</div>