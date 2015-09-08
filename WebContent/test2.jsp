<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.web.sur.*"%>
<%@ page import="java.util.*"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Money Tree Corporation, Ltd</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description"
	content="A preview of the jQuery UI Bootstrap theme.">
<meta name="author" content="Addy Osmani">

<!-- Le styles -->
<link rel="stylesheet" href="assets/css/bootstrap.min.css">
<link rel="stylesheet"
	href="css/custom-theme/jquery-ui-1.10.3.custom.css">
<!--<link rel="stylesheet" href="css/custom-theme/jquery-ui-1.10.3.theme.css">-->
<link rel="stylesheet" href="assets/css/font-awesome.min.css">
<!--[if IE 7]>
    <link rel="stylesheet" href="assets/css/font-awesome-ie7.min.css">
    <![endif]-->
<!--[if lt IE 9]>
    <link rel="stylesheet" href="css/custom-theme/jquery.ui.1.10.3.ie.css">
    <![endif]-->
<link rel="stylesheet" href="assets/css/docs.css">
<link rel="stylesheet"
	href="assets/js/google-code-prettify/prettify.css">
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="assets/js/vendor/html5shiv.js" type="text/javascript"></script>
      <script src="assets/js/vendor/respond.min.js" type="text/javascript"></script>
    <![endif]-->
<!-- Le fav and touch icons -->



</head>

