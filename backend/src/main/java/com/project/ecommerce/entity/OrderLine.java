package com.project.ecommerce.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderLine {

    @EmbeddedId
    private OrderLineId id = new OrderLineId();

    @NotNull(message = "La quantité est obligatoire")
    @Column(nullable = false)
    private Integer quantity;

    @NotNull(message = "Le prix est obligatoire")
    @Column(nullable = false, length = 6)
    private Double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productId")
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("commandId")
    @JoinColumn(name = "command_id", nullable = false)
    private Command command;

}
