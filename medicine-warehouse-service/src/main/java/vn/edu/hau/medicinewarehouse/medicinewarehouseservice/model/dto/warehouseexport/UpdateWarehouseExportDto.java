package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.warehouseexport;

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
public class UpdateWarehouseExportDto {
    @Size(min = 1, max = 500)
    private String note;

    private Long customerId;

    private List<CreateWarehouseExportDetailDto> createWarehouseExportDetailDtos = new ArrayList<>();
}
