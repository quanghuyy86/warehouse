package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.warehouseimport;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateWarehouseImportDto {

    @NotNull(message = "Bắt buộc nhập mã đơn hàng")
    @Size(min = 1, max = 500)
    private String code;

    @Size(min = 1, max = 500)
    private String note;

    @NotNull(message = "Bắt buộc điền tên khách hàng")
    private Long supplierId;

    @NotNull(message = "Bắt buộc thêm sản phẩm")
    private List<CreateWarehouseImportDetailDto> createWarehouseImportDetailDto = new ArrayList<>();

    private Double totalPrice;
}
