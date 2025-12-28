package com.project.ecommerce.service;

import com.project.ecommerce.dto.OrderLineDTO;
import com.project.ecommerce.entity.OrderLine;
import com.project.ecommerce.exception.ResourceNotFoundException;
import com.project.ecommerce.mapper.OrderLineMapper;
import com.project.ecommerce.repository.OrderLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderLineService {
    @Autowired
    private OrderLineRepository orderLineRepository;

    @Autowired
    private OrderLineMapper orderLineMapper;

    @Transactional(readOnly = true)
    public List<OrderLineDTO> getAll(){
        return orderLineRepository.findAll().stream().map((u) -> orderLineMapper.toDto(u)).toList();
    }

    @Transactional(readOnly = true)
    public OrderLineDTO getById(Long commandId, Long productId){
        return orderLineMapper.toDto(orderLineRepository.findByCommandIdAndProductId(commandId, productId).orElseThrow(() -> new ResourceNotFoundException("OrderLine", "id", commandId + productId)));
    }

    public OrderLineDTO saveOrderLine(OrderLineDTO orderLineDTO){
        OrderLine orderLine = orderLineMapper.toEntity(orderLineDTO);
        return orderLineMapper.toDto(orderLineRepository.save(orderLine));
    }

    public OrderLineDTO update(Long commandId, Long productId, OrderLineDTO orderLineDTO){
        OrderLine orderLine = orderLineRepository.findByCommandIdAndProductId(commandId, productId).orElseThrow(() -> new ResourceNotFoundException("OrderLine", "id", commandId + productId));
        return orderLineMapper.toDto(orderLineRepository.save(orderLineMapper.updateEntity(orderLineDTO, orderLine)));
    }

    public void delete(Long commandId, Long productId){
        OrderLine orderLine = orderLineRepository.findByCommandIdAndProductId(commandId, productId).orElseThrow(() -> new ResourceNotFoundException("OrderLine", "id", commandId + productId));
        orderLineRepository.delete(orderLine);
    }
}
