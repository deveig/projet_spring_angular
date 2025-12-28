package com.project.ecommerce.dto;

import com.project.ecommerce.entity.OrderLine;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private Long id;

    @NotNull(message = "Le nom du produit est obligatoire")
    @NotBlank(message = "Le nom du produit est obligatoire")
    @Size(min = 1, max= 50, message = "Le nom du produit contient entre 1 est 50 caractères.")
    private String name;

    @NotNull(message = "La description du produit est obligatoire")
    @NotBlank(message = "La description du produit est obligatoire")
    @Size(min = 1, max= 255, message = "La description du produit contient entre 1 est 255 caractères.")
    private String description;

    private String image;

    @NotNull(message = "Le prix du produit est obligatoire")
    @Positive(message = "Le prix du produit est supérieur à zéro")
    private Double price;

    @NotNull(message = "La quantité du produit est obligatoire")
    @PositiveOrZero(message = "La quantité du produit est supérieure ou égale à zéro")
    private Integer quantity;

}
