package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.exception.ResourceNotFoundException;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.product.ProductDetailDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.product.ProductDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.Category;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.Product;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.request.Request;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.repository.CategoryRepository;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.repository.ProductRepository;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.service.ProductService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

@Service
public class ProductServiceImpl extends BaseServiceImpl<Product, Long> implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/uploads/";
    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        super(productRepository);
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    private boolean isEmptyUploadFile(MultipartFile avatar) {
        return avatar == null || Objects.requireNonNull(avatar.getOriginalFilename()).isEmpty();
    }
    public List<Product> searchProduct(String keyword,  Category category ){
        return productRepository.findByNameContainingAndCategory(keyword, category);
    }

    @Override
    public List<Product> getListProduct(Request request, Category category) {
        return this.searchProduct(request.getKeyword(), category);
    }

    @Override
    public Product createProduct(ProductDto productDto, MultipartFile avatar) {
        categoryRepository.findById(productDto.getCategoryId()).orElseThrow(()->new ResourceNotFoundException("Not found category with id = " + productDto.getCategoryId()));
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setQuantity(productDto.getQuantity());
        product.setCategoryId(productDto.getCategoryId());
        product.setCategoryId(productDto.getCategoryId());
        if (!isEmptyUploadFile(avatar)) {
            try {
                if (!isEmptyUploadFile(avatar)) {
                    Path filaeNameAndPath = Paths.get(UPLOAD_DIRECTORY, avatar.getOriginalFilename());
                    Files.write(filaeNameAndPath,avatar.getBytes());
                    product.setAvatar("/uploads/" + avatar.getOriginalFilename());
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return this.productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, ProductDto productDto, MultipartFile avatar) {
        Product product = this.productRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Not found product with id = " + id));
        categoryRepository.findById(productDto.getCategoryId()).orElseThrow(()->new ResourceNotFoundException("Not found category with id = " + productDto.getCategoryId()));
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setQuantity(productDto.getQuantity());
        product.setCategoryId(productDto.getCategoryId());
        try {
            if (!isEmptyUploadFile(avatar)) {
                Path filaeNameAndPath = Paths.get(UPLOAD_DIRECTORY, avatar.getOriginalFilename());
                Files.write(filaeNameAndPath,avatar.getBytes());
                product.setAvatar("/uploads/" + avatar.getOriginalFilename());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return this.productRepository.save(product);
    }

    @Override
    public ProductDetailDto getProductById(Long id) {
        Product product = this.productRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Not found Product with id = " + id));
        ProductDetailDto productDetailDto = new ProductDetailDto();
        productDetailDto.setId(product.getId());
        productDetailDto.setName(product.getName());
        productDetailDto.setQuantity(product.getQuantity());
        productDetailDto.setDescription(product.getDescription());
        productDetailDto.setCatgoryName(product.getCategory().getName());
        return productDetailDto;
    }

    @Override
    public Boolean deleteProductById(Long id) {
        Product product = this.productRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Not found product with id = " + id));
        product.setDeleted(true);
        this.productRepository.save(product);
        return true;
    }
}
