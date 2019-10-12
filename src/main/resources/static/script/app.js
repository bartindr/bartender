$(document).ready(function() {
	console.log("welcome to your checklist")
	$("#ingredientSearch").autocomplete({
		source : "/ingredient/search"
	});
	
	$("#ingredientSearchForm").submit(function(e) {
		e.preventDefault();
		var actionUrl = $(this).attr("action");
		var body = $(this).serialize();
		console.log(body);
		$.ajax({
			  url: actionUrl,
			  method: "POST",
			  data: body,
			  success: function(dLI) {
				  console.log("success");
//				  console.log(dLI);
//				  console.log(data);
				  var newRow = "<tr class='checklist_ingredient_" + dLI.ingredient.id + "'><td>" 
				  + dLI.ingredient.name.replace(/['"]+/g, '') + "</td><td>" 
				  + '<form class="delete-ingredient" action="api/'+ dLI.drinkList.id 
				  +'/delete/'+ dLI.ingredient.id +'" method="POST"><input type="hidden" name="_method" value="delete"/>' 
				  + '<input type="hidden" name="drinkListId" value="' + dLI.drinkList.id 
				  + '"/><input type="hidden" name="ingredientId" value="'
				  + dLI.ingredient.id +'"/><input type="submit" class="delete-list-item" value="X" /></form>'
				  + "</td></tr>";
//				  console.log(newRow);
			  	  $("tbody").append(newRow);
			  },
			  dataType: "json"
		}).done((data)=>{
			console.log(data);
		}).fail((err)=>{
			console.log(err);
		})
		e.preventDefault();
		return false;
	})
//	
//	$("body").on("click", ".delete-list-item", function(e){
//		e.preventDefault();
//
////	    alert("check");
//	});
	
	$(".delete-list-item").click(function(e){
		e.preventDefault();
		console.log($(this).attr("data-form-id"));
		
		var formId = $(this).attr("data-form-id");
		var form = $("#" + formId + "");
		var body = form.serialize();
		var actionUrl = form.attr("action");
		
		$.ajax({
			  url: actionUrl,
			  method: "DELETE",
			  data: body,
			  success: function(data) {
				  console.log("success");
			  },
			  dataType: "json"
		}).done((data)=>{
			console.log(data);
			$("#checklist_ingredient_"+ data +"").remove();
		}).fail((err)=>{
			console.log("Error with call")
			console.log(err);
		})
		
		
	})
	
//	$(".delete-ingredient").submit(function(e) {
//		console.log("IN AJAX DELETE")
//		var actionUrl = $(this).attr("action");
//		var body = $(this).serialize();
//		console.log(body);
//		e.preventDefault();
//		$.ajax({
//			  url: actionUrl,
//			  method: "DELETE",
//			  data: body,
//			  success: function(data) {
//				  console.log("success");
//			  },
//			  dataType: "json"
//		}).done((data)=>{
//			console.log(data);
//			$("#checklist_ingredient_"+ data +"").remove();
//		}).fail((err)=>{
//			console.log("Error with call")
//			console.log(err);
//		})
		
//		e.preventDefault();
//		return false;
//	})

/* AJAX CALL TO RETRIEVE DRINK LIST FILTER */

	
});