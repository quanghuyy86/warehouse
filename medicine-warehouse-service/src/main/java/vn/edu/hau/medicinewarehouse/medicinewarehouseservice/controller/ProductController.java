package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.product.ProductDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.Category;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.Product;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.request.Request;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.service.ProductService;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin("*")
public class ProductController{
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public ResponseEntity<Object> getListProduct(Request request, Category category){
        List<Product> productPage = this.productService.getListProduct(request, category);
        return new ResponseEntity<>(productPage, HttpStatus.OK);
    }
    @PostMapping("/products")
    public ResponseEntity<Object> createProduct(@Validated @RequestBody ProductDto productDto,
                                                MultipartFile avatar)  {
        return new ResponseEntity<>(this.productService.createProduct(productDto, avatar),HttpStatus.CREATED);
    }
    @GetMapping("/products/{id}")
    public ResponseEntity<Object> getByIdProduct(@PathVariable Long id){
        return new ResponseEntity<>(this.productService.getProductById(id), HttpStatus.OK);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable Long id,
                                                @Validated @RequestBody ProductDto productDto,
                                                MultipartFile avatar)  {
        Product product = this.productService.updateProduct(id, productDto, avatar);
        return new ResponseEntity<>(product,HttpStatus.OK);
    }
    @DeleteMapping("/products/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Long id){
        return new ResponseEntity<>(this.productService.deleteProductById(id), HttpStatus.OK);
    }
}
