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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.wn.loanapp.constants.Constants;
import com.wn.loanapp.dto.UserDTO;
import com.wn.loanapp.enums.AccountStatusEnum;
import com.wn.loanapp.exception.EmailAddressAlreadyExitsException;
import com.wn.loanapp.form.UserForm;
import com.wn.loanapp.model.Partner;
import com.wn.loanapp.model.Role;
import com.wn.loanapp.model.User;
import com.wn.loanapp.repository.PartnerRepository;
import com.wn.loanapp.repository.RoleRepository;
import com.wn.loanapp.repository.UserRepository;
import com.wn.loanapp.service.UserLoginService;
import com.wn.loanapp.util.DateDifference;
import com.wn.loanapp.util.Format;

@Service(UserLoginServiceImpl.SERVICE_NAME)
public class UserLoginServiceImpl implements UserLoginService {

	public static final String SERVICE_NAME = "userLoginService";

	@Autowired
	private UserRepository userRepository;
	
	@Autowired PartnerRepository partnerRepository;
	
	@Autowired
    private RoleRepository roleRepository;
	
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public User findUserByEmail(String emailAddress) {
		return userRepository.findByEmail(emailAddress);
		//List<User> users = (List<User>) userRepository.findByEmail(userForm.getEmailAddress());
		//return users.get(Constants.GET_ZERO_INDEX_OBJECT);
	}

    @Override
	public User findUserByEmailAndAccountStatus(String emailAddress, AccountStatusEnum accountStatus) {
		return userRepository.findByEmailAndAccountStatus(emailAddress, accountStatus);
	}
    
    @Override
	public List<UserDTO> findAllByRoleId(int roleId) throws ParseException {
    	List<UserDTO> userDTOs = null;
    	List<Object> users = userRepository.findAllByRoleId(roleId);
    	if(Format.isCollectionNotEmtyAndNotNull(users)) {
    		userDTOs = new ArrayList<>();
    		Iterator<Object> itr = users.iterator();
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
    	}
    	return userDTOs;
	}
    
	@Override
	public void saveUser(UserForm userForm) throws EmailAddressAlreadyExitsException{
		User user = userRepository.findByEmail(userForm.getEmailAddress());
		if(Format.isObjectNotEmptyAndNotNull(user)) {
			user = null;
			throw new EmailAddressAlreadyExitsException();
		}else {
			Partner partner = partnerRepository.findByEmail(userForm.getEmailAddress());
			if(Format.isObjectNotEmptyAndNotNull(partner)) {
				partner = null;
				throw new EmailAddressAlreadyExitsException();
			}else {
				user = new User();
				user.setEmail(userForm.getEmailAddress());
				user.setName(userForm.getName());
				user.setPassword(bCryptPasswordEncoder.encode(userForm.getPassword()));
		        user.setAccountStatus(AccountStatusEnum.Active);
		        Role userRole = roleRepository.findByRole(Constants.ADMIN);
		        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		        user.setCreatedBy(userForm.getEmailAddress());
		        user.setCreatedOn(new Date());
				userRepository.save(user);
				user = null;
			}
		}
	}

	@Override
	public void updateUser(User user) {
		userRepository.save(user);
		user = null;
	}
	
	/*@Override
	public User findUserByEmail(UserForm userForm) {
		User user = new User();
		return user;
	}
	
	@Override
	public void userRegistration(UserForm userForm) {
		
	}*/
}
