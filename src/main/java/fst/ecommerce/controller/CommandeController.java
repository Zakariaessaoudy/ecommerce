package fst.ecommerce.controller;

import fst.ecommerce.dto.commande.CommandeDto;
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

    @PostMapping
    public ResponseEntity<CommandeDto> create(@RequestBody CommandeDto dto) {
        return ResponseEntity.ok(commandeService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommandeDto> getById(@PathVariable String id) {
        return ResponseEntity.ok(commandeService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<CommandeDto>> getAll() {
        return ResponseEntity.ok(commandeService.getAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        commandeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
