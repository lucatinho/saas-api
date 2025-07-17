package com.example.saas_app.service;

import com.example.saas_app.domain.brand.Brand;
import com.example.saas_app.domain.brand.BrandRequestDTO;
import com.example.saas_app.domain.brand.BrandResponseDTO;
import com.example.saas_app.repositories.BrandRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService {

    @Autowired
    private BrandRepository brandRepository;

    public Brand createBrand(BrandRequestDTO data) {
        Brand newBrand = new Brand();
        newBrand.setName(data.name());

        brandRepository.save(newBrand);

        return newBrand;
    }

    public BrandResponseDTO getBrandById(Long id) {
        Brand brand = brandRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Id nao encontrado"));
        return new BrandResponseDTO(brand.getId(), brand.getName());
    }

    public List<BrandResponseDTO> getBrands(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Brand> brandsPage = this.brandRepository.findAll(pageable);
        return brandsPage.map(brand -> new BrandResponseDTO(brand.getId(), brand.getName())).stream().toList();
    }

    public Brand updateBrand(Long id, BrandRequestDTO data) {
        Brand newBrand = brandRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Id nao encontrado"));
        newBrand.setName(data.name());

        brandRepository.save(newBrand);

        return newBrand;
    }

    public void deleteBrand(Long id) {
        if (!brandRepository.existsById(id)) {
            throw new EntityNotFoundException("Id nao encontrado");
        }
        brandRepository.deleteById(id);
    }
}
