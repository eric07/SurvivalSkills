<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<%@ page import="com.web.sur.*"%>
	<%@ page import="java.io.Serializable"%>
	<%@ page import="java.util.*"%>
	<%
		//Get wanted variables from request
		ArrayList<Portfolio> pl = (ArrayList<Portfolio>)request.getAttribute("Portfoliolist");
		String pName =(String) request.getAttribute("pname");
		ArrayList<String> portnames =  (ArrayList<String>)request.getAttribute("portnames");
	%>
<script>
$(document).ready(function() {
	$(".portfoliooptions").hide();
	$('#' + '<%=portnames.get(0)%>' + '_option').show();
});

	
</script>
</head>
<body>
	<div id="PM_position">
		<p>Position Tab</p>
		
		<!-- Drop down menu for portfolios -->
		<label for="portfolio">Select Portfolio</label> <select
			name="selectmenu1" class="port_select">

			<%
				for (String pname : portnames) {
			%>

			<option value="<%=pname%>"><%=pname%></option>
			<%
				}
			%>
		</select> </select>
		<div>
		
		<!-- Navigate along all porfolios -->
			<%
				for (Portfolio port: pl) {
			%>
			<table class="portfoliooptions" width="100%"

				id="<%=(port.getName()+ "_option")%>" role="grid"
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
				
					<!-- Navigate along all positions in a portfolio -->
					<%
						for (Positions p : port.poslist) {
					%>
					<tr role="row" class="odd">


						<%
							for (String attribute : p.getAttributes()) {
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
</body>

</html>