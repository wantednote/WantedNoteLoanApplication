<%@ include file="/common/taglibs.jsp"%>
<script src="assets/js/jquery-2.1.1.js"></script>
<title>Wantednote | Actors</title>

<!-- <div class="row">
    <div class="col-lg-12">
        <div class="wrapper wrapper-content">
            <div class="middle-box text-center animated fadeInRightBig">
                <h3 class="font-bold">This is page content</h3>

                <div class="error-desc">
                    You can create here any grid layout you want. And any variation layout you imagine:) Check out main dashboard and other site. It use many different layout.
                    <br/><a href="index.html" class="btn btn-primary m-t">Dashboard</a>
                </div>
            </div>
        </div>
    </div>
</div> -->
<div class="wrapper wrapper-content">				    
        <div class="row">
            <div class="col-lg-3">
                <div class="ibox float-e-margins">
                    <div class="ibox-content mailbox-content">
                        <div class="file-manager">
                            <button class="btn btn-block btn-primary compose-mail" id="addUserBtn">Add ${currentRole}</button>                            
                            <!-- <div class="panel-group" id="accordion">
								<div class="panel panel-primary" style="cursor: pointer;">
									<div class="panel-heading">
										<h5 class="panel-title" style="text-align: center;">
											<a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="false" class="collapsed" style="text-transform: none;">Add Actor</a>
										</h5>
									</div>
									<div id="collapseOne" class="panel-collapse collapse" aria-expanded="false" style="height: 0px;">
										<div class="panel-body">
											
										</div>
									</div>
								</div>
							</div> -->
							
                            <div class="space-25"></div>
                            <h5>Actors</h5>
                            <ul class="folder-list m-b-md" style="padding: 0">
	                            <c:forEach var="role" items="${roles}">
									<li><a href="${userDetailsSessionForm.selectedBaseLink}?id=${role.id}"> <i class="fa fa-file-text-o "></i> ${role.role} <span class="label label-warning pull-right">${role.id}</span> </a></li>
								</c:forEach>
                                <!-- <li><a href="mailbox.html"> <i class="fa fa-inbox "></i> Inbox <span class="label label-warning pull-right">16</span> </a></li>
                                <li><a href="mailbox.html"> <i class="fa fa-envelope-o"></i> Send Mail</a></li>
                                <li><a href="mailbox.html"> <i class="fa fa-certificate"></i> Important</a></li>
                                <li><a href="mailbox.html"> <i class="fa fa-file-text-o"></i> Drafts <span class="label label-danger pull-right">2</span></a></li>
                                <li><a href="mailbox.html"> <i class="fa fa-trash-o"></i> Trash</a></li> -->
                            </ul>
                            <div class="clearfix"></div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-9 animated fadeInRight" id ="viewUserDiv">
            <div class="mail-box-header">

                <form method="get" action="index.html" class="pull-right mail-search">
                    <div class="input-group">
                        <input type="text" class="form-control input-sm" name="search" placeholder="Search actor">
                        <div class="input-group-btn">
                            <button type="submit" class="btn btn-sm btn-primary">
                                Search
                            </button>
                        </div>
                    </div>
                </form>
                <h2>
                    ${currentRole} (${userCount})
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

                <table class="table table-hover table-mail">
	                <tbody>
	                <tr class="unread">
	                    <td class="check-mail">
	                        <!-- <input type="checkbox" class="i-checks"> -->Sl
	                    </td>
	                    <td class="mail-ontact"><a href="mail_detail.html">Name & Account Status</a></td>
	                    <td class="mail-subject"><a href="mail_detail.html">Email Address</a></td>
	                    <td class="">Last Modification<!-- <i class="fa fa-paperclip"></i> --></td>
	                    <td class="mail-date">Last Login</td>
	                    <td class="text-right mail-date">Action</td>
	                </tr>
	                <c:if test="${not empty users}">
	                	<c:forEach var="user" items="${users}" varStatus="index">
	                	<tr class="read">
		                    <td class="check-mail">
		                        <!-- <input type="checkbox" class="i-checks"> -->${index.count}
		                    </td>
		                    <td class="mail-ontact"><a href="#">${user.name}</a>
		                    	<c:if test="${user.accountStatus == 'Pending'}"> 
		                    		<span class="label label-warning pull-right">${user.accountStatus}</span> 
		                    	</c:if>
		                    	<c:if test="${user.accountStatus == 'Active'}"> 
		                    		<span class="label label-primary pull-right">${user.accountStatus}</span> 
		                    	</c:if>
		                    	<c:if test="${user.accountStatus == 'Blocked'}"> 
		                    		<span class="label label-success pull-right">${user.accountStatus}</span> 
		                    	</c:if>
		                    	<c:if test="${user.accountStatus == 'Deleted'}"> 
		                    		<span class="label label-danger pull-right">${user.accountStatus}</span> 
		                    	</c:if>
		                    </td>
		                    <td class="mail-subject"><a href="#">${user.email}</a></td>
		                    <td class="">${user.modifiedOn}</td>
		                    <td class="mail-date">${user.lastLogin}</td>
		                    <td>
		                    	<div class="input-group-btn">
                                    <button data-toggle="dropdown" class="btn btn-white dropdown-toggle btn-sm" type="button" aria-expanded="true">Action <span class="caret"></span></button>
                                    <ul class="dropdown-menu pull-right">
                                        <li><a href="#">Active</a></li>
                                        <li><a href="#">Pending</a></li>
                                        <li><a href="#">Blocked</a></li>
                                        <li class="divider"></li>
                                        <li><a href="#">Delete</a></li>
                                    </ul>
                                </div>
		                    </td>
		                </tr>
	                </c:forEach>
	                </c:if>
	                <c:if test="${empty users}">
	                	<tr class="read">
		                    <td class="check-mail">
		                        <!-- <input type="checkbox" class="i-checks"> -->
		                    </td>
		                    <td class="mail-ontact"><a href="mail_detail.html"></a> <span class="label label-warning pull-right"></span> </td>
		                    <td class="mail-subject"><a href="mail_detail.html">No record found</a></td>
		                    <td class=""></td>
		                    <td class="text-right mail-date"></td>
		                </tr>
	                </c:if>
	                <!-- <tr class="unread">
	                    <td class="check-mail">
	                        <input type="checkbox" class="i-checks">
	                    </td>
	                    <td class="mail-ontact"><a href="mail_detail.html">Anna Smith</a></td>
	                    <td class="mail-subject"><a href="mail_detail.html">Lorem ipsum dolor noretek imit set.</a></td>
	                    <td class=""><i class="fa fa-paperclip"></i></td>
	                    <td class="text-right mail-date">6.10 AM</td>
	                </tr>
	                <tr class="unread">
	                    <td class="check-mail">
	                        <input type="checkbox" class="i-checks" checked>
	                    </td>
	                    <td class="mail-ontact"><a href="mail_detail.html">Jack Nowak</a></td>
	                    <td class="mail-subject"><a href="mail_detail.html">Aldus PageMaker including versions of Lorem Ipsum.</a></td>
	                    <td class=""></td>
	                    <td class="text-right mail-date">8.22 PM</td>
	                </tr>
	                <tr class="read">
	                    <td class="check-mail">
	                        <input type="checkbox" class="i-checks">
	                    </td>
	                    <td class="mail-ontact"><a href="mail_detail.html">Facebook</a> <span class="label label-warning pull-right">Clients</span> </td>
	                    <td class="mail-subject"><a href="mail_detail.html">Many desktop publishing packages and web page editors.</a></td>
	                    <td class=""></td>
	                    <td class="text-right mail-date">Jan 16</td>
	                </tr>
	                <tr class="read">
	                    <td class="check-mail">
	                        <input type="checkbox" class="i-checks">
	                    </td>
	                    <td class="mail-ontact"><a href="mail_detail.html">Mailchip</a></td>
	                    <td class="mail-subject"><a href="mail_detail.html">There are many variations of passages of Lorem Ipsum.</a></td>
	                    <td class=""></td>
	                    <td class="text-right mail-date">Mar 22</td>
	                </tr> -->
	                </tbody>
	                </table>
                </div>
            </div>
            
            <div class="col-lg-9 animated fadeInRight" id="addUserDiv">
	            <div class="mail-box-header">
	                <!-- <div class="pull-right tooltip-demo">
	                    <a href="mailbox.html" class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="top" title="" data-original-title="Move to draft folder"><i class="fa fa-pencil"></i> Draft</a>
	                    <a href="mailbox.html" class="btn btn-danger btn-sm" data-toggle="tooltip" data-placement="top" title="Discard email"><i class="fa fa-times"></i> Discard</a>
	                </div> -->
	                <h2>
	                    Add ${currentRole}
	                </h2>
	            </div>
                <div class="mail-box">
	                <div class="mail-body">
	                    <form class="form-horizontal" method="get">
	                    	<input type="hidden" value="${currentRole}">
	                        <div class="form-group"><label class="col-sm-3 control-label">Name:</label>
	
	                            <div class="col-sm-9"><input type="text" class="form-control"></div>
	                        </div>
	                        <div class="form-group"><label class="col-sm-3 control-label">Email Address:</label>
	
	                            <div class="col-sm-9"><input type="text" class="form-control" value=""></div>
	                        </div>
	                        <div class="form-group"><label class="col-sm-3 control-label">Password:</label>
	
	                            <div class="col-sm-9"><input type="text" class="form-control" value=""></div>
	                        </div>
	                        <div class="form-group"><label class="col-sm-3 control-label">Confirm Password:</label>
	
	                            <div class="col-sm-9"><input type="text" class="form-control" value=""></div>
	                        </div>
	                    </form>
	                </div>
                    <div class="mail-body text-right tooltip-demo">
                        <a href="mailbox.html" class="btn btn-sm btn-primary"><i class="fa fa-reply"></i> Save</a>
                        <button class="btn btn-danger btn-sm" id="clacelAddUserBtn"><i class="fa fa-times"></i>Cancel</button>
                        <!-- <a href="mailbox.html" class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="top" title="Move to draft folder"><i class="fa fa-pencil"></i> Draft</a> -->
                    </div>
                    <div class="clearfix"></div>
                </div>
            </div>
        </div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	$("#pageHeaderNav").addClass('white-bg');
	$("#quickLinksDiv").hide();
	$("#addUserDiv").hide();
	
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
});
</script>