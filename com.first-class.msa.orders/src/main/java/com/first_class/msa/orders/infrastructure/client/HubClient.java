package com.first_class.msa.orders.infrastructure.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.first_class.msa.orders.application.service.HubService;

@FeignClient(name = "hub-service")
public interface HubClient extends HubService {

	@GetMapping("/external/hubs/user/{userId}/id")
	Long getHubByID(@PathVariable(name = "userId") Long userId);

}
