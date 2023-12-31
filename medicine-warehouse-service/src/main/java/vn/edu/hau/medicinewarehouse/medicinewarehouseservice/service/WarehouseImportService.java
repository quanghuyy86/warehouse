package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.service;

import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.common.dto.page.PageResponse;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.warehouseimport.CreateWarehouseImportDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.warehouseimport.ResponseWarehouseImportDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.warehouseimport.WarehouseImportFilter;

public interface WarehouseImportService {
    void createWarehouseImport(CreateWarehouseImportDto importDto);
    void updateWarehouseImport(Long importId, CreateWarehouseImportDto importDto);

    ResponseWarehouseImportDto detailImport(Long id);
    PageResponse<ResponseWarehouseImportDto> getListWarehouseImport(WarehouseImportFilter filter);
    void deleteImport(Long id);

}
