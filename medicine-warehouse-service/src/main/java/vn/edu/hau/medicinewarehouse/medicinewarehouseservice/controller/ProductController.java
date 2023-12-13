package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.product.ProductDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.Product;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.service.ProductService;

import java.io.IOException;

@RestController
public class ProductController extends BaseController<Product, Long>{
    private final ProductService productService;

    public ProductController(ProductService productService) {
        super(productService);
        this.productService = productService;
    }
    @PostMapping("products/")
    public ResponseEntity<Object> createProduct(@Validated @RequestBody ProductDto productDto, MultipartFile avatar) throws IOException {
        return new ResponseEntity<>(this.productService.createOrUpdateProduct(null, productDto, avatar),HttpStatus.CREATED);
    }
}
