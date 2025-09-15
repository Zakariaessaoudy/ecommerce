package fst.ecommerce.controller;

import fst.ecommerce.dto.utilisateur.UtilisateurDto;
import fst.ecommerce.service.utilisateur.UtilisateurService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    // 🔹 Créer un utilisateur
    @PostMapping
    public ResponseEntity<UtilisateurDto> create(@RequestBody UtilisateurDto utilisateurDto) {
        UtilisateurDto saved = utilisateurService.create(utilisateurDto);
        return ResponseEntity.ok(saved);
    }

    // 🔹 Récupérer un utilisateur par ID
    @GetMapping("/{id}")
    public ResponseEntity<UtilisateurDto> getById(@PathVariable Long id) {
        UtilisateurDto utilisateur = utilisateurService.getById(id);
        return ResponseEntity.ok(utilisateur);
    }

    // 🔹 Récupérer tous les utilisateurs
    @GetMapping
    public ResponseEntity<List<UtilisateurDto>> getAll() {
        List<UtilisateurDto> utilisateurs = utilisateurService.getAll();
        return ResponseEntity.ok(utilisateurs);
    }

    // 🔹 Supprimer un utilisateur par ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        utilisateurService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
