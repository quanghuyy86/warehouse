package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.common.dto.page.PageResponse;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.common.dto.page.PageResponseConverter;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.exception.ResourceNotFoundException;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.customer.CreateCustomerDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.customer.CustomerDetailDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.customer.CustomerParamFilterDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.customer.UpdateCustomerDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.Customer;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.repository.CustomerRepository;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.service.CustomerService;

import java.util.Optional;

@Service
public class CustomerServiceImpl extends BaseServiceImpl<Customer, Long> implements CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        super(customerRepository);
        this.customerRepository = customerRepository;
    }

    @Override
    public PageResponse<CustomerDetailDto> categoriesList(CustomerParamFilterDto request) {
        Pageable pageable = Pageable.ofSize(request.getPageSize()).withPage(request.getPageNumber() - 1);
        Page<CustomerDetailDto> list = customerRepository.searchCustomer(request.getName(), request.getPhone(), request.getEmail(), pageable)
                .map(group -> new CustomerDetailDto(
                        group.getId(),
                        group.getFullName(),
                        group.getPhone(),
                        group.getEmail(),
                        group.getAddress(),
                        group.getNote()
                ));
        return PageResponseConverter.convert(list);
    }

    @Override
    public void createCustomer(CreateCustomerDto createCustomerDto) {
        if (customerRepository.existsByFullName(createCustomerDto.getFullName())) {
            throw new ResourceNotFoundException("Tên nhóm khám đã tồn tại!");
        }
        Customer customer = new Customer();
        customer.setFullName(createCustomerDto.getFullName());
        customer.setPhone(createCustomerDto.getPhone());
        customer.setEmail(createCustomerDto.getEmail());
        customer.setAddress(createCustomerDto.getAddress());
        customer.setNote(createCustomerDto.getNote());
        this.customerRepository.save(customer);
    }

    @Override
    public void updateCustomer(Long id, UpdateCustomerDto updateCustomerDto) {
//        Optional<String> name = Optional.ofNullable(updateCustomerDto.getFullName());
//        if (name.isPresent() && customerRepository.existsByFullName(name.get())) {
//            throw new ResourceNotFoundException("Tên nhóm khám đã tồn tại!");
//        }
//        customerRepository.findById(id)
//                .map(group -> {
//                    Optional.ofNullable(updateCustomerDto.getFullName()).ifPresent(group::setFullName);
//                    Optional.ofNullable(updateCustomerDto.getEmail()).ifPresent(group::setEmail);
//                    Optional.ofNullable(updateCustomerDto.getPhone()).ifPresent(group::setPhone);
//                    Optional.ofNullable(updateCustomerDto.getAddress()).ifPresent(group::setAddress);
//                    Optional.ofNullable(updateCustomerDto.getNote()).ifPresent(group::setNote);
//                    return group;
//                })
//                .map(customerRepository::save).orElseThrow(() -> new ResourceNotFoundException("Not found medical group with id = " + id));
        Customer customer = this.customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found customer with id = " + id));
        customer.setFullName(updateCustomerDto.getFullName());
        customer.setPhone(updateCustomerDto.getPhone());
        customer.setAddress(updateCustomerDto.getAddress());
        customer.setNote(updateCustomerDto.getNote());
        this.customerRepository.save(customer);
    }

    @Override
    public CustomerDetailDto getCustomerById(Long id) {
        Customer customer = this.customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found customer with id = " + id));
        CustomerDetailDto customerDetailDto = new CustomerDetailDto();
        customerDetailDto.setFullName(customer.getFullName());
        customerDetailDto.setEmail(customer.getEmail());
        customerDetailDto.setAddress(customer.getAddress());
        customerDetailDto.setPhone(customer.getPhone());
        customerDetailDto.setNote(customer.getNote());
        return !customer.isDeleted() ? customerDetailDto : null;
    }

    @Override
    public void deleteCustomerById(Long id) {
        Customer customer = this.customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found customer with id = " + id));
        customer.setDeleted(true);
        this.customerRepository.save(customer);
    }
}
