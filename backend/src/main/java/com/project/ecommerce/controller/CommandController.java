package com.project.ecommerce.controller;

import com.project.ecommerce.dto.CommandDTO;
import com.project.ecommerce.service.CommandService;
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

@Tag(name = "Commandes", description = "API de gestion des commandes")
@RestController
@RequestMapping("/api/commands")
public class CommandController {
    @Autowired
    private CommandService commandService;

    /**
     * GET /api/commands - Liste toutes les commandes
     *
     * @return Liste de toutes les commandes
     */
    @Operation(
            summary = "Récupérer toutes les commandes",
            description = "Retourne la liste complète de toutes les commandes de la boutique"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Liste des commandes récupérées avec succès",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CommandDTO.class)
                    )
            )
    })
    @Secured({"ADMIN"})
    @GetMapping
    public ResponseEntity<List<CommandDTO>> getAllCommands(){
        return ResponseEntity.ok(commandService.getAll());
    }

    /**
     * GET /api/commands/{id} - Récupère une commande par ID
     *
     * @param id L'identifiant de la commande
     * @return La commande trouvée
     */
    @Operation(
            summary = "Récupérer une commande par ID",
            description = "Retourne une commande spécifique identifiée par son ID"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Commande trouvée",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CommandDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Commande non trouvée",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @Secured({"ADMIN"})
    @GetMapping("/{id}")
    public ResponseEntity<CommandDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok(commandService.getById(id));
    }

    /**
     * POST /api/commands - Crée une nouvelle commande
     *
     * @param commandDTO Les données de la commande à créer
     * @return La commande créée avec le status 201 Created
     */
    @Operation(
            summary = "Créer une nouvelle commande",
            description = "Crée une nouvelle commande de la boutique"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Commande créée avec succès",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CommandDTO.class)
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
    @Secured({"USER"})
    @PostMapping
    public ResponseEntity<CommandDTO> createCommand(@RequestBody CommandDTO commandDTO){
        CommandDTO command = commandService.saveCommand(commandDTO);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(command.getId())
                .toUri();
        return ResponseEntity.created(location).body(command);
    }

    /**
     * PUT /api/commands/{id} - Met à jour une commande
     *
     * @param id L'identifiant de la commande
     * @param commandDTO Les nouvelles données de la commande
     * @return La commande mise à jour
     */
    @Operation(
            summary = "Mettre à jour les données de la commande",
            description = "Met à jour la commande de la boutique"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Commande modifiée avec succès",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CommandDTO.class)
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
    @Secured({"ADMIN"})
    @PutMapping("/{id}")
    public ResponseEntity<CommandDTO> update(@PathVariable Long id, @RequestBody CommandDTO commandDTO){
        return ResponseEntity.ok(commandService.update(id, commandDTO));
    }

    /**
     * DELETE /api/commands/{id} - Supprime une commande
     *
     * @param id L'identifiant de la commande
     * @return Status 204 No Content
     */
    @Operation(
            summary = "Supprimer une commande",
            description = "Supprime une commande de la boutique"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Commande supprimée avec succès",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CommandDTO.class)
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
    @Secured({"ADMIN"})
    @DeleteMapping("/{id}")
    public ResponseEntity<CommandDTO> delete(@PathVariable Long id){
        commandService.delete(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * GET /api/commands/search/userId - Récupère la liste des commandes pour un utilisateur
     *
     * @param userId L'identifiant de l'utilisateur
     * @return La commande trouvée
     */
    @Operation(
            summary = "Récupérer la liste des commandes pour un utilisateur",
            description = "Retourne la liste des commandes pour un utilisateur de la boutique"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Commandes trouvées",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CommandDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Commandes non trouvées",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @Secured({"USER"})
    @GetMapping("/search/userId")
    public ResponseEntity<CommandDTO> searchByUser(@RequestParam Long userId){
        return ResponseEntity.ok(commandService.getById(userId));
    }
}
