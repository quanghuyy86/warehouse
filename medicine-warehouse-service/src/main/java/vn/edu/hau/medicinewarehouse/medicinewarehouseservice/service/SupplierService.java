package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.service;

import org.springframework.data.domain.Page;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.customer.CustomerDetailDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.customer.CustomerDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.supplier.SupplierDetailDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.supplier.SupplierDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.Customer;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.Supplier;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.request.Request;

public interface SupplierService extends BaseService<Supplier, Long> {
    Page<Supplier> getListSupplier(Request request);
    Supplier createOrUpdateSupplier(SupplierDto customerDto, Long id);
    SupplierDetailDto getSupplierById(Long id);
    Boolean deleteSupplierById(Long id);
}
