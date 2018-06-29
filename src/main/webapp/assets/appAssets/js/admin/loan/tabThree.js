$(document).ready(function() {
	$('form#bankStatementForm').on('submit', uploadTabThreeCSVData);
	
		$('#distributerList3').multiselect({
		  nonSelectedText: 'Select Distributer',
		  enableFiltering: true,
		  enableCaseInsensitiveFiltering: true,
		  includeSelectAllOption: true,
		  buttonWidth:'150px'
		 });
		
		$(function() {
		    var start = moment().subtract(29, 'days');
		    var end = moment();
		    function cb(start, end) {
		        $('#reportrange3 span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
		    }
		    $('#reportrange3').daterangepicker({
		        startDate: start,
		        endDate: end,
		        ranges: {
		           'Today': [moment(), moment()],
		           'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
		           'Last 7 Days': [moment().subtract(6, 'days'), moment()],
		           'Last 30 Days': [moment().subtract(29, 'days'), moment()],
		           'This Month': [moment().startOf('month'), moment().endOf('month')],
		           'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
		        }
		    }, cb);
		    cb(start, end);
		    //alert("start " + start + " end " + end)
		});
});
function getTabThreeRerereshData(){
	tabThreeData();
}
function tabThreeData(){
	
}
function getTabThreeCSVData(){
	
}
$("#uploadBankStatementBtn").click(function (event) {

    //stop submit the form, we will post it manually.
    event.preventDefault();

    // Get form
    var form = $('#bankStatementForm')[0];

	// Create an FormData object 
    var data = new FormData(form);

	// If you want to add an extra field for the FormData
    //data.append("CustomField", "This is some extra data, testing");

	// disabled the submit button
    $("#uploadBankStatementBtn").prop("disabled", true);

    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: $("#appLink").val() +"uploadBankStatement",
        data: data,
        processData: false,
        contentType: false,
        cache: false,
        timeout: 600000,
        success: function (data) {

            $("#result").text(data);
            console.log("SUCCESS : ", data);
            $("#btnSubmit").prop("disabled", false);

        },
        error: function (e) {

            $("#result").text(e.responseText);
            console.log("ERROR : ", e);
            $("#btnSubmit").prop("disabled", false);

        }
    });

});