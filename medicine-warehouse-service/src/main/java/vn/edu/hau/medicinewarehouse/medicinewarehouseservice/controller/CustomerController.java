package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.customer.CustomerDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.Customer;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.request.Request;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.service.CustomerService;

@RestController
@CrossOrigin("*")
public class CustomerController{
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    public ResponseEntity<Object> getListCustomers(Request request){
        Page<Customer> customerPage = this.customerService.getListCustomer(request);
        return new ResponseEntity<>(customerPage, HttpStatus.OK);
    }

    @PostMapping("/customers")
    public ResponseEntity<Object> createCustomer(@RequestBody @Validated CustomerDto customerDto){
        return new ResponseEntity<>(this.customerService.createCustomer(customerDto), HttpStatus.CREATED);
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<Object> getCustomerById(@PathVariable Long id){
        return new ResponseEntity<>(this.customerService.getCustomerById(id), HttpStatus.OK);
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<Object> updateCustomerById(@PathVariable Long id,
                                                     @Validated @RequestBody CustomerDto customerDto){
        return new ResponseEntity<>(this.customerService.updateCustomer(id, customerDto), HttpStatus.OK);
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Long id){
        return new ResponseEntity<>(this.customerService.deleteCustomerById(id), HttpStatus.OK);
    }
}
