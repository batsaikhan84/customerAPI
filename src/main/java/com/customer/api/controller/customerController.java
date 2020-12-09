package com.customer.api.controller;

import com.customer.api.Repository.CustomerRepository;
import com.customer.api.exception.RecordNotFoundException;
import com.customer.api.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Page<Customer> getCustomers(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }
//    @PostMapping("/customers")
//    public Customer addCustomer(@RequestBody Customer customer) {
//        return customerRepository.save(customer);
//    }
    @GetMapping("/customers")
    public String getCustomers(Model model) {
        List<Customer> customerList = new ArrayList<>();
        model.addAttribute("customers", customerList);
        return "customerList";
    }
    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable(value = "id") long id) throws RecordNotFoundException {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("ID: " + id + " customer not found!"));
        return ResponseEntity.ok().body(customer);
    }
    @DeleteMapping("/customers/{id}")
    public Map<String, String> deleteCustomer(@PathVariable(value = "id") long id) throws RecordNotFoundException {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Employee not found by ID: " + id));
        customerRepository.delete(customer);
        Map<String, String> deleteCustomer = new HashMap<>();
        deleteCustomer.put("Person is deleted", "by id: " + id);
        return deleteCustomer;
    }
    @PutMapping("/customers/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable(value = "id")long id, @RequestBody Customer customerDetails) throws RecordNotFoundException {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("ID " + id + " not found"));
        customer.setName(customerDetails.getName());
        customer.setEmail(customerDetails.getEmail());
        final Customer updateCustomer = customerRepository.save(customer);
        return ResponseEntity.ok(updateCustomer);
    }
}
