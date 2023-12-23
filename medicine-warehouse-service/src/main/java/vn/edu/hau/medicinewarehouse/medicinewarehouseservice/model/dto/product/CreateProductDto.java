package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductDto {
    @NotNull(message = "bắt buộc nhập tên")
    @Size(min = 1, max = 500)
    private String name;
    private String avatar;
    @Size(min = 1, max = 500)
    private String description;
    @Min(value = 0, message = "Số lượng không được bé hơn 0")
    private Long quantity;
    @NotNull(message = "bắt buộc chọn loại thuốc")
    private Long categoryId;
}
