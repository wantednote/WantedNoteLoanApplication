$(document).ready(function() {
	var currentRoleId = $("#currentRoleId").val();
	getLoanDetails();
	
	
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
	
	$("#pageHeaderNav").addClass('white-bg');
	$("#quickLinksDiv").hide();
	
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
function getRefereshData(){
	getLoanDetails();
}
function getLoanDetails(currentRoleId){
	var date = $('#reportrange span').html();
	var dates = date.split("-");
	var startDate = dates[0];
	var endDate = dates[1];
	//alert(startDate);
	//alert(endDate);
	    
	$('#loanDetails').dataTable().fnDestroy();
	var rowCount = 0;
	var columns = ["orderNo", "distributorName", "firstName", "tnDate", "amount" ];
	var dt = $('#loanDetails').DataTable({
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
		"iDisplayLength" : 25,
		"bFilter" : true,
		"aaSorting": [[3,"desc"]],
		"ajax" : {
			"url" : $("#appLink").val() + "loanList",
			"type" : 'POST',
			"data" : function(d) {
				d.start = d.start / d.length;
				d.sortDirection = d.order[0].dir;
				d.tnDateStart = startDate;
				d.tnDateEnd = endDate;
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
			"width" : "30%",
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
function viewProfileDetails(id, name, email){
	//alert(id + " " + name + " " + email)
	$('#container').waitMe({
		effect : 'win8',
		text : 'Please Wait',
		sizeW : '',
		sizeH : '',
		source : ''
	});
	location.href= $("#appLink").val() + $("#selectedBaseLink").val()+"/"+id;
}
function updateAccountStatus(id, status, email){
	$.confirm({
        title: 'Attention Please',
        content: 'Are you sure you want to ' + status + " "+ email + "'s  account",
        icon: 'fa fa-question-circle',
        animation: 'scale',
        closeAnimation: 'scale',
        opacity: 0.5,
        theme: 'supervan',
        buttons: {
            'confirm': {
                text: 'Proceed',
                btnClass: 'btn-blue',
                action: function () {
                	$('#viewUserDiv').waitMe({
                		effect : 'win8',
                		text : 'Please Wait',
                		sizeW : '',
                		sizeH : '',
                		source : ''
                	});
                	doAccountStatusOperation(id, status, email);
                }
            },
            cancel: function () {
                //$.alert('you clicked on <strong>cancel</strong>');
            },
        }
    });
}
function doAccountStatusOperation(id, status, email){
	var roleName = $("#currentRoleCaps").val();
	//alert(id + " " + status + " " + email + " " + roleName)
	var data = {
			id : id,
			emailAddress : email,
			accountStatus : status,
			roleName : roleName
	}
	data["query"] = $("#query").val();
	
	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : $("#appLink").val() + "updateAccountStatus",
		data : JSON.stringify(data),
		dataType : 'json',
		timeout : 100000,
		success : function(data) {
			//console.log("SUCCESS: ", data);
			$('#viewUserDiv').waitMe('hide');
			display(data);
		},
		error : function(e) {
			$('#viewUserDiv').waitMe('hide');
			//console.log("ERROR: ", e);
			//display(e);
			setTimeout(function() {
		        toastr.options = {
		            closeButton: true,
		            progressBar: true,
		            showMethod: 'slideDown',
		            timeOut: 4000
		        };
		        toastr.error(e, "Error");
		    }, 1300);
		},
		done : function(e) {
			console.log("DONE");
		}
	});
}
function display(data){
	if(data.messageType == "Success"){
		setTimeout(function() {
	        toastr.options = {
	            closeButton: true,
	            progressBar: true,
	            showMethod: 'slideDown',
	            timeOut: 4000
	        };
	        toastr.success(data.successOrErrorMessage, data.messageType);
	    }, 1300);	
	}else{
		setTimeout(function() {
	        toastr.options = {
	            closeButton: true,
	            progressBar: true,
	            showMethod: 'slideDown',
	            timeOut: 4000
	        };
	        toastr.error(data.successOrErrorMessage, data.messageType);
	    }, 1300);
	}
}