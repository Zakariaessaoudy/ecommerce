package fst.ecommerce.dto.coupon;

import lombok.Data;

import java.util.Date;

@Data
public class CouponDto {
    private Long id;
    private String code;
    private double reduction;
    private Date endDate;
}
