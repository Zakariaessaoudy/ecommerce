package fst.ecommerce.controller;

import fst.ecommerce.entity.PanierItem;
import fst.ecommerce.service.panierItem.PanierItemService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/panierItem")
public class PanierItemController {

    private final PanierItemService service;

    public PanierItemController(PanierItemService service) {
        this.service = service;
    }

    @GetMapping
    public List<PanierItem> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public PanierItem getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public PanierItem create(@RequestBody PanierItem panieritem) {
        return service.create(panieritem);
    }

    @PutMapping("/{id}")
    public PanierItem update(@PathVariable Long id, @RequestBody PanierItem panieritem) {
        return service.update(id, panieritem);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
