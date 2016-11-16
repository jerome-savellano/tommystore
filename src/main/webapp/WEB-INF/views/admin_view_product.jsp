<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:forEach items="${products}" var="product" varStatus="status">
	<div class="col-md-4" style="padding: 1%">
		<div class="card">
			<img class="card-img-top"
				src="${pageContext.request.contextPath}/image?name=${product.getName()}"
				alt="Card image cap" style="width: 100%; height: auto;">
			<div class="card-block">
				<h4 class="card-title">Card title</h4>
				<p class="card-text">Some quick example text to build on the
					card title and make up the bulk of the card's content.</p>
				<a href="#" class="btn btn-primary">Go somewhere</a>
			</div>
		</div>
	</div>
</c:forEach>
