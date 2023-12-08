package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.category.CategoryDetailDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.category.CategoryDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.Category;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.request.CategoryRequest;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.response.ResponseHandler;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.service.CategoryService;

@RestController
@RequestMapping("categories")
public class CategoryController extends BaseController<Category, Long> {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        super(categoryService);
        this.categoryService = categoryService;
    }

    @GetMapping("get-list-categories")
    public ResponseEntity<Object> getListCategories(CategoryRequest request){
        Page<CategoryDto> data = categoryService.categoriesList(request);
        return ResponseHandler.generateResponse(HttpStatus.OK, "Lấy danh sách thành công", data);
    }

    @PostMapping("create-category")
    public ResponseEntity<Object> cteatecategory(@RequestBody CategoryDto categoryDto){
        Category category = categoryService.createOrUpdateCategory(null, categoryDto);
        return ResponseHandler.generateResponse(HttpStatus.CREATED, "Cập nhật thành công", category);
    }

    @GetMapping("get-category/{id}")
    public ResponseEntity<Object> getCategoryById(@PathVariable("id") Long id){
        CategoryDetailDto category = categoryService.getCategoryById(id);
        return ResponseHandler.generateResponse(HttpStatus.OK,"", category);
    }

    @PutMapping("update-category/{id}")
    public ResponseEntity<Object> updateCategory(@PathVariable("id") Long id,
                                                 @RequestBody CategoryDto  categoryDto){
        Category category = categoryService.createOrUpdateCategory(id, categoryDto);
        return ResponseHandler.generateResponse(HttpStatus.CREATED, "Cập nhật thành công", category);
    }

    @DeleteMapping("delete-category/{id}")
    public ResponseEntity<Object> deleteCategory(@PathVariable("id") Long id){
        Boolean delete = categoryService.deleteCategoryById(id);
        return ResponseHandler.generateResponse(HttpStatus.OK, "Xóa thành công", delete);
    }
}
