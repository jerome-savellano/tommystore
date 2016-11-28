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

					<div class="navbar-form navbar-left">
						<div class="form-group">
							<input type="text" class="form-control" />
						</div>
						<button type="submit" class="btn btn-default">Search product</button>
					</div>
					<c:choose>
						<c:when test="${not empty user.getEmail() && user.getUserType() == 'CUSTOMER'}">
							<ul class="nav navbar-nav navbar-right">
								<li><a href="#">Hi, <span class="text-primary">${user.getEmail()}</span></a></li>
								<li><a href="#">Cart</a></li>
								<li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
							</ul>
						</c:when>
						<c:otherwise>
							<ul class="nav navbar-nav navbar-right">
								<li><a href="${pageContext.request.contextPath}/login">Login</a></li>
								<li><a href="${pageContext.request.contextPath}/register">Register</a></li>
							</ul>
						</c:otherwise>
					</c:choose>
				</div>
				</nav>
			</div>
		</div>