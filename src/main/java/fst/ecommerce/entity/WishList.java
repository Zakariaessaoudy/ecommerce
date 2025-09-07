package fst.ecommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WishList {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Temporal(TemporalType.DATE)
    private Date dateCreation;
    @OneToMany(mappedBy = "wishList")
    private List<WishListItem>  wishListItems ;
}
