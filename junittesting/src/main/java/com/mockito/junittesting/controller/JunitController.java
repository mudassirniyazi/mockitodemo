package com.mockito.junittesting.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mockito.junittesting.constant.BasicConstant;
import com.mockito.junittesting.dto.Userdto;
import com.mockito.junittesting.model.User;
import com.mockito.junittesting.service.JunitServiceImpl;

@RestController
public class JunitController extends BasicConstant {

	static Logger logger = LoggerFactory.getLogger(JunitController.class);

	@Autowired
	JunitServiceImpl userservice;

	// User Data save api
	@PostMapping("/user")
	public ResponseEntity<Userdto> createUser(@Valid @RequestBody User user) {
		logger.info("starts || CreateUser");
		Userdto dto = null;
		try {
			dto = new Userdto();
			userservice.save(user);
			dto.setName(user.getName());
			dto.setSurname(user.getSurname());
			dto.setEmail(user.getEmail());
			dto.setPhone(user.getPhone());
			dto.setDob(user.getDob());
			dto.setDoj(user.getDoj());
			dto.setPincode(user.getPincode());
		} catch (Exception e) {
			logger.info("Exception", e);
		}
		logger.info("Ends || CreateUser");
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	// User data update api
	@SuppressWarnings("rawtypes")
	@PutMapping("/update/{id}")
	public ResponseEntity<Map> updateUserdetails(@PathVariable("id") long id, @RequestBody User user) {
		logger.debug("inside || userupdate");
		HashMap<String, String> map = null;
		Optional<User> userdata = null;
		try {
			logger.debug("inside try || userupdate");
			userdata = userservice.updateUser(id);
			if (userdata.isPresent()) {
				User user_data = userdata.get();
				user_data.setName(user.getName());
				user_data.setSurname(user.getSurname());
				user_data.setEmail(user.getEmail());
				user_data.setPhone(user.getPhone());
				user_data.setDob(user.getDob());
				user_data.setDoj(user.getDoj());
				user_data.setPincode(user.getPincode());
				userservice.save(user_data);
				map = new HashMap<>();
				map.put(BasicConstant.sucess, BasicConstant.update);
				logger.debug("ends || userupdate");
				return new ResponseEntity<>(map, HttpStatus.OK);
			} else {
				map = new HashMap<>();
				map.put(BasicConstant.Error, BasicConstant.idnotfound);
				return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			logger.info("Exception", e);
		}
		return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
	}

	// Search by Name

	@GetMapping("/findbyName/{name}")
	public ResponseEntity<Map<String, List<User>>> findByUserName(@PathVariable("name") String name) {
		logger.debug("inside || findByUserName");
		HashMap<String, List<User>> map = null;
		try {
			List<User> userdata = userservice.findByUserName(name);
			if (userdata.size() > 0) {
				map = new HashMap<>();
				map.put(BasicConstant.sucess, userdata);
				logger.debug("inside try ends|| findByUserName", map);
				return new ResponseEntity<>(map, HttpStatus.OK);
			} else {
				map = new HashMap<>();
				map.put(BasicConstant.nodata, userdata);
				logger.debug("inside try ends|| findByUserName", map);
				return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			logger.info("Exception", e);
		}
		map = new HashMap<>();
		return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
	}

	// Search By Surname

	@GetMapping("/findbySurName/{surname}")
	public ResponseEntity<Map<String, List<User>>> findbySurName(@PathVariable("surname") String surname) {
		logger.debug("inside || findByUserName");
		HashMap<String, List<User>> map = null;
		try {
			List<User> userdata = userservice.findByUserSurName(surname);
			if (userdata.size() > 0) {
				map = new HashMap<>();
				map.put(BasicConstant.sucess, userdata);
				logger.debug("inside try || findByUserName", map);
				return new ResponseEntity<>(map, HttpStatus.OK);
			} else {
				map = new HashMap<>();
				map.put(BasicConstant.nodata, userdata);
				return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			logger.debug("Exception", e);
		}
		return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
	}

	// Sort By DOB and DOJ

	@GetMapping("/sortByDOBandDOJ")
	public ResponseEntity<Map<String, List<User>>> sortUserByDOBandDOJ(@RequestParam("dob") String dob,
			@RequestParam("doj") String doj) {
		logger.debug("inside || sortUserByDOBandDOJ");
		List<User> userlist = null;
		HashMap<String, List<User>> map = null;
		try {
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate dob_convert = LocalDate.parse(dob, dateTimeFormatter); // String to LocalDate
			LocalDate doj_convert = LocalDate.parse(doj, dateTimeFormatter); // String to LocalDate
			userlist = userservice.findUserByDOBandDOJ(dob_convert, doj_convert);
			if (userlist.size() > 0) {
				Collections.sort(userlist, Collections.reverseOrder());
				map = new HashMap<>();
				map.put(BasicConstant.sucess, userlist);
				return new ResponseEntity<>(map, HttpStatus.OK);
			} else {
				map = new HashMap<>();
				map.put(BasicConstant.emptydata, userlist);
				return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			logger.debug("Exception", e);
			map = new HashMap<>();
			map.put(BasicConstant.failure, userlist);
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
	}

	// Delete the User based on id

	@DeleteMapping("delete/{id}")
	public ResponseEntity<String> deletebyid(@PathVariable("id") int id) {
		logger.info("Deleting user with id: {}", id);
		Optional<User> user = userservice.findByUserId(id);

		if (!user.isPresent()) {
			return new ResponseEntity<>(BasicConstant.idnotfound, HttpStatus.NOT_FOUND);
		} else {
			userservice.delete(id);
			return new ResponseEntity<>(BasicConstant.iddelete, HttpStatus.OK);
		}

	}

	// Retreive the user details

	@GetMapping("/alluser")
	public List<User> getAllUserDetails() {
		logger.info("Getting all user details");
		List<User> userlist = userservice.getUsers();

		if (userlist.size() > 0) {
			return userlist;
		} else {
			return userlist;
		}
	}

	// Delete the User
	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteUser(@RequestBody User user) {
		userservice.deleteUser(user);
		return new ResponseEntity<>(BasicConstant.delete, HttpStatus.OK);
	}

}

