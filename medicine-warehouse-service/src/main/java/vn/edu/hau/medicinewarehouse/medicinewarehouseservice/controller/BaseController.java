package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.response.ResponseHandler;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.service.BaseService;

public abstract class BaseController<T, ID> {
    protected BaseService<T, ID> baseService;

    public BaseController(BaseService<T, ID> baseService) {
        this.baseService = baseService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<Object> getAll() {
        return ResponseHandler.generateResponse(HttpStatus.OK, "", baseService.getAll());
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<Object> getById(@PathVariable("id") ID id) {
        return ResponseHandler.generateResponse(HttpStatus.OK, "", baseService.getById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Object> create(@Validated @RequestBody T t) {
        return ResponseHandler.generateResponse(HttpStatus.CREATED, "", baseService.createOrUpdate(null, t));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") ID id,
                                         @Validated @RequestBody T t) {
        return ResponseHandler.generateResponse(HttpStatus.CREATED, "", baseService.createOrUpdate(id, t));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") ID id) {
        return ResponseHandler.generateResponse(HttpStatus.ACCEPTED, "", baseService.deleteById(id));
    }
}
