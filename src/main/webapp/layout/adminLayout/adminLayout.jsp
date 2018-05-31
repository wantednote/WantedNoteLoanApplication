<%@ include file="/common/taglibs.jsp"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ include file="/common/meta.jsp"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <title>Wantednote | Administrator</title>
    
    <!-- <link rel="icon" href="assets/img/logo/WantedNote1.png">
    <link href="assets/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/font-awesome/css/font-awesome.css" rel="stylesheet"> -->
    
    <!-- Toastr style -->
    <!-- <link href="assets/css/plugins/toastr/toastr.min.css" rel="stylesheet"> -->
    
    <!-- Gritter -->
    <!-- <link href="assets/js/plugins/gritter/jquery.gritter.css" rel="stylesheet">
    <link href="assets/css/animate.css" rel="stylesheet">
    <link href="assets/css/style.css" rel="stylesheet"> -->
    
    
    <link rel="icon" href="<c:url value="/assets/img/logo/WantedNote1.png" />">
    <link href="<c:url value="/assets/css/bootstrap.min.css" />" rel="stylesheet">
    <link href="<c:url value="/assets/font-awesome/css/font-awesome.css" />" rel="stylesheet">
    
    <!-- Toastr style -->
    <link href="<c:url value="/assets/css/plugins/toastr/toastr.min.css" />" rel="stylesheet">
    
    <!-- Gritter -->
    <link href="<c:url value="/assets/js/plugins/gritter/jquery.gritter.css" />" rel="stylesheet">
    <link href="<c:url value="/assets/css/animate.css" />" rel="stylesheet">
    <link href="<c:url value="/assets/css/style.css" />" rel="stylesheet">
    
    <!-- loader -->
    <link href="<c:url value="/assets/loader/waitMe.css" />" rel="stylesheet">
    <!-- Confirmation -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.0/jquery-confirm.min.css">
</head>

<body>
    <div id="wrapper">
    	<tiles:insertAttribute name="adminSideMenu" />
    	<div id="page-wrapper" class="gray-bg dashbard-1">
        	<div class="row border-bottom">
		        <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0;" id="pageHeaderNav">
		        <!-- <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0;box-shadow: 0 1px 1px 0 #ccc;"> -->
		        	<tiles:insertAttribute name="adminHeader" />
		        </nav>
	        </div>
	        <tiles:insertAttribute name="adminQuickLinks" />
	        <jsp:include page="/common/messages.jsp" flush="true" />
	        <tiles:insertAttribute name="adminBody" />
	        <input type="hidden" value="<c:url value="/" />" id="appLink">
			<input type="hidden" value="${userDetailsSessionForm.selectedBaseLink}" id="selectedBaseLink">
	        <tiles:insertAttribute name="adminFooter" />
        </div>
    </div>
    <%
		response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
	%>

<!-- Mainly scripts -->
<!-- <script src="assets/js/jquery-2.1.1.js"></script> -->

<!-- <script src="assets/js/bootstrap.min.js"></script>
<script src="assets/js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="assets/js/plugins/slimscroll/jquery.slimscroll.min.js"></script> -->
<script src="<c:url value="/assets/js/bootstrap.min.js" />"></script>
<script src="<c:url value="/assets/js/plugins/metisMenu/jquery.metisMenu.js" />"></script>
<script src="<c:url value="/assets/js/plugins/slimscroll/jquery.slimscroll.min.js" />"></script>

<!-- Flot -->
<!-- <script src="assets/js/plugins/flot/jquery.flot.js"></script>
<script src="assets/js/plugins/flot/jquery.flot.tooltip.min.js"></script>
<script src="assets/js/plugins/flot/jquery.flot.spline.js"></script>
<script src="assets/js/plugins/flot/jquery.flot.resize.js"></script>
<script src="assets/js/plugins/flot/jquery.flot.pie.js"></script> -->
<script src="<c:url value="/assets/js/plugins/flot/jquery.flot.js" />"></script>
<script src="<c:url value="/assets/js/plugins/flot/jquery.flot.tooltip.min.js" />"></script>
<script src="<c:url value="/assets/js/plugins/flot/jquery.flot.spline.js" />"></script>
<script src="<c:url value="/assets/js/plugins/flot/jquery.flot.resize.js" />"></script>
<script src="<c:url value="/assets/js/plugins/flot/jquery.flot.pie.js" />"></script>

