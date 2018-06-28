$(document).ready(function() {
	$("#pageHeaderNav").addClass('white-bg');
	$("#quickLinksDiv").hide();
	
	for(var i=1; i<=4; i++){
		if(i==1){
			$("#tab" + i).show();
			$("#"+1).addClass("folder-list-selected");
		}else{
			$("#tab" + i).hide();
		}
	}
	
	$('#distributerList').multiselect({
	  nonSelectedText: 'Select Distributer',
	  enableFiltering: true,
	  enableCaseInsensitiveFiltering: true,
	  includeSelectAllOption: true,
	  buttonWidth:'300px'
	 });
	
	$(function() {
	    var start = moment().subtract(29, 'days');
	    var end = moment();
	    function cb(start, end) {
	        $('#reportrange span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
	    }
	    $('#reportrange').daterangepicker({
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
	
	$('#distributerList1').multiselect({
		  nonSelectedText: 'Select Distributer',
		  enableFiltering: true,
		  enableCaseInsensitiveFiltering: true,
		  includeSelectAllOption: true,
		  buttonWidth:'300px'
		 });
		
	$(function() {
	    var start = moment().subtract(29, 'days');
	    var end = moment();
	    function cb(start, end) {
	        $('#reportrange1 span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
	    }
	    $('#reportrange1').daterangepicker({
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
	
	/* setTimeout(function() {
        toastr.options = {
            closeButton: true,
            progressBar: true,
            showMethod: 'slideDown',
            timeOut: 4000
        };
        toastr.success('WantedNote', 'View Several Syatem Actors');
    }, 1300); */
});