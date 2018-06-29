<%@ include file="/common/taglibs.jsp"%>
<c:if test="${successMessages == null && errorMessages == null}">
	<div class="row wrapper border-bottom white-bg page-heading" id="quickLinksDiv">
	    <div class="col-sm-4">
	        <h2>${userDetailsSessionForm.pageHeaderTitle}</h2>
	        <ol class="breadcrumb">
	            <li>
	            	<a href="<c:url value="/${userDetailsSessionForm.selectedBaseLink}" />">This is</a>
	                <%-- <a href="${userDetailsSessionForm.selectedBaseLink}">This is</a> --%>
	            </li>
	            <li class="active">
	                <%-- <strong><a href="${userDetailsSessionForm.selectedSubLink}"> ${userDetailsSessionForm.pageHeaderTitle}</a></strong> --%>
	                <strong><a href="<c:url value="/${userDetailsSessionForm.selectedSubLink}" />"> ${userDetailsSessionForm.pageHeaderTitle}</a></strong>
	            </li>
	        </ol>
	    </div>
	    <div class="col-sm-8">
	        <div class="title-action">
	            <a href="<c:url value="/${userDetailsSessionForm.selectedBaseLink}" />" class="btn btn-primary">Referesh</a>
	        </div>
	    </div>
	</div>
</c:if>