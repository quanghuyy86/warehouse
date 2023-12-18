package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.BaseEntity;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.warehouse_export.WarehouseExport;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.warehouse_import.WarehouseImport;

import java.util.HashSet;
import java.util.Set;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_users")
public class User extends BaseEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username")
    private String userName;
    @Column(name = "password")
    private String passWord;
    @Column(name = "fullname")
    private String fullName;
    @Column(name = "email")
    private String email;
    @Column(name = "telephone")
    private String telephone;
    @OneToMany(mappedBy = "user",
            fetch = FetchType.EAGER)
    private Set<UserRole> userRoles;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "user")
    private Set<WarehouseImport> warehouseImports = new HashSet<WarehouseImport>();

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "user")
    private Set<WarehouseExport> warehouseExports  = new HashSet<WarehouseExport>();

}
