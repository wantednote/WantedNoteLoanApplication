<%@ include file="/common/taglibs.jsp"%>
<script src="<c:url value="/assets/appAssets/js/admin/loan/tabTwo.js" />"></script>
<div class="ibox float-e-margins">
                       <div class="ibox-title">
                           <h5>Loan Dispersed</h5>
                       </div>
                       <div class="ibox-content" style="display: block;background-color: #e7eaec; padding: 15px 20px 4px 20px;">
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
                               <!-- <div class="btn-group dropup">
								  <button type="button" class="btn btn-secondary">
								    Verify
								  </button>
								  <button type="button" class="btn btn-secondary dropdown-toggle dropdown-toggle-split" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
								    <span class="sr-only">Toggle Dropdown</span>
								  </button>
								  <div class="dropdown-menu">
								    Dropdown menu links
								    <a class="dropdown-item" href="#">T</a>
								    <a class="dropdown-item" href="#">F</a>
								  </div>
								</div> -->
                               <!-- <div class="btn-group">
								  <button type="button" class="btn btn-danger dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
								    Action
								  </button>
								  <div class="dropdown-menu">
								    <a class="dropdown-item" href="#">T</a>
								    <a class="dropdown-item" href="#">F</a>
								  </div>
							  </div> -->
                               <!-- <div>
										<select>
										verify
										  <option>T</option>
										  <option selected="selected">F</option>
										</select>
                               </div> -->
                               <div class="form-group pull-right">
                               	<button style="border-radius: 0px;" type="button" class="btn btn-primary" onclick="getTabTwoRerereshData();"><i class="fa fa-refresh"></i> Search</button>
                               </div>
                               <div class="form-group pull-right">
                               	<button style="border-radius: 0px;" type="button" class="btn btn-white" onclick="getTabTwoCSVData();"><i class="fa fa-download"></i> Download CSV</button>
                               </div>
                           </form>
                       </div>
                       <div class="mail-box">
		                <table class="table table-hover table-mail" id="loanDetails2">
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