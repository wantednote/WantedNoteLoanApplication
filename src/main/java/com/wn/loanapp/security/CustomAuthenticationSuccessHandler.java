package com.wn.loanapp.security;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import com.wn.loanapp.constants.Constants;
import com.wn.loanapp.form.PartnerForm;
import com.wn.loanapp.form.UserDetailsSessionForm;
import com.wn.loanapp.form.UserForm;
import com.wn.loanapp.model.Partner;
import com.wn.loanapp.model.User;
import com.wn.loanapp.service.PartnerLoginService;
import com.wn.loanapp.service.UserLoginService;
import com.wn.loanapp.util.ActorRoleFormater;
import com.wn.loanapp.util.StringGeneratorUtil;

public class CustomAuthenticationSuccessHandler extends
		SavedRequestAwareAuthenticationSuccessHandler {
	
	private Log log = LogFactory.getLog(this.getClass());

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Autowired
	private UserLoginService userLoginService;
	
	@Autowired
	private PartnerLoginService partnerLoginService;
	
	private RequestCache requestCache = new HttpSessionRequestCache();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		
		onAuthenticationSuccessPrivate(request, response, authentication);

		// set the user details in the Session for future use
		final String currentUser = authentication.getName();
		log.debug("Current user  : " + currentUser);
		@SuppressWarnings("unchecked")
		List<GrantedAuthority> authorityList = (List<GrantedAuthority>) authentication.getAuthorities();
		String roleName = authorityList.get(0).getAuthority();
		UserDetailsSessionForm userDetailsSessionForm = (UserDetailsSessionForm) request.getSession(false).getAttribute("userDetailsSessionForm");
		if (userDetailsSessionForm == null || userDetailsSessionForm.getEmailAddress() == null) {
			userDetailsSessionForm = new UserDetailsSessionForm();
		}
		if(roleName.equals(Constants.PARTNER)){
			PartnerForm partnerForm = new PartnerForm();
			partnerForm.setEmailAddress(currentUser);
			Partner partner = partnerLoginService.getPartner(partnerForm);
			partner.setSessionID(request.getSession(false).getId());
			if(com.wn.loanapp.util.Format.isObjectNotEmptyAndNotNull(partner)){
				String loginToken = null;
				if(com.wn.loanapp.util.Format.isStringEmptyOrNull(partner.getLoginToken())){
					loginToken = StringGeneratorUtil.generateString(Constants.PIN_MIN_LENGTH_ADMIN, Constants.PIN_MAX_LENGTH_ADMIN, Constants.PIN_NO_OF_ALPHABETS_ADMIN, Constants.PIN_NO_OF_DIGITS_ADMIN, Constants.PIN_NO_OF_SPECIALCHAR);
					partner.setLoginToken(loginToken);
				}else{
					loginToken = partner.getLoginToken();
				}
				//Updating partner login token and session id
				partnerLoginService.updatePartner(partner);
				userDetailsSessionForm.setName(partner.getName());
				userDetailsSessionForm.setLoginToken(loginToken);
			}
		}else if(roleName.equals(Constants.ADMIN)){
			UserForm userForm = new UserForm();
			userForm.setEmailAddress(currentUser);
			User user = userLoginService.getUser(userForm);
			user.setSessionID(request.getSession(false).getId());
			if(com.wn.loanapp.util.Format.isObjectNotEmptyAndNotNull(user)){
				String loginToken = null;
				if(com.wn.loanapp.util.Format.isStringEmptyOrNull(user.getLoginToken())){
					loginToken = StringGeneratorUtil.generateString(Constants.PIN_MIN_LENGTH_ADMIN, Constants.PIN_MAX_LENGTH_ADMIN, Constants.PIN_NO_OF_ALPHABETS_ADMIN, Constants.PIN_NO_OF_DIGITS_ADMIN, Constants.PIN_NO_OF_SPECIALCHAR);
					user.setLoginToken(loginToken);
				}else{
					loginToken = user.getLoginToken();
				}
				//Updating user login token and session id
				userLoginService.updateUser(user);
				userDetailsSessionForm.setName(user.getName());
				userDetailsSessionForm.setLoginToken(loginToken);
				/*userDetailsSessionForm.setProfileImageUrl(employee.getPhoto());
				userDetailsSessionForm.setUserId(employee.getEmployeeLoginId().toString());
				userDetailsSessionForm.setEmployeePin(employee.getEmployeePin());
				userDetailsSessionForm.setSalesRole(employee.getSalesType());*/
			}
		}
		userDetailsSessionForm.setEmailAddress(currentUser);
		userDetailsSessionForm.setUserRole(roleName);
		userDetailsSessionForm.setUserRoleName(ActorRoleFormater.getFormatedActorRole(roleName));
		/*userDetailsSessionForm.setUserRoleName(ConverterUtil.covertRoleToString(roleName));
		userDetailsSessionForm.setEmployeeBaseUrl(ConverterUtil.setEmploueeBaseUrl(roleName));
		List<InsuranceType> insuranceTypes = insuranceTypeService.getAllInsuranceType();
		if(Format.isCollectionNotEmtyAndNotNull(insuranceTypes)){
			adminDetailsSessionForm.setInsuranceTypeList(insuranceTypes);
		}*/
		request.getSession(false).setAttribute("userDetailsSessionForm",userDetailsSessionForm);

	}

	private void onAuthenticationSuccessPrivate(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws ServletException, IOException {
		String targetUrlParameter  = null;
		
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		
		if (savedRequest == null) {
			 targetUrlParameter = determineTargetUrl(authentication);
		}else{
			targetUrlParameter = savedRequest.getRedirectUrl();
			
		}
		/*if (isAlwaysUseDefaultTargetUrl()
				|| (targetUrlParameter != null && StringUtils.hasText(request
						.getParameter(targetUrlParameter)))) {
			requestCache.removeRequest(request, response);
			super.onAuthenticationSuccess(request, response, authentication);

		return;
		}*/
		clearAuthenticationAttributes(request);
		logger.debug("Redirecting to Url: " + targetUrlParameter);
		getRedirectStrategy().sendRedirect(request, response, targetUrlParameter);
	}

	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}

	protected RedirectStrategy getRedirectStrategy() {
		return redirectStrategy;
	}

	  /** Builds the target URL according to the logic defined in the main class Javadoc. */
    protected String determineTargetUrl(Authentication authentication) {
        boolean isPartner = false;
        boolean isAdmin = false;
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
        	String role = grantedAuthority.getAuthority();
            if (role.equals(Constants.PARTNER)) {
                isPartner= true;
                break;
            } else if (role.equals(Constants.ADMIN))
            {
                isAdmin = true;
                break;
            }
        }
        if (isPartner) {
            return "/"+ Constants.SELECTED_BASE_LINK_PARTNER_DASHBOARD +"/";
        } else if (isAdmin) {
            return "/" + Constants.SELECTED_BASE_LINK_ADMIN_DASHBOARD +"/";
        } else {
            throw new IllegalStateException();
        }
    }
}
