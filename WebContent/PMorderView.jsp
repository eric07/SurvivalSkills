<%@page import="org.omg.PortableInterceptor.SYSTEM_EXCEPTION"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Money Tree Corporation, Ltd</title>
<link rel="stylesheet" href="css/jquery-ui.css">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/docs.css">
<script src="scripts/jquery.js"></script>
<script src="scripts/jquery-ui.js"></script>
<script src="scripts/orderView.js"></script>
<script>
	
</script>
<style>
fieldset {
	border: 0;
}

label {
	display: block;
	margin: 10px 0 0 0;
}

select {
	width: 200px;
}

.overflow {
	height: 50px;
}

#testheight {
	max-height: 200px;
	overflow: auto;
}

.orderview_table{
table-layout: fixed;
}
</style>
</head>
<body>
	<%@ page import="com.web.sur.*"%>

	<%@ page import="java.util.*"%>
	<%
		HttpSession hs = request.getSession();
		// Receiving list of porfolios from servlets
		ArrayList<Portfolio> portfolioList = (ArrayList<Portfolio>) hs
				.getAttribute("Portlist");
	%>

	<!-- Navbar
    ================================================== -->
	<nav class="navbar navbar-default navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<button class="navbar-toggle" type="button" data-toggle="collapse"
				data-target="navbar-collapse">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">| Money Tree Corporation, Ltd |</a>
		</div>

		<div class="navbar-right">
			<button class="navbar-toggle" type="button" data-toggle="collapse"
				data-target="navbar-collapse">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="./logoutServlet">| Logout |</a>
		</div>

	</div>
	</nav>

	<!-- Subhead
    ================================================== -->
	<header class="bs-header" id="overview">
	<div class="container">
		<h1>Portfolio Manager Dashboard</h1>
	</div>
	</header>
	<br>
	<br>


	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<div id="tabs">
					<!-- Tab menu to navigate in PM Dashboard  -->
					<ul>
						<li><a href="#PM_order_view">Order View</a></li>
						<li><a href="./PositionServlet"> Position </a></li>
						<li><a href="PMCreateOrder.jsp">Create Order</a></li>
					</ul>

					<!-- HTML code for viewing orders  -->
					<div id="PM_order_view">
						<div>
							<label for="status_select">Filter ByStatus:</label> <select
								name="status_select" class="status_select">
								<option value="SHOWALL">Show All</option>
								<option value="New">New</option>
								<option value="Open">OPEN</option>
								<option value="Pending">PENDING</option>
								<option value="Filled">FILLED</option>
							</select>
						</div>
						<!-- Navigate along porfolios  -->
						<div id="accordion">
							<%
								for (Portfolio portfolio : portfolioList) {
							%>
							<h3><%=portfolio.getName()%></h3>
							<div style="height: 10px;">
								<table class="orderview_table" style="width:98%;"
									id="<%=portfolio.getName()%>_table" role="grid"
									>
									<thead>
										<tr role="row">
											<th class="sorting_asc" tabindex="0" style="width: 5%;">Order</th>
											<th class="sorting" tabindex="0" style="width: 5%;">Block</th>
											<th class="sorting" tabindex="0" style="width: 10%;">Symbol</th>
											<th class="sorting" tabindex="0" style="width: 5%;">Trader</th>
											<th class="sorting" tabindex="0" style="width: 5%;">Side</th>
											<th class="sorting" tabindex="0" style="width: 10%;">Market
												Price</th>
											<th class="sorting" tabindex="0" style="width: 5%;">Qty</th>
											<th class="sorting" tabindex="0" style="width: 15%;">Date</th>
											<th class="sorting" tabindex="0" style="width: 10%;">Time</th>
											<th class="sorting" tabindex="0" style="width: 10%;">Type</th>
											<th class="sorting" tabindex="0" style="width: 10%;">Type
												Parameter</th>

											<th class="sorting" tabindex="0" style="width: 10%;">Status</th>
										</tr>
									</thead>
								</table>
								<div id="testheight">

									<table class="orderview_table" style="width:100%;">
									
										<thead>
											<tr class="hidethis" role="row", height="0%">
												<th class="sorting_asc" tabindex="0" style="width: 5%;"></th>
												<th class="sorting" tabindex="0" style="width: 5%;"></th>
												<th class="sorting" tabindex="0" style="width: 10%;"></th>
												<th class="sorting" tabindex="0" style="width: 5%;"></th>
												<th class="sorting" tabindex="0" style="width: 5%;"></th>
												<th class="sorting" tabindex="0" style="width: 10%;"></th>
												<th class="sorting" tabindex="0" style="width: 5%;"></th>
												<th class="sorting" tabindex="0" style="width: 15%;"></th>
												<th class="sorting" tabindex="0" style="width: 10%;"></th>
												<th class="sorting" tabindex="0" style="width: 10%;"></th>
												<th class="sorting" tabindex="0" style="width: 10%;"></th>
												<th class="sorting" tabindex="0" style="width: 10%;"></th>
											</tr>
										</thead>
										<tbody>
											<!-- Navigate in all orders of one portfolio  -->
											<%
												for (Order Order : portfolio.getOrderList()) {
											%>
											<div>
												<tr class="orderrow <%=Order.getStatus()%>">
													<%
														for (Object attribute : Order.getAttributes()) {
													%>
													<td><%=attribute%></td>
													<%
														}
													%>
												</tr>
											</div>
											<%
												}
											%>
										</tbody>
									</table>
								</div>

							</div>
							<%
								}
							%>
						</div>
					</div>


				</div>


			</div>

		</div>

	</div>
	<br>
	<br>
	<br>
	<!-- Footer
================================================== -->
	<footer class="bs-footer">
	<div class="container">
		<div class="row">
			<div class="col-lg-6 col-sm-5">
				<h5>
					<strong>Money Tree Corporation, Ltd</strong> &copy; 2015
				</h5>
			</div>
		</div>
	</div>
	</footer>
</body>
</html>