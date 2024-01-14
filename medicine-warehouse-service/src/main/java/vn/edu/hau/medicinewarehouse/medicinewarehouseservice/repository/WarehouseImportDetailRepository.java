package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.warehouse_import.WarehouseImportDetail;

import java.util.List;

public interface WarehouseImportDetailRepository extends JpaRepository<WarehouseImportDetail, Long> {
    void deleteAllByWarehouseImportId(Long warehouseImportId);

    List<WarehouseImportDetail> findAllByWarehouseImportId(Long id);
}
