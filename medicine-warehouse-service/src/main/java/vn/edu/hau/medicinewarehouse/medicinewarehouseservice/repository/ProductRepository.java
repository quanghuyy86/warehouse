package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.Customer;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT s FROM Product s WHERE " +
            "(:keyword IS NULL OR s.name LIKE %:keyword% ) " +
            "AND (:categoryId IS NULL OR s.categoryId = :categoryId)")
    Page<Product> searchProduct(@Param("keyword") String keyword,@Param("categoryId") Long categoryId, Pageable pageable);
}
