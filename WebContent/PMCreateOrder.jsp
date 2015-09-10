<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js">
	
</script>
<link rel="stylesheet" href="jquery-ui.css">
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$('#createOrder select[id="orderType"]')
								.change(
										function() {
											if ($(
													'#createOrder select[id="orderType"] option:selected')
													.val() == 'Limit') {
												$('#parameterValue').show();
												var input = document
														.getElementById('parameterValue');
												input.value = "0";
											} else {
												$('#parameterValue').hide();
												var input = document
														.getElementById('parameterValue');
												input.value = "0";

											}
										});
					});
</script>
<%@ page import="java.sql.*"%>
<%
	HttpSession hs=request.getSession();
	String uID = hs.getAttribute("username").toString();

	ResultSet resultset = null;
	ResultSet resultset1 = null;
	ResultSet resultset2 = null;
	ResultSet rs3 = null;

	try {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection connection = DriverManager
				.getConnection("jdbc:mysql://10.209.70.23:3306/webtardb3?user=root&password=root");

		Statement statement = connection.createStatement();
		Statement statement1 = connection.createStatement();
		Statement statement2 = connection.createStatement();

		String sql1 = "select portfolios.pm_id from portfolios, users where portfolios.pm_id = users.user_id and username =?";
		;

		try {
			PreparedStatement ps = connection.prepareStatement(sql1);
			ps.setString(1, uID);
			rs3 = ps.executeQuery();

			if (rs3.next()) {
				uID = rs3.getString("pm_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
%>
</head>
<body>
	<form id="createOrder" name="createOrder" method="post"
		action="pmCreateOrderServlet">
		<br> Portfolio: <select ID="portfolioID" name="portfolioID"
			required>
			<option value='' disabled selected style='display: none;'>Select
				a Portfolio</option>
			<%
				resultset = statement
							.executeQuery("select * from portfolios where pm_id = '"
									+ uID + "'");
					while (resultset.next()) {
			%>
			<option value="<%=resultset.getString(1)%>"><%=resultset.getString(2)%></option>
			<%
				}
			%>
		</select> <br> Trader ID: <select id="traderID" name="traderID" required>
			<option value='' disabled selected style='display: none;'>Select
				Trader ID</option>
			<%
				resultset1 = statement1
							.executeQuery("select f_name,l_name,user_id from users where acc_type = 'trader'");
					while (resultset1.next()) {

						String traderName;
						traderName = resultset1.getString(1) + " "
								+ resultset1.getString(2);
			%>
			<option value="<%=resultset1.getString(3)%>">
				<%=traderName%>
			</option>
			<%
				}
			%>
		</select> <br> Security Type: <select id="securityType"
			name="securityType" required>
			<option value='' disabled selected style='display: none;'>Select
				Security Type</option>
			<option value="Equity">Equity</option>
			<option value="Fixed Income">Fixed Income</option>
		</select> <br> Symbol: <select id="symbol" name="symbol" required>
			<option value='' disabled selected style='display: none;'>Select
				Symbol</option>
			<%
				resultset2 = statement2
							.executeQuery("select * from securities");
					while (resultset2.next()) {

						String securityDetails;
						securityDetails = resultset2.getString(1) + " - "
								+ resultset2.getString(2);
			%>
			<option value="<%=resultset2.getString(1)%>"><%=securityDetails%></option>
			<%
				}
			%>

		</select> <br> Side: <select id="side" name="side" required>
			<option value='' disabled selected style='display: none;'>Select
				Side</option>
			<option value="Buy">Buy</option>
			<option value="Sell">Sell</option>
		</select> <br> Order Type: <select id="orderType" name="orderType"
			required>
			<option value='' disabled selected style='display: none;'>Select
				Order Type</option>
			<option value="Market">Market</option>
			<option value="Limit">Limit</option>
		</select> <input value="Null" placeholder="Enter price value" type="text"
			name="parameterValue" id="parameterValue"
			style="width: 100px; height: 29px; display: none;"> <br>
		Enter Quantity: <input type="number" min="1" name="quantity"
			id="quantity" required> <br> Enter any Comments: <input
			type="text" name="comments" id="comments"
			style="width: 273px; height: 72px;"> <br>
		<button type="submit" value="Submit" id="send">Submit Order</button>
	</form>
	<%
		} catch (Exception e) {
			out.println("wrong entry" + e);
		}
		resultset.close();
	%>

</body>
</html>