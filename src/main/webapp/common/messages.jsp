<%@ include file="/common/taglibs.jsp"%>

<!-- SUCCESS MESSAGE START -->
<c:if test="${not empty successMessages}">
	<div class="alert alert-success" role="alert" style="line-height: 24px;margin-top: 20px;">
		<ul class="panel-controls  pull-left">
			<li class="panel-remove"><a href="#"><span
					class="fa fa-check" style="color: #FFF"></span></a></li>
		</ul>
		<button type="button" class="close" data-dismiss="alert"
			style="line-height: 24px; padding-right:20px">
			<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
		</button>
		<strong
			style="padding-left: 10px; padding-top: 20px; font-size: 16px;">Success!</strong>
		<c:forEach var="success" items="${successMessages}">
			<c:out value="${success}" />
			<br />
		</c:forEach>
	</div>
</c:if>
<!-- SUCCESS MESSAGE END -->
<!-- ERROR MESSAGE START -->
<c:if test="${not empty errorMessages}">
	<div class="alert alert-danger" role="alert" style="line-height: 24px;margin-top: 20px;">
		<ul class="panel-controls  pull-left">
			<li class="panel-remove"><a href="#"><span
					class="fa fa-times" style="color: #FFF"></span></a></li>
		</ul>
		<button type="button" class="close" data-dismiss="alert" style="padding-right:20px">
			<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
		</button>
		<strong style="padding-left: 10px; padding-top: 5px; font-size: 16px;">Oh
			snap!</strong>
		<c:forEach var="error" items="${errorMessages}">
			<c:out value="${error}" />
			<br />
		</c:forEach>
	</div>
</c:if>
<!-- ERROR MESSAGE END -->

<%
	session.setAttribute("successMessages", null);
	session.setAttribute("errorMessages", null);
%>