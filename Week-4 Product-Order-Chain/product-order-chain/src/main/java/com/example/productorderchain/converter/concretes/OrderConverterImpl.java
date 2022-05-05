package com.example.productorderchain.converter.concretes;

import com.example.productorderchain.converter.abstracts.OrderConverter;
import com.example.productorderchain.dto.process.create.CreateOrderRequestDTO;
import com.example.productorderchain.dto.process.get.GetOrderResponseDTO;
import com.example.productorderchain.model.Customer;
import com.example.productorderchain.model.Order;
import com.example.productorderchain.model.OrderStatus;
import com.example.productorderchain.service.abstracts.BasketService;
import com.example.productorderchain.service.abstracts.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class OrderConverterImpl implements OrderConverter {

    private final BasketService basketService;
    private final CustomerService customerService;

    @Override
    public Order toOrder(CreateOrderRequestDTO createOrderRequestDTO) {
        Order order = new Order();
        order.setOrderNumber(new Random().nextLong(100_000_000,2_125_000_000));
        order.setCustomer(customerService.getCustomer(createOrderRequestDTO.customerId()));
        order.setBasket(basketService.getBasket(createOrderRequestDTO.basketID()));
        order.setOrderTotalPrice((basketService.getBasket(createOrderRequestDTO.basketID()).getTotalPrice()).subtract(customerBasedCoupon(customerService.getCustomer(createOrderRequestDTO.customerId()))));
        order.setOrderTaxPrice(basketService.getBasket(createOrderRequestDTO.basketID()).getTaxPrice());
        order.setOrderShipmentPrice(basketService.getBasket(createOrderRequestDTO.basketID()).getShippingPrice());
        order.setCustomerShippingAddress(customerService.getCustomer(createOrderRequestDTO.customerId()).getCustomerAddress());
        order.setCustomerBillingAddress(customerService.getCustomer(createOrderRequestDTO.customerId()).getCustomerAddress());
        order.setPaymentMethod(createOrderRequestDTO.paymentMethod());
        order.setPaymentInfo("Order is created successfully");
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        order.setOrderDate(dateFormat.format(date));
        order.setOrderStatus(OrderStatus.PROCESSING);

        return order;
    }

    @Override
    public GetOrderResponseDTO toGetOrderResponse(Order order) {
        return new GetOrderResponseDTO(order.getOrderNumber(),
                order.getOrderTotalPrice(),order.getOrderTaxPrice(),order.getOrderShipmentPrice(), order.getPaymentInfo(),order.getOrderDate(),order.getPaymentMethod(),order.getOrderStatus(), order.getId());
    }

    //This method checks if there is a defined customer coupon and returns this coupon price
    public BigDecimal customerBasedCoupon(Customer customer){
        Customer c1=customerService.getCustomer(customer.getId());
        if(!c1.getDiscountCoupon().equals(BigDecimal.ZERO) && (c1.getDiscountCoupon()).compareTo(BigDecimal.ZERO)>0){
            return c1.getDiscountCoupon();
        }
        return BigDecimal.ZERO;
    }


}
