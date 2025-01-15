package com.Orderss.Orderss.DAO.Repository;

import com.Orderss.Orderss.DAO.Entity.OrderEntity;
import com.Orderss.Orderss.MODEL.Enum.OrderStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface OrderRepository extends CrudRepository<OrderEntity, Long> {

    Optional<OrderEntity> findByIdAndStatusNot(Long id, OrderStatus status);
}

