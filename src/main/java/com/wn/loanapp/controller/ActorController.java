package com.wn.loanapp.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.wn.loanapp.common.BaseController;
import com.wn.loanapp.constants.Constants;
import com.wn.loanapp.dto.DatatableJsonResponse;
import com.wn.loanapp.dto.RoleDTO;
import com.wn.loanapp.dto.UserDTO;
import com.wn.loanapp.exception.EmailAddressAlreadyExitsException;
import com.wn.loanapp.exception.UserOrPartnerNotFoundException;
import com.wn.loanapp.form.PartnerForm;
import com.wn.loanapp.form.RoleForm;
import com.wn.loanapp.form.UserDetailsSessionForm;
import com.wn.loanapp.form.UserForm;
import com.wn.loanapp.model.Partner;
import com.wn.loanapp.model.Role;
import com.wn.loanapp.model.User;
import com.wn.loanapp.service.PartnerLoginService;
import com.wn.loanapp.service.RoleService;
import com.wn.loanapp.service.UserLoginService;
import com.wn.loanapp.util.ActorRoleFormater;
import com.wn.loanapp.util.Format;

@Controller
//@RequestMapping(value = "/admin")
public class ActorController extends BaseController{
	
	@Autowired
	private UserLoginService userLoginService;
	
	@Autowired
	private PartnerLoginService partnerLoginService;
	
	@Autowired
	private RoleService roleService;

	@RequestMapping(value="/actors" , method = RequestMethod.GET)
	//@RequestMapping(value={"/actors" , "/actors/"} , method = RequestMethod.GET)
    public ModelAndView getActors(HttpServletRequest request, UserDetailsSessionForm userDetailsSessionForm/*, @RequestParam(required=false) String id*/) {
		ModelAndView modelAndView = null;
		if(Format.isStringNotEmptyAndNotNull(userDetailsSessionForm.getEmailAddress())) {
			modelAndView = new ModelAndView("viewActors");
			userDetailsSessionForm.setPageHeaderTitle(Constants.ADMIN_VIEW_ACTOR_HEADER_TITLE);
			userDetailsSessionForm.setSelectedBaseLink(Constants.SELECTED_BASE_LINK_ADMIN_VIEW_ACTOR);
			userDetailsSessionForm.setSelectedSubLink(Constants.SELECTED_BASE_LINK_ADMIN_VIEW_ACTOR);
			try {
				List<RoleDTO> roleDTOs = null;
				RoleForm roleForm = new RoleForm();
				List<Role> roles = roleService.getRoles(roleForm);
				if(Format.isCollectionEmtyOrNull(roles)) {
					roleDTOs = new ArrayList<>();
				}else {
					roleDTOs = new ArrayList<>();
					modelAndView.addObject("currentRoleCaps", roles.get(Constants.GET_ZERO_INDEX_OBJECT).getRole());
					modelAndView.addObject("currentRoleSmall", ActorRoleFormater.getFormatedActorRole(roles.get(Constants.GET_ZERO_INDEX_OBJECT).getRole()));
					modelAndView.addObject("currentRoleId", roles.get(Constants.GET_ZERO_INDEX_OBJECT).getId());
					for(Role role:roles) {
						RoleDTO roleDTO = new RoleDTO();
						roleDTO.setId(role.getId());
						roleDTO.setRole(role.getRole());
						roleDTO.setRoleName(ActorRoleFormater.getFormatedActorRole(role.getRole()));
						roleDTOs.add(roleDTO);
					}
				}
				modelAndView.addObject("roles", roleDTOs);
			} catch (Exception e) {
				saveErrorMessage("Something went wrong");
			}
		}else {
			modelAndView = redirectToLoginPage(request.getServletPath());
		}
        return modelAndView;
    }
	
	
	
	@RequestMapping(value="actors", method=RequestMethod.POST)
	public ModelAndView addActors(UserDetailsSessionForm userDetailsSessionForm, UserForm userForm, RedirectAttributes redirectAttributes) {
		log.debug("Start of method addRole");
		ModelAndView modelAndView = null;
		String msg = null;
		if (Format.isStringNotEmptyAndNotNull(userDetailsSessionForm.getEmailAddress())) {
			modelAndView = new ModelAndView(new RedirectView("actors"));
			try {
        		if(userForm.getRoleName().equals(Constants.PARTNER))
        			partnerLoginService.addPartner(userForm);
            	else 
            		userLoginService.saveUser(userForm);
        		msg = userForm.getName() + " added successfully as " + ActorRoleFormater.getFormatedActorRole(userForm.getRoleName());
        		saveSuccessMessage(msg);
        	}catch (EmailAddressAlreadyExitsException e) {
				msg = getText("error.email.exist");
				saveErrorMessage(msg);
			}catch (Exception e) {
				msg = getText("error.something.went.wrong");
				saveErrorMessage(msg);
			}
			addSuccessOrErrorMessageToModel(modelAndView, redirectAttributes);
		} else {
			modelAndView = redirectToLoginPage("/");
		}
		log.debug("End of method addRole");
		return modelAndView;
	}
	
