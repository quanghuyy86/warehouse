package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.service;

import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.common.dto.page.PageResponse;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.supplier.CreateSupplierDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.supplier.SupplierDetailDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.supplier.SupplierParamFilterDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.supplier.UpdateSupplierDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.Supplier;

public interface SupplierService extends BaseService<Supplier, Long> {

    PageResponse<SupplierDetailDto> suppierList(SupplierParamFilterDto request);
    void createSupplier(CreateSupplierDto customerDto);

    void updateSupplier(Long id, UpdateSupplierDto customerDto);
    SupplierDetailDto getSupplierById(Long id);
    void deleteSupplierById(Long id);
}
