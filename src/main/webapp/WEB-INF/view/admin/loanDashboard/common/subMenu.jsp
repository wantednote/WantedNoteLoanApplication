<%@ include file="/common/taglibs.jsp"%>
<div class="col-lg-2" style="background: #ebebed; min-height: 100%;">
	<!-- <div class="mail-box-header"> -->
    <div class="ibox float-e-margins">
        <div class="ibox-content mailbox-content">
            <div class="file-manager">
                <%-- <button class="btn btn-block btn-primary compose-mail" id="addUserBtn">Add <span class="currentRoleSmallClass">${currentRoleSmall}</span></button>  --%>                           
                
                <div class="space-25"></div>
                <h5>Action</h5>
                <ul class="folder-list m-b-md" style="padding: 0">
                	<li><a href="#"></a></li>
			           <li id="1" onclick="changeSelection(1);"><a href="#tab1">&nbsp;&nbsp;&nbsp;&nbsp;Applied </a></li>
			           <li id="2" onclick="changeSelection(2);"><a href="#tab2">&nbsp;&nbsp;&nbsp;&nbsp;Dispersed </a></li>
			           <li id="3" onclick="changeSelection(3);"><a href="#tab3">&nbsp;&nbsp;&nbsp;&nbsp;Recieved </a></li>
			           <li id="4" onclick="changeSelection(4);"><a href="#tab4">&nbsp;&nbsp;&nbsp;&nbsp;Settled </a></li>
                </ul>
                <div class="clearfix"></div>
            </div>
        </div>
    </div>
    <!-- </div> -->
</div>