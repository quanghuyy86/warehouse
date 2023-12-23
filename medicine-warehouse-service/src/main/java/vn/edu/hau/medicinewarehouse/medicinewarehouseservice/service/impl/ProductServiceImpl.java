package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.common.dto.page.PageResponse;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.common.dto.page.PageResponseConverter;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.exception.ResourceNotFoundException;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.product.ProductDetailDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.product.CreateProductDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.product.ProductParamFilterDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.product.UpdateProductDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.Product;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.repository.CategoryRepository;
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
    private final CategoryRepository categoryRepository;
    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/uploads/";

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        super(productRepository);
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    private boolean isEmptyUploadFile(MultipartFile avatar) {
        return avatar != null && !Objects.requireNonNull(avatar.getOriginalFilename()).isEmpty();
    }

    private void createAvatar(MultipartFile avatar, Product product) {
        try {
            if (isEmptyUploadFile(avatar)) {
                Path filaeNameAndPath = Paths.get(UPLOAD_DIRECTORY, avatar.getOriginalFilename());
                Files.write(filaeNameAndPath, avatar.getBytes());
                product.setAvatar("/uploads/" + avatar.getOriginalFilename());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public PageResponse<ProductDetailDto> productsList(ProductParamFilterDto request) {
        Pageable pageable = Pageable.ofSize(request.getPageSize()).withPage(request.getPageNumber() - 1);
        Page<ProductDetailDto> list = productRepository.searchProduct(request.getKeyword(), request.getCategoryId(), pageable)
                .map(group -> new ProductDetailDto(
                        group.getId(),
                        group.getName(),
                        group.getDescription(),
                        group.getAvatar(),
                        group.getQuantity(),
                        group.getCategory().getName()
                ));
        return PageResponseConverter.convert(list);
    }

    @Override
    public void createProduct(CreateProductDto createProductDto, MultipartFile avatar) {
        categoryRepository.findById(createProductDto.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException("Not found category with id = " + createProductDto.getCategoryId()));
        if (productRepository.existsByName(createProductDto.getName())) {
            throw new ResourceNotFoundException("Đã có sản phẩm trong kho");
        }
        Product product = new Product();
        product.setName(createProductDto.getName());
        product.setDescription(createProductDto.getDescription());
        product.setQuantity(createProductDto.getQuantity());
        product.setCategoryId(createProductDto.getCategoryId());
        product.setCategoryId(createProductDto.getCategoryId());
        if (isEmptyUploadFile(avatar)) {
            createAvatar(avatar, product);
        }
        this.productRepository.save(product);
    }

    @Override
    public void updateProduct(Long id, UpdateProductDto updateProductDto, MultipartFile avatar) {
        Product product = this.productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found product with id = " + id));
        categoryRepository.findById(updateProductDto.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException("Not found category with id = " + updateProductDto.getCategoryId()));
        product.setName(updateProductDto.getName());
        product.setDescription(updateProductDto.getDescription());
        product.setQuantity(updateProductDto.getQuantity());
        product.setCategoryId(updateProductDto.getCategoryId());
        createAvatar(avatar, product);
        this.productRepository.save(product);
    }


    @Override
    public ProductDetailDto getProductById(Long id) {
        Product product = this.productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found Product with id = " + id));
        ProductDetailDto productDetailDto = new ProductDetailDto();
        productDetailDto.setId(product.getId());
        productDetailDto.setName(product.getName());
        productDetailDto.setQuantity(product.getQuantity());
        productDetailDto.setDescription(product.getDescription());
        productDetailDto.setCatgoryName(product.getCategory().getName());
        return productDetailDto;
    }

    @Override
    public void deleteProductById(Long id) {
        Product product = this.productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found product with id = " + id));
        product.setDeleted(true);
        this.productRepository.save(product);
    }
}
