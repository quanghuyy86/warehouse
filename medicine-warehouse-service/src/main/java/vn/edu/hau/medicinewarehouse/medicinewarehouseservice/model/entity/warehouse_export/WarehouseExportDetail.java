package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.warehouse_export;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.BaseEntity;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.Product;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.warehouse_import.WarehouseExport;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_warehouse_export_detail")
public class WarehouseExportDetail extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "warehouse_export_id")
    private Long warehouseExportId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouse_export_id", updatable = false, insertable = false)
    private WarehouseExport warehouseExport;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "quantity")
    private Long quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", updatable = false, insertable = false)
    private Product product;

    private double price; //giá bán
}
