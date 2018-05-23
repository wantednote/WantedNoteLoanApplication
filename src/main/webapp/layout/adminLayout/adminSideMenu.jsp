<%@ include file="/common/taglibs.jsp"%>
<script src="assets/js/jquery-2.1.1.js"></script>
<nav class="navbar-default navbar-static-side" role="navigation">
    <div class="sidebar-collapse">
        <ul class="nav" id="side-menu">
        	<c:if test="${not empty userDetailsSessionForm.emailAddress}">
	            <li class="nav-header">
	                <div class="dropdown profile-element"> <span>
	                    <!-- <img alt="image" class="img-circle" src="assets/img/myname.jpg" style="height: 50px; width: 50px;"/> -->
	                    <img alt="image" src="assets/img/logo/WantedNote1.png" style="height: 50px; width: 50px;"/>
	                    </span>
	                    <a data-toggle="dropdown" class="dropdown-toggle" href="#">
	                    <span class="clear"> <span class="block m-t-xs"> <strong class="font-bold">${userDetailsSessionForm.name}</strong>
	                    </span> <span class="text-muted text-xs block">${userDetailsSessionForm.userRoleName} <b class="caret"></b></span> </span> </a>
	                    <ul class="dropdown-menu animated fadeInRight m-t-xs">
	                        <li><a href="#">Profile</a></li>
	                        <li><a href="#">Contacts</a></li>
	                        <li><a href="#">Mailbox</a></li>
	                        <li class="divider"></li>
	                        <%-- <li><a href="<%=request.getContextPath()%>/logout.note">Logout</a></li> --%>
	                        <li><a href="<%=request.getContextPath()%>/logout">Logout</a></li>
	                    </ul>
	                </div>
	                <div class="logo-element">
	                    <!-- WN -->
	                    <!-- <img alt="image" class="img-circle" src="assets/img/logo/WantedNote1.png" style="height: 40px; width: 40px;"/> -->
	                    <img alt="image" src="assets/img/logo/WantedNote1.png" style="height: 40px; width: 40px;"/>
	                </div>
	            </li>
            </c:if>
            
            <%-- <c:if test="${empty adminDetailsSessionForm.emailAddress}">
	            <li class="nav-header">
	                <div class="dropdown profile-element"> 
	                	<span>
	                    	<img alt="image" src="assets/img/logo/WantedNote1.png" style="height: 50px; width: 50px;"/>
	                    	<!-- <img alt="image" class="img-circle" src="assets/img/logo/WantedNote1.png" style="height: 16%; width: 40%;border-radius: 0%;"/> -->
	                    </span>
	                    <a data-toggle="dropdown" class="dropdown-toggle" href="#">
	                    <span class="clear"> <span class="block m-t-xs"> <strong class="font-bold">WANTEDNOTE</strong>
	                    </span> <span class="text-muted text-xs block">We Wanted. We Noted.</span> </span> </a>
	                </div>
	                <div class="logo-element">
	                    <!-- WN -->
	                    <!-- <img alt="image" class="img-circle" src="assets/img/logo/WantedNote1.png" style="height: 40px; width: 40px;"/> -->
	                    <img alt="image" src="assets/img/logo/WantedNote1.png" style="height: 40px; width: 40px;"/>
	                </div>
	            </li>
            </c:if> --%>
            
            <li id="leftMenu_admin">
                <a href="admin"><i class="fa fa-th-large"></i> <span class="nav-label">Dashboard</span></a>
            </li>
            <!-- <li id="leftMenu_items">
                <a href="items.note"><i class="fa fa-diamond"></i> <span class="nav-label">Items</span></a>
            </li>
            <li id="leftMenu_orders">
                <a href="index.html"><i class="fa fa-flask"></i> <span class="nav-label">Orders</span> <span class="fa arrow"></span></a>
                <ul class="nav nav-second-level">
                    <li id="leftSubMenu_createOrder"><a href="createOrder.note">Create Order</a></li>
                    <li id="leftSubMenu_allOrder"><a href="allOrder.note">All Order</a></li>
                    <li id="leftSubMenu_retrieveOrder"><a href="retrieveOrder.note">Retrieve Order</a></li>
                    <li id="leftSubMenu_updateOrder"><a href="updateOrder.note">Update Order</a></li>
                    <li id="leftSubMenu_cencelOrder"><a href="cancelOrder.note">Cancel Order</a></li>
                </ul>
            </li> -->
        </ul>

    </div>
</nav>
<script>
$(document).ready(function() {
	var linkSelected = '${userDetailsSessionForm.selectedBaseLink}';
	var linkSubSelected = '${userDetailsSessionForm.selectedSubLink}';
	$("li").removeClass("active");
	$("#leftMenu_" + linkSelected).addClass("active");
	$("#leftSubMenu_" + linkSubSelected).addClass("active");
});
</script>