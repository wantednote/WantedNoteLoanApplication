<%@ include file="/common/taglibs.jsp"%>
<!-- <script src="assets/js/jquery-2.1.1.js"></script> -->
<script src="<c:url value="/assets/js/jquery-2.1.1.js" />"></script>

<!-- SUCCESS MESSAGE START -->
<c:if test="${not empty successMessages}">
	<div class="row wrapper border-bottom image-bg page-heading" id="successMessageDiv">
	    <div class="col-sm-10">
	        <h1 style="color:#1ab394;"><strong>Success</strong></h1>
	        <ol class="breadcrumb" style="font-size: 14px; color: #428bca;background-color: rgba(255,255,255,.15);">
	            <li>
	                <a href="#">
	                	<c:forEach var="success" items="${successMessages}">
							<c:out value="${success}" />
							<br />
						</c:forEach>
					</a>
	            </li>
	        </ol>
	    </div>
	    <div class="col-sm-2">
	    	<button type="button" class="btn btn-sm btn-primary pull-right" id="successBtn" style="margin-top: 30%"><i class="fa fa-times"></i> Close</button>
	    </div>
	</div>
</c:if>
<!-- SUCCESS MESSAGE END -->
<!-- ERROR MESSAGE START -->
<c:if test="${not empty errorMessages}">
	<div class="row wrapper border-bottom image-bg page-heading" id="errorMessageDiv">
	    <div class="col-sm-10">
	        <h1 style="color:#ed5565;"><strong>Failure</strong></h1>
	        <ol class="breadcrumb" style="font-size: 14px; color: #f8ac59;background-color: rgba(255,255,255,.15);">
	            <li>
	                <a href="#">
	                	<c:forEach var="error" items="${errorMessages}">
							<c:out value="${error}" />
							<br />
						</c:forEach>
					</a>
	            </li>
	        </ol>
	    </div>
	    <div class="col-sm-2">
	    	<button type="button" class="btn btn-danger btn-sm pull-right" id="errorBtn" style="margin-top: 30%"><i class="fa fa-times"></i> Close</button>
	    </div>
	</div>
</c:if>
<!-- ERROR MESSAGE END -->

<%
	session.setAttribute("successMessages", null);
	session.setAttribute("errorMessages", null);
%>
<style>
.image-bg {
    background-image: url(assets/img/logo/messagebg.png);
    background-size: 334px 104px;
}
</style>
<script type="text/javascript">
$(document).ready(function() {
	$( "#successBtn" ).click(function() {
	  	$("#successMessageDiv").hide();
	});
	$( "#errorBtn" ).click(function() {
	  	$("#errorMessageDiv").hide();
	});
});
</script>