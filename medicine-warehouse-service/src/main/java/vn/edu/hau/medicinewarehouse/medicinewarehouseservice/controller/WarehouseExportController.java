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
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.warehouseexport.ResponseWarehouseExportDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.warehouseexport.UpdateWarehouseExportDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.warehouseimport.ResponseWarehouseImportDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.service.WarehouseExportService;

@RestController
@RequestMapping("/warehouse-export")
@RequiredArgsConstructor
@Slf4j
public class WarehouseExportController {
    private final WarehouseExportService warehouseExportService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ApiResponse<Void>> createExport(@RequestBody @Validated CreateWarehouseExport warehouseExport) {
        this.warehouseExportService.createWarehouseExport(warehouseExport);
        return new ResponseEntity<>(ApiResponseGenerator.success(ApiResponseCode.CREATED, "Create export"), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ApiResponse<Void>> updateExport(@PathVariable("id") Long id, @RequestBody @Validated UpdateWarehouseExportDto warehouseExport) {
        this.warehouseExportService.updateWarehouseExport(id, warehouseExport);
        return new ResponseEntity<>(ApiResponseGenerator.success(ApiResponseCode.SUCCESS, "Create export"), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ApiResponse<Void>> deleteExport(@PathVariable("id") Long id) {
        warehouseExportService.deleteExport(id);
        return new ResponseEntity<>(
                ApiResponseGenerator.success(ApiResponseCode.SUCCESS, "Deleted"), HttpStatus.OK);
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ApiResponse<ResponseWarehouseExportDto>> getWarehouseExport(@PathVariable("id") Long id) {
        ResponseWarehouseExportDto responseWarehouseExportDto = this.warehouseExportService.detailExport(id);
        return new ResponseEntity<>(ApiResponseGenerator.success(ApiResponseCode.SUCCESS, "Get Detail Success", responseWarehouseExportDto), HttpStatus.OK);
    }
}
