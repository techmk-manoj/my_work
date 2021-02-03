package com.m3bi.api.customer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.m3bi.common.customer.Customer;
import com.m3bi.common.customer.CustomerService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class CustomerController {

	private final CustomerService customerService;

	@GetMapping("{id}")
	public Customer find(@PathVariable Integer id) {
		return customerService.find(id);
	}

	@PostMapping("/add-user-points")
	public Customer update(@RequestParam Integer id, @RequestParam double points) {

		Customer customer = customerService.find(id);
		customer.setPoints(customer.getPoints() + points);
		return customerService.update(customer);

	}

}
