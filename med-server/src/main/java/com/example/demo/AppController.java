package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.net.*;
import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@RestController
public class AppController extends HttpServlet{

@Autowired
private UserRepository userRepository;

 @PostMapping("/user/logup")
 public List<SignFormat> getUser(@RequestParam String login,@RequestParam String password ) {
	 
	 System.out.println(login + " " + password);
	 return userRepository.findUser(login, password);
 }
 
 @PostMapping("/user/save")
 public int save(@RequestParam String login,@RequestParam String password ) {
	  if(userRepository.findUser(login, password).isEmpty()) {
		  userRepository.signUpUser(login,password);
		return userRepository.getId(login, password);
	  }
	  return 0;
	 
 }
 
@Autowired
private AccountRepository accRepository;

 @PostMapping("/user/found-acc")
 public List<MainInfo> getAcc(@RequestParam Integer signForm_id){
	return accRepository.findAccount(signForm_id);
 }
 @PostMapping("/user/save-infouser")
 public Boolean saveInfoOfUser(@RequestParam List<String> data){
     String Name = data.get(0);
     Date DB = null;
	try {
		DB = new SimpleDateFormat("dd.MM.yyyy").parse(data.get(1).toString());
	} catch (ParseException e) {
		e.printStackTrace();
	}
     String region = data.get(2);
     String locality = data.get(3);
     String adress = data.get(4);
     Long SNILS = Long.parseLong(data.get(5));
     Long OMS = Long.parseLong(data.get(6));
     Boolean IsDoc = Boolean.parseBoolean(data.get(7));
     String number = data.get(8);
     String email = data.get(9);
     Integer signForm_id = Integer.parseInt(data.get(10));
     
     accRepository.saveInfo(Name, DB, region, locality, adress, SNILS, OMS, IsDoc, number, email, signForm_id);
	 return true;
 }
 
 
 @Autowired
 private MedDataRepository medDataRepository;
 
 @PostMapping("user/get-medbook")
 public ArrayList<allMedRecords> InMedBook(@RequestParam String patientName,@RequestParam String patientDB){
	 Date PatientDB = null;
	try {
		PatientDB = new SimpleDateFormat("dd.MM.yyyy").parse(patientDB);
	} catch (ParseException e) {
		e.printStackTrace();
	}
	 return medDataRepository.getMedBook(patientName, PatientDB);}
 
 
}
 
//@GetMapping("/data")
// public List<MainInfo> getUser(HttpServletRequest req, HttpServletResponse res) {
//////	 Integer id = Integer.parseInt(req.getParameter("id"));
//////	 Optional<MainInfo> user = userRepository.findById(1);
////	 userRepository.findByEmail("jbmvhk.com")
//	 return userRepository.findByEmail("jbmvhk.com");
//}
//}

