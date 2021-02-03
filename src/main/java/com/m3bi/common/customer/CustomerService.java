package com.m3bi.common.customer;

import org.springframework.stereotype.Service;

import com.m3bi.common.exception.NotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {

	private final CustomerRepository customerRepository;

	public Customer find(Integer id) {
		return customerRepository.findOne(id).orElseThrow(() -> new NotFoundException(
				String.format("customer with id %d is not found or it has been deleted", id)));
	}

	public Customer update(Customer customer) {

		return customerRepository.save(customer);

	}

}
