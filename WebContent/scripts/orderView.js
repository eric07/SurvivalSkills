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
		$(".portfoliooptions").hide();
		var portfolio = $(this).val();
		$('#' + portfolio + '_option').show();
	});

	$(document).ready(function() {
		$(".portfoliooptions").hide();
		$('#' + "Portfolio1" + '_option').show();
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
	
