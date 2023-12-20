package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.service;

import org.springframework.data.domain.Page;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.category.CategoryDetailDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.category.CreatCategoryDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.category.UpdateCategoryDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.Category;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.request.Request;

public interface CategoryService extends BaseService<Category, Long>{
    Page<CreatCategoryDto> categoriesList(Request request);
    CategoryDetailDto getCategoryById(Long id);
    void createCategory(CreatCategoryDto creatCategoryDto);
    void updateCategory(Long id, UpdateCategoryDto updateCategoryDto);
    void deleteCategoryById(Long id);
}
