package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.category.CategoryDetailDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.Category;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailDto {
    private Long id;
    private String name;
    private String description;
    private Long quantity;
    private CategoryDetailDto category;
}
