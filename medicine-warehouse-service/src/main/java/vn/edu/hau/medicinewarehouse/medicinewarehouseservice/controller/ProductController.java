package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.common.dto.page.PageResponse;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.common.response.ApiResponse;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.common.response.ApiResponseCode;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.common.response.ApiResponseGenerator;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.product.ProductDetailDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.product.CreateProductDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.product.ProductParamFilterDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.product.UpdateProductDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.Product;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ApiResponse<PageResponse<ProductDetailDto>>> getListProduct(ProductParamFilterDto requestProduct) {
        PageResponse<ProductDetailDto> productPage = this.productService.productsList(requestProduct);
        return new ResponseEntity<>(ApiResponseGenerator.success(ApiResponseCode.SUCCESS, "get product", productPage), HttpStatus.OK);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ApiResponse<Void>> createProduct(@Validated @RequestBody CreateProductDto createProductDto,
                                                           MultipartFile avatar) {
        this.productService.createProduct(createProductDto, avatar);

        return new ResponseEntity<>(ApiResponseGenerator.success(ApiResponseCode.CREATED, "create product"), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ApiResponse<ProductDetailDto>> getByIdProduct(@PathVariable("id") Long id) {
        ProductDetailDto product = productService.getProductById(id);
        return new ResponseEntity<>(ApiResponseGenerator.success(ApiResponseCode.SUCCESS, "get product", product), HttpStatus.OK);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ApiResponse<Void>> updateProduct(@PathVariable("id") Long id,
                                                           @Validated @RequestBody UpdateProductDto updateProductDto,
                                                           MultipartFile avatar) {
        this.productService.updateProduct(id, updateProductDto, avatar);
        return new ResponseEntity<>(ApiResponseGenerator.success(ApiResponseCode.SUCCESS, "update product"), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<ApiResponse<Void>> deleteById(@PathVariable("id") Long id) {
        this.productService.deleteProductById(id);
        return new ResponseEntity<>(ApiResponseGenerator.success(ApiResponseCode.SUCCESS, "delete product"), HttpStatus.OK);
    }

    @GetMapping("get-all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ApiResponse<List<ProductDetailDto>>> getAllProduct() {
        List<ProductDetailDto> entities = productService.findAll();
        return new ResponseEntity<>(ApiResponseGenerator.success(ApiResponseCode.SUCCESS, "get product", entities), HttpStatus.OK);
    }
}
