package com.example.demo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.repository.Modifying;

import javax.persistence.Entity;


@Entity
@Table(name = "signform")
public class SignFormat {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer id;
	
    public String login;
    public String password;
}
