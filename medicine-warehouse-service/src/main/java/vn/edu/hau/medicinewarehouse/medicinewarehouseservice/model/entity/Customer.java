package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.warehouse_export.WarehouseExport;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.warehouse_import.WarehouseImport;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_customer")
public class Customer extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "fullname")
    @NotNull(message = "Bắt buộc nhập tên khách hàng")
    private String fullName;

    @Column(name = "phone")
    @NotNull(message = "Bắt buộc nhập số điện thoại")
    private String phone;

    @Column(name = "email")
    @Email(message = "định dạng emial không hợp lệ")
    @NotNull(message = "Bắt buộc nhập email")
    private String email;

    @Column(name = "address")
    @NotNull(message = "Bắt buộc nhập địa chỉ")
    private String address;

    @Column(name = "note")
    private String note;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "customer")
    private Set<WarehouseExport> warehouseExports  = new HashSet<WarehouseExport>();

}
