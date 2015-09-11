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
<link rel="stylesheet" href="css/font-awesome.min.css">
<script src="scripts/jquery.js"></script>
<script src="scripts/jquery-ui.js"></script>
<script src="scripts/orderView.js"></script>
<script>
	$(function() {
		$("#tabs").tabs();
	});
	$(function() {
		$(".sendbutton").button().click(function(event) {
			event.preventDefault();
			alert($(this).attr('name'));
		});
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
		$(".portfolio_options").hide();
		$('#' + "Portfolio1" + '_option').show();
	});

	$(document).on('click', ".BlockButton", function() {
		var BlockID = $(this).closest('.block_table').attr('id');
		$("." + BlockID + "_row").toggle();
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

.table-container {
	display: flex;
}

.fixed {
	width: 100%;
}

.flex-item {
	flex-grow: 1;
}

.BlockButton{
    width: 2em;
}
</style>
</head>
<body>
	<%@ page import="com.web.sur.*"%>

	<%@ page import="java.util.*"%>

	<%
		HttpSession hs = request.getSession();
		// Receiving list of blocks from servlets
		ArrayList<Block> blockList = (ArrayList<Block>) hs
				.getAttribute("Blockslist");
	%>

		<!-- Navbar
    ================================================== -->
	<nav class="navbar navbar-default navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
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
		<h1>Trader Dashboard</h1>
	</div>
	</header>
	<br>
	<br>


	<!-- Hardcoded values for testing -->
	<%
		BlockingFunc func = new BlockingFunc();
		ArrayList<Block> blocklist = new ArrayList<Block>();
		Order order1 = new Order("O122", "B121", "APPLE", "T101", "SELL",
				"100", "60", "test1", "test1", "MARKET", "", "LOGGING");
		Order order2 = new Order("O122", "B121", "APPLE", "T101", "SELL",
				"100", "40", "test1", "test1", "MARKET", "", "LOGGING");
		Order order3 = new Order("O122", "B121", "SAPIENT", "T101", "BUY",
				"55", "100", "test1", "test1", "MARKET", "", "LOGGING");
		Order order4 = new Order("O122", "B121", "SAPIENT", "T101", "SELL",
				"50", "100", "test1", "test1", "LIMIT", "50", "LOGGING");
		Order order5 = new Order("O122", "B121", "SAPIENT", "T101", "SELL",
				"50", "100", "test1", "test1", "LIMIT", "60", "LOGGING");
		Order order6 = new Order("O122", "B121", "APPLE", "T101", "BUY",
				"60", "200", "test1", "test1", "MARKET", "", "LOGGING");
		Order order7 = new Order("O122", "B121", "APPLE", "T101", "BUY",
				"60", "100", "test1", "test1", "MARKET", "", "LOGGING");
		ArrayList<Order> orderlist = new ArrayList<Order>();
		orderlist.add(order1);
		orderlist.add(order2);
		orderlist.add(order3);
		orderlist.add(order4);
		orderlist.add(order5);
		orderlist.add(order6);
		orderlist.add(order7);
		func.sortOrdersToBlocks(orderlist, blocklist);
	%>

	<div class="container">
	<div class="row">
			<div class="col-lg-12">

		<div id="tabs">
			<ul>
				<li><a href="#T_Block_Create">Blocks</a></li>
				<li><a href="#T_Workbench">Order Workbench</a></li>
				<li><a href="#T_Create Order">Create Order</a></li>
				<li><a href="#T_Block_Histroy_View">Block History</a></li>
			</ul>
			<div id="T_Block_Create" class="table-container">
				<%
					String BlockTitle = "";
					for (Block block : blocklist) {
				%>
				<div class="fixed" style="display: inline-block">
					<table id="<%=block.getBlockID()%>" class="block_table"
						width="100%" role="grid" style="width: 100%;">

						<tr>
							<td>
								<table style="width: 100%;">
									<tr role="row">
										<th style="width: 5%;"></th>
										<th align="left" class="sorting_asc" tabindex="0"
											style="width: 15%;">ID</th>
										<th align="left" class="sorting" tabindex="0"
											style="width: 15%;">Status</th>
										<th align="left" class="sorting" tabindex="0"
											style="width: 15%;">Symbol</th>
										<th align="left" class="sorting" tabindex="0"
											style="width: 10%;">Side</th>
										<th align="left" class="sorting" tabindex="0"
											style="width: 15%;">Trade Type</th>
										<th align="left" class="sorting" tabindex="0"
											style="width: 15%;">Trade Parameter</th>
										<th align="left" class="sorting" tabindex="0"
											style="width: 5%;">Qty</th>
									</tr>
								</table>
							</td>
						</tr>
						<td>
							<table style="width: 100%;">
								<tr role="row">
									<th style="width: 5%;">
										<button class="BlockButton" type="submit" form="form1"
											value="Submit">+</button>
									</th>
									<th align="left" class="sorting" tabindex="0"
										style="width: 15%;"><%=block.getBlockID()%></th>
									<th align="left" class="sorting" tabindex="0"
										style="width: 15%;"><%=block.getStatus()%></th>
									<th align="left" class="sorting" tabindex="0"
										style="width: 15%;"><%=block.getSymbol()%></th>
									<th align="left" class="sorting" tabindex="0"
										style="width: 10%;"><%=block.getSide()%></th>
									<th align="left" class="sorting" tabindex="0"
										style="width: 15%;"><%=block.getTradetype()%></th>
									<th align="left" class="sorting" tabindex="0"
										style="width: 15%;"><%=block.getTradeParam()%></th>
									<th align="left" class="sorting_asc" tabindex="0"
										style="width: 5%;"><%=block.gettotalQty()%></th>
								</tr>
							</table>
						</td>
						<td>
						<tr>
							<tbody>
							<tr>
							<td>
								<table style="width: 100%;">
									<thead>
										<tr role="row">
											<th style="width: 5%;">
												<div></div>
											</th>
							<th class="sorting_asc" tabindex="0" style="width: 15%;"></th>
							<th class="sorting" tabindex="0" style="width: 15%;"></th>
							<th class="sorting" tabindex="0" style="width: 15%;"></th>
							<th class="sorting" tabindex="0" style="width: 10%;"></th>
							<th class="sorting" tabindex="0" style="width: 15%;"></th>
							<th class="sorting" tabindex="0" style="width: 15%;"></th>
							<th class="sorting" tabindex="0" style="width: 5%;"></th>
						</tr>
						


									
										</thead>

									<tbody>

										<%
											for (Order order : block.getOrderList()) {
										%>
										<tr
												class="<%="orderblockrow" + " " + block.getBlockID()
							+ "_row"%>">
											<td></td>

											<%
												for (String attribute : order.getBlockAttributes()) {
											%>
											<td><%=attribute%></td>
											<%
												}
											%>
											<%
												}
											%>
										
								
									</table>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="flex-item" align="right">
				<form action="SendBlockToBroker" method="post">
					<button type="submit"
							class="<%=block.getBlockID() + "_send"%> sendbutton"
							value="Submit" name=<%=block.getBlockID()%>>Send Block</button>
				</form>
			</div>
			<%
				}
			%>
		</div>
		<div id="T_Workbench"></div>
		<div id="T_Create Order"></div>
		<div id="T_Block_Histroy_View"></div>
	</div>
	<br>
	<br>
	<br>
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

</body>
</html>