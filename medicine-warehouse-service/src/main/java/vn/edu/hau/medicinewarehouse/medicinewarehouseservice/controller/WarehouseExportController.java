package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.common.response.ApiResponse;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.common.response.ApiResponseCode;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.common.response.ApiResponseGenerator;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.warehouseexport.CreateWarehouseExport;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.service.WarehouseExportService;

@RestController
@RequestMapping("/warehouse-export")
@RequiredArgsConstructor
@Slf4j
public class WarehouseExportController {
    private final WarehouseExportService warehouseExportService;
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ApiResponse<Void>> createImport(@RequestBody @Validated CreateWarehouseExport warehouseExport) {
        this.warehouseExportService.createWarehouseExport(warehouseExport);
        return new ResponseEntity<>(ApiResponseGenerator.success(ApiResponseCode.CREATED, "Create export"), HttpStatus.CREATED);
    }

}
