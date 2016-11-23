<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<title>TS | Admin</title>
</head>
<body>
	<div class="container">
		<jsp:include page="admin_header.jsp"></jsp:include>
		<c:if test="${activePage == 'DASHBOARD'}">
			<jsp:include page="admin_dashboard.jsp"></jsp:include>
		</c:if>
		<c:if test="${activePage == 'USER_LIST'}">
			<jsp:include page="admin_view_users.jsp"></jsp:include>
		</c:if>
		<c:if test="${activePage == 'ADD_ADMINISTRATOR'}">
			<jsp:include page="admin_add_administrator.jsp"></jsp:include>
		</c:if>
		<c:if test="${activePage == 'ADD_CATEGORY'}">
			<jsp:include page="admin_add_category.jsp"></jsp:include>
		</c:if>
		<c:if test="${activePage == 'VIEW_CATEGORY'}">
			<jsp:include page="admin_view_categories.jsp"></jsp:include>
		</c:if>
		<c:if test="${activePage == 'UPDATE_CATEGORY'}">
			<jsp:include page="admin_update_category.jsp"></jsp:include>
		</c:if>
		<c:if test="${activePage == 'ADD_PRODUCT'}">
			<jsp:include page="admin_add_product.jsp"></jsp:include>
		</c:if>
		<c:if test="${activePage == 'VIEW_PRODUCT'}">
			<jsp:include page="admin_view_product.jsp"></jsp:include>
		</c:if>
		<c:if test="${activePage == 'UPDATE_PRODUCT'}">
			<jsp:include page="admin_update_product.jsp"></jsp:include>
		</c:if>
		<c:if test="${activePage == 'UPDATE_INVENTORY'}">
			<jsp:include page="admin_update_inventory.jsp"></jsp:include>
		</c:if>
		<c:if test="${activePage == 'VIEW_INVENTORY_HISTORY'}">
			<jsp:include page="admin_view_inventory_history.jsp"></jsp:include>
		</c:if>
	</div>
</body>
</html>