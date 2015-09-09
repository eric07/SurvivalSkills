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
<script src="jquery.js"></script>
<script src="jquery-ui.js"></script>
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
		alert("Doc Ready");
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

.container {
	display: flex;
}

.fixed {
	width: 80%;
}

.flex-item {
	flex-grow: 1;
}
</style>
</head>
<body>
	<%@ page import="com.web.sur.*"%>

	<%@ page import="java.util.*"%>
	<%
		BlockingFunc func = new BlockingFunc();
		ArrayList<Block> blocklist = new ArrayList<Block>();
		Order order1 = new Order("O122", "B121", "APPLE", "T101", "SELL", "100", "60", "test1", "test1", "MARKET",
				"", "LOGGING");
		Order order2 = new Order("O122", "B121", "APPLE", "T101", "SELL", "100", "40", "test1", "test1", "MARKET",
				"", "LOGGING");
		Order order3 = new Order("O122", "B121", "SAPIENT", "T101", "BUY", "55", "100", "test1", "test1", "MARKET",
				"", "LOGGING");
		Order order4 = new Order("O122", "B121", "SAPIENT", "T101", "SELL", "50", "100", "test1", "test1", "LIMIT",
				"50", "LOGGING");
		Order order5 = new Order("O122", "B121", "SAPIENT", "T101", "SELL", "50", "100", "test1", "test1", "LIMIT",
				"60", "LOGGING");
		Order order6 = new Order("O122", "B121", "APPLE", "T101", "BUY", "60", "200", "test1", "test1", "MARKET",
				"", "LOGGING");
		Order order7 = new Order("O122", "B121", "APPLE", "T101", "BUY", "60", "100", "test1", "test1", "MARKET",
				"", "LOGGING");
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
	<div id="tabs">
		<ul>
			<li><a href="#T_Block_Create">Blocks</a></li>
			<li><a href="#T_Workbench">Order Workbench</a></li>
			<li><a href="#T_Create Order">Create Order</a></li>
			<li><a href="#T_Block_Histroy_View">Block History</a></li>
		</ul>
		<div id="T_Block_Create" class="container">
			<%
				String BlockTitle = "";
				for (Block block : blocklist) {
			%>
			<div class="fixed" style="display: inline-block">
				<table id="<%=block.getBlockID()%>" class="block_table" width="100%"
					role="grid" style="width: 100%;">

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
											class="<%="orderblockrow" + " " + block.getBlockID() + "_row"%>">
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
			<div class="flex-item" style="display: inline-block">
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
</body>
</html>