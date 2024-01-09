package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.service;

import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.warehouseexport.CreateWarehouseExport;

public interface WarehouseExportService {
    void createWarehouseExport(CreateWarehouseExport createWarehouseExport);

    void updateWarehouseExport(Long id, CreateWarehouseExport warehouseExport);

    void deleteExport(Long id);
}
