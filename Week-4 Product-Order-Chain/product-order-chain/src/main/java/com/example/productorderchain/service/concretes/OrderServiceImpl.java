package com.example.productorderchain.service.concretes;

import com.example.productorderchain.converter.abstracts.OrderConverter;
import com.example.productorderchain.core.utilities.Result;
import com.example.productorderchain.core.utilities.SuccessResult;
import com.example.productorderchain.dto.process.create.CreateOrderRequestDTO;
import com.example.productorderchain.dto.process.get.GetOrderResponseDTO;
import com.example.productorderchain.exception.BaseException;
import com.example.productorderchain.exception.BusinessServiceOperationException;
import com.example.productorderchain.model.Customer;
import com.example.productorderchain.model.Order;
import com.example.productorderchain.repository.OrderRepository;
import com.example.productorderchain.service.abstracts.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {


    private final OrderRepository orderRepository;
    private final OrderConverter orderConverter;


    @Override
    public Result createOrder(CreateOrderRequestDTO createOrderRequestDTO) {
        Order order = orderConverter.toOrder(createOrderRequestDTO);
        orderRepository.save(order);

        return new Result(true,"Order is created successfully");
    }

    @Override
    public Order getOrder(Long id) throws BaseException {
        Order order = orderRepository.findById(id)
                .orElseThrow(()-> new BusinessServiceOperationException.OrderNotFoundException("Order is not found"));
        if (order.isDeleted()) {
            throw new BusinessServiceOperationException.OrderAlreadyDeletedException("Order was deleted");
        }
        return order;
    }

    @Override
    public Collection<GetOrderResponseDTO> getAllOrders() {
        return orderRepository
                .findAllProductsByDeleteStatusByJPQL(false)
                .stream()
                .map(orderConverter::toGetOrderResponse)
                .toList();
    }

    @Override
    public Result cancelOrder(Long id, boolean hardDelete) throws BaseException {
       Order order = orderRepository
                .findById(id)
                .orElseThrow(() -> new BusinessServiceOperationException.OrderNotFoundException("Order is not found"));
        if (order.isDeleted()) {
            throw new BusinessServiceOperationException.OrderAlreadyDeletedException("Order was already deleted");
        }
        if (hardDelete) {
            orderRepository.delete(order);
            return new SuccessResult("Order "+order.getOrderNumber()+" is deleted with HardDelete successfully");
        }
        order.setDeleted(true);
        orderRepository.save(order);
        return new SuccessResult("Order "+order.getOrderNumber()+" is deleted with SoftDelete successfully");
    }
    }

