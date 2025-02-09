package com.first_class.msa.orders.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.first_class.msa.orders.application.dto.ResOrderDTO;
import com.first_class.msa.orders.domain.model.Order;
import com.first_class.msa.orders.infrastructure.config.RabbitMQConfig;
import com.first_class.msa.orders.infrastructure.event.OrderCancelDeliveryEvent;
import com.first_class.msa.orders.infrastructure.event.OrderCreateDeliveryEvent;
import com.first_class.msa.orders.infrastructure.event.OrderCreateProductEvent;
import com.first_class.msa.orders.infrastructure.event.OrderDeleteDeliveryEvent;
import com.first_class.msa.orders.infrastructure.event.OrderDeleteProductEvent;
import com.first_class.msa.orders.infrastructure.messaging.OrderEventPublisher;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderEventServiceImpl implements OrderEventService{

	private final OrderEventPublisher orderEventPublisher;

	@Override
	public void orderCreateProductEvent(Order order) {
		OrderCreateProductEvent orderCreateProductEvent = OrderCreateProductEvent.from(order);
		orderEventPublisher.publishEvent(RabbitMQConfig.ORDER_CREATED_PRODUCT_KEY , orderCreateProductEvent);
	}

	@Override
	public void orderCreateDeliveryEvent(Order order) {
		OrderCreateDeliveryEvent orderCreateDeliveryEvent = OrderCreateDeliveryEvent.from(order);
		orderEventPublisher.publishEvent(RabbitMQConfig.ORDER_CREATED_DELIVERY_KEY , orderCreateDeliveryEvent);
	}

	@Override
	public void orderDeleteProductEvent(
		Long orderId,
		List<ResOrderDTO.OrderDTO.OrderLineDTO> orderLineDTOList) {

		OrderDeleteProductEvent orderDeleteProductEvent = new OrderDeleteProductEvent(orderId, orderLineDTOList);
		orderEventPublisher.publishEvent(RabbitMQConfig.ORDER_DELETED_PRODUCT_KEY , orderDeleteProductEvent);
	}

	@Override
	public void orderDeleteDeliveryEvent(Long orderId, Long userId) {
		OrderDeleteDeliveryEvent orderDeleteDeliveryEvent = new OrderDeleteDeliveryEvent(orderId, userId);
		orderEventPublisher.publishEvent(RabbitMQConfig.ORDER_DELETED_DELIVERY_KEY, orderDeleteDeliveryEvent);
	}

	@Override
	public void orderCancelEvent(Long userId, Long orderId){
		OrderCancelDeliveryEvent orderCancelDeliveryEvent = OrderCancelDeliveryEvent.of(userId, orderId);
		orderEventPublisher.publishEvent(RabbitMQConfig.ORDER_FAILED_KEY, orderId);
	}

}
