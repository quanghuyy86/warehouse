package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.customer.CustomerDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.supplier.SupplierDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.Customer;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.Supplier;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.request.Request;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.service.SupplierService;

@RestController
public class SupplierController{
    private final SupplierService supplierService;
    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping("/suppliers")
    public ResponseEntity<Object> getListCustomers(Request request){
        Page<Supplier> supplier = this.supplierService.getListSupplier(request);
        return new ResponseEntity<>(supplier, HttpStatus.OK);
    }

    @PostMapping("/suppliers")
    public ResponseEntity<Object> createCustomer(@RequestBody @Validated SupplierDto supplierDto){
        return new ResponseEntity<>(this.supplierService.createOrUpdateSupplier(supplierDto, null), HttpStatus.CREATED);
    }

    @GetMapping("/suppliers/{id}")
    public ResponseEntity<Object> getCustomerById(@PathVariable Long id){
        return new ResponseEntity<>(this.supplierService.getSupplierById(id), HttpStatus.OK);
    }

    @PutMapping("/suppliers/{id}")
    public ResponseEntity<Object> updateCustomerById(@PathVariable Long id, @Validated @RequestBody SupplierDto supplierDto){
        return new ResponseEntity<>(this.supplierService.createOrUpdateSupplier(supplierDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/suppliers/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Long id){
        return new ResponseEntity<>(this.supplierService.deleteSupplierById(id), HttpStatus.OK);
    }
}
