package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.warehouseimport;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateWarehouseImportDto {
    @Size(min = 1, max = 500)
    private String note;
    private Long supplierId;
    private List<CreateWarehouseImportDetailDto> createWarehouseImportDetailDto = new ArrayList<>();
}
