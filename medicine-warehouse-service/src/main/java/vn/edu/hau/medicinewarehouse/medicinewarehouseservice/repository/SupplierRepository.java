package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    @Query("SELECT s FROM Supplier s WHERE " +
            "(:name IS NULL OR s.fullName LIKE %:name% ) " +
            "AND (:phone IS NULL OR s.phone LIKE %:phone%) " +
            "AND (:email IS NULL OR s.email LIKE %:email%) ORDER BY s.createdAt DESC")
    Page<Supplier> searchSuppliers(@Param("name") String name, @Param("phone") String phone, @Param("email") String email, Pageable pageable);
    boolean existsByFullName(String fullName);
}
