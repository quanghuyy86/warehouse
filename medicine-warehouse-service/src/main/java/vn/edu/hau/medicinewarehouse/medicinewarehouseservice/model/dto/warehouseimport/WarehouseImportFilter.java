package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.warehouseimport;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.common.dto.page.PagingOption;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseImportFilter extends PagingOption {
    private String keyword;
}
