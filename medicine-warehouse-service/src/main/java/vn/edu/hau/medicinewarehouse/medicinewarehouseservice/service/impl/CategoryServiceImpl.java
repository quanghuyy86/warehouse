package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.exception.ResourceNotFoundException;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.category.CategoryDetailDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.category.CategoryDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.Category;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.request.Request;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.repository.CategoryRepository;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.service.CategoryService;

import java.util.List;

@Service
public class CategoryServiceImpl extends BaseServiceImpl<Category, Long> implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        super(categoryRepository);
        this.categoryRepository = categoryRepository;
    }

    public List<Category> searchCategory(String keyword){
        return categoryRepository.findByNameContaining(keyword);
    }
    @Override
    public Page<CategoryDto> categoriesList(Request request) {
        List list = this.searchCategory(request.getKeyword());
        Pageable pageable = PageRequest.of(request.getPage(), request.getPageSize());
        return new PageImpl<CategoryDto>(list, pageable, this.searchCategory(request.getKeyword()).size());
    }
    @Override
    public CategoryDetailDto getCategoryById(Long id) {
            Category category = this.categoryRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Not found category with id = " + id));
            CategoryDetailDto categoryDetailDto = new CategoryDetailDto();
            categoryDetailDto.setId(category.getId());
            categoryDetailDto.setName(category.getName());
            categoryDetailDto.setDescription(category.getDescription());
            categoryDetailDto.setCreatedAt(category.getCreatedAt());
            categoryDetailDto.setUpdatedAt(category.getUpdatedAt());
            return categoryDetailDto;
    }

    @Override
    public Category createOrUpdateCategory(Long id, CategoryDto categoryDto) {
            if(id != null && id > 0){
                Category categoryEntity = this.categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found category with id = " + id));
                categoryEntity.setName(categoryDto.getName());
                categoryEntity.setDescription(categoryDto.getDescription());
                return categoryRepository.save(categoryEntity);
            }else {
                Category categoryEntity = new Category();
                categoryEntity.setName(categoryDto.getName());
                categoryEntity.setDescription(categoryDto.getDescription());
                return categoryRepository.save(categoryEntity);
            }
    }

    @Override
    public Boolean deleteCategoryById(Long id) {
        try {
            Category category = this.categoryRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Not found category with id = " + id));
            category.setDeleted(true);
            this.repository.save(category);
            return true;
        } catch (Exception var3) {
            return false;
        }
    }
}
