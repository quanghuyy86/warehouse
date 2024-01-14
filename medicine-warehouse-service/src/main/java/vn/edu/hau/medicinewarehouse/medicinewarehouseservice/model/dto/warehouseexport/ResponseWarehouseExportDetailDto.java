package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.warehouseexport;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseWarehouseExportDetailDto {
    private Long id;
    private Long idImportDetail;
    private String productName;
    private Long quantity; // số lượng xuất
    private double price; //giá xuất từng sp
    private double totalPrice;
}
