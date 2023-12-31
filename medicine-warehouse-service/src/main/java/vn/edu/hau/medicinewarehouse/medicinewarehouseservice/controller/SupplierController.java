package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.common.dto.page.PageResponse;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.common.dto.page.PagingOption;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.common.response.ApiResponse;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.common.response.ApiResponseCode;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.common.response.ApiResponseGenerator;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.product.ProductDetailDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.supplier.CreateSupplierDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.supplier.SupplierDetailDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.supplier.SupplierParamFilterDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.supplier.UpdateSupplierDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.service.SupplierService;

import java.util.List;

@RestController
@RequestMapping("/suppliers")
@RequiredArgsConstructor
@Slf4j
public class SupplierController {
    private final SupplierService supplierService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ApiResponse<PageResponse<SupplierDetailDto>>> getListCustomers(SupplierParamFilterDto request) {
        PageResponse<SupplierDetailDto> supplier = this.supplierService.suppierList(request);
        return new ResponseEntity<>(ApiResponseGenerator.success(ApiResponseCode.SUCCESS, "get list supplier", supplier), HttpStatus.OK);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ApiResponse<Void>> createCustomer(@RequestBody @Validated CreateSupplierDto createSupplierDto) {
        this.supplierService.createSupplier(createSupplierDto);
        return new ResponseEntity<>(ApiResponseGenerator.success(ApiResponseCode.CREATED, "Create supplier"), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ApiResponse<SupplierDetailDto>> getCustomerById(@PathVariable Long id) {
        SupplierDetailDto supplierDetailDto = this.supplierService.getSupplierById(id);
        return new ResponseEntity<>(ApiResponseGenerator.success(ApiResponseCode.SUCCESS, "Get supplier by id", supplierDetailDto), HttpStatus.OK);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ApiResponse<Void>> updateCustomerById(@PathVariable Long id, @Validated @RequestBody UpdateSupplierDto updateSupplierDto) {
        this.supplierService.updateSupplier(id, updateSupplierDto);
        return new ResponseEntity<>(ApiResponseGenerator.success(ApiResponseCode.SUCCESS, "update supplier"), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Object> deleteById(@PathVariable Long id) {
        this.supplierService.deleteSupplierById(id);
        return new ResponseEntity<>(ApiResponseGenerator.success(ApiResponseCode.NO_CONTENT, "Delete supplier"), HttpStatus.OK);
    }
    @GetMapping("get-all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ApiResponse<List<SupplierDetailDto>>> getAllProduct() {
        List<SupplierDetailDto> entities = supplierService.findAll();
        return new ResponseEntity<>(ApiResponseGenerator.success(ApiResponseCode.SUCCESS, "get product", entities), HttpStatus.OK);
    }
}
