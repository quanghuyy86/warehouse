package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.service;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.product.ProductDetailDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.product.ProductDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.Product;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.request.Request;

import java.io.IOException;

public interface ProductService extends BaseService<Product, Long>{
    Page<Product> getListProduct(Request request);
    Product createOrUpdateProduct(Long id,ProductDto productDto, MultipartFile avatar) throws IOException;
    ProductDetailDto getProductById(Long id);
    Boolean deleteProductById(Long id);
}
