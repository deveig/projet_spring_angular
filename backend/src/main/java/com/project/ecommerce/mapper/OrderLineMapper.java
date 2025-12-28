package com.project.ecommerce.mapper;

import com.project.ecommerce.dto.OrderLineDTO;
import com.project.ecommerce.entity.OrderLine;
import com.project.ecommerce.entity.OrderLineId;
import org.springframework.stereotype.Component;

@Component
public class OrderLineMapper {

    public OrderLine toEntity(OrderLineDTO dto){
        if(dto == null){
            return null;
        }
        OrderLine orderLine = new OrderLine();
        orderLine.setPrice(dto.getPrice());
        orderLine.setQuantity(dto.getQuantity());
        orderLine.setCommand(dto.getCommand());
        orderLine.setProduct(dto.getProduct());
        return orderLine;
    }

    public OrderLineDTO toDto(OrderLine orderLine){
        if(orderLine == null){
            return null;
        }
        OrderLineDTO dto = new OrderLineDTO();
        dto.setId(orderLine.getId());
        dto.setPrice(orderLine.getPrice());
        dto.setQuantity(orderLine.getQuantity());
        dto.setCommand(orderLine.getCommand());
        dto.setProduct(orderLine.getProduct());
        return dto;
    }

    public OrderLine updateEntity(OrderLineDTO dto, OrderLine orderLine){
        if (dto == null  || orderLine == null){
            return null;
        }
        orderLine.setPrice(dto.getPrice());
        orderLine.setQuantity(dto.getQuantity());
        orderLine.setCommand(dto.getCommand());
        orderLine.setProduct(dto.getProduct());
        return orderLine;
    }
}
