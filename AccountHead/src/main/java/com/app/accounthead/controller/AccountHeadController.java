package com.app.accounthead.controller;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.app.controller.EnquiryDetailscontroller;
import com.app.exceptions.HandleCustomException;
import com.app.model.CustomerLoanApplication;
import com.app.model.LoanDisbursement;

@RestController
@RequestMapping("/app")
public class AccountHeadController {
	@Autowired
	RestTemplate rs;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EnquiryDetailscontroller.class);

	@GetMapping("/api/accounthead/getallsactioneddata")
	public List<CustomerLoanApplication> getAllSactionedData() {
		LOGGER.info("Received GET request to fetch all Customerloanapplication Form whose loanStatus is Santioned");
		String url = "http://localhost:8080/app/api/getAllSanctionedData";
		CustomerLoanApplication [] listofAllSactioned = rs.getForObject(url, CustomerLoanApplication[].class);
		List<CustomerLoanApplication> listofAllSactionedData = Arrays.asList(listofAllSactioned);
		LOGGER.debug("Fetched {} Customerloanapplication Form successfully whose loanStatus is Santioned", listofAllSactionedData.size());
		return listofAllSactionedData;
	}

	@PutMapping("/api/accounthead/getloandisbursement/{customerLoanId}")
	public ResponseEntity<CustomerLoanApplication> getLoanDisbursement(@RequestBody LoanDisbursement loanDisb,
		@PathVariable int customerLoanId) {
		LOGGER.info("Received PUT request for CustomerLoanApplication  with customerLoanID: {}", customerLoanId);
		
				loanDisb.setLoanNo(customerLoanId);
				List<CustomerLoanApplication> cla = getAllSactionedData();
				for(CustomerLoanApplication custLoanApp : cla) {
					if(custLoanApp.getCustomerLoanID()==customerLoanId) {
						custLoanApp.setLoandisbursement(loanDisb);
						LOGGER.info("Received PUT request for CustomerLoanApplication  with customerLoanID: {}", customerLoanId);
						String url = "http://localhost:8080/app/api/Loandisbursement/"+customerLoanId;
						rs.put(url, custLoanApp);
						return new ResponseEntity<CustomerLoanApplication>(custLoanApp, HttpStatus.ACCEPTED);
					}
				}
				throw new HandleCustomException("CustomerLoanApplication data for given customerLoanId is not present");
				
	}
}
