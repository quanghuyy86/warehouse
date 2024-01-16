package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.Category;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("SELECT s FROM Category s WHERE " +
            "(:keyword IS NULL OR s.name LIKE %:keyword%) ORDER BY s.createdAt DESC")
    Page<Category> searchCategories(@Param("keyword") String keyword, Pageable pageable);
    boolean existsByName(String name);


}
