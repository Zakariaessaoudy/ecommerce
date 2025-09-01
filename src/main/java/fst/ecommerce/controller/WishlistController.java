package fst.ecommerce.controller;

import fst.ecommerce.entity.Wishlist;
import fst.ecommerce.service.wishlist.WishlistService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {

    private final WishlistService service;

    public WishlistController(WishlistService service) {
        this.service = service;
    }

    @GetMapping
    public List<Wishlist> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Wishlist getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public Wishlist create(@RequestBody Wishlist wishlist) {
        return service.create(wishlist);
    }

    @PutMapping("/{id}")
    public Wishlist update(@PathVariable Long id, @RequestBody Wishlist wishlist) {
        return service.update(id, wishlist);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
