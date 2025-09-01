package fst.ecommerce.controller;

import fst.ecommerce.entity.Panier;
import fst.ecommerce.service.panier.PanierService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/panier")
public class PanierController {

    private final PanierService service;

    public PanierController(PanierService service) {
        this.service = service;
    }

    @GetMapping
    public List<Panier> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Panier getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public Panier create(@RequestBody Panier panier) {
        return service.create(panier);
    }

    @PutMapping("/{id}")
    public Panier update(@PathVariable Long id, @RequestBody Panier panier) {
        return service.update(id, panier);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
