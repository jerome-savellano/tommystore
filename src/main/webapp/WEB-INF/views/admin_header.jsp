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
					href="${pageContext.request.contextPath}/admin/dashboard">Hi, <strong
					class="text-info">${user.getFirstName()}</strong></a>
			</div>

			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a href="dashboard">Dashboard</a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">More<strong class="caret"></strong></a>
						<ul class="dropdown-menu">
							<li class="dropdown-header">User</li>
							<li><a href="addAdmin">Add new administrator</a></li>
							<li><a href="viewUsers?userType=">View users</a></li>
							<li class="divider"></li>
							<li class="dropdown-header">Category</li>
							<li><a href="addCategory">Add new category</a></li>
							<li><a href="viewCategories">View categories</a></li>
							<li class="divider"></li>
							<li class="dropdown-header">Product</li>
							<li><a href="addProduct">Add new product</a></li>
							<li><a href="viewProducts?category=">View products</a></li>
							<li><a href="viewOrders">View product orders</a></li>
							<li class="divider"></li>
							<li class="dropdown-header">Inventory</li>
							<li><a href="viewInventory">View inventory</a></li>
							<li><a href="viewInventoryHistory?prodId=">View inventory history</a></li>
						</ul></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
				</ul>
			</div>
		</nav>
		
	</div>
</div>