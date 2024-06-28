package com.wrkspot.assignment.service.impl;

import com.wrkspot.assignment.model.Customer;
import com.wrkspot.assignment.repository.CustomerRepository;
import com.wrkspot.assignment.service.CustomerService;
import com.wrkspot.assignment.service.spec.CustomerSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private KafkaTemplate<String, Customer> kafkaTemplate;

    @Override
    public Customer createCustomer(Customer customer) {
        Customer createdCustomer = customerRepository.save(customer);
        logger.info("Sending customer info to kafka {}", createdCustomer);
        try{
            kafkaTemplate.send("customer", createdCustomer);
        }catch(Exception e){
            logger.error(e.getMessage(), e.getClass());
        }
        return createdCustomer;

    }

    @Override
    public List<Customer> findByNameAndCityAndState(String name, String city, String state) {
        return customerRepository.findAll(CustomerSpecification.searchCustomers(name, city, state));
    }
}
