package com.example.saas_app.mapper;

import com.example.saas_app.domain.product.Product;
import com.example.saas_app.domain.product.ProductResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses =  {ProductMapper.class})
public interface ProductMapper {

    @Mapping(source = "brand.name", target = "brand")
    @Mapping(source = "categoryProd.name", target = "category_prod")
    @Mapping(source = "productBatch", target = "productBatch")
    ProductResponseDTO toResponseDTO(Product product);

}