<!-- Peity -->
<!-- <script src="assets/js/plugins/peity/jquery.peity.min.js"></script>
<script src="assets/js/demo/peity-demo.js"></script> -->
<script src="<c:url value="/assets/js/plugins/peity/jquery.peity.min.js" />"></script>
<script src="<c:url value="/assets/js/demo/peity-demo.js" />"></script>

<!-- Custom and plugin javascript -->
<!-- <script src="assets/js/inspinia.js"></script>
<script src="assets/js/plugins/pace/pace.min.js"></script> -->
<script src="<c:url value="/assets/js/inspinia.js" />"></script>
<script src="<c:url value="/assets/js/plugins/pace/pace.min.js" />"></script>

<!-- jQuery UI -->
<!-- <script src="assets/js/plugins/jquery-ui/jquery-ui.min.js"></script> -->
<script src="<c:url value="/assets/js/plugins/jquery-ui/jquery-ui.min.js" />"></script>

<!-- GITTER -->
<!-- <script src="assets/js/plugins/gritter/jquery.gritter.min.js"></script> -->
<script src="<c:url value="/assets/js/plugins/gritter/jquery.gritter.min.js" />"></script>

<!-- Sparkline -->
<!-- <script src="assets/js/plugins/sparkline/jquery.sparkline.min.js"></script> -->
<script src="<c:url value="/assets/js/plugins/sparkline/jquery.sparkline.min.js" />"></script>

<!-- Sparkline demo data  -->
<!-- <script src="assets/js/demo/sparkline-demo.js"></script> -->
<script src="<c:url value="/assets/js/demo/sparkline-demo.js" />"></script>

<!-- ChartJS-->
<!-- <script src="assets/js/plugins/chartJs/Chart.min.js"></script> -->
<script src="<c:url value="/assets/js/plugins/chartJs/Chart.min.js" />"></script>

<!-- Toastr -->
<!-- <script src="assets/js/plugins/toastr/toastr.min.js"></script> -->
<script src="<c:url value="/assets/js/plugins/toastr/toastr.min.js" />"></script>

<!-- Loader -->
<script src="<c:url value="/assets/loader/waitMe.js" />"></script>

<!-- Confirmation -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.0/jquery-confirm.min.js"></script>

</body>
<style>
.table > thead > tr > th, .table > tbody > tr > th, .table > tfoot > tr > th, .table > thead > tr > td, .table > tbody > tr > td, .table > tfoot > tr > td {
    border-top: 1px solid #e7eaec;
    line-height: 1.42857;
    padding: 8px;
    vertical-align: top;
    font-size: 12px;
}

.lds-roller {
  display: inline-block;
  position: relative;
  width: 64px;
  height: 64px;
}
.lds-roller div {
  animation: lds-roller 1.2s cubic-bezier(0.5, 0, 0.5, 1) infinite;
  transform-origin: 32px 32px;
}
.lds-roller div:after {
  content: " ";
  display: block;
  position: absolute;
  width: 6px;
  height: 6px;
  border-radius: 50%;
  background: #fff;
  margin: -3px 0 0 -3px;
}
.lds-roller div:nth-child(1) {
  animation-delay: -0.036s;
}
.lds-roller div:nth-child(1):after {
  top: 50px;
  left: 50px;
}
.lds-roller div:nth-child(2) {
  animation-delay: -0.072s;
}
.lds-roller div:nth-child(2):after {
  top: 54px;
  left: 45px;
}
.lds-roller div:nth-child(3) {
  animation-delay: -0.108s;
}
.lds-roller div:nth-child(3):after {
  top: 57px;
  left: 39px;
}
.lds-roller div:nth-child(4) {
  animation-delay: -0.144s;
}
.lds-roller div:nth-child(4):after {
  top: 58px;
  left: 32px;
}
.lds-roller div:nth-child(5) {
  animation-delay: -0.18s;
}
.lds-roller div:nth-child(5):after {
  top: 57px;
  left: 25px;
}
.lds-roller div:nth-child(6) {
  animation-delay: -0.216s;
}
.lds-roller div:nth-child(6):after {
  top: 54px;
  left: 19px;
}
.lds-roller div:nth-child(7) {
  animation-delay: -0.252s;
}
.lds-roller div:nth-child(7):after {
  top: 50px;
  left: 14px;
}
.lds-roller div:nth-child(8) {
  animation-delay: -0.288s;
}
.lds-roller div:nth-child(8):after {
  top: 45px;
  left: 10px;
}
@keyframes lds-roller {
  0% {
    transform: rotate(0deg);
  }
  100% {
    transform: rotate(360deg);
  }
}

</style>
</html>