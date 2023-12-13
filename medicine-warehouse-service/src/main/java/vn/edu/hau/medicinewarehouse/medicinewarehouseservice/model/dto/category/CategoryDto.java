package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.category;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
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
public class CategoryDto {
    @Nullable
    private Long id;
    @NotNull(message = "bắt buộc nhập tên")
    @Size(min = 1, max = 50)
    private String name;
    @Nullable
    @Size(min = 1)
    private String description;
}
