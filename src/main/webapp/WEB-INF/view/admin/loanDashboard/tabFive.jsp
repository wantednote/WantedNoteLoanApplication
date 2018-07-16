<%@ include file="/common/taglibs.jsp"%>
<script src="<c:url value="/assets/appAssets/js/admin/loan/tabFive.js" />"></script>

<style>
/* The container */
.container {
	width:114px;
    display: block;
    position: relative;
    padding-left: 35px;
    /* margin-bottom: 12px; */
    margin-top: 4px;
    cursor: pointer;
    /* font-size: 14px; */
    -webkit-user-select: none;
    -moz-user-select: none;
    -ms-user-select: none;
    user-select: none;
}

/* Hide the browser's default checkbox */
.container input {
    position: absolute;
    opacity: 0;
    cursor: pointer;
}

/* Create a custom checkbox */
.checkmark {
    position: absolute;
    top: 0;
    left: 0;
    height: 25px;
    width: 25px;
    background-color: #eee;
}

/* On mouse-over, add a grey background color */
.container:hover input ~ .checkmark {
    background-color: #ccc;
}

/* When the checkbox is checked, add a blue background */
.container input:checked ~ .checkmark {
    background-color: #1ab394;
}

/* Create the checkmark/indicator (hidden when not checked) */
.checkmark:after {
    content: "";
    position: absolute;
    display: none;
}

/* Show the checkmark when checked */
.container input:checked ~ .checkmark:after {
    display: block;
}

/* Style the checkmark/indicator */
.container .checkmark:after {
    left: 9px;
    top: 5px;
    width: 5px;
    height: 10px;
    border: solid white;
    border-width: 0 3px 3px 0;
    -webkit-transform: rotate(45deg);
    -ms-transform: rotate(45deg);
    transform: rotate(45deg);
}
/* style the upload button space */
.btn-space {
    margin-right: 5px;
}
</style>

<div class="ibox float-e-margins">
                       <div class="ibox-title">
                           <h5>Loans paid by retailers</h5>
                       </div>
                       <div class="ibox-content" style="display: block;background-color: #e7eaec; padding: 15px 20px 4px 20px;">
                           <form role="form" class="form-inline">
                               <div class="form-group">
                                   <div id="reportrange5" style="background: #fff; cursor: pointer; padding: 7px 10px;margin-top: -5px; border: 1px solid #ccc; width: 100%">
									   <i class="fa fa-calendar"></i>&nbsp;
									   <span></span> <i class="fa fa-caret-down"></i>
								   </div>
                               </div>&nbsp;
                               <c:if test="${not empty retailers}">
	                               <div class="form-group">
	                                    <select id="retailerList1" name="retailer[]" multiple class="form-control" >
	                                    	<!-- <option value="">Select Distributer</option> -->
		                                    <c:forEach var="retailer" items="${retailers}">
		                                    	<c:if test="${not empty retailer.retName}">
													<option value="${retailer.retId}"> ${retailer.retName}</option>
												</c:if>
											</c:forEach>
									    </select>
	                               </div>&nbsp;&nbsp;
                               </c:if>
                               <div class="form-group pull-right">
                               	<button style="border-radius: 0px;" type="button" class="btn btn-primary" onclick="getTabFiveRerereshData();"><i class="fa fa-refresh"></i> Search</button>
                               	<!-- <button type="button" class="btn btn-primary btn-sm" ><i class="fa fa-refresh"></i> Search</button> -->
                               </div>
                               <div class="form-group pull-right">
                               	<button style="border-radius: 0px;" type="button" class="btn btn-white" onclick="getTabFiveCSVData();"><i class="fa fa-download"></i> Download CSV</button>
                               </div>
                           </form>
                       </div>
                        <div class="mail-box">
			                <table class="table table-hover table-mail display" id="loanDetails5">
				                <thead style="background: #f3f3f4;">
					                <tr class="unread">
					                	<th>TXN Id</th>
					                	<th>Payment Id</th>
					                	<th>Name</th>
					                	<th>Amount</th>
					                	<th>Date</th>
					                	<th>Is Verify</th>
					                </tr>
				                </thead>
		                   </table> 
	                   </div>
                   </div>