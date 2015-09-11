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
	$(document).ready(function() {
			$('#createOrder select[id="combineType"]').change(function() {
						if (document.getElementById('combineType').value === 'BL' 
								|| document.getElementById('combineType').value === 'SL') {
								$('#parameterValue').show();
								var input = document.getElementById('parameterValue');
								input.value = "0";
						} else {
							$('#parameterValue').hide();
							var input = document.getElementById('parameterValue');
							input.value = "0";
							}
				});
		});
	
	//changes side
	$(document).ready(function() {
		$('#createOrder select[id="combineType"]').change(function() {
					if (document.getElementById('combineType').value === 'SM' 
							|| document.getElementById('combineType').value === 'SL') {
							document.getElementById('side').value = 'Sell';
					} else {
						
						document.getElementById('side').value = 'Buy';
						}
			});
	});
	
	//Change order type
		$(document).ready(function() {
		$('#createOrder select[id="combineType"]').change(function() {
					if (document.getElementById('combineType').value === 'BM' 
							|| document.getElementById('combineType').value === 'SM') {
							document.getElementById('orderType').value = 'Market';
					} else {
						
						document.getElementById('orderType').value = 'Limit';
						}
			});
	});
	
	$(document).ready(function () {
	    $('#symbol').change(function () {
			document.getElementById('securityType').value = 'Equity';
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

		String sql1 = "select user_id from webtardb3.users where username =?";
		;

		try {
			PreparedStatement ps = connection.prepareStatement(sql1);
			ps.setString(1, uID);
			rs3 = ps.executeQuery();

			if (rs3.next()) {
				uID = rs3.getString("user_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
%>
</head>
<body>
	<form id="createOrder" name="createOrder" method="post" action="traderCreateOrderServlet">
	<input type="hidden" value="<%= uID %>" name="tid" id="tid">
		<br> Portfolio: <select ID="portfolioID" name="portfolioID"
			required>
			<option value='' disabled selected style='display: none;'>Select
				a Portfolio</option>
			<%
				resultset = statement
							.executeQuery("select * from portfolios");
					while (resultset.next()) {
			%>
			<option value="<%=resultset.getString(1)%>"><%=resultset.getString(2)%></option>
			<%
				}
			%>
		</select> <br> 
		 Symbol: <select id="symbol" name="symbol" required>
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

		</select> 
		   Security Type: <select id="securityType" name="securityType" disabled required>
			<option value='' disabled selected style='display: none;'>---</option>
			<option value="Equity">Equity</option>
			<option value="Fixed Income">Fixed Income</option>
		</select>

		<input type="hidden" id="side" name="side" value="">
		</select> 
		<br> Order Type: <select id="combineType" name="combineType" required>
			<option value='' disabled selected style='display: none;'>Select
				Order Type</option>
				<option value="BM">Buy Market</option>
			<option value="BL">Buy Limit</option>
			<option value="SM">Sell Market</option>
			<option value="SL">Sell Limit</option>
		</select>
		<input type="hidden" id="orderType" name="orderType" value=""> 
		<input value="Null" placeholder="Enter price value" type="text" name="parameterValue" id="parameterValue"
			style="width: 100px; height: 29px; display: none;"> 
		<br>Enter Quantity:
		 <input type="number" min="1" name="quantity" id="quantity" required> 
		 <br> Enter any Comments: 
		 <input type="text" name="comments" id="comments" style="width: 273px; height: 72px;"> 
		 <br>
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