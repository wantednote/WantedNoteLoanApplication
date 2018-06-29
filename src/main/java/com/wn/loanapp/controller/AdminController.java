package com.wn.loanapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.wn.loanapp.common.BaseController;
import com.wn.loanapp.constants.Constants;
import com.wn.loanapp.exception.EmailAddressAlreadyExitsException;
import com.wn.loanapp.exception.UserOrPartnerNotFoundException;
import com.wn.loanapp.form.UserDetailsSessionForm;
import com.wn.loanapp.form.UserForm;
import com.wn.loanapp.service.UserLoginService;
import com.wn.loanapp.util.ActorRoleFormater;
import com.wn.loanapp.util.Format;

/**
 * Only here to test access restrictions for Spring Security.
 * 
 * @author Mithun Mondal
 *
 */
@Controller
//@RequestMapping(value = "/admin")
public class AdminController extends BaseController{

   @Autowired
   private UserLoginService userLoginService;
	
   //@RequestMapping(value={"/dashboard" , "/" ,"/home", "/index"})
   @RequestMapping(value="/admin" , method = RequestMethod.GET)
   public ModelAndView adminHome(HttpServletRequest request, UserDetailsSessionForm userDetailsSessionForm){
		log.debug("Start of method adminHome");
		ModelAndView modelAndView = null;
		try {
			if(Format.isStringNotEmptyAndNotNull(userDetailsSessionForm.getEmailAddress())){
				// here we are redirecting the user to the dashboard based on role.
				//modelAndView = dashboad(request , userDetailsSessionForm);
				modelAndView = new ModelAndView("adminDashboard");
				modelAndView.addObject("url", Constants.USER_TYPE_ADMIN);
				userDetailsSessionForm.setPageHeaderTitle(Constants.ADMIN_DASHBOARD_HEADER_TITLE);
				userDetailsSessionForm.setSelectedBaseLink(Constants.SELECTED_BASE_LINK_ADMIN_DASHBOARD);
				userDetailsSessionForm.setSelectedSubLink(Constants.SELECTED_BASE_LINK_ADMIN_DASHBOARD);
			}else{
				modelAndView = redirectToLoginPage(request.getServletPath());
			}
		} catch (Exception e) {
			log.error("exception occured : ",e);
			saveErrorMessage("Some Exception occured! Please try again");
			modelAndView = redirectToLoginPage(request.getServletPath());
		}
		log.debug("End of method adminHome()");
		addSuccessOrErrorMessageToModel(modelAndView);
		return modelAndView;
   }
   
   @RequestMapping(value="/profile" , method = RequestMethod.GET)
   public ModelAndView adminProfile(HttpServletRequest request, UserDetailsSessionForm userDetailsSessionForm){
		log.debug("Start of method adminProfile");
		ModelAndView modelAndView = null;
		try {
			if(Format.isStringNotEmptyAndNotNull(userDetailsSessionForm.getEmailAddress())){
				modelAndView = new ModelAndView("profile");
				modelAndView.addObject("url", Constants.USER_TYPE_ADMIN);
				userDetailsSessionForm.setPageHeaderTitle(Constants.ADMIN_VIEW_PROFILE_HEADER_TITLE);
				userDetailsSessionForm.setSelectedBaseLink(Constants.SELECTED_BASE_LINK_ADMIN_PROFILE);
				userDetailsSessionForm.setSelectedSubLink(Constants.SELECTED_BASE_LINK_ADMIN_PROFILE);
			}else{
				modelAndView = redirectToLoginPage(request.getServletPath());
			}
		} catch (Exception e) {
			log.error("exception occured : ",e);
			saveErrorMessage("Some Exception occured! Please try again");
			modelAndView = redirectToLoginPage(request.getServletPath());
		}
		log.debug("End of method adminProfile()");
		addSuccessOrErrorMessageToModel(modelAndView);
		return modelAndView;
	}
    
    @RequestMapping(value="profile", method=RequestMethod.POST)
	public ModelAndView updateProfile(UserDetailsSessionForm userDetailsSessionForm, UserForm userForm, RedirectAttributes redirectAttributes) {
		log.debug("Start of method updateProfile");
		ModelAndView modelAndView = null;
		String msg = null;
		if (Format.isStringNotEmptyAndNotNull(userDetailsSessionForm.getEmailAddress())) {
			modelAndView = new ModelAndView(new RedirectView("profile"));
			userForm.setEmailAddress(userDetailsSessionForm.getEmailAddress());	
			try {
				userLoginService.updateProfile(userForm);
				userDetailsSessionForm.setName(userForm.getName());
				saveSuccessMessage("You have successfully update your profile");
			} catch (UserOrPartnerNotFoundException e) {
				saveErrorMessage("We are unable to find your account");
			}
		}else {
			modelAndView = redirectToLoginPage("/");
		}
		userForm = null;
		addSuccessOrErrorMessageToModel(modelAndView, redirectAttributes);
		log.debug("End of method updateProfile");
		return modelAndView;
	}
    
