package com.example.demo;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface UserRepository extends JpaRepository<SignFormat, Integer>{
	

    
	@Query(value="SELECT l FROM SignFormat l where l.login = ?1 and l.password = ?2")
	public List<SignFormat> findUser(String login,String password);

	@Transactional
	@Modifying
	@Query(value="INSERT INTO info_medbook.signform (login,password) VALUES (:login,:password)", nativeQuery = true)
	public void signUpUser( String login,String password);
	
	@Query(value="SELECT l.id FROM SignFormat l where l.login = ?1 and l.password = ?2")
	public int getId(String login,String password);
}

