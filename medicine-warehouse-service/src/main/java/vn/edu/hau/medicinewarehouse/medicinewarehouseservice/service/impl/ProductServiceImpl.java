package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.product.ProductDetailDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.product.ProductDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.Category;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.Product;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.request.Request;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.repository.ProductRepository;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.service.ProductService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Service
public class ProductServiceImpl extends BaseServiceImpl<Product, Long> implements ProductService {
    private final ProductRepository productRepository;
    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/uploads/";
    public ProductServiceImpl(ProductRepository productRepository) {
        super(productRepository);
        this.productRepository = productRepository;
    }

    private boolean isEmptyUploadFile(MultipartFile avatar) {
        return avatar == null || Objects.requireNonNull(avatar.getOriginalFilename()).isEmpty();
    }

    @Override
    public Page<Product> getListProduct(Request request) {
        return null;
    }

    @Override
    public Product createOrUpdateProduct(Long id, ProductDto productDto, MultipartFile avatar) throws IOException {
        if(id != null && id > 0){
            Product product = this.productRepository.findById(id).orElseThrow(()-> new RuntimeException("Not found with id = " + id));
            product.setName(productDto.getName());
            product.setDescription(productDto.getDescription());
            product.setQuantity(productDto.getQuantity());
            if (!isEmptyUploadFile(avatar)) {
                Path filaeNameAndPath = Paths.get(UPLOAD_DIRECTORY, avatar.getOriginalFilename());
                Files.write(filaeNameAndPath,avatar.getBytes());
                product.setAvatar("/uploads/" + avatar.getOriginalFilename());
            }
            return this.productRepository.save(product);
        }else {
            Product product = new Product();
            product.setName(productDto.getName());
            product.setDescription(productDto.getDescription());
            product.setQuantity(productDto.getQuantity());
            if (!isEmptyUploadFile(avatar)) {
                Path filaeNameAndPath = Paths.get(UPLOAD_DIRECTORY, avatar.getOriginalFilename());
                Files.write(filaeNameAndPath,avatar.getBytes());
                product.setAvatar("/uploads/" + avatar.getOriginalFilename());
            }
            return this.productRepository.save(product);
        }
    }

    @Override
    public ProductDetailDto getProductById(Long id) {
        return null;
    }

    @Override
    public Boolean deleteProductById(Long id) {
        return null;
    }
}
