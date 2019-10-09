$(document).ready(function() {
	$("#ingredientSearch").autocomplete({
		source : "/ingredient/search"
	});
	
	$("#ingredientSearchForm").submit(function() {
		var actionUrl = $(this).attr("action");
		var body = $(this).serialize();
		
		$.ajax({
			  url: actionUrl,
			  method: "POST",
			  data: body,
			  success: function(data) {
				  var newRow = "<tr><td>" + data.name + "</td><td>" + "X" + "</td></tr>";
				  console.log(newRow);
			  	  $("tbody").append(newRow);
			  },
			  dataType: "json"
		});
		
		return false;
	})
});