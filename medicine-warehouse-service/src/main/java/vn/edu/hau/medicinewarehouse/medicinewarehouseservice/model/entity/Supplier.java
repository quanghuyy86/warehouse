package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.warehouse_import.WarehouseImport;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_supplier")
public class Supplier extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "fullname")
    @NotNull(message = "bắt buộc nhập tên nhà cung cấp")
    private String fullName;

    @Column(name = "phone")
    @NotNull(message = "bắt buộc nhập số điện thoại")
    private String phone;

    @Column(name = "email")
    @Email(message = "Email không hợp lệ")
    @NotNull(message = "bắt buộc nhập email")
    private String email;

    @Column(name = "address")
    @NotNull(message = "bắt buộc nhập địa chỉ")
    private String address;

    @Column(name = "note")
    private String note;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "supplier")
    private Set<WarehouseImport> warehouseImports  = new HashSet<WarehouseImport>();
}
