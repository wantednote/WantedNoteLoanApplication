$(document).ready(function() {
	
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
		
		var fileName = null;
		$('input[type="file"]').change(function(e){
            fileName = e.target.files[0].name;
        });
		
		$("#uploadBankStatementBtn").click(function (event) {
		    //stop submit the form, we will post it manually.
		    event.preventDefault();

		    // Get form
		    var form = $('#bankStatementForm')[0];

			// Create an FormData object 
		    var data = new FormData(form);

			// If you want to add an extra field for the FormData
		    //data.append("CustomField", "This is some extra data, testing");
		    var extensions = ["csv"];
		    var ext = null;
		    if(fileName !=null){
		    	ext = fileName.split(".")[1];
		    }
            if(extensions.indexOf(ext) >= 0){
		    	$('#container').waitMe({
	        		effect : 'win8',
	        		text : 'Please Wait',
	        		sizeW : '',
	        		sizeH : '',
	        		source : ''
	        	});
		    	
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
			        	/*$("#result").text(data);
			            console.log("SUCCESS : ", data);*/
			        	if(data.messageType == "Success"){
			        		toastr.options = {
		        	            closeButton: true,
		        	            progressBar: true,
		        	            showMethod: 'slideDown',
		        	            timeOut: 4000
		        	        };
		        	        toastr.success(data.successOrErrorMessage, 'File Upload');
			        	}else{
			        		toastr.options = {
			        	            closeButton: true,
			        	            progressBar: true,
			        	            showMethod: 'slideDown',
			        	            timeOut: 4000
			        	        };
			        		toastr.error(data.successOrErrorMessage, 'File Upload');
			        	}
			            $("#uploadBankStatementBtn").prop("disabled", false);
			            $("#upload_tab_three_model").hide();
			            $('#container').waitMe('hide');
			        },
			        error: function (e) {
			            /*$("#result").text(e.responseText);
			            console.log("ERROR : ", e);*/
			            $("#uploadBankStatementBtn").prop("disabled", false);
			            toastr.options = {
	        	            closeButton: true,
	        	            progressBar: true,
	        	            showMethod: 'slideDown',
	        	            timeOut: 4000
	        	        };
			            toastr.error(data.successOrErrorMessage, 'File Upload');
			            $("#upload_tab_three_model").hide();
			            $('#container').waitMe('hide');
			        }
			    });
		    }else{
		    	toastr.options = {
    	            closeButton: true,
    	            progressBar: true,
    	            showMethod: 'slideDown',
    	            timeOut: 4000
    	        };
	            toastr.warning("Please select a file to upload", '.csv format');
		    }
			
		});
});
function getTabThreeRerereshData(){
	tabThreeData();
}
function tabThreeData(){
	var date1 = $('#reportrange3 span').html();
	var dates1 = date1.split("-");
	var startDate1 = dates1[0];
	var endDate1 = dates1[1];
	
	var selectedDistributers1 = "";
	var count1 = 0;
    $('select#distributerList3').children('option:selected').each( function() {
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
    
    $('#loanDetails3').dataTable().fnDestroy();
	var rowCount = 0;
	var columns = ["txnId", "onlinePaymentId", "retailerName", "amount", "tnDate" ,"verify"];
	var dt = $('#loanDetails3').DataTable({
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
			"url" : $("#appLink").val() + "receivedPaymentList",
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
function getTabThreeCSVData(){
	var date = $('#reportrange3 span').html();
	var dates = date.split("-");
	var startDate = dates[0];
	var endDate = dates[1];
	
	var selectedDistributers = "";
	var count = 0;
    $('select#distributerList3').children('option:selected').each( function() {
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
	location.href=$("#appLink").val() + "downloadrecievedpaymentlist?startDate="+startDate+"&endDate="+endDate+"&distributers="+selectedDistributers;
}