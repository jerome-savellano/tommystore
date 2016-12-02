<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script type="text/javascript"
	src="<c:url value="/resources/js/admin_view_order.js" />"></script>
<div class="row"
	style="padding: 1%; padding-left: 5%; padding-right: 5%; margin-top: -2em;">
	<h1 class="page-header">Order history</h1>
	<div style="margin-top: -1em">
		<table id="dt" class="table table-bordered table-striped">
			<thead class="bg-primary">
				<tr>
					<th class="col-xs-2">Order number</th>
					<th class="col-xs-2">Product</th>
					<th class="col-xs-2">Quantity</th>
					<th class="col-xs-2">Date ordered</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${orders}" var="order"
					varStatus="status">
					<tr>
						<td>${order.getOrderNumber()}</td>
						<td>${order.getProduct().getName()}</td>
						<td>${order.getQuantity()}</td>
						<td>${order.getOrderDate()}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>