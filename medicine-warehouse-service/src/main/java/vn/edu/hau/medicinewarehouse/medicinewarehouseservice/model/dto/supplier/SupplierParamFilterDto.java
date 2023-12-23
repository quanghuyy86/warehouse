package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.supplier;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.common.dto.page.PagingOption;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SupplierParamFilterDto extends PagingOption {
    private String name;
    private String phone;
    private String email;
}
