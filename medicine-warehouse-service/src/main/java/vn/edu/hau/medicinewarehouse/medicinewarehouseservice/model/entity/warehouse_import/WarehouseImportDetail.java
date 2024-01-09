package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.warehouse_import;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.BaseEntity;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.Product;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_warehouse_inport_detail")
@Where(clause = "is_delete = 0")
public class WarehouseImportDetail extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "warehouse_inport_id")
    private Long warehouseImportId;

    @Column(name = "product_id")
    private Long productId;

    private Long quantity; // số lượng nhập

    private double price; //giá nhập

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouse_inport_id", insertable = false, updatable = false)
    private WarehouseExport warehouseImport; //id đơn nhập

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product; //id đơn thuốc
}
