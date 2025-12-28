package com.project.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Le nom du produit est obligatoire")
    @NotBlank(message = "Le nom du produit est obligatoire")
    @Size(min = 1, max= 50, message = "Le nom du produit contient entre 1 est 50 caractères.")
    @Column(nullable = false, length = 50)
    private String name;

    @NotNull(message = "La description du produit est obligatoire")
    @NotBlank(message = "La description du produit est obligatoire")
    @Size(min = 1, max= 255, message = "La description du produit contient entre 1 est 255 caractères.")
    @Column(nullable = false, length = 255)
    private String description;

    private String image;

    @NotNull(message = "Le prix du produit est obligatoire")
    @Positive(message = "Le prix du produit est supérieur à zéro")
    @Column(nullable = false, length = 4)
    private Double price;

    @NotNull(message = "La quantité du produit est obligatoire")
    @PositiveOrZero(message = "La quantité du produit est supérieure ou égale à zéro")
    @Column(nullable = false)
    private Integer quantity;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<OrderLine> orderLineList;
}
