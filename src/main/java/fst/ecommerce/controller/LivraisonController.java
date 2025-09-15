package fst.ecommerce.controller;

import fst.ecommerce.dto.livraison.LivraisonDto;
import fst.ecommerce.entity.Livraison;
import fst.ecommerce.enums.StatutLivraison;
import fst.ecommerce.service.livraison.LivraisonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/livraisons")
public class LivraisonController {

    private final LivraisonService livraisonService;

    public LivraisonController(LivraisonService livraisonService) {
        this.livraisonService = livraisonService;
    }

    @PostMapping
    public ResponseEntity<LivraisonDto> create(@RequestBody LivraisonDto dto) {
        return ResponseEntity.ok(livraisonService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivraisonDto> getById(@PathVariable String id) {
        return ResponseEntity.ok(livraisonService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<LivraisonDto>> getAll() {
        return ResponseEntity.ok(livraisonService.getAll());
    }

    @PutMapping("/{id}/statut")
    public ResponseEntity<LivraisonDto> updateStatut(
            @PathVariable String id,
            @RequestParam StatutLivraison statut) {
        return ResponseEntity.ok(livraisonService.updateStatut(id, statut));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        livraisonService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
