package fst.ecommerce.controller;

import fst.ecommerce.entity.Avis;
import fst.ecommerce.service.avis.AvisService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/avis")
public classAvisController {

    private final AvisService service;

    public AvisController(AvisService service) {
        this.service = service;
    }

    @GetMapping
    public List<Avis> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Avis getById(@PathVariable Long id) {
        return service.getById(id);
    }


    @PostMapping
    public Avis create(@RequestBody Avis avis) {
        return service.create(avis);
    }

    @PutMapping("/{id}")
    public Avis update(@PathVariable Long id, @RequestBody Avis avis) {
        return service.update(id, avis);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
