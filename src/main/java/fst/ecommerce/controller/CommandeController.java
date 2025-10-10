package fst.ecommerce.controller;

import fst.ecommerce.dto.commande.CommandeDto;
import fst.ecommerce.dto.ligneCommande.LigneCommandDto;
import fst.ecommerce.service.commande.CommandeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/commandes")
@RequiredArgsConstructor
public class CommandeController {

    private final CommandeService commandeService;

    // ✅ Créer une commande
    @PostMapping
    public ResponseEntity<CommandeDto> create(@RequestBody CommandeDto dto) {
        return ResponseEntity.ok(commandeService.create(dto));
    }

    // ✅ Récupérer une commande par ID
    @GetMapping("/{id}")
    public ResponseEntity<CommandeDto> getById(@PathVariable String id) {
        return ResponseEntity.ok(commandeService.getById(id));
    }

    // ✅ Récupérer toutes les commandes
    @GetMapping
    public ResponseEntity<List<CommandeDto>> getAll() {
        return ResponseEntity.ok(commandeService.getAll());
    }

    // ✅ Supprimer une commande
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        commandeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // ✅ Ajouter une ligne à une commande
    @PostMapping("/{commandeId}/lignes")
    public ResponseEntity<CommandeDto> addLigneToCommande(
            @PathVariable String commandeId,
            @RequestBody LigneCommandDto ligneDto
    ) {
        return ResponseEntity.ok(commandeService.addLigneToCommande(commandeId, ligneDto));
    }

    // ✅ Supprimer une ligne d’une commande
    @DeleteMapping("/{commandeId}/lignes/{ligneId}")
    public ResponseEntity<CommandeDto> removeLigneFromCommande(
            @PathVariable String commandeId,
            @PathVariable String ligneId
    ) {
        return ResponseEntity.ok(commandeService.removeLigneFromCommande(commandeId, ligneId));
    }
}
