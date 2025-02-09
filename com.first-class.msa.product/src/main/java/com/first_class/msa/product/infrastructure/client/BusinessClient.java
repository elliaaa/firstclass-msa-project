package com.first_class.msa.product.infrastructure.client;

import com.first_class.msa.product.application.dto.external.ExternalResBusinessGetByIdDTO;
import com.first_class.msa.product.application.service.BusinessService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "business-service")
public interface BusinessClient extends BusinessService {

    @GetMapping("/external/business/{businessId}")
    ExternalResBusinessGetByIdDTO getBy(@PathVariable("businessId") Long businessId);

    @GetMapping("/external/business/{businessId}/exists")
    boolean existsBy(@PathVariable("businessId") Long businessId);

}
