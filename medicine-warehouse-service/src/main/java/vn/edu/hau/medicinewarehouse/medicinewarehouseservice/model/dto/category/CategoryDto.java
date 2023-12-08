package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.category;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    @Nonnull
    private String name;
    @Nullable
    private String description;
}
