package com.example.saas_app.service;

import com.example.saas_app.domain.category_prod.CategoryProd;
import com.example.saas_app.domain.category_prod.CategoryProdRequestDTO;
import com.example.saas_app.domain.category_prod.CategoryProdResponseDTO;
import com.example.saas_app.repositories.CategoryProdRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryProdService {

    @Autowired
    private CategoryProdRepository categoryProdRepository;

    public CategoryProd createCategoryprod(CategoryProdRequestDTO data) {
        CategoryProd newCategoryProd = new CategoryProd();
        newCategoryProd.setName(data.name());

        categoryProdRepository.save(newCategoryProd);

        return newCategoryProd;
    }

    public CategoryProdResponseDTO getCategoryProdById(Long id) {
        CategoryProd categoryProd = categoryProdRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Id nao encontrado"));
        return new CategoryProdResponseDTO(categoryProd.getId(), categoryProd.getName());
    }

    public List<CategoryProdResponseDTO> getCategoryProds(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CategoryProd> categoryProds = this.categoryProdRepository.findAll(pageable);
        return categoryProds.map(brand -> new CategoryProdResponseDTO(brand.getId(), brand.getName())).stream().toList();
    }

    public CategoryProd updateBrand(Long id, CategoryProdRequestDTO data) {
        CategoryProd newCategoryProd = categoryProdRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Id nao encontrado"));
        newCategoryProd.setName(data.name());

        categoryProdRepository.save(newCategoryProd);

        return newCategoryProd;
    }
}
