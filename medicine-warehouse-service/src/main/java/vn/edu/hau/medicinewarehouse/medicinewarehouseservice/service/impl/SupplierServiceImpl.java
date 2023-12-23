package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.common.dto.page.PageResponse;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.common.dto.page.PageResponseConverter;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.exception.ResourceNotFoundException;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.customer.CustomerDetailDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.customer.CustomerParamFilterDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.supplier.CreateSupplierDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.supplier.SupplierDetailDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.supplier.SupplierParamFilterDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.supplier.UpdateSupplierDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.Supplier;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.repository.SupplierRepository;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.service.SupplierService;

import java.util.Optional;

@Service
public class SupplierServiceImpl extends BaseServiceImpl<Supplier, Long> implements SupplierService {
    private final SupplierRepository supplierRepository;

    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        super(supplierRepository);
        this.supplierRepository = supplierRepository;
    }
    @Override
    public PageResponse<SupplierDetailDto> suppierList(SupplierParamFilterDto request) {
        Pageable pageable = Pageable.ofSize(request.getPageSize()).withPage(request.getPageNumber() - 1);
        Page<SupplierDetailDto> list = supplierRepository.searchSuppliers(request.getName(), request.getPhone(), request.getEmail(), pageable)
                .map(group -> new SupplierDetailDto(
                        group.getId(),
                        group.getFullName(),
                        group.getEmail(),
                        group.getPhone(),
                        group.getAddress(),
                        group.getNote()
                ));
        return PageResponseConverter.convert(list);
    }




    @Override
    public void createSupplier(CreateSupplierDto createSupplierDto) {
        if (supplierRepository.existsByFullName(createSupplierDto.getFullName())) {
            throw new ResourceNotFoundException("Tên nhóm khám đã tồn tại!");
        }
        Supplier supplier = new Supplier();
        supplier.setFullName(createSupplierDto.getFullName());
        supplier.setPhone(createSupplierDto.getPhone());
        supplier.setEmail(createSupplierDto.getEmail());
        supplier.setAddress(createSupplierDto.getAddress());
        supplier.setNote(createSupplierDto.getNote());
        this.supplierRepository.save(supplier);
    }

    @Override
    public void updateSupplier(Long id, UpdateSupplierDto updateSupplierDto) {
        Optional<String> name = Optional.ofNullable(updateSupplierDto.getFullName());
        if (name.isPresent() && supplierRepository.existsByFullName(name.get())) {
            throw new ResourceNotFoundException("Tên nhóm khám đã tồn tại!");
        }
        supplierRepository.findById(id)
                .map(group -> {
                    Optional.ofNullable(updateSupplierDto.getFullName()).ifPresent(group::setFullName);
                    Optional.ofNullable(updateSupplierDto.getEmail()).ifPresent(group::setEmail);
                    Optional.ofNullable(updateSupplierDto.getPhone()).ifPresent(group::setPhone);
                    Optional.ofNullable(updateSupplierDto.getAddress()).ifPresent(group::setAddress);
                    Optional.ofNullable(updateSupplierDto.getNote()).ifPresent(group::setNote);
                    return group;
                })
                .map(supplierRepository::save).orElseThrow(() -> new ResourceNotFoundException("Not found medical group with id = " + id));


    }

    @Override
    public SupplierDetailDto getSupplierById(Long id) {
        Supplier supplier = this.supplierRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found customer with id = " + id));
        SupplierDetailDto createSupplierDetailDto = new SupplierDetailDto();
        createSupplierDetailDto.setFullName(supplier.getFullName());
        createSupplierDetailDto.setEmail(supplier.getEmail());
        createSupplierDetailDto.setAddress(supplier.getAddress());
        createSupplierDetailDto.setPhone(supplier.getPhone());
        createSupplierDetailDto.setNote(supplier.getNote());
        return !supplier.isDeleted() ? createSupplierDetailDto : null;
    }

    @Override
    public void deleteSupplierById(Long id) {
        Supplier supplier = this.supplierRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found customer with id = " + id));
        supplier.setDeleted(true);
        this.supplierRepository.save(supplier);
    }
}
