package com.first_class.msa.delivery.infrastructure.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.first_class.msa.delivery.domain.model.Delivery;
import com.first_class.msa.delivery.domain.repository.DeliveryRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class DeliveryRepositoryImpl implements DeliveryRepository {
	private final DeliveryJpaRepository deliveryJpaRepository;

	@Override
	public Delivery save(Delivery delivery) {
		return deliveryJpaRepository.save(delivery);
	}

	@Override
	public Optional<Delivery> findByIdAndIsNotNULL(Long deliveryId) {
		return deliveryJpaRepository.findByIdAndDeletedByIsNotNull(deliveryId);
	}

	@Override
	public Optional<Delivery> findById(Long deliveryId) {
		return deliveryJpaRepository.findById(deliveryId);
	}

	@Override
	public List<Delivery> findAllByOrderIdInAndDeletedByIsNull(List<Long> orderIdList) {
		return deliveryJpaRepository.findAllByOrderIdInAndDeletedByIsNull(orderIdList);
	}

	@Override
	public boolean existsByIdAndOrderId(Long deliveryId, Long orderId) {
		return deliveryJpaRepository.existsByIdAndOrderId(deliveryId, orderId);
	}

}
