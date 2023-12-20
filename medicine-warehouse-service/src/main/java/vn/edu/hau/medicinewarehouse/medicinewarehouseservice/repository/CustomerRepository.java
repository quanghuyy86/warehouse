package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.Customer;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByFullNameContaining(String keyword);
    Boolean existsByFullName(String fullName);
}
