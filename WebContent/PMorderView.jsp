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
	$(function() {
		$("#portfolio_select").change(function() {
			var selected = $(this).val(); // or this.value
			alert(selected);
		});
	});

	$(window).bind('scroll', function() {
		if ($(window).scrollTop() > num) {
			$('.menu').addClass('fixed');
		} else {
			$('.menu').removeClass('fixed');
		}
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

	<%@ page import="com.web.sur.Portfolio"%>
	<%@ page import="java.util.*"%>
	<%
		HashMap<String, String> order1 = new HashMap<String, String>();
		order1.put("Order ID", "test2");
		order1.put("Block ID", "test1");
		order1.put("Symbol", "test1");
		order1.put("Trader ID", "test1");
		order1.put("Side", "test1");
		order1.put("Market Price", "test1");
		order1.put("Qty", "test1");
		order1.put("Date", "test1");
		order1.put("Time", "test1");
		order1.put("Status", "test2");
		HashMap<String, String> order2 = new HashMap<String, String>();
		order2.put("Order ID", "test2");
		order2.put("Block ID", "test2");
		order2.put("Symbol", "test2");
		order2.put("Trader ID", "test2");
		order2.put("Side", "test2");
		order2.put("Market Price", "test2");
		order2.put("Qty", "test2");
		order2.put("Date", "test2");
		order2.put("Time", "test2");
		order2.put("Status", "test2");

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
				for(Portfolio portfolio: portfolioList){
				%>
				<h3><%=portfolio.getName() %></h3>
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
										style="width: 42px;">Status</th>
								</tr>
							</thead>

							<tbody>



								<!-- This Type of Loop, Designed to print attributes and populate a table of predefined size. -->

								<%
									for (HashMap Order : portfolio.getOrderList()) {
								%>
								<tr role="row" class="odd">
									<%
										for (Object attribute : Order.values()) {
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
				<% }%>
			</div>
		</div>
		<div id="PM_position">
			<p>Position Tab</p>

			<label for="portfolio">Select Portfolio</label> <select
				name="portfolio_select" id="portfolio_select">
				<option selected="selected" value="p1">Portfolio 1</option>
				<option value="p2">Portfolio 2</option>
				<option value="p3">Portfolio 3</option>

			</select>
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