	@RequestMapping(value="actorList", method=RequestMethod.POST)
	public @ResponseBody DatatableJsonResponse getActorList(UserForm userForm) {
		List<UserDTO> userDTOs = null;
		Integer count = 0;
		DatatableJsonResponse datatableJsonResponse = new DatatableJsonResponse();
		if(Format.isStringNotEmptyAndNotNull(userForm.getRoleId())) {
			try {
				RoleForm roleForm = new RoleForm();
				roleForm.setId(Long.parseLong(userForm.getRoleId()));
				Role role = roleService.getRole(roleForm);
				if(role.getRole().equals(Constants.PARTNER)) {
					userDTOs = partnerLoginService.findAllByRoleId(role.getId());
				}else {
					userDTOs = userLoginService.findAllByRoleId(userForm);
				}
				if(Format.isCollectionEmtyOrNull(userDTOs)) {
					userDTOs = new ArrayList<>();
				}else {
					count = userDTOs.size();
				}
			} catch (NumberFormatException | ParseException e) {
				userDTOs = new ArrayList<>();
			}
		}else {
			datatableJsonResponse.setData(new ArrayList<>());
		}
		datatableJsonResponse.setData(userDTOs);
		datatableJsonResponse.setRecordsTotal(count);
		datatableJsonResponse.setRecordsFiltered(count);
		return datatableJsonResponse;
	}
	
	@RequestMapping(value="/actors/{id}" , method = RequestMethod.GET)
    public ModelAndView getActorDetails(HttpServletRequest request, UserDetailsSessionForm userDetailsSessionForm, @PathVariable("id") long id) {
		ModelAndView modelAndView = null;
		Boolean flag = false;
		User user = null;
		Partner partner = null;
		if(Format.isStringNotEmptyAndNotNull(userDetailsSessionForm.getEmailAddress())) {
			if(Format.isNotNull(id)) {
				UserForm userForm = new UserForm();
				userForm.setId(id);
				user = userLoginService.getUser(userForm);
				if(Format.isObjectNotEmptyAndNotNull(user)) {
					flag = true;
				}else {
					PartnerForm partnerForm = new PartnerForm();
					partnerForm.setId(id);
					partner = partnerLoginService.getPartner(partnerForm);
					if(Format.isObjectNotEmptyAndNotNull(partner)) {
						flag = true;
					}
				}
			}
		}
		if(flag) {
			modelAndView = new ModelAndView("viewActorDetails");
			String name = null;
			if(Format.isObjectNotEmptyAndNotNull(user)) {
				name = user.getName();
				modelAndView.addObject("userType", Constants.ADMIN);
				modelAndView.addObject("user", user);
			}else {
				name = partner.getName();
				modelAndView.addObject("userType", Constants.PARTNER);
				modelAndView.addObject("user", partner);
			}
			userDetailsSessionForm.setPageHeaderTitle(name + " Profile");
			userDetailsSessionForm.setSelectedBaseLink(Constants.SELECTED_BASE_LINK_ADMIN_VIEW_ACTOR);
			userDetailsSessionForm.setSelectedSubLink(Constants.SELECTED_BASE_LINK_ADMIN_VIEW_ACTOR);
		}else {
			modelAndView = redirectToLoginPage(request.getServletPath());
		}
        return modelAndView;
    }
	
	@RequestMapping(value="updateAccountStatus", method=RequestMethod.POST)
	public @ResponseBody UserForm updateAccountStatus(UserDetailsSessionForm userDetailsSessionForm, @RequestBody UserForm userForm) {
		String successOrErrorMessage = null;
		String messageType = null;
		try {
			userForm.setModifiedBy(userDetailsSessionForm.getEmailAddress());
			if(userForm.getRoleName().equals(Constants.PARTNER)) {
				partnerLoginService.updateAccountStatus(userForm);
			}else {
				userLoginService.updateAccountStatus(userForm);
			}
			successOrErrorMessage = ActorRoleFormater.getFormatedActorRole(userForm.getRoleName()) 
					+ " email address such as " + userForm.getEmailAddress() 
					+ " has been " + userForm.getAccountStatus() 
					+ " successfully";
			messageType = Constants.SUCCESS;
		}catch (UserOrPartnerNotFoundException e) {
			successOrErrorMessage = ActorRoleFormater.getFormatedActorRole(userForm.getRoleName())
					+ " email address such as " + userForm.getEmailAddress()
					+ " does't exist";
			messageType = Constants.FAILURE;
		}catch (Exception e) {
			System.out.println("Exception " + e);
			successOrErrorMessage = getText("error.something.went.wrong");
			messageType = Constants.FAILURE;
		}
		userForm.setSuccessOrErrorMessage(successOrErrorMessage);
		userForm.setMessageType(messageType);
		userForm.setId(0);
		userForm.setEmailAddress(null);
		userForm.setRoleName(null);
		userForm.setAccountStatus(null);
		userForm.setModifiedBy(null);
		return userForm;
	}
	
	@RequestMapping(value="role", method=RequestMethod.POST)
	public ModelAndView saveOrUpdateSub(UserDetailsSessionForm userDetailsSessionForm, RoleForm roleForm, RedirectAttributes redirectAttributes) {
		log.debug("Start of method addSubject");
		ModelAndView modelAndView = null;
		String msg = null;
		if (Format.isStringNotEmptyAndNotNull(userDetailsSessionForm.getEmailAddress())) {
			try {
				modelAndView = new ModelAndView(new RedirectView("actors"));
				roleService.addRole(roleForm.getRoleName());
				//msg = getText("success.subject.add");
				msg = "Role Added Successfully";
				saveSuccessMessage(msg);
			} catch (Exception e) {
				log.error("Exception Occurred : " , e);
				msg = getText("error.common.tryLater");
				saveErrorMessage(msg);
			}
			addSuccessOrErrorMessageToModel(modelAndView, redirectAttributes);
		} else {
			modelAndView = redirectToLoginPage("/");
		}
		log.debug("End of method addSubject");
		return modelAndView;
	
	}
}
