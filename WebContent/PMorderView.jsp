<%@page import="org.omg.PortableInterceptor.SYSTEM_EXCEPTION"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Layout</title>
<link rel="stylesheet" href="jquery-ui.css">
<link rel="stylesheet" href="customMine.css">
<script src="jquery.js"></script>
<script src="jquery-ui.js"></script>
<script>
	$(function() {
		$("#tabs").tabs();
	});
	$(function() {
		$("#accordion").accordion({
			collapsible : true,
			active : 2
		});
	});
	$(function() {
		$("#portfolio_select").selectmenu();
	});
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
</style>
</head>
<body>

	<%@ page import="com.web.sur.Order"%>
	<%@ page import="com.web.sur.Portfolio"%>
	<%@ page import="java.util.*"%>
	<%
		Order order1 = new Order("O123", "B123", "AAPL", "T100", "BUY", "$100", "50", "test1", "test1", "", "",
				"LOGGED");
		Order order2 = new Order("O122", "B121", "SAPIENT", "T101", "SELL", "$50", "100", "test1", "test1", "", "",
				"PENDING");

		Portfolio portfolio1 = new Portfolio("Portfolio 1");
		portfolio1.addOrder(order1);
		portfolio1.addOrder(order2);
		Portfolio portfolio2 = new Portfolio("Portfolio 2");
		portfolio2.addOrder(order1);
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
			<p>OverView Tab Goes Here</p>
			<div id="accordion">

				<%
					for (Portfolio portfolio : portfolioList) {
				%>
				<h3><%=portfolio.getName()%></h3>
				<div>
					<div id="DataTables_Table_4_wrapper" class="dataTables_wrapper">
						<table class="demo compact dataTable" width="100%"
							id="DataTables_Table_4" role="grid" style="width: 100%;">
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
								<tr role="row" class="odd" id=<%=Order.getStatus()%>>


									<%
										for (Object attribute : Order.getAttributes()) {
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
					</div>
				</div>
				<%
					}
				%>
			</div>
		</div>
		<div id="PM_position">
			<p>Position Tab</p>

			<label for="portfolio">Select Portfolio</label> <select
				name="selectmenu1" id="portfolioselect">
				<option value="portfolio1">Portfolio1</option>
				<option value="portfolio2">Portfolio2</option>
				<option value="portfolio3">Portfolio3</option>
				<option value="portfolio4">Portfolio4</option>
			</select> </select>
		</div>
		<div id="PM_create_order">
			<p>Create Order Tab</p>
		</div>
	</div>
	<br>
	<br>
	<br>



</body>
</html>