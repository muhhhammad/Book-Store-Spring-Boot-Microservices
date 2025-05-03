package com.practice.order.exception;

public class OrderNotFoundException extends RuntimeException{

    public OrderNotFoundException(String message){
        super(message);
    }

    public static OrderNotFoundException forOrderNumber(String orderNumber){
        return new OrderNotFoundException("Order With Order "+ orderNumber +" Not Found");
    }
}
