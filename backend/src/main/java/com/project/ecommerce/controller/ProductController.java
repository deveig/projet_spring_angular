package com.project.ecommerce.controller;

import com.project.ecommerce.dto.ProductDTO;
import com.project.ecommerce.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Tag(name = "Produits", description = "API de gestion des produits")
@RestController
@RequestMapping("/api/products")
@CrossOrigin("http://localhost:4200")
public class ProductController {
    @Autowired
    private ProductService productService;

    /**
     * GET /api/products - Liste tous les produits
     *
     * @return Liste de tous les produits
     */
    @Operation(
            summary = "Récupérer tous les produits",
            description = "Retourne la liste complète de tous les produits de la boutique"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Liste des produits récupérés avec succès",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProductDTO.class)
                    )
            )
    })
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts(){
        return ResponseEntity.ok(productService.getAll());
    }

    /**
     * GET /api/products/{id} - Récupère un produit par ID
     *
     * @param id L'identifiant d'un produit
     * @return Le produit trouvé
     */
    @Operation(
            summary = "Récupérer un produit par ID",
            description = "Retourne un produit spécifique identifié par son ID"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Produit trouvé",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProductDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Produit non trouvé",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok(productService.getById(id));
    }

    /**
     * POST /api/products - Crée un nouveau produit
     *
     * @param productDTO Les données du produit à créer
     * @return Le produit créé avec le status 201 Created
     */
    @Operation(
            summary = "Créer un nouveau produit",
            description = "Crée un nouveau produit de la boutique"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Produit créé avec succès",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProductDTO.class)
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
    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO){
        ProductDTO product = productService.saveProduct(productDTO);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(product.getId())
                .toUri();
        return ResponseEntity.created(location).body(product);
    }

    /**
     * PUT /api/products/{id} - Met à jour un produit
     *
     * @param id L'identifiant du produit à modifier
     * @param productDTO Les nouvelles données du produit
     * @return Le produit mis à jour
     */
    @Operation(
            summary = "Mettre à jour les données du produit",
            description = "Met à jour le produit de la boutique"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Produit modifié avec succès",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProductDTO.class)
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
    public ResponseEntity<ProductDTO> update(@PathVariable Long id, @RequestBody ProductDTO productDTO){
        return ResponseEntity.ok(productService.update(id, productDTO));
    }

    /**
     * DELETE /api/products/{id} - Supprime un produit
     *
     * @param id L'identifiant du produit à supprimer
     * @return Status 204 No Content
     */
    @Operation(
            summary = "Supprimer un produit",
            description = "Supprime un produit de la boutique"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Produit supprimé avec succès",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProductDTO.class)
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
    public ResponseEntity<ProductDTO> delete(@PathVariable Long id){
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * GET /api/products/search/criteria - Recherche tous les produit avec un critère dans leur description
     *
     * @param criteria Le critère contenu dans la description
     * @return La liste des produits correspondants
     */
    @Operation(
            summary = "Récupérer la liste des produits du critère",
            description = "Retourne la liste des produits correspondant au critère"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Produits trouvés",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProductDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Produits non trouvé",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @GetMapping("/search/criteria")
    public ResponseEntity<List<ProductDTO>> getByCriteria(@RequestParam String criteria){
        return ResponseEntity.ok(productService.getByCriteria(criteria));
    }
}
