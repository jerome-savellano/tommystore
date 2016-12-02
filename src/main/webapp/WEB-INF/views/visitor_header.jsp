<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">
	<div class="col-md-12">
		<nav class="navbar navbar-default navbar-fixed" role="navigation">
			<div class="navbar-header">

				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span><span
						class="icon-bar"></span><span class="icon-bar"></span><span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Tommy's Store</a>
			</div>

			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">

				<ul class="nav navbar-nav navbar-right">
					<li><a id="header_cart"
						href="${pageContext.request.contextPath}/initial">Home</a></li>
					<li><a id="header_cart"
						href="${pageContext.request.contextPath}/viewCart">Cart</a></li>
					<li><a id="header_cart"
						href="${pageContext.request.contextPath}/categories">Categories</a></li>
					<li><a href="${pageContext.request.contextPath}/login">Login</a></li>
					<li><a href="${pageContext.request.contextPath}/register">Register</a></li>
				</ul>
			</div>
		</nav>
	</div>
</div>