package fst.ecommerce.controller;

import fst.ecommerce.entity.Utilisateur;
import fst.ecommerce.service.utilisateur.UtilisateurService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/utilisateur")
public class UtilisateurController {

    private final UtilisateurService service;

    public UtilisateurController(UtilisateurService service) {
        this.service = service;
    }

    @GetMapping
    public List<Utilisateur> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Utilisateur getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public Utilisateur create(@RequestBody Utilisateur utilisateur) {
        return service.create(utilisateur);
    }

    @PutMapping("/{id}")
    public Utilisateur update(@PathVariable Long id, @RequestBody Utilisateur utilisateur) {
        return service.update(id, utilisateur);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
