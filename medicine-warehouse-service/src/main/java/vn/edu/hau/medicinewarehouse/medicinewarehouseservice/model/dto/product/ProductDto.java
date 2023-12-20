package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.product;

import io.micrometer.common.lang.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    @Nullable
    private Long id;
    private String name;
    private String avatar;
    private String description;
    private Long quantity;
    private Long categoryId;
}
