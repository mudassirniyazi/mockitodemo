package com.mockito.junittesting.dto;

import java.time.LocalDate;

	
	public class Userdto {

			//("name")
		    private String name;

			//("surname")
		    private String surname;
		        
			//("email")
		    private String email;

			//("phone")		 
		    private String phone;

			//("dob")
		    private LocalDate dob;

			//("doj")
		    private LocalDate doj;
		    
		    
		    private String pincode;
		    
		    public String getPincode() {
				return pincode;
			}

			public void setPincode(String pincode) {
				this.pincode = pincode;
			}

			public LocalDate getDoj() {
				return doj;
			}

			public void setDoj(LocalDate doj) {
				this.doj = doj;
			}

			public String getName() {
				return name;
			}

			public void setName(String name) {
				this.name = name;
			}

			public String getSurname() {
				return surname;
			}

			public void setSurname(String surname) {
				this.surname = surname;
			}

			public String getEmail() {
				return email;
			}

			public void setEmail(String email) {
				this.email = email;
			}

			public String getPhone() {
				return phone;
			}

			public void setPhone(String phone) {
				this.phone = phone;
			}

			public LocalDate getDob() {
				return dob;
			}

			public void setDob(LocalDate dob) {
				this.dob = dob;
			}


	}
