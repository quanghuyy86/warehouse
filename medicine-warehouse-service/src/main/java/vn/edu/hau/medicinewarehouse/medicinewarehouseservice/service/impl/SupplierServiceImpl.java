package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.exception.ResourceNotFoundException;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.supplier.SupplierDetailDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.supplier.SupplierDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.Supplier;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.request.Request;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.repository.SupplierRepository;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.service.SupplierService;

import java.util.List;

@Service
public class SupplierServiceImpl extends BaseServiceImpl<Supplier, Long> implements SupplierService {
    private final SupplierRepository supplierRepository;

    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        super(supplierRepository);
        this.supplierRepository = supplierRepository;
    }

    public List<Supplier> searchSupplierByName(String keyword){
        return this.supplierRepository.findByFullNameContaining(keyword);
    }

    @Override
    public Page<Supplier> getListSupplier(Request request) {
        List<Supplier> list = this.searchSupplierByName(request.getKeyword());
        Pageable pageable = PageRequest.of(request.getPage(), request.getPageSize());
        return new PageImpl<Supplier>(list, pageable, this.searchSupplierByName(request.getKeyword()).size());
    }

    @Override
    public Supplier createSupplier(SupplierDto supplierDto) {
        Supplier supplier = new Supplier();
        supplier.setFullName(supplierDto.getFullName());
        supplier.setPhone(supplierDto.getPhone());
        supplier.setEmail(supplierDto.getEmail());
        supplier.setAddress(supplierDto.getAddress());
        supplier.setNote(supplierDto.getNote());
        return this.supplierRepository.save(supplier);
    }

    @Override
    public Supplier updateSupplier(Long id, SupplierDto supplierDto) {
        Supplier supplier = this.supplierRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Not found with id = " + id));
        supplier.setFullName(supplierDto.getFullName());
        supplier.setPhone(supplierDto.getPhone());
        supplier.setEmail(supplierDto.getEmail());
        supplier.setAddress(supplierDto.getAddress());
        supplier.setNote(supplierDto.getNote());
        return this.supplierRepository.save(supplier);
    }

    @Override
    public SupplierDetailDto getSupplierById(Long id) {
        Supplier supplier = this.supplierRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Not found customer with id = " + id));
        SupplierDetailDto supplierDetailDto = new SupplierDetailDto();
        supplierDetailDto.setFullName(supplier.getFullName());
        supplierDetailDto.setEmail(supplier.getEmail());
        supplierDetailDto.setAddress(supplier.getAddress());
        supplierDetailDto.setPhone(supplier.getPhone());
        supplierDetailDto.setNote(supplier.getNote());
        supplierDetailDto.setCreatedAt(supplier.getCreatedAt());
        supplierDetailDto.setUpdatedAt(supplier.getUpdatedAt());
        return !supplier.isDeleted() ? supplierDetailDto : null;
    }

    @Override
    public Boolean deleteSupplierById(Long id) {
        Supplier supplier = this.supplierRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Not found customer with id = " + id));
        supplier.setDeleted(true);
        this.supplierRepository.save(supplier);
        return true;
    }
}
