package fst.ecommerce.controller;


import fst.ecommerce.dto.categorie.CategorieDto;
import fst.ecommerce.service.categorie.CategorieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/categorie")
@RequiredArgsConstructor
public class CategorieController {

    private final CategorieService service;

    // ➕ deposer une categorie
    @PostMapping
    public ResponseEntity<CategorieDto> create(@RequestBody CategorieDto categorieDto) {
        return ResponseEntity.ok(service.create(categorieDto));
    }

    // ❌ Supprimer une categorie
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // 🔍 Récupérer une categorie
    @GetMapping("/{id}")
    public ResponseEntity<CategorieDto> getById(@PathVariable String id) {
        return ResponseEntity.ok(service.getById(id));
    }

    // ✏️ Mettre à jour une categorie
    @PutMapping("/{id}")
    public ResponseEntity<CategorieDto> update(@PathVariable String id, @RequestBody CategorieDto categorieDto) {
        categorieDto.setId(id); // s'assurer que l'id vient de l'URL
        return ResponseEntity.ok(service.update(categorieDto));
    }

    // 📦 Liste de tous les categorie
    @GetMapping
    public ResponseEntity<List<CategorieDto>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }


}
