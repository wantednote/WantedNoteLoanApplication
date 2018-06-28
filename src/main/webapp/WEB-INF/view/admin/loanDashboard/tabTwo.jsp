<%@ include file="/common/taglibs.jsp"%>
<script src="<c:url value="/assets/appAssets/js/admin/loan/tabTwo.js" />"></script>
<div class="ibox float-e-margins">
                       <div class="ibox-title">
                           <h5>Loan Dispersed</h5>
                       </div>
                       <div class="ibox-content" style="display: block;">
                           <form role="form" class="form-inline">
                               <%-- <jsp:include page="/WEB-INF/view/admin/loanDashboard/common/searchMenu.jsp" flush="true" /> --%>
                               <div class="form-group">
                                   <div id="reportrange2" style="background: #fff; cursor: pointer; padding: 7px 10px;margin-top: -5px; border: 1px solid #ccc; width: 100%">
									   <i class="fa fa-calendar"></i>&nbsp;
									   <span></span> <i class="fa fa-caret-down"></i>
								   </div>
                               </div>
                               <c:if test="${not empty distributers}">
	                               <div class="form-group">
	                                    <select id="distributerList2" name="distributer[]" multiple class="form-control" >
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
                               	<button style="border-radius: 0px;" type="button" class="btn btn-primary" onclick="getTabTwoRerereshData();"><i class="fa fa-refresh"></i> Search</button>
                               </div>
                               <!-- <div class="form-group pull-right">
                               	<button style="border-radius: 0px;" type="button" class="btn btn-white" onclick="getCSVData();"><i class="fa fa-download"></i> Download CSV</button>
                               </div> -->
                           </form>
                       </div>
                       <div class="mail-box">
		                <table class="table table-hover table-mail" id="loanDetails1">
			                <thead style="background: #f3f3f4;">
				                <tr class="unread">
				                	<th>Txn Id</th>
				                	<th>Online Payment Id</th>
				                	<th>Retailer Name</th>
				                	<th>Amount</th>
				                	<th>Date</th>
				                	<th>Repay Txn Id</th> 
				                </tr>
			                </thead>
	                	</table>
	                </div>
                   </div>