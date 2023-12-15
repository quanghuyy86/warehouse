package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.service;

import org.springframework.data.domain.Page;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.category.CategoryDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.customer.CustomerDetailDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.customer.CustomerDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.Customer;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.request.Request;

public interface CustomerService extends BaseService<Customer, Long> {
    Page<Customer> getListCustomer(Request request);
    Customer createCustomer(CustomerDto customerDto);
    Customer updateCustomer(Long id,CustomerDto customerDto);
    CustomerDetailDto getCustomerById(Long id);
    Boolean deleteCustomerById(Long id);
}
