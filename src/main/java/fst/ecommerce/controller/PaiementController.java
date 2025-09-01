package fst.ecommerce.controller;

import fst.ecommerce.entity.Paiement;
import fst.ecommerce.service.paiement.PaiementService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/paiement")
public class PaiementController {

    private final PaiementService service;

    public PaiementController(PaiementService service) {
        this.service = service;
    }

    @GetMapping
    public List<Paiement> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Paiement getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public Paiement create(@RequestBody Paiement paiement) {
        return service.create(paiement);
    }

    @PutMapping("/{id}")
    public Paiement update(@PathVariable Long id, @RequestBody Paiement paiement) {
        return service.update(id, paiement);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
