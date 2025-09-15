package fst.ecommerce.dto.coupon;

import lombok.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CouponDto {
    private String id;
    private String code;
    private double reduction;
    private Date endDate;
    private boolean active;
    private int usageLimit;
    private int usedCount;
}
