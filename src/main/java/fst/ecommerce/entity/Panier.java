package fst.ecommerce.entity;

import jakarta.persistence.*;
import jakarta.persistence.criteria.Fetch;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Panier {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Temporal(TemporalType.DATE)
    private Date dateCreation;

    @OneToOne
    private Utilisateur utilisateur;
    @OneToMany(mappedBy = "panier")
    private Collection<PanierItem> panierItem;

}
