package com.first_class.msa.orders.infrastructure.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.first_class.msa.orders.application.dto.ResBusinessDTO;
import com.first_class.msa.orders.application.service.BusinessService;
@FeignClient(name = "business-service")
public interface BusinessClient extends BusinessService {

	@GetMapping({"/external/business/{businessId}"})
	ResBusinessDTO getBusinessBy(@PathVariable(name = "businessId") Long businessId);

	@GetMapping({"/external/business"})
	ResBusinessDTO getBusinessUserBy(@RequestParam Long userId);
}
