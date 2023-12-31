package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.warehouse_import.WarehouseImport;
@Repository
public interface WarehouseImportRepository extends JpaRepository<WarehouseImport, Long> {
    boolean existsByCode(String code);

    @Query("SELECT s FROM WarehouseImport s WHERE " +
            "(:keyword IS NULL OR s.code LIKE %:keyword%)")
    Page<WarehouseImport> searchWarehouseImport(@Param("keyword") String keyword, Pageable pageable);
}
