package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.Customer;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("SELECT s FROM Customer s WHERE " +
            "(:name IS NULL OR s.fullName LIKE %:name% ) " +
            "AND (:phone IS NULL OR s.phone LIKE %:phone%) " +
            "AND (:email IS NULL OR s.email LIKE %:email%) ORDER BY s.createdAt DESC")
    Page<Customer> searchCustomer(@Param("name") String name,@Param("phone") String phone,@Param("email") String email, Pageable pageable);
    Boolean existsByFullName(String fullName);
}
