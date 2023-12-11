package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.exception.ResourceNotFoundException;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.customer.CustomerDetailDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.customer.CustomerDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.Customer;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.request.Request;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.repository.CustomerRepository;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.service.CustomerService;

import java.util.List;

@Service
public class CustomerServiceImpl extends BaseServiceImpl<Customer, Long> implements CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        super(customerRepository);
        this.customerRepository = customerRepository;
    }

    public List<Customer> searchCustomerByName(String keyword){
        return this.customerRepository.findByFullNameContaining(keyword);
    }

    @Override
    public Page<Customer> getListCustomer(Request request) {
        List<Customer> list = this.searchCustomerByName(request.getKeyword());
        Pageable pageable = PageRequest.of(request.getPage(), request.getPageSize());
        return new PageImpl<Customer>(list, pageable, this.searchCustomerByName(request.getKeyword()).size());
    }

    @Override
    public Customer createOrUpdateCustomer(CustomerDto customerDto, Long id) {
        if(id != null && id > 0){
            Customer customer = this.customerRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Not found with id = " + id));
            customer.setFullName(customerDto.getFullName());
            customer.setPhone(customerDto.getPhone());
            customer.setEmail(customerDto.getEmail());
            customer.setAddress(customerDto.getAddress());
            customer.setNote(customerDto.getNote());
            return this.customerRepository.save(customer);
        }else {
            Customer customer = new Customer();
            customer.setFullName(customerDto.getFullName());
            customer.setPhone(customerDto.getPhone());
            customer.setEmail(customerDto.getEmail());
            customer.setAddress(customerDto.getAddress());
            customer.setNote(customerDto.getNote());
            return this.customerRepository.save(customer);
        }
    }

    @Override
    public CustomerDetailDto getCustomerById(Long id) {
        Customer customer = this.customerRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Not found customer with id = " + id));
        CustomerDetailDto customerDetailDto = new CustomerDetailDto();
        customerDetailDto.setFullName(customer.getFullName());
        customerDetailDto.setEmail(customer.getEmail());
        customerDetailDto.setAddress(customer.getAddress());
        customerDetailDto.setPhone(customer.getPhone());
        customerDetailDto.setNote(customer.getNote());
        customerDetailDto.setCreatedAt(customer.getCreatedAt());
        customerDetailDto.setUpdatedAt(customer.getUpdatedAt());
        return customerDetailDto;
    }

    @Override
    public Boolean deleteCustomerById(Long id) {
        Customer customer = this.customerRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Not found customer with id = " + id));
        customer.setDeleted(true);
        this.customerRepository.save(customer);
        return true;
    }
}
