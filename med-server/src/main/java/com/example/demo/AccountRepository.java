package com.example.demo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface AccountRepository extends JpaRepository<MainInfo, Integer>{
	
	@Query(value="Select a from MainInfo a where a.signForm_id = ?1")
	public List<MainInfo> findAccount(Integer signForm_id);
	
	@Transactional
	@Modifying
	@Query(value="INSERT INTO info_medbook.maininfo (Name,DB,region,locality,adress,SNILS,OMS,IsDoc,number,email,signForm_id) VALUES (:Name,:DB,:region,:locality,:adress,:SNILS,:OMS,:IsDoc,:number,:email,:signForm_id)", nativeQuery = true)
	public void saveInfo( String Name,Date DB,String region,String locality,String adress,Long SNILS,Long OMS,Boolean IsDoc,String number,String email,Integer signForm_id);

}
