package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.supplier;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class SupplierDto {
    @NotNull(message = "bắt buộc nhập tên nhà cung cấp")
    @Size(min = 1, max = 50)
    private String fullName;
    @NotNull(message = "bắt buộc nhập số điện thoại")
    @Size(min = 1, max = 50)
    private String phone;
    @NotNull(message = "bắt buộc nhập email")
    @Size(min = 1, max = 50)
    private String email;
    @NotNull(message = "bắt buộc nhập địa chỉ")
    @Size(min = 1, max = 50)
    private String address;
    @Nullable
    private String note;
}
