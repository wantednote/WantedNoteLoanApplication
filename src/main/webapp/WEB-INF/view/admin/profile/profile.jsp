<%@ include file="/common/taglibs.jsp"%>
<link href="<c:url value="/assets/formvalidation/formValidation.min09a2.css?v2.1.0" />" rel="stylesheet">

<script src="<c:url value="/assets/js/jquery-2.1.1.js" />"></script>

<script src="<c:url value="/assets/formvalidation/formValidation.min.js" />"></script>
<script src="<c:url value="/assets/formvalidation/framework/bootstrap.min.js" />"></script>
<script src="<c:url value="/assets/appAssets/js/admin/profile/profile.js" />"></script>
<title>Wantednote | Profile</title>
<div class="wrapper wrapper-content">
    <div class="row animated fadeInRight">
        <div class="col-md-4">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>Profile Detail</h5>
                </div>
                <div>
                    <div class="ibox-content no-padding border-left-right">
                        <img alt="image" class="img-responsive" src="assets/img/profile_big.jpg">
                    </div>
                    <div class="ibox-content profile-content">
                        <h4><strong>${userDetailsSessionForm.name}</strong></h4>
                        <p><i class="fa fa-meh-o"></i> Role ${userDetailsSessionForm.userRoleName} </p>
                        <h5>
                            Contact Info
                        </h5>
                        <p>
                            For Support reach us @ : 044-30840178, Email @ :  sales@cfshealthcare.com, support@cfshealthcare.com.
							Copyright © cfshealthcare.com 2017, An initiative of CFS Health Care Service Pvt Ltd.
                        </p>
                    </div>
            </div>
        </div>
            </div>
        <div class="col-md-8">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>Update</h5>                </div>
                <div class="ibox-content" style="display: block;">

                    <div>
                        <div class="feed-activity-list">

                            <div class="feed-element">
                                <!-- <a href="#" class="pull-left">
                                    <span><i class="fa fa-smile-o"></i></span>
                                </a> -->
                                <div class="media-body ">
                                	<div class="media-body ">
	                                	<form action="profile" class="form-horizontal" method="post" id="updateProfile" role="form">
					                    	<input type="hidden" name="roleName" value="${url}" id="currentRoleCaps">
					                        <div class="form-group">
					                        	<label class="col-sm-3 control-label">Name:</label>
					                            <div class="col-sm-9">
					                            	<input type="text" class="form-control" name="name">
					                            </div>
					                        </div>
					                        <div class="pull-right">
							                    <button type="submit" class="btn btn-xs btn-primary"><i class="fa fa-cog"></i> Update Profile Details</button>
		                                    </div>
					                    </form>
	                                </div>
                                </div>
                            </div>

                            <div class="feed-element">
                                <!-- <a href="#" class="pull-left">
                                    <span><i class="fa fa-cog"></i></span>
                                </a> -->
                                <div class="media-body ">
                                	<form action="password" class="form-horizontal" method="post" id="changePassword" role="form">
				                    	<input type="hidden" name="roleName" value="${url}" id="currentRoleCaps">
				                        <div class="form-group">
				                        	<label class="col-sm-3 control-label">Existing Password:</label>
				                            <div class="col-sm-9">
				                            	<input type="password" class="form-control" name="password">
				                            </div>
				                        </div>
				                        <div class="form-group">
				                        	<label class="col-sm-3 control-label">New Password:</label>
				                            <div class="col-sm-9">
				                            	<input type="password" class="form-control" name="newPassword">
				                            </div>
				                        </div>
				                        <div class="form-group">
				                        	<label class="col-sm-3 control-label">Confirm Password:</label>
				                            <div class="col-sm-9">
				                            	<input type="password" class="form-control" name="confirmPassword">
				                            </div>
				                        </div>
				                        <div class="pull-right">
						                    <button type="submit" class="btn btn-xs btn-primary"><i class="fa fa-cog"></i> Change Password</button>
	                                    </div>
				                    </form>
                                </div>
                            </div>

                            
                        </div>

                        <!-- <button class="btn btn-primary btn-block m"><i class="fa fa-arrow-down"></i> Show More</button> -->

                    </div>

                </div>
            </div>

        </div>
    </div>
</div>
<script type="text/javascript">
 
</script>