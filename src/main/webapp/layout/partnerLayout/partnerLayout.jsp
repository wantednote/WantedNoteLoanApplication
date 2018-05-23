<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Wanted Note | Partner Dashboard</title>

    <link href="assets/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/font-awesome/css/font-awesome.css" rel="stylesheet">

    <link href="assets/css/animate.css" rel="stylesheet">
    <link href="assets/css/style.css" rel="stylesheet">

</head>

<body class="gray-bg">

	<!-- Header -->
	<tiles:insertAttribute name="partnerHeader" />
	<tiles:insertAttribute name="partnerSideMenu" />
	<tiles:insertAttribute name="partnerQuickLinks" />
	<!-- Body -->
	<tiles:insertAttribute name="partnerBody" />
	<!-- Footer -->
	<tiles:insertAttribute name="partnerFooter" />
		
<!-- Mainly scripts -->
<script src="assets/js/jquery-2.1.1.js"></script>
<script src="assets/js/bootstrap.min.js"></script>

</body>
</html>