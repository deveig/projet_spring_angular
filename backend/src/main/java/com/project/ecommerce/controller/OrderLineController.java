package com.project.ecommerce.controller;

import com.project.ecommerce.dto.OrderLineDTO;
import com.project.ecommerce.service.OrderLineService;
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

@Tag(name = "Lignes de commandes", description = "API de gestion des lignes de commandes")
@RestController
@RequestMapping("/api/cart")
public class OrderLineController {
    @Autowired
    private OrderLineService orderLineService;

    /**
     * GET /api/cart - Liste toutes les lignes de commandes
     *
     * @return Liste de toutes les lignes de commandes
     */
    @Operation(
            summary = "Récupérer toutes les lignes de commandes",
            description = "Retourne la liste complète de toutes les lignes de commandes de la boutique"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Liste des lignes de commandes récupérées avec succès",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = OrderLineDTO.class)
                    )
            )
    })
    @Secured({"ADMIN"})
    @GetMapping
    public ResponseEntity<List<OrderLineDTO>> getAllOrderLines(){
        return ResponseEntity.ok(orderLineService.getAll());
    }

    /**
     * GET /api/cart/{commandId}/{productId} - Récupère une ligne de commande par ID
     *
     * @param commandId L'identifiant de la commande
     * @param productId L'identifiant du produit
     * @return La ligne de commande trouvée
     */
    @Operation(
            summary = "Récupérer une ligne de commande par ID",
            description = "Retourne une ligne de commande spécifique identifiée par son ID"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ligne de commande trouvée",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = OrderLineDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ligne de commande non trouvée",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @Secured({"ADMIN"})
    @GetMapping("/{commandId}/{productId}")
    public ResponseEntity<OrderLineDTO> getById(@PathVariable Long commandId, @PathVariable Long productId){
        return ResponseEntity.ok(orderLineService.getById(commandId, productId));
    }

    /**
     * POST /api/cart - Crée une nouvelle ligne de commande
     *
     * @param orderLineDTO Les données de la ligne de commande à créer
     * @return La ligne de commande créée avec le status 201 Created
     */
    @Operation(
            summary = "Créer une nouvelle ligne de commande",
            description = "Crée une nouvelle ligne de commande de la boutique"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Ligne de commande créée avec succès",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = OrderLineDTO.class)
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
    public ResponseEntity<OrderLineDTO> createOrderLine(@RequestBody OrderLineDTO orderLineDTO){
        OrderLineDTO orderLine = orderLineService.saveOrderLine(orderLineDTO);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{commandId}/{productId}")
                .buildAndExpand(orderLine.getCommand().getId(), orderLine.getProduct().getId())
                .toUri();
        return ResponseEntity.created(location).body(orderLine);
    }

    /**
     * PUT /api/cart/{commandId}/{productId} - Met à jour une ligne de commande
     *
     * @param commandId L'identifiant de la commande
     * @param productId L'identifiant du produit
     * @param orderLineDTO Les nouvelles données de la ligne de commande
     * @return La ligne de commande mise à jour
     */
    @Operation(
            summary = "Mettre à jour les données de la ligne de commande",
            description = "Met à jour la ligne de commande du panier"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ligne de commande modifiée avec succès",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = OrderLineDTO.class)
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
    @PutMapping("/{commandId}/{productId}")
    public ResponseEntity<OrderLineDTO> update(@PathVariable Long commandId, @PathVariable Long productId, @RequestBody OrderLineDTO orderLineDTO){
        return ResponseEntity.ok(orderLineService.update(commandId, productId, orderLineDTO));
    }

    /**
     * DELETE /api/cart/{commandId}/{productId} - Supprime une ligne de commande
     *
     * @param commandId L'identifiant de la commande
     * @param productId L'identifiant du produit
     * @return Status 204 No Content
     */
    @Operation(
            summary = "Supprimer une ligne de commande",
            description = "Supprime une ligne de commande du panier"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Ligne de commande supprimée avec succès",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = OrderLineDTO.class)
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
    @DeleteMapping("/{commandId}/{productId}")
    public ResponseEntity<OrderLineDTO> delete(@PathVariable Long commandId, @PathVariable Long productId){
        orderLineService.delete(commandId, productId);
        return ResponseEntity.noContent().build();
    }
}
