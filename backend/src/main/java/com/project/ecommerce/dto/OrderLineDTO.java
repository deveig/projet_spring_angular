package com.project.ecommerce.dto;

import com.project.ecommerce.entity.Command;
import com.project.ecommerce.entity.OrderLineId;
import com.project.ecommerce.entity.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineDTO {

    private OrderLineId id;

    @NotNull(message = "La quantité est obligatoire")
    private Integer quantity;

    @NotNull(message = "Le prix est obligatoire")
    private Double price;

    private Product product;

    private Command command;
}
