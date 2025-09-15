package fst.ecommerce.controller;


import fst.ecommerce.dto.produit.ProduitAdminDto;
import fst.ecommerce.dto.wishListItem.WishListItemDto;
import fst.ecommerce.dto.wishlist.WishlistDto;
import fst.ecommerce.service.wishListItem.WishListItemService;
import fst.ecommerce.service.wishlist.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/wishlist")
@RequiredArgsConstructor

public class WishlistController {

    private final WishlistService service1;
    private final WishListItemService service2;

    // ➕ deposer une Wishlist
    @PostMapping
    public ResponseEntity<WishlistDto> create(@RequestBody WishlistDto wishlistDto) {
        return ResponseEntity.ok(service1.create(wishlistDto));
    }

    // ❌ Supprimer une Wishlist
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service1.delete(id);
        return ResponseEntity.noContent().build();
    }

    // 🔍 Récupérer une Wishlist
    @GetMapping("/{id}")
    public ResponseEntity<WishlistDto> getById(@PathVariable String id) {
        return ResponseEntity.ok(service1.getById(id));
    }

    // ✏️ Mettre à jour une Wishlist
    @PutMapping("/{id}")
    public ResponseEntity<WishlistDto> update(@PathVariable String id, @RequestBody WishlistDto wishlistDto) {
        wishlistDto.setId(id); // s'assurer que l'id vient de l'URL
        return ResponseEntity.ok(service1.update(wishlistDto));
    }
//======================================================================================
    @PostMapping("/{id}/item")
    public ResponseEntity<WishListItemDto> affecte(@PathVariable String id, @RequestBody WishListItemDto itemDto) {
        //here we need to affecte the created Item to a specific WishList not just create it only
            return ResponseEntity.ok(service2.addToWishlist(id,itemDto));
    }

    @DeleteMapping("/{id1}/item/{id2}")
    public ResponseEntity<Void> deleteWishListItem(@PathVariable String id1 , @PathVariable String id2) {
        //think more here !! how do we know which WishList are we going to delete the items from
        //delete WishListItem By id Where WishList id Equals id1
        service2.deleteFromWishList(id1,id2);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/item")
    public ResponseEntity<List<WishListItemDto>> getAllWishlistItems(@PathVariable String id) {
        //also here how do we know that we are getting the WishListItems from the right WishList
        //idea: getAllWishListItemsByWishListId
        return ResponseEntity.ok(service2.getAllByWishlistId(id));
    }


}
