package fst.ecommerce.controller;

import fst.ecommerce.entity.Produit;
import fst.ecommerce.service.produit.ProduitService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/produit")
public class ProduitController {

    private final ProduitService service;

    public ProduitController(ProduitService service) {
        this.service = service;
    }

    @GetMapping
    public List<Produit> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Produit getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public Produit create(@RequestBody Produit produit) {
        return service.create(produit);
    }

    @PutMapping("/{id}")
    public Produit update(@PathVariable Long id, @RequestBody Produit produit) {
        return service.update(id, produit);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
