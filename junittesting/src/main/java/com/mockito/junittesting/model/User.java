package com.mockito.junittesting.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class User {
     
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
     
    @NotBlank(message = "Name is mandatory")
    private String name;
     
    @NotBlank(message = "SurName is mandatory")
    private String surname;
        
    @NotBlank(message = "Email is mandatory")
    @Size(min=10,max=15,message = "Email id should be greater than 10 and less than 15")
   // @Email    
    private String email;

 
    @NotEmpty(message = "Phone number is required")
    @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Mobile number is invalid")
    private String phone;
     
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dob;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate doj;
    
    
    @NotBlank(message="PinCode should not be blank")
    private String pincode;
    
    
    
	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public LocalDate getDoj() {
		return doj;
	}

	public void setDoj(LocalDate doj) {
		this.doj = doj;
	}
	
	public User() {
		
	}

	public User(long id, @NotBlank(message = "Name is mandatory") String name,
			@NotBlank(message = "SurName is mandatory") String surname,
			@NotBlank(message = "Email is mandatory") @Size(min = 10, max = 15, message = "Email id should be greater than 10 and less than 15") String email,
			@NotEmpty(message = "Phone number is required") @Pattern(regexp = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message = "Mobile number is invalid") String phone,
			LocalDate dob, LocalDate doj, @NotBlank(message = "PinCode should not be blank") String pincode) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.phone = phone;
		this.dob = dob;
		this.doj = doj;
		this.pincode = pincode;
	}



}