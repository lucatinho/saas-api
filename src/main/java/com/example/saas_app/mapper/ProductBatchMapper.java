package com.example.saas_app.mapper;

import com.example.saas_app.domain.product_batch.ProductBatch;
import com.example.saas_app.domain.product_batch.ProductBatchResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductBatchMapper {

    ProductBatchResponseDTO toProductBatchResponseDto(ProductBatch productBatch);
}
