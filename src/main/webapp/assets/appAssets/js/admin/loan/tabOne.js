$(document).ready(function() {
		$('#distributerList1').multiselect({
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
});
function getTabOneRerereshData(){
	tabOneData();
}
function tabOneData(){
	var loanAppliedFlag = "f";
    var loanPendingFlag = "f";
    
	var loanStatus = $('input[name=loanStatus]:checked').val();
	if(loanStatus == "t"){
		loanAppliedFlag = "t";
	    loanPendingFlag = "f";
	}else if(loanStatus == "f"){
		loanAppliedFlag = "f";
	    loanPendingFlag = "t";
	}else {
		loanAppliedFlag = "f";
	    loanPendingFlag = "f";
	}
	
	var date = $('#reportrange1 span').html();
	var dates = date.split("-");
	var startDate = dates[0];
	var endDate = dates[1];
	
	var selectedDistributers = "";
	var count = 0;
	
    $('select#distributerList1').children('option:selected').each( function() {
         var $this = $(this);
         //selectedDistributers.push("'" + $this.val() + "'");
         //selectedDistributers.push($this.val());
         if(count > 0){
        	 selectedDistributers = selectedDistributers + ",";
         }
         selectedDistributers = selectedDistributers + "'" + $this.val() + "'";
         count = count + 1;
    });
    if(selectedDistributers !=""){
    	selectedDistributers = "(" + selectedDistributers + ")";
    }
    
	$('#loanDetails1').dataTable().fnDestroy();
	var rowCount = 0;
	var columns = ["orderNo", "distributorName", "firstName", "tnDate", "amount" ];
	var dt = $('#loanDetails1').DataTable({
        "responsive": true,
        "processing" : true,
		"serverSide" : true,
		"sort" : "position",
		"searching":false,
		"sPaginationType" : "full_numbers",
		"lengthChange" : true,
		"lengthChange" : false,
		"iDisplayLength" : 25,
		"bFilter" : true,
		"aaSorting": [[ 3, 'desc' ]],
		"ajax" : {
			"url" : $("#appLink").val() + "loanList",
			"type" : 'POST',
			"data" : function(d) {
				d.start = d.start / d.length;
				d.sortDirection = d.order[0].dir;
				d.tnDateStart = startDate;
				d.tnDateEnd = endDate;
				d.loanApplied = loanAppliedFlag;
				d.loanPending = loanPendingFlag;
				d.distributer = selectedDistributers;
				d.fieldForSorting = columns[d.order[0].column];
			}
		},
		"columnDefs" : [
		{
			"targets" :0,
			"searchable" : false,
			'bSortable' : true,
			"data" : "orderNo",
			"width" : "10%",
			"render" : function (data, type, full) {
				if (data == null || data == "") {
					return '<span>-<span>'
				}else{
					return data;
				}
			}
		},{
			"targets" :1,
			"searchable" : false,
			'bSortable' : true,
			"data" : "distributorName",
			"width" : "30%",
			"render" : function (data, type, full) {
				if (data == null || data == "") {
					return '<span>-<span>'
				}else{
					return data;
				}
			}
		},{
			"targets" : 2,
			"searchable" : false,
			'bSortable' : true,
			"data" : "firstName",
			"width" : "25%",
			"render" : function (data, type, full) {
				if (data == null || data == "") {
					return '<span>-<span>'
				}else{
					return data;
				}
			}
		},{
			"targets" : 3,
			"searchable" : false,
			'bSortable' : true,
			"data" : "tnDate",
			"width" : "20%",
			"render" : function (data, type, full) {
				if (data == null || data == "") {
					return '<span>-<span>'
				}else{
					return data;
				}
			}
		},{
			"targets" : 4,
			"searchable" : false,
			'bSortable' : false,
			"data" : "amount",
			"width" : "15%",
			"render" : function (data, type, full) {
				if (data == null || data == "") {
					return '<span>-<span>'
				}else{
					return '<span>Rs. <span>'+ data +'<span>.00<span>';
				}
			}
		}]
	});
	
}
function getTabOneCSVData(){
	var date = $('#reportrange1 span').html();
	var dates = date.split("-");
	var startDate = dates[0];
	var endDate = dates[1];
	
	var selectedDistributers = "";
	var count = 0;
    $('select#distributerList1').children('option:selected').each( function() {
         var $this = $(this);
         if(count > 0){
        	 selectedDistributers = selectedDistributers + ",";
         }
         selectedDistributers = selectedDistributers + "'" + $this.val() + "'";
         count = count + 1;
    });
    if(selectedDistributers !=""){
    	selectedDistributers = "(" + selectedDistributers + ")";
    }
	location.href=$("#appLink").val() + "downloadloancsv?startDate="+startDate+"&endDate="+endDate+"&distributers="+selectedDistributers;
}