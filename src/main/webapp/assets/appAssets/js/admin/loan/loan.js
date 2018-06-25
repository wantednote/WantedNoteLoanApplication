$(document).ready(function() {
	var currentRoleId = $("#currentRoleId").val();
	//getActors(currentRoleId);
	
	$("#pageHeaderNav").addClass('white-bg');
	
	$("#quickLinksDiv").hide();
	$("#addUserDiv").hide();
	
	$( "#getByEmailAddressBtn" ).click(function() {
		var currentRoleId = $("#currentRoleId").val();
		getActors(currentRoleId);
	});
	$( "#addUserBtn" ).click(function() {
	  	$("#viewUserDiv").hide();
	  	$("#addUserDiv").show();
	});
	$( "#clacelAddUserBtn" ).click(function() {
		$("#addUserDiv").hide();
	  	$("#viewUserDiv").show();
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
	$('#addActor').formValidation({
        message: 'This value is not valid',
        icon: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
        	name: {
        		validators: {
        			notEmpty: {
                        message: 'The name is required'
                    }
                }
            },
            emailAddress: {
        		validators: {
                    notEmpty: {
                        message: 'The email address is required and can\'t be empty'
                    },
                    emailAddress: {
                        message: 'The input is not a valid email address'
                    }
                }
            },
            password: {
                validators: {
                    notEmpty: {
                        message: 'The password is required and can\'t be empty'
                    }
                }
            },
            confirmPassword: {
                validators: {
                    notEmpty: {
                        message: 'The confirm password is required and can\'t be empty'
                    },
                    identical: {
                        field: 'password',
                        message: 'The password and its confirm are not the same'
                    }
                }
            },
            role: {
        		validators: {
        			notEmpty: {
                        message: 'The role is required'
                    }
                }
            }
        }
    });
	
});
function changeRole(currentRoleId, currentRoleValue, currentRole){
	//alert(currentRoleId + "    " + currentRoleValue + " " + currentRole);
	$("#currentRoleId").val(currentRoleId);
	$('.currentRoleSmallClass').html(currentRoleValue);
	$("#currentRoleCaps").val(currentRole);
	getActors(currentRoleId);
}
function getActors(currentRoleId){
	$('#actors').dataTable().fnDestroy();
	var emailAddress = $("#searchByEmailAddress").val();
	var roleName = $("#roleCaps").val();
	var rowCount = 0;
	var columns = ["name", "accountStatus", "email", "modifiedOn", "lastLogin", "id" ];
	var dt = $('#actors').DataTable({
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
		"lengthChange" : false,
		"iDisplayLength" : 10,
		"bFilter" : true,
		"aaSorting": [[4,"desc"]],
		"ajax" : {
			"url" : $("#appLink").val() + "actorList",
			"type" : 'POST',
			"data" : function(d) {
				d.start = d.start / d.length;
				d.sortDirection = d.order[0].dir;
				d.roleId = currentRoleId;
				d.emailAddress = emailAddress;
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
			"data" : "name",
			"width" : "20%",
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
			"data" : "accountStatus",
			"width" : "10%",
			"render" : function (data, type, full) {
				if (data == null || data == "") {
					return '<span>-<span>'
				}else{
					if(data == "Pending"){
						return '<span class="label label-warning pull-left">'+ data +'</span> '
					}else if(data == "Active"){
						return '<span class="label label-primary pull-left">'+ data +'</span> '
					}else if(data == "Blocked"){
						return '<span class="label label-success pull-left">'+ data +'</span> '
					}else if(data == "Deleted"){
						return '<span class="label label-danger pull-left">'+ data +'</span> '
					}
				}
			}
		},{
			"targets" : 2,
			"searchable" : false,
			'bSortable' : true,
			"data" : "email",
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
			"data" : "modifiedOn",
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
			"data" : "lastLogin",
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
			"data" : "id",
			"width" : "10%",
			"render" : function (data, type, full) {
				if (data == null || data == "") {
					return '<span>-<span>'
				}else{
					//alert($('#actors').dataTable().fnGetData().length);
					if(full.accountStatus == "Deleted"){
						return '<div class="input-group-btn">'
			                   + '<button data-toggle="dropdown" class="btn btn-white dropdown-toggle btn-sm" type="button" aria-expanded="true">Action <span class="caret"></span></button>'
				                + '<ul class="dropdown-menu pull-right">'
				                    //+ '<li class="divider"></li>'
				                    + '<li><a href="javascript:void(0);" onclick="viewProfileDetails(\'' + data + '\', \'' + full.name + '\', \'' + full.email + '\');">View Details</a></li>'
				                + '</ul>'
				            + '</div>'
					}else{
						return '<div class="input-group-btn">'
					            + '<button data-toggle="dropdown" class="btn btn-white dropdown-toggle btn-sm" type="button" aria-expanded="true">Action <span class="caret"></span></button>'
					                + '<ul class="dropdown-menu pull-right">'
					                    + '<li><a href="javascript:void(0);" onclick="updateAccountStatus(\'' + data + '\', \'Active\', \'' + full.email + '\');">Active</a></li>'
					                    + '<li><a href="javascript:void(0);" onclick="updateAccountStatus(\'' + data + '\', \'Pending\', \'' + full.email + '\');">Pending</a></li>'
					                    + '<li><a href="javascript:void(0);" onclick="updateAccountStatus(\'' + data + '\', \'Blocked\', \'' + full.email + '\');">Blocked</a></li>'
					                    + '<li><a href="javascript:void(0);" onclick="updateAccountStatus(\'' + data + '\', \'Deleted\', \'' + full.email + '\');">Deleted</a></li>'
					                    + '<li class="divider"></li>'
					                    + '<li><a href="javascript:void(0);" onclick="viewProfileDetails(\'' + data + '\', \'' + full.name + '\', \'' + full.email + '\');">View Details</a></li>'
					                + '</ul>'
					            + '</div>'
					}
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