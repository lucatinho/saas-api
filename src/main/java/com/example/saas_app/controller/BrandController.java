package com.example.saas_app.controller;

import com.example.saas_app.domain.brand.Brand;
import com.example.saas_app.domain.brand.BrandRequestDTO;
import com.example.saas_app.domain.brand.BrandResponseDTO;
import com.example.saas_app.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping("/{id}")
    public ResponseEntity<BrandResponseDTO> getById(@PathVariable Long id) {
        BrandResponseDTO brand = brandService.getBrandById(id);
        return ResponseEntity.ok(brand);
    }

    @GetMapping
    public ResponseEntity<List<BrandResponseDTO>> getBrands(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        List<BrandResponseDTO> allBrands = this.brandService.getBrands(page, size);
        return ResponseEntity.ok(allBrands);
    }

    @PostMapping
    public ResponseEntity<Brand> create(@RequestBody BrandRequestDTO body) {
        Brand newBrand = this.brandService.createBrand(body);
        return ResponseEntity.ok(newBrand);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Brand> update(@PathVariable Long id, @RequestBody BrandRequestDTO body) {
        Brand updateBrand = this.brandService.updateBrand(id, body);
        return ResponseEntity.ok(updateBrand);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.brandService.deleteBrand(id);
        return ResponseEntity.noContent().build();
    }
}
