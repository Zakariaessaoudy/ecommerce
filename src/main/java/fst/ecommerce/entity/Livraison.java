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
public class Livraison {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    // TODO: add fields
}
