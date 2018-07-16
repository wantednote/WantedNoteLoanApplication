<%@ include file="/common/taglibs.jsp"%>
<script src="<c:url value="/assets/appAssets/js/admin/loan/tabOne.js" />"></script>
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
                           <h5>Loan Applied</h5>
                       </div>
                       <div class="ibox-content" style="display: block;background-color: #e7eaec; padding: 15px 20px 4px 20px;">
                           <form role="form" class="form-inline">
                               <%-- <jsp:include page="/WEB-INF/view/admin/loanDashboard/common/searchMenu.jsp" flush="true" /> --%>
                               <div class="input-group">
                               <div class="form-group">
                                   <div id="reportrange1" style="background: #fff; cursor: pointer; padding: 7px 10px;margin-top: -5px; border: 1px solid #ccc; width: 100%">
									   <i class="fa fa-calendar"></i>&nbsp;
									   <span></span> <i class="fa fa-caret-down"></i>
								   </div>
                               </div>
                               <c:if test="${not empty distributers}">
	                               <div class="form-group">
	                                    <select id="distributerList1" name="distributer[]" multiple class="form-control" >
	                                    	<!-- <option value="">Select Distributer</option> -->
		                                    <c:forEach var="distributer" items="${distributers}">
		                                    	<c:if test="${not empty distributer.distName}">
													<option value="${distributer.distId}"> ${distributer.distName}</option>
												</c:if>
											</c:forEach>
									    </select>
	                               </div>
                               </c:if>&nbsp;
                               <div class="input-group-btn">
                                       <button data-toggle="dropdown" style="border-color: #c2c2c2;border-radius: 0px;" class="btn btn-white dropdown-toggle" type="button" aria-expanded="false" >Loan Action<span class="caret"></span></button>
                                       <ul class="dropdown-menu pull-right">
                                           <li>
	                                           <a href="#">
	                                           		<label class="container">Applied
													  <input type="radio" value="t" checked="checked" name="loanStatus">
													  <span class="checkmark"></span>
													</label>
	                                           </a>
                                           </li>
                                           <li>
                                           		<a href="#">
	                                           		<label class="container">Pending
													  <input type="radio" value="f" name="loanStatus">
													  <span class="checkmark"></span>
													</label>
	                                           </a>
                                           </li>
                                           <li class="divider"></li>
                                           <li>
	                                           <a href="#">
	                                           		<label class="container">All
													  <input type="radio" value="" name="loanStatus">
													  <span class="checkmark"></span>
													</label>
	                                           </a>
                                           </li>
                                       </ul>
                                 </div>&nbsp;&nbsp;
                               </div>
                               <div class="form-group pull-right">
                               	<button style="border-radius: 0px;" type="button" class="btn btn-primary" onclick="getTabOneRerereshData();"><i class="fa fa-refresh"></i> Search</button>
                               	<!-- <button type="button" class="btn btn-primary btn-sm" ><i class="fa fa-refresh"></i> Search</button> -->
                               </div>
                               <div class="form-group pull-right">
                               	<button style="border-radius: 0px;" type="button" class="btn btn-white" onclick="getTabOneCSVData();"><i class="fa fa-download"></i> Download CSV</button>
                               </div>
                           </form>
                       </div>
                       <div class="mail-box">
		                <table class="table table-hover table-mail" id="loanDetails1">
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