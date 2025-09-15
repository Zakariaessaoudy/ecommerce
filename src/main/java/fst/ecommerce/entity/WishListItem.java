package fst.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class WishListItem {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Temporal(TemporalType.DATE)
    private Date dateAjout;
    private int priorite;
    @ManyToOne
    @JsonIgnore
    private WishList wishList;
    @ManyToOne
    private Produit produit;

}
