package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.common.dto.page.PageResponse;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.common.dto.page.PageResponseConverter;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.exception.ResourceNotFoundException;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.category.CategoryDetailDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.category.CategoryParamFilterDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.category.CreatCategoryDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.category.UpdateCategoryDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.Category;
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

    @Override
    public PageResponse<CategoryDetailDto> categoriesList(CategoryParamFilterDto request) {
        Pageable pageable = Pageable.ofSize(request.getPageSize()).withPage(request.getPageNumber() - 1);
        Page<CategoryDetailDto> medicalGroupList = categoryRepository.searchCategories(request.getKeyword(), pageable)
                .map(group -> new CategoryDetailDto(
                        group.getId(),
                        group.getName(),
                        group.getDescription()
                ));

        return PageResponseConverter.convert(medicalGroupList);
    }

    @Override
    public List<Category> findAll() {
        return this.categoryRepository.findAll();
    }

    @Override
    public CategoryDetailDto getCategoryById(Long id) {
        Category category = this.categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found category with id = " + id));
        CategoryDetailDto categoryDetailDto = new CategoryDetailDto();
        categoryDetailDto.setId(category.getId());
        categoryDetailDto.setName(category.getName());
        categoryDetailDto.setDescription(category.getDescription());
        return !category.isDeleted() ? categoryDetailDto : null;
    }

    @Override
    public void createCategory(CreatCategoryDto creatCategoryDto) {
        if (this.categoryRepository.existsByName(creatCategoryDto.getName())) {
            throw new ResourceNotFoundException("Tên nhóm thuốc đã tồn tại!");
        }
        Category categoryEntity = new Category();
        categoryEntity.setName(creatCategoryDto.getName());
        categoryEntity.setDescription(creatCategoryDto.getDescription());
        categoryRepository.save(categoryEntity);
    }

    @Override
    public void updateCategory(Long id, UpdateCategoryDto updateCategoryDto) {
//        Optional<String> name = Optional.ofNullable(updateCategoryDto.getName());
//        if (name.isPresent() && categoryRepository.existsByName(name.get())) {
//            throw new ResourceNotFoundException("Tên nhóm thuốc đã tồn tại!");
//        }
//        categoryRepository.findById(id)
//                .map(group -> {
//                    Optional.ofNullable(updateCategoryDto.getName()).ifPresent(group::setName);
//                    Optional.ofNullable(updateCategoryDto.getDescription()).ifPresent(group::setDescription);
//                    return group;
//                })
//                .map(categoryRepository::save).orElseThrow(() -> new ResourceNotFoundException("Not found medical group with id = " + id));
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found medical group with id = " + id));
        category.setName(updateCategoryDto.getName());
        category.setDescription(updateCategoryDto.getDescription());
        categoryRepository.save(category);
    }


    @Override
    public void deleteCategoryById(Long id) {
        Category category = this.categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found category with id = " + id));
        category.setDeleted(true);
        this.repository.save(category);
    }
}
