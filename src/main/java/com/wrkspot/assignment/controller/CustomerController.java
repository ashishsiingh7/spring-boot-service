package com.wrkspot.assignment.controller;

import com.wrkspot.assignment.model.Customer;
import com.wrkspot.assignment.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/")
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "customer", method = RequestMethod.POST)
    public ResponseEntity<Customer> create(@RequestBody Customer customer) {
        Customer createdCustomer = customerService.createCustomer(customer);
        logger.info("Created customer with ID {}: {}", createdCustomer.getCustomerId(), createdCustomer);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
    }

    @RequestMapping(value = "search", method = RequestMethod.GET)
    public ResponseEntity<List<Customer>> findCustomer(@RequestParam(required = false) String name,
                                                       @RequestParam(required = false) String city,
                                                       @RequestParam(required = false) String state) {
        logger.info("Fetching customers with name '{}', state '{}', and city '{}'", name, state, city);
        List<Customer> customers = customerService.findByNameAndCityAndState(name,city,state);
        return ResponseEntity.status(HttpStatus.OK).body(customers);
    }

}
