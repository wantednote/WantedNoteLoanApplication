package com.wn.loanapp.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.wn.loanapp.constants.Constants;
import com.wn.loanapp.dto.UserDTO;
import com.wn.loanapp.enums.AccountStatusEnum;
import com.wn.loanapp.exception.EmailAddressAlreadyExitsException;
import com.wn.loanapp.exception.UserOrPartnerNotFoundException;
import com.wn.loanapp.form.PartnerForm;
import com.wn.loanapp.form.RoleForm;
import com.wn.loanapp.form.UserForm;
import com.wn.loanapp.model.Partner;
import com.wn.loanapp.model.PartnerRole;
import com.wn.loanapp.model.Role;
import com.wn.loanapp.model.User;
import com.wn.loanapp.model.UserRole;
import com.wn.loanapp.repository.PartnerRepository;
import com.wn.loanapp.repository.PartnerRoleRepository;
import com.wn.loanapp.repository.RoleRepository;
import com.wn.loanapp.repository.UserRepository;
import com.wn.loanapp.service.PartnerLoginService;
import com.wn.loanapp.util.DateDifference;
import com.wn.loanapp.util.Format;

@Service(PartnerLoginServiceImpl.SERVICE_NAME)
public class PartnerLoginServiceImpl implements PartnerLoginService{

	public static final String SERVICE_NAME = "partnerLoginService";

	/**
	 * @Autowired partnerRepository
	 */
	@Autowired
	private PartnerRepository partnerRepository;
	
	/**
	 * @Autowired userRepository
	 */
	@Autowired
	private UserRepository userRepository;
	
	/**
	 * @Autowired roleRepository
	 */
	@Autowired
    private RoleRepository roleRepository;
	
	/**
	 * @Autowired partnerRoleRepository
	 */
	@Autowired
	private PartnerRoleRepository partnerRoleRepository;
	/**
	 * @Autowired sessionRegistry
	 */
	@Autowired
	private SessionRegistry sessionRegistry;
	
	/**
	 * @Autowired bCryptPasswordEncoder
	 */
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    

    @Override
	public Partner getPartner(PartnerForm partnerForm) {
		return partnerRepository.getPartner(partnerForm);
	}
	
	@Override
	public List<UserDTO> findAllByRoleId(long roleId) throws ParseException {
		List<UserDTO> userDTOs = null;
    	/*List<Object> partners = partnerRepository.findAllByRoleId(roleId);
    	if(Format.isCollectionNotEmtyAndNotNull(partners)) {
    		userDTOs = new ArrayList<>();
    		Iterator<Object> itr = partners.iterator();
        	while(itr.hasNext()){
        	   Object[] obj = (Object[]) itr.next();
        	   UserDTO userDTO = new UserDTO();
        	   userDTO.setId(String.valueOf(obj[0]));
        	   userDTO.setName(String.valueOf(obj[1]));
        	   userDTO.setEmail(String.valueOf(obj[2]));
        	   userDTO.setAccountStatus(String.valueOf(obj[3]));
        	   
        	   String dateString = String.valueOf(obj[4]);
        	   if(!dateString.equals(Constants.STRING_NULL)){
            	   Date lastLogin = new SimpleDateFormat("dd-MM-yyyy").parse(dateString);
            	   userDTO.setLastLogin(DateDifference.getDaysDiff(lastLogin));
        	   }else {
        		   userDTO.setLastLogin("Never Loged In");
        	   }
        	   
        	   String modifiedOn = String.valueOf(obj[5]);
        	   if(!dateString.equals(Constants.STRING_NULL)){
        		   userDTO.setModifiedOn(modifiedOn);
        	   }else {
        		   userDTO.setModifiedOn("Not Modified");
        	   }
        	   
        	   userDTO.setModifiedBy(String.valueOf(obj[6]));
        	   userDTOs.add(userDTO);
        	}
    	}*/
    	return userDTOs;
	}
	
	@Override
	public void addPartner(UserForm userForm) throws EmailAddressAlreadyExitsException{
		PartnerForm partnerForm = new PartnerForm();
		partnerForm.setEmailAddress(userForm.getEmailAddress());
		Partner partner = partnerRepository.getPartner(partnerForm);
		if(Format.isObjectNotEmptyAndNotNull(partner)) {
			partner = null;
			throw new EmailAddressAlreadyExitsException();
		}else {
			User user = userRepository.getUser(userForm);
			if(Format.isObjectNotEmptyAndNotNull(user)) {
				user = null;
				throw new EmailAddressAlreadyExitsException();
			}else {
				partner = new Partner();
				partner.setEmail(userForm.getEmailAddress());
				partner.setName(userForm.getName());
				partner.setPassword(bCryptPasswordEncoder.encode(userForm.getPassword()));
		        partner.setAccountStatus(AccountStatusEnum.Active);
		        RoleForm roleForm = new RoleForm();
		        roleForm.setRoleName(userForm.getRoleName());
		        Role role = roleRepository.getRole(roleForm);
		        partner.setRoles(new HashSet<Role>(Arrays.asList(role)));
		        partner.setCreatedBy(userForm.getEmailAddress());
		        partner.setCreatedOn(Format.getCurrentSqlDateTimeStamp());
				partnerRepository.saveOrUpdate(partner);
				partner = null;
			}
		}
	}

	@Override
	public void updatePartner(Partner partner) {
		partnerRepository.update(partner);
		partner = null;
	}

	@Override
	public void updateAccountStatus(UserForm userForm) throws UserOrPartnerNotFoundException {
		PartnerForm partnerForm = new PartnerForm();
		partnerForm.setEmailAddress(userForm.getEmailAddress());
		Partner partner = partnerRepository.getPartner(partnerForm);
		if(Format.isObjectNotEmptyAndNotNull(partner)) {
			if(userForm.getAccountStatus().toString().equals(AccountStatusEnum.Active.toString())) {
				partner.setAccountStatus(AccountStatusEnum.Active);
			}else if(userForm.getAccountStatus().toString().equals(AccountStatusEnum.Pending.toString())) {
				partner.setAccountStatus(AccountStatusEnum.Pending);
			}else if(userForm.getAccountStatus().toString().equals(AccountStatusEnum.Blocked.toString())) {
				partner.setAccountStatus(AccountStatusEnum.Blocked);
			}else if(userForm.getAccountStatus().toString().equals(AccountStatusEnum.Deleted.toString())) {
				partner.setAccountStatus(AccountStatusEnum.Deleted);
			}
			partner.setModifiedOn(Format.getCurrentSqlDateTimeStamp());
			if(Format.isStringNotEmptyAndNotNull(userForm.getEmailAddress())) {
				partner.setModifiedBy(userForm.getEmailAddress());
			}else {
				partner.setModifiedBy("System");
			}
			partnerRepository.update(partner);
			if(!userForm.getAccountStatus().toString().equals(AccountStatusEnum.Active.toString())) {
				expireSession(partner.getSessionID());
			}
			partner = null;
		}else {
			throw new UserOrPartnerNotFoundException();
		}
	}
	
	/**This method marks the session for expiration
	 * @author mithun Mondal
	 * @param sessionId
	  */
	private void  expireSession(String sessionId){
		if(sessionId!=null){
			SessionInformation sessionInformation=sessionRegistry.getSessionInformation(sessionId);
			if(sessionInformation!=null) {
				sessionInformation.expireNow(); // marks the session for expiration
			}
		}
	}
	
}
