package com.project.ecommerce.dto;

import com.project.ecommerce.entity.User;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommandDTO {
    private Long id;

    @NotNull(message = "La date de commande est obligatoire")
    private LocalDate date;

    @NotNull(message = "Le mode depaiement est obligatoire")
    @NotBlank(message = "Le mode depaiement est obligatoire")
    @Size(min = 1, max= 10, message = "Le mode de paiement contient entre 1 est 10 caractères.")
    private String paymentMode;

    private User user;

}
