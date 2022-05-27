package com.example.demo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MedDataRepository extends JpaRepository<allMedRecords, Integer> {
	
	@Query(value="SELECT a from allMedRecords a where a.patientName = ?1 and a.patientDB = ?2")
	public ArrayList<allMedRecords> getMedBook(String patientName,Date PatientDB);

}
