package fst.ecommerce.controller;

import fst.ecommerce.dto.produit.*;
import fst.ecommerce.service.produit.ProduitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produits")
@RequiredArgsConstructor
public class ProduitController {

    private final ProduitService produitService;

    // ➕ Créer un produit
    @PostMapping
    public ResponseEntity<ProduitAdminDto> create(@RequestBody ProduitAdminDto produitAdminDto) {
        return ResponseEntity.ok(produitService.create(produitAdminDto));
    }

    // ✏️ Mettre à jour un produit
    @PutMapping("/{id}")
    public ResponseEntity<ProduitAdminDto> update(@PathVariable String id, @RequestBody ProduitAdminDto produitAdminDto) {
        produitAdminDto.setId(id); // s'assurer que l'id vient de l'URL
        return ResponseEntity.ok(produitService.update(produitAdminDto));
    }

    // ❌ Supprimer un produit
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        produitService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // 🔍 Récupérer un produit (version admin)
    @GetMapping("/{id}")
    public ResponseEntity<ProduitAdminDto> getById(@PathVariable String id) {
        return ResponseEntity.ok(produitService.getById(id));
    }

    // 🔍 Récupérer un produit (version détails client)
    @GetMapping("/{id}/details")
    public ResponseEntity<ProduitDetailsDto> getDetails(@PathVariable String id) {
        return ResponseEntity.ok(produitService.findById(id));
    }

    // 📦 Liste de tous les produits (admin)
    @GetMapping
    public ResponseEntity<List<ProduitAdminDto>> getAll() {
        return ResponseEntity.ok(produitService.getAll());
    }

    // 🏠 Produits pour la page d’accueil
    @GetMapping("/landing")
    public ResponseEntity<List<ProduitSimpleDto>> landingProduits() {
        return ResponseEntity.ok(produitService.LandingPageProduits());
    }

    // 🔎 Recherche de produits
    @GetMapping("/search")
    public ResponseEntity<List<ProduitListDto>> search(@RequestParam String keyword) {
        return ResponseEntity.ok(produitService.ListRechercheProduits(keyword));
    }
}


