package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.service;

import org.springframework.web.multipart.MultipartFile;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.common.dto.page.PageResponse;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.product.ProductDetailDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.product.ProductDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.product.ProductParamFilterDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.Product;

public interface ProductService extends BaseService<Product, Long>{
    PageResponse<ProductDetailDto> productsList(ProductParamFilterDto request);
    void createProduct(ProductDto productDto, MultipartFile avatar);
    void updateProduct(Long id, ProductDto productDto, MultipartFile avatar);
    ProductDetailDto getProductById(Long id);
    void deleteProductById(Long id);
}
