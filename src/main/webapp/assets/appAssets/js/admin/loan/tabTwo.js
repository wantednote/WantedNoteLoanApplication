$(document).ready(function() {
	tab2();
});
function getRefereshData1(){
	tab2();
}
function tab2(){
	var date1 = $('#reportrange1 span').html();
	var dates1 = date1.split("-");
	var startDate1 = dates1[0];
	var endDate1 = dates1[1];
	
	var selectedDistributers1 = "";
	var count1 = 0;
    $('select#distributerList1').children('option:selected').each( function() {
         var $this = $(this);
         //selectedDistributers.push("'" + $this.val() + "'");
         //selectedDistributers.push($this.val());
         if(count1 > 0){
        	 selectedDistributers1 = selectedDistributers1 + ",";
         }
         selectedDistributers1 = selectedDistributers1 + "'" + $this.val() + "'";
         count1 = count1 + 1;
    });
    if(selectedDistributers1 !=""){
    	selectedDistributers1 = "(" + selectedDistributers1 + ")";
    }
    
    $('#loanDetails1').dataTable().fnDestroy();
	var rowCount = 0;
	var columns = ["txnId", "onlinePaymentId", "retailerName", "tnDate", "amount", "repayTxnId", "settleAmt", "verify" ];
	var dt = $('#loanDetails1').DataTable({
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
			"url" : $("#appLink").val() + "dispersedList",
			"type" : 'POST',
			"data" : function(d) {
				d.start = d.start / d.length;
				d.sortDirection = d.order[0].dir;
				d.tnStartDate = startDate1;
				d.tnEndDate = endDate1;
				d.distributer = selectedDistributers1;
				d.fieldForSorting = columns[d.order[0].column]
			}
		},
		"columnDefs" : [
		/*{
			"class":          "details-control",
		    "orderable":      false,
		    "targets" : 0,
		    "data":           "id",
		    "width" : "10%",
		    "defaultContent": "More",
		    "render" : function (data, type, full) {
		    	return '<span class="badge badge-primary">More</span>';
			}
		},*/{
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
			"width" : "10%",
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
			"width" : "15%",
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
			"width" : "10%",
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
			"width" : "15%",
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
			"data" : "repayTxnId",
			"width" : "10%",
			"render" : function (data, type, full) {
				if (data == null || data == "") {
					return '<span>-<span>'
				}else{
					return data;
				}
			}
		}
		{
			"targets" : 6,
			"searchable" : false,
			'bSortable' : false,
			"data" : "settleAmt",
			"width" : "15%",
			"render" : function (data, type, full) {
				if (data == null || data == "") {
					return '<span>-<span>'
				}else{
					return data;
				}
			}
		}
		{
			"targets" : 7,
			"searchable" : false,
			'bSortable' : false,
			"data" : "verify",
			"width" : "15%",
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