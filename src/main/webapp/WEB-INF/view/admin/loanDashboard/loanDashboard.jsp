<%@ include file="/common/taglibs.jsp"%>
<link href="<c:url value="/assets/formvalidation/formValidation.min09a2.css?v2.1.0" />" rel="stylesheet">
<link href="<c:url value="/assets/css/plugins/dataTables/dataTables.responsive.css" />" rel="stylesheet">
<link href="<c:url value="/assets/css/plugins/dataTables/dataTables.tableTools.min.css" />" rel="stylesheet">

<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-multiselect/0.9.13/css/bootstrap-multiselect.css" />

<script src="<c:url value="/assets/js/jquery-2.1.1.js" />"></script>

<script src="<c:url value="/assets/formvalidation/formValidation.min.js" />"></script>
<script src="<c:url value="/assets/formvalidation/framework/bootstrap.min.js" />"></script>

<script src="<c:url value="/assets/js/plugins/dataTables/jquery.dataTables.js" />"></script>
<script src="<c:url value="/assets/js/plugins/dataTables/dataTables.bootstrap.js" />"></script>
<script src="<c:url value="/assets/js/plugins/dataTables/dataTables.responsive.js" />"></script>
<script src="<c:url value="/assets/js/plugins/dataTables/dataTables.tableTools.min.js" />"></script>
   
<script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-3-typeahead/4.0.2/bootstrap3-typeahead.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-multiselect/0.9.13/js/bootstrap-multiselect.js"></script>
  
  
<script src="<c:url value="/assets/appAssets/js/admin/loan/loan.js" />"></script>

<title>Wantednote | Loan</title>
<style>
.dataTables_info{
	padding-left: 12px;
}
.pagination {
    display: inline-block;
    padding-left: 30%;
    margin: 0px 0;
    border-radius: 4px;
}
.folder-list-selected {
    /* border-bottom: 1px solid #f8ac59;
    border-top: 1px solid #f8ac59; */
    background: #e7eaec;
}
.btn-group>.btn:first-child {
    margin-left: 0;
    background: white;
    color: #263949;
    border-radius: 0px;
}
.dropdown-menu {
	max-height: 400px;
	overflow-y: auto;
	overflow-x: hidden;
}
</style>
<div class="wrapper wrapper-content" id="container">		    
	<div class="row">
	    <jsp:include page="/WEB-INF/view/admin/loanDashboard/common/subMenu.jsp" flush="true" />
	    <div class="col-lg-10" id="tab1">
	    	<jsp:include page="/WEB-INF/view/admin/loanDashboard/tabOne.jsp" flush="true" /> 
	    </div>
	    <div class="col-lg-10" id="tab2">
	    	<jsp:include page="/WEB-INF/view/admin/loanDashboard/tabTwo.jsp" flush="true" />
	    </div>
	    <div class="col-lg-10" id="tab3">
	    	<jsp:include page="/WEB-INF/view/admin/loanDashboard/tabThree.jsp" flush="true" />
	    </div>
	    <div class="col-lg-10" id="tab4">
	    	<jsp:include page="/WEB-INF/view/admin/loanDashboard/tabFour.jsp" flush="true" />
	    </div>
	</div>
</div>
<script type="text/javascript">
 /* function changeSelection(li){
	 //$("li").removeClass("folder-list-selected");
	 //$("#" + li).addClass("folder-list-selected");
  	 for(var i=1; i<=4; i++){
  		 if(li == i){
  			 $("#tab" + i).show();
  			$("#" + i).addClass("folder-list-selected");
  		 }else{
  			$("#tab" + i).hide();
  			$("#" + i).removeClass("folder-list-selected");
  		 }
  	 }
 } */
</script>