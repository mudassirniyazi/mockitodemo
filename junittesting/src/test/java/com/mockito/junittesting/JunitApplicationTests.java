package com.mockito.junittesting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.Month;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.mockito.junittesting.model.User;
import com.mockito.junittesting.repository.UserRespository;
import com.mockito.junittesting.service.JunitServiceImpl;


@RunWith(SpringRunner.class)
@SpringBootTest
public class JunitApplicationTests {

	
	@Autowired
	private JunitServiceImpl userservice;
	
	
	@MockBean
	private UserRespository userrepository;
	
		// get All the user Details

		@Test
		public void getAllUserDetails() {
		
		//Current Date
				LocalDate today = LocalDate.now();
				System.out.println("Current Date="+today);
				
				//Creating LocalDate by providing input arguments
				LocalDate jan = LocalDate.of(2019, Month.JANUARY, 1);
				System.out.println("Specific Date="+jan);
				
		when(userrepository.findAll()).thenReturn(Stream.of(new User(1,"c","c","i@gmail.com","12345678910",today,jan, "12"),new User(2,"d","d","d@gmail.com","123",today,jan, "11")).collect(Collectors.toList()));		
		assertEquals(2, userservice.getUsers().size());
		}


		// get the User name 
		
		@Test
		public void geUserName() {
		String name = "a";	
		LocalDate today = LocalDate.now();
		System.out.println("Current Date="+today);
		
		//Creating LocalDate by providing input arguments
		LocalDate jan = LocalDate.of(2019, Month.JANUARY, 1);
		System.out.println("Specific Date="+jan);
		when(userrepository.findByName(name)).thenReturn(Stream.of(new User(13,"a","aa","a@yahoo.com","12234", today,jan,"09")).collect(Collectors.toList()));	
		assertEquals(1,userservice.findByUserName(name).size());
		}

		// get the User Surname
	
		@Test
		public void geUserSurName() {
		String surname = "ccc";	
		LocalDate dob= null;
		LocalDate doj = null;
		when(userrepository.findBysurname(surname)).thenReturn(Stream.of(new User(13,"","","","", dob, doj,"")).collect(Collectors.toList()));	
		assertEquals(1,userservice.findByUserSurName(surname).size());
		
		}

	
	
		@Test
		public void deleteUser() {
			LocalDate dob=null;
			LocalDate doj= null;
			User user = new User(1,"a","aa","a@gmail.com","123", dob, doj,"007");
			userservice.deleteUser(user);
			verify(userrepository,times(1)).delete(user);
		}
		
	
//			@Test
//			public void createUser() {
//				LocalDate dob=null;
//				LocalDate doj=null;
//				User user = new User(1,"first","name","f1@gmail.com","12345678910", dob, doj,"444");		
//				when(userrepository.save(user)).thenReturn(user);
//				assertEquals(user,userservice.save(user));		
//			}


	@Test
	void contextLoads() {
	}

}
