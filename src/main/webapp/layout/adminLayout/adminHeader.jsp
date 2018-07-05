<%@ include file="/common/taglibs.jsp"%>
<div class="navbar-header">
    <a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="#"><i class="fa fa-bars"></i> </a>
    <!-- <form role="search" class="navbar-form-custom" method="post" action="search_results.html">
        <div class="form-group">
            <input type="text" placeholder="Search for something..." class="form-control" name="top-search" id="top-search">
        </div>
    </form> -->
</div>
	<c:if test="${not empty userDetailsSessionForm.emailAddress}">
	    <div class="navbar-collapse collapse" id="navbar">
               <ul class="nav navbar-nav navbar-right">
                   <li class="active">
                       <a aria-expanded="false" role="button" href="<c:url value="/admin" />"> Dashboard</a>
                   </li>
                   <li class="dropdown">
                       <a aria-expanded="false" role="button" href="#" class="dropdown-toggle" data-toggle="dropdown"> Options <span class="caret"></span></a>
                       <ul role="menu" class="dropdown-menu">
                           <li><a href="<c:url value="/loan" />">Loan</a></li>
                           <li><a href="<c:url value="/actors" />">Actors</a></li>
                           <!-- <li><a href="">Menu item</a></li>
                           <li><a href="">Menu item</a></li> -->
                       </ul>
                   </li>
                   <li class="dropdown">
                       <a aria-expanded="false" role="button" href="#" class="dropdown-toggle" data-toggle="dropdown"> My Account <span class="caret"></span></a>
                       <ul role="menu" class="dropdown-menu">
                           <li><a href="<c:url value="/profile" />">Profile</a></li>
                           <li><a href="<c:url value="/profile" />">Setting</a></li>
                           <li><a href="<c:url value="/logout" />"><i class="fa fa-sign-out"></i> Log Out</a></li>
                           <!-- <li><a href="">Menu item</a></li> -->
                       </ul>
                   </li>
                   <%-- <li>
			            <a href="<c:url value="/logout" />">
			                <i class="fa fa-sign-out"></i> Log out
			            </a>
			        </li> --%>
			        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
               </ul>
               <%-- <ul class="nav navbar-nav navbar-right">
                   <li>
			            <a href="<c:url value="/logout" />">
			                <i class="fa fa-sign-out"></i> Log out
			            </a>
			        </li>
               </ul> --%>
          </div>
	</c:if>