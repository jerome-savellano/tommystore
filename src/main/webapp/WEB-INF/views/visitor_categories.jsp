<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="row"
	style="padding: 2%; padding-left: 5%; padding-right: 5%; margin-top: -3em;">
	<div class="col-md-12">
		<h1 class="page-header">Categories</h1>
		<div class="list-group" style="padding-left: 5%; padding-right: 5%;">
			<c:forEach items="${categories}" var="category">
				<a href="viewProducts?category=${category.getName()}" class="list-group-item list-group-item-action">${category.getName()}</a>
			</c:forEach>
		</div>
	</div>
</div>
