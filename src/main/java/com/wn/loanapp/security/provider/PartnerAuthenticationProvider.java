package com.wn.loanapp.security.provider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.wn.loanapp.enums.AccountStatusEnum;
import com.wn.loanapp.form.PartnerForm;
import com.wn.loanapp.model.Partner;
import com.wn.loanapp.model.PartnerRole;
import com.wn.loanapp.model.Role;
import com.wn.loanapp.service.PartnerLoginService;
import com.wn.loanapp.service.PartnerRoleService;
import com.wn.loanapp.service.RoleService;
import com.wn.loanapp.util.Format;

@Component
public class PartnerAuthenticationProvider implements AuthenticationProvider{

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	/**
	 * Instantiated Logger
	 */
	private Log log = LogFactory.getLog(this.getClass());
	
	/**
	 * Autowired partnerLoginService
	 */
	@Autowired
	private PartnerLoginService partnerLoginService;
	
	/**
	 * Autowired roleService
	 */
	@Autowired
	private RoleService roleService;
	
	/**
	 * Autowired userRoleService
	 */
	@Autowired
	private PartnerRoleService partnerRoleService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		log.debug("inside  authenticate in PartnerCustomAuthenticationProvider");
	
		PartnerForm partnerForm = new PartnerForm();
		partnerForm.setEmailAddress(authentication.getPrincipal().toString());
		partnerForm.setAccountStatus(AccountStatusEnum.Active.toString());
		Partner partner = partnerLoginService.findPartnerByEmail(partnerForm);
		if(partner == null){	
			throw new BadCredentialsException("User does not exists!");
		} else {
			if (bCryptPasswordEncoder.matches((String)authentication.getCredentials(), partner.getPassword())) {
			    //List<Role> roles = new ArrayList<Role>();
				PartnerRole partnerRole = partnerRoleService.findByPartnerId(Long.valueOf(partner.getId()));
				if(Format.isObjectNotEmptyAndNotNull(partnerRole)) {
					Role role = roleService.findById(Long.valueOf(partnerRole.getRoleId()));
					if(Format.isObjectNotEmptyAndNotNull(role)) {
						List<Role> roles = new ArrayList<>();
						roles.add(role);
						return new UsernamePasswordAuthenticationToken(authentication.getName(),
								authentication.getCredentials(),
								getAuthorities(roles));
					}else {
						throw new BadCredentialsException("Wrong Password!");
					}
				}else {
					throw new BadCredentialsException("Wrong Password!");
				}
			}else {
				throw new BadCredentialsException("Wrong Password!");
			}
	 }
	}

	/**
	 * Retrieves the correct ROLE type depending on the access level, where
	 * access level is an Integer. Basically, this interprets the access value
	 * whether it's for a regular user or admin. access an integer value
	 * representing the access of the user
	 * 
	 * @param roleList as List
	 * @return collection of granted authorities
	 */
	public Collection<GrantedAuthority> getAuthorities(List<Role> roles) {
		log.debug("Inside getAuthorities of PartnerCustomAuthenticationProvider");
		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(4);
		log.debug("roleList -------------> " + roles.size());
		if (roles != null) {
			for (Role role : roles) {
				String roleName = String.valueOf(role.getRole());
				log.debug("Role : " + roleName);
				authList.add(new SimpleGrantedAuthority(roleName));
			}
		}
		// Return list of granted authorities
		return authList;
		
	}
	
	
	//@Override
	public boolean supports(Class<?> authentication) {
		log.debug("Inside support()  PartnerCustomAuthenticationProvider");
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}


}
