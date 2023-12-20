package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.common.response.ApiResponse;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.common.response.ApiResponseCode;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.common.response.ApiResponseGenerator;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.customer.CustomerDetailDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.customer.CreateCustomerDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.customer.UpdateCustomerDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.Customer;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.request.Request;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.service.CustomerService;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
@Slf4j
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ApiResponse<Page<Customer>>> getListCustomers(Request request) {
        Page<Customer> customerPage = this.customerService.getListCustomer(request);
        return new ResponseEntity<>(ApiResponseGenerator.success(ApiResponseCode.SUCCESS, "get list customer", customerPage), HttpStatus.OK);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ApiResponse<Void>> createCustomer(@RequestBody @Validated CreateCustomerDto createCustomerDto) {
        this.customerService.createCustomer(createCustomerDto);
        return new ResponseEntity<>(ApiResponseGenerator.success(ApiResponseCode.CREATED, "Create customers"), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ApiResponse<CustomerDetailDto>> getCustomerById(@PathVariable Long id) {
        CustomerDetailDto customerDetailDto = this.customerService.getCustomerById(id);
        return new ResponseEntity<>(ApiResponseGenerator.success(ApiResponseCode.SUCCESS, "get by id customer", customerDetailDto), HttpStatus.OK);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ApiResponse<Void>> updateCustomerById(@PathVariable Long id,
                                                                @Validated @RequestBody UpdateCustomerDto updateCustomerDto) {
        this.customerService.updateCustomer(id, updateCustomerDto);
        return new ResponseEntity<>(ApiResponseGenerator.fail(ApiResponseCode.SUCCESS, "Update customer"), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<ApiResponse<Void>> deleteById(@PathVariable Long id) {
        this.customerService.deleteCustomerById(id);
        return new ResponseEntity<>(ApiResponseGenerator.success(ApiResponseCode.NO_CONTENT, "delete customer"), HttpStatus.OK);
    }
}
