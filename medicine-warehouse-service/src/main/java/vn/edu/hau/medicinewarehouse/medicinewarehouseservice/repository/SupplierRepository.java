package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.Supplier;


public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
