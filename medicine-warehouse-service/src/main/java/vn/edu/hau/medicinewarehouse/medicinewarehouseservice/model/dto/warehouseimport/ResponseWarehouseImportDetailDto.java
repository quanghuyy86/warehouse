package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.warehouseimport;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseWarehouseImportDetailDto {
    private Long idImportDetail;

    private String productName;

    private Long quantity; // số lượng nhập

    private double price; //giá nhập
}
