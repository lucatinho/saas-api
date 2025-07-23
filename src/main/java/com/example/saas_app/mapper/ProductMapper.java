package com.example.saas_app.mapper;

import com.example.saas_app.domain.product.Product;
import com.example.saas_app.domain.product.ProductRequestDTO;
import com.example.saas_app.domain.product.ProductResponseDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses =  {ProductBatchMapper.class})
public interface ProductMapper {

    @Mapping(target = "brand", ignore = true)
    @Mapping(target = "categoryProd", ignore = true)
    Product toEntity(ProductRequestDTO dto);

    @Mapping(source = "brand.name", target = "brand")
    @Mapping(source = "categoryProd.name", target = "category_prod")
    @Mapping(source = "productBatch", target = "productBatch")
    ProductResponseDTO toResponseDTO(Product product);
}
