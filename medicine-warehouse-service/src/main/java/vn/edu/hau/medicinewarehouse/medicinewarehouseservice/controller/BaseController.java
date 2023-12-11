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

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<Object> getById(@PathVariable("id") ID id) {
        return new ResponseEntity<>(baseService.getById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Object> create(@Validated @RequestBody T t) {
        return new ResponseEntity<>(baseService.createOrUpdate(null, t), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") ID id,
                                         @Validated @RequestBody T t) {
        return new ResponseEntity<>(baseService.createOrUpdate(id, t), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") ID id) {
        return new ResponseEntity<>(baseService.deleteById(id), HttpStatus.NO_CONTENT);
    }

}
