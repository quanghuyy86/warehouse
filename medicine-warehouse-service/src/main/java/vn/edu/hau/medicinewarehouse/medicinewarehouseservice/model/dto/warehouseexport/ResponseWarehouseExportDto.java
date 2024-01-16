package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.warehouseexport;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.customer.CustomerDetailDto;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseWarehouseExportDto {
    private Long idWarehouseExport;
    private String code;
    private String note;
    private CustomerDetailDto customer;
    private List<ResponseWarehouseExportDetailDto> detailExport = new ArrayList<>();
    private double totalPrice;

}
