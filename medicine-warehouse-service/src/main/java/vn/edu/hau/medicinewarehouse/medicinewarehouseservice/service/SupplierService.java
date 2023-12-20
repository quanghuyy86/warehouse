package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.service;

import org.springframework.data.domain.Page;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.supplier.CreateSupplierDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.supplier.SupplierDetailDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.supplier.UpdateSupplierDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.Supplier;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.request.Request;

public interface SupplierService extends BaseService<Supplier, Long> {
    Page<Supplier> getListSupplier(Request request);
    void createSupplier(CreateSupplierDto customerDto);

    void updateSupplier(Long id, UpdateSupplierDto customerDto);
    SupplierDetailDto getSupplierById(Long id);
    void deleteSupplierById(Long id);
}
