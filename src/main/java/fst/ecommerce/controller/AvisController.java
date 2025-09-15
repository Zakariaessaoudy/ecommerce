package fst.ecommerce.controller;

import fst.ecommerce.dto.avis.AvisDto;
import fst.ecommerce.service.avis.AvisService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/avis")
@RequiredArgsConstructor
public class AvisController {

    private final AvisService service;

    // ➕ deposer un Avis
    @PostMapping
    public ResponseEntity<AvisDto> create(@RequestBody AvisDto avisDto) {
        return ResponseEntity.ok(service.create(avisDto));
    }


    // ❌ Supprimer un Avis
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // 🔍 Récupérer un Avis
    @GetMapping("/{id}")
    public ResponseEntity<AvisDto> getById(@PathVariable String id) {
        return ResponseEntity.ok(service.getById(id));
    }
    // 📦 Liste de tous les Avis
    @GetMapping
    public ResponseEntity<List<AvisDto>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

}
