package fst.ecommerce.controller;

import fst.ecommerce.entity.WishList;
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
    public List<WishList> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public WishList getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public WishList create(@RequestBody WishList wishlist) {
        return service.create(wishlist);
    }

    @PutMapping("/{id}")
    public WishList update(@PathVariable Long id, @RequestBody WishList wishlist) {
        return service.update(id, wishlist);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
