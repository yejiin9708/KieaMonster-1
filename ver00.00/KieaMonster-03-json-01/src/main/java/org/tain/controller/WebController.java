package org.tain.controller;

import java.util.Arrays;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tain.model.Company;
import org.tain.model.Product;

@RestController
public class WebController {

	@GetMapping("/getcompany")
	public Company getCompany(){
		Company company = new Company(1, "Apple",
				// list of products
				Arrays.asList(
						new Product("A-123", "Iphone 7", "Price: 649.00 USD & FREE shipping"),
						new Product("A-456", "IPadPro", "Price: 417.67 USD & FREE shipping")
				)
		);
		
		System.out.println(company.toString());
		
		return company;
	}
}
