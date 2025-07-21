package com.example.saas_app.controller;

import com.example.saas_app.domain.category_prod.CategoryProd;
import com.example.saas_app.domain.category_prod.CategoryProdRequestDTO;
import com.example.saas_app.domain.category_prod.CategoryProdResponseDTO;
import com.example.saas_app.service.CategoryProdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category-prod")
public class CategoryProdController {

    @Autowired
    private CategoryProdService categoryProdService;

    @GetMapping("/{id}")
    public ResponseEntity<CategoryProdResponseDTO> getById(@PathVariable Long id) {
        CategoryProdResponseDTO category = categoryProdService.getCategoryProdById(id);
        return ResponseEntity.ok(category);
    }

    @GetMapping
    public ResponseEntity<List<CategoryProdResponseDTO>> getBrands(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        List<CategoryProdResponseDTO> allBrands = this.categoryProdService.getCategoryProds(page, size);
        return ResponseEntity.ok(allBrands);
    }

    @PostMapping
    public ResponseEntity<CategoryProd> create(@RequestBody CategoryProdRequestDTO body) {
        CategoryProd categoryProd = this.categoryProdService.createCategoryprod(body);
        return ResponseEntity.ok(categoryProd);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryProd> update(@PathVariable Long id, @RequestBody CategoryProdRequestDTO body) {
        CategoryProd updateCategoryProd = this.categoryProdService.updateBrand(id, body);
        return ResponseEntity.ok(updateCategoryProd);
    }
}
