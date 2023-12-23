package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.common.dto.page.PagingOption;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryParamFilterDto extends PagingOption {
    private String keyword;
}
