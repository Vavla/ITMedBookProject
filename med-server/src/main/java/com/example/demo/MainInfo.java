package com.example.demo;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="maininfo")
public class MainInfo {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer id;
	
	public String Name;
	public Date DB;
	public String region;
	public String locality;
	public String adress;
	public Integer SNILS;
	public Integer OMS;
	public Boolean IsDoc;
	public String number;
	public String email;
	public int signForm_id;
	
}
