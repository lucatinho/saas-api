package com.example.saas_app.mapper;

import com.example.saas_app.domain.product_batch.ProductBatch;
import com.example.saas_app.domain.product_batch.ProductBatchRequestDTO;
import com.example.saas_app.domain.product_batch.ProductBatchResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductBatchMapper {

    ProductBatch toEntity(ProductBatchRequestDTO dto);

    @Mapping(source = "product.id", target = "product_id")
    ProductBatchResponseDTO toProductBatchResponseDto(ProductBatch productBatch);

    @Mapping(target = "id", ignore = true)
    void updateProductBatchFromDto(ProductBatchRequestDTO dto, @MappingTarget ProductBatch entity);
}
