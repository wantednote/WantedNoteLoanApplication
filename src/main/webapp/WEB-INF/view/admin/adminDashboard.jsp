<%@ include file="/common/taglibs.jsp"%>
<script src="assets/js/jquery-2.1.1.js"></script>
<title>Wantednote | Admin Dashboard</title>

<div class="row">
    <div class="col-lg-12">
        <div class="wrapper wrapper-content">
            <div class="middle-box text-center animated fadeInRightBig">
                <h3 class="font-bold">This is page content</h3>

                <div class="error-desc">
                    You can create here any grid layout you want. And any variation layout you imagine:) Check out main dashboard and other site. It use many different layout.
                    <br/><a href="index.html" class="btn btn-primary m-t">Dashboard</a>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
$(document).ready(function() {
	setTimeout(function() {
        toastr.options = {
            closeButton: true,
            progressBar: true,
            showMethod: 'slideDown',
            timeOut: 4000
        };
        toastr.success('This is landing page', 'Welcome to Wantednote');
    }, 1300);
});
</script>