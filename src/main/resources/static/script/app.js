$(document).ready(function() {
	console.log("welcome to your checklist")
	$("#ingredientSearch").autocomplete({
		source : "/ingredient/search"
	});
	
	$("#ingredientSearchForm").submit(function() {
		var actionUrl = $(this).attr("action");
		var body = $(this).serialize();
		console.log(body);
		$.ajax({
			  url: actionUrl,
			  method: "POST",
			  data: body,
			  success: function(data) {
				  console.log("success");
				  console.log(data);
				  var newRow = "<tr><td>" + data.name + "</td><td>" + "X" + "</td></tr>";
				  console.log(newRow);
			  	  $("tbody").append(newRow);
			  }
		}).done((data)=>{
			console.log(data);
		}).fail((err)=>{
			console.log(err);
		})
		
		return false;
	})
});