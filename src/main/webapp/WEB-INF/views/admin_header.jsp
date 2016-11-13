<%--  --%>
<div class="row">
	<div class="col-md-12">
		<nav class="navbar navbar-default " role="navigation"
			style="padding-right: 1%;">
			<div class="navbar-header">

				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span><span
						class="icon-bar"></span><span class="icon-bar"></span><span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand"
					href="${pageContext.request.contextPath}/admin/home">Hi, <strong
					class="text-info">${user.getFirstName()}</strong></a>
			</div>

			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a href="#">Dashboard</a></li>
					<li><a href="#">User lists</a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">Dropdown<strong class="caret"></strong></a>
						<ul class="dropdown-menu">
							<li class="dropdown-header">Administrator</li>
							<li><a href="#">Add new administrator</a></li>
							<li class="divider"></li>
							<li class="dropdown-header">Category</li>
							<li><a href="#">Add new category</a></li>
							<li><a href="#">Update category</a></li>
							<li><a href="#">Delete category</a></li>
							<li class="divider"></li>
							<li class="dropdown-header">Product</li>
							<li><a href="#">Add new product</a></li>
							<li><a href="#">Update product</a></li>
							<li><a href="#">Delete product</a></li>
							<li><a href="#">View product history</a></li>
							<li><a href="#">View product order</a></li>
						</ul></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
				</ul>
			</div>
		</nav>
		<div class="jumbotron">
			<h2>Hello, world!</h2>
			<p>This is a template for a simple marketing or informational
				website. It includes a large callout called the hero unit and three
				supporting pieces of content. Use it as a starting point to create
				something more unique.</p>
			<p>
				<a class="btn btn-primary btn-large" href="#">Learn more</a>
			</p>
		</div>
	</div>
</div>