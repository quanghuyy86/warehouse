package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDetailDto {
    private Long id;
    private String fullName;
    private String phone;
    private String email;
    private String address;
    private String note;

}
