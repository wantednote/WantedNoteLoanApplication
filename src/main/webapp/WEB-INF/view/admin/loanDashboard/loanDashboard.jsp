<%@ include file="/common/taglibs.jsp"%>
<link href="<c:url value="/assets/formvalidation/formValidation.min09a2.css?v2.1.0" />" rel="stylesheet">
<link href="<c:url value="/assets/css/plugins/dataTables/dataTables.responsive.css" />" rel="stylesheet">
<link href="<c:url value="/assets/css/plugins/dataTables/dataTables.tableTools.min.css" />" rel="stylesheet">

<script src="<c:url value="/assets/js/jquery-2.1.1.js" />"></script>

<script src="<c:url value="/assets/formvalidation/formValidation.min.js" />"></script>
<script src="<c:url value="/assets/formvalidation/framework/bootstrap.min.js" />"></script>

<script src="<c:url value="/assets/js/plugins/dataTables/jquery.dataTables.js" />"></script>
<script src="<c:url value="/assets/js/plugins/dataTables/dataTables.bootstrap.js" />"></script>
<script src="<c:url value="/assets/js/plugins/dataTables/dataTables.responsive.js" />"></script>
<script src="<c:url value="/assets/js/plugins/dataTables/dataTables.tableTools.min.js" />"></script>

<script src="<c:url value="/assets/appAssets/js/admin/loan/loan.js" />"></script>

<title>Wantednote | Loan</title>
<style>
.dataTables_info{
	padding-left: 12px;
}
.pagination {
    display: inline-block;
    padding-left: 30%;
    margin: 0px 0;
    border-radius: 4px;
}
.folder-list-selected {
    /* border-bottom: 1px solid #f8ac59;
    border-top: 1px solid #f8ac59; */
    background: #e7eaec;
}
</style>
<div class="wrapper wrapper-content" id="container">		    
        <div class="row">
            <div class="col-lg-2">
            	<!-- <div class="mail-box-header"> -->
                <div class="ibox float-e-margins">
                    <div class="ibox-content mailbox-content">
                        <div class="file-manager">
                            <%-- <button class="btn btn-block btn-primary compose-mail" id="addUserBtn">Add <span class="currentRoleSmallClass">${currentRoleSmall}</span></button>  --%>                           
                            
                            <div class="space-25"></div>
                            <h5>Action</h5>
                            <ul class="folder-list m-b-md" style="padding: 0">
                            	<li><a href="#"></a></li>
			                    <li id="1" onclick="changeSelection(1);"><a href="#">&nbsp;&nbsp;&nbsp;&nbsp;Upload </a></li>
			                    <li id="2" onclick="changeSelection(2);"><a href="#">&nbsp;&nbsp;&nbsp;&nbsp;Download </a></li>
                            </ul>
                            <div class="clearfix"></div>
                        </div>
                    </div>
                </div>
                <!-- </div> -->
            </div>
            <!-- <form action="csv/upload" method="post" enctype="multipart/form-data">
	        	<input type="file" name="csvFile">
	        	<button type="submit">Submit</button>
	        </form> -->
            <div class="col-lg-10 animated fadeInRight" id ="viewUserDiv">
	            <div class="mail-box-header">
	
	                <form method="get" action="index.html" class="pull-right mail-search">
	                    <div class="input-group">
	                        <input type="text" class="form-control input-sm" name="search" id="searchByEmailAddress"placeholder="Search by email address">
	                        <div class="input-group-btn">
	                            <button type="button" id="getByEmailAddressBtn"class="btn btn-sm btn-primary">
	                                Search
	                            </button>
	                        </div>
	                    </div>
	                </form>
	                <h2>
	                    <span class="currentRoleSmallClass">${currentRoleSmall} </span><%-- (${userCount}) --%>
	                    <input type="hidden" value="${currentRoleId}" id="currentRoleId">
	                </h2>
	                <div class="mail-tools tooltip-demo m-t-md">
	                    <!-- <div class="btn-group pull-right">
	                        <button class="btn btn-white btn-sm"><i class="fa fa-arrow-left"></i></button>
	                        <button class="btn btn-white btn-sm"><i class="fa fa-arrow-right"></i></button>
	                    </div> -->
	                    
	                    <%-- <a href="${userDetailsSessionForm.selectedBaseLink}" class="btn btn-white btn-sm"><i class="fa fa-refresh"></i> Refresh</a>
	                    <button class="btn btn-primary btn-sm pull-right"><i class="fa fa-reply"></i> Add ${currentRole}</button> --%>
	                    <a href="${userDetailsSessionForm.selectedBaseLink}" class="btn btn-primary btn-sm pull-right"><i class="fa fa-refresh"></i> Refresh</a>
	                    <br/>
	                    <!-- <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="top" title="Mark as read"><i class="fa fa-eye"></i> </button>
	                    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="top" title="Mark as important"><i class="fa fa-exclamation"></i> </button>
	                    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="top" title="Move to trash"><i class="fa fa-trash-o"></i> </button> -->
	
	                </div>
	            </div>
                <div class="mail-box">
	                <table class="table table-hover table-mail" id="loanDetails">
		                <thead style="background: #f3f3f4;">
			                <tr class="unread">
			                	<th>Order No</th>
			                	<th>Distributer Name</th>
			                	<th>First Name</th>
			                	<th>Date</th>
			                	<th>Amount</th>
			                </tr>
		                </thead>
                	</table>
                </div>
            </div>
        </div>
</div>
<script type="text/javascript">
 function changeSelection(li){
	 //$("li").removeClass("folder-list");
	 $("li").removeClass("folder-list-selected");
	 $("#" + li).addClass("folder-list-selected");
 }
</script>