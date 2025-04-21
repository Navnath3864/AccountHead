package com.app.accounthead.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.app.model.CustomerLoanApplication;
import com.app.model.LoanDisbursement;
@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("/app")
public class AccountHeadController {
	@Autowired
	RestTemplate rs;
	

	@GetMapping("/api/accounthead/getallsactioneddata")
	public List<CustomerLoanApplication> getAllSactionedData() {
		String url = "http://localhost:8080/app/api/getAllSanctionedData";
		CustomerLoanApplication [] listofAllSactioned = rs.getForObject(url, CustomerLoanApplication[].class);
		List<CustomerLoanApplication> listofAllSactionedData = Arrays.asList(listofAllSactioned);
		return listofAllSactionedData;
	}

	@PutMapping("/api/accounthead/getloandisbursement/{customerLoanId}")
	public ResponseEntity<LoanDisbursement> getLoanDisbursement(@RequestBody LoanDisbursement loanDisb,
		@PathVariable int customerLoanId) {
				loanDisb.setLoanNo(customerLoanId);
			//	List<CustomerLoanApplication> cla = getAllSactionedData();
			//	for(CustomerLoanApplication custLoanApp : cla) {
			//		if(custLoanApp.getCustomerLoanID()==customerLoanId) {
			//			custLoanApp.setLoandisbursement(loanDisb);
						String url = "http://localhost:8080/app/api/Loandisbursement/"+customerLoanId;
						rs.put(url, loanDisb);
						return new ResponseEntity<LoanDisbursement>(loanDisb, HttpStatus.OK);
					
	}
}
