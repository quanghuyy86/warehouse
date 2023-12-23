package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.service;

import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.common.dto.page.PageResponse;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.customer.CreateCustomerDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.customer.CustomerDetailDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.customer.CustomerParamFilterDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.customer.UpdateCustomerDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.Customer;

public interface CustomerService extends BaseService<Customer, Long> {
    PageResponse<CustomerDetailDto> categoriesList(CustomerParamFilterDto request);
    void createCustomer(CreateCustomerDto createCustomerDto);
    void updateCustomer(Long id, UpdateCustomerDto updateCustomerDto);
    CustomerDetailDto getCustomerById(Long id);
    void deleteCustomerById(Long id);
}
