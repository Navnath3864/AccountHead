package com.app.accounthead.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.app.model.CustomerLoanApplication;

@RestController
@RequestMapping("/app")
public class AccountHeadController {
	@Autowired
	RestTemplate rs;
	
	@GetMapping("/api/accounthead/getallsactioneddata")
	public ResponseEntity<List<CustomerLoanApplication>> getAllSactionedData(){
		String url = "http://localhost:8080/app/api/getAllSanctionedData";
		List<CustomerLoanApplication> listofAllSactionedData=rs.getForObject(url, List.class);
		return new ResponseEntity<List<CustomerLoanApplication>>(listofAllSactionedData,HttpStatus.OK);
	}
}
