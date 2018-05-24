<%@ include file="/common/taglibs.jsp"%>
<script src="assets/js/jquery-2.1.1.js"></script>

<div class="loginColumns animated fadeInDown">
    <div class="row">
    	<div class="col-md-6">
			<center><img alt="image" class="img-circle" src="assets/img/logo/WantedNote.png" style="height: 20%; width: 40%;border-radius: 0%;"/></center>
        </div>
        <div class="col-md-6">
        	<h2>${userType} Login</h2>
            <div class="ibox-content" style="box-shadow: 0 1px 1px 0 #ccc;">
            	<br/>
            	<c:if test="${not empty errorMessages}">
					<div class="login-title" style="color: #C00; font-size: 13px; margin-bottom: 0px;margin-top: 20px;text-align: center;">
						<c:forEach var="error" items="${errorMessages}">
							<c:out value="${error}" />
							<br />
						</c:forEach>
					</div>
				</c:if>
				<br/>
                <form action="login" method="post" id="loginForm">
					<input type="hidden"  id="userType" name="userType" value="${userType}"/>
		            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                	<!-- <input type="hidden" name="userType" value="Admin"> -->
                    <div class="form-group">
                        <input type="text" class="form-control" name="username" placeholder="Username" required>
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" name="password" placeholder="Password">
                    </div>
                    <button type="submit" class="btn btn-primary block full-width m-b">Sign In</button>

                    <a href="registration">
                        <small>Yet not registered?</small>
                    </a>
                    <!-- <a class="btn btn-sm btn-white btn-block" href="register.html">Create an account</a> -->
                </form>
                <!-- <p class="m-t">
                    <small>Inspinia we app framework base on Bootstrap 3 &copy; 2014</small>
                </p> -->
            </div>
        </div>
    </div>
    <!-- <hr/> -->
</div>
<script type="text/javascript">
$(document).ready(function(){

});
</script>