package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.category.CategoryDetailDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.category.CategoryDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.Category;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.request.Request;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.service.CategoryService;

@RestController
public class CategoryController extends BaseController<Category, Long> {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        super(categoryService);
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public ResponseEntity<Object> getListCategories(Request request){
        Page<CategoryDto> data = categoryService.categoriesList(request);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PostMapping("/categories")
    public ResponseEntity<Object> createCategory(@Validated  @RequestBody CategoryDto categoryDto){
        Category category = categoryService.createOrUpdateCategory(null, categoryDto);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<Object> getCategoryById(@PathVariable("id") Long id){
        CategoryDetailDto category = categoryService.getCategoryById(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<Object> updateCategory(@PathVariable("id") Long id,
                                                 @Validated @RequestBody CategoryDto  categoryDto){
        return new ResponseEntity<>(this.categoryService.createOrUpdateCategory(id, categoryDto),HttpStatus.OK);
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<Object> deleteCategory(@PathVariable("id") Long id){
        Boolean delete = categoryService.deleteCategoryById(id);
        return new ResponseEntity<>(delete, HttpStatus.NO_CONTENT);
    }
}
