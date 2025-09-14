package fst.ecommerce.dto.coupon;

import lombok.*;

import java.util.Date;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CouponDto {
    private String id;
    private String code;
    private double reduction;
    private Date endDate;
}
