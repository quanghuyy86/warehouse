package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.service;

import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.common.dto.page.PageResponse;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.warehouseexport.CreateWarehouseExport;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.warehouseexport.ResponseWarehouseExportDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.warehouseexport.UpdateWarehouseExportDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.warehouseexport.WarehouseExportFilter;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.warehouseimport.ResponseWarehouseImportDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.warehouseimport.WarehouseImportFilter;

public interface WarehouseExportService {
    void createWarehouseExport(CreateWarehouseExport createWarehouseExport);

    void updateWarehouseExport(Long id, UpdateWarehouseExportDto warehouseExport);

    void deleteExport(Long id);

    ResponseWarehouseExportDto detailExport(Long aLong);

    PageResponse<ResponseWarehouseExportDto> getListWarehouseExport(WarehouseExportFilter request);
}
