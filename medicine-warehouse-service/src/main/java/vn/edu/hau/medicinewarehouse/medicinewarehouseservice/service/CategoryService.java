package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.service;

import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.common.dto.page.PageResponse;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.category.CategoryDetailDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.category.CategoryParamFilterDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.category.CreatCategoryDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.category.UpdateCategoryDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.Category;

public interface CategoryService extends BaseService<Category, Long>{
    PageResponse<CategoryDetailDto> categoriesList(CategoryParamFilterDto request);
    CategoryDetailDto getCategoryById(Long id);
    void createCategory(CreatCategoryDto creatCategoryDto);
    void updateCategory(Long id, UpdateCategoryDto updateCategoryDto);
    void deleteCategoryById(Long id);
}
