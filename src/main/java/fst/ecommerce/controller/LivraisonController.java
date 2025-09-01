package fst.ecommerce.controller;

import fst.ecommerce.entity.Livraison;
import fst.ecommerce.service.livraison.LivraisonService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/livraison")
public class LivraisonController {

    private final LivraisonService service;

    public LivraisonController(LivraisonService service) {
        this.service = service;
    }

    @GetMapping
    public List<Livraison> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Livraison getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public Livraison create(@RequestBody Livraison livraison) {
        return service.create(livraison);
    }

    @PutMapping("/{id}")
    public Livraison update(@PathVariable Long id, @RequestBody Livraison livraison) {
        return service.update(id, livraison);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
