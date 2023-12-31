package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.warehouseimport;

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
public class ResponseWarehouseImportDto {
    private Long idWarehouseImport;
    private String code;
    private String note;
    private String supplierName;
    private List<ResponseWarehouseImportDetailDto> detailImport = new ArrayList<>();

}
