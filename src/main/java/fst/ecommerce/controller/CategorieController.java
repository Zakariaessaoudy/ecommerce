package fst.ecommerce.controller;

import fst.ecommerce.entity.Categorie;
import fst.ecommerce.service.categorie.CategorieService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/categorie")
public class CategorieController {

    private final CategorieService service;

    public CategorieController(CategorieService service) {
        this.service = service;
    }

    @GetMapping
    public List<Categorie> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Categorie getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public Categorie create(@RequestBody Categorie categorie) {
        return service.create(categorie);
    }

    @PutMapping("/{id}")
    public Categorie update(@PathVariable Long id, @RequestBody Categorie categorie) {
        return service.update(id, categorie);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
