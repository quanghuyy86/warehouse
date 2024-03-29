package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.warehouse_export;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.BaseEntity;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.Customer;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.user.User;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_warehouse_export")
@Where(clause = "is_delete = 0")
public class WarehouseExport extends BaseEntity {
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

    @Column(name = "customer_id")
    private Long customerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "total_price")
    private double totalPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", updatable = false, insertable = false)
    private Customer customer;


//    @OneToMany(fetch = FetchType.LAZY,
//            mappedBy = "warehouseExport")
//    private Set<WarehouseExportDetail> warehouseExportDetails = new HashSet<WarehouseExportDetail>();
}
