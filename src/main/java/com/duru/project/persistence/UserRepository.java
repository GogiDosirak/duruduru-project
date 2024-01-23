package com.duru.project.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.duru.project.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	Optional<User> findByUserid(String userid);


	Optional<User> findByEmailAndPhonenumber(String email, String phonenumber);

	Optional<User> findByUseridAndPhonenumberAndEmail(String userid, String phonenumber2, String email2);

}
