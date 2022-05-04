package com.example.productorderchain.converter.concretes;

import com.example.productorderchain.converter.abstracts.OrderConverter;
import com.example.productorderchain.dto.process.create.CreateOrderRequestDTO;
import com.example.productorderchain.dto.process.get.GetOrderResponseDTO;
import com.example.productorderchain.model.Basket;
import com.example.productorderchain.model.Customer;
import com.example.productorderchain.model.Order;
import com.example.productorderchain.model.OrderStatus;
import com.example.productorderchain.service.abstracts.BasketService;
import com.example.productorderchain.service.abstracts.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class OrderConverterImpl implements OrderConverter {

    private final BasketService basketService;
    private final CustomerService customerService;

    @Override
    public Order toOrder(CreateOrderRequestDTO createOrderRequestDTO) {
        Order order = new Order();
        order.setOrderNumber(new Random().nextLong());
        order.setCustomer(customerService.getCustomer(createOrderRequestDTO.customerId()));
        order.setBasket(basketService.getBasket(createOrderRequestDTO.basketID()));
        order.setOrderTotalPrice(basketService.getBasket(createOrderRequestDTO.basketID()).getTotalPrice());
        order.setOrderTaxPrice(basketService.getBasket(createOrderRequestDTO.basketID()).getTaxPrice());
        order.setOrderShipmentPrice(basketService.getBasket(createOrderRequestDTO.basketID()).getShippingPrice());
        order.setCustomerShippingAddress(customerService.getCustomer(createOrderRequestDTO.customerId()).getCustomerAddress());
        order.setCustomerBillingAddress(customerService.getCustomer(createOrderRequestDTO.customerId()).getCustomerAddress());
        order.setPaymentMethod(createOrderRequestDTO.paymentMethod().name());
        order.setPaymentInfo("Order is created successfully");
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        order.setOrderDate(dateFormat.format(date));
        order.setOrderStatus(OrderStatus.PROCESSING);

        return order;
    }

    @Override
    public CreateOrderRequestDTO toCreateOrderRequest(Order order) {
        return null;
    }

    @Override
    public GetOrderResponseDTO toGetOrderResponse(Order order) {
        return new GetOrderResponseDTO(order.getOrderNumber(),order.getOrderTotalPrice(),order.getOrderTaxPrice(),order.getOrderShipmentPrice(),
                                       order.getCustomerShippingAddress(),order.getPaymentInfo(),order.getOrderDate(),order.getOrderStatus(), order.getId());
    }
}
