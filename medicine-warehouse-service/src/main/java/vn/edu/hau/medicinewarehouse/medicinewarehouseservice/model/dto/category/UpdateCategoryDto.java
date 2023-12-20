package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.category;

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
public class UpdateCategoryDto {
    @Nullable
    @Size(min = 1, max = 500)
    private String name;
    @Nullable
    @Size(min = 1, max = 500)
    private String description;
}
