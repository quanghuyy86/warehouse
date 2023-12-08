package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.Category;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
//    @Query("select c from Category c where c.name like %?1%")
    List<Category> findByNameContaining(String keyword);

}
