package com.mockito.junittesting;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mockito.junittesting.controller.JunitController;
import com.mockito.junittesting.service.JunitServiceImpl;

@ExtendWith(MockitoExtension.class)
public class JunitApplicationController {

	
	
	
			@InjectMocks
			JunitController junitController;
	
	
			@Mock
			JunitServiceImpl service;
			
			
			
			  
		    @Before(value = "")
		    public void init() {
		        MockitoAnnotations.initMocks(this);
		    }
		 
			
			
			
	
	
	
}
