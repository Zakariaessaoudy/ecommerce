package fst.ecommerce.controller;

import fst.ecommerce.dto.utilisateur.UtilisateurDto;
import fst.ecommerce.entity.Utilisateur;
import fst.ecommerce.service.utilisateur.UtilisateurService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/utilisateur")
@RequiredArgsConstructor
public class UtilisateurController {

    private final UtilisateurService utilisateurService ;

    @PostMapping
    public ResponseEntity<UtilisateurDto> create(@RequestBody UtilisateurDto dto ){
        return ResponseEntity.ok(utilisateurService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UtilisateurDto> getById(@PathVariable String id){
        return ResponseEntity.ok(utilisateurService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<UtilisateurDto>> getAll(){
        return ResponseEntity.ok(utilisateurService.getAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        utilisateurService.delete(id);
        return ResponseEntity.noContent().build();

    }


}
