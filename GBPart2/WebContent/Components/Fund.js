$(document).ready(function()
{
if ($("#alertSuccess").text().trim() == "")
{
$("#alertSuccess").hide();
}
$("#alertError").hide();
});

// SAVE ============================================
$(document).on("click", "#btnSave", function(event) {
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();

	// Form validation-------------------
	var status = validateFundForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	// If valid------------------------

	var type = ($("#hidpIdSave").val() == "") ? "POST" : "PUT";

	$.ajax({
		url : "FundAPI",
		type : type,
		data : $("#formFund").serialize(),
		dataType : "text",
		complete : function(response, status) {
			onOrdSaveComplete(response.responseText, status);
		}
	});
});


function onFundSaveComplete(response, status) {
	
	
	if (status == "success") {
		var resultSet = JSON.parse(response);

    if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#divFundGrid").html(resultSet.data);

		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}

	} else if (status == "error") {
		$("#alertError").text("Error while saving.");
		$("#alertError").show();

	} 

	else {
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}
	
	$("#hidOrdIDSave").val("");
	$("#formOrd")[0].reset();
}

// UPDATE==========================================
$(document).on(	"click",".btnUpdate",function(event) {
	
	$("#hidpIdSave").val($(this).data("fId")); 
	 $("#fTitle").val($(this).closest("tr").find('td:eq(0)').text()); 
	 $("#fDesc").val($(this).closest("tr").find('td:eq(1)').text()); 
	 $("#fPrice").val($(this).closest("tr").find('td:eq(2)').text()); 
	 $("#fuName").val($(this).closest("tr").find('td:eq(3)').text());
	 $("#date").val($(this).closest("tr").find('td:eq(4)').text());
	 
		});

// REMOVE==========================================
$(document).on("click", ".btnRemove", function(event)
		{ 
		 $.ajax( 
		 { 
		 url : "FundAPI", 
		 type : "DELETE", 
		 data : "fId=" + $(this).data("fId"),
		 dataType : "text", 
		 complete : function(response, status) 
		 { 
		 onFundDeleteComplete(response.responseText, status); 
		 } 
		 }); 
		});


function onOrdFundComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#divOrdGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}