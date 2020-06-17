package com.mockito.junittesting.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mockito.junittesting.model.User;

@Repository
public interface UserRespository extends JpaRepository<User,Long>
{

	
	List<User> findByName(String name);

	List<User> findBysurname(String surname);
	
	@Query(value = "SELECT * FROM User u WHERE u.dob =?1 and u.doj=?2 order by dob",nativeQuery = true)
	List<User> findbydobanddoj(@Param("dob") LocalDate dob, @Param("doj") LocalDate doj);

	
}
