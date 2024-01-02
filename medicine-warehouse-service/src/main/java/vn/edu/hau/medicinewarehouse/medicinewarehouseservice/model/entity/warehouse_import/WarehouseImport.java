package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.warehouse_import;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.BaseEntity;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.Supplier;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.user.User;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tbl_warehouse_inport")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "is_delete = 0")
public class WarehouseImport extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "time", nullable = true)
    @JsonIgnore
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;

    @Column(name = "note")
    private String note;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "supplier_id")
    private Long supplierId;

    @Column(name = "total_price")
    private Double totalPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",insertable = false, updatable=false)
    private User user; //nhân viên tạo đơn

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id", insertable = false, updatable=false)
    private Supplier supplier;

    @OneToMany(fetch = FetchType.LAZY,
               mappedBy = "warehouseImport")
    private Set<WarehouseImportDetail> warehouseImportDetails = new HashSet<WarehouseImportDetail>();
}
