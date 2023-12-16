package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.service;

import org.springframework.data.domain.Page;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.category.CategoryDetailDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.category.CategoryDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.Category;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.request.Request;

public interface CategoryService extends BaseService<Category, Long>{
    Page<CategoryDto> categoriesList(Request request);
    CategoryDetailDto getCategoryById(Long id);
    Category createCategory(CategoryDto categoryDto);
    Category updateCategory(Long id, CategoryDto categoryDto);
    Boolean deleteCategoryById(Long id);
}