    @RequestMapping(value="password", method=RequestMethod.POST)
	public ModelAndView changePassword(UserDetailsSessionForm userDetailsSessionForm, UserForm userForm, RedirectAttributes redirectAttributes) {
		log.debug("Start of method changePassword");
		ModelAndView modelAndView = null;
		String msg = null;
		if (Format.isStringNotEmptyAndNotNull(userDetailsSessionForm.getEmailAddress())) {
			modelAndView = new ModelAndView(new RedirectView("profile"));
			userForm.setEmailAddress(userDetailsSessionForm.getEmailAddress());	
			Boolean isValidUser = userLoginService.isValidUser(userForm);
			if(isValidUser) {
				try {
					userLoginService.changePassword(userForm);
					saveSuccessMessage("You have successfully changed your password");
				} catch (UserOrPartnerNotFoundException e) {
					saveErrorMessage("We are unable to find your account");
				}
			}else {
				saveErrorMessage("The existing password you have entered is currently not matcing with us");
			}
		}else {
			modelAndView = redirectToLoginPage("/");
		}
		userForm = null;
		addSuccessOrErrorMessageToModel(modelAndView, redirectAttributes);
		log.debug("End of method changePassword");
		return modelAndView;
	}
   
    /*@RequestMapping(value={"/admin/dashboard","/sadmin/dashboard","/productmgr/dashboard","/dsa/dashboard"})
	public ModelAndView dashboad(HttpServletRequest request , UserDetailsSessionForm userDetailsSessionForm) {
		log.debug("start of method dashboad");
		ModelAndView modelAndView = new ModelAndView("");;
		if(Format.isStringNotEmptyAndNotNull(adminDetailsSessionForm.getEmailAddress())){
			switch(adminDetailsSessionForm.getUserRole())
			{
				case Constants.ROLE_SADMIN : modelAndView = new ModelAndView("sadminDashboard");
					adminDetailsSessionForm.setSelectedBaseLink(Constants.SELECTED_BASE_LINK_SADMIN_DASHBOARD);
					modelAndView.addObject("url", "sadmin");
					break;
				case Constants.ROLE_TECH_ADMIN : modelAndView = new ModelAndView("techAdminDashboard");
					adminDetailsSessionForm.setSelectedBaseLink(Constants.SELECTED_BASE_LINK_TECH_ADMIN_DASHBOARD);
					modelAndView.addObject("url", "admin");
					break;
				case Constants.ROLE_PRODUCT_MANAGER : modelAndView = new ModelAndView("productManagerDashboard");
					adminDetailsSessionForm.setSelectedBaseLink(Constants.SELECTED_BASE_LINK_PRODUCT_MANAGER_DASHBOARD);
					ProductForm productForm = new ProductForm();
					productForm.setProductStatus(ProductStatusEnum.Pending.toString());
					productForm.setProductType(ProductTypeEnum.Loan.toString());
					Long pendingLoanProduct = productService.getProductCount(productForm);
					modelAndView.addObject("pendingLoanProduct", pendingLoanProduct);
					break;
				case Constants.ROLE_BUSINESS_PARTNER  : modelAndView = new ModelAndView("bpDashboard");
					adminDetailsSessionForm.setSelectedBaseLink(Constants.SELECTED_BASE_LINK_BP_DASHBOARD);
					break;	
				case Constants.ROLE_BUSINESS_DSA  : modelAndView = new ModelAndView("dsaDashboard");
					adminDetailsSessionForm.setSelectedBaseLink(Constants.SELECTED_SUB_LINK_DSA);
					break;
				case Constants.ROLE_SALES_STRING  : modelAndView = new ModelAndView("dsaDashboard");
					adminDetailsSessionForm.setSelectedBaseLink(Constants.SELECTED_LINK_SALES_DASHBOARD);
					modelAndView.addObject("url", "admin");
					break;	
					
				default : modelAndView =redirectToLoginPage(request.getServletPath());
			}
			List<LeadsStatus> leadStatus = CustomizeLeadStatus.modifyLeadsStatus();
			modelAndView.addObject("leadsStatusList", leadStatus);
			List<InsuranceType> insuranceTypes = insuranceTypeService.getAllInsuranceType();
			if(Format.isCollectionNotEmtyAndNotNull(insuranceTypes)){
				modelAndView.addObject("insuranceTypeList", insuranceTypes);
			}
		}else{
			modelAndView = redirectToLoginPage(request.getServletPath());
		}
		log.debug("end of method dashboad");
		return modelAndView;
	}*/
    
    /*@RequestMapping(value = "/greet/{name}", method = RequestMethod.GET)
    public String greet(@AuthenticationPrincipal UserDetails user, @PathVariable(value = "name") final String name, final Model model) {
        if (user == null) {
            throw new RuntimeException("Authentication error");
        }
        model.addAttribute("username", user.getUsername());
        model.addAttribute("name", name);
        log.info("The authenticated user '" + user.getUsername() + "' is masquarading as '" + name + "'.");
        return "site.admin.greet";
    }*/
}
