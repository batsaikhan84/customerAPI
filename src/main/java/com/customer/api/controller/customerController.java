package com.customer.api.controller;

import com.customer.api.Repository.CustomerRepository;
import com.customer.api.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class customerController {
    @Value("$spring.application.name")
    String appName;
    @Autowired
    public CustomerRepository customerRepository;
    @GetMapping("/")
    public String homepage(Model model) {
        model.addAttribute("appName", appName);
        return "home";
    }
    @GetMapping("/customers")
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }
    @PostMapping("/customers")
    public Customer addCustomer(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

//    @GetMapping("/customers/{id}")
//    public ResponseEntity<Customer> getCustomer() {
//        Customer customer = customerRepository.findById(id)
//    }
}
