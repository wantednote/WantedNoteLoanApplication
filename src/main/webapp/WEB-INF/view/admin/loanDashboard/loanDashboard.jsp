<%@ include file="/common/taglibs.jsp"%>
<link href="<c:url value="/assets/formvalidation/formValidation.min09a2.css?v2.1.0" />" rel="stylesheet">
<link href="<c:url value="/assets/css/plugins/dataTables/dataTables.responsive.css" />" rel="stylesheet">
<link href="<c:url value="/assets/css/plugins/dataTables/dataTables.tableTools.min.css" />" rel="stylesheet">

<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-multiselect/0.9.13/css/bootstrap-multiselect.css" />

<script src="<c:url value="/assets/js/jquery-2.1.1.js" />"></script>

<script src="<c:url value="/assets/formvalidation/formValidation.min.js" />"></script>
<script src="<c:url value="/assets/formvalidation/framework/bootstrap.min.js" />"></script>

<script src="<c:url value="/assets/js/plugins/dataTables/jquery.dataTables.js" />"></script>
<script src="<c:url value="/assets/js/plugins/dataTables/dataTables.bootstrap.js" />"></script>
<script src="<c:url value="/assets/js/plugins/dataTables/dataTables.responsive.js" />"></script>
<script src="<c:url value="/assets/js/plugins/dataTables/dataTables.tableTools.min.js" />"></script>
   
<script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-3-typeahead/4.0.2/bootstrap3-typeahead.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-multiselect/0.9.13/js/bootstrap-multiselect.js"></script>
  
  
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
.btn-group>.btn:first-child {
    margin-left: 0;
    background: white;
    color: #263949;
    border-radius: 0px;
}
.dropdown-menu {
	max-height: 400px;
	overflow-y: auto;
	overflow-x: hidden;
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
            <div class="col-lg-10">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>Loan Details</h5>
                        </div>
                        <div class="ibox-content" style="display: block;">
                            <form role="form" class="form-inline">
                                <div class="form-group">
                                    <div id="reportrange" style="background: #fff; cursor: pointer; padding: 7px 10px;margin-top: -5px; border: 1px solid #ccc; width: 100%">
									    <i class="fa fa-calendar"></i>&nbsp;
									    <span></span> <i class="fa fa-caret-down"></i>
									</div>
                                </div>
                                <c:if test="${not empty distributers}">
	                                <div class="form-group">
	                                    <select id="distributerList" name="distributer[]" multiple class="form-control" >
	                                    	<!-- <option value="">Select Distributer</option> -->
		                                    <c:forEach var="distributer" items="${distributers}">
		                                    	<c:if test="${not empty distributer.distName}">
													<option value="${distributer.distId}"> ${distributer.distName}</option>
												</c:if>
											</c:forEach>
									    </select>
	                                </div>
                                </c:if>
                                <div class="form-group pull-right">
                                	<button style="border-radius: 0px;" type="button" class="btn btn-primary" onclick="getRefereshData();"><i class="fa fa-refresh"></i> Search</button>
                                	<!-- <button type="button" class="btn btn-primary btn-sm" ><i class="fa fa-refresh"></i> Search</button> -->
                                </div>
                                <div class="form-group pull-right">
                                	<button style="border-radius: 0px;" type="button" class="btn btn-white"><i class="fa fa-download"></i> Download CSV</button>
                                </div>
                            </form>
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
            <!-- <div class="col-lg-10 animated fadeInRight" id ="viewUserDiv">
	            <div class="mail-box-header">
	            	<div class="mail-tools tooltip-demo m-t-md">
		                <div id="reportrange" style="background: #fff; cursor: pointer; padding: 5px 10px; border: 1px solid #ccc; width: 30%">
						    <i class="fa fa-calendar"></i>&nbsp;
						    <span></span> <i class="fa fa-caret-down"></i>
						</div>
					</div>
					<select id="lstFruits" multiple="multiple">
				        <option value="1">Mango</option>
				        <option value="2">Apple</option>
				        <option value="3">Banana</option>
				        <option value="4">Guava</option>
				        <option value="5">Orange</option>
				    </select>
					<div class="mail-tools tooltip-demo m-t-md">
						<a href="#" onclick="getRefereshData();" class="btn btn-primary btn-sm pull-right" ><i class="fa fa-refresh"></i> Refresh</a>
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
            </div> -->
        </div>
</div>
<script type="text/javascript">
 function changeSelection(li){
	 //$("li").removeClass("folder-list");
	 $("li").removeClass("folder-list-selected");
	 $("#" + li).addClass("folder-list-selected");
  
</script>