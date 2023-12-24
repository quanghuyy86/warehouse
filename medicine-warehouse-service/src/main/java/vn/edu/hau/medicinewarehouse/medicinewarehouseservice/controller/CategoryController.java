package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.common.dto.page.PageResponse;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.common.response.ApiResponse;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.common.response.ApiResponseCode;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.common.response.ApiResponseGenerator;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.category.CategoryDetailDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.category.CategoryParamFilterDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.category.CreatCategoryDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.category.UpdateCategoryDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.Category;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.service.CategoryService;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@Slf4j
public class CategoryController{

    private final CategoryService categoryService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ApiResponse<PageResponse<CategoryDetailDto>>> getListCategories(CategoryParamFilterDto request) {
        PageResponse<CategoryDetailDto> data = categoryService.categoriesList(request);
        return new ResponseEntity<>(ApiResponseGenerator.success(ApiResponseCode.SUCCESS, "get list category", data), HttpStatus.OK);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ApiResponse<Void>> createCategory(@Validated @RequestBody CreatCategoryDto creatCategoryDto) {
        categoryService.createCategory(creatCategoryDto);
        return new ResponseEntity<>(ApiResponseGenerator.success(ApiResponseCode.CREATED, "create category"), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ApiResponse<CategoryDetailDto>> getCategoryById(@PathVariable("id") Long id) {
        CategoryDetailDto category = categoryService.getCategoryById(id);
        return new ResponseEntity<>(ApiResponseGenerator.success(ApiResponseCode.SUCCESS, "Get by id category", category), HttpStatus.OK);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ApiResponse<Void>> updateCategory(@PathVariable("id") Long id,
                                                            @Validated @RequestBody UpdateCategoryDto updateCategoryDto) {
        this.categoryService.updateCategory(id, updateCategoryDto);
        return new ResponseEntity<>(ApiResponseGenerator.success(ApiResponseCode.SUCCESS, "update category"), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<ApiResponse<Void>> deleteCategory(@PathVariable("id") Long id) {
        categoryService.deleteCategoryById(id);
        return new ResponseEntity<>(ApiResponseGenerator.success(ApiResponseCode.NO_CONTENT, "delete category"), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/get-all")
    public ResponseEntity<Object> getAll() {
        List<Category> entities = categoryService.findAll();
        entities.sort(Comparator.comparing(Category::getName));

        return new ResponseEntity<>(entities, HttpStatus.OK);
    }
}
