package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.warehouse_import;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.BaseEntity;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.Supplier;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.user.User;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tbl_warehouse_inport")
public class WarehouseImport extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "time", nullable = true)
    @JsonIgnore
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;
    @Column(name = "note")
    private String note;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user; //nhân viên tạo đơn
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier; //nhà cung cấp
    @OneToMany(cascade = CascadeType.ALL, //Khi xóa 1 category thì tất cả pruducts sẽ bị xóa
            fetch = FetchType.LAZY, // khi lấy product đấy, thì nó sẽ k lấy tất cả các sản phẩm product đấy, chỉ lấy thông tin của mặt hàng đấy thôi
            mappedBy = "warehouseImport") //giá trị của mappedBy bằng tên property của ManyToOne
    private Set<WarehouseImportDetail> warehouseImportDetails = new HashSet<WarehouseImportDetail>();
}
