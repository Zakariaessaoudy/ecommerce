package fst.ecommerce.controller;

import fst.ecommerce.dto.commande.CommandeDto;
import fst.ecommerce.service.commande.CommandeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/commandes")
public class CommandeController {

    private final CommandeService commandeService;

    public CommandeController(CommandeService commandeService) {
        this.commandeService = commandeService;
    }

    @PostMapping
    public ResponseEntity<CommandeDto> create(@RequestBody CommandeDto dto) {
        return ResponseEntity.ok(commandeService.createCommande(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommandeDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(commandeService.getCommandeById(id));
    }

    @GetMapping
    public ResponseEntity<List<CommandeDto>> getAll() {
        return ResponseEntity.ok(commandeService.getAllCommande());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        commandeService.deleteCommande(id);
        return ResponseEntity.noContent().build();
    }
}
