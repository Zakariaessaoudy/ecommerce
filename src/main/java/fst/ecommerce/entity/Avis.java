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
public class Avis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private int note;
    private String comment;
    @ManyToOne
    private Produit produit;
}
