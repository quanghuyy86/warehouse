package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.customer.CustomerDetailDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.customer.CustomerDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.Customer;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.request.Request;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.service.CustomerService;

@RestController
@RequestMapping("customer")
public class CustomerController extends BaseController<Customer, Long> {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        super(customerService);
        this.customerService = customerService;
    }

    @GetMapping("get-list-customer")
    public ResponseEntity<Object> getListCustomers(Request request){
        Page<Customer> customerPage = this.customerService.getListCustomer(request);
        return new ResponseEntity<>(customerPage, HttpStatus.OK);
    }

    @PostMapping("create-customer")
    public ResponseEntity<Object> createCustomer(@RequestBody @Validated CustomerDto customerDto){
        return new ResponseEntity<>(this.customerService.createOrUpdateCustomer(customerDto, null), HttpStatus.CREATED);
    }

    @GetMapping("get-customer/{id}")
    public ResponseEntity<Object> getCustomerById(@PathVariable Long id){
        return new ResponseEntity<>(this.customerService.getCustomerById(id), HttpStatus.OK);
    }

    @PutMapping("update-customer/{id}")
    public ResponseEntity<Object> updateCustomerById(@PathVariable Long id, @RequestBody @Validated CustomerDto customerDto){
        return new ResponseEntity<>(this.customerService.createOrUpdateCustomer(customerDto, id), HttpStatus.OK);
    }

    @DeleteMapping("delete-customer/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Long id){
        return new ResponseEntity<>(this.customerService.deleteById(id), HttpStatus.OK);
    }
}
