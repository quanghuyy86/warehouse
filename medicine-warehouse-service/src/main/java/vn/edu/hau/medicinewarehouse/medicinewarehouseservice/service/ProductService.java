package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.service;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.product.ProductDetailDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.product.ProductDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.Category;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.Product;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.request.Request;

import java.io.IOException;
import java.util.List;

public interface ProductService extends BaseService<Product, Long>{
    List<Product> getListProduct(Request request, Category category);
    void createProduct(ProductDto productDto, MultipartFile avatar);
    void updateProduct(Long id, ProductDto productDto, MultipartFile avatar);
    ProductDetailDto getProductById(Long id);
    void deleteProductById(Long id);
}
