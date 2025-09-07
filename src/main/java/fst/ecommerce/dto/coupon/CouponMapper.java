package fst.ecommerce.dto.coupon;

import fst.ecommerce.entity.Coupon;

public interface CouponMapper {
    CouponDto toDTO(Coupon coupon);
    Coupon toEntity (CouponDto couponDto);

}
