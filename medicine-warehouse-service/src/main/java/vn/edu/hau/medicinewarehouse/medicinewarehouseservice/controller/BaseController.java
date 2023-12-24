package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.service.BaseService;

public abstract class BaseController<T, ID> {
    protected BaseService<T, ID> baseService;

    public BaseController(BaseService<T, ID> baseService) {
        this.baseService = baseService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<Object> getAll() {
        return new ResponseEntity<>(baseService.getAll(), HttpStatus.OK);
    }

}
