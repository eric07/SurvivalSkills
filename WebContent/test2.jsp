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
<link rel="stylesheet" href="jquery-ui.css">
<script src="jquery.js"></script>
<script src="jquery-ui.js"></script>
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
				<br> <br>


				<div id="tabs">

					<ul>
						<li><a href="pmOrderView.jsp">Order View</a></li>
						<li><a href="pmPositions.jsp">Position</a></li>
						<li><a href="pmCreateOrder.jsp">Create Order</a></li>
					</ul>
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
			$(".portfolio_options").hide();
			$('#' + "Portfolio1" + '_option').show();
		});
	</script>
</body>
</html>