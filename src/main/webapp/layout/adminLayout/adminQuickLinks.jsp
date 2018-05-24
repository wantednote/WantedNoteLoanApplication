<%@ include file="/common/taglibs.jsp"%>
<div class="row wrapper border-bottom white-bg page-heading" id="quickLinksDiv">
    <div class="col-sm-4">
        <h2>${userDetailsSessionForm.pageHeaderTitle}</h2>
        <ol class="breadcrumb">
            <li>
                <a href="${userDetailsSessionForm.selectedBaseLink}">This is</a>
            </li>
            <li class="active">
                <strong><a href="${userDetailsSessionForm.selectedSubLink}"> ${userDetailsSessionForm.pageHeaderTitle}</a></strong>
            </li>
        </ol>
    </div>
    <div class="col-sm-8">
        <div class="title-action">
            <a href="" class="btn btn-primary">This is action area</a>
        </div>
    </div>
</div>