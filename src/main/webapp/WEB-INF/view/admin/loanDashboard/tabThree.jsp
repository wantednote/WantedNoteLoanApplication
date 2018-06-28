<%@ include file="/common/taglibs.jsp"%>
<script src="<c:url value="/assets/appAssets/js/admin/loan/tabThree.js" />"></script>

<script src="<c:url value="/assets/fileUpload/js/jquery.knob.js" />"></script>
<script src="<c:url value="/assets/fileUpload/js/jquery.ui.widget.js" />"></script>
<script src="<c:url value="/assets/fileUpload/js/jquery.iframe-transport.js" />"></script>
<script src="<c:url value="/assets/fileUpload/js/jquery.fileupload.js" />"></script>
<div class="ibox float-e-margins">
                       <div class="ibox-title">
                           <h5>Fund Received</h5>
                       </div>
                       <div class="ibox-content" style="display: block;">
                           <form role="form" class="form-inline">
                               <%-- <jsp:include page="/WEB-INF/view/admin/loanDashboard/common/searchMenu.jsp" flush="true" /> --%>
                               <div class="form-group">
                                   <div id="reportrange3" style="background: #fff; cursor: pointer; padding: 7px 10px;margin-top: -5px; border: 1px solid #ccc; width: 100%">
									   <i class="fa fa-calendar"></i>&nbsp;
									   <span></span> <i class="fa fa-caret-down"></i>
								   </div>
                               </div>
                               <c:if test="${not empty distributers}">
	                               <div class="form-group">
	                                    <select id="distributerList3" name="distributer[]" multiple class="form-control" >
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
                               	<button style="border-radius: 0px;" type="button" class="btn btn-primary" onclick="getTabThreeRerereshData();"><i class="fa fa-refresh"></i> Search</button>
                               </div>
                               <div class="form-group pull-right">
                               	<button style="border-radius: 0px;" type="button" class="btn btn-white" onclick="getTabThreeCSVData();"><i class="fa fa-download"></i> Download CSV</button>
                               </div>
                               <div class="form-group pull-right">
                               	<a style="border-radius: 0px;" class="btn btn-white" data-toggle="modal" href="#upload_tab_three_model"><i class="fa fa-upload"></i> Upload Bank Statement CSV</a>
                               </div>
                           </form>
                       </div>
                       <div class="mail-box">
			               <!-- <table class="table table-hover table-mail" id="loanDetails3">
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
		                   </table> -->
	                   </div>
	                   <div id="upload_tab_three_model" class="modal fade" aria-hidden="true">
                           <div class="modal-dialog">
                               <div class="modal-content">
                                   <div class="modal-body">
                                       <div class="row">
                                           <div class="col-sm-6 b-r"><h3 class="m-t-none m-b">Upload Bank Statement</h3>

                                               <p>Select .CSV file to upload.</p>

                                               <form action="uploadBankStatement" role="form" enctype="multipart/form-data" id="bankStatementForm" method="post">
                                                   <!-- <div class="form-group"><label>Email</label> <input type="email" placeholder="Enter email" class="form-control"></div> -->
                                                   <div class="form-group">
                                                   		<input id="default_file" type="file" name="csvFile" class="form-control">
                                                   </div>
                                                   <div>
                                                       <button class="btn btn-sm btn-primary pull-right m-t-n-xs" type="submit"><strong>Upload</strong></button>
                                                   </div>
                                               </form>
                                           </div>
                                           <!-- <div class="col-sm-6"><h4>Not a member?</h4>
                                               <p>You can create an account:</p>
                                               <p class="text-center">
                                                   <a href=""><i class="fa fa-sign-in big-icon"></i></a>
                                               </p>
                                       	   </div> -->
                                   </div>
                               </div>
                               </div>
                           </div>
                       </div>
 				 </div>