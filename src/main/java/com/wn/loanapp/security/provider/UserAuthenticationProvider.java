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
import com.wn.loanapp.form.RoleForm;
import com.wn.loanapp.form.UserForm;
import com.wn.loanapp.model.Role;
import com.wn.loanapp.model.User;
import com.wn.loanapp.model.UserRole;
import com.wn.loanapp.service.RoleService;
import com.wn.loanapp.service.UserLoginService;
import com.wn.loanapp.service.UserRoleService;
import com.wn.loanapp.util.Format;

@Component
public class UserAuthenticationProvider implements AuthenticationProvider {

	/**
	 *  Instantiated of passwordEncoder
	 */
	//private Md5PasswordEncoder passwordEncoder = new Md5PasswordEncoder();
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	/**
	 * Instantiated Logger
	 */
	private Log log = LogFactory.getLog(this.getClass());
	
	/**
	 * Autowired userLoginService
	 */
	@Autowired
	private UserLoginService userLoginService;
	
	/**
	 * Autowired roleService
	 */
	@Autowired
	private RoleService roleService;
	
	/**
	 * Autowired userRoleService
	 */
	@Autowired
	private UserRoleService userRoleService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		log.debug("inside  authenticate in CustomAuthenticationProvider");
		
		/*UserForm userForm = new UserForm();
		userForm.setEmailAddress(authentication.getPrincipal().toString());
		userForm.setAccountStatus(AccountStatusEnum.Active.toString());
		User user = userLoginService.findUserByEmail(userForm);*/
		UserForm userForm = new UserForm();
		userForm.setEmailAddress(authentication.getPrincipal().toString());
		userForm.setAccountStatus(AccountStatusEnum.Active);
		User user = userLoginService.getUser(userForm);
		if(user == null){	
			throw new BadCredentialsException("User does not exists!");
		} else {
			//if(passwordEncoder.isPasswordValid(emp.getPassword(), (String)authentication.getCredentials(), null) == true){
			/*if(bCryptPasswordEncoder.matches(user.getPassword(), (String)authentication.getCredentials()) == true){
				List<Role> roles = new ArrayList<Role>();
				roles.add((Role) user.getRoles());
				return new UsernamePasswordAuthenticationToken(authentication.getName(),
						authentication.getCredentials(),
						getAuthorities(roles));
			}else {
				throw new BadCredentialsException("Wrong Password!");
			}*/
			
			/*String existingPassword = bCryptPasswordEncoder.encode((String)authentication.getCredentials());
			String dbPassword = bCryptPasswordEncoder.encode(user.getPassword());
*/
			if (bCryptPasswordEncoder.matches((String)authentication.getCredentials(), user.getPassword())) {
			    //List<Role> roles = new ArrayList<Role>();
				UserRole userRole = userRoleService.findByUserId(Long.valueOf(user.getId()));
				if(Format.isObjectNotEmptyAndNotNull(userRole)) {
					RoleForm roleForm = new RoleForm();
					roleForm.setId(Long.valueOf(userRole.getRoleId()));
					Role role = roleService.getRole(roleForm);
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
		log.debug("Inside getAuthorities of CustomAuthenticationManager");
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
		log.debug("Inside support() UserCustomAuthenticationProvider");
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}


	
}
