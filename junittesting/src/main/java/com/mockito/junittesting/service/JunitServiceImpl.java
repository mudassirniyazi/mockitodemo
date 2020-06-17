package com.mockito.junittesting.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mockito.junittesting.model.User;
import com.mockito.junittesting.repository.UserRespository;

@Service
public class JunitServiceImpl {

	static Logger logger = LoggerFactory.getLogger(JunitServiceImpl.class);
	
	@Autowired
	UserRespository userrepository;
	
	   
		public void save(User user)
		{
			logger.info("Inside Save || JunitServiceImpl.");
			userrepository.save(user);
			logger.info("End of Save || JunitServiceImpl..");
		}
	
	
		public Optional<User> updateUser(long id){
			 Optional<User>  userdata = userrepository.findById(id);						 
			 if (userdata.isPresent()) {
				 return userdata;
			 } 
			return userdata;
		}
		
		
		
		public List<User> findByUserName(String name) {
			logger.info("FIND BY USERNAME",name);
			List<User> userlist = userrepository.findByName(name);
				if(userlist.size()>0)
				{
					return userlist;
				}			
			return userlist;			
		}
		
		
		
	public List<User> findByUserSurName(String surname){
		logger.info("FIND BY USERNAME",surname);
		List<User> user = userrepository.findBysurname(surname);
			if(user.size()>0)
			{
			return user;
			}			
		return user;	
		
	}


	public List<User> findUserByDOBandDOJ(LocalDate dob, LocalDate doj) {

		List<User> listuser = userrepository.findbydobanddoj(dob,doj);
			if(listuser.size()>0) {
				return listuser;
			}
		
		return listuser;
	}


		public Optional<User> findByUserId(long id) {
			Optional<User> user = userrepository.findById(id);
			return user;
		}

		
		public void delete(long id)
		{
			userrepository.deleteById(id);
		}
		
		
		public List<User> getUsers(){
			List<User> listuser = userrepository.findAll();
			if(listuser.size()>0) {
					return listuser;
			}else {
				return listuser;
			}
		}


		public void deleteUser(User user) {
			userrepository.delete(user);			
		}
		
		
		
		
		
}
