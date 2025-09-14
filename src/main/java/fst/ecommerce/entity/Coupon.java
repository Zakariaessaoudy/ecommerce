package fst.ecommerce.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter


public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(unique = true, nullable = false)
    private String code;
    private double reduction;
    @Temporal(TemporalType.DATE)
    private Date endDate;

}
