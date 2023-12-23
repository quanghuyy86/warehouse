package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.common.dto.page.PagingOption;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerParamFilterDto extends PagingOption {
    private String name;
    private String phone;
    private String email;
}
