package com.m3bi.common.customer;

import java.util.List;

import com.m3bi.common.jpa.Jpa8Repository;

public interface CustomerRepository extends Jpa8Repository<Customer, Integer> {
	
	List<Customer> findAll();
}
