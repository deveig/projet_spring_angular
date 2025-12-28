package com.project.ecommerce.controller;

import com.project.ecommerce.dto.LoginRequest;
import com.project.ecommerce.dto.LoginResponse;
import com.project.ecommerce.dto.UserDTO;
import com.project.ecommerce.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Tag(name = "Utilisateurs", description = "API de gestion des utilisateurs")
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * GET /api/users - Liste tous les utilisateurs
     *
     * @return Liste de tous les utilisateurs
     */
    @Operation(
            summary = "Récupérer tous les utilisateurs",
            description = "Retourne la liste complète de tous les utilisateurs de la boutique"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Liste des utilisateurs récupérés avec succès",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserDTO.class)
                    )
            )
    })
    @GetMapping
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return ResponseEntity.ok(userService.getAll());
    }

    /**
     * GET /api/users/{id} - Récupère un utilisateur par ID
     *
     * @param id L'identifiant d'un utilisateur
     * @return L'utilisateur trouvé
     */
    @Operation(
            summary = "Récupérer un utilisateur par ID",
            description = "Retourne un utilisateur spécifique identifié par son ID"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Utilisateur trouvé",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Utilisateur non trouvé",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @GetMapping("/{id}")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<UserDTO> getAllUsers(@PathVariable Long id){
        return ResponseEntity.ok(userService.getById(id));
    }

    /**
     * POST /api/users/register - Crée un nouvel utilisateur
     *
     * @param userDTO Les données de l'utilisateur à créer
     * @return L'utilisateur créé avec le status 201 Created
     */
    @Operation(
            summary = "Créer un nouvel utilisateur",
            description = "Crée un nouvel utilisateur de la boutique"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Utilisateur créé avec succès",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Données invalides",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @PostMapping("/register")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO){
        UserDTO user = userService.saveUser(userDTO, "USER");
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();
        return ResponseEntity.created(location).body(user);
    }

    /**
     * POST /api/users/register-admin - Crée un nouvel administrateur
     *
     * @param userDTO Les données de l'administrateur à créer
     * @return L'administrateur créé avec le status 201 Created
     */
    @Operation(
            summary = "Créer un nouvel administrateur",
            description = "Crée un nouvel administrateur de la boutique"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Administrateur créé avec succès",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Données invalides",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @PostMapping("/register-admin")
    public ResponseEntity<UserDTO> createAdmin(@RequestBody UserDTO userDTO){
        UserDTO user = userService.saveUser(userDTO, "ADMIN");
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();
        return ResponseEntity.created(location).body(user);
    }

    /**
     * POST /api/users/login - Authentification d'un utilisateur
     *
     * @param request Les données d'authentification
     * @return La réponse d'authentification valide avec un token.
     */
    @Operation(
            summary = "Connecter un utilisateur",
            description = "Connecte un utilisateur pour accéder à la boutique"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Connexion établie avec succès",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = LoginResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Non Autorisé",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        try {
            LoginResponse response = userService.authenticate(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(401).build();
        }
    }

    /**
     * PUT /api/users/{id} - Met à jour un utilisateur
     *
     * @param id L'identifiant de l'utilisateur à modifier
     * @param userDTO Les nouvelles données de l'utilisateur
     * @return L'utilisateur mis à jour
     */
    @Operation(
            summary = "Mettre à jour les données de l'utilisateur",
            description = "Met à jour l'utilisateur de la boutique"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Utilisateur modifié avec succès",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Données invalides",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @PutMapping("/{id}")
    @Secured({"ROLE_USER"})
    public ResponseEntity<UserDTO> update(@PathVariable Long id, @RequestBody UserDTO userDTO){
        return ResponseEntity.ok(userService.update(id, userDTO));
    }

    /**
     * DELETE /api/users/{id} - Supprime un utilisateur
     *
     * @param id L'identifiant de l'utilisateur à supprimer
     * @return Status 204 No Content
     */
    @Operation(
            summary = "Supprimer un utilisateur",
            description = "Supprime un utilisateur de la boutique"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Utilisateur supprimé avec succès",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Données invalides",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @DeleteMapping("/{id}")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<UserDTO> delete(@PathVariable Long id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
