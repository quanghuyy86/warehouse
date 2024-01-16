package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.common.dto.page.PageResponse;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.common.response.ApiResponse;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.common.response.ApiResponseCode;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.common.response.ApiResponseGenerator;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.supplier.CreateSupplierDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.supplier.SupplierDetailDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.supplier.SupplierParamFilterDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.warehouseimport.CreateWarehouseImportDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.warehouseimport.ResponseWarehouseImportDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.warehouseimport.UpdateWarehouseImportDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.warehouseimport.WarehouseImportFilter;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.service.WarehouseImportService;

@RestController
@RequestMapping("/warehouse-import")
@RequiredArgsConstructor
@Slf4j
public class WarehouseImportController {

    private final WarehouseImportService warehouseImportService;
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ApiResponse<Void>> createImport(@RequestBody @Validated CreateWarehouseImportDto createWarehouseImportDto) {
        this.warehouseImportService.createWarehouseImport(createWarehouseImportDto);
        return new ResponseEntity<>(ApiResponseGenerator.success(ApiResponseCode.CREATED, "Create import"), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ApiResponse<Void>> updateImport(@PathVariable("id") Long id,@RequestBody @Validated UpdateWarehouseImportDto createWarehouseImportDto) {
        this.warehouseImportService.updateWarehouseImport(id,createWarehouseImportDto);
        return new ResponseEntity<>(ApiResponseGenerator.success(ApiResponseCode.SUCCESS, "Create import"), HttpStatus.OK);
    }
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ApiResponse<ResponseWarehouseImportDto>> getWarehouseImport(@PathVariable("id") Long id) {
        ResponseWarehouseImportDto responseWarehouseImportDto = this.warehouseImportService.detailImport(id);
        return new ResponseEntity<>(ApiResponseGenerator.success(ApiResponseCode.SUCCESS, "Get Detail Success", responseWarehouseImportDto), HttpStatus.OK);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ApiResponse<PageResponse<ResponseWarehouseImportDto>>> searchMedicalGroup(WarehouseImportFilter request) {
        PageResponse<ResponseWarehouseImportDto> list = this.warehouseImportService.getListWarehouseImport(request);
        return new ResponseEntity<>(ApiResponseGenerator.success(ApiResponseCode.SUCCESS, "Search list medical group", list), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ApiResponse<Void>> deleteMedicalRegister(@PathVariable("id") Long id) {
        warehouseImportService.deleteImport(id);
        return new ResponseEntity<>(
                ApiResponseGenerator.success(ApiResponseCode.SUCCESS, "Deleted"), HttpStatus.OK);
    }

}
