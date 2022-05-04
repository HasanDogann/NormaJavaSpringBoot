package com.example.productorderchain.service.abstracts;

import com.example.productorderchain.core.utilities.Result;
import com.example.productorderchain.dto.process.create.CreateOrderRequestDTO;
import com.example.productorderchain.dto.process.get.GetOrderResponseDTO;
import com.example.productorderchain.exception.BaseException;
import com.example.productorderchain.model.Order;
import java.util.Collection;

public interface OrderService {


    Result createOrder(CreateOrderRequestDTO createOrderRequestDTO);

    Order getOrder(Long id) throws BaseException;

    Collection<GetOrderResponseDTO> getAllOrders();

    Result cancelOrder(Long id, boolean hardDelete) throws BaseException;




}
