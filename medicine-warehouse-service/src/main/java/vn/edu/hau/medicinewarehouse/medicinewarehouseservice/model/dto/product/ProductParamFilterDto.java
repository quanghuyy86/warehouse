package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.common.dto.page.PagingOption;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductParamFilterDto extends PagingOption {
    private String keyword;
    private Long idCategory;
}
