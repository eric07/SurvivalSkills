<%@page import="org.omg.PortableInterceptor.SYSTEM_EXCEPTION"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.web.sur.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Layout</title>
<link rel="stylesheet" href="jquery-ui.css">
<script src="jquery.js"></script>
<script src="jquery-ui.js"></script>
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

.ui-menu { width: auto; }

</style>
</head>
<body>

<div id="navigator">
	<ul id="nav" class="testnav">
		<li><a class="clk" href="#">Item 1</a></li>
		<li><a class="clk" href="#">Item 2</a></li>
		<li><a class="clk" href="#">Item 3</a>
			<ul class="sub-menu">
				<li><a href="#">Item 3-1</a>
					<ul class="sub-menu">
						<li><a href="#">Item 3-11</a></li>
						<li><a href="#">Item 3-12</a>
							<ul>
								<li><a href="#">Item 3-111</a></li>							
								<li><a href="#">Item 3-112</a>
									<ul>
										<li><a href="#">Item 3-1111</a></li>							
										<li><a href="#">Item 3-1112</a></li>							
										<li><a href="#">Item 3-1113</a>
											<ul>
												<li><a href="#">Item 3-11131</a></li>							
												<li><a href="#">Item 3-11132</a></li>							
											</ul>
										</li>							
									</ul>
								</li>
								<li><a href="#">Item 3-113</a></li>
							</ul>
						</li>
						<li><a href="#">Item 3-13</a></li>
					</ul>
				</li>
				<li><a href="#">Item 3-2</a>
					<ul>
						<li><a href="#."> Item 3-21 </a></li>
						<li><a href="#."> Item 3-22 </a></li>
						<li><a href="#."> Item 3-23 </a></li>
					</ul>	
				</li>
				<li><a href="#">Item 3-3</a></li>
				<li><a href="#">Item 3-4</a></li>
				<li><a href="#">Item 3-5</a></li>
			</ul>
		</li>
		<li><a class="clk" href="#">Item 4</a>
			<ul class="sub-menu">
				<li><a href="#">Item 4-1</a>
					<ul class="sub-menu">
						<li><a href="#."> Item 4-11 </a></li>
						<li><a href="#."> Item 4-12 </a></li>
						<li><a href="#."> Item 4-13 </a>
							<ul>
								<li><a href="#."> Item 4-131 </a></li>
								<li><a href="#."> Item 4-132 </a></li>
								<li><a href="#."> Item 4-133 </a></li>
							</ul>
						</li>
					</ul>
				</li>
                <li><a href="#">Item 4-2</a></li>
                <li><a href="#">Item 4-3</a></li>
            </ul>
		</li>
		<li><a class="clk" href="#">Item 5</a></li>
	</ul>
</div>


	<%
		//Example Input for ORders and Portfolios
		Order order1 = new Order("O123", "B123", "AAPL", "T100", "BUY",
				"$100", "50", "test1", "test1", "", "", "LOGGED");
		Order order2 = new Order("O122", "B121", "SAPIENT", "T101", "SELL",
				"$50", "100", "test1", "test1", "", "", "PENDING");
		Stock stock1 = new Stock("AAPL", "50", "100", "5000");
		Stock stock2 = new Stock("SAPIENT", "40", "500", "20000");
		Stock stock3 = new Stock("GOOGL", "100", "40", "4000");

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
					<option value="SHOWALL">Show All</option>
					<option value="LOGGED">LOGGED</option>
					<option value="OPEN">OPEN</option>
					<option value="PENDING">PENDING</option>
					<option value="FILLED">FILLED</option>
				</select>
			</div>
			<div id="accordion">
				<%
					for (Portfolio portfolio : portfolioList) {
				%>
				<h3><%=portfolio.getName()%></h3>
				<div>
					<table class="orderview_table" width="100%"
						id="<%=portfolio.getName()%>_table" role="grid"
						style="width: 100%;">
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
				<%
					}
				%>
			</select> </select>
			<div>
				<%
					for (Portfolio portfolio : portfolioList) {
				%>
				<table class="portfolio_options" width="100%"
					id=<%=(portfolio.getName() + "_option")%> role="grid"
					style="width: 100%;">
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
</body>
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
		$(".testnav").addClass("ui-menu ui-widget ui-widget-content ui-corner-all");
		$(".testnav li").addClass("ui-menu-item");
		$(".sub-menu").hide();


		$(".clk").click(function(){
			
			var menu = "#nav";
			var position = {my: "left top", at: "left bottom+8"};
			$(menu).menu({
		    position: position,
		    blur: function() {
		        $(this).menu("option", "position", position);
		    },
		    focus: function(e, ui) {
		        if ($(menu).get(0) !== $(ui).get(0).item.parent().get(0)) {
		            $(this).menu("option", "position", {my: "left top", at: "left top"});
		        }
		    });
	});

	$(function() {
		$("#menu").menu();
	});
	




		});

		}); // End of document function
	
</script>
</html>