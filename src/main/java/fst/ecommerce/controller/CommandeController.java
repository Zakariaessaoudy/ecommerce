package fst.ecommerce.controller;

import fst.ecommerce.entity.Commande;
import fst.ecommerce.service.commande.CommandeService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/commande")
public class CommandeController {

    private final CommandeService service;

    public CommandeController(CommandeService service) {
        this.service = service;
    }

    @GetMapping
    public List<Commande> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Commande getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public Commande create(@RequestBody Commande commande) {
        return service.create(commande);
    }

    @PutMapping("/{id}")
    public Commande update(@PathVariable Long id, @RequestBody Commande commande) {
        return service.update(id, commande);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
