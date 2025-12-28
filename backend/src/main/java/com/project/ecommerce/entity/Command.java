package com.project.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Command {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "La date de commande est obligatoire")
    @Column(nullable = false)
    private LocalDate date;

    @NotNull(message = "Le mode depaiement est obligatoire")
    @NotBlank(message = "Le mode depaiement est obligatoire")
    @Size(min = 1, max= 10, message = "Le mode de paiement contient entre 1 est 10 caractères.")
    @Column(nullable = false, length = 10)
    private String paymentMode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "command")
    @JsonIgnore
    private List<OrderLine> orderLineList;
}
