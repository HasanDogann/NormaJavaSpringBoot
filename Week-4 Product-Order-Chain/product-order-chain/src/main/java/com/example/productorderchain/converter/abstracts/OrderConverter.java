package com.example.productorderchain.converter.abstracts;

import com.example.productorderchain.dto.process.create.CreateOrderRequestDTO;
import com.example.productorderchain.dto.process.get.GetOrderResponseDTO;
import com.example.productorderchain.model.Order;

public interface OrderConverter {

    Order toOrder(CreateOrderRequestDTO createOrderRequestDTO);

    GetOrderResponseDTO toGetOrderResponse(Order order);


}
