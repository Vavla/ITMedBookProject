package com.example.demo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="allmedrecords")
public class allMedRecords {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer id;
	
	public Date dateRecord;
	public String hospital;
	public String typeRecord;
	public String patientName;
	public Date patientDB;
	public String appeal;
	public String diagnosis;
	public String doctor;
	

}
