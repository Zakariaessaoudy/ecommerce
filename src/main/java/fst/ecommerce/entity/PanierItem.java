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
public class PanierItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private int quantite;
    @ManyToOne
    private Panier panier;
    @ManyToOne
    private Produit produit;

}