<body data-spy="scroll" data-target=".bs-docs-sidebar"
	data-twttr-rendered="true">

	<!-- Navbar
    ================================================== -->
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button class="navbar-toggle" type="button" data-toggle="collapse"
					data-target="navbar-collapse">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand"
					href="http://github.com/addyosmani/jquery-ui-bootstrap">| Money
					Tree Corporation, Ltd |</a>
			</div>
			<nav class="navbar-collapse collapse">
				<div class="navbar-right">
					<button class="navbar-toggle" type="button" data-toggle="collapse"
						data-target="navbar-collapse">
						<span class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="index.html">| Logout |</a>
				</div>
			</nav>
		</div>
	</header>

	<!-- Subhead
    ================================================== -->
	<header class="bs-header" id="overview">
		<div class="container">
			<h1>Portfolio Manager Dashboard</h1>
		</div>
	</header>

	<div class="container" id="layout">
		<!-- Docs nav ================================================== -->
		<div class="row">
			<div class="col-lg-12 col-sm-12" id="article">
			<div class="page-header"></div>
				<%
		//Example Input for ORders and Portfolios
		Order order1 = new Order("O123", "B123", "AAPL", "T100", "BUY", "$100", "50", "test1", "test1", "", "",
				"LOGGED");
		Order order2 = new Order("O122", "B121", "SAPIENT", "T101", "SELL", "$50", "100", "test1", "test1", "", "",
				"PENDING");
		Stock stock1 = new Stock("AAPL","50","100","5000");
		Stock stock2 = new Stock("SAPIENT","40","500","20000");
		Stock stock3 = new Stock("GOOGL","100","40","4000");
		
		Portfolio portfolio1 = new Portfolio("Portfolio1");
		portfolio1.addOrder(order1);
		portfolio1.addOrder(order2);
		
		portfolio1.addStock(stock1);
		portfolio1.addStock(stock2);
		portfolio1.addStock(stock3);
		Portfolio portfolio2 = new Portfolio("Portfolio2");
		portfolio2.addOrder(order1);
		portfolio2.addStock(stock1);
		portfolio2.addStock(stock2);
		ArrayList<Portfolio> portfolioList = new ArrayList<Portfolio>();
		portfolioList.add(portfolio1);
		portfolioList.add(portfolio2);
	%>

	<div id="tabs">
		<ul>
			<li><a href="#PM_order_view">Order View</a></li>
			<li><a href="#PM_position">Position</a></li>
			<li><a href="#PM_create_order">Create Order</a></li>
		</ul>
		<div id="PM_order_view">
			<div>
			<label for="status_select">Filter ByStatus:</label> <select
				name="status_select" class="status_select">
				<option value ="SHOWALL">Show All</option>
				<option value="LOGGED">LOGGED</option>
				<option value="OPEN">OPEN</option>
				<option value="PENDING">PENDING</option>
				<option value="FILLED">FILLED</option>
				</select>
			</div>
			<div id="menu-collapse">
				<%
					for (Portfolio portfolio : portfolioList) {
				%>
				<h3><%=portfolio.getName()%></h3>
				<div>
						<table class="orderview_table" width="100%"
							id="<%=portfolio.getName()%>_table" role="grid" style="width: 100%;">
							<thead>
								<tr role="row">
									<th class="sorting_asc" tabindex="0"
										aria-controls="DataTables_Table_4" rowspan="1" colspan="1"
										aria-sort="ascending"
										aria-label="s activate to sort column descending"
										style="width: 20px;">Order ID</th>
									<th class="sorting" tabindex="0"
										aria-controls="DataTables_Table_4" rowspan="1" colspan="1"
										aria-label="Position: activate to sort column ascending"
										style="width: 30px;">Block ID</th>
									<th class="sorting" tabindex="0"
										aria-controls="DataTables_Table_4" rowspan="1" colspan="1"
										aria-label="Office: activate to sort column ascending"
										style="width: 78px;">Symbol</th>
									<th class="sorting" tabindex="0"
										aria-controls="DataTables_Table_4" rowspan="1" colspan="1"
										aria-label="Age: activate to sort column ascending"
										style="width: 27px;">Trader ID</th>
									<th class="sorting" tabindex="0"
										aria-controls="DataTables_Table_4" rowspan="1" colspan="1"
										aria-label="Start date: activate to sort column ascending"
										style="width: 68px;">Side</th>
									<th class="sorting" tabindex="0"
										aria-controls="DataTables_Table_4" rowspan="1" colspan="1"
										aria-label="Salary: activate to sort column ascending"
										style="width: 42px;">Market Price</th>
									<th class="sorting" tabindex="0"
										aria-controls="DataTables_Table_4" rowspan="1" colspan="1"
										aria-label="Salary: activate to sort column ascending"
										style="width: 42px;">Qty</th>
									<th class="sorting" tabindex="0"
										aria-controls="DataTables_Table_4" rowspan="1" colspan="1"
										aria-label="Salary: activate to sort column ascending"
										style="width: 42px;">Date</th>
									<th class="sorting" tabindex="0"
										aria-controls="DataTables_Table_4" rowspan="1" colspan="1"
										aria-label="Salary: activate to sort column ascending"
										style="width: 42px;">Time</th>
									<th class="sorting" tabindex="0"
										aria-controls="DataTables_Table_4" rowspan="1" colspan="1"
										aria-label="Salary: activate to sort column ascending"
										style="width: 42px;">Type</th>
									<th class="sorting" tabindex="0"
										aria-controls="DataTables_Table_4" rowspan="1" colspan="1"
										aria-label="Salary: activate to sort column ascending"
										style="width: 42px;">Type Parameter</th>
									<th class="sorting" tabindex="0"
										aria-controls="DataTables_Table_4" rowspan="1" colspan="1"
										aria-label="Salary: activate to sort column ascending"
										style="width: 42px;">Status</th>
								</tr>
							</thead>

							<tbody>
								<!-- This Type of Loop, Designed to print attributes and populate a table of predefined size. -->
								<%
									for (Order Order : portfolio.getOrderList()) {
								%>
								<div><tr  class="orderrow <%=Order.getStatus()%>">
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
				<%
					}
				%>
			</div>
		</div>
		<div id="PM_position">
			<p>Position Tab</p>

			<label for="portfolio">Select Portfolio</label> <select
				name="selectmenu1" class="port_select">
				
				<%
					for (Portfolio portfolio : portfolioList) {
				%>
				<option value=<%=portfolio.getName()%>><%=portfolio.getName()%></option>
				<% }%>
			</select> </select>
			<div>
			<%
					for (Portfolio portfolio : portfolioList) {
				%>
			<table class="portfolio_options" width="100%"
							
							id=<%=(portfolio.getName()+"_option")%> role="grid" style="width: 100%;">
							<thead>
								<tr role="row">
									<th class="sorting_asc" tabindex="0"
										aria-controls="DataTables_Table_4" rowspan="1" colspan="1"
										aria-sort="ascending"
										aria-label="s activate to sort column descending"
										style="width: 20px;">Symbol</th>
									<th class="sorting" tabindex="0"
										aria-controls="DataTables_Table_4" rowspan="1" colspan="1"
										aria-label="Position: activate to sort column ascending"
										style="width: 30px;">Quantity</th>
									<th class="sorting" tabindex="0"
										aria-controls="DataTables_Table_4" rowspan="1" colspan="1"
										aria-label="Office: activate to sort column ascending"
										style="width: 78px;">Price</th>
									<th class="sorting" tabindex="0"
										aria-controls="DataTables_Table_4" rowspan="1" colspan="1"
										aria-label="Age: activate to sort column ascending"
										style="width: 27px;">Market Value</th>
								</tr>
							</thead>
							<tbody>
								<!-- This Type of Loop, Designed to print attributes and populate a table of predefined size. -->

								<%
									for (Stock stock : portfolio.getStockList()) {
								%>
								<tr role="row" class="odd" id=<%=stock.getSymbol()%>>


									<%
										for (Object attribute : stock.getAttributes()) {
									%>
									<td><%=attribute%></td>
									<%
										}
									%>
								</tr>
								<%
									}
								%>



							</tbody>
						</table>
						<%
					}
				%>
			</div>
		</div>
		<div id="PM_create_order">
			<p>Create Order Tab</p>
		</div>
	</div>
	<br>
	<br>
	<br>

				</div>
			</div>
		</div>
	</div>

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
	
	
	<!-- Placed at the end of the document so the pages load faster -->
	<script>
	$(function() {
		$("#tabs").tabs();
	});

	// Accordion
	$("#menu-collapse").accordion({
		header : "h3",
		collapsible : true,
		active : 2
	});

	$(document).on('change', '.port_select', function() {
		$(".portfolio_options").hide();
		var portfolio = $(this).val();
		$('#' + portfolio + '_option').show();
	});
	$(document).on('change', '.status_select', function() {
		var status = $(this).val();
		if (status == "SHOWALL") {
			$(".orderrow").show()
		} else {
			$(".orderrow").hide();
			$("." + status).show();
		}
	});
	$(document).ready(function() {
		alert("Doc Ready");
		$(".portfolio_options").hide();
		$('#' + "Portfolio1" + '_option').show();
	});
</script>
	<script src="assets/js/vendor/jquery-1.9.1.min.js"
		type="text/javascript"></script>
	<script src="assets/js/vendor/jquery-migrate-1.2.1.min.js"
		type="text/javascript"></script>
	<script src="assets/js/vendor/bootstrap.js" type="text/javascript"></script>
	<script src="assets/js/vendor/holder.js" type="text/javascript"></script>
	<script src="assets/js/vendor/jquery-ui-1.10.3.custom.min.js"
		type="text/javascript"></script>
	<script src="assets/js/google-code-prettify/prettify.js"
		type="text/javascript"></script>
	<script src="assets/js/docs.js" type="text/javascript"></script>
	<script src="assets/js/demo.js" type="text/javascript"></script>
</body>
</html>