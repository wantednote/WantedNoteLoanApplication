<%@ include file="/common/taglibs.jsp"%>

<script src="assets/js/jquery-2.1.1.js"></script>
<div class="loginColumns animated fadeInDown">
    <div class="row">
        <div class="col-md-6">
        	<h2>Admin Registration</h2>
            <div class="ibox-content" style="box-shadow: 0 1px 1px 0 #ccc;">
                <form class="m-t" role="form" action="registration" id="userForm"  method="post">
                	<input type="hidden" name="userType" value="Admin">
                    <div class="form-group">
                        <input type="text" class="form-control" name="name" placeholder="Name" required>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="emailAddress" placeholder="Email Address">
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" name="password" placeholder="Password">
                    </div>
                    <button type="submit" class="btn btn-primary block full-width m-b">Register</button>

                    <a href="admin">
                        <small>Already Registered?</small>
                    </a>
                    <!-- <a class="btn btn-sm btn-white btn-block" href="register.html">Create an account</a> -->
                </form>
                <!-- <p class="m-t">
                    <small>Inspinia we app framework base on Bootstrap 3 &copy; 2014</small>
                </p> -->
            </div>
        </div>
        
        <div class="col-md-6">
        	<h2>Partner Registration</h2>
            <div class="ibox-content" style="box-shadow: 0 1px 1px 0 #ccc;">
                <form class="m-t" role="form" action="registration" id="userForm"  method="post">
                	<input type="hidden" name="userType" value="Partner">
                    <div class="form-group">
                        <input type="text" class="form-control" name="name" placeholder="Name" required>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="emailAddress" placeholder="Email Address">
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" name="password" placeholder="Password">
                    </div>
                    <button type="submit" class="btn btn-primary block full-width m-b">Register</button>

                    <a href="partner">
                        <small>Already Registered?</small>
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
<script>
      $(document).ready(function(){

          $("#loginForm").validate({
              rules: {
            	  j_password: {
                      required: true,
                      minlength: 3
                  }/* ,
                  url: {
                      required: true,
                      url: true
                  },
                  number: {
                      required: true,
                      number: true
                  },
                  min: {
                      required: true,
                      minlength: 6
                  },
                  max: {
                      required: true,
                      maxlength: 4
                  } */
              }
          });
          
          /* window.setInterval(function(){
        	  incremental();
        	  flash();
          }, 600000); */
      });
      function flash(){
    	  location.href="flash.note";
      }
      function incremental(){
    	  location.href="incremental.note";
      }
 </script>