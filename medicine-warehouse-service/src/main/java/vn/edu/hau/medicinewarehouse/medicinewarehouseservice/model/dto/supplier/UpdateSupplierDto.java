package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.supplier;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
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
public class UpdateSupplierDto {
    @Nullable
    @Size(min = 1, max = 500)
    private String fullName;
    @Nullable
    @Size(min = 1, max = 500)
    private String phone;
    @Nullable
    @Email(message = "không đúng định dạng email")
    @Size(min = 1, max = 500)
    private String email;
    @Nullable
    @Size(min = 1, max = 500)
    private String address;
    @Nullable
    private String note;
}
