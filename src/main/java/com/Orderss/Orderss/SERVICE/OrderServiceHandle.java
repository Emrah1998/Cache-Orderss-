package com.Orderss.Orderss.SERVICE;

import com.Orderss.Orderss.DAO.Entity.OrderEntity;
import com.Orderss.Orderss.DAO.Repository.OrderRepository;
import com.Orderss.Orderss.MODEL.Enum.OrderStatus;
import com.Orderss.Orderss.MODEL.Request.CreateOrderRequest;
import com.Orderss.Orderss.MODEL.Request.UpdateAgeRequest;
import com.Orderss.Orderss.MODEL.Response.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

@Service
@RequiredArgsConstructor
public class OrderServiceHandle  {
    private final OrderRepository orderRepository;



    public void saveOrder(CreateOrderRequest order) {
        orderRepository.save(
                OrderEntity.builder()
                        .name(order.getName())
                        .surname(order.getSurname())
                        .age(order.getAge())
                        .build()
        );
    }


    public void updateOrderAge(Long id, UpdateAgeRequest request) {

        var order = orderRepository.findById(id)
                .orElseThrow(RuntimeException::new);
        order.setAge(request.getAge());
        orderRepository.save(order);
    }


    public void deleteOrder(Long id) {

        var order = fetchOrderIfExist(id);
        order.setStatus(OrderStatus.DELETED);
        orderRepository.save(order);
    }

    @Cacheable("getOrderss")
    public OrderResponse getOrder(Long id) {
        var order = fetchOrderIfExist(id);
        return new OrderResponse(order.getName(), order.getSurname(), order.getAge());
    }
    @CacheEvict(value = "getOrderss",allEntries = true)
    public void deleteCache(){
    }

    @CachePut("getOrderss")
    public OrderResponse updateCache(Long id){
        return getOrder(id);
    }

    private OrderEntity fetchOrderIfExist(Long id) {
        return orderRepository.findByIdAndStatusNot(id,OrderStatus.DELETED)
                .orElseThrow(RuntimeException::new);
        //.
    }

}
