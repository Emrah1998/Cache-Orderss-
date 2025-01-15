package com.Orderss.Orderss.CONTROLLER;

import com.Orderss.Orderss.MODEL.Request.CreateOrderRequest;
import com.Orderss.Orderss.MODEL.Request.UpdateAgeRequest;
import com.Orderss.Orderss.MODEL.Response.OrderResponse;
import com.Orderss.Orderss.SERVICE.OrderServiceHandle;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/orderss")
public class OrderController {
    private final OrderServiceHandle orderService;

    @PostMapping
    public void saveOrder(@RequestBody CreateOrderRequest order) {
        orderService.saveOrder(order);
    }

    @PatchMapping("/{id}/age")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updataOrderAge(@PathVariable Long id,
                               @RequestBody UpdateAgeRequest request){
        orderService.updateOrderAge(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable Long id){
        orderService.deleteOrder(id);
    }

    @GetMapping("/{id}")
    public OrderResponse getOrder(@PathVariable Long id){
        return orderService.getOrder(id);
    }

    @DeleteMapping("/cache")
    public void deleteCache(){
        orderService.deleteCache();
    }

    @PutMapping("/cache")
    public OrderResponse updateCache(@RequestParam Long id){
        return orderService.updateCache(id);
    }


}
