package fst.ecommerce.controller;

import fst.ecommerce.dto.panier.PanierDto;
import fst.ecommerce.service.panier.PanierService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/paniers")
public class PanierController {

    private final PanierService panierService;

    public PanierController(PanierService panierService) {
        this.panierService = panierService;
    }

    @PostMapping
    public ResponseEntity<PanierDto> create(@RequestBody PanierDto dto) {
        return ResponseEntity.ok(panierService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PanierDto> getById(@PathVariable String id) {
        return ResponseEntity.ok(panierService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<PanierDto>> getAll() {
        return ResponseEntity.ok(panierService.getAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        panierService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
