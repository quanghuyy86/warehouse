package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.service;

import org.springframework.data.domain.Page;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.customer.CustomerDetailDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.customer.CreateCustomerDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.customer.UpdateCustomerDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.Customer;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.request.Request;

public interface CustomerService extends BaseService<Customer, Long> {
    Page<Customer> getListCustomer(Request request);
    void createCustomer(CreateCustomerDto createCustomerDto);
    void updateCustomer(Long id, UpdateCustomerDto updateCustomerDto);
    CustomerDetailDto getCustomerById(Long id);
    void deleteCustomerById(Long id);
}
