package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.warehouseexport;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateWarehouseExportDetailDto {
    @NotNull(message = "Bắt buộc nhập sản phẩm")
    private Long productId;
    @NotNull(message = "bắt buộc điền số lượng")
    private Long quantity;
    @NotNull(message = "bắt buộc điền giá nhập")
    private Double price;
}
