package com.wn.loanapp.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.wn.loanapp.common.BaseController;
import com.wn.loanapp.constants.Constants;
import com.wn.loanapp.dto.UserDTO;
import com.wn.loanapp.form.RoleForm;
import com.wn.loanapp.form.UserDetailsSessionForm;
import com.wn.loanapp.model.Role;
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
    public ModelAndView gerister(HttpServletRequest request, UserDetailsSessionForm userDetailsSessionForm, @RequestParam(required=false) String id) {
		ModelAndView modelAndView = null;
		if(Format.isStringNotEmptyAndNotNull(userDetailsSessionForm.getEmailAddress())) {
			modelAndView = new ModelAndView("viewActors");
			userDetailsSessionForm.setPageHeaderTitle(Constants.ADMIN_VIEW_ACTOR_HEADER_TITLE);
			userDetailsSessionForm.setSelectedBaseLink(Constants.SELECTED_BASE_LINK_ADMIN_VIEW_ACTOR);
			userDetailsSessionForm.setSelectedSubLink(Constants.SELECTED_BASE_LINK_ADMIN_VIEW_ACTOR);
			try {
				createModelAndView(modelAndView, id);
			} catch (ParseException e) {
				saveErrorMessage("Something went wrong");
			}
		}else {
			modelAndView = redirectToLoginPage(request.getServletPath());
		}
        return modelAndView;
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
	private void createModelAndView(ModelAndView modelAndView, String roleId) throws ParseException {
		modelAndView.addObject("url", Constants.USER_TYPE_ADMIN);
		String currentRole = null;
		Boolean flag = true;
		List<Role> roles = roleService.findAll();
		if(Format.isCollectionNotEmtyAndNotNull(roles)) {
			for(int i=0; i<roles.size(); i++) {
				Role role = roles.get(i);
				if(Format.isStringEmptyOrNull(roleId)) {
					currentRole = roles.get(Constants.GET_ZERO_INDEX_OBJECT).getRole();
					flag = false;
				}else {
					if(role.getId() == Integer.parseInt(roleId)) {
						currentRole = role.getRole();
						flag = true;
					}
				}
				String roleString = ActorRoleFormater.getFormatedActorRole(role.getRole());
				role.setRole(roleString);
			}
		}else {
			roles = new ArrayList<>();
			currentRole = "";
		}
		
		List<UserDTO> users = null;
		Integer userCount = null;
		if(Format.isCollectionNotEmtyAndNotNull(roles)) {
			if(flag) {
				if(currentRole.equals(Constants.PARTNER)) {
					users = partnerLoginService.findAllByRoleId(Integer.parseInt(roleId));
				}else {
					users = userLoginService.findAllByRoleId(Integer.parseInt(roleId));
				}
			}else {
				if(currentRole.equals(ActorRoleFormater.getFormatedActorRole(Constants.PARTNER))) {
					users = partnerLoginService.findAllByRoleId(roles.get(Constants.GET_ZERO_INDEX_OBJECT).getId());
				}else {
					users = userLoginService.findAllByRoleId(roles.get(Constants.GET_ZERO_INDEX_OBJECT).getId());
				}
			}
			if(Format.isCollectionEmtyOrNull(users)) {
				users = new ArrayList<>();
				userCount = Constants.GET_ZERO_INDEX_OBJECT;
			}else {
				userCount = users.size();
			}
		}else {
			users = new ArrayList<>();
			userCount = Constants.GET_ZERO_INDEX_OBJECT;
		}
		
		modelAndView.addObject("roles", roles);
		if(flag) {
			modelAndView.addObject("currentRole", ActorRoleFormater.getFormatedActorRole(currentRole));
		}else {
			modelAndView.addObject("currentRole", currentRole);
		}
		modelAndView.addObject("users", users);
		modelAndView.addObject("userCount", userCount);
	}
}
