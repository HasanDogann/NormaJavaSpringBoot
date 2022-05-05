package com.example.productorderchain.controller;


import com.example.productorderchain.core.utilities.Result;
import com.example.productorderchain.core.utilities.SuccessDataResult;
import com.example.productorderchain.dto.process.create.CreateOrderRequestDTO;
import com.example.productorderchain.dto.process.get.GetOrderResponseDTO;
import com.example.productorderchain.model.Order;
import com.example.productorderchain.service.abstracts.OrderService;
import com.example.productorderchain.validator.IDValidation.OrderIDValidator;
import com.example.productorderchain.validator.Validator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("api/orders")
public class OrderController {

    private final OrderService orderService;

    private final Validator<CreateOrderRequestDTO> createOrderRequestDTOValidator;

    private final OrderIDValidator orderIDValidator;

    public OrderController(OrderService orderService, Validator<CreateOrderRequestDTO> createOrderRequestDTOValidator,@Qualifier(value = "orderIDQ") OrderIDValidator orderIDValidator) {
        this.orderService = orderService;
        this.createOrderRequestDTOValidator = createOrderRequestDTOValidator;
        this.orderIDValidator = orderIDValidator;
    }

    @PostMapping
    ResponseEntity<?> createOrder(@RequestBody CreateOrderRequestDTO createOrderRequestDTO){
        createOrderRequestDTOValidator.validate(createOrderRequestDTO);
        Result result = orderService.createOrder(createOrderRequestDTO);
        return ResponseEntity.ok().body(result.getMessage());
    }

    @GetMapping
    SuccessDataResult<Collection<GetOrderResponseDTO>> getAllOrders(){
        orderService.getAllOrders();
        return new SuccessDataResult<>(orderService.getAllOrders(),"Orders are listed successfully");
    }
    @GetMapping("/{id}")
    public ResponseEntity<SuccessDataResult<Order>> getOrder(@PathVariable Long id) {
        orderIDValidator.validate(id);
        return ResponseEntity.ok(new SuccessDataResult<>(orderService.getOrder(id),"Order is listed"));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> cancelOrder(@PathVariable Long id,
                                          @RequestParam(name = "hardDelete", required = false) boolean hardDelete) {
        orderIDValidator.validate(id);
        Result result =orderService.cancelOrder(id,hardDelete);
        return ResponseEntity.ok().body(result.getMessage());
    }




}
