$(document).ready(function() {
		$('#retailerList1').multiselect({
			  nonSelectedText: 'Select Retailer',
			  enableFiltering: true,
			  enableCaseInsensitiveFiltering: true,
			  includeSelectAllOption: true,
			  buttonWidth:'150px'
			 });
		
		$(function() {
		    var start = moment().subtract(29, 'days');
		    var end = moment();
		    function cb(start, end) {
		        $('#reportrange5 span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
		    }
		    $('#reportrange5').daterangepicker({
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
function getTabFiveRerereshData(){
	tabFiveData();
}
function tabFiveData(){
	
	var date1 = $('#reportrange5 span').html();
	var dates1 = date1.split("-");
	var startDate1 = dates1[0];
	var endDate1 = dates1[1];
	
	var selectedRetailers1 = "";
	var count1 = 0;
    
	 $('select#retailerList1').children('option:selected').each( function() {
         var $this = $(this);
         //selectedDistributers.push("'" + $this.val() + "'");
         //selectedDistributers.push($this.val());
         if(count1 > 0){
        	 selectedRetailers1 = selectedRetailers1 + ",";
         }
         selectedRetailers1 = selectedRetailers1 + "'" + $this.val() + "'";
         count1 = count1 + 1;
    });
    if(selectedRetailers1 !=""){
    	selectedRetailers1 = "(" + selectedRetailers1 + ")";
    }
	
    $('#loanDetails5').dataTable().fnDestroy();
	var rowCount = 0;
	var columns = ["txnId", "onlinePaymentId", "retailerName", "amount", "tnDate" ,"verify"];
	var dt = $('#loanDetails5').DataTable({
        responsive: true,
        //"dom": 'T<"clear">lfrtip',
        /* "tableTools": {
            "sSwfPath": "js/plugins/dataTables/swf/copy_csv_xls_pdf.swf"
        } */
        "processing" : true,
		"serverSide" : true,
		"sort" : "position",
		"searching":false,
		"sPaginationType" : "full_numbers",
		"lengthChange" : true,
		"lengthChange" : false,
		"iDisplayLength" : 25,
		"bFilter" : true,
		"aaSorting": [[4,"desc"]],
		"ajax" : { 
			"url" : $("#appLink").val() + "retailerLoansList",
			"type" : 'POST',
			"data" : function(d) {
				d.start = d.start / d.length;
				d.sortDirection = d.order[0].dir;
				d.repayStartDate = startDate1;
				d.repayEndDate = endDate1;
			//	d.settleState = isSettle;
				d.retailer = selectedRetailers1;
				d.fieldForSorting = columns[d.order[0].column]
			}
		},
		"columnDefs" : [{
			"targets" :0,
			"searchable" : false,
			'bSortable' : true,
			"data" : "txnId",
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
			"data" : "onlinePaymentId",
			"width" : "20%",
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
			"data" : "retailerName",
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
			"data" : "amount",
			"width" : "15%",
			"render" : function (data, type, full) {
				if (data == null || data == "") {
					return '<span>-<span>'
				}else{
					return '<span>Rs. <span>'+ data +'<span>.00<span>';
				}
			}
		},{
			"targets" : 4,
			"searchable" : false,
			'bSortable' : false,
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
			"targets" : 5,
			"searchable" : false,
			'bSortable' : false,
			"data" : "verify",
			"width" : "10%",
			"render" : function (data, type, full) {
				if (data == null || data == "") {
					return '<span>-<span>'
				}else{
					return data;
				}
			}
		}]
	});
}
function getTabFiveCSVData(){
	var date = $('#reportrange5 span').html();
	var dates = date.split("-");
	var startDate = dates[0];
	var endDate = dates[1];
	
	var selectedRetailers = "";
	var count = 0;
    $('select#retailerList1').children('option:selected').each( function() {
         var $this = $(this);
         if(count > 0){
        	 selectedRetailers = selectedRetailers + ",";
         }
         selectedRetailers = selectedRetailers + "'" + $this.val() + "'";
         count = count + 1;
    });
    if(selectedRetailers !=""){
    	selectedRetailers = "(" + selectedRetailers + ")";
    }
	location.href=$("#appLink").val() + "downloadloaninvoicecsv?startDate="+startDate+"&endDate="+endDate+"&distributers="+selectedRetailers;
}
