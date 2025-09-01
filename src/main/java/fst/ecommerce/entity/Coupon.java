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
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String code;
    private double reduction;
    @Temporal(TemporalType.DATE)
    private Date endDate;

}
