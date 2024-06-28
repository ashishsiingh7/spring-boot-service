package com.wrkspot.assignment.service;

import com.wrkspot.assignment.model.Customer;
import java.util.List;

public interface CustomerService {
    Customer createCustomer(Customer customer);

    List<Customer> findByNameAndCityAndState(String name, String city, String state);
}
