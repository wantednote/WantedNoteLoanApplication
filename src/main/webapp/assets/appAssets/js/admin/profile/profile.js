$(document).ready(function() {
	
	$('#updateProfile').formValidation({
        message: 'This value is not valid',
        icon: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
        	name: {
        		validators: {
        			notEmpty: {
                        message: 'Please enter your name here'
                    }
                }
            }
        }
    });
	
	$('#changePassword').formValidation({
        message: 'This value is not valid',
        icon: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
        	password: {
        		validators: {
        			notEmpty: {
                        message: 'Please enter your current password'
                    }
                }
            },
            newPassword: {
                validators: {
                    notEmpty: {
                        message: 'Please provide an new password'
                    }
                }
            },
            confirmPassword: {
                validators: {
                    notEmpty: {
                        message: 'The confirm password is required and can\'t be empty'
                    },
                    identical: {
                        field: 'newPassword',
                        message: 'The password and its confirm are not the same'
                    }
                }
            }
        }
    });
	
});