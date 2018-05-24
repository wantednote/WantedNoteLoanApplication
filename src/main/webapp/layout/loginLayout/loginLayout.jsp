<%@ include file="/common/taglibs.jsp"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ include file="/common/meta.jsp"%>
<!DOCTYPE html>
<html>

<head>
	
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	
    <title>Wantednote | Login</title>
    
	<link rel="icon" href="assets/img/logo/WantedNote1.png">
	
    <link href="assets/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/font-awesome/css/font-awesome.css" rel="stylesheet">

    <link href="assets/css/animate.css" rel="stylesheet">
    <link href="assets/css/style.css" rel="stylesheet">

</head>

<body class="gray-bg">
	<tiles:insertAttribute name="loginHeader" />
    <tiles:insertAttribute name="loginBody" />
    <tiles:insertAttribute name="loginFooter" />
    <%
		response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	
</body>
<!-- Mainly scripts -->
<!-- <script src="assets/js/jquery-2.1.1.js"></script> -->
<script src="assets/js/bootstrap.min.js"></script>
<script src="assets/js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="assets/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

<!-- Custom and plugin javascript -->
<script src="assets/js/inspinia.js"></script>
<script src="assets/js/plugins/pace/pace.min.js"></script>

<script src="assets/js/plugins/jquery-ui/jquery-ui.min.js"></script>

<!-- Jquery Validate -->
<script src="assets/js/plugins/validate/jquery.validate.min.js"></script>
</html>