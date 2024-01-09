package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.warehouse_export.WarehouseExportDetail;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.warehouse_import.WarehouseImportDetail;

import java.util.List;

@Repository
public interface WarehouseExportDetailRepository extends JpaRepository<WarehouseExportDetail, Long> {
    void deleteByWarehouseExportId(Long warehouseImportId);

    List<WarehouseExportDetail> findAllByWarehouseExportId(Long id);
}
