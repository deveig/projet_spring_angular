package com.project.ecommerce.dto;

import com.project.ecommerce.entity.Command;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;

    @NotNull(message = "Le nom de l'utilisateur est obligatoire")
    @NotBlank(message = "Le nom de l'utilisateur est obligatoire")
    @Size(min = 1, max= 50, message = "Le nom de l'utilisateur contient entre 1 est 50 caractères.")
    private String lastName;

    @NotNull(message = "Le prénom de l'utilisateur est obligatoire")
    @NotBlank(message = "Le prénom de l'utilisateur est obligatoire")
    @Size(min = 1, max= 50, message = "Le prénom de l'utilisateur contient entre 1 est 50 caractères.")
    private String firstName;

    @NotNull(message = "L'adresse de l'utilisateur est obligatoire")
    @NotBlank(message = "L'adresse de l'utilisateur est obligatoire")
    @Size(min = 1, max= 50, message = "L'adresse de l'utilisateur contient entre 1 est 50 caractères.")
    private String address;

    @NotNull(message = "Le téléphone de l'utilisateur est obligatoire")
    @NotBlank(message = "Le téléphone de l'utilisateur est obligatoire")
    @Size(min = 8, max= 15, message = "Le téléphone de l'utilisateur contient entre 8 est 15 caractères.")
    private String phoneNumber;

    @NotNull(message = "L'email de l'utilisateur est obligatoire")
    @NotBlank(message = "L'email de l'utilisateur est obligatoire")
    @Size(min = 5, max= 50, message = "L'email de l'utilisateur contient entre 5 est 50 caractères.")
    private String email;

    @NotNull(message = "L'username de l'utilisateur est obligatoire")
    @NotBlank(message = "L'username de l'utilisateur est obligatoire")
    @Size(min = 3, max= 50, message = "L'username de l'utilisateur contient entre 3 est 50 caractères.")
    private String username;

    @NotNull(message = "Le mot de passe de l'utilisateur est obligatoire")
    @NotBlank(message = "Le mot de passe de l'utilisateur est obligatoire")
    @Size(min = 8, max= 255, message = "Le mot de passe de l'utilisateur contient entre 8 est 50 caractères.")
    private String password;

    @NotNull(message = "Le role de l'utilisateur est obligatoire")
    @NotBlank(message = "Le role de l'utilisateur est obligatoire")
    @Size(min = 1, max= 10, message = "Le role de l'utilisateur contient entre 1 est 10 caractères.")
    private String role;

    // Constructeur sans mot de passe
    public UserDTO(String username, String lastName, String firstName, String address, String phoneNumber, String email, String role) {
        this.username = username;
        this.lastName = lastName;
        this.firstName = firstName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.role = role;
    }

}